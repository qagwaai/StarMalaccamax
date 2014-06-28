/**
 * UserDAO.java
 * Created by pgirard at 3:21:33 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * @author pgirard
 * 
 */
public interface PlanetDAO {

    /**
     * 
     * @param newPlanet
     *            the new planet to add
     * @return the updated new planet
     * @throws DAOException
     *             if the datastore fails
     */
    PlanetDTO addPlanet(PlanetDTO newPlanet) throws DAOException;

    /**
     * 
     * @param newPlanets
     *            the new planets to add
     * @return the updated new planet
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<PlanetDTO> bulkAddPlanet(ArrayList<PlanetDTO> newPlanets) throws DAOException;

    /**
     * 
     * @return true if successful
     * @throws DAOException
     *             if the datastore fails
     */
    boolean deleteAllPlanets() throws DAOException;

    /**
     * 
     * @return all planets in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<PlanetDTO> getAllPlanets() throws DAOException;

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
     * @return all planets in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<PlanetDTO> getAllPlanets(int startRow, int endRow, ArrayList<Filter> criteria, String sortBy)
        throws DAOException;

    /**
     * 
     * @param startRow
     *            paging support - start row
     * @param endRow
     *            paging support - end row
     * @param sortBy
     *            the complete sort string
     * @return all planets in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<PlanetDTO> getAllPlanets(int startRow, int endRow, String sortBy) throws DAOException;

    /**
     * 
     * @param solarSystemId
     *            the solar system id used in the query
     * @return a list of planets that are not gas giants in the solar system
     * @throws DAOException
     *             if the query could not be fulfilled
     */
    ArrayList<PlanetDTO> getNonGasGiantPlanetsForSolarSystem(Long solarSystemId) throws DAOException;

    /**
     * 
     * @param id
     *            the unique key
     * @return the found planet or null
     * @throws DAOException
     *             if the datastore fails
     */
    PlanetDTO getPlanet(Long id) throws DAOException;

    /**
     * 
     * @param solarSystemId
     *            the solar system id used in the query
     * @return a list of planets in the solar system - includes moons
     * @throws DAOException
     *             if the query could not be fulfilled
     */
    ArrayList<PlanetDTO> getPlanetsForSolarSystem(Long solarSystemId) throws DAOException;

    /**
     * 
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalPlanets() throws DAOException;

    /**
     * @param criteria
     *            the filter criteria
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalPlanetsWithFilter(ArrayList<Filter> criteria) throws DAOException;

    /**
     * 
     * @param planet
     *            the planet to be removed
     * @return true if the removal was successful
     * @throws DAOException
     *             if the datastore fails
     */
    boolean removePlanet(PlanetDTO planet) throws DAOException;

    /**
     * 
     * @param planet
     *            the planet to update
     * @return the updated planet
     * @throws DAOException
     *             if the datastore fails
     */
    PlanetDTO updatePlanet(PlanetDTO planet) throws DAOException;
}
