/**
 * ShipLaunch.java
 * com.qagwaai.starmalaccamax.shared.model.gameevent
 * StarMalaccamax
 * Created Mar 10, 2011 at 12:37:17 PM
 */
package com.qagwaai.starmalaccamax.shared.model.gameevent;

import java.io.Serializable;

import com.google.code.twig.annotation.Embedded;
import com.google.code.twig.annotation.Id;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;

/**
 * @author pgirard
 * 
 */

@SuppressWarnings("serial")
public final class ShipLaunchActivity implements GameActivity, Serializable {

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
    @Embedded
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
     *            the unique ship id
     * @param destination
     *            the destination
     */
    public ShipLaunchActivity(final long shipId, final LocationDTO destination) {
        super();
        this.shipId = shipId;
        this.destination = destination;
    }

    /**
	 * 
	 */
    public ShipLaunchActivity() {
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
        return "ShipLaunchActivity [id=" + id + ", shipId=" + shipId + ", destination=" + destination + "]";
    }

}
