/**
 * UserRecord.java
 * Created by pgirard at 10:29:55 AM on Aug 20, 2010
 * in the com.qagwaai.starmalaccamax.shared.model.record package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.qagwaai.starmalaccamax.shared.model.Ship;
import com.qagwaai.starmalaccamax.shared.model.ShipAttributes;
import com.qagwaai.starmalaccamax.shared.model.ShipCargoDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipTravelStatus;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class ShipRecord extends ListGridRecord implements Ship {

    /**
	 * 
	 */
    public static final String ID = "id";
    /**
	 * 
	 */
    public static final String OWNER_ID = "ownerId";

    /**
	 * 
	 */
    public static final String ATTRIBUTES = "attributes";

    /**
	 * 
	 */
    public static final String NAME = "name";
    /**
	 * 
	 */
    public static final String SHIP_TYPE_ID = "shipTypeId";

    /**
	 * 
	 */
    public static final String CARGO = "cargo";

    /**
	 * 
	 */
    public static final String TRAVEL_STATUS = "travelStatus";

    /**
	 * 
	 */
    public static final String LOCATION = "location";

    /**
	 * 
	 */
    public ShipRecord() {

    }

    /**
     * 
     * @param ship
     *            the ship DTO
     */
    public ShipRecord(final ShipDTO ship) {
        setId(ship.getId());
        setOwnerId(ship.getOwnerId());
        setName(ship.getName());
        setShipTypeId(ship.getShipTypeId());
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<ShipCargoDTO> getCargo() {
        // TODO return the ship cargo
        return null;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        try {
            return LongConverter.fromJavaScript(this, ShipRecord.ID);
        } catch (DataException e) {
            GWT.log("Identity field could not be converted to Long", e);
        }
        return -1L;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public LocationDTO getLocation() {
        // TODO return the location
        return null;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return getAttributeAsString(ShipRecord.NAME);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getOwnerId() {
        try {
            return LongConverter.fromJavaScript(this, ShipRecord.OWNER_ID);
        } catch (DataException e) {
            GWT.log("Identity field could not be converted to Long", e);
        }
        return -1L;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ShipAttributes getShipAttributes() {
        // TODO return the attributes
        return null;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ShipTravelStatus getShipTravelStatus() {
        // TODO return the travel status
        return null;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getShipTypeId() {
        try {
            return LongConverter.fromJavaScript(this, ShipRecord.SHIP_TYPE_ID);
        } catch (DataException e) {
            GWT.log("Identity field could not be converted to Long", e);
        }
        return -1L;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setCargo(final ArrayList<ShipCargoDTO> cargo) {
        // TODO set the ship cargo

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setId(final Long id) {
        setAttribute(ShipRecord.ID, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setLocation(final LocationDTO location) {
        // TODO set the ship location

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setName(final String name) {
        setAttribute(ShipRecord.NAME, name);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setOwnerId(final Long id) {
        this.setAttribute(ShipRecord.OWNER_ID, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setShipAttributes(final ShipAttributes attributes) {
        // TODO set the ship attributes
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setShipTravelStatus(final ShipTravelStatus status) {
        // TODO set the ship travel status

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setShipTypeId(final Long shipTypeId) {
        setAttribute(ShipRecord.SHIP_TYPE_ID, shipTypeId);
    }

    /**
     * 
     * @return a solar system object from this record
     */
    public ShipDTO toShip() {
        ShipDTO p = new ShipDTO();
        p.setId(getId());
        p.setOwnerId(getOwnerId());
        p.setName(getName());
        p.setShipTypeId(getShipTypeId());
        return p;
    }

}
