/**
 * UserDAO.java
 * Created by pgirard at 3:21:33 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.ShipTypeDTO;

/**
 * @author pgirard
 * 
 */
public interface ShipTypeDAO {

    /**
     * 
     * @param newShipType
     *            the new shipType to add
     * @return the updated new shipType
     * @throws DAOException
     *             if the datastore fails
     */
    ShipTypeDTO addShipType(ShipTypeDTO newShipType) throws DAOException;

    /**
     * 
     * @param newShipTypes
     *            the new shipTypes to add
     * @return the updated new shipType
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<ShipTypeDTO> bulkAddShipType(ArrayList<ShipTypeDTO> newShipTypes) throws DAOException;

    /**
     * 
     * @return true if the ship types were deleted
     * @throws DAOException
     *             if the query cannot be completed
     */
    boolean deleteAllShipTypes() throws DAOException;

    /**
     * 
     * @return all shipTypes in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<ShipTypeDTO> getAllShipTypes() throws DAOException;

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
     * @return all shipTypes in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<ShipTypeDTO> getAllShipTypes(int startRow, int endRow, ArrayList<Filter> criteria, String sortBy)
        throws DAOException;

    /**
     * 
     * @param startRow
     *            paging support - start row
     * @param endRow
     *            paging support - end row
     * @param sortBy
     *            the complete sort string
     * @return all shipTypes in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<ShipTypeDTO> getAllShipTypes(int startRow, int endRow, String sortBy) throws DAOException;

    /**
     * 
     * @param id
     *            the unique key
     * @return the found shipType or null
     * @throws DAOException
     *             if the datastore fails
     */
    ShipTypeDTO getShipType(Long id) throws DAOException;

    /**
     * 
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalShipTypes() throws DAOException;

    /**
     * @param criteria
     *            the filter criteria
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalShipTypesWithFilter(ArrayList<Filter> criteria) throws DAOException;

    /**
     * 
     * @param shipType
     *            the shipType to be removed
     * @return true if the removal was successful
     * @throws DAOException
     *             if the datastore fails
     */
    boolean removeShipType(ShipTypeDTO shipType) throws DAOException;

    /**
     * 
     * @param shipType
     *            the shipType to update
     * @return the updated shipType
     * @throws DAOException
     *             if the datastore fails
     */
    ShipTypeDTO updateShipType(ShipTypeDTO shipType) throws DAOException;

}
