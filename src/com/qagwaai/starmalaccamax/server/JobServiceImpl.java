package com.qagwaai.starmalaccamax.server;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.qagwaai.starmalaccamax.client.service.JobService;
import com.qagwaai.starmalaccamax.client.service.action.Action;
import com.qagwaai.starmalaccamax.client.service.action.AddJob;
import com.qagwaai.starmalaccamax.client.service.action.AddJobResponse;
import com.qagwaai.starmalaccamax.client.service.action.BulkAddJob;
import com.qagwaai.starmalaccamax.client.service.action.BulkAddJobResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllJobs;
import com.qagwaai.starmalaccamax.client.service.action.GetAllJobsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllJobs;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllJobsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveJob;
import com.qagwaai.starmalaccamax.client.service.action.RemoveJobResponse;
import com.qagwaai.starmalaccamax.client.service.action.Response;
import com.qagwaai.starmalaccamax.client.service.action.UpdateJob;
import com.qagwaai.starmalaccamax.client.service.action.UpdateJobResponse;
import com.qagwaai.starmalaccamax.server.config.Configuration;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.JobDAO;
import com.qagwaai.starmalaccamax.shared.ServiceException;
import com.qagwaai.starmalaccamax.shared.model.JobDTO;

/**
 * 
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class JobServiceImpl extends RemoteServiceServlet implements JobService {
    /**
     * Logger for this service
     */
    private static Logger log = Logger.getLogger(JobServiceImpl.class.getName());

    /**
     * 
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Response> T execute(final Action<T> action) throws ServiceException {
        Object response = null;
        long startTime = Instrumentation.callStart("JobServiceImpl: " + action);
        UserService userService = UserServiceFactory.getUserService();
        try {
            if (!userService.isUserLoggedIn()) {
                log.warning("Not logged in user tried to execute action " + action.getClass().getName());
                throw new ServiceException("User is not logged in");
            }
        } catch (NullPointerException npe) {
            // assume that we are in a test unit
            LocalServiceTestHelper helper =
                new LocalServiceTestHelper(new LocalUserServiceTestConfig(), new LocalDatastoreServiceTestConfig())
                    .setEnvIsAdmin(true).setEnvIsLoggedIn(true);
            helper.setUp();
        }
        if (!userService.isUserLoggedIn()) {
            throw new ServiceException("User is not logged in");
        }
        if (action instanceof GetAllJobs) {
            response = (T) executeGetAllJobs((GetAllJobs) action);
        } else if (action instanceof AddJob) {
            response = (T) executeAddJob((AddJob) action);
        } else if (action instanceof UpdateJob) {
            response = (T) executeUpdateJob((UpdateJob) action);
        } else if (action instanceof RemoveJob) {
            response = (T) executeRemoveJob((RemoveJob) action);
        } else if (action instanceof BulkAddJob) {
            response = (T) executeBulkAddJob((BulkAddJob) action);
        } else if (action instanceof RemoveAllJobs) {
            response = (T) executeRemoveAllJobs((RemoveAllJobs) action);
        }

        if (response != null) {
            Instrumentation.callEnd("JobServiceImpl: " + response, startTime, action.getClass().getSimpleName(),
                response);
            return (T) response;
        }
        log.severe("Action not Implemented: " + action.getClass().getName());
        throw new ServiceException("Action not Implemented");
    }

    /**
     * 
     * @param action
     *            the AddJob action
     * @return a AddJobReponse object including the Job added
     * @throws ServiceException
     *             if the datastore could not complete the Job addition
     */
    private AddJobResponse executeAddJob(final AddJob action) throws ServiceException {
        AddJobResponse response = new AddJobResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        JobDAO jobDAO = factory.getJobDAO();
        JobDTO foundJob = null;
        try {
            foundJob = jobDAO.addJob(action.getJob());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetJob.");
        }
        response.setJob(foundJob);

        return response;
    }

    /**
     * 
     * @param action
     *            the BulkAddJob action
     * @return a BulkAddJobResponse including the Jobs added
     * @throws ServiceException
     *             if the datastore could not add the Jobs
     */
    private BulkAddJobResponse executeBulkAddJob(final BulkAddJob action) throws ServiceException {
        BulkAddJobResponse response = new BulkAddJobResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        JobDAO jobDAO = factory.getJobDAO();
        ArrayList<JobDTO> foundJob = null;
        try {
            foundJob = jobDAO.bulkAddJob(action.getJobs());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetJob.");
        }
        response.setSuccess(true);

        return response;
    }

    /**
     * 
     * @param action
     *            the get all jobs action
     * @return the get all jobs response object with the array list of all jobs
     *         enclosed
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetAllJobsResponse executeGetAllJobs(final GetAllJobs action) throws ServiceException {
        GetAllJobsResponse response = new GetAllJobsResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        JobDAO jobDAO = factory.getJobDAO();

        ArrayList<JobDTO> foundJobs = null;
        try {
            if (action.usePaging()) {
                boolean useFilter = false;
                if ((action.getCriteria() != null) && (action.getCriteria().size() > 0)) {
                    useFilter = true;
                }
                if (!useFilter) {
                    foundJobs = jobDAO.getAllJobs(action.getStartRow(), action.getEndRow(), action.getSortBy());
                    response.setTotalJobs(jobDAO.getTotalJobs());
                } else {
                    foundJobs =
                        jobDAO.getAllJobs(action.getStartRow(), action.getEndRow(), action.getCriteria(),
                            action.getSortBy());
                    if (foundJobs.size() < action.getEndRow()) {
                        // shortcut
                        response.setTotalJobs(foundJobs.size());
                    } else {
                        response.setTotalJobs(jobDAO.getTotalJobsWithFilter(action.getCriteria()));
                    }
                }
            } else {
                foundJobs = jobDAO.getAllJobs();
                response.setTotalJobs(foundJobs.size());
            }
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetAllJobs.");
        }
        response.setJobs(foundJobs);
        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveAllJobs action
     * @return a RemoveAllJobsResponse with the status of the removal
     * @throws ServiceException
     *             if the action fails
     */
    private RemoveAllJobsResponse executeRemoveAllJobs(final RemoveAllJobs action) throws ServiceException {
        RemoveAllJobsResponse response = new RemoveAllJobsResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        JobDAO jobDAO = factory.getJobDAO();
        Boolean result = null;
        try {
            result = jobDAO.deleteAllJobs();
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveAllJobs.");
        }
        response.setResponse(result);

        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveJob action
     * @return a RemoveJobResponse object with the removed Job
     * @throws ServiceException
     *             if the datastore could not complete the Job removal
     */
    private RemoveJobResponse executeRemoveJob(final RemoveJob action) throws ServiceException {
        RemoveJobResponse response = new RemoveJobResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        JobDAO jobDAO = factory.getJobDAO();
        // boolean foundJob = false;
        try {
            jobDAO.removeJob(action.getJob());
        } catch (Throwable e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveJob.");
        }
        response.setJob(action.getJob());

        return response;
    }

    /**
     * 
     * @param action
     *            the UpdateJob action
     * @return a UpdateJobResponse object with the Job updated
     * @throws ServiceException
     *             if the datastore could not upate the Job
     */
    private UpdateJobResponse executeUpdateJob(final UpdateJob action) throws ServiceException {
        UpdateJobResponse response = new UpdateJobResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        JobDAO jobDAO = factory.getJobDAO();
        JobDTO foundJob = null;
        try {
            foundJob = jobDAO.updateJob(action.getJob());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command UpdateJob.");
        }
        response.setJob(foundJob);

        return response;
    }

}
