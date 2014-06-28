/**
 * UserDAO.java
 * Created by pgirard at 3:21:33 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.JumpGateDTO;

/**
 * @author pgirard
 * 
 */
public interface JumpGateDAO {

    /**
     * 
     * @param newJumpGate
     *            the new jumpGate to add
     * @return the updated new jumpGate
     * @throws DAOException
     *             if the datastore fails
     */
    JumpGateDTO addJumpGate(JumpGateDTO newJumpGate) throws DAOException;

    /**
     * 
     * @param newJumpGates
     *            the new jumpGates to add
     * @return the updated new jumpGate
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<JumpGateDTO> bulkAddJumpGate(ArrayList<JumpGateDTO> newJumpGates) throws DAOException;

    /**
     * 
     * @return true if the delete succeeds
     * @throws DAOException
     *             if the delete fails
     */
    boolean deleteAllJumpGates() throws DAOException;

    /**
     * 
     * @return all jumpGates in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<JumpGateDTO> getAllJumpGates() throws DAOException;

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
     * @return all jumpGates in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<JumpGateDTO> getAllJumpGates(int startRow, int endRow, ArrayList<Filter> criteria, String sortBy)
        throws DAOException;

    /**
     * 
     * @param startRow
     *            paging support - start row
     * @param endRow
     *            paging support - end row
     * @param sortBy
     *            the complete sort string
     * @return all jumpGates in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<JumpGateDTO> getAllJumpGates(int startRow, int endRow, String sortBy) throws DAOException;

    /**
     * 
     * @param id
     *            the unique key
     * @return the found jumpGate or null
     * @throws DAOException
     *             if the datastore fails
     */
    JumpGateDTO getJumpGate(Long id) throws DAOException;

    /**
     * 
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalJumpGates() throws DAOException;

    /**
     * @param criteria
     *            the filter criteria
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalJumpGatesWithFilter(ArrayList<Filter> criteria) throws DAOException;

    /**
     * 
     * @param jumpGate
     *            the jumpGate to be removed
     * @return true if the removal was successful
     * @throws DAOException
     *             if the datastore fails
     */
    boolean removeJumpGate(JumpGateDTO jumpGate) throws DAOException;

    /**
     * 
     * @param jumpGate
     *            the jumpGate to update
     * @return the updated jumpGate
     * @throws DAOException
     *             if the datastore fails
     */
    JumpGateDTO updateJumpGate(JumpGateDTO jumpGate) throws DAOException;
}
