/**
 * DataNucleusUserDAO.java
 * Created by pgirard at 3:25:05 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao.datanucleus package
 * for the GameActivityMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao.twig;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.google.code.twig.ObjectDatastore;
import com.google.code.twig.annotation.AnnotationObjectDatastore;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.GameActivityDAO;
import com.qagwaai.starmalaccamax.shared.model.gameevent.GameActivity;

/**
 * @author pgirard
 * 
 */
public final class TwigGameActivityDAO implements GameActivityDAO {

    /**
	 * 
	 */
    public TwigGameActivityDAO() {

    }

    /**
     * 
     * {@inheritDoc}
     */
    public <U extends GameActivity> GameActivity addGameActivity(final U newGameActivity) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        Transaction trans = datastore.beginTransaction();
        Key key = datastore.store(newGameActivity);
        newGameActivity.setId(key.getId());
        trans.commit();

        return newGameActivity;
    }

    /**
     * 
     * {@inheritDoc}
     */
    /*
     * @Override public GameActivity addGameActivity(final GameActivity
     * newGameActivity) throws DAOException { ObjectDatastore datastore = new
     * AnnotationObjectDatastore();
     * 
     * Transaction trans = datastore.beginTransaction(); Key key =
     * datastore.store(newGameActivity); newGameActivity.setId(key.getId());
     * trans.commit();
     * 
     * return newGameActivity; }
     */
    /**
     * 
     * @param itGameActivitys
     *            the GameActivitys to convert
     * @return the converted GameActivitys
     */
    /*
     * private ArrayList<GameActivity> convertToInterface(final
     * Iterator<GameActivity> itGameActivitys) { ArrayList<GameActivity> list =
     * new ArrayList<GameActivity>();
     * 
     * while (itGameActivitys.hasNext()) { list.add(itGameActivitys.next()); }
     * return list; }
     */

    /**
     * 
     * {@inheritDoc}
     * 
     * @return
     */
    @Override
    public <U extends GameActivity> U getGameActivity(@SuppressWarnings("rawtypes") final Class clazz, final Long id)
        throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        return (U) datastore.load(clazz, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public <U extends GameActivity> boolean removeGameActivity(final U gameActivity) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.associate(gameActivity);
        datastore.delete(gameActivity);
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public <U extends GameActivity> GameActivity updateGameActivity(final U gameActivity) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.storeOrUpdate(gameActivity);

        return gameActivity;
    }
}
