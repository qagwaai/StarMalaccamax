/**
 * GetCurrentUserResponse.java
 * Created by pgirard at 3:57:56 PM on Jul 22, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.actions package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public final class GetShipsForUserResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<ShipDTO> ships;

    /**
	 * 
	 */
    private UserDTO user;

    /**
     * @return the ships
     */
    public ArrayList<ShipDTO> getShips() {
        return ships;
    }

    /**
     * @return the user
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * @param ships
     *            the ships to set
     */
    public void setShips(final ArrayList<ShipDTO> ships) {
        this.ships = ships;
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
        return "GetShipsForUserResponse [ships=" + ships + ", user=" + user + "]";
    }

}
