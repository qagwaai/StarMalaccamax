package com.qagwaai.starmalaccamax.server;

import java.util.logging.Logger;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.qagwaai.starmalaccamax.client.service.ChannelService;
import com.qagwaai.starmalaccamax.client.service.action.Action;
import com.qagwaai.starmalaccamax.client.service.action.AddChatMessage;
import com.qagwaai.starmalaccamax.client.service.action.AddChatMessageResponse;
import com.qagwaai.starmalaccamax.client.service.action.JoinGameChatRoom;
import com.qagwaai.starmalaccamax.client.service.action.JoinGameChatRoomResponse;
import com.qagwaai.starmalaccamax.client.service.action.JoinSolarSystemChatRoom;
import com.qagwaai.starmalaccamax.client.service.action.JoinSolarSystemChatRoomResponse;
import com.qagwaai.starmalaccamax.client.service.action.LeaveChatRoom;
import com.qagwaai.starmalaccamax.client.service.action.LeaveChatRoomResponse;
import com.qagwaai.starmalaccamax.client.service.action.Response;
import com.qagwaai.starmalaccamax.server.business.ChatRoomManager;
import com.qagwaai.starmalaccamax.server.business.model.RoomUserDTO;
import com.qagwaai.starmalaccamax.shared.ServiceException;
import com.qagwaai.starmalaccamax.shared.model.ChannelUserId;
import com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO;
import com.qagwaai.starmalaccamax.shared.model.channel.ChatRoomMessage;
import com.qagwaai.starmalaccamax.shared.model.channel.LeftChatRoomMessage;

/**
 * 
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class ChannelServiceImpl extends RemoteServiceServlet implements ChannelService {
    /**
     * Logger for this service
     */
    private static Logger log = Logger.getLogger(ChannelServiceImpl.class.getName());

    /**
     * 
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Response> T execute(final Action<T> action) throws ServiceException {
        Object response = null;
        long startTime = Instrumentation.callStart("ChannelServiceImpl: " + action);
        UserService userService = UserServiceFactory.getUserService();
        try {
            if (!userService.isUserLoggedIn()) {
                log.warning("Not logged in user tried to execute action " + action.getClass().getName());
                throw new ServiceException("User is not logged in");
            }
        } catch (NullPointerException npe) {
            // assume that we are in a test unit
            LocalServiceTestHelper helper =
                new LocalServiceTestHelper(new LocalUserServiceTestConfig(), new LocalDatastoreServiceTestConfig())
                    .setEnvIsAdmin(true).setEnvIsLoggedIn(true);
            helper.setUp();
        }
        if (!userService.isUserLoggedIn()) {
            throw new ServiceException("User is not logged in");
        }
        if (action instanceof JoinSolarSystemChatRoom) {
            response = (T) executeJoinSolarSystemChannel((JoinSolarSystemChatRoom) action);
        } else if (action instanceof AddChatMessage) {
            response = (T) executeAddChatMessage((AddChatMessage) action);
        } else if (action instanceof JoinGameChatRoom) {
            response = (T) executeJoinGameChatRoom((JoinGameChatRoom) action);
        } else if (action instanceof LeaveChatRoom) {
            response = (T) executeLeaveChatRoom((LeaveChatRoom) action);
        }
        if (response != null) {
            Instrumentation.callEnd("ChannelServiceImpl: " + response, startTime, action.getClass().getSimpleName(),
                response);
            return (T) response;
        }

        log.severe("Action not Implemented: " + action.getClass().getName());
        throw new ServiceException("Action not Implemented");
    }

    /**
     * 
     * @param action
     *            a LeaveChatRoom action
     * @return a LeaveChatRoomResponse with the status of the leaving
     * @throws ServiceException
     *             if the service could not complete the removal
     */
    private LeaveChatRoomResponse executeLeaveChatRoom(final LeaveChatRoom action) throws ServiceException {
        LeaveChatRoomResponse response = new LeaveChatRoomResponse();

        response.setSuccess(false);
        if (action.getChatRoom() != null) {
            ChatRoomDTO chatRoom = ChatRoomManager.getInstance().getChatRoomById(action.getChatRoom().getId());
            if (chatRoom != null) {
                RoomUserDTO roomUser =
                    ChatRoomManager.getInstance().getRoomUserInChatRoom(action.getUserName(), action.getChatRoom());
                if (roomUser != null) {
                    ChatRoomManager.getInstance().leaveChatRoom(roomUser, chatRoom);
                    ChatRoomManager.getInstance().sendMessageToAllInRoom(chatRoom, action.getUserName(),
                        new LeftChatRoomMessage(action.getUserName(), chatRoom));
                    response.setSuccess(true);
                }
            } else {
                throw new ServiceException("Chat room not created: " + action.getChatRoom().getName());
            }
        }
        return response;
    }

    /**
     * 
     * @param action
     *            an AddChatMessage action
     * @return the AddChatMessageResponse with the status of the add
     * @throws ServiceException
     *             if the service could not complete the action
     */
    private AddChatMessageResponse executeAddChatMessage(final AddChatMessage action) throws ServiceException {
        AddChatMessageResponse response = new AddChatMessageResponse();
        // validate that we have created the room
        ChatRoomDTO chatRoom = ChatRoomManager.getInstance().getChatRoomById(action.getChatRoom().getId());
        if (chatRoom != null) {
            ChatRoomManager.getInstance().sendMessageToAllInRoom(chatRoom, action.getName(),
                new ChatRoomMessage(action.getMessage(), action.getName(), chatRoom));
        } else {
            throw new ServiceException("Chat room not created: " + action.getChatRoom().getName());
        }

        return response;
    }

    /**
     * 
     * @param action
     *            a JoinGameChatRoom action
     * @return a JoinGameChatRoom with the status of the join
     * @throws ServiceException
     *             if the service could not complete the action
     */
    private JoinGameChatRoomResponse executeJoinGameChatRoom(final JoinGameChatRoom action) throws ServiceException {
        JoinGameChatRoomResponse response = new JoinGameChatRoomResponse();

        ChatRoomDTO chatRoom = ChatRoomManager.getInstance().getChatRoom(action.getRoomName());
        if (chatRoom == null) {
            chatRoom = ChatRoomManager.getInstance().createGameChatRoom(action.getRoomName());
        }

        if (action.getRoomName() != null) {
            ChatRoomManager.getInstance().joinChatRoom(action.getUserName(),
                new ChannelUserId(action.getUser().getUniqueId()), chatRoom);
        } else {
            throw new ServiceException("Room name cannot be null");
        }

        response.setChatRoom(chatRoom);
        return response;
    }

    /**
     * 
     * @param action
     *            the JoinSolarSystemChannel action
     * @return a JoinSolarSystemChannelReponse object including the user token
     * @throws ServiceException
     *             if the channel could not be joined
     */
    private JoinSolarSystemChatRoomResponse executeJoinSolarSystemChannel(final JoinSolarSystemChatRoom action)
        throws ServiceException {
        JoinSolarSystemChatRoomResponse response = new JoinSolarSystemChatRoomResponse();

        ChatRoomDTO chatRoom = ChatRoomManager.getInstance().getChatRoom(action.getSolarSystem());
        if (chatRoom == null) {
            chatRoom = ChatRoomManager.getInstance().createSolarSystemChatRoom(action.getSolarSystem());
        }

        if (action.getSolarSystem() != null) {
            ChatRoomManager.getInstance().joinChatRoom(action.getUserName(),
                new ChannelUserId(action.getUser().getUniqueId()), chatRoom);
        } else {
            throw new ServiceException("Solar system cannot be null");
        }
        response.setChatRoom(chatRoom);
        return response;
    }
}
