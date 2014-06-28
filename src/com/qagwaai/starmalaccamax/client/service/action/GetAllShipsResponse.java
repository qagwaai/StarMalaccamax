/**
 * GetAllUsersResponse.java
 * Created by pgirard at 2:07:29 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;

/**
 * @author pgirard
 * 
 */
public final class GetAllShipsResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<ShipDTO> ships;
    /**
	 * 
	 */
    private int totalShips;

    /**
     * @return the users
     */
    public ArrayList<ShipDTO> getShips() {
        return ships;
    }

    /**
     * @return the totalShips
     */
    public int getTotalShips() {
        return totalShips;
    }

    /**
     * @param ships
     *            the users to set
     */
    public void setShips(final ArrayList<ShipDTO> ships) {
        this.ships = ships;
    }

    /**
     * @param totalShips
     *            the totalShips to set
     */
    public void setTotalShips(final int totalShips) {
        this.totalShips = totalShips;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetAllShipsResponse [ships=" + ships + ", totalShips=" + totalShips + "]";
    }

}
