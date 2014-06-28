/**
 * UserDAO.java
 * Created by pgirard at 3:21:33 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao package
 * for the GameActivityMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao;

import com.qagwaai.starmalaccamax.shared.model.gameevent.GameActivity;

/**
 * @author pgirard
 * 
 */
public interface GameActivityDAO {

    /**
     * 
     * @param <U>
     *            the activity class that extends game activity
     * @param newGameActivity
     *            the activity to add
     * @return the added gameactivity with the unique identifier updated
     * @throws DAOException
     *             if the activity cannot be added
     */
    <U extends GameActivity> GameActivity addGameActivity(U newGameActivity) throws DAOException;

    /**
     * 
     * @param <U>
     *            the activity class that extends game activity
     * @param clazz
     *            the concrete class to search for this activity
     * @param id
     *            the id of the concrete class
     * @return the found activity or null
     * @throws DAOException
     *             if there was a storage error
     */
    <U extends GameActivity> U getGameActivity(@SuppressWarnings("rawtypes") Class clazz, final Long id)
        throws DAOException;

    /**
     * 
     * @param <U>
     *            the activity class that extends game activity
     * @param gameActivity
     *            the activity to remove
     * @return true if successful
     * @throws DAOException
     *             if there was a storage error
     */
    <U extends GameActivity> boolean removeGameActivity(U gameActivity) throws DAOException;

    /**
     * 
     * @param <U>
     *            the activity class that extends game activity
     * @param gameActivity
     *            the activity to update
     * @return the removed activity
     * @throws DAOException
     *             if there was a storage error
     */
    <U extends GameActivity> GameActivity updateGameActivity(U gameActivity) throws DAOException;
}
