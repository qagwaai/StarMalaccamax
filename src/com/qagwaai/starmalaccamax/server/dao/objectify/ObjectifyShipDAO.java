package com.qagwaai.starmalaccamax.server.dao.objectify;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.ShipDAO;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;

public class ObjectifyShipDAO implements ShipDAO {

	@Override
	public ShipDTO addShip(ShipDTO newShip) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAllShips() throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<ShipDTO> getAllShips() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ShipDTO> getAllShips(int startRow, int endRow,
			ArrayList<Filter> criteria, String sortBy) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ShipDTO> getAllShips(int startRow, int endRow,
			String sortBy) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShipDTO getShip(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShipDTO getShipForCaptain(CaptainDTO captain) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalShips() throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalShipsWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeShip(ShipDTO ship) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ShipDTO updateShip(ShipDTO ship) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
