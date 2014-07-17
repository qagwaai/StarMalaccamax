package com.qagwaai.starmalaccamax.server;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.qagwaai.starmalaccamax.client.service.JumpGateService;
import com.qagwaai.starmalaccamax.client.service.action.Action;
import com.qagwaai.starmalaccamax.client.service.action.AddJumpGate;
import com.qagwaai.starmalaccamax.client.service.action.AddJumpGateResponse;
import com.qagwaai.starmalaccamax.client.service.action.BulkAddJumpGate;
import com.qagwaai.starmalaccamax.client.service.action.BulkAddJumpGateResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllJumpGates;
import com.qagwaai.starmalaccamax.client.service.action.GetAllJumpGatesResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllJumpGates;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllJumpGatesResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveJumpGate;
import com.qagwaai.starmalaccamax.client.service.action.RemoveJumpGateResponse;
import com.qagwaai.starmalaccamax.client.service.action.Response;
import com.qagwaai.starmalaccamax.client.service.action.UpdateJumpGate;
import com.qagwaai.starmalaccamax.client.service.action.UpdateJumpGateResponse;
import com.qagwaai.starmalaccamax.server.config.Configuration;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.JumpGateDAO;
import com.qagwaai.starmalaccamax.shared.ServiceException;
import com.qagwaai.starmalaccamax.shared.model.JumpGateDTO;

/**
 * 
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class JumpGateServiceImpl extends RemoteServiceServlet implements JumpGateService {
    /**
     * Logger for this service
     */
    private static Logger log = Logger.getLogger(JumpGateServiceImpl.class.getName());

    /**
     * 
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Response> T execute(final Action<T> action) throws ServiceException {
        Object response = null;
        long startTime = Instrumentation.callStart("JumpGateServiceImpl: " + action);
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
        if (action instanceof GetAllJumpGates) {
            response = (T) executeGetAllJumpGates((GetAllJumpGates) action);
        } else if (action instanceof AddJumpGate) {
            response = (T) executeAddJumpGate((AddJumpGate) action);
        } else if (action instanceof UpdateJumpGate) {
            response = (T) executeUpdateJumpGate((UpdateJumpGate) action);
        } else if (action instanceof RemoveJumpGate) {
            response = (T) executeRemoveJumpGate((RemoveJumpGate) action);
        } else if (action instanceof BulkAddJumpGate) {
            response = (T) executeBulkAddJumpGate((BulkAddJumpGate) action);
        } else if (action instanceof RemoveAllJumpGates) {
            response = (T) executeRemoveAllJumpGates((RemoveAllJumpGates) action);
        }

        if (response != null) {
            Instrumentation.callEnd("JumpGateServiceImpl: " + response, startTime, action.getClass().getSimpleName(),
                response);
            return (T) response;
        }

        log.severe("Action not Implemented: " + action.getClass().getName());
        throw new ServiceException("Action not Implemented");
    }

    /**
     * 
     * @param action
     *            the AddJumpGate action
     * @return a AddJumpGateReponse object including the JumpGate added
     * @throws ServiceException
     *             if the datastore could not complete the JumpGate addition
     */
    private AddJumpGateResponse executeAddJumpGate(final AddJumpGate action) throws ServiceException {
        AddJumpGateResponse response = new AddJumpGateResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        JumpGateDAO jumpGateDAO = factory.getJumpGateDAO();
        JumpGateDTO foundJumpGate = null;
        try {
            foundJumpGate = jumpGateDAO.addJumpGate(action.getJumpGate());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetJumpGate.");
        }
        response.setJumpGate(foundJumpGate);

        return response;
    }

    /**
     * 
     * @param action
     *            the BulkAddJumpGate action
     * @return a BulkAddJumpGateResponse including the JumpGates added
     * @throws ServiceException
     *             if the datastore could not add the JumpGates
     */
    private BulkAddJumpGateResponse executeBulkAddJumpGate(final BulkAddJumpGate action) throws ServiceException {
        BulkAddJumpGateResponse response = new BulkAddJumpGateResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        JumpGateDAO jumpGateDAO = factory.getJumpGateDAO();
        ArrayList<JumpGateDTO> foundJumpGate = null;
        try {
            foundJumpGate = jumpGateDAO.bulkAddJumpGate(action.getJumpGates());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetJumpGate.");
        }
        response.setSuccess(true);

        return response;
    }

    /**
     * 
     * @param action
     *            the get all jumpGates action
     * @return the get all jumpGates response object with the array list of all
     *         jumpGates enclosed
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetAllJumpGatesResponse executeGetAllJumpGates(final GetAllJumpGates action) throws ServiceException {
        GetAllJumpGatesResponse response = new GetAllJumpGatesResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        JumpGateDAO jumpGateDAO = factory.getJumpGateDAO();

        ArrayList<JumpGateDTO> foundJumpGates = null;
        try {
            if (action.usePaging()) {
                boolean useFilter = false;
                if ((action.getCriteria() != null) && (action.getCriteria().size() > 0)) {
                    useFilter = true;
                }
                if (!useFilter) {
                    foundJumpGates =
                        jumpGateDAO.getAllJumpGates(action.getStartRow(), action.getEndRow(), action.getSortBy());
                    response.setTotalJumpGates(jumpGateDAO.getTotalJumpGates());
                } else {
                    foundJumpGates =
                        jumpGateDAO.getAllJumpGates(action.getStartRow(), action.getEndRow(), action.getCriteria(),
                            action.getSortBy());
                    if (foundJumpGates.size() < action.getEndRow()) {
                        // shortcut
                        response.setTotalJumpGates(foundJumpGates.size());
                    } else {
                        response.setTotalJumpGates(jumpGateDAO.getTotalJumpGatesWithFilter(action.getCriteria()));
                    }
                }
            } else {
                foundJumpGates = jumpGateDAO.getAllJumpGates();
                response.setTotalJumpGates(foundJumpGates.size());
            }
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetAllJumpGates.");
        }
        response.setJumpGates(foundJumpGates);
        return response;
    }

    /**
     * 
     * @param action
     *            a RemoveAllJumpGates action
     * @return a RemoveAllJumpGatesResponse with the status of the removal
     * @throws ServiceException
     *             if the action could not be completed
     */
    private RemoveAllJumpGatesResponse executeRemoveAllJumpGates(final RemoveAllJumpGates action)
        throws ServiceException {
        RemoveAllJumpGatesResponse response = new RemoveAllJumpGatesResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        JumpGateDAO jumpGateDAO = factory.getJumpGateDAO();
        Boolean result = null;
        try {
            result = jumpGateDAO.deleteAllJumpGates();
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveAllJumpGates.");
        }
        response.setResponse(result);

        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveJumpGate action
     * @return a RemoveJumpGateResponse object with the removed JumpGate
     * @throws ServiceException
     *             if the datastore could not complete the JumpGate removal
     */
    private RemoveJumpGateResponse executeRemoveJumpGate(final RemoveJumpGate action) throws ServiceException {
        RemoveJumpGateResponse response = new RemoveJumpGateResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        JumpGateDAO jumpGateDAO = factory.getJumpGateDAO();
        // boolean foundJumpGate = false;
        try {
            jumpGateDAO.removeJumpGate(action.getJumpGate());
        } catch (Throwable e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveJumpGate.");
        }
        response.setJumpGate(action.getJumpGate());

        return response;
    }

    /**
     * 
     * @param action
     *            the UpdateJumpGate action
     * @return a UpdateJumpGateResponse object with the JumpGate updated
     * @throws ServiceException
     *             if the datastore could not upate the JumpGate
     */
    private UpdateJumpGateResponse executeUpdateJumpGate(final UpdateJumpGate action) throws ServiceException {
        UpdateJumpGateResponse response = new UpdateJumpGateResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        JumpGateDAO jumpGateDAO = factory.getJumpGateDAO();
        JumpGateDTO foundJumpGate = null;
        try {
            foundJumpGate = jumpGateDAO.updateJumpGate(action.getJumpGate());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command UpdateJumpGate.");
        }
        response.setJumpGate(foundJumpGate);

        return response;
    }
}
