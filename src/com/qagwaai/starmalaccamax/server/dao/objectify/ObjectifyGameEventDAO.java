package com.qagwaai.starmalaccamax.server.dao.objectify;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.GameEventDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;

public class ObjectifyGameEventDAO implements GameEventDAO {

	@Override
	public GameEventDTO addGameEvent(GameEventDTO newGameEvent)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GameEventDTO> bulkAddGameEvent(
			ArrayList<GameEventDTO> newGameEvents) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAllGameEvents() throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<GameEventDTO> getAllGameEvents() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GameEventDTO> getAllGameEvents(int startRow, int endRow,
			ArrayList<Filter> criteria, String sortBy) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GameEventDTO> getAllGameEvents(int startRow, int endRow,
			String sortBy) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameEventDTO getGameEvent(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalGameEvents() throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalGameEventsWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeGameEvent(GameEventDTO gameEvent) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GameEventDTO updateGameEvent(GameEventDTO gameEvent)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GameEventDTO> getEndedGameEvents() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
