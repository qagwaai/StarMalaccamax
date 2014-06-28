/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the JobMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO;

/**
 * @author pgirard
 * 
 */
public final class LeaveChatRoom implements IsSerializable, Action<LeaveChatRoomResponse> {

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
        return "LeftChatRoom [userName=" + userName + ", chatRoom=" + chatRoom + "]";
    }

    /**
     * @param userName
     *            the user name
     * @param chatRoom
     *            the chat room
     */
    public LeaveChatRoom(final String userName, final ChatRoomDTO chatRoom) {
        super();
        this.userName = userName;
        this.chatRoom = chatRoom;
    }

    /**
	 * 
	 */
    public LeaveChatRoom() {
        super();
    }

}
