package com.qagwaai.starmalaccamax.server;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.google.gson.Gson;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.qagwaai.starmalaccamax.client.Application;
import com.qagwaai.starmalaccamax.client.service.StarService;
import com.qagwaai.starmalaccamax.client.service.action.AbstractPolyAction;
import com.qagwaai.starmalaccamax.client.service.action.AbstractPolyResponse;
import com.qagwaai.starmalaccamax.client.service.action.Action;
import com.qagwaai.starmalaccamax.client.service.action.AddStar;
import com.qagwaai.starmalaccamax.client.service.action.AddStarResponse;
import com.qagwaai.starmalaccamax.client.service.action.BulkAddStar;
import com.qagwaai.starmalaccamax.client.service.action.BulkAddStarResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllStars;
import com.qagwaai.starmalaccamax.client.service.action.GetAllStarsResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetStarsForSolarSystem;
import com.qagwaai.starmalaccamax.client.service.action.GetStarsForSolarSystemResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetStarsForSolarSystemResponseInJSON;
import com.qagwaai.starmalaccamax.client.service.action.GetStarsForSolarSystemResponseInRPC;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllSuns;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllSunsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveStar;
import com.qagwaai.starmalaccamax.client.service.action.RemoveStarResponse;
import com.qagwaai.starmalaccamax.client.service.action.Response;
import com.qagwaai.starmalaccamax.client.service.action.UpdateStar;
import com.qagwaai.starmalaccamax.client.service.action.UpdateStarResponse;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.StarDAO;
import com.qagwaai.starmalaccamax.shared.MimeType;
import com.qagwaai.starmalaccamax.shared.ServiceException;
import com.qagwaai.starmalaccamax.shared.model.StarDTO;

/**
 * 
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class StarServiceImpl extends RemoteServiceServlet implements StarService {
    /**
     * Logger for this service
     */
    private static Logger log = Logger.getLogger(StarServiceImpl.class.getName());

    /**
     * 
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Response> T execute(final Action<T> action) throws ServiceException {
        Object response = null;
        long startTime = Instrumentation.callStart("StarServiceImpl: " + action);
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
        if (action instanceof GetAllStars) {
            response = (T) executeGetAllStars((GetAllStars) action);
        } else if (action instanceof AddStar) {
            response = (T) executeAddStar((AddStar) action);
        } else if (action instanceof UpdateStar) {
            response = (T) executeUpdateStar((UpdateStar) action);
        } else if (action instanceof RemoveStar) {
            response = (T) executeRemoveStar((RemoveStar) action);
        } else if (action instanceof BulkAddStar) {
            response = (T) executeBulkAddStar((BulkAddStar) action);
        } else if (action instanceof RemoveAllSuns) {
            response = (T) executeRemoveAllSuns((RemoveAllSuns) action);
        } else if (action instanceof GetStarsForSolarSystem) {
            response = (T) executeGetStarsForSolarSystem((GetStarsForSolarSystem) action);
        }
        if (response != null) {
            Instrumentation.callEnd("StarServiceImpl: " + response, startTime, action.getClass().getSimpleName(),
                response);
            
            if (action instanceof AbstractPolyAction) {
            	if (!(((AbstractPolyAction) action).getMimeType().equals(((AbstractPolyResponse) response).getMimeType()))) {
            		throw new ServiceException("Implementation fault: request for mime type [" + ((AbstractPolyAction) action).getMimeType() 
            				+ "] returned mime type [" + ((AbstractPolyResponse) response).getMimeType() + "]");
            	}
            }
            return (T) response;
        }
        log.severe("Action not Implemented: " + action.getClass().getName());
        throw new ServiceException("Action not Implemented");
    }

    /**
     * 
     * @param action
     *            the AddStar action
     * @return an AddStarResponse object with the Star added
     * @throws ServiceException
     *             if the datastore could not add the Star
     */
    private AddStarResponse executeAddStar(final AddStar action) throws ServiceException {
        AddStarResponse response = new AddStarResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        StarDAO starDAO = factory.getStarDAO();
        StarDTO foundStar = null;
        try {
            foundStar = starDAO.addStar(action.getStar());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetStar.");
        }
        response.setStar(foundStar);

        return response;
    }

    /**
     * 
     * @param action
     *            the BulkAddStar action
     * @return a BulkAddStarReponse object with the Stars added
     * @throws ServiceException
     *             if the datastore could not add the Stars
     */
    private BulkAddStarResponse executeBulkAddStar(final BulkAddStar action) throws ServiceException {
        BulkAddStarResponse response = new BulkAddStarResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        StarDAO starDAO = factory.getStarDAO();
        ArrayList<StarDTO> foundStar = null;
        try {
            foundStar = starDAO.bulkAddStar(action.getStars());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetStar.");
        }
        response.setSuccess(true);

        return response;
    }

    /**
     * 
     * @param action
     *            the get all stars action
     * @return the get all stars response object with the array list of all
     *         stars enclosed
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetAllStarsResponse executeGetAllStars(final GetAllStars action) throws ServiceException {
        GetAllStarsResponse response = new GetAllStarsResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        StarDAO starDAO = factory.getStarDAO();

        ArrayList<StarDTO> foundStars = null;
        try {
            if (action.usePaging()) {
                boolean useFilter = false;
                if ((action.getCriteria() != null) && (action.getCriteria().size() > 0)) {
                    useFilter = true;
                }
                if (!useFilter) {
                    foundStars = starDAO.getAllStars(action.getStartRow(), action.getEndRow(), action.getSortBy());
                    response.setTotalStars(starDAO.getTotalStars());
                } else {
                    foundStars =
                        starDAO.getAllStars(action.getStartRow(), action.getEndRow(), action.getCriteria(),
                            action.getSortBy());
                    if (foundStars.size() < action.getEndRow()) {
                        // shortcut
                        response.setTotalStars(foundStars.size());
                    } else {
                        response.setTotalStars(starDAO.getTotalStarsWithFilter(action.getCriteria()));
                    }
                }
            } else {
                foundStars = starDAO.getAllStars();
                response.setTotalStars(foundStars.size());
            }
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetAllStars.");
        }
        response.setStars(foundStars);
        return response;
    }

    /**
     * 
     * @param action
     *            an GetStarsForSolarSystem action
     * @return an GetStarsForSolarSystemResponse with a list of stars
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetStarsForSolarSystemResponse executeGetStarsForSolarSystem(final GetStarsForSolarSystem action)
        throws ServiceException {
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        StarDAO starDAO = factory.getStarDAO();

        ArrayList<StarDTO> foundStars = null;
        try {
            foundStars = starDAO.getStarsForSolarSystem(action.getId());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetStarsForSolarSystem.");
        }
        
        GetStarsForSolarSystemResponse response = null;
        if (action.getMimeType() == null) {
            throw new ServiceException("Invalid mime type: [null]");
        }
        if (action.getMimeType().equals(MimeType.rpc)) {
        	response = new GetStarsForSolarSystemResponseInRPC();
        	((GetStarsForSolarSystemResponseInRPC) response).setStars(foundStars);
        } else if (action.getMimeType().equals(MimeType.js)) {
        	response = new GetStarsForSolarSystemResponseInJSON();
        	Gson gson = new Gson();
        	((GetStarsForSolarSystemResponseInJSON) response).setStars(gson.toJson(foundStars));
        } else { 
        	throw new ServiceException("Invalid mime type: [" + action.getMimeType() + "]");
        }
        return response;
    }

    /**
     * 
     * @param action
     *            an RemoveAllSuns action
     * @return an RemoveAllSunsResponse with the status of the removal
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private RemoveAllSunsResponse executeRemoveAllSuns(final RemoveAllSuns action) throws ServiceException {
        RemoveAllSunsResponse response = new RemoveAllSunsResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        StarDAO starDAO = factory.getStarDAO();
        Boolean result = null;
        try {
            result = starDAO.deleteAllStars();
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveAllSuns.");
        }
        response.setResponse(result);

        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveStar action
     * @return a RemoveStarResponse object with the Star removed
     * @throws ServiceException
     *             if the datastore could not remove the Star
     */
    private RemoveStarResponse executeRemoveStar(final RemoveStar action) throws ServiceException {
        RemoveStarResponse response = new RemoveStarResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        StarDAO starDAO = factory.getStarDAO();
        // boolean foundStar = false;
        try {
            starDAO.removeStar(action.getStar());
        } catch (Throwable e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveStar.");
        }
        response.setStar(action.getStar());

        return response;
    }

    /**
     * 
     * @param action
     *            the UpdateStar action
     * @return an UpdateStarResponse object with the Star updated
     * @throws ServiceException
     *             if the datastore could not update the Star
     */
    private UpdateStarResponse executeUpdateStar(final UpdateStar action) throws ServiceException {
        UpdateStarResponse response = new UpdateStarResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        StarDAO starDAO = factory.getStarDAO();
        StarDTO foundStar = null;
        try {
            foundStar = starDAO.updateStar(action.getStar());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command UpdateStar.");
        }
        response.setStar(foundStar);

        return response;
    }

}
