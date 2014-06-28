/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO;

/**
 * @author pgirard
 * 
 */
public final class AddChatMessage implements IsSerializable, Action<AddChatMessageResponse> {

    /**
	 * 
	 */
    private String name;
    /**
	 * 
	 */
    private String message;
    /**
	 * 
	 */
    private ChatRoomDTO chatRoom;

    /**
	 * 
	 */
    public AddChatMessage() {

    }

    /**
     * @param name
     *            the user name used in the chat room
     * @param message
     *            the message to add
     * @param chatRoom
     *            the room to add the message to
     */
    public AddChatMessage(final String name, final String message, final ChatRoomDTO chatRoom) {
        super();
        this.name = name;
        this.message = message;
        this.chatRoom = chatRoom;
    }

    /**
     * @return the chatChannel
     */
    public ChatRoomDTO getChatRoom() {
        return chatRoom;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param chatRoom
     *            the chatChannel to set
     */
    public void setChatRoom(final ChatRoomDTO chatRoom) {
        this.chatRoom = chatRoom;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AddChatMessage [name=" + name + ", message=" + message + ", chatRoom=" + chatRoom + "]";
    }

}
