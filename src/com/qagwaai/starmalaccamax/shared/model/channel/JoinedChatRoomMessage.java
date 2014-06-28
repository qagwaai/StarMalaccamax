/**
 * JoinedChatRoomMessage.java
 * com.qagwaai.starmalaccamax.shared.model.channel
 * StarMalaccamax
 * Created Mar 9, 2011 at 9:13:10 AM
 */
package com.qagwaai.starmalaccamax.shared.model.channel;

import java.io.Serializable;

import com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class JoinedChatRoomMessage implements Message, Serializable {
    /**
	 * 
	 */
    private String userName;

    /**
	 * 
	 */
    private ChatRoomDTO chatRoom;

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * @return the chatRoom
     */
    public ChatRoomDTO getChatRoom() {
        return chatRoom;
    }

    /**
     * @param chatRoom
     *            the chatRoom to set
     */
    public void setChatRoom(final ChatRoomDTO chatRoom) {
        this.chatRoom = chatRoom;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "JoinedChatRoomMessage [userName=" + userName + ", chatRoom=" + chatRoom + "]";
    }

    /**
     * @param userName
     *            the user name
     * @param chatRoom
     *            the chat room
     */
    public JoinedChatRoomMessage(final String userName, final ChatRoomDTO chatRoom) {
        super();
        this.userName = userName;
        this.chatRoom = chatRoom;
    }

    /**
	 * 
	 */
    public JoinedChatRoomMessage() {
        super();

    }

}
