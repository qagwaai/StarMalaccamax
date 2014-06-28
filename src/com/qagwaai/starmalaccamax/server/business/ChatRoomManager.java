/**
 * 
 */
package com.qagwaai.starmalaccamax.server.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import com.qagwaai.starmalaccamax.server.business.model.RoomUserDTO;
import com.qagwaai.starmalaccamax.shared.model.ChannelUserId;
import com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;
import com.qagwaai.starmalaccamax.shared.model.channel.JoinedChatRoomMessage;
import com.qagwaai.starmalaccamax.shared.model.channel.Message;

/**
 * @author pgirard
 * 
 */
public final class ChatRoomManager {

    /**
	 * 
	 */
    private static Logger log = Logger.getLogger(ChatRoomManager.class.getName());
    /**
	 * 
	 */
    private static ChatRoomManager ref;
    /**
	 * 
	 */
    private static ArrayList<ChatRoomDTO> rooms = new ArrayList<ChatRoomDTO>();
    /**
	 * 
	 */
    private static HashMap<ChatRoomDTO, ArrayList<RoomUserDTO>> attendees = new HashMap<ChatRoomDTO, ArrayList<RoomUserDTO>>();

    /**
     * 
     * @return the chat room manager instance
     */
    public static ChatRoomManager getInstance() {
        if (ref == null) {
            ref = new ChatRoomManager();
        }
        return ref;
    }

    /**
	 * 
	 */
    private ChatRoomManager() {
    }

    /**
     * 
     * @param chatRoom
     *            the chat room to add
     */
    public void addChatRoom(final ChatRoomDTO chatRoom) {
        rooms.add(chatRoom);
    }

    /**
     * 
     * @param roomName
     *            the room name to create
     * @return the created chat room
     */
    public ChatRoomDTO createGameChatRoom(final String roomName) {
        ChatRoomDTO newRoom = new ChatRoomDTO();
        newRoom.setId(roomName);
        // TODO get a real ID for the above
        newRoom.setName(roomName);
        rooms.add(newRoom);
        attendees.put(newRoom, new ArrayList<RoomUserDTO>());
        return newRoom;
    }

    /**
     * 
     * @param solarSystem
     *            the solar system chat room to create
     * @return the created chat room
     */
    public ChatRoomDTO createSolarSystemChatRoom(final SolarSystemDTO solarSystem) {
        ChatRoomDTO newRoom = new ChatRoomDTO();
        newRoom.setId(solarSystem.getId().toString() + "|" + solarSystem.getName());
        newRoom.setName(solarSystem.getName());
        rooms.add(newRoom);
        attendees.put(newRoom, new ArrayList<RoomUserDTO>());
        return newRoom;
    }

    /**
     * 
     * @param roomToFind
     *            the chat room to find
     * @return the found chat room or null
     */
    public ChatRoomDTO getChatRoom(final ChatRoomDTO roomToFind) {
        for (ChatRoomDTO chatRoom : rooms) {
            if (chatRoom.equals(roomToFind)) {
                return chatRoom;
            }
        }
        return null;
    }

    /**
     * 
     * @param solarSystem
     *            the system to look for
     * @return the found chat room or null
     */
    public ChatRoomDTO getChatRoom(final SolarSystemDTO solarSystem) {
        for (ChatRoomDTO chatRoom : rooms) {
            if (chatRoom.getId().equals(solarSystem.getId().toString() + "|" + solarSystem.getName())) {
                return chatRoom;
            }
        }
        return null;
    }

    /**
     * 
     * @param name
     *            the name to search for
     * @return the found chat room or null
     */
    public ChatRoomDTO getChatRoom(final String name) {
        for (ChatRoomDTO chatRoom : rooms) {
            if (chatRoom.getName().equals(name)) {
                return chatRoom;
            }
        }
        return null;
    }

    /**
     * 
     * @param id
     *            the unique chat room identifier
     * @return the found chat room or null
     */
    public ChatRoomDTO getChatRoomById(final String id) {
        for (ChatRoomDTO chatRoom : rooms) {
            if (chatRoom.getId().equals(id)) {
                return chatRoom;
            }
        }
        return null;
    }

    /**
     * 
     * @param name
     *            the name to search for
     * @param chatRoom
     *            the chat room to look in
     * @return true if found
     */
    private boolean isUserInRoom(final String name, final ChatRoomDTO chatRoom) {
        for (RoomUserDTO user : attendees.get(chatRoom)) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param userName
     *            the user name to look for
     * @param chatRoom
     *            the chat room to look in
     * @return the found user or null
     */
    public RoomUserDTO getRoomUserInChatRoom(final String userName, final ChatRoomDTO chatRoom) {
        for (RoomUserDTO user : attendees.get(chatRoom)) {
            if (user.getName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    /**
     * 
     * @param name
     *            the name of the user
     * @param channelId
     *            the channelid of the user
     * @param chatRoom
     *            the room to join
     */
    public void joinChatRoom(final String name, final ChannelUserId channelId, final ChatRoomDTO chatRoom) {
        if (!isUserInRoom(name, chatRoom)) {
            RoomUserDTO newUser = new RoomUserDTO();
            newUser.setChannelUserId(channelId);
            newUser.setName(name);
            if (attendees.get(chatRoom) == null) {
                attendees.put(chatRoom, new ArrayList<RoomUserDTO>());
            }
            attendees.get(chatRoom).add(newUser);
        }
        sendMessageToAllInRoom(chatRoom, name, new JoinedChatRoomMessage(name, chatRoom));
    }

    /**
     * 
     * @param user
     *            the user to remove
     * @param chatRoom
     *            the chat room to remove the user from
     */
    public void leaveChatRoom(final RoomUserDTO user, final ChatRoomDTO chatRoom) {
        attendees.get(chatRoom).remove(user);
    }

    /**
     * 
     * @param chatRoom
     *            the chat room to remove
     */
    public void removeChatRoom(final ChatRoomDTO chatRoom) {
        rooms.remove(chatRoom);
    }

    /**
     * 
     * @param chatRoom
     *            the chat room to send the messages in
     * @param name
     *            the name "from"
     * @param message
     *            the message to send
     */
    public void sendMessageToAllInRoom(final ChatRoomDTO chatRoom, final String name, final Message message) {

        if (chatRoom == null) {
            return;
        }
        if (name == null) {
            return;
        }
        if (message == null) {
            return;
        }
        ArrayList<RoomUserDTO> users = attendees.get(chatRoom);
        ArrayList<ChannelUserId> ids = new ArrayList<ChannelUserId>();
        for (RoomUserDTO user : users) {
            ids.add(user.getChannelUserId());
        }
        log.info("Pushing message to all users in [ " + ids + "]");
        if (ids.size() > 0) {
            ChannelManager.pushMessage(ids, message);
        } else {
            log.info("No users in room: pushed message to no one");
        }
        /*
         * for (RoomUser user : users) { if (user.getStatus() == 0) {
         * log.info("Sending message to " + user.getName() +
         * " with channel token: " + user.getToken()); try {
         * channelService.sendMessage(new ChannelMessage(user.getToken(),
         * formatMessage(name, message))); } catch (Throwable t) {
         * t.printStackTrace(); log.severe(t.getMessage()); // continue with
         * next user in queue... user.setStatus(-1); } } else {
         * log.info("skipping user " + user.getName() +
         * " because it is marked as invalid."); } }
         */

    }
}
