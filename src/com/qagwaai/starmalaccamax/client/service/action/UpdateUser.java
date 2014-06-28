/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public final class UpdateUser implements IsSerializable, Action<UpdateUserResponse> {

    /**
	 * 
	 */
    private UserDTO user;

    /**
	 * 
	 */
    public UpdateUser() {

    }

    /**
     * @param user
     *            the User to update
     */
    public UpdateUser(final UserDTO user) {
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
        return "UpdateUser [user=" + user + "]";
    }

}
