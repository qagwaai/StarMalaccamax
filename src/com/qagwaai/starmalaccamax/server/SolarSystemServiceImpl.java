/**
 * UserServiceImple.java
 * Created by pgirard at 1:37:52 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.server package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.google.gson.Gson;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.qagwaai.starmalaccamax.client.Application;
import com.qagwaai.starmalaccamax.client.service.SolarSystemService;
import com.qagwaai.starmalaccamax.client.service.action.AbstractPolyAction;
import com.qagwaai.starmalaccamax.client.service.action.AbstractPolyResponse;
import com.qagwaai.starmalaccamax.client.service.action.Action;
import com.qagwaai.starmalaccamax.client.service.action.AddClosest;
import com.qagwaai.starmalaccamax.client.service.action.AddClosestResponse;
import com.qagwaai.starmalaccamax.client.service.action.AddSolarSystem;
import com.qagwaai.starmalaccamax.client.service.action.AddSolarSystemResponse;
import com.qagwaai.starmalaccamax.client.service.action.BulkAddClosest;
import com.qagwaai.starmalaccamax.client.service.action.BulkAddClosestResponse;
import com.qagwaai.starmalaccamax.client.service.action.BulkAddSolarSystem;
import com.qagwaai.starmalaccamax.client.service.action.BulkAddSolarSystemResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllClosests;
import com.qagwaai.starmalaccamax.client.service.action.GetAllClosestsResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllSolarSystems;
import com.qagwaai.starmalaccamax.client.service.action.GetAllSolarSystemsResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetClosestPlanetsForPlanet;
import com.qagwaai.starmalaccamax.client.service.action.GetClosestPlanetsForPlanetResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetClosestsForPlanet;
import com.qagwaai.starmalaccamax.client.service.action.GetClosestsForPlanetResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetLocationForNewCaptain;
import com.qagwaai.starmalaccamax.client.service.action.GetLocationForNewCaptainResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetSolarSystem;
import com.qagwaai.starmalaccamax.client.service.action.GetSolarSystemResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetSolarSystemResponseInJSON;
import com.qagwaai.starmalaccamax.client.service.action.GetSolarSystemResponseInRPC;
import com.qagwaai.starmalaccamax.client.service.action.GetSolarSystemsForCaptains;
import com.qagwaai.starmalaccamax.client.service.action.GetSolarSystemsForCaptainsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllClosest;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllClosestResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllSolarSystems;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllSolarSystemsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveClosest;
import com.qagwaai.starmalaccamax.client.service.action.RemoveClosestResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveSolarSystem;
import com.qagwaai.starmalaccamax.client.service.action.RemoveSolarSystemResponse;
import com.qagwaai.starmalaccamax.client.service.action.Response;
import com.qagwaai.starmalaccamax.client.service.action.UpdateClosest;
import com.qagwaai.starmalaccamax.client.service.action.UpdateClosestResponse;
import com.qagwaai.starmalaccamax.client.service.action.UpdateSolarSystem;
import com.qagwaai.starmalaccamax.client.service.action.UpdateSolarSystemResponse;
import com.qagwaai.starmalaccamax.server.dao.CaptainDAO;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.PlanetDAO;
import com.qagwaai.starmalaccamax.server.dao.ShipDAO;
import com.qagwaai.starmalaccamax.server.dao.SolarSystemDAO;
import com.qagwaai.starmalaccamax.shared.MimeType;
import com.qagwaai.starmalaccamax.shared.ServiceException;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.CaptainSolarSystemDTO;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDistanceDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.SolarSystem;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class SolarSystemServiceImpl extends RemoteServiceServlet implements SolarSystemService {
    /**
     * Logger for this service
     */
    private static Logger log = Logger.getLogger(SolarSystemServiceImpl.class.getName());

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Response> T execute(final Action<T> action) throws ServiceException {
        Object response = null;
        long startTime = Instrumentation.callStart("SolarSystemServiceImpl: " + action);
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
        if (action instanceof GetAllSolarSystems) {
            response = (T) executeGetAllSolarSystems((GetAllSolarSystems) action);
        } else if (action instanceof AddSolarSystem) {
            response = (T) executeAddSolarSystem((AddSolarSystem) action);
        } else if (action instanceof UpdateSolarSystem) {
            response = (T) executeUpdateSolarSystem((UpdateSolarSystem) action);
        } else if (action instanceof RemoveSolarSystem) {
            response = (T) executeRemoveSolarSystem((RemoveSolarSystem) action);
        } else if (action instanceof BulkAddSolarSystem) {
            response = (T) executeBulkAddSolarSystem((BulkAddSolarSystem) action);
        } else if (action instanceof GetSolarSystemsForCaptains) {
            response = (T) executeGetSolarSystemsForCaptains((GetSolarSystemsForCaptains) action);
        } else if (action instanceof GetSolarSystem) {
            response = (T) executeGetSolarSystem((GetSolarSystem) action);
        } else if (action instanceof GetLocationForNewCaptain) {
            response = (T) executeGetLocationForNewCaptain((GetLocationForNewCaptain) action);
        } else
        /************* closest ************/
        if (action instanceof GetAllClosests) {
            response = (T) executeGetAllClosests((GetAllClosests) action);
        } else if (action instanceof AddClosest) {
            response = (T) executeAddClosest((AddClosest) action);
        } else if (action instanceof UpdateClosest) {
            response = (T) executeUpdateClosest((UpdateClosest) action);
        } else if (action instanceof RemoveClosest) {
            response = (T) executeRemoveClosest((RemoveClosest) action);
        } else if (action instanceof BulkAddClosest) {
            response = (T) executeBulkAddClosest((BulkAddClosest) action);
        } else if (action instanceof GetClosestsForPlanet) {
            response = (T) executeGetClosestsForPlanet((GetClosestsForPlanet) action);
        } else if (action instanceof GetClosestPlanetsForPlanet) {
            response = (T) executeGetClosestPlanetsForPlanet((GetClosestPlanetsForPlanet) action);
        } else if (action instanceof RemoveAllClosest) {
            response = (T) executeRemoveAllClosest((RemoveAllClosest) action);
        } else if (action instanceof RemoveAllSolarSystems) {
            response = (T) executeRemoveAllSolarSystems((RemoveAllSolarSystems) action);
        }
        if (response != null) {
            Instrumentation.callEnd("SolarSystemServiceImpl: " + response, startTime,
                action.getClass().getSimpleName(), response);
            
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

    private GetLocationForNewCaptainResponse executeGetLocationForNewCaptain(final GetLocationForNewCaptain action)
        throws ServiceException {
        GetLocationForNewCaptainResponse response = new GetLocationForNewCaptainResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        SolarSystemDAO solarSystemDAO = factory.getSolarSystemDAO();
        PlanetDAO planetDAO = factory.getPlanetDAO();
        Random r = new Random(System.currentTimeMillis());
        try {
            ArrayList<SolarSystemDTO> solarSystems = solarSystemDAO.getAllSolarSystems();
            if (solarSystems.size() == 0) {
                throw new ServiceException("No solar systems loaded...");
            }
            int planetIndex;
            int loop = 0;
            do {
                int systemIndex = r.nextInt(solarSystems.size());
                SolarSystem system = solarSystems.get(systemIndex);
                ArrayList<PlanetDTO> planets = planetDAO.getPlanetsForSolarSystem(system.getId());
                planetIndex = -1;
                if (planets.size() > 0) {
                    planetIndex = r.nextInt(planets.size());
                    response.setSolarSystem((SolarSystemDTO) system);
                    response.setPlanet((PlanetDTO) planets.get(planetIndex));
                }
                loop++;
            } while ((planetIndex >= 0) && (loop < 50));
            if ((planetIndex < 0) && (loop >= 50)) {
                throw new ServiceException("Could not find a location, tried 50 times...");
            }
        } catch (DAOException e) {
            throw new ServiceException("Could not get location: " + e.getMessage());
        }

        return response;
    }

    /****************************************** closest **************************************/
    /**
     * 
     * @param action
     *            the AddClosest action
     * @return an AddClosestResponse object with the Closest removed
     * @throws ServiceException
     *             if the datastore could not add the Closest
     */
    private AddClosestResponse executeAddClosest(final AddClosest action) throws ServiceException {
        AddClosestResponse response = new AddClosestResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        SolarSystemDAO closestDAO = factory.getSolarSystemDAO();
        ClosestDTO foundClosest = null;
        try {
            foundClosest = closestDAO.addClosest(action.getClosest());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetClosest.");
        }
        response.setClosest(foundClosest);

        return response;
    }

    /**
     * 
     * @param action
     *            the AddSolarSystem action
     * @return an AddSolarSystemResponse object with the SolarSystem removed
     * @throws ServiceException
     *             if the datastore could not add the SolarSystem
     */
    private AddSolarSystemResponse executeAddSolarSystem(final AddSolarSystem action) throws ServiceException {
        AddSolarSystemResponse response = new AddSolarSystemResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        SolarSystemDAO solarSystemDAO = factory.getSolarSystemDAO();
        SolarSystemDTO foundSolarSystem = null;
        try {
            foundSolarSystem = solarSystemDAO.addSolarSystem(action.getSolarSystem());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetSolarSystem.");
        }
        response.setSolarSystem(foundSolarSystem);

        return response;
    }

    /**
     * 
     * @param action
     *            the BulkAddClosest action
     * @return a BulkAddClosestResponse with the Closests added
     * @throws ServiceException
     *             if the datastore could not add the Closests
     */
    private BulkAddClosestResponse executeBulkAddClosest(final BulkAddClosest action) throws ServiceException {
        BulkAddClosestResponse response = new BulkAddClosestResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        SolarSystemDAO closestDAO = factory.getSolarSystemDAO();
        try {
            closestDAO.bulkAddClosest(action.getClosests());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetClosest.");
        }
        response.setSuccess(true);

        return response;
    }

    private GetSolarSystemResponse executeGetSolarSystem(final GetSolarSystem action) throws ServiceException {

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        SolarSystemDAO solarSystemDAO = factory.getSolarSystemDAO();
        SolarSystemDTO foundSolarSystem = null;
        try {
            foundSolarSystem = solarSystemDAO.getSolarSystem(action.getId());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetSolarSystem.");
        }
        GetSolarSystemResponse response = null;
        if (action.getMimeType() == null) {
            throw new ServiceException("Invalid mime type: [null]");
        }
        if (action.getMimeType().equals(MimeType.rpc)) {
        	response = new GetSolarSystemResponseInRPC();
        	((GetSolarSystemResponseInRPC) response).setSolarSystem(foundSolarSystem);
        } else if (action.getMimeType().equals(MimeType.js)) {
        	response = new GetSolarSystemResponseInJSON();
        	Gson gson = new Gson();
        	((GetSolarSystemResponseInJSON) response).setSolarSystem(gson.toJson(foundSolarSystem));
        } else { 
        	throw new ServiceException("Invalid mime type: [" + action.getMimeType() + "]");
        }

        return response;
    }

    /**
     * 
     * @param action
     *            the BulkAddSolarSystem action
     * @return a BulkAddSolarSystemResponse with the SolarSystems added
     * @throws ServiceException
     *             if the datastore could not add the SolarSystems
     */
    private BulkAddSolarSystemResponse executeBulkAddSolarSystem(final BulkAddSolarSystem action)
        throws ServiceException {
        BulkAddSolarSystemResponse response = new BulkAddSolarSystemResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        SolarSystemDAO solarSystemDAO = factory.getSolarSystemDAO();
        try {
            solarSystemDAO.bulkAddSolarSystem(action.getSolarSystems());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetSolarSystem.");
        }
        response.setSuccess(true);

        return response;
    }

    /**
     * 
     * @param action
     *            the get all closests action
     * @return the get all closests response object with the array list of all
     *         closests enclosed
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetAllClosestsResponse executeGetAllClosests(final GetAllClosests action) throws ServiceException {
        GetAllClosestsResponse response = new GetAllClosestsResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        SolarSystemDAO closestDAO = factory.getSolarSystemDAO();

        ArrayList<ClosestDTO> foundClosests = null;
        try {
            if (action.usePaging()) {
                boolean useFilter = false;
                if ((action.getCriteria() != null) && (action.getCriteria().size() > 0)) {
                    useFilter = true;
                }
                if (!useFilter) {
                    foundClosests =
                        closestDAO.getAllClosests(action.getStartRow(), action.getEndRow(), action.getSortBy());
                    response.setTotalClosests(closestDAO.getTotalClosests());
                } else {
                    foundClosests =
                        closestDAO.getAllClosests(action.getStartRow(), action.getEndRow(), action.getCriteria(),
                            action.getSortBy());
                    if (foundClosests.size() < action.getEndRow()) {
                        // shortcut
                        response.setTotalClosests(foundClosests.size());
                    } else {
                        response.setTotalClosests(closestDAO.getTotalClosestsWithFilter(action.getCriteria()));
                    }
                }
            } else {
                foundClosests = closestDAO.getAllClosests();
                response.setTotalClosests(foundClosests.size());
            }
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetAllClosests.");
        }
        response.setClosests(foundClosests);
        return response;
    }

    /**
     * 
     * @param action
     *            the get all solarSystems action
     * @return the get all solarSystems response object with the array list of
     *         all solarSystems enclosed
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetAllSolarSystemsResponse executeGetAllSolarSystems(final GetAllSolarSystems action)
        throws ServiceException {
        GetAllSolarSystemsResponse response = new GetAllSolarSystemsResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        SolarSystemDAO solarSystemDAO = factory.getSolarSystemDAO();

        ArrayList<SolarSystemDTO> foundSolarSystems = null;
        try {
            if (action.usePaging()) {
                boolean useFilter = false;
                if ((action.getCriteria() != null) && (action.getCriteria().size() > 0)) {
                    useFilter = true;
                }
                if (!useFilter) {
                    foundSolarSystems =
                        solarSystemDAO.getAllSolarSystems(action.getStartRow(), action.getEndRow(), action.getSortBy());
                    response.setTotalSolarSystems(solarSystemDAO.getTotalSolarSystems());
                } else {
                    foundSolarSystems =
                        solarSystemDAO.getAllSolarSystems(action.getStartRow(), action.getEndRow(),
                            action.getCriteria(), action.getSortBy());
                    if (foundSolarSystems.size() < action.getEndRow()) {
                        // shortcut
                        response.setTotalSolarSystems(foundSolarSystems.size());
                    } else {
                        response.setTotalSolarSystems(solarSystemDAO.getTotalSolarSystemsWithFilter(action
                            .getCriteria()));
                    }
                }
            } else {
                foundSolarSystems = solarSystemDAO.getAllSolarSystems();
                response.setTotalSolarSystems(foundSolarSystems.size());
            }
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetAllSolarSystems.");
        }
        response.setSolarSystems(foundSolarSystems);
        return response;
    }

    /**
     * 
     * @param action
     *            the GetClosestPlanetsForPlanet action
     * @return an GetClosestPlanetsForPlanetResponse object with the Closests
     *         located
     * @throws ServiceException
     *             if the datastore could not failed or there are more than one
     *             market
     */
    private GetClosestPlanetsForPlanetResponse
        executeGetClosestPlanetsForPlanet(final GetClosestPlanetsForPlanet action) throws ServiceException {
        GetClosestPlanetsForPlanetResponse response = new GetClosestPlanetsForPlanetResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        SolarSystemDAO solarSystemDAO = factory.getSolarSystemDAO();
        ArrayList<PlanetDistanceDTO> foundClosests = null;
        try {
            foundClosests = solarSystemDAO.getClosestPlanetsForPlanet(action.getPlanet());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetClosestPlanetsForPlanet.");
        }
        response.setPlanet(action.getPlanet());
        response.setPlanets(foundClosests);

        return response;
    }

    /**
     * 
     * @param action
     *            the GetClosestsForPlanet action
     * @return an GetClosestsForPlanetResponse object with the Closests located
     * @throws ServiceException
     *             if the datastore could not failed or there are more than one
     *             market
     */
    private GetClosestsForPlanetResponse executeGetClosestsForPlanet(final GetClosestsForPlanet action)
        throws ServiceException {
        GetClosestsForPlanetResponse response = new GetClosestsForPlanetResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        SolarSystemDAO solarSystemDAO = factory.getSolarSystemDAO();
        ArrayList<ClosestDTO> foundClosests = null;
        try {
            foundClosests = solarSystemDAO.getClosestsForPlanet(action.getPlanet());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetClosestsForPlanet.");
        }
        response.setPlanet(action.getPlanet());
        response.setClosests(foundClosests);

        return response;
    }

    /**
     * 
     * @param action
     *            the GetSolarSystemsForCaptains action
     * @return an GetSolarSystemsForCaptainsResponse with a list of solar
     *         systems
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetSolarSystemsForCaptainsResponse
        executeGetSolarSystemsForCaptains(final GetSolarSystemsForCaptains action) throws ServiceException {
        GetSolarSystemsForCaptainsResponse response = new GetSolarSystemsForCaptainsResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        CaptainDAO captainDAO = factory.getCaptainDAO();
        ShipDAO shipDAO = factory.getShipDAO();
        SolarSystemDAO solarSystemDAO = factory.getSolarSystemDAO();
        ArrayList<CaptainSolarSystemDTO> results = new ArrayList<CaptainSolarSystemDTO>();

        ArrayList<CaptainDTO> foundCaptains = null;
        try {
            foundCaptains = captainDAO.getCaptainsForUser(action.getUser());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetSolarSystemsForCaptains.");
        }

        ShipDTO foundShip = null;
        for (CaptainDTO captain : foundCaptains) {
            try {
                foundShip = shipDAO.getShipForCaptain(captain);
                if (foundShip != null) {
                    CaptainSolarSystemDTO captainSolarSystem = new CaptainSolarSystemDTO();
                    if (foundShip.getLocation().getSolarSystemId() != 0) {
                        SolarSystemDTO solarSystem =
                            solarSystemDAO.getSolarSystem(foundShip.getLocation().getSolarSystemId());
                        captainSolarSystem.setCaptain(captain);
                        captainSolarSystem.setSolarSystem(solarSystem);
                        results.add(captainSolarSystem);
                    }
                }
            } catch (DAOException e) {
                log.severe(e.toString());
                throw new ServiceException("Could not complete command GetSolarSystemsForCaptains.");
            }
        }

        response.setResults(results);

        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveAllClosest action
     * @return an RemoveAllClosestResponse with the status of the removal
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private RemoveAllClosestResponse executeRemoveAllClosest(final RemoveAllClosest action) throws ServiceException {
        RemoveAllClosestResponse response = new RemoveAllClosestResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        SolarSystemDAO solarSystemDAO = factory.getSolarSystemDAO();
        Boolean result = null;
        try {
            result = solarSystemDAO.deleteAllClosest();
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveAllClosest.");
        }
        response.setResponse(result);

        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveAllSolarSystems action
     * @return an RemoveAllSolarSystemsResponse with the status of the removal
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private RemoveAllSolarSystemsResponse executeRemoveAllSolarSystems(final RemoveAllSolarSystems action)
        throws ServiceException {
        RemoveAllSolarSystemsResponse response = new RemoveAllSolarSystemsResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        SolarSystemDAO solarSystemDAO = factory.getSolarSystemDAO();
        Boolean result = null;
        try {
            result = solarSystemDAO.deleteAllSolarSystems();
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveAllSolarSystems.");
        }
        response.setResponse(result);

        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveClosest action
     * @return a RemoveClosestReponse object with the Closest removed
     * @throws ServiceException
     *             if the datastore could not remove the Closest
     */
    private RemoveClosestResponse executeRemoveClosest(final RemoveClosest action) throws ServiceException {
        RemoveClosestResponse response = new RemoveClosestResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        SolarSystemDAO closestDAO = factory.getSolarSystemDAO();
        // boolean foundClosest = false;
        try {
            closestDAO.removeClosest(action.getClosest());
        } catch (Throwable e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveClosest.");
        }
        response.setClosest(action.getClosest());

        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveSolarSystem action
     * @return a RemoveSolarSystemReponse object with the SolarSystem removed
     * @throws ServiceException
     *             if the datastore could not remove the SolarSystem
     */
    private RemoveSolarSystemResponse executeRemoveSolarSystem(final RemoveSolarSystem action) throws ServiceException {
        RemoveSolarSystemResponse response = new RemoveSolarSystemResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        SolarSystemDAO solarSystemDAO = factory.getSolarSystemDAO();
        // boolean foundSolarSystem = false;
        try {
            solarSystemDAO.removeSolarSystem(action.getSolarSystem());
        } catch (Throwable e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveSolarSystem.");
        }
        response.setSolarSystem(action.getSolarSystem());

        return response;
    }

    /**
     * 
     * @param action
     *            the UpdateClosest action
     * @return an UpdateClosestResponse object with the updated Closest
     * @throws ServiceException
     *             if the datastore could not update the Closest
     */
    private UpdateClosestResponse executeUpdateClosest(final UpdateClosest action) throws ServiceException {
        UpdateClosestResponse response = new UpdateClosestResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        SolarSystemDAO closestDAO = factory.getSolarSystemDAO();
        ClosestDTO foundClosest = null;
        try {
            foundClosest = closestDAO.updateClosest(action.getClosest());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command UpdateClosest.");
        }
        response.setClosest(foundClosest);

        return response;
    }

    /**
     * 
     * @param action
     *            the UpdateSolarSystem action
     * @return an UpdateSolarSystemResponse object with the updated SolarSystem
     * @throws ServiceException
     *             if the datastore could not update the SolarSystem
     */
    private UpdateSolarSystemResponse executeUpdateSolarSystem(final UpdateSolarSystem action) throws ServiceException {
        UpdateSolarSystemResponse response = new UpdateSolarSystemResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        SolarSystemDAO solarSystemDAO = factory.getSolarSystemDAO();
        SolarSystemDTO foundSolarSystem = null;
        try {
            foundSolarSystem = solarSystemDAO.updateSolarSystem(action.getSolarSystem());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command UpdateSolarSystem.");
        }
        response.setSolarSystem(foundSolarSystem);

        return response;
    }
}
