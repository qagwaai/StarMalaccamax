/**
 * ShipDTO.java
 * Created by pgirard at 1:40:34 PM on Aug 31, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.code.twig.annotation.Embedded;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.Entity;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
@Entity
public final class ShipDTO implements IsSerializable, Serializable, Ship {

    /**
	 * 
	 */
    private Long shipTypeId;

    /**
	 * 
	 */
    @Embedded
    private ShipAttributes shipAttributes = new ShipAttributes();

    /**
	 * 
	 */
    private String name;

    /**
	 * 
	 */
    @com.google.code.twig.annotation.Id
    @com.googlecode.objectify.annotation.Id
    @com.googlecode.objectify.annotation.Index
    private Long id;

    /**
	 * 
	 */
    @com.googlecode.objectify.annotation.Index
    private Long ownerId;
    /**
	 * 
	 */
    @Embedded
    private LocationDTO location;

    /**
	 * 
	 */
    @Embedded
    private ArrayList<ShipCargoDTO> shipCargo = new ArrayList<ShipCargoDTO>();

    /**
	 * 
	 */
    private String travelStatus;

    /**
	 * 
	 */
    public ShipDTO() {
        travelStatus = ShipTravelStatus.Docked.toString();
        location = new LocationDTO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<ShipCargoDTO> getCargo() {
        return shipCargo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public LocationDTO getLocation() {
        return location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return the ownerId
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ShipAttributes getShipAttributes() {
        return shipAttributes;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ShipTravelStatus getShipTravelStatus() {
        return ShipTravelStatus.valueOf(travelStatus);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getShipTypeId() {
        return shipTypeId;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setCargo(final ArrayList<ShipCargoDTO> cargo) {
        this.shipCargo = cargo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final Long id) {
        this.id = id;

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setLocation(final LocationDTO location) {
        this.location = location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param ownerId
     *            the ownerId to set
     */
    public void setOwnerId(final Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setShipAttributes(final ShipAttributes shipAttributes) {
        this.shipAttributes = shipAttributes;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setShipTravelStatus(final ShipTravelStatus status) {
        this.travelStatus = status.toString();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setShipTypeId(final Long shipTypeId) {
        this.shipTypeId = shipTypeId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ShipDTO [id=" + id + ", location=" + location + ", name=" + name + ", ownerId=" + ownerId
            + ", shipAttributes=" + shipAttributes + ", shipCargo=" + shipCargo + ", shipTypeId=" + shipTypeId
            + ", travelStatus=" + travelStatus + "]";
    }
    public static String getFieldGetter(String fieldName) {
    	String methodName = null;
    	
    	if (fieldName.equals("id")) {
    		methodName = "getId";
    	} else if (fieldName.equals("ownerId")) {
    		methodName = "getOwnerId";
    	}
    	return methodName;
    }

}
