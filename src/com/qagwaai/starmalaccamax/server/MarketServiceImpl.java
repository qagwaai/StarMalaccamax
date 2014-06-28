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
import com.qagwaai.starmalaccamax.client.service.MarketService;
import com.qagwaai.starmalaccamax.client.service.action.Action;
import com.qagwaai.starmalaccamax.client.service.action.AddMarket;
import com.qagwaai.starmalaccamax.client.service.action.AddMarketResponse;
import com.qagwaai.starmalaccamax.client.service.action.BulkAddMarket;
import com.qagwaai.starmalaccamax.client.service.action.BulkAddMarketResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllMarkets;
import com.qagwaai.starmalaccamax.client.service.action.GetAllMarketsResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetLocalOpportunitiesForCargo;
import com.qagwaai.starmalaccamax.client.service.action.GetLocalOpportunitiesForCargoResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetMarket;
import com.qagwaai.starmalaccamax.client.service.action.GetMarketForPlanet;
import com.qagwaai.starmalaccamax.client.service.action.GetMarketForPlanetResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetMarketResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllMarkets;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllMarketsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveMarket;
import com.qagwaai.starmalaccamax.client.service.action.RemoveMarketResponse;
import com.qagwaai.starmalaccamax.client.service.action.Response;
import com.qagwaai.starmalaccamax.client.service.action.UpdateMarket;
import com.qagwaai.starmalaccamax.client.service.action.UpdateMarketResponse;
import com.qagwaai.starmalaccamax.server.business.Calculations;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.MarketDAO;
import com.qagwaai.starmalaccamax.server.dao.PlanetDAO;
import com.qagwaai.starmalaccamax.shared.ServiceException;
import com.qagwaai.starmalaccamax.shared.model.DistanceDTO;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketCommodityDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketOpportunityForShipDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipCargoDTO;

/**
 * 
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class MarketServiceImpl extends RemoteServiceServlet implements MarketService {
    /**
     * Logger for this service
     */
    private static Logger log = Logger.getLogger(MarketServiceImpl.class.getName());

    /**
     * 
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Response> T execute(final Action<T> action) throws ServiceException {
        Object response = null;
        long startTime = Instrumentation.callStart("MarketServiceImpl: " + action);
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
        if (action instanceof GetAllMarkets) {
            response = (T) executeGetAllMarkets((GetAllMarkets) action);
        } else if (action instanceof AddMarket) {
            response = (T) executeAddMarket((AddMarket) action);
        } else if (action instanceof UpdateMarket) {
            response = (T) executeUpdateMarket((UpdateMarket) action);
        } else if (action instanceof RemoveMarket) {
            response = (T) executeRemoveMarket((RemoveMarket) action);
        } else if (action instanceof GetMarketForPlanet) {
            response = (T) executeGetMarketForPlanet((GetMarketForPlanet) action);
        } else if (action instanceof GetMarket) {
            response = (T) executeGetMarket((GetMarket) action);
        } else if (action instanceof BulkAddMarket) {
            response = (T) executeBulkAddMarket((BulkAddMarket) action);
        } else if (action instanceof RemoveAllMarkets) {
            response = (T) executeRemoveAllMarkets((RemoveAllMarkets) action);
        } else if (action instanceof GetLocalOpportunitiesForCargo) {
            response = (T) executeGetLocalOpportunitiesForCargo((GetLocalOpportunitiesForCargo) action);
        }
        if (response != null) {
            Instrumentation.callEnd("MarketServiceImpl: " + response, startTime, action.getClass().getSimpleName(),
                response);
            return (T) response;
        }

        log.severe("Action not Implemented: " + action.getClass().getName());
        throw new ServiceException("Action not Implemented");
    }

    /**
     * 
     * @param action
     *            the AddMarket action
     * @return an AddMarketResponse object with the Market added
     * @throws ServiceException
     *             if the datastore could not add the Market
     */
    private AddMarketResponse executeAddMarket(final AddMarket action) throws ServiceException {
        AddMarketResponse response = new AddMarketResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        MarketDAO marketDAO = factory.getMarketDAO();
        MarketDTO foundMarket = null;
        try {
            foundMarket = marketDAO.addMarket(action.getMarket());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetMarket.");
        }
        response.setMarket(foundMarket);

        return response;
    }

    /**
     * 
     * @param action
     *            the BulkAddMarket action
     * @return a BulkAddMarketResponse object with the Markets added
     * @throws ServiceException
     *             if the datastore could not add the Markets
     */
    private BulkAddMarketResponse executeBulkAddMarket(final BulkAddMarket action) throws ServiceException {
        BulkAddMarketResponse response = new BulkAddMarketResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        MarketDAO marketDAO = factory.getMarketDAO();
        ArrayList<MarketDTO> foundMarket = null;
        try {
            foundMarket = marketDAO.bulkAddMarket(action.getMarkets());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command BulkAddMarket.");
        }
        response.setSuccess(true);

        return response;
    }

    /**
     * 
     * @param action
     *            the get all markets action
     * @return the get all markets response object with the array list of all
     *         markets enclosed
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetAllMarketsResponse executeGetAllMarkets(final GetAllMarkets action) throws ServiceException {
        GetAllMarketsResponse response = new GetAllMarketsResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        MarketDAO marketDAO = factory.getMarketDAO();

        ArrayList<MarketDTO> foundMarkets = null;
        try {
            if (action.usePaging()) {
                boolean useFilter = false;
                if ((action.getCriteria() != null) && (action.getCriteria().size() > 0)) {
                    useFilter = true;
                }
                if (!useFilter) {
                    foundMarkets =
                        marketDAO.getAllMarkets(action.getStartRow(), action.getEndRow(), action.getSortBy());
                    response.setTotalMarkets(marketDAO.getTotalMarkets());
                } else {
                    foundMarkets =
                        marketDAO.getAllMarkets(action.getStartRow(), action.getEndRow(), action.getCriteria(),
                            action.getSortBy());
                    if (foundMarkets.size() < action.getEndRow()) {
                        // shortcut
                        response.setTotalMarkets(foundMarkets.size());
                    } else {
                        response.setTotalMarkets(marketDAO.getTotalMarketsWithFilter(action.getCriteria()));
                    }
                }
            } else {
                foundMarkets = marketDAO.getAllMarkets();
                response.setTotalMarkets(foundMarkets.size());
            }
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetAllMarkets.");
        }
        response.setMarkets(foundMarkets);
        return response;
    }

    /**
     * 
     * @param action
     *            a GetLocalOpportunitiesForCargo action
     * @return an GetLocalOpportunitiesForCargo with the opportunities for this
     *         cargo
     * @throws ServiceException
     *             if the action fails
     */
    private GetLocalOpportunitiesForCargoResponse executeGetLocalOpportunitiesForCargo(
        final GetLocalOpportunitiesForCargo action) throws ServiceException {
        GetLocalOpportunitiesForCargoResponse response = new GetLocalOpportunitiesForCargoResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        PlanetDAO planetDAO = factory.getPlanetDAO();
        MarketDAO marketDAO = factory.getMarketDAO();

        ArrayList<MarketOpportunityForShipDTO> opportunities = new ArrayList<MarketOpportunityForShipDTO>();
        try {
            LocationDTO shipLocation = action.getShip().getLocation();
            if (shipLocation != null) {
                if (shipLocation.getSolarSystemId() != 0) {
                    ArrayList<PlanetDTO> localPlanets =
                        planetDAO.getNonGasGiantPlanetsForSolarSystem(shipLocation.getSolarSystemId());
                    for (PlanetDTO planet : localPlanets) {
                        System.out.println("Getting market for planet " + planet.getName());
                        MarketDTO market = marketDAO.getMarket(planet.getId());
                        // Market market = marketDAO.getMarketForPlanet(planet);
                        if (market != null) {
                            MarketCommodityDTO marketCommodity =
                                market.getCommodities().get(action.getCargo().getCommodity());
                            if (marketCommodity != null) {
                                MarketOpportunityForShipDTO opportunity = new MarketOpportunityForShipDTO();
                                opportunity.setAmountAvailable(marketCommodity.getPurchaseAmountWanted());
                                opportunity.setSalePrice(marketCommodity.getPurchasePrice());
                                opportunity.setCargo((ShipCargoDTO) action.getCargo());
                                opportunity.setCommodity(action.getCargo().getCommodity());
                                DistanceDTO distance = new DistanceDTO();
                                distance.setDistanceInKM(Calculations.calculateDistanceInKm(shipLocation,
                                    planet.getLocation()));
                                opportunity.setDistance(distance);
                                opportunity.setMarket(market);
                                opportunity.setShip(action.getShip());
                                opportunity.setPlanet(planet);
                                opportunities.add(opportunity);
                            }
                        }
                    }
                }
            }
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetLocalOpportunitiesForCargo.");
        }
        response.setOpportunities(opportunities);
        response.setCargo(action.getCargo());
        response.setShip(action.getShip());
        return response;
    }

    /**
     * 
     * @param action
     *            the GetMarket action
     * @return an GetMarketResponse object with the Market located
     * @throws ServiceException
     *             if the datastore could not failed or there are more than one
     *             market
     */
    private GetMarketResponse executeGetMarket(final GetMarket action) throws ServiceException {
        GetMarketResponse response = new GetMarketResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        MarketDAO marketDAO = factory.getMarketDAO();
        MarketDTO foundMarket = null;
        try {
            foundMarket = marketDAO.getMarket(action.getId());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetMarket.");
        }
        response.setMarket(foundMarket);

        return response;
    }

    /**
     * 
     * @param action
     *            the GetMarketForPlanet action
     * @return an GetMarketForPlanetResponse object with the Market located
     * @throws ServiceException
     *             if the datastore could not failed or there are more than one
     *             market
     */
    private GetMarketForPlanetResponse executeGetMarketForPlanet(final GetMarketForPlanet action)
        throws ServiceException {
        GetMarketForPlanetResponse response = new GetMarketForPlanetResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        MarketDAO marketDAO = factory.getMarketDAO();
        MarketDTO foundMarket = null;
        try {
            foundMarket = marketDAO.getMarketForPlanet(action.getPlanet());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetMarketForPlanet.");
        }
        response.setPlanet(action.getPlanet());
        response.setMarket(foundMarket);

        return response;
    }

    /**
     * 
     * @param action
     *            a RemoveAllMarkets action
     * @return an RemoveAllMarketsResponse with the status of the removal
     * @throws ServiceException
     *             if the removal fails
     */
    private RemoveAllMarketsResponse executeRemoveAllMarkets(final RemoveAllMarkets action) throws ServiceException {
        RemoveAllMarketsResponse response = new RemoveAllMarketsResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        MarketDAO marketDAO = factory.getMarketDAO();
        Boolean result = null;
        try {
            result = marketDAO.deleteAllMarkets();
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveAllMarkets.");
        }
        response.setResponse(result);

        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveMarket action
     * @return a RemoveMarketResponse object with the Market removed
     * @throws ServiceException
     *             if the datastore could not remove the Market
     */
    private RemoveMarketResponse executeRemoveMarket(final RemoveMarket action) throws ServiceException {
        RemoveMarketResponse response = new RemoveMarketResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        MarketDAO marketDAO = factory.getMarketDAO();
        // boolean foundMarket = false;
        try {
            marketDAO.removeMarket(action.getMarket());
        } catch (Throwable e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveMarket.");
        }
        response.setMarket(action.getMarket());

        return response;
    }

    /**
     * 
     * @param action
     *            the UpdateMarket action
     * @return an UpateMarketResponse object with the Market updated
     * @throws ServiceException
     *             if the datastore could not update the Market
     */
    private UpdateMarketResponse executeUpdateMarket(final UpdateMarket action) throws ServiceException {
        UpdateMarketResponse response = new UpdateMarketResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        MarketDAO marketDAO = factory.getMarketDAO();
        MarketDTO foundMarket = null;
        try {
            foundMarket = marketDAO.updateMarket(action.getMarket());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command UpdateMarket.");
        }
        response.setMarket(foundMarket);

        return response;
    }

}
