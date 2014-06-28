/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the JobMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public final class JoinSolarSystemChatRoom implements IsSerializable, Action<JoinSolarSystemChatRoomResponse> {

    /**
	 * 
	 */
    private SolarSystemDTO solarSystem;
    /**
	 * 
	 */
    private String userName;
    /**
	 * 
	 */
    private UserDTO user;

    /**
	 * 
	 */
    public JoinSolarSystemChatRoom() {
        super();
    }

    /**
     * @param solarSystem
     *            the solar system to build the chat room from
     * @param userName
     *            the name to use in the chat room
     * @param user
     *            the currently logged in user
     */
    public JoinSolarSystemChatRoom(final SolarSystemDTO solarSystem, final String userName, final UserDTO user) {
        super();
        this.solarSystem = solarSystem;
        this.userName = userName;
        this.user = user;
    }

    /**
     * @return the solarSystem
     */
    public SolarSystemDTO getSolarSystem() {
        return solarSystem;
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
     * @param solarSystem
     *            the solarSystem to set
     */
    public void setSolarSystem(final SolarSystemDTO solarSystem) {
        this.solarSystem = solarSystem;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(final UserDTO user) {
        this.user = user;
    }

    /**
     * @param userName
     *            the name to set
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "JoinSolarSystemChatRoom [solarSystem=" + solarSystem + ", userName=" + userName + ", user=" + user
            + "]";
    }

}
