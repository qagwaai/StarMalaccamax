/**
 * GetUserResponse.java
 * Created by pgirard at 1:40:09 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.ShipTypeDTO;

/**
 * @author pgirard
 * 
 */
public final class UpdateShipTypeResponse extends AbstractResponse implements IsSerializable {
    /**
	 * 
	 */
    private ShipTypeDTO shipType;

    /**
     * @return the shipType
     */
    public ShipTypeDTO getShipType() {
        return shipType;
    }

    /**
     * @param shipType
     *            the shipType to set
     */
    public void setShipType(final ShipTypeDTO shipType) {
        this.shipType = shipType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "UpdateShipTypeResponse [shipType=" + shipType + "]";
    }

}
