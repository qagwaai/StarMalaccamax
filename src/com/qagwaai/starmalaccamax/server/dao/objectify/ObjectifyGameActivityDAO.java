package com.qagwaai.starmalaccamax.server.dao.objectify;

import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.GameActivityDAO;
import com.qagwaai.starmalaccamax.shared.model.gameevent.GameActivity;

public class ObjectifyGameActivityDAO implements GameActivityDAO {

	@Override
	public <U extends GameActivity> GameActivity addGameActivity(
			U newGameActivity) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U extends GameActivity> U getGameActivity(Class clazz, Long id)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U extends GameActivity> boolean removeGameActivity(U gameActivity)
			throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <U extends GameActivity> GameActivity updateGameActivity(
			U gameActivity) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
