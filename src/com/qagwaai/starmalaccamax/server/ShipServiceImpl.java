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
import com.qagwaai.starmalaccamax.client.service.ShipService;
import com.qagwaai.starmalaccamax.client.service.action.Action;
import com.qagwaai.starmalaccamax.client.service.action.AddShip;
import com.qagwaai.starmalaccamax.client.service.action.AddShipResponse;
import com.qagwaai.starmalaccamax.client.service.action.AddShipType;
import com.qagwaai.starmalaccamax.client.service.action.AddShipTypeResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllShipTypes;
import com.qagwaai.starmalaccamax.client.service.action.GetAllShipTypesResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllShips;
import com.qagwaai.starmalaccamax.client.service.action.GetAllShipsResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetShip;
import com.qagwaai.starmalaccamax.client.service.action.GetShipForCaptain;
import com.qagwaai.starmalaccamax.client.service.action.GetShipForCaptainResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetShipResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetShipsForUser;
import com.qagwaai.starmalaccamax.client.service.action.GetShipsForUserResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllShipTypes;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllShipTypesResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllShips;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllShipsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveShip;
import com.qagwaai.starmalaccamax.client.service.action.RemoveShipResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveShipType;
import com.qagwaai.starmalaccamax.client.service.action.RemoveShipTypeResponse;
import com.qagwaai.starmalaccamax.client.service.action.Response;
import com.qagwaai.starmalaccamax.client.service.action.UpdateShip;
import com.qagwaai.starmalaccamax.client.service.action.UpdateShipResponse;
import com.qagwaai.starmalaccamax.client.service.action.UpdateShipType;
import com.qagwaai.starmalaccamax.client.service.action.UpdateShipTypeResponse;
import com.qagwaai.starmalaccamax.server.dao.CaptainDAO;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.ShipDAO;
import com.qagwaai.starmalaccamax.server.dao.ShipTypeDAO;
import com.qagwaai.starmalaccamax.shared.ServiceException;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipTypeDTO;

/**
 * 
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class ShipServiceImpl extends RemoteServiceServlet implements ShipService {
    /**
     * Logger for this service
     */
    private static Logger log = Logger.getLogger(ShipServiceImpl.class.getName());

    /**
     * 
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Response> T execute(final Action<T> action) throws ServiceException {
        Object response = null;
        long startTime = Instrumentation.callStart("ShipServiceImpl: " + action);
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
        // Ship
        if (action instanceof GetShip) {
            response = (T) executeGetShip((GetShip) action);
        } else if (action instanceof GetAllShips) {
            response = (T) executeGetAllShips((GetAllShips) action);
        } else if (action instanceof AddShip) {
            response = (T) executeAddShip((AddShip) action);
        } else if (action instanceof UpdateShip) {
            response = (T) executeUpdateShip((UpdateShip) action);
        } else if (action instanceof RemoveShip) {
            response = (T) executeRemoveShip((RemoveShip) action);
        } else if (action instanceof GetShipForCaptain) {
            response = (T) executeGetShipForCaptain((GetShipForCaptain) action);
        } else if (action instanceof GetShipsForUser) {
            response = (T) executeGetShipsForUser((GetShipsForUser) action);
        }

        // ShipType
        if (action instanceof GetAllShipTypes) {
            response = (T) executeGetAllShipTypes((GetAllShipTypes) action);
        } else if (action instanceof AddShipType) {
            response = (T) executeAddShipType((AddShipType) action);
        } else if (action instanceof UpdateShipType) {
            response = (T) executeUpdateShipType((UpdateShipType) action);
        } else if (action instanceof RemoveShipType) {
            response = (T) executeRemoveShipType((RemoveShipType) action);
        } else if (action instanceof RemoveAllShips) {
            response = (T) executeRemoveAllShips((RemoveAllShips) action);
        } else if (action instanceof RemoveAllShipTypes) {
            response = (T) executeRemoveAllShipTypes((RemoveAllShipTypes) action);
        }

        if (response != null) {
            Instrumentation.callEnd("ShipServiceImpl: " + response, startTime, action.getClass().getSimpleName(),
                response);
            return (T) response;
        }

        log.severe("Action not Implemented: " + action.getClass().getName());
        throw new ServiceException("Action not Implemented");
    }

    /**
     * 
     * @param action
     *            the AddShip action
     * @return an AddShipResponse object with the Ship added
     * @throws ServiceException
     *             if the datastore could not add the Ship
     */
    private AddShipResponse executeAddShip(final AddShip action) throws ServiceException {
        AddShipResponse response = new AddShipResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        ShipDAO shipDAO = factory.getShipDAO();
        ShipDTO foundShip = null;
        try {
            foundShip = shipDAO.addShip(action.getShip());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetShip.");
        }
        response.setShip(foundShip);

        return response;
    }

    /**
     * 
     * @param action
     *            the AddShipType action
     * @return an AddShipTypeResponse object with the ShipType added
     * @throws ServiceException
     *             if the datastore could not add the ShipType
     */
    private AddShipTypeResponse executeAddShipType(final AddShipType action) throws ServiceException {
        AddShipTypeResponse response = new AddShipTypeResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        ShipTypeDAO shipTypeDAO = factory.getShipTypeDAO();
        ShipTypeDTO foundShipType = null;
        try {
            foundShipType = shipTypeDAO.addShipType(action.getShipType());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetShipType.");
        }
        response.setShipType(foundShipType);

        return response;
    }

    /**
     * 
     * @param action
     *            the get all ships action
     * @return the get all ships response object with the array list of all
     *         ships enclosed
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetAllShipsResponse executeGetAllShips(final GetAllShips action) throws ServiceException {
        GetAllShipsResponse response = new GetAllShipsResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        ShipDAO shipDAO = factory.getShipDAO();

        ArrayList<ShipDTO> foundShips = null;
        try {
            if (action.usePaging()) {
                boolean useFilter = false;
                if ((action.getCriteria() != null) && (action.getCriteria().size() > 0)) {
                    useFilter = true;
                }
                if (!useFilter) {
                    foundShips = shipDAO.getAllShips(action.getStartRow(), action.getEndRow(), action.getSortBy());
                    response.setTotalShips(shipDAO.getTotalShips());
                } else {
                    foundShips =
                        shipDAO.getAllShips(action.getStartRow(), action.getEndRow(), action.getCriteria(),
                            action.getSortBy());
                    if (foundShips.size() < action.getEndRow()) {
                        // shortcut
                        response.setTotalShips(foundShips.size());
                    } else {
                        response.setTotalShips(shipDAO.getTotalShipsWithFilter(action.getCriteria()));
                    }
                }
            } else {
                foundShips = shipDAO.getAllShips();
                response.setTotalShips(foundShips.size());
            }
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetAllShips.");
        }
        response.setShips(foundShips);
        return response;
    }

    /**
     * 
     * @param action
     *            the get all ships types action
     * @return the get all ships types response object with the array list of
     *         all ships enclosed
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetAllShipTypesResponse executeGetAllShipTypes(final GetAllShipTypes action) throws ServiceException {
        GetAllShipTypesResponse response = new GetAllShipTypesResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        ShipTypeDAO shipTypeDAO = factory.getShipTypeDAO();

        ArrayList<ShipTypeDTO> foundShipTypes = null;
        try {
            if (action.usePaging()) {
                boolean useFilter = false;
                if ((action.getCriteria() != null) && (action.getCriteria().size() > 0)) {
                    useFilter = true;
                }
                if (!useFilter) {
                    foundShipTypes =
                        shipTypeDAO.getAllShipTypes(action.getStartRow(), action.getEndRow(), action.getSortBy());
                    response.setTotalShipTypes(shipTypeDAO.getTotalShipTypes());
                } else {
                    foundShipTypes =
                        shipTypeDAO.getAllShipTypes(action.getStartRow(), action.getEndRow(), action.getCriteria(),
                            action.getSortBy());
                    if (foundShipTypes.size() < action.getEndRow()) {
                        // shortcut
                        response.setTotalShipTypes(foundShipTypes.size());
                    } else {
                        response.setTotalShipTypes(shipTypeDAO.getTotalShipTypesWithFilter(action.getCriteria()));
                    }
                }
            } else {
                foundShipTypes = shipTypeDAO.getAllShipTypes();
                response.setTotalShipTypes(foundShipTypes.size());
            }
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetAllShipTypes.");
        }
        response.setShipTypes(foundShipTypes);
        return response;
    }

    /**
     * Get the ship from the id
     * 
     * @param action
     *            the get current ship action
     * @return the filled out response - the ship object
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetShipResponse executeGetShip(final GetShip action) throws ServiceException {

        GetShipResponse response = new GetShipResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        ShipDAO shipDAO = factory.getShipDAO();
        ShipDTO foundShip = null;
        try {
            foundShip = shipDAO.getShip(action.getId());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetShip.");
        }
        response.setShip(foundShip);

        return response;
    }

    /**
     * 
     * @param action
     *            the GetShipForCaptain action
     * @return an GetShipForCaptainResponse object with the Ship located
     * @throws ServiceException
     *             if the datastore could not failed or there are more than one
     *             ship
     */
    private GetShipForCaptainResponse executeGetShipForCaptain(final GetShipForCaptain action) throws ServiceException {
        GetShipForCaptainResponse response = new GetShipForCaptainResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        ShipDAO shipDAO = factory.getShipDAO();
        ShipDTO foundShip = null;
        try {
            foundShip = shipDAO.getShipForCaptain(action.getCaptain());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetShipForCaptain.");
        }
        response.setCaptain(action.getCaptain());
        response.setShip(foundShip);

        return response;
    }

    /**
     * 
     * @param action
     *            the GetShipsForUser action
     * @return an GetShipsForUserResponse with the list of ships
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetShipsForUserResponse executeGetShipsForUser(final GetShipsForUser action) throws ServiceException {
        GetShipsForUserResponse response = new GetShipsForUserResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        CaptainDAO captainDAO = factory.getCaptainDAO();
        ShipDAO shipDAO = factory.getShipDAO();

        ArrayList<CaptainDTO> foundCaptains = null;
        try {
            foundCaptains = captainDAO.getCaptainsForUser(action.getUser());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetShipsForUser.");
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
                throw new ServiceException("Could not complete command GetShipsForUser.");
            }
        }
        response.setUser(action.getUser());
        response.setShips(allShips);
        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveAllShips action
     * @return an RemoveAllShipsResponse with the status of the removal
     * @throws ServiceException
     *             the the action cannot be completed
     */
    private RemoveAllShipsResponse executeRemoveAllShips(final RemoveAllShips action) throws ServiceException {
        RemoveAllShipsResponse response = new RemoveAllShipsResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        ShipDAO shipDAO = factory.getShipDAO();
        Boolean result = null;
        try {
            result = shipDAO.deleteAllShips();
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveAllShips.");
        }
        response.setResponse(result);

        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveAllShipTypes action
     * @return an RemoveAllShipTypesResponse with the status of the removal
     * @throws ServiceException
     *             if the query cannot be completed
     */
    private RemoveAllShipTypesResponse executeRemoveAllShipTypes(final RemoveAllShipTypes action)
        throws ServiceException {
        RemoveAllShipTypesResponse response = new RemoveAllShipTypesResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        ShipTypeDAO shipTypeDAO = factory.getShipTypeDAO();
        Boolean result = null;
        try {
            result = shipTypeDAO.deleteAllShipTypes();
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveAllShipTypes.");
        }
        response.setResponse(result);

        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveShip action
     * @return a RemoveShipResponse object with the Ship removed
     * @throws ServiceException
     *             if the datastore could not remove the Ship
     */
    private RemoveShipResponse executeRemoveShip(final RemoveShip action) throws ServiceException {
        RemoveShipResponse response = new RemoveShipResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        ShipDAO shipDAO = factory.getShipDAO();
        // boolean foundShip = false;
        try {
            shipDAO.removeShip(action.getShip());
        } catch (Throwable e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveShip.");
        }
        response.setShip(action.getShip());

        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveShipType action
     * @return a RemoveShipTypeResponse object with the ShipType removed
     * @throws ServiceException
     *             if the datastore could not remove the ShipType
     */
    private RemoveShipTypeResponse executeRemoveShipType(final RemoveShipType action) throws ServiceException {
        RemoveShipTypeResponse response = new RemoveShipTypeResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        ShipTypeDAO shipTypeDAO = factory.getShipTypeDAO();
        // boolean foundShipType = false;
        try {
            shipTypeDAO.removeShipType(action.getShipType());
        } catch (Throwable e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveShipType.");
        }
        response.setShipType(action.getShipType());

        return response;
    }

    /**
     * 
     * @param action
     *            the UpdateShip action
     * @return an UpateShipResponse object with the Ship updated
     * @throws ServiceException
     *             if the datastore could not update the Ship
     */
    private UpdateShipResponse executeUpdateShip(final UpdateShip action) throws ServiceException {
        UpdateShipResponse response = new UpdateShipResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        ShipDAO shipDAO = factory.getShipDAO();
        ShipDTO foundShip = null;
        try {
            foundShip = shipDAO.updateShip(action.getShip());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command UpdateShip.");
        }
        response.setShip(foundShip);

        return response;
    }

    /**
     * 
     * @param action
     *            the UpdateShipType action
     * @return an UpateShipTypeResponse object with the ShipType updated
     * @throws ServiceException
     *             if the datastore could not update the ShipType
     */
    private UpdateShipTypeResponse executeUpdateShipType(final UpdateShipType action) throws ServiceException {
        UpdateShipTypeResponse response = new UpdateShipTypeResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        ShipTypeDAO shipTypeDAO = factory.getShipTypeDAO();
        ShipTypeDTO foundShipType = null;
        try {
            foundShipType = shipTypeDAO.updateShipType(action.getShipType());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command UpdateShipType.");
        }
        response.setShipType(foundShipType);

        return response;
    }

}
