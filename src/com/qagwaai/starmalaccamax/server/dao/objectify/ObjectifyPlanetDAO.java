package com.qagwaai.starmalaccamax.server.dao.objectify;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.PlanetDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

public class ObjectifyPlanetDAO implements PlanetDAO {

	@Override
	public PlanetDTO addPlanet(PlanetDTO newPlanet) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlanetDTO> bulkAddPlanet(ArrayList<PlanetDTO> newPlanets)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAllPlanets() throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<PlanetDTO> getAllPlanets() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlanetDTO> getAllPlanets(int startRow, int endRow,
			ArrayList<Filter> criteria, String sortBy) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlanetDTO> getAllPlanets(int startRow, int endRow,
			String sortBy) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlanetDTO> getNonGasGiantPlanetsForSolarSystem(
			Long solarSystemId) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanetDTO getPlanet(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlanetDTO> getPlanetsForSolarSystem(Long solarSystemId)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalPlanets() throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalPlanetsWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removePlanet(PlanetDTO planet) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PlanetDTO updatePlanet(PlanetDTO planet) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
