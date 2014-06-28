/**
 * UserDAO.java
 * Created by pgirard at 3:21:33 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public interface UserDAO {
    /**
     * 
     * @param newUser
     *            the new user to add
     * @return the updated new user
     * @throws DAOException
     *             if the datastore fails
     */
    UserDTO addUser(UserDTO newUser) throws DAOException;

    /**
     * 
     * @return true if the delete was successful
     * @throws DAOException
     *             if the query could not complete
     */
    boolean deleteAllUsers() throws DAOException;

    /**
     * 
     * @param appworksUserId
     *            returned from the user service
     * @return a valid user object or null;
     * @throws DAOException
     *             if the datastore fails
     */
    UserDTO findUserByAppworksId(String appworksUserId) throws DAOException;

    /**
     * 
     * @param email
     *            the email to search for
     * @return a hydrated user object or null
     * @throws DAOException
     *             if the datastore fails
     */
    UserDTO findUserByEmail(String email) throws DAOException;

    /**
     * 
     * @return all users in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<UserDTO> getAllUsers() throws DAOException;

    /**
     * 
     * @param startRow
     *            paging support - start row
     * @param endRow
     *            paging support - end row
     * @return all users in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<UserDTO> getAllUsers(int startRow, int endRow) throws DAOException;

    /**
     * 
     * @param id
     *            the unique key
     * @return the found user or null
     * @throws DAOException
     *             if the datastore fails
     */
    UserDTO getUser(Long id) throws DAOException;

    /**
     * 
     * @param user
     *            the user to be removed
     * @return true if the removal was successful
     * @throws DAOException
     *             if the datastore fails
     */
    boolean removeUser(UserDTO user) throws DAOException;

    /**
     * 
     * @param user
     *            the user to update
     * @return the updated user
     * @throws DAOException
     *             if the datastore fails
     */
    UserDTO updateUser(UserDTO user) throws DAOException;

}
