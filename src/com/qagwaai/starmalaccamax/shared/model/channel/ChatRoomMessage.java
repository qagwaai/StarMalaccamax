/**
 * ChatRoomMessage.java
 * com.qagwaai.starmalaccamax.server.model.channel
 * StarMalaccamax
 * Created Mar 1, 2011 at 10:07:08 AM
 */
package com.qagwaai.starmalaccamax.shared.model.channel;

import java.io.Serializable;

import com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class ChatRoomMessage implements Message, Serializable {
    /**
	 * 
	 */
    private String message;
    /**
	 * 
	 */
    private String from;
    /**
	 * 
	 */
    private ChatRoomDTO chatRoom;

    /**
	 * 
	 */
    public ChatRoomMessage() {
        super();

    }

    /**
     * @param message
     *            the message
     * @param from
     *            the username
     * @param chatRoom
     *            the room
     */
    public ChatRoomMessage(final String message, final String from, final ChatRoomDTO chatRoom) {
        super();
        this.message = message;
        this.from = from;
        this.chatRoom = chatRoom;
    }

    /**
     * @return the chatRoom
     */
    public ChatRoomDTO getChatRoom() {
        return chatRoom;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param chatRoom
     *            the chatRoom to set
     */
    public void setChatRoom(final ChatRoomDTO chatRoom) {
        this.chatRoom = chatRoom;
    }

    /**
     * @param from
     *            the from to set
     */
    public void setFrom(final String from) {
        this.from = from;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ChatRoomMessage [message=" + message + ", from=" + from + ", chatRoom=" + chatRoom + "]";
    }

}
