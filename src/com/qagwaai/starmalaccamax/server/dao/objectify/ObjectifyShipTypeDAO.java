package com.qagwaai.starmalaccamax.server.dao.objectify;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.ShipTypeDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.ShipTypeDTO;

public class ObjectifyShipTypeDAO implements ShipTypeDAO {

	@Override
	public ShipTypeDTO addShipType(ShipTypeDTO newShipType) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ShipTypeDTO> bulkAddShipType(
			ArrayList<ShipTypeDTO> newShipTypes) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAllShipTypes() throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<ShipTypeDTO> getAllShipTypes() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ShipTypeDTO> getAllShipTypes(int startRow, int endRow,
			ArrayList<Filter> criteria, String sortBy) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ShipTypeDTO> getAllShipTypes(int startRow, int endRow,
			String sortBy) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShipTypeDTO getShipType(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalShipTypes() throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalShipTypesWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeShipType(ShipTypeDTO shipType) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ShipTypeDTO updateShipType(ShipTypeDTO shipType) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
