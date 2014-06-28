/**
 * GetCurrentUserResponse.java
 * Created by pgirard at 3:57:56 PM on Jul 22, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.actions package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;

/**
 * @author pgirard
 * 
 */
public final class GetShipForCaptainResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ShipDTO ship;

    /**
	 * 
	 */
    private CaptainDTO captain;

    /**
     * @return the captain
     */
    public CaptainDTO getCaptain() {
        return captain;
    }

    /**
     * @return the ship
     */
    public ShipDTO getShip() {
        return ship;
    }

    /**
     * @param captain
     *            the captain to set
     */
    public void setCaptain(final CaptainDTO captain) {
        this.captain = captain;
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
        return "GetShipForCaptainResponse [ship=" + ship + ", captain=" + captain + "]";
    }

}
