/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;

/**
 * @author pgirard
 * 
 */
public final class UpdateShip implements IsSerializable, Action<UpdateShipResponse> {

    /**
	 * 
	 */
    private ShipDTO ship;

    /**
	 * 
	 */
    public UpdateShip() {

    }

    /**
     * @param ship
     *            the Ship to update
     */
    public UpdateShip(final ShipDTO ship) {
        this.ship = ship;
    }

    /**
     * @return the ship
     */
    public ShipDTO getShip() {
        return ship;
    }

    /**
     * @param ship
     *            the ship to set
     */
    public void setShip(final ShipDTO ship) {
        this.ship = ship;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "UpdateShip [ship=" + ship + "]";
    }

}
