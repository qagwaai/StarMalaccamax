/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the JobMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public final class JoinGameChatRoom implements IsSerializable, Action<JoinGameChatRoomResponse> {

    /**
	 * 
	 */
    private String userName;
    /**
	 * 
	 */
    private String roomName;
    /**
	 * 
	 */
    private UserDTO user;

    /**
	 * 
	 */
    public JoinGameChatRoom() {

    }

    /**
     * @param userName
     *            the name to use in this chat room
     * @param roomName
     *            the room to join
     * @param user
     *            the currently logged in user
     */
    public JoinGameChatRoom(final String userName, final String roomName, final UserDTO user) {
        super();
        this.userName = userName;
        this.roomName = roomName;
        this.user = user;
    }

    /**
     * @return the roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * @return the user
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * @return the name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param roomName
     *            the roomName to set
     */
    public void setRoomName(final String roomName) {
        this.roomName = roomName;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(final UserDTO user) {
        this.user = user;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setUserName(final String name) {
        this.userName = name;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "JoinGameChatRoom [userName=" + userName + ", roomName=" + roomName + ", user=" + user + "]";
    }

}
