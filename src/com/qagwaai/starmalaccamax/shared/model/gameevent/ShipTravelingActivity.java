/**
 * ShipTravelingActivity.java
 * com.qagwaai.starmalaccamax.shared.model.gameevent
 * StarMalaccamax
 * Created Mar 14, 2011 at 2:15:09 PM
 */
package com.qagwaai.starmalaccamax.shared.model.gameevent;

import java.io.Serializable;
import java.util.Date;

import com.google.code.twig.annotation.Embedded;
import com.google.code.twig.annotation.Id;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.qagwaai.starmalaccamax.shared.model.TravelVectorDTO;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class ShipTravelingActivity implements GameActivity, Serializable {

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
    private TravelVectorDTO vector;
    /**
	 * 
	 */
    @Embedded
    private LocationDTO destination;
    /**
	 * 
	 */
    private Date arrival;

    /**
	 * 
	 */
    private long numTicks;

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
     * @return the vector
     */
    public TravelVectorDTO getVector() {
        return vector;
    }

    /**
     * @param vector
     *            the vector to set
     */
    public void setVector(final TravelVectorDTO vector) {
        this.vector = vector;
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
     * @return the numTicks
     */
    public long getNumTicks() {
        return numTicks;
    }

    /**
     * @param numTicks
     *            the numTicks to set
     */
    public void setNumTicks(final long numTicks) {
        this.numTicks = numTicks;
    }

    /**
	 * 
	 */
    public ShipTravelingActivity() {
        super();
    }

    /**
     * @return the arrival
     */
    public Date getArrival() {
        return arrival;
    }

    /**
     * @param arrival
     *            the arrival to set
     */
    public void setArrival(final Date arrival) {
        this.arrival = arrival;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ShipTravelingActivity [id=" + id + ", shipId=" + shipId + ", vector=" + vector + ", destination="
            + destination + ", arrival=" + arrival + ", numTicks=" + numTicks + "]";
    }

}
