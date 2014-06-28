/**
 * CaptainDAO.java
 * Created by pgirard at 3:12:49 PM on Aug 11, 2010
 * in the com.qagwaai.starmalaccamax.server.dao package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public interface CaptainDAO {
    /**
     * 
     * @param newCaptain
     *            the new captain to add
     * @return the updated new captain
     * @throws DAOException
     *             if the datastore fails
     */
    CaptainDTO addCaptain(CaptainDTO newCaptain) throws DAOException;

    /**
     * 
     * @return true if successful
     * @throws DAOException if the datastore fails
     */
    boolean deleteAllCaptains() throws DAOException;

    /**
     * 
     * @return all captains in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<CaptainDTO> getAllCaptains() throws DAOException;

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
     * @return all captains in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<CaptainDTO> getAllCaptains(int startRow, int endRow, ArrayList<Filter> criteria, String sortBy)
        throws DAOException;

    /**
     * 
     * @param startRow
     *            paging support - start row
     * @param endRow
     *            paging support - end row
     * @param sortBy
     *            the complete sort string
     * @return all captains in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<CaptainDTO> getAllCaptains(int startRow, int endRow, String sortBy) throws DAOException;

    /**
     * 
     * @param id
     *            the unique key
     * @return the found captain or null
     * @throws DAOException
     *             if the datastore fails
     */
    CaptainDTO getCaptain(Long id) throws DAOException;

    /**
     * 
     * @param user
     *            a user used in the query
     * @return a list of captains owned by that user
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<CaptainDTO> getCaptainsForUser(UserDTO user) throws DAOException;

    /**
     * 
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalCaptains() throws DAOException;

    /**
     * @param criteria
     *            the filter criteria
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalCaptainsWithFilter(ArrayList<Filter> criteria) throws DAOException;

    /**
     * 
     * @param captain
     *            the captain to be removed
     * @return true if the removal was successful
     * @throws DAOException
     *             if the datastore fails
     */
    boolean removeCaptain(CaptainDTO captain) throws DAOException;

    /**
     * 
     * @param captain
     *            the captain to update
     * @return the updated captain
     * @throws DAOException
     *             if the datastore fails
     */
    CaptainDTO updateCaptain(CaptainDTO captain) throws DAOException;

    boolean captainNameExists(String name) throws DAOException;

}
