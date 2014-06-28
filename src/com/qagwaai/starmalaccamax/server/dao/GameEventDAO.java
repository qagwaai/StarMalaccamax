/**
 * UserDAO.java
 * Created by pgirard at 3:21:33 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;

/**
 * @author pgirard
 * 
 */
public interface GameEventDAO {

    /**
     * 
     * @param newGameEvent
     *            the new gameEvent to add
     * @return the updated new gameEvent
     * @throws DAOException
     *             if the datastore fails
     */
    GameEventDTO addGameEvent(GameEventDTO newGameEvent) throws DAOException;

    /**
     * 
     * @param newGameEvents
     *            the new gameEvents to add
     * @return the updated new gameEvent
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<GameEventDTO> bulkAddGameEvent(ArrayList<GameEventDTO> newGameEvents) throws DAOException;

    /**
     * 
     * @return true if all game events are deleted
     * @throws DAOException
     *             if the delete fails
     */
    boolean deleteAllGameEvents() throws DAOException;

    /**
     * 
     * @return all gameEvents in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<GameEventDTO> getAllGameEvents() throws DAOException;

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
     * @return all gameEvents in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<GameEventDTO> getAllGameEvents(int startRow, int endRow, ArrayList<Filter> criteria, String sortBy)
        throws DAOException;

    /**
     * 
     * @param startRow
     *            paging support - start row
     * @param endRow
     *            paging support - end row
     * @param sortBy
     *            the complete sort string
     * @return all gameEvents in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<GameEventDTO> getAllGameEvents(int startRow, int endRow, String sortBy) throws DAOException;

    /**
     * 
     * @param id
     *            the unique key
     * @return the found gameEvent or null
     * @throws DAOException
     *             if the datastore fails
     */
    GameEventDTO getGameEvent(Long id) throws DAOException;

    /**
     * 
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalGameEvents() throws DAOException;

    /**
     * @param criteria
     *            the filter criteria
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalGameEventsWithFilter(ArrayList<Filter> criteria) throws DAOException;

    /**
     * 
     * @param gameEvent
     *            the gameEvent to be removed
     * @return true if the removal was successful
     * @throws DAOException
     *             if the datastore fails
     */
    boolean removeGameEvent(GameEventDTO gameEvent) throws DAOException;

    /**
     * 
     * @param gameEvent
     *            the gameEvent to update
     * @return the updated gameEvent
     * @throws DAOException
     *             if the datastore fails
     */
    GameEventDTO updateGameEvent(GameEventDTO gameEvent) throws DAOException;

    /**
     * 
     * @return a list of all game events that have expired
     * @throws DAOException
     *             if the query fails
     */
    ArrayList<GameEventDTO> getEndedGameEvents() throws DAOException;
}
