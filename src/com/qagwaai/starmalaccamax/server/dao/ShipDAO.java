/**
 * UserDAO.java
 * Created by pgirard at 3:21:33 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;

/**
 * @author pgirard
 * 
 */
public interface ShipDAO {

    /**
     * 
     * @param newShip
     *            the new ship to add
     * @return the updated new ship
     * @throws DAOException
     *             if the datastore fails
     */
    ShipDTO addShip(ShipDTO newShip) throws DAOException;

    /**
     * 
     * @return true if all ships were deleted
     * @throws DAOException
     *             if the query cannot be completed
     */
    boolean deleteAllShips() throws DAOException;

    /**
     * 
     * @return all ships in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<ShipDTO> getAllShips() throws DAOException;

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
     * @return all ships in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<ShipDTO> getAllShips(int startRow, int endRow, ArrayList<Filter> criteria, String sortBy)
        throws DAOException;

    /**
     * 
     * @param startRow
     *            paging support - start row
     * @param endRow
     *            paging support - end row
     * @param sortBy
     *            the complete sort string
     * @return all ships in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<ShipDTO> getAllShips(int startRow, int endRow, String sortBy) throws DAOException;

    /**
     * 
     * @param id
     *            the unique key
     * @return the found ship or null
     * @throws DAOException
     *             if the datastore fails
     */
    ShipDTO getShip(Long id) throws DAOException;

    /**
     * 
     * @param captain
     *            the ship's captain to search for
     * @return the found ship or null
     * @throws DAOException
     *             if the datastore fails
     */
    ShipDTO getShipForCaptain(CaptainDTO captain) throws DAOException;

    /**
     * 
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalShips() throws DAOException;

    /**
     * @param criteria
     *            the filter criteria
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalShipsWithFilter(ArrayList<Filter> criteria) throws DAOException;

    /**
     * 
     * @param ship
     *            the ship to be removed
     * @return true if the removal was successful
     * @throws DAOException
     *             if the datastore fails
     */
    boolean removeShip(ShipDTO ship) throws DAOException;

    /**
     * 
     * @param ship
     *            the ship to update
     * @return the updated ship
     * @throws DAOException
     *             if the datastore fails
     */
    ShipDTO updateShip(ShipDTO ship) throws DAOException;

}
