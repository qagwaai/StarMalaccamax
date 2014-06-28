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
public final class AddShip implements IsSerializable, Action<AddShipResponse> {

    /**
	 * 
	 */
    private ShipDTO ship;

    /**
	 * 
	 */
    public AddShip() {

    }

    /**
     * @param ship
     *            the Ship to add
     */
    public AddShip(final ShipDTO ship) {
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
     *            the Ship to set
     */
    public void setShip(final ShipDTO ship) {
        this.ship = ship;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "AddShip [ship=" + ship + "]";
    }

}
