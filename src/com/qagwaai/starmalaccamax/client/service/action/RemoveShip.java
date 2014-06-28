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
public final class RemoveShip implements IsSerializable, Action<RemoveShipResponse> {

    /**
	 * 
	 */
    private ShipDTO ship;

    /**
	 * 
	 */
    public RemoveShip() {

    }

    /**
     * @param ship
     *            the Ship to remove
     */
    public RemoveShip(final ShipDTO ship) {
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
        return "RemoveShip [ship=" + ship + "]";
    }

}
