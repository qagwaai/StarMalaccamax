package com.qagwaai.starmalaccamax.server;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.qagwaai.starmalaccamax.client.Application;
import com.qagwaai.starmalaccamax.client.service.GameEventService;
import com.qagwaai.starmalaccamax.client.service.action.Action;
import com.qagwaai.starmalaccamax.client.service.action.AddGameEvent;
import com.qagwaai.starmalaccamax.client.service.action.AddGameEventResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllGameEvents;
import com.qagwaai.starmalaccamax.client.service.action.GetAllGameEventsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllGameEvents;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllGameEventsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveGameEvent;
import com.qagwaai.starmalaccamax.client.service.action.RemoveGameEventResponse;
import com.qagwaai.starmalaccamax.client.service.action.Response;
import com.qagwaai.starmalaccamax.client.service.action.UpdateGameEvent;
import com.qagwaai.starmalaccamax.client.service.action.UpdateGameEventResponse;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.GameEventDAO;
import com.qagwaai.starmalaccamax.shared.ServiceException;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;

/**
 * 
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class GameEventServiceImpl extends RemoteServiceServlet implements GameEventService {
    /**
     * Logger for this service
     */
    private static Logger log = Logger.getLogger(GameEventServiceImpl.class.getName());

    /**
     * 
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Response> T execute(final Action<T> action) throws ServiceException {
        Object response = null;
        long startTime = Instrumentation.callStart("GameEventServiceImpl: " + action);
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
        if (action instanceof GetAllGameEvents) {
            response = (T) executeGetAllGameEvents((GetAllGameEvents) action);
        } else if (action instanceof AddGameEvent) {
            response = (T) executeAddGameEvent((AddGameEvent) action);
        } else if (action instanceof UpdateGameEvent) {
            response = (T) executeUpdateGameEvent((UpdateGameEvent) action);
        } else if (action instanceof RemoveGameEvent) {
            response = (T) executeRemoveGameEvent((RemoveGameEvent) action);
        } else if (action instanceof RemoveAllGameEvents) {
            response = (T) executeRemoveAllGameEvents((RemoveAllGameEvents) action);
        }

        if (response != null) {
            Instrumentation.callEnd("GameEventServiceImpl: " + response, startTime, action.getClass().getSimpleName(),
                response);
            return (T) response;
        }

        log.severe("Action not Implemented: " + action.getClass().getName());
        throw new ServiceException("Action not Implemented");
    }

    /**
     * 
     * @param action
     *            the AddGameEvent action
     * @return an AddGameEventResponse object with the GameEvent added
     * @throws ServiceException
     *             if the datastore could not add the GameEvent
     */
    private AddGameEventResponse executeAddGameEvent(final AddGameEvent action) throws ServiceException {
        AddGameEventResponse response = new AddGameEventResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        GameEventDAO gameEventDAO = factory.getGameEventDAO();
        GameEventDTO foundGameEvent = null;
        try {
            foundGameEvent = gameEventDAO.addGameEvent(action.getGameEvent());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command AddGameEvent.");
        }
        response.setGameEvent(foundGameEvent);

        return response;
    }

    /**
     * 
     * @param action
     *            the get all gameEvents action
     * @return the get all gameEvents response object with the array list of all
     *         gameEvents enclosed
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetAllGameEventsResponse executeGetAllGameEvents(final GetAllGameEvents action) throws ServiceException {
        GetAllGameEventsResponse response = new GetAllGameEventsResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        GameEventDAO gameEventDAO = factory.getGameEventDAO();

        ArrayList<GameEventDTO> foundGameEvents = null;
        try {
            if (action.usePaging()) {
                boolean useFilter = false;
                if ((action.getCriteria() != null) && (action.getCriteria().size() > 0)) {
                    useFilter = true;
                }
                if (!useFilter) {
                    foundGameEvents =
                        gameEventDAO.getAllGameEvents(action.getStartRow(), action.getEndRow(), action.getSortBy());
                    response.setTotalGameEvents(gameEventDAO.getTotalGameEvents());
                } else {
                    foundGameEvents =
                        gameEventDAO.getAllGameEvents(action.getStartRow(), action.getEndRow(), action.getCriteria(),
                            action.getSortBy());
                    if (foundGameEvents.size() < action.getEndRow()) {
                        // shortcut
                        response.setTotalGameEvents(foundGameEvents.size());
                    } else {
                        response.setTotalGameEvents(gameEventDAO.getTotalGameEventsWithFilter(action.getCriteria()));
                    }
                }
            } else {
                foundGameEvents = gameEventDAO.getAllGameEvents();
                response.setTotalGameEvents(foundGameEvents.size());
            }
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetAllGameEvents.");
        }
        response.setGameEvents(foundGameEvents);
        return response;
    }

    /**
     * 
     * @param action
     *            the remove all game events action
     * @return the response from the execution
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private RemoveAllGameEventsResponse executeRemoveAllGameEvents(final RemoveAllGameEvents action)
        throws ServiceException {
        RemoveAllGameEventsResponse response = new RemoveAllGameEventsResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        GameEventDAO gameEventDAO = factory.getGameEventDAO();
        Boolean result = null;
        try {
            result = gameEventDAO.deleteAllGameEvents();
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveAllGameEvents.");
        }
        response.setResponse(result);

        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveGameEvent action
     * @return a RemoveGameEventResponse object with the GameEvent removed
     * @throws ServiceException
     *             if the datastore could not remove the GameEvent
     */
    private RemoveGameEventResponse executeRemoveGameEvent(final RemoveGameEvent action) throws ServiceException {
        RemoveGameEventResponse response = new RemoveGameEventResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        GameEventDAO gameEventDAO = factory.getGameEventDAO();
        // boolean foundGameEvent = false;
        try {
            gameEventDAO.removeGameEvent(action.getGameEvent());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveGameEvent.");
        } catch (IllegalArgumentException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveGameEvent.");
        }
        response.setGameEvent(action.getGameEvent());

        return response;
    }

    /**
     * 
     * @param action
     *            the UpdateGameEvent action
     * @return an UpateGameEventResponse object with the GameEvent updated
     * @throws ServiceException
     *             if the datastore could not update the GameEvent
     */
    private UpdateGameEventResponse executeUpdateGameEvent(final UpdateGameEvent action) throws ServiceException {
        UpdateGameEventResponse response = new UpdateGameEventResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        GameEventDAO gameEventDAO = factory.getGameEventDAO();
        GameEventDTO foundGameEvent = null;
        try {
            foundGameEvent = gameEventDAO.updateGameEvent(action.getGameEvent());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command UpdateGameEvent.");
        }
        response.setGameEvent(foundGameEvent);

        return response;
    }

}
