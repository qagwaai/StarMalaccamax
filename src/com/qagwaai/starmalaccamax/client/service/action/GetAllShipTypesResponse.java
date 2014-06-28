/**
 * GetAllUsersResponse.java
 * Created by pgirard at 2:07:29 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.ShipTypeDTO;

/**
 * @author pgirard
 * 
 */
public final class GetAllShipTypesResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<ShipTypeDTO> shipTypes;
    /**
	 * 
	 */
    private int totalShipTypes;

    /**
     * @return the users
     */
    public ArrayList<ShipTypeDTO> getShipTypes() {
        return shipTypes;
    }

    /**
     * @return the totalShipTypes
     */
    public int getTotalShipTypes() {
        return totalShipTypes;
    }

    /**
     * @param shipTypes
     *            the users to set
     */
    public void setShipTypes(final ArrayList<ShipTypeDTO> shipTypes) {
        this.shipTypes = shipTypes;
    }

    /**
     * @param totalShipTypes
     *            the totalShipTypes to set
     */
    public void setTotalShipTypes(final int totalShipTypes) {
        this.totalShipTypes = totalShipTypes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetAllShipTypesResponse [shipTypes=" + shipTypes + ", totalShipTypes=" + totalShipTypes + "]";
    }

}
