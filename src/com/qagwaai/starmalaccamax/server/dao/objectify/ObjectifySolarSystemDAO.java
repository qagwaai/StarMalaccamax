package com.qagwaai.starmalaccamax.server.dao.objectify;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.SolarSystemDAO;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketDistanceDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketOpportunityForShipDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDistanceDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;

public class ObjectifySolarSystemDAO implements SolarSystemDAO {

	@Override
	public ClosestDTO addClosest(ClosestDTO newClosest) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SolarSystemDTO addSolarSystem(SolarSystemDTO newSolarSystem)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ClosestDTO> bulkAddClosest(
			ArrayList<ClosestDTO> newClosests) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SolarSystemDTO> bulkAddSolarSystem(
			ArrayList<SolarSystemDTO> newSolarSystems) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAllClosest() throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAllSolarSystems() throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<ClosestDTO> getAllClosests() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ClosestDTO> getAllClosests(int startRow, int endRow,
			ArrayList<Filter> criteria, String sortBy) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ClosestDTO> getAllClosests(int startRow, int endRow,
			String sortBy) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SolarSystemDTO> getAllSolarSystems() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SolarSystemDTO> getAllSolarSystems(int startRow,
			int endRow, ArrayList<Filter> criteria, String sortBy)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SolarSystemDTO> getAllSolarSystems(int startRow,
			int endRow, String sortBy) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClosestDTO getClosest(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MarketOpportunityForShipDTO> getClosestMarketsForPlanet(
			PlanetDTO planet) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MarketDTO> getClosestMarketsForPlanets(
			ArrayList<Long> planetIds) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MarketDistanceDTO> getClosestMarketsForShip(ShipDTO ship)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlanetDistanceDTO> getClosestPlanetsForPlanet(
			PlanetDTO planet) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlanetDistanceDTO> getClosestPlanetsForShips(
			ArrayList<ShipDTO> ships) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ClosestDTO> getClosestsForPlanet(PlanetDTO planet)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SolarSystemDTO getSolarSystem(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalClosests() throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalClosestsWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalSolarSystems() throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalSolarSystemsWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeClosest(ClosestDTO closest) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeSolarSystem(SolarSystemDTO solarSystem)
			throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ClosestDTO updateClosest(ClosestDTO closest) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SolarSystemDTO updateSolarSystem(SolarSystemDTO solarSystem)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
