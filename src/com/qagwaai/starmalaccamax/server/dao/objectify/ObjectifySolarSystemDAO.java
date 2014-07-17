package com.qagwaai.starmalaccamax.server.dao.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.qagwaai.starmalaccamax.server.business.Calculations;
import com.qagwaai.starmalaccamax.server.config.Configuration;
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

public class ObjectifySolarSystemDAO implements SolarSystemDAO {
	/**
	 * 
	 * @param command
	 *            the command to add filters to
	 * @param criteria
	 *            the to add to the command
	 * @return the filtered command
	 */
	private Query<SolarSystemDTO> addSolarSystemFilterToCommand(Query<SolarSystemDTO> command,
			final ArrayList<Filter> criteria) {
		if (criteria == null) {
			return command;
		}
		for (Filter criterion : criteria) {
			if (criterion instanceof SimpleFilterItem) {
				SimpleFilterItem item = (SimpleFilterItem) criterion;
				if (item.getField().equals("name")) {
					command = command.filter(item.getField(), item.getValue());
				} else 	if (item.getField().equals("x")) {
					command = command.filter(item.getField(), Double.valueOf(item.getValue()).doubleValue());
				} else 	if (item.getField().equals("y")) {
					command = command.filter(item.getField(), Double.valueOf(item.getValue()).doubleValue());
				} else 	if (item.getField().equals("z")) {
					command = command.filter(item.getField(), Double.valueOf(item.getValue()).doubleValue());
				} else if ( item.getField().equals("id")) {
					command = (Query<SolarSystemDTO>) command.filterKey(Key.create(SolarSystemDTO.class, Long.valueOf(item.getValue()).longValue()));
				}
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
	private Query<SolarSystemDTO> addSolarSystemSortsToCommand(
			Query<SolarSystemDTO> command, final String fullSort) {
		if (fullSort == null) {
			return command;
		}
		if (fullSort.equals("")) {
			return command;
		}
		String[] sortFields = fullSort.split(",");

		for (int i = 0; i < sortFields.length; i++) {
			command = command.order(sortFields[i]);
		}

		return command;
	}
	
    /**
     * 
     * @param itSolarSystem
     *            the SolarSystem to convert
     * @return the converted SolarSystem
     */
    private ArrayList<SolarSystemDTO> convertToSolarSystemInterface(final Iterator<SolarSystemDTO> itSolarSystem) {
        ArrayList<SolarSystemDTO> list = new ArrayList<SolarSystemDTO>();

        while (itSolarSystem.hasNext()) {
            list.add(itSolarSystem.next());
        }
        return list;
    }
    
	/**
	 * 
	 * @param command
	 *            the command to add filters to
	 * @param criteria
	 *            the to add to the command
	 * @return the filtered command
	 */
	private Query<ClosestDTO> addClosestFilterToCommand(Query<ClosestDTO> command,
			final ArrayList<Filter> criteria) {
		if (criteria == null) {
			return command;
		}
		for (Filter criterion : criteria) {
			if (criterion instanceof SimpleFilterItem) {
				SimpleFilterItem item = (SimpleFilterItem) criterion;
				if (item.getField().equals("originId")) {
					command = command.filter(item.getField(), Integer.valueOf(item.getValue()).intValue());
				} else if (item.getField().equals("toId")) {
					command = command.filter(item.getField(), Integer.valueOf(item.getValue()).intValue());
				} else if ( item.getField().equals("id")) {
					command = (Query<ClosestDTO>) command.filterKey(Key.create(ClosestDTO.class, Long.valueOf(item.getValue()).longValue()));
				}
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
	private Query<ClosestDTO> addClosestSortsToCommand(
			Query<ClosestDTO> command, final String fullSort) {
		if (fullSort == null) {
			return command;
		}
		if (fullSort.equals("")) {
			return command;
		}
		String[] sortFields = fullSort.split(",");

		for (int i = 0; i < sortFields.length; i++) {
			command = command.order(sortFields[i]);
		}

		return command;
	}
	
    /**
     * 
     * @param itClosest
     *            the Closest to convert
     * @return the converted Closest
     */
    private ArrayList<ClosestDTO> convertToClosestInterface(final Iterator<ClosestDTO> itClosest) {
        ArrayList<ClosestDTO> list = new ArrayList<ClosestDTO>();

        while (itClosest.hasNext()) {
            list.add(itClosest.next());
        }
        return list;
    }

	@Override
	public ClosestDTO addClosest(ClosestDTO newClosest) throws DAOException {
		Key<ClosestDTO> pId = ofy().save().entity(newClosest).now();
		newClosest.setId(pId.getId());
		return newClosest;
	}

	@Override
	public SolarSystemDTO addSolarSystem(SolarSystemDTO newSolarSystem)
			throws DAOException {
		Key<SolarSystemDTO> pId = ofy().save().entity(newSolarSystem).now();
		newSolarSystem.setId(pId.getId());
		return newSolarSystem;
	}

	@Override
	public ArrayList<ClosestDTO> bulkAddClosest(
			ArrayList<ClosestDTO> newClosests) throws DAOException {
        for (ClosestDTO closest : newClosests) {
            addClosest((ClosestDTO) closest);
        }

        return newClosests;
	}

	@Override
	public ArrayList<SolarSystemDTO> bulkAddSolarSystem(
			ArrayList<SolarSystemDTO> newSolarSystems) throws DAOException {
        for (SolarSystemDTO solarSystem : newSolarSystems) {
            addSolarSystem((SolarSystemDTO) solarSystem);
        }

        return newSolarSystems;
	}

	@Override
	public boolean deleteAllClosest() throws DAOException {
		List<Key<ClosestDTO>> keys = ofy().load().type(ClosestDTO.class).keys().list();
		ofy().delete().keys(keys);
		return true;
	}

	@Override
	public boolean deleteAllSolarSystems() throws DAOException {
		List<Key<SolarSystemDTO>> keys = ofy().load().type(SolarSystemDTO.class).keys().list();
		ofy().delete().keys(keys);
		return true;
	}

	@Override
	public ArrayList<ClosestDTO> getAllClosests() throws DAOException {
		return getAllClosests(0, getTotalClosests(), null, "");
	}

	@Override
	public ArrayList<ClosestDTO> getAllClosests(int startRow, int endRow,
			ArrayList<Filter> criteria, String sortBy) throws DAOException {
		Query<ClosestDTO> command = ofy().load().type(ClosestDTO.class)
				.limit(endRow);
		command = addClosestFilterToCommand(command, criteria);
		command = addClosestSortsToCommand(command, sortBy);
		command.chunkAll();
		Iterator<ClosestDTO> itClosests = command.iterator();
		return convertToClosestInterface(itClosests);
	}

	@Override
	public ArrayList<ClosestDTO> getAllClosests(int startRow, int endRow,
			String sortBy) throws DAOException {
		Query<ClosestDTO> command = ofy().load().type(ClosestDTO.class)
				.limit(endRow);
		command = addClosestSortsToCommand(command, sortBy);
		Iterator<ClosestDTO> itClosest = command.iterator();
		return convertToClosestInterface(itClosest);
	}

	@Override
	public ArrayList<SolarSystemDTO> getAllSolarSystems() throws DAOException {
		return getAllSolarSystems(0, getTotalSolarSystems(), null, "");
	}

	@Override
	public ArrayList<SolarSystemDTO> getAllSolarSystems(int startRow,
			int endRow, ArrayList<Filter> criteria, String sortBy)
			throws DAOException {
		Query<SolarSystemDTO> command = ofy().load().type(SolarSystemDTO.class)
				.limit(endRow);
		command = addSolarSystemFilterToCommand(command, criteria);
		command = addSolarSystemSortsToCommand(command, sortBy);
		command.chunkAll();
		Iterator<SolarSystemDTO> itSolarSystems = command.iterator();
		return convertToSolarSystemInterface(itSolarSystems);
	}

	@Override
	public ArrayList<SolarSystemDTO> getAllSolarSystems(int startRow,
			int endRow, String sortBy) throws DAOException {
		Query<SolarSystemDTO> command = ofy().load().type(SolarSystemDTO.class)
				.limit(endRow);
		command = addSolarSystemSortsToCommand(command, sortBy);
		Iterator<SolarSystemDTO> itSolarSystem = command.iterator();
		return convertToSolarSystemInterface(itSolarSystem);
	}

	@Override
	public ClosestDTO getClosest(Long id) throws DAOException {
		return ofy().load().type(ClosestDTO.class).id(id).now();
	}

	@Override
	public ArrayList<MarketOpportunityForShipDTO> getClosestMarketsForPlanet(
			PlanetDTO planet) throws DAOException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        MarketDAO marketDAO = factory.getMarketDAO();

        Query<ClosestDTO> command = ofy().load().type(ClosestDTO.class);
        command.filter("originId", planet.getSolarSystemId());
        Iterator<ClosestDTO> itClosests = command.iterator();
        ArrayList<Long> foundSystems = new ArrayList<Long>();
        foundSystems.add(planet.getSolarSystemId());
        ArrayList<MarketOpportunityForShipDTO> foundMarkets = new ArrayList<MarketOpportunityForShipDTO>();
        while (itClosests.hasNext()) {
            ClosestDTO closest = itClosests.next();

            Query<PlanetDTO> findPlanetsCommand = ofy().load().type(PlanetDTO.class);
            findPlanetsCommand.filter("solarSystemId", closest.getSolarSystemId());
            Iterator<PlanetDTO> itPlanets = findPlanetsCommand.iterator();

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

	@Override
	public ArrayList<MarketDTO> getClosestMarketsForPlanets(
			ArrayList<Long> planetIds) throws DAOException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        MarketDAO marketDAO = factory.getMarketDAO();

        return marketDAO.getMarketsForPlanets(planetIds);
	}

	@Override
	public ArrayList<PlanetDistanceDTO> getClosestPlanetsForPlanet(
			PlanetDTO planet) throws DAOException {
		//DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		Query<ClosestDTO> command = ofy().load().type(ClosestDTO.class);
        command.filter("originId", planet.getSolarSystemId());
        Iterator<ClosestDTO> itClosests = command.iterator();

        ArrayList<PlanetDistanceDTO> foundPlanets = new ArrayList<PlanetDistanceDTO>();
        while (itClosests.hasNext()) {
            ClosestDTO closest = itClosests.next();

            Query<PlanetDTO> findPlanetsCommand = ofy().load().type(PlanetDTO.class);
            findPlanetsCommand.filter("solarSystemId", closest.getToSolarSystemId());
            Iterator<PlanetDTO> itPlanets = findPlanetsCommand.iterator();

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


	@Override
	public ArrayList<ClosestDTO> getClosestsForPlanet(PlanetDTO planet)
			throws DAOException {
		//DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		Query<ClosestDTO> command = ofy().load().type(ClosestDTO.class);
        command.filter("originId", planet.getSolarSystemId());
        Iterator<ClosestDTO> itClosests = command.iterator();

        return convertToClosestInterface(itClosests);

	}

	@Override
	public SolarSystemDTO getSolarSystem(Long id) throws DAOException {
		return ofy().load().type(SolarSystemDTO.class).id(id).now();
	}

	@Override
	public int getTotalClosests() throws DAOException {
		return ofy().load().type(ClosestDTO.class).count();
	}

	@Override
	public int getTotalClosestsWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		Query<ClosestDTO> command = ofy().load().type(ClosestDTO.class);
		command = addClosestFilterToCommand(command, criteria);
		return command.count();
	}

	@Override
	public int getTotalSolarSystems() throws DAOException {
		return ofy().load().type(SolarSystemDTO.class).count();
		
	}

	@Override
	public int getTotalSolarSystemsWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		Query<SolarSystemDTO> command = ofy().load().type(SolarSystemDTO.class);
		command = addSolarSystemFilterToCommand(command, criteria);
		return command.count();
	}

	@Override
	public boolean removeClosest(ClosestDTO closest) throws DAOException {
		ofy().delete().type(ClosestDTO.class).id(closest.getId());
		return true;
	}

	@Override
	public boolean removeSolarSystem(SolarSystemDTO solarSystem)
			throws DAOException {
		ofy().delete().type(SolarSystemDTO.class).id(solarSystem.getId());
		return true;
	}

	@Override
	public ClosestDTO updateClosest(ClosestDTO closest) throws DAOException {
		Key<ClosestDTO> jumpGateId = ofy().save().entity(closest).now();
		closest.setId(jumpGateId.getId());
		return closest;
	}

	@Override
	public SolarSystemDTO updateSolarSystem(SolarSystemDTO solarSystem)
			throws DAOException {
		Key<SolarSystemDTO> jumpGateId = ofy().save().entity(solarSystem).now();
		solarSystem.setId(jumpGateId.getId());
		return solarSystem;
	}


	@Override
	public ArrayList<MarketDistanceDTO> getClosestMarketsForShip(ShipDTO ship)
			throws DAOException {

        ArrayList<MarketDistanceDTO> foundMarkets = new ArrayList<MarketDistanceDTO>();
        Query<ClosestDTO> closestCommand = ofy().load().type(ClosestDTO.class);
        closestCommand.filter("originId", ship.getLocation().getSolarSystemId());
        closestCommand.filter("numJumps", 0);
        Iterator<ClosestDTO> itClosests = closestCommand.iterator();

        while (itClosests.hasNext()) {
            ClosestDTO closest = itClosests.next();
            Query<PlanetDTO> findPlanetsCommand = ofy().load().type(PlanetDTO.class);
            findPlanetsCommand.filter("solarSystemId", closest.getToSolarSystemId());
            findPlanetsCommand.filter("isGasGiant", false);
            Iterator<PlanetDTO> itPlanets = findPlanetsCommand.iterator();
            while (itPlanets.hasNext()) {
                Planet planet = itPlanets.next();
                Query<MarketDTO> findMarketCommand = ofy().load().type(MarketDTO.class);
                findMarketCommand.filter("planetId", planet.getId());
                Iterator<MarketDTO> itMarket = findMarketCommand.iterator();
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

        return foundMarkets;
	}


	@Override
	public ArrayList<PlanetDistanceDTO> getClosestPlanetsForShips(
			ArrayList<ShipDTO> ships) throws DAOException {

		ArrayList<Long> foundSystems = new ArrayList<Long>();
        for (Ship ship : ships) {
            LocationDTO location = ship.getLocation();
            if (location != null) {
                foundSystems.add(location.getSolarSystemId());
            }
        }

        Query<ClosestDTO> closestCommand = ofy().load().type(ClosestDTO.class);
        closestCommand.filter("originId in", foundSystems);
        Iterator<ClosestDTO> itClosests = closestCommand.iterator();

        ArrayList<PlanetDistanceDTO> foundPlanets = new ArrayList<PlanetDistanceDTO>();
        while (itClosests.hasNext()) {
            ClosestDTO closest = itClosests.next();
            Query<PlanetDTO> findPlanetsCommand = ofy().load().type(PlanetDTO.class);
            findPlanetsCommand.filter("solarSystemId", closest.getSolarSystemId());
            Iterator<PlanetDTO> itPlanets = findPlanetsCommand.iterator();

            while (itPlanets.hasNext()) {
                foundPlanets.add(new PlanetDistanceDTO(itPlanets.next(), new DistanceDTO(closest.getNumberOfJumps(),
                    new Double(0))));
            }
        }

        return foundPlanets;

	}

}
