package com.qagwaai.starmalaccamax.server;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.qagwaai.starmalaccamax.client.service.PlayerSummaryService;
import com.qagwaai.starmalaccamax.client.service.action.Action;
import com.qagwaai.starmalaccamax.client.service.action.GetPlayerOpportunitiesPage;
import com.qagwaai.starmalaccamax.client.service.action.GetPlayerOpportunitiesPageResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetPlayerSummaryPage;
import com.qagwaai.starmalaccamax.client.service.action.GetPlayerSummaryPageResponse;
import com.qagwaai.starmalaccamax.client.service.action.Response;
import com.qagwaai.starmalaccamax.server.business.MarketOpportunities;
import com.qagwaai.starmalaccamax.server.config.Configuration;
import com.qagwaai.starmalaccamax.server.dao.CaptainDAO;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.GameEventDAO;
import com.qagwaai.starmalaccamax.server.dao.ShipDAO;
import com.qagwaai.starmalaccamax.shared.ServiceException;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

/**
 * @author pgirard
 * 
 */

@SuppressWarnings("serial")
public final class PlayerSummaryServiceImpl extends RemoteServiceServlet implements PlayerSummaryService {
    /**
     * Logger for this service
     */
    private static Logger log = Logger.getLogger(PlayerSummaryServiceImpl.class.getName());

    /**
     * 
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Response> T execute(final Action<T> action) throws ServiceException {
        Object response = null;
        long startTime = Instrumentation.callStart("PlayerSummaryServiceImpl: " + action);
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
        if (action instanceof GetPlayerSummaryPage) {
            response = (T) executeGetPlayerSummaryPage((GetPlayerSummaryPage) action);
        } else if (action instanceof GetPlayerOpportunitiesPage) {
            response = (T) executeGetPlayerOpportunitiesPage((GetPlayerOpportunitiesPage) action);
        }
        if (response != null) {
            Instrumentation.callEnd("PlayerSummaryServiceImpl: " + response, startTime, action.getClass()
                .getSimpleName(), response);
            return (T) response;
        }

        log.severe("PlayerSummaryServiceImpl: Action not Implemented: " + action.getClass().getName());
        throw new ServiceException("Action not Implemented");
    }

    /**
     * 
     * @param action
     *            the get Player Opportunities Page action
     * @return the Player Opportunities Page response object
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetPlayerOpportunitiesPageResponse
        executeGetPlayerOpportunitiesPage(final GetPlayerOpportunitiesPage action) throws ServiceException {
        GetPlayerOpportunitiesPageResponse response = new GetPlayerOpportunitiesPageResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        CaptainDAO captainDAO = factory.getCaptainDAO();
        ShipDAO shipDAO = factory.getShipDAO();

        ArrayList<CaptainDTO> foundCaptains = null;
        try {
            foundCaptains = captainDAO.getCaptainsForUser(action.getUser());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetPlayerSummaryPage.");
        }

        ShipDTO foundShip = null;
        ArrayList<ShipDTO> allShips = new ArrayList<ShipDTO>();
        for (CaptainDTO captain : foundCaptains) {
            try {
                foundShip = shipDAO.getShipForCaptain(captain);
                if (foundShip != null) {
                    allShips.add(foundShip);
                }
            } catch (DAOException e) {
                log.severe(e.toString());
                throw new ServiceException("Could not complete command GetPlayerSummaryPage.");
            }
        }

        response.setMarketOpportunities(MarketOpportunities.getMarketOpportunitiesForShips(allShips));
        return response;
    }

    /**
     * 
     * @param action
     *            the get Player Summary Page action
     * @return the Player Summary Page response object
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetPlayerSummaryPageResponse executeGetPlayerSummaryPage(final GetPlayerSummaryPage action)
        throws ServiceException {
        GetPlayerSummaryPageResponse response = new GetPlayerSummaryPageResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        CaptainDAO captainDAO = factory.getCaptainDAO();
        ShipDAO shipDAO = factory.getShipDAO();
        GameEventDAO gameEventDAO = factory.getGameEventDAO();

        ArrayList<CaptainDTO> foundCaptains = null;
        try {
            foundCaptains = captainDAO.getCaptainsForUser(action.getUser());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetPlayerSummaryPage.");
        }

        ShipDTO foundShip = null;
        ArrayList<ShipDTO> allShips = new ArrayList<ShipDTO>();
        for (CaptainDTO captain : foundCaptains) {
            try {
                foundShip = shipDAO.getShipForCaptain(captain);
                if (foundShip != null) {
                    allShips.add(foundShip);
                }
            } catch (DAOException e) {
                log.severe(e.toString());
                throw new ServiceException("Could not complete command GetPlayerSummaryPage.");
            }
        }

        ArrayList<Filter> userFilter = new ArrayList<Filter>();
        userFilter.add(new SimpleFilterItem("playerId", String.valueOf(action.getUser().getId())));
        ArrayList<GameEventDTO> foundEvents = new ArrayList<GameEventDTO>();
        try {
            foundEvents = gameEventDAO.getAllGameEvents(0, 50, userFilter, "");
            log.info("Found " + foundEvents.size() + " events for " + action.getUser());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetPlayerSummaryPage.");
        }

        response.setCaptains(foundCaptains);
        response.setShips(allShips);
        response.setEvents(foundEvents);
        return response;
    }

}
