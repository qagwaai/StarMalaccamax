/**
 * GetCurrentUser.java
 * Created by pgirard at 3:57:10 PM on Jul 22, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.actions package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public final class GetShipsForUser implements IsSerializable, Action<GetShipsForUserResponse> {
    /**
	 * 
	 */
    private UserDTO user;

    /**
	 * 
	 */
    public GetShipsForUser() {

    }

    /**
     * 
     * @param user
     *            the user to search for
     */
    public GetShipsForUser(final UserDTO user) {
        this.user = user;
    }

    /**
     * @return the user
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(final UserDTO user) {
        this.user = user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetShipsForUser [user=" + user + "]";
    }

}
