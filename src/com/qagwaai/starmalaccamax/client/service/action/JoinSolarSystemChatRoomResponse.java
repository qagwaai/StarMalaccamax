/**
 * GetUserResponse.java
 * Created by pgirard at 1:40:09 PM on Aug 19, 2010
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
public final class JoinSolarSystemChatRoomResponse extends AbstractResponse implements IsSerializable {
    /**
	 * 
	 */
    private ChatRoomDTO chatRoom;

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
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "JoinSolarSystemChatRoomResponse [chatRoom=" + chatRoom + "]";
    }

}