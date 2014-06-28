package com.qagwaai.starmalaccamax.server.dao.objectify;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.MarketDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

public class ObjectifyMarketDAO implements MarketDAO {

	@Override
	public MarketDTO addMarket(MarketDTO newMarket) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MarketDTO> bulkAddMarket(ArrayList<MarketDTO> newMarkets)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAllMarkets() throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<MarketDTO> getAllMarkets() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MarketDTO> getAllMarkets(int startRow, int endRow,
			ArrayList<Filter> criteria, String sortBy) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MarketDTO> getAllMarkets(int startRow, int endRow,
			String sortBy) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MarketDTO getMarket(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MarketDTO getMarketForPlanet(PlanetDTO planet) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MarketDTO> getMarketsForPlanets(ArrayList<Long> planetIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalMarkets() throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalMarketsWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeMarket(MarketDTO market) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MarketDTO updateMarket(MarketDTO market) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
