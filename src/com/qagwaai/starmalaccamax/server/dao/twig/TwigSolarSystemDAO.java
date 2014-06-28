/**
 * DataNucleusUserDAO.java
 * Created by pgirard at 3:25:05 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao.datanucleus package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao.twig;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Transaction;
import com.google.code.twig.FindCommand.RootFindCommand;
import com.google.code.twig.ObjectDatastore;
import com.google.code.twig.annotation.AnnotationObjectDatastore;
import com.qagwaai.starmalaccamax.server.business.Calculations;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.MarketDAO;
import com.qagwaai.starmalaccamax.server.dao.SolarSystemDAO;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;
import com.qagwaai.starmalaccamax.shared.model.DistanceDTO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketDistanceDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketOpportunityForShipDTO;
import com.qagwaai.starmalaccamax.shared.model.Planet;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDistanceDTO;
import com.qagwaai.starmalaccamax.shared.model.Ship;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;

/**
 * @author pgirard
 * 
 */
public final class TwigSolarSystemDAO implements SolarSystemDAO {

    /**
	 * 
	 */
    public TwigSolarSystemDAO() {

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ClosestDTO addClosest(final ClosestDTO newClosest) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        Transaction trans = datastore.beginTransaction();
        Key key = datastore.store(newClosest);
        newClosest.setId(key.getId());
        trans.commit();

        return newClosest;
    }

    /**
     * 
     * @param command
     *            the command to add filters to
     * @param criteria
     *            the to add to the command
     * @return the filtered command
     */
    private RootFindCommand<ClosestDTO> addFilterToClosestCommand(final RootFindCommand<ClosestDTO> command,
        final ArrayList<Filter> criteria) {
        for (Filter criterion : criteria) {
            if (criterion instanceof SimpleFilterItem) {
                SimpleFilterItem item = (SimpleFilterItem) criterion;
                // this is using the equal operator - any text searching will be
                // impled below
                if (item.getField().equals("originId") || item.getField().equals("toId")) {
                    command.addFilter(item.getField(), FilterOperator.EQUAL, Long.valueOf(item.getValue()));
                } else if (item.getField().equals("order")) {
                    command.addFilter(item.getField(), FilterOperator.EQUAL, Integer.valueOf(item.getValue())
                        .intValue());
                } else {
                    command.addFilter(item.getField(), FilterOperator.EQUAL, item.getValue());
                }
            }
        }
        return command;
    }

    /**
     * 
     * @param command
     *            the command to add filters to
     * @param criteria
     *            the to add to the command
     * @return the filtered command
     */
    private RootFindCommand<SolarSystemDTO> addFilterToSolarSystemCommand(
        final RootFindCommand<SolarSystemDTO> command, final ArrayList<Filter> criteria) {
        for (Filter criterion : criteria) {
            if (criterion instanceof SimpleFilterItem) {
                SimpleFilterItem item = (SimpleFilterItem) criterion;
                if (!item.getField().equals("name")) {
                    // this is using the equal operator - any text searching
                    // will be impled below
                    if (item.getField().equals("numberOfComponents") || item.getField().equals("maximumOrbits")
                        || item.getField().equals("habitableOrbit") || item.getField().equals("gasGiants")
                        || item.getField().equals("planetoidBelts") || item.getField().equals("hip")) {
                        command.addFilter(item.getField(), FilterOperator.EQUAL, Integer.valueOf(item.getValue()));
                    } else if (item.getField().equals("alpha") || item.getField().equals("delta")
                        || item.getField().equals("parallax") || item.getField().equals("x")
                        || item.getField().equals("y") || item.getField().equals("z")) {
                        command.addFilter(item.getField(), FilterOperator.EQUAL, Double.valueOf(item.getValue()));
                    } else {
                        command.addFilter(item.getField(), FilterOperator.EQUAL, item.getValue());
                    }
                }
            }
        }
        return command;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public SolarSystemDTO addSolarSystem(final SolarSystemDTO newSolarSystem) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        Transaction trans = datastore.beginTransaction();
        Key key = datastore.store(newSolarSystem);
        newSolarSystem.setId(key.getId());
        trans.commit();

        return newSolarSystem;
    }

    /**
     * 
     * @param command
     *            the command to add sort criteria to
     * @param fullSort
     *            the full sort string ("name, -field")
     * @return the augmented command
     */
    private RootFindCommand<ClosestDTO> addSortsToClosestCommand(final RootFindCommand<ClosestDTO> command,
        final String fullSort) {
        if (fullSort == null) {
            return command;
        }
        if (fullSort.equals("")) {
            return command;
        }
        String[] sortFields = fullSort.split(",");

        for (int i = 0; i < sortFields.length; i++) {
            if (sortFields[i].startsWith("-")) {
                command.addSort(sortFields[i].substring(1, sortFields[i].length()), SortDirection.DESCENDING);
            } else {
                command.addSort(sortFields[i], SortDirection.ASCENDING);
            }
        }

        return command;
    }

    /**
     * 
     * @param command
     *            the command to add sort criteria to
     * @param fullSort
     *            the full sort string ("name, -field")
     * @return the augmented command
     */
    private RootFindCommand<SolarSystemDTO> addSortsToSolarSystemCommand(final RootFindCommand<SolarSystemDTO> command,
        final String fullSort) {
        if (fullSort == null) {
            return command;
        }
        if (fullSort.equals("")) {
            return command;
        }
        String[] sortFields = fullSort.split(",");

        for (int i = 0; i < sortFields.length; i++) {
            if (sortFields[i].startsWith("-")) {
                command.addSort(sortFields[i].substring(1, sortFields[i].length()), SortDirection.DESCENDING);
            } else {
                command.addSort(sortFields[i], SortDirection.ASCENDING);
            }
        }

        return command;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<ClosestDTO> bulkAddClosest(final ArrayList<ClosestDTO> newClosests) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        for (ClosestDTO closest : newClosests) {
            datastore.storeOrUpdate((ClosestDTO) closest);
        }

        return newClosests;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<SolarSystemDTO> bulkAddSolarSystem(final ArrayList<SolarSystemDTO> newSolarSystems) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        for (SolarSystemDTO solarSystem : newSolarSystems) {
            datastore.storeOrUpdate((SolarSystemDTO) solarSystem);
        }

        return newSolarSystems;
    }

    /**
     * 
     * @param itClosests
     *            the Closests to convert
     * @return the converted Closests
     */
    private ArrayList<ClosestDTO> convertToClosestInterface(final Iterator<ClosestDTO> itClosests) {
        ArrayList<ClosestDTO> list = new ArrayList<ClosestDTO>();
        while (itClosests.hasNext()) {
            list.add(itClosests.next());
        }
        return list;
    }

    /**
     * 
     * @param itSolarSystems
     *            the SolarSystems to convert
     * @return the converted SolarSystems
     */
    private ArrayList<SolarSystemDTO> convertToSolarSystemInterface(final Iterator<SolarSystemDTO> itSolarSystems) {
        ArrayList<SolarSystemDTO> list = new ArrayList<SolarSystemDTO>();
        while (itSolarSystems.hasNext()) {
            list.add(itSolarSystems.next());
        }
        return list;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAllClosest() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.deleteAll(ClosestDTO.class);

        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAllSolarSystems() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.deleteAll(SolarSystemDTO.class);

        return true;
    }

    /**
     * 
     * @param itSolarSystems
     *            the SolarSystems to filter
     * @param nameFilter
     *            the name filter
     * @return the converted filtered list
     */
    private ArrayList<SolarSystemDTO> filterOnSolarSystemName(final Iterator<SolarSystemDTO> itSolarSystems,
        final SimpleFilterItem nameFilter) {
        ArrayList<SolarSystemDTO> list = new ArrayList<SolarSystemDTO>();

        while (itSolarSystems.hasNext()) {
            SolarSystemDTO ss = itSolarSystems.next();
            if (ss.getName().contains((String) nameFilter.getValue())) {
                list.add(ss);
            }
        }
        return list;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<ClosestDTO> getAllClosests() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        Iterator<ClosestDTO> itClosests = datastore.find().type(ClosestDTO.class).now();

        return convertToClosestInterface(itClosests);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<ClosestDTO> getAllClosests(final int startRow, final int endRow, final ArrayList<Filter> criteria,
        final String sortBy) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<ClosestDTO> command =
            datastore.find().type(ClosestDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addFilterToClosestCommand(command, criteria);
        command = addSortsToClosestCommand(command, sortBy);
        Iterator<ClosestDTO> itClosests = command.now();
        return convertToClosestInterface(itClosests);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<ClosestDTO> getAllClosests(final int startRow, final int endRow, final String sortBy)
        throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<ClosestDTO> command =
            datastore.find().type(ClosestDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addSortsToClosestCommand(command, sortBy);
        Iterator<ClosestDTO> itClosests = command.now();

        return convertToClosestInterface(itClosests);
    }

    /************************************** closest *******************************************/

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<SolarSystemDTO> getAllSolarSystems() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        Iterator<SolarSystemDTO> itSolarSystems = datastore.find().type(SolarSystemDTO.class).now();

        return convertToSolarSystemInterface(itSolarSystems);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<SolarSystemDTO> getAllSolarSystems(final int startRow, final int endRow,
        final ArrayList<Filter> criteria, final String sortBy) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<SolarSystemDTO> command =
            datastore.find().type(SolarSystemDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addFilterToSolarSystemCommand(command, criteria);
        command = addSortsToSolarSystemCommand(command, sortBy);
        Iterator<SolarSystemDTO> itSolarSystems = command.now();
        if (getSolarSystemNameFilter(criteria) != null) {
            return filterOnSolarSystemName(itSolarSystems, getSolarSystemNameFilter(criteria));
        } else {
            return convertToSolarSystemInterface(itSolarSystems);
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<SolarSystemDTO> getAllSolarSystems(final int startRow, final int endRow, final String sortBy)
        throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<SolarSystemDTO> command =
            datastore.find().type(SolarSystemDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addSortsToSolarSystemCommand(command, sortBy);
        Iterator<SolarSystemDTO> itSolarSystems = command.now();

        return convertToSolarSystemInterface(itSolarSystems);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ClosestDTO getClosest(final Long id) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        return datastore.load(ClosestDTO.class, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<MarketOpportunityForShipDTO> getClosestMarketsForPlanet(final PlanetDTO planet) throws DAOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.TWIG);
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        MarketDAO marketDAO = factory.getMarketDAO();

        RootFindCommand<ClosestDTO> command = datastore.find().type(ClosestDTO.class);
        command.addFilter("originId", FilterOperator.EQUAL, planet.getSolarSystemId());
        Iterator<ClosestDTO> itClosests = command.now();
        ArrayList<Long> foundSystems = new ArrayList<Long>();
        foundSystems.add(planet.getSolarSystemId());
        ArrayList<MarketOpportunityForShipDTO> foundMarkets = new ArrayList<MarketOpportunityForShipDTO>();
        while (itClosests.hasNext()) {
            ClosestDTO closest = itClosests.next();

            RootFindCommand<PlanetDTO> findPlanetsCommand = datastore.find().type(PlanetDTO.class);
            findPlanetsCommand.addFilter("solarSystemId", FilterOperator.EQUAL, closest.getSolarSystemId());

            Iterator<PlanetDTO> itPlanets = findPlanetsCommand.now();

            ArrayList<Long> foundPlanets = new ArrayList<Long>();
            while (itPlanets.hasNext()) {
                foundPlanets.add(itPlanets.next().getId());
            }

            ArrayList<MarketDTO> markets = marketDAO.getMarketsForPlanets(foundPlanets);
            /*
             * for (Market market : markets) { foundMarkets.add(new
             * ShipMarketDistanceDTO(market, new
             * DistanceDTO(closest.getNumberOfJumps(), new Double(0)))); }
             */
        }
        return foundMarkets;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public ArrayList<MarketDTO> getClosestMarketsForPlanets(final ArrayList<Long> planetIds) throws DAOException {
        TwigDAOFactory factory = (TwigDAOFactory) DAOFactory.getDAOFactory(DAOFactory.TWIG);
        MarketDAO marketDAO = factory.getMarketDAO();

        return marketDAO.getMarketsForPlanets(planetIds);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<MarketDistanceDTO> getClosestMarketsForShip(final ShipDTO ship) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        ArrayList<MarketDistanceDTO> foundMarkets = new ArrayList<MarketDistanceDTO>();
        RootFindCommand<ClosestDTO> closestCommand = datastore.find().type(ClosestDTO.class);
        closestCommand.addFilter("originId", FilterOperator.EQUAL, ship.getLocation().getSolarSystemId());
        closestCommand.addFilter("numJumps", FilterOperator.EQUAL, 0);
        Iterator<ClosestDTO> itClosests = closestCommand.now();

        while (itClosests.hasNext()) {
            ClosestDTO closest = itClosests.next();
            RootFindCommand<PlanetDTO> findPlanetsCommand = datastore.find().type(PlanetDTO.class);
            findPlanetsCommand.addFilter("solarSystemId", FilterOperator.EQUAL, closest.getToSolarSystemId());
            findPlanetsCommand.addFilter("isGasGiant", FilterOperator.EQUAL, false);
            Iterator<PlanetDTO> itPlanets = findPlanetsCommand.now();
            while (itPlanets.hasNext()) {
                Planet planet = itPlanets.next();
                RootFindCommand<MarketDTO> findMarketCommand = datastore.find().type(MarketDTO.class);
                findMarketCommand.addFilter("planetId", FilterOperator.EQUAL, planet.getId());
                Iterator<MarketDTO> itMarket = findMarketCommand.now();
                while (itMarket.hasNext()) {
                    MarketDTO market = (MarketDTO) itMarket.next();
                    Double distanceInAU = Double.valueOf(0);
                    if (closest.getNumberOfJumps() == 0) {
                        // is in the same solar system - build au calculation
                        distanceInAU = Calculations.calculateDistanceInAU(ship.getLocation(), planet.getLocation());
                    }
                    foundMarkets.add(new MarketDistanceDTO(market, new DistanceDTO(closest.getNumberOfJumps(),
                        distanceInAU)));
                }
            }
        }

        /*
         * ArrayList<ClosestDTO> closestSolarSystems = new
         * ArrayList<ClosestDTO>(); ArrayList<Long> closestSolarSystemIds = new
         * ArrayList<Long>(); while (itClosests.hasNext()) { ClosestDTO closest
         * = itClosests.next(); closestSolarSystems.add(closest);
         * closestSolarSystemIds.add(closest.getToSolarSystemId()); }
         * RootFindCommand<PlanetDTO> findPlanetsCommand =
         * datastore.find().type(PlanetDTO.class);
         * findPlanetsCommand.addFilter("solarSystemId", FilterOperator.IN,
         * closestSolarSystemIds); Iterator<PlanetDTO> itPlanets =
         * findPlanetsCommand.now(); ArrayList<Long> foundPlanetsIds = new
         * ArrayList<Long>(); ArrayList<Planet> foundPlanets = new
         * ArrayList<Planet>(); while (itPlanets.hasNext()) { Planet planet =
         * itPlanets.next(); foundPlanetsIds.add(planet.getId());
         * foundPlanets.add(planet); } ArrayList<Market> markets =
         * getClosestMarketsForPlanets(foundPlanetsIds);
         * 
         * for (ClosestDTO closest : closestSolarSystems) { for (Planet planet :
         * foundPlanets) { if
         * (closest.getSolarSystemId().equals(planet.getSolarSystemId())) { for
         * (Market market : markets) { if
         * (market.getPlanetId().equals(planet.getId())) { Double distanceInAU =
         * Double.valueOf(0); if (closest.getNumberOfJumps() == 0) { // is in
         * the same solar system - build au calculation distanceInAU =
         * DistanceCalculation.calculateDistanceInAU(ship.getLocation(),
         * planet.getLocation()); } foundMarkets.add(new
         * MarketDistanceDTO(market, new DistanceDTO(closest.getNumberOfJumps(),
         * distanceInAU))); } } } } }
         */
        return foundMarkets;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public ArrayList<PlanetDistanceDTO> getClosestPlanetsForPlanet(final PlanetDTO planet) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<ClosestDTO> command = datastore.find().type(ClosestDTO.class);
        command.addFilter("originId", FilterOperator.EQUAL, planet.getSolarSystemId());
        Iterator<ClosestDTO> itClosests = command.now();

        ArrayList<PlanetDistanceDTO> foundPlanets = new ArrayList<PlanetDistanceDTO>();
        while (itClosests.hasNext()) {
            ClosestDTO closest = itClosests.next();

            RootFindCommand<PlanetDTO> findPlanetsCommand = datastore.find().type(PlanetDTO.class);
            findPlanetsCommand.addFilter("solarSystemId", FilterOperator.EQUAL, closest.getToSolarSystemId());

            Iterator<PlanetDTO> itPlanets = findPlanetsCommand.now();

            Double distanceInAU = new Double(0);
            while (itPlanets.hasNext()) {
                PlanetDTO to = (PlanetDTO) itPlanets.next();
                if (closest.getNumberOfJumps() == 0) {
                    // is in the same solar system - build au calculation
                    distanceInAU = Calculations.calculateDistanceInAU(planet.getLocation(), to.getLocation());
                }
                foundPlanets.add(new PlanetDistanceDTO(to, new DistanceDTO(closest.getNumberOfJumps(), distanceInAU)));
            }

        }

        return foundPlanets;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<PlanetDistanceDTO> getClosestPlanetsForShips(final ArrayList<ShipDTO> ships) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        ArrayList<Long> foundSystems = new ArrayList<Long>();
        for (Ship ship : ships) {
            LocationDTO location = ship.getLocation();
            if (location != null) {
                foundSystems.add(location.getSolarSystemId());
            }
        }

        RootFindCommand<ClosestDTO> closestCommand = datastore.find().type(ClosestDTO.class);
        closestCommand.addFilter("originId", FilterOperator.IN, foundSystems);
        Iterator<ClosestDTO> itClosests = closestCommand.now();

        ArrayList<PlanetDistanceDTO> foundPlanets = new ArrayList<PlanetDistanceDTO>();
        while (itClosests.hasNext()) {
            ClosestDTO closest = itClosests.next();
            RootFindCommand<PlanetDTO> findPlanetsCommand = datastore.find().type(PlanetDTO.class);
            findPlanetsCommand.addFilter("solarSystemId", FilterOperator.EQUAL, closest.getSolarSystemId());
            Iterator<PlanetDTO> itPlanets = findPlanetsCommand.now();

            while (itPlanets.hasNext()) {
                foundPlanets.add(new PlanetDistanceDTO(itPlanets.next(), new DistanceDTO(closest.getNumberOfJumps(),
                    new Double(0))));
            }
        }

        return foundPlanets;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<ClosestDTO> getClosestsForPlanet(final PlanetDTO planet) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<ClosestDTO> command = datastore.find().type(ClosestDTO.class);
        command.addFilter("originId", FilterOperator.EQUAL, planet.getSolarSystemId());
        Iterator<ClosestDTO> itClosests = command.now();

        return convertToClosestInterface(itClosests);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public SolarSystemDTO getSolarSystem(final Long id) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        return datastore.load(SolarSystemDTO.class, id);
    }

    /**
     * 
     * @param criteria
     *            the full list of criteria to search
     * @return the filterItem if found or null
     */
    private SimpleFilterItem getSolarSystemNameFilter(final ArrayList<Filter> criteria) {
        for (Filter criterion : criteria) {
            if (criterion instanceof SimpleFilterItem) {
                SimpleFilterItem item = (SimpleFilterItem) criterion;
                if (item.getField().equals("name")) {
                    return item;
                }
            }
        }
        return null;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalClosests() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        return datastore.find().type(ClosestDTO.class).returnCount().now();
        /*
         * ArrayList<Closest> found =
         * convertToInterface(datastore.find().type(ClosestDTO.class).now());
         * 
         * return found.size();
         */
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalClosestsWithFilter(final ArrayList<Filter> criteria) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<ClosestDTO> command = datastore.find().type(ClosestDTO.class);
        command = addFilterToClosestCommand(command, criteria);

        Iterator<ClosestDTO> itClosests = command.now();
        return convertToClosestInterface(itClosests).size() - 1;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalSolarSystems() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        return datastore.find().type(SolarSystemDTO.class).returnCount().now();
        /*
         * ArrayList<SolarSystem> found =
         * convertToInterface(datastore.find().type
         * (SolarSystemDTO.class).now());
         * 
         * return found.size();
         */
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalSolarSystemsWithFilter(final ArrayList<Filter> criteria) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<SolarSystemDTO> command = datastore.find().type(SolarSystemDTO.class);
        command = addFilterToSolarSystemCommand(command, criteria);

        Iterator<SolarSystemDTO> itSolarSystems = command.now();
        if (getSolarSystemNameFilter(criteria) != null) {
            ArrayList<SolarSystemDTO> filteredResult =
                filterOnSolarSystemName(itSolarSystems, getSolarSystemNameFilter(criteria));
            return filteredResult.size() - 1;
        } else {
            return convertToSolarSystemInterface(itSolarSystems).size() - 1;
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean removeClosest(final ClosestDTO closest) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.associate(closest);
        datastore.delete(closest);
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean removeSolarSystem(final SolarSystemDTO solarSystem) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.associate(solarSystem);
        datastore.delete(solarSystem);
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ClosestDTO updateClosest(final ClosestDTO closest) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.storeOrUpdate((ClosestDTO) closest);

        return closest;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public SolarSystemDTO updateSolarSystem(final SolarSystemDTO solarSystem) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.storeOrUpdate((SolarSystemDTO) solarSystem);

        return solarSystem;
    }
}
