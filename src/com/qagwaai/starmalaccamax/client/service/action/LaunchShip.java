/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the JobMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;

/**
 * @author pgirard
 * 
 */
public final class LaunchShip implements IsSerializable, Action<LaunchShipResponse> {

    /**
	 * 
	 */
    private ShipDTO ship;
    /**
	 * 
	 */
    private LocationDTO destination;

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
     * @return the destination
     */
    public LocationDTO getDestination() {
        return destination;
    }

    /**
     * @param destination
     *            the destination to set
     */
    public void setDestination(final LocationDTO destination) {
        this.destination = destination;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "LaunchShip [ship=" + ship + ", destination=" + destination + "]";
    }

    /**
     * @param ship
     *            the ship
     * @param destination
     *            the destination
     */
    public LaunchShip(final ShipDTO ship, final LocationDTO destination) {
        super();
        this.ship = ship;
        this.destination = destination;
    }

    /**
	 * 
	 */
    public LaunchShip() {
        super();
    }

}
