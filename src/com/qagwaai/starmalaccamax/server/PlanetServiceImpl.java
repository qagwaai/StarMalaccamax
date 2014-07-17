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
import com.qagwaai.starmalaccamax.client.service.PlanetService;
import com.qagwaai.starmalaccamax.client.service.action.AbstractPolyAction;
import com.qagwaai.starmalaccamax.client.service.action.AbstractPolyResponse;
import com.qagwaai.starmalaccamax.client.service.action.Action;
import com.qagwaai.starmalaccamax.client.service.action.AddPlanet;
import com.qagwaai.starmalaccamax.client.service.action.AddPlanetResponse;
import com.qagwaai.starmalaccamax.client.service.action.BulkAddPlanet;
import com.qagwaai.starmalaccamax.client.service.action.BulkAddPlanetResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllPlanets;
import com.qagwaai.starmalaccamax.client.service.action.GetAllPlanetsResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetPlanet;
import com.qagwaai.starmalaccamax.client.service.action.GetPlanetResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetPlanetsForSolarSystem;
import com.qagwaai.starmalaccamax.client.service.action.GetPlanetsForSolarSystemResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetPlanetsForSolarSystemResponseInJSON;
import com.qagwaai.starmalaccamax.client.service.action.GetPlanetsForSolarSystemResponseInRPC;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllPlanets;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllPlanetsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemovePlanet;
import com.qagwaai.starmalaccamax.client.service.action.RemovePlanetResponse;
import com.qagwaai.starmalaccamax.client.service.action.Response;
import com.qagwaai.starmalaccamax.client.service.action.UpdatePlanet;
import com.qagwaai.starmalaccamax.client.service.action.UpdatePlanetResponse;
import com.qagwaai.starmalaccamax.server.config.Configuration;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.PlanetDAO;
import com.qagwaai.starmalaccamax.shared.MimeType;
import com.qagwaai.starmalaccamax.shared.ServiceException;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class PlanetServiceImpl extends RemoteServiceServlet implements PlanetService {
    /**
     * Logger for this service
     */
    private static Logger log = Logger.getLogger(PlanetServiceImpl.class.getName());

    /**
     * 
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Response> T execute(final Action<T> action) throws ServiceException {
        Object response = null;
        long startTime = Instrumentation.callStart("PlanetServiceImpl: " + action);
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
        if (action instanceof GetAllPlanets) {
            response = (T) executeGetAllPlanets((GetAllPlanets) action);
        } else if (action instanceof AddPlanet) {
            response = (T) executeAddPlanet((AddPlanet) action);
        } else if (action instanceof UpdatePlanet) {
            response = (T) executeUpdatePlanet((UpdatePlanet) action);
        } else if (action instanceof RemovePlanet) {
            response = (T) executeRemovePlanet((RemovePlanet) action);
        } else if (action instanceof BulkAddPlanet) {
            response = (T) executeBulkAddPlanet((BulkAddPlanet) action);
        } else if (action instanceof GetPlanet) {
            response = (T) executeGetPlanet((GetPlanet) action);
        } else if (action instanceof RemoveAllPlanets) {
            response = (T) executeRemoveAllPlanets((RemoveAllPlanets) action);
        } else if (action instanceof GetPlanetsForSolarSystem) {
            response = (T) executeGetPlanetsForSolarSystem((GetPlanetsForSolarSystem) action);
        }
        if (response != null) {
            Instrumentation.callEnd("PlanetServiceImpl: " + response, startTime, action.getClass().getSimpleName(),
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
     *            the AddPlanet action
     * @return an AddPlanetResponse object with the Planet added
     * @throws ServiceException
     *             if the datastore could not add the Planet
     */
    private AddPlanetResponse executeAddPlanet(final AddPlanet action) throws ServiceException {
        AddPlanetResponse response = new AddPlanetResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        PlanetDAO planetDAO = factory.getPlanetDAO();
        PlanetDTO foundPlanet = null;
        try {
            foundPlanet = planetDAO.addPlanet(action.getPlanet());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command AddPlanet.");
        }
        response.setPlanet(foundPlanet);

        return response;
    }

    /**
     * 
     * @param action
     *            the BulkAddPlanet action
     * @return a BulkAddPlanetResponse object with the Planets added
     * @throws ServiceException
     *             if the datastore could not add the Planets
     */
    private BulkAddPlanetResponse executeBulkAddPlanet(final BulkAddPlanet action) throws ServiceException {
        BulkAddPlanetResponse response = new BulkAddPlanetResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        PlanetDAO planetDAO = factory.getPlanetDAO();
        ArrayList<PlanetDTO> foundPlanet = null;
        try {
            foundPlanet = planetDAO.bulkAddPlanet(action.getPlanets());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command BulkAddPlanet.");
        }
        response.setSuccess(true);

        return response;
    }

    /**
     * 
     * @param action
     *            the get all planets action
     * @return the get all planets response object with the array list of all
     *         planets enclosed
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetAllPlanetsResponse executeGetAllPlanets(final GetAllPlanets action) throws ServiceException {
        GetAllPlanetsResponse response = new GetAllPlanetsResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        PlanetDAO planetDAO = factory.getPlanetDAO();

        ArrayList<PlanetDTO> foundPlanets = null;
        try {
            if (action.usePaging()) {
                boolean useFilter = false;
                if ((action.getCriteria() != null) && (action.getCriteria().size() > 0)) {
                    useFilter = true;
                }
                if (!useFilter) {
                    foundPlanets =
                        planetDAO.getAllPlanets(action.getStartRow(), action.getEndRow(), action.getSortBy());
                    response.setTotalPlanets(planetDAO.getTotalPlanets());
                } else {
                    foundPlanets =
                        planetDAO.getAllPlanets(action.getStartRow(), action.getEndRow(), action.getCriteria(),
                            action.getSortBy());
                    if (foundPlanets.size() < action.getEndRow()) {
                        // shortcut
                        response.setTotalPlanets(foundPlanets.size());
                    } else {
                        response.setTotalPlanets(planetDAO.getTotalPlanetsWithFilter(action.getCriteria()));
                    }
                }
            } else {
                foundPlanets = planetDAO.getAllPlanets();
                response.setTotalPlanets(foundPlanets.size());
            }
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetAllPlanets.");
        }
        response.setPlanets(foundPlanets);
        return response;
    }

    /**
     * Get the planet from the id
     * 
     * @param action
     *            the get current planet action
     * @return the filled out response - the planet object
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetPlanetResponse executeGetPlanet(final GetPlanet action) throws ServiceException {

        GetPlanetResponse response = new GetPlanetResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        PlanetDAO planetDAO = factory.getPlanetDAO();
        PlanetDTO foundPlanet = null;
        try {
            foundPlanet = planetDAO.getPlanet(action.getId());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetPlanet.");
        }
        response.setPlanet(foundPlanet);

        return response;
    }

    /**
     * 
     * @param action
     *            the GetPlanetsForSolarSystem action
     * @return an GetPlanetsForSolarSystemResponse with the list of planets
     * @throws ServiceException
     *             if the action could not be completed
     */
    private GetPlanetsForSolarSystemResponse executeGetPlanetsForSolarSystem(final GetPlanetsForSolarSystem action)
        throws ServiceException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        PlanetDAO planetDAO = factory.getPlanetDAO();

        ArrayList<PlanetDTO> foundPlanets = null;
        try {
            foundPlanets = planetDAO.getPlanetsForSolarSystem(action.getId());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetPlanetsForSolarSystem.");
        }
        
        GetPlanetsForSolarSystemResponse response = null;
        if (action.getMimeType() == null) {
            throw new ServiceException("Invalid mime type: [null]");
        }
        if (action.getMimeType().equals(MimeType.rpc)) {
        	response = new GetPlanetsForSolarSystemResponseInRPC();
        	((GetPlanetsForSolarSystemResponseInRPC) response).setPlanets(foundPlanets);
        } else if (action.getMimeType().equals(MimeType.js)) {
        	response = new GetPlanetsForSolarSystemResponseInJSON();
        	Gson gson = new Gson();
        	((GetPlanetsForSolarSystemResponseInJSON) response).setPlanets(gson.toJson(foundPlanets));
        } else { 
        	throw new ServiceException("Invalid mime type: [" + action.getMimeType() + "]");
        }

        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveAllPlanets action
     * @return a RemoveAllPlanetsResponse with the status of the removal
     * @throws ServiceException
     *             if the action could not be completed
     */
    private RemoveAllPlanetsResponse executeRemoveAllPlanets(final RemoveAllPlanets action) throws ServiceException {
        RemoveAllPlanetsResponse response = new RemoveAllPlanetsResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        PlanetDAO planetDAO = factory.getPlanetDAO();
        Boolean result = null;
        try {
            result = planetDAO.deleteAllPlanets();
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveAllPlanets.");
        }
        response.setResponse(result);

        return response;
    }

    /**
     * 
     * @param action
     *            the RemovePlanet action
     * @return a RemovePlanetResponse object with the Planet removed
     * @throws ServiceException
     *             if the datastore could not remove the Planet
     */
    private RemovePlanetResponse executeRemovePlanet(final RemovePlanet action) throws ServiceException {
        RemovePlanetResponse response = new RemovePlanetResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        PlanetDAO planetDAO = factory.getPlanetDAO();
        // boolean foundPlanet = false;
        try {
            planetDAO.removePlanet(action.getPlanet());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemovePlanet.");
        } catch (IllegalArgumentException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemovePlanet.");
        }
        response.setPlanet(action.getPlanet());

        return response;
    }

    /**
     * 
     * @param action
     *            the UpdatePlanet action
     * @return an UpatePlanetResponse object with the Planet updated
     * @throws ServiceException
     *             if the datastore could not update the Planet
     */
    private UpdatePlanetResponse executeUpdatePlanet(final UpdatePlanet action) throws ServiceException {
        UpdatePlanetResponse response = new UpdatePlanetResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        PlanetDAO planetDAO = factory.getPlanetDAO();
        PlanetDTO foundPlanet = null;
        try {
            foundPlanet = planetDAO.updatePlanet(action.getPlanet());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command UpdatePlanet.");
        }
        response.setPlanet(foundPlanet);

        return response;
    }
}
