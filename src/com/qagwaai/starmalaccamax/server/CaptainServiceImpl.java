/**
 * UserServiceImple.java
 * Created by pgirard at 1:37:52 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.server package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.qagwaai.starmalaccamax.client.service.CaptainService;
import com.qagwaai.starmalaccamax.client.service.action.Action;
import com.qagwaai.starmalaccamax.client.service.action.AddCaptain;
import com.qagwaai.starmalaccamax.client.service.action.AddCaptainResponse;
import com.qagwaai.starmalaccamax.client.service.action.CaptainNameCheck;
import com.qagwaai.starmalaccamax.client.service.action.CaptainNameCheckResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllCaptains;
import com.qagwaai.starmalaccamax.client.service.action.GetAllCaptainsResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetCaptain;
import com.qagwaai.starmalaccamax.client.service.action.GetCaptainResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllCaptains;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllCaptainsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveCaptain;
import com.qagwaai.starmalaccamax.client.service.action.RemoveCaptainResponse;
import com.qagwaai.starmalaccamax.client.service.action.Response;
import com.qagwaai.starmalaccamax.client.service.action.UpdateCaptain;
import com.qagwaai.starmalaccamax.client.service.action.UpdateCaptainResponse;
import com.qagwaai.starmalaccamax.server.config.Configuration;
import com.qagwaai.starmalaccamax.server.dao.CaptainDAO;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.shared.ServiceException;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class CaptainServiceImpl extends RemoteServiceServlet implements CaptainService {
    /**
     * Logger for this service
     */
    private static Logger log = Logger.getLogger(CaptainServiceImpl.class.getName());

    /**
     * 
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Response> T execute(final Action<T> action) throws ServiceException {
        Object response = null;
        long startTime = Instrumentation.callStart("CaptainServiceImpl: " + action);
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
        if (action instanceof GetCaptain) {
            response = (T) executeGetCaptain((GetCaptain) action);
        } else if (action instanceof GetAllCaptains) {
            response = (T) executeGetAllCaptains((GetAllCaptains) action);
        } else if (action instanceof AddCaptain) {
            response = (T) executeAddCaptain((AddCaptain) action);
        } else if (action instanceof UpdateCaptain) {
            response = (T) executeUpdateCaptain((UpdateCaptain) action);
        } else if (action instanceof RemoveCaptain) {
            response = (T) executeRemoveCaptain((RemoveCaptain) action);
        } else if (action instanceof RemoveAllCaptains) {
            response = (T) executeRemoveAllCaptains((RemoveAllCaptains) action);
        } else if (action instanceof CaptainNameCheck) {
            response = (T) executeCaptainNameCheck((CaptainNameCheck) action);
        }
        if (response != null) {
            Instrumentation.callEnd("CaptainServiceImpl: " + response, startTime, action.getClass().getSimpleName(),
                response);
            return (T) response;
        }

        log.severe("Action not Implemented: " + action.getClass().getName());
        throw new ServiceException("Action not Implemented");
    }

    private CaptainNameCheckResponse executeCaptainNameCheck(final CaptainNameCheck action) throws ServiceException {
        CaptainNameCheckResponse response = new CaptainNameCheckResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        CaptainDAO captainDAO = factory.getCaptainDAO();

        try {
            response.setExists(captainDAO.captainNameExists(action.getName()));
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command CaptainNameCheck.");
        }
        return response;
    }

    /**
     * 
     * @param action
     *            the AddCaptain action
     * @return a AddCaptainReponse object including the Captain added
     * @throws ServiceException
     *             if the datastore could not complete the Captain addition
     */
    private AddCaptainResponse executeAddCaptain(final AddCaptain action) throws ServiceException {
        AddCaptainResponse response = new AddCaptainResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        CaptainDAO captainDAO = factory.getCaptainDAO();
        CaptainDTO foundCaptain = null;
        try {
            foundCaptain = captainDAO.addCaptain(action.getCaptain());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetCaptain.");
        }
        response.setCaptain(foundCaptain);

        return response;
    }

    /**
     * 
     * @param action
     *            the get all captains action
     * @return the get all captains response object with the array list of all
     *         captains enclosed
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetAllCaptainsResponse executeGetAllCaptains(final GetAllCaptains action) throws ServiceException {
        GetAllCaptainsResponse response = new GetAllCaptainsResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        CaptainDAO captainDAO = factory.getCaptainDAO();

        ArrayList<CaptainDTO> foundCaptains = null;
        try {
            if (action.usePaging()) {
                boolean useFilter = false;
                if ((action.getCriteria() != null) && (action.getCriteria().size() > 0)) {
                    useFilter = true;
                }
                if (!useFilter) {
                    foundCaptains =
                        captainDAO.getAllCaptains(action.getStartRow(), action.getEndRow(), action.getSortBy());
                    response.setTotalCaptains(captainDAO.getTotalCaptains());
                } else {
                    foundCaptains =
                        captainDAO.getAllCaptains(action.getStartRow(), action.getEndRow(), action.getCriteria(),
                            action.getSortBy());
                    if (foundCaptains.size() < action.getEndRow()) {
                        // shortcut
                        response.setTotalCaptains(foundCaptains.size());
                    } else {
                        response.setTotalCaptains(captainDAO.getTotalCaptainsWithFilter(action.getCriteria()));
                    }
                }
            } else {
                foundCaptains = captainDAO.getAllCaptains();
                response.setTotalCaptains(foundCaptains.size());
            }
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetAllCaptains.");
        }
        response.setCaptains(foundCaptains);
        return response;
    }

    /**
     * Get the captain from the id
     * 
     * @param action
     *            the get current captain action
     * @return the filled out response - the captain object
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetCaptainResponse executeGetCaptain(final GetCaptain action) throws ServiceException {

        GetCaptainResponse response = new GetCaptainResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        CaptainDAO captainDAO = factory.getCaptainDAO();
        CaptainDTO foundCaptain = null;
        try {
            foundCaptain = captainDAO.getCaptain(action.getId());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetCaptain.");
        }
        response.setCaptain(foundCaptain);

        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveAllCaptains action
     * @return a RemoveAllCaptainsResponse with the status of the removal
     * @throws ServiceException
     *             if the datastore could not complete the Captains removal
     */
    private RemoveAllCaptainsResponse executeRemoveAllCaptains(final RemoveAllCaptains action) throws ServiceException {
        RemoveAllCaptainsResponse response = new RemoveAllCaptainsResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        CaptainDAO captainDAO = factory.getCaptainDAO();
        Boolean result = null;
        try {
            result = captainDAO.deleteAllCaptains();
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveAllCaptains.");
        }
        response.setResponse(result);

        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveCaptain action
     * @return a RemoveCaptainResponse object with the removed Captain
     * @throws ServiceException
     *             if the datastore could not complete the Captain removal
     */
    private RemoveCaptainResponse executeRemoveCaptain(final RemoveCaptain action) throws ServiceException {
        RemoveCaptainResponse response = new RemoveCaptainResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        CaptainDAO captainDAO = factory.getCaptainDAO();
        // boolean foundCaptain = false;
        try {
            captainDAO.removeCaptain(action.getCaptain());
        } catch (Throwable e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveCaptain.");
        }
        response.setCaptain(action.getCaptain());

        return response;
    }

    /**
     * 
     * @param action
     *            the UpdateCaptain action
     * @return a UpdateCaptainResponse object with the Captain updated
     * @throws ServiceException
     *             if the datastore could not upate the Captain
     */
    private UpdateCaptainResponse executeUpdateCaptain(final UpdateCaptain action) throws ServiceException {
        UpdateCaptainResponse response = new UpdateCaptainResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        CaptainDAO captainDAO = factory.getCaptainDAO();
        CaptainDTO foundCaptain = null;
        try {
            foundCaptain = captainDAO.updateCaptain(action.getCaptain());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command UpdateCaptain.");
        }
        response.setCaptain(foundCaptain);

        return response;
    }

}
