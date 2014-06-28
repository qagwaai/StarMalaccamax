/**
 * GetAllUsersResponse.java
 * Created by pgirard at 2:07:29 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public final class GetAllUsersResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<UserDTO> users;
    /**
	 * 
	 */
    private int totalUsers;

    /**
     * @return the totalUsers
     */
    public int getTotalUsers() {
        return totalUsers;
    }

    /**
     * @return the users
     */
    public ArrayList<UserDTO> getUsers() {
        return users;
    }

    /**
     * @param totalUsers
     *            the totalUsers to set
     */
    public void setTotalUsers(final int totalUsers) {
        this.totalUsers = totalUsers;
    }

    /**
     * @param users
     *            the users to set
     */
    public void setUsers(final ArrayList<UserDTO> users) {
        this.users = users;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetAllUsersResponse [users=" + users + ", totalUsers=" + totalUsers + "]";
    }

}
