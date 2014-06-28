/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
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
public final class BulkAddShipType implements IsSerializable, Action<BulkAddShipTypeResponse> {

    /**
	 * 
	 */
    private ArrayList<ShipTypeDTO> shipTypes;

    /**
	 * 
	 */
    public BulkAddShipType() {

    }

    /**
     * @param shipTypes
     *            the ShipTypes to add
     */
    public BulkAddShipType(final ArrayList<ShipTypeDTO> shipTypes) {
        this.shipTypes = shipTypes;
    }

    /**
     * @return the shipTypes
     */
    public ArrayList<ShipTypeDTO> getShipTypes() {
        return shipTypes;
    }

    /**
     * @param shipTypes
     *            the ShipTypes to set
     */
    public void setShipTypes(final ArrayList<ShipTypeDTO> shipTypes) {
        this.shipTypes = shipTypes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final int maxLen = 10;
        return "BulkAddShipType [shipTypes="
            + (shipTypes != null ? shipTypes.subList(0, Math.min(shipTypes.size(), maxLen)) : null) + "]";
    }

}
