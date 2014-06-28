package com.qagwaai.starmalaccamax.server.dao.objectify;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.StarDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.StarDTO;

public class ObjectifyStarDAO implements StarDAO {

	@Override
	public StarDTO addStar(StarDTO newStar) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StarDTO> bulkAddStar(ArrayList<StarDTO> newStars)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAllStars() throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<StarDTO> getAllStars() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StarDTO> getAllStars(int startRow, int endRow,
			ArrayList<Filter> criteria, String sortBy) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StarDTO> getAllStars(int startRow, int endRow,
			String sortBy) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StarDTO getStar(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StarDTO> getStarsForSolarSystem(Long id)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalStars() throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalStarsWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeStar(StarDTO star) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public StarDTO updateStar(StarDTO star) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
