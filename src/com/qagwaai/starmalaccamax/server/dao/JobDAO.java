/**
 * UserDAO.java
 * Created by pgirard at 3:21:33 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.JobDTO;

/**
 * @author pgirard
 * 
 */
public interface JobDAO {

    /**
     * 
     * @param newJob
     *            the new job to add
     * @return the updated new job
     * @throws DAOException
     *             if the datastore fails
     */
    JobDTO addJob(JobDTO newJob) throws DAOException;

    /**
     * 
     * @param newJobs
     *            the new jobs to add
     * @return the updated new job
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<JobDTO> bulkAddJob(ArrayList<JobDTO> newJobs) throws DAOException;

    /**
     * 
     * @return true if the delete succeeds
     * @throws DAOException
     *             if the delete fails
     */
    boolean deleteAllJobs() throws DAOException;

    /**
     * 
     * @return all jobs in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<JobDTO> getAllJobs() throws DAOException;

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
     * @return all jobs in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<JobDTO> getAllJobs(int startRow, int endRow, ArrayList<Filter> criteria, String sortBy) throws DAOException;

    /**
     * 
     * @param startRow
     *            paging support - start row
     * @param endRow
     *            paging support - end row
     * @param sortBy
     *            the complete sort string
     * @return all jobs in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<JobDTO> getAllJobs(int startRow, int endRow, String sortBy) throws DAOException;

    /**
     * 
     * @param id
     *            the unique key
     * @return the found job or null
     * @throws DAOException
     *             if the datastore fails
     */
    JobDTO getJob(Long id) throws DAOException;

    /**
     * 
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalJobs() throws DAOException;

    /**
     * @param criteria
     *            the filter criteria
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalJobsWithFilter(ArrayList<Filter> criteria) throws DAOException;

    /**
     * 
     * @param job
     *            the job to be removed
     * @return true if the removal was successful
     * @throws DAOException
     *             if the datastore fails
     */
    boolean removeJob(JobDTO job) throws DAOException;

    /**
     * 
     * @param job
     *            the job to update
     * @return the updated job
     * @throws DAOException
     *             if the datastore fails
     */
    JobDTO updateJob(JobDTO job) throws DAOException;

}
