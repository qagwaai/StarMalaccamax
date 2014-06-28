/**
 * ShipArrivalActivity.java
 * com.qagwaai.starmalaccamax.shared.model.gameevent
 * StarMalaccamax
 * Created Mar 11, 2011 at 2:50:03 PM
 */
package com.qagwaai.starmalaccamax.shared.model.gameevent;

import java.io.Serializable;

import com.google.code.twig.annotation.Id;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class ShipArrivalActivity implements GameActivity, Serializable {

    /**
	 * 
	 */
    @Id
    private long id;
    /**
	 * 
	 */
    private long shipId;
    /**
	 * 
	 */
    private LocationDTO destination;

    /**
     * @return the shipId
     */
    public long getShipId() {
        return shipId;
    }

    /**
     * @param shipId
     *            the shipId to set
     */
    public void setShipId(final long shipId) {
        this.shipId = shipId;
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
     * @param shipId
     *            the unique id of the ship
     * @param destination
     *            the destination
     */
    public ShipArrivalActivity(final long shipId, final LocationDTO destination) {
        super();
        this.shipId = shipId;
        this.destination = destination;
    }

    /**
	 * 
	 */
    public ShipArrivalActivity() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ShipArrivalActivity [id=" + id + ", shipId=" + shipId + ", destination=" + destination + "]";
    }

}
