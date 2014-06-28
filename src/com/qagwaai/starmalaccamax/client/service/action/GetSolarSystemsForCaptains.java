/**
 * GetAllUsers.java
 * Created by pgirard at 2:06:53 PM on Aug 19, 2010
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
public final class GetSolarSystemsForCaptains implements IsSerializable, Action<GetSolarSystemsForCaptainsResponse> {

    /**
	 * 
	 */
    private UserDTO user;

    /**
	 * 
	 */
    public GetSolarSystemsForCaptains() {

    }

    /**
     * @param user
     *            the user used in the query
     */
    public GetSolarSystemsForCaptains(final UserDTO user) {
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
        return "GetSolarSystemsForCaptains [user=" + user + "]";
    }

}
