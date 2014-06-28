/**
 * Ship.java
 * Created by pgirard at 1:37:04 PM on Aug 31, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.util.ArrayList;

/**
 * @author pgirard
 * 
 */
public interface Ship extends Model {

    /**
     * 
     * @return the commodities stored on the ship
     */
    ArrayList<ShipCargoDTO> getCargo();

    /**
     * @return the id
     */
    Long getId();

    /**
     * 
     * @return the location of the ship
     */
    LocationDTO getLocation();

    /**
     * 
     * @return the name
     */
    String getName();

    /**
     * 
     * @return the captain owner
     */
    Long getOwnerId();

    /**
     * 
     * @return the properties of the ship
     */
    ShipAttributes getShipAttributes();

    /**
     * 
     * @return the travel status of this ship
     */
    ShipTravelStatus getShipTravelStatus();

    /**
     * 
     * @return the ship configuration
     */
    Long getShipTypeId();

    /**
     * 
     * @param cargo
     *            the cargo stored on the ship
     */
    void setCargo(ArrayList<ShipCargoDTO> cargo);

    /**
     * @param id
     *            the id to set
     */
    void setId(final Long id);

    /**
     * 
     * @param location
     *            the location of the ship
     */
    void setLocation(LocationDTO location);

    /**
     * 
     * @param name
     *            the name
     */
    void setName(String name);

    /**
     * 
     * @param id
     *            the captain owner
     */
    void setOwnerId(Long id);

    /**
     * 
     * @param shipAttributes
     *            the properties of the ship
     */
    void setShipAttributes(ShipAttributes shipAttributes);

    /**
     * 
     * @param status
     *            the travel status of this ship
     */
    void setShipTravelStatus(ShipTravelStatus status);

    /**
     * 
     * @param shipTypeId
     *            the ship configuration
     */
    void setShipTypeId(Long shipTypeId);
}
