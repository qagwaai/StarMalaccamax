/**
 * UserDAO.java
 * Created by pgirard at 3:21:33 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.StarDTO;

/**
 * @author pgirard
 * 
 */
public interface StarDAO {

    /**
     * 
     * @param newStar
     *            the new star to add
     * @return the updated new star
     * @throws DAOException
     *             if the datastore fails
     */
    StarDTO addStar(StarDTO newStar) throws DAOException;

    /**
     * 
     * @param newStars
     *            the new stars to add
     * @return the updated new star
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<StarDTO> bulkAddStar(ArrayList<StarDTO> newStars) throws DAOException;

    /**
     * 
     * @return true if the delete was successful
     * @throws DAOException
     *             if the query could not be completed
     */
    boolean deleteAllStars() throws DAOException;

    /**
     * 
     * @return all stars in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<StarDTO> getAllStars() throws DAOException;

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
     * @return all stars in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<StarDTO> getAllStars(int startRow, int endRow, ArrayList<Filter> criteria, String sortBy)
        throws DAOException;

    /**
     * 
     * @param startRow
     *            paging support - start row
     * @param endRow
     *            paging support - end row
     * @param sortBy
     *            the complete sort string
     * @return all stars in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<StarDTO> getAllStars(int startRow, int endRow, String sortBy) throws DAOException;

    /**
     * 
     * @param id
     *            the unique key
     * @return the found star or null
     * @throws DAOException
     *             if the datastore fails
     */
    StarDTO getStar(Long id) throws DAOException;

    /**
     * 
     * @param id
     *            the solar system id used in the query
     * @return a list of stars in the solar system
     * @throws DAOException
     *             if the query could not be completed
     */
    ArrayList<StarDTO> getStarsForSolarSystem(Long id) throws DAOException;

    /**
     * 
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalStars() throws DAOException;

    /**
     * @param criteria
     *            the filter criteria
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalStarsWithFilter(ArrayList<Filter> criteria) throws DAOException;

    /**
     * 
     * @param star
     *            the star to be removed
     * @return true if the removal was successful
     * @throws DAOException
     *             if the datastore fails
     */
    boolean removeStar(StarDTO star) throws DAOException;

    /**
     * 
     * @param star
     *            the star to update
     * @return the updated star
     * @throws DAOException
     *             if the datastore fails
     */
    StarDTO updateStar(StarDTO star) throws DAOException;
}
