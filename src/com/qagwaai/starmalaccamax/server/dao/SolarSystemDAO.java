/**
 * UserDAO.java
 * Created by pgirard at 3:21:33 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketDistanceDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketOpportunityForShipDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDistanceDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;

/**
 * @author pgirard
 * 
 */
public interface SolarSystemDAO {

    /**
     * 
     * @param newClosest
     *            the new closest to add
     * @return the updated new closest
     * @throws DAOException
     *             if the datastore fails
     */
    ClosestDTO addClosest(ClosestDTO newClosest) throws DAOException;

    /**
     * 
     * @param newSolarSystem
     *            the new solarSystem to add
     * @return the updated new solarSystem
     * @throws DAOException
     *             if the datastore fails
     */
    SolarSystemDTO addSolarSystem(SolarSystemDTO newSolarSystem) throws DAOException;

    /**
     * 
     * @param newClosests
     *            the new closests to add
     * @return the updated new closest
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<ClosestDTO> bulkAddClosest(ArrayList<ClosestDTO> newClosests) throws DAOException;

    /**
     * 
     * @param newSolarSystems
     *            the new solarSystems to add
     * @return the updated new solarSystem
     * @throws DAOException
     *             if the datastore fails or cannot fulfill the request
     */
    ArrayList<SolarSystemDTO> bulkAddSolarSystem(ArrayList<SolarSystemDTO> newSolarSystems) throws DAOException;

    /**
     * 
     * @return true if all closest records are deleted
     * @throws DAOException
     *             if the datastore fails or cannot fulfill the request
     */
    boolean deleteAllClosest() throws DAOException;

    /**
     * 
     * @return true if all the solar system records are deleted
     * @throws DAOException
     *             if the datastore fails or cannot fulfill the request
     */
    boolean deleteAllSolarSystems() throws DAOException;

    /**
     * 
     * @return all closests in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<ClosestDTO> getAllClosests() throws DAOException;

    /**
     * 
     * @param startRow
     *            paging support - start row
     * @param endRow
     *            paging support - end row
     * @param criteria
     *            if there is any filter
     * @param sortBy
     *            the fully qualified sort string
     * @return all closests in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<ClosestDTO> getAllClosests(int startRow, int endRow, ArrayList<Filter> criteria, String sortBy)
        throws DAOException;

    /**
     * 
     * @param startRow
     *            paging support - start row
     * @param endRow
     *            paging support - end row
     * @param sortBy
     *            the complete sort string
     * @return all closests in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<ClosestDTO> getAllClosests(int startRow, int endRow, String sortBy) throws DAOException;

    /**
     * 
     * @return all solarSystems in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<SolarSystemDTO> getAllSolarSystems() throws DAOException;

    /**
     * 
     * @param startRow
     *            paging support - start row
     * @param endRow
     *            paging support - end row
     * @param criteria
     *            if there is any filter
     * @param sortBy
     *            the fully qualified sort string
     * @return all solarSystems in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<SolarSystemDTO> getAllSolarSystems(int startRow, int endRow, ArrayList<Filter> criteria, String sortBy)
        throws DAOException;

    /************************* closest ******************************/

    /**
     * 
     * @param startRow
     *            paging support - start row
     * @param endRow
     *            paging support - end row
     * @param sortBy
     *            the complete sort string
     * @return all solarSystems in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<SolarSystemDTO> getAllSolarSystems(int startRow, int endRow, String sortBy) throws DAOException;

    /**
     * 
     * @param id
     *            the unique key
     * @return the found closest or null
     * @throws DAOException
     *             if the datastore fails
     */
    ClosestDTO getClosest(Long id) throws DAOException;

    /**
     * 
     * @param planet
     *            the planet to center the search on
     * @return a list of markets and their distances
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<MarketOpportunityForShipDTO> getClosestMarketsForPlanet(PlanetDTO planet) throws DAOException;

    /**
     * 
     * @param planetIds
     *            a list of ids to get market's closest to
     * @return a list of markets
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<MarketDTO> getClosestMarketsForPlanets(ArrayList<Long> planetIds) throws DAOException;

    /**
     * 
     * @param ship
     *            the ships to use in the query
     * @return a list of all market distance records
     * @throws DAOException
     *             if the datastore fails or cannot fulfill the request
     */
    ArrayList<MarketDistanceDTO> getClosestMarketsForShip(ShipDTO ship) throws DAOException;

    /**
     * 
     * @param planet
     *            the planet to use for the query
     * @return a list of planets
     * @throws DAOException
     *             if the datastore fails or cannot fulfill the request
     */
    ArrayList<PlanetDistanceDTO> getClosestPlanetsForPlanet(PlanetDTO planet) throws DAOException;

    /**
     * 
     * @param ships
     *            the ships to use in the query
     * @return a list of planets and their distances
     * @throws DAOException
     *             if the datastore fails or cannot fulfill the request
     */
    ArrayList<PlanetDistanceDTO> getClosestPlanetsForShips(ArrayList<ShipDTO> ships) throws DAOException;

    /**
     * 
     * @param planet
     *            the planet to use in the query
     * @return a list of closest records
     * @throws DAOException
     *             if the datastore fails or cannot fulfill the request
     */
    ArrayList<ClosestDTO> getClosestsForPlanet(PlanetDTO planet) throws DAOException;

    /**
     * 
     * @param id
     *            the unique key
     * @return the found solarSystem or null
     * @throws DAOException
     *             if the datastore fails
     */
    SolarSystemDTO getSolarSystem(Long id) throws DAOException;

    /**
     * 
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalClosests() throws DAOException;

    /**
     * @param criteria
     *            the filter criteria
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalClosestsWithFilter(ArrayList<Filter> criteria) throws DAOException;

    /**
     * 
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalSolarSystems() throws DAOException;

    /**
     * @param criteria
     *            the filter criteria
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalSolarSystemsWithFilter(ArrayList<Filter> criteria) throws DAOException;

    /**
     * 
     * @param closest
     *            the closest to be removed
     * @return true if the removal was successful
     * @throws DAOException
     *             if the datastore fails
     */
    boolean removeClosest(ClosestDTO closest) throws DAOException;

    /**
     * 
     * @param solarSystem
     *            the solarSystem to be removed
     * @return true if the removal was successful
     * @throws DAOException
     *             if the datastore fails
     */
    boolean removeSolarSystem(SolarSystemDTO solarSystem) throws DAOException;

    /**
     * 
     * @param closest
     *            the closest to update
     * @return the updated closest
     * @throws DAOException
     *             if the datastore fails
     */
    ClosestDTO updateClosest(ClosestDTO closest) throws DAOException;

    /**
     * 
     * @param solarSystem
     *            the solarSystem to update
     * @return the updated solarSystem
     * @throws DAOException
     *             if the datastore fails
     */
    SolarSystemDTO updateSolarSystem(SolarSystemDTO solarSystem) throws DAOException;
}
