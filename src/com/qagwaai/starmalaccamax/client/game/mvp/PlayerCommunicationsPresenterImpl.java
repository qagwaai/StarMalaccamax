/**
 * PlayerCalendarPresenter.java
 * Created by pgirard at 1:12:07 PM on Dec 2, 2010
 * in the com.qagwaai.starmalaccamax.client.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.client.core.LoginWatcher;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.event.PushMessageReceivedEvent;
import com.qagwaai.starmalaccamax.client.event.PushMessageReceivedHandler;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerCommCaptainDoubleClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerCommCloseChannelClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerCommEditorKeyPressHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerCommSendMessageClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.AddChatMessage;
import com.qagwaai.starmalaccamax.client.service.action.AddChatMessageResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetSolarSystemsForCaptains;
import com.qagwaai.starmalaccamax.client.service.action.GetSolarSystemsForCaptainsResponse;
import com.qagwaai.starmalaccamax.client.service.action.JoinGameChatRoom;
import com.qagwaai.starmalaccamax.client.service.action.JoinSolarSystemChatRoom;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.client.service.helpers.JoinedGameChatRoom;
import com.qagwaai.starmalaccamax.client.service.helpers.JoinedSolarSystemChatRoom;
import com.qagwaai.starmalaccamax.shared.model.CaptainSolarSystemDTO;
import com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;
import com.qagwaai.starmalaccamax.shared.model.User;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.qagwaai.starmalaccamax.shared.model.channel.ChatRoomMessage;
import com.qagwaai.starmalaccamax.shared.model.channel.JoinedChatRoomMessage;
import com.qagwaai.starmalaccamax.shared.model.channel.LeftChatRoomMessage;
import com.qagwaai.starmalaccamax.shared.model.channel.Message;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;
import com.smartgwt.client.widgets.grid.events.RowContextClickHandler;

/**
 * @author pgirard
 * 
 */
public class PlayerCommunicationsPresenterImpl extends AbstractPresenter<PlayerCommunicationsView, User> implements
    PushMessageReceivedHandler, PlayerCommunicationsPresenter {


    /**
     * 
     * @param eventBus
     *            the bus to publish on
     * @param view
     *            an associated view
     * @param model
     *            the user model
     */
    public PlayerCommunicationsPresenterImpl(final EventBus eventBus, final PlayerCommunicationsViewImpl view, final User model) {
        super(eventBus, view, model);
        view.setPresenter(this);
        view.layout();
        eventBus.addHandler(PushMessageReceivedEvent.getType(), this);

        ServiceFactory.getSolarSystemService().execute(new GetSolarSystemsForCaptains((UserDTO) model),
            new BaseAsyncCallback<GetSolarSystemsForCaptainsResponse>() {

                @Override
                public void onFailure(final Throwable caught) {
                    super.onFailure(caught);
                    view.say("Error", "Could not get channels for captains: " + caught);

                }

                @Override
                public void onSuccess(final GetSolarSystemsForCaptainsResponse result) {
                    super.onSuccess(result);
                    ArrayList<CaptainSolarSystemDTO> ss = result.getResults();
                    ArrayList<ListGridRecord> records = new ArrayList<ListGridRecord>();
                    for (CaptainSolarSystemDTO css : ss) {
                        ListGridRecord record = new ListGridRecord();
                        record.setAttribute(PlayerCommunicationsViewImpl.CHAT_ROOM_SYSTEM_NAME, css.getSolarSystem()
                            .getName());
                        record
                            .setAttribute(PlayerCommunicationsViewImpl.CHAT_ROOM_CAPTAIN_NAME, css.getCaptain().getName());
                        record.setAttribute(PlayerCommunicationsViewImpl.SOLAR_SYSTEM_OBJ, css.getSolarSystem());
                        record.setAttribute(PlayerCommunicationsViewImpl.CAPTAIN_OBJ, css.getCaptain());
                        records.add(record);
                    }
                    view.getCaptainsListGrid().setData(records.toArray(new ListGridRecord[records.size()]));
                }
            });

        view.getRoomTabSet().addCloseClickHandler(new PlayerCommCloseChannelClickHandlerImplementation(view));

        view.getCaptainsListGrid().addDoubleClickHandler(new PlayerCommCaptainDoubleClickHandlerImplementation(view, this));
        view.getRichTextEditor().addKeyPressHandler(new PlayerCommEditorKeyPressHandlerImplementation(view, this));

        view.getSendButton().addClickHandler(new PlayerCommSendMessageClickHandlerImplementation(view, this));

    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsPresenter#addMessage(com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO, java.lang.String, java.lang.String)
	 */
    @Override
	public void addMessage(final ChatRoomDTO chatRoom, final String name, final String message) {
        getView().postMessageToRoom(name, chatRoom, message);
        getView().getRichTextEditor().setValue("");

        ServiceFactory.getChannelService().execute(new AddChatMessage(name, message, chatRoom),
            new BaseAsyncCallback<AddChatMessageResponse>() {

                @Override
                public void onFailure(final Throwable caught) {
                    super.onFailure(caught);
                    getView().say("Chat Error", "Could not send message: " + caught.getMessage());

                }

                @Override
                public void onSuccess(final AddChatMessageResponse result) {
                    super.onSuccess(result);
                    GWT.log("chat message send successful");

                }
            });
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void destroyView() {
        getView().destroy();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void hideView() {
        getView().hide();

    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsPresenter#joinChatRoom(com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO, java.lang.String)
	 */
    @Override
	public final void joinChatRoom(final SolarSystemDTO solarSystem, final String name) {
        ServiceFactory.getChannelService().execute(
            new JoinSolarSystemChatRoom(solarSystem, name, LoginWatcher.getInstance().getLastEvent().getCurrentUser()),
            new JoinedSolarSystemChatRoom() {

                @Override
                public void joined(final ChatRoomDTO chatRoom) {
                    String id = getView().addRoomTab(chatRoom, solarSystem.getId() + "|" + solarSystem.getName(), name);
                    addChatProfileListener(id);
                    // getView().postMessageToRoom(name, chatRoom,
                    // "Joining chat room");
                }
            });
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsPresenter#joinGameChatRoom(java.lang.String)
	 */
    @Override
	public final void joinGameChatRoom(final String roomName) {
        final String userName = getModel().getNickname();
        ServiceFactory.getChannelService().execute(
            new JoinGameChatRoom(userName, roomName, LoginWatcher.getInstance().getLastEvent().getCurrentUser()),
            new JoinedGameChatRoom() {

                @Override
                public void joined(final ChatRoomDTO chatRoom) {
                    String id = getView().addRoomTab(chatRoom, roomName, userName);
                    addChatProfileListener(id);
                    // getView().postMessageToRoom(userName, chatRoom,
                    // "Joining chat room");
                }
            });
    }

    private final void addChatProfileListener(final String id) {
        if (getView().getChatAttendees().get(id) != null) {
            getView().getChatAttendees().get(id).addRowContextClickHandler(new RowContextClickHandler() {

                @Override
                public void onRowContextClick(RowContextClickEvent event) {
                    ListGridRecord record = event.getRecord();
                    getView().say("Properties", "View user profile for " + record.getAttributeAsString("name"));
                }
            });
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void renderView() {
        getView().render();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void showView() {
        getView().show();

    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsPresenter#onPushMessageReceived(com.qagwaai.starmalaccamax.client.event.PushMessageReceivedEvent)
	 */
    @Override
	public final void onPushMessageReceived(final PushMessageReceivedEvent event) {
        Message message = event.getMessage();
        if (message instanceof ChatRoomMessage) {
            ChatRoomMessage chatMessage = (ChatRoomMessage) message;
            ChatRoomDTO chatRoom = chatMessage.getChatRoom();
            getView().postMessageToRoom(chatMessage.getFrom(), chatRoom, chatMessage.getMessage());
        } else if (message instanceof JoinedChatRoomMessage) {
            JoinedChatRoomMessage joinedMessage = (JoinedChatRoomMessage) message;
            ChatRoomDTO chatRoom = joinedMessage.getChatRoom();
            String userName = joinedMessage.getUserName();

            getView().postMessageToRoom("ChatRoom Administrator", chatRoom, userName + " has joined this room");
            getView().addUserToRoomList(userName, chatRoom);
        } else if (message instanceof LeftChatRoomMessage) {
            LeftChatRoomMessage leftMessage = (LeftChatRoomMessage) message;
            ChatRoomDTO chatRoom = leftMessage.getChatRoom();
            String userName = leftMessage.getUserName();

            getView().postMessageToRoom("ChatRoom Administrator", chatRoom, userName + " has left this room");
            getView().removeUserFromRoomList(userName, chatRoom);
        }

    }

}
