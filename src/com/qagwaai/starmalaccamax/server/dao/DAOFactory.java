/**
 * DAOFactory.java
 * Created by pgirard at 3:17:30 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao;

import com.qagwaai.starmalaccamax.server.dao.objectify.ObjectifyDAOFactory;
import com.qagwaai.starmalaccamax.server.dao.twig.TwigDAOFactory;

/**
 * @author pgirard
 * 
 */
public abstract class DAOFactory {

    // List of DAO types supported by the factory
    /**
	 * 
	 */
    public static final int TWIG = 1;

    /**
	 * 
	 */
    public static final int MYSQL = 2;
    
    public static final int OBJECTIFY = 3;

    /**
     * 
     * @param whichFactory
     *            which factory to return
     * @return a DAO factory
     */
    public static DAOFactory getDAOFactory(final int whichFactory) {

        switch (whichFactory) {
            case TWIG:
                return new TwigDAOFactory();
            case MYSQL:
                return null;
            default:
                return new ObjectifyDAOFactory();
        }
    }

    /**
	 * 
	 */
    public DAOFactory() {

    }

    /**
     * 
     * @return the CaptainDAO
     */
    public abstract CaptainDAO getCaptainDAO();

    /**
     * 
     * @return the GameEventDAO
     */
    public abstract GameEventDAO getGameEventDAO();

    /**
     * 
     * @return the JobDAO
     */
    public abstract JobDAO getJobDAO();

    /**
     * 
     * @return the JumpGateDAO
     */
    public abstract JumpGateDAO getJumpGateDAO();

    /**
     * 
     * @return the MarketDAO
     */
    public abstract MarketDAO getMarketDAO();

    /**
     * 
     * @return the PlanetDAO
     */
    public abstract PlanetDAO getPlanetDAO();

    /**
     * 
     * @return the ShipDAO
     */
    public abstract ShipDAO getShipDAO();

    /**
     * 
     * @return the ShipTypeDAO
     */
    public abstract ShipTypeDAO getShipTypeDAO();

    /**
     * 
     * @return the SolarSystemDAO
     */
    public abstract SolarSystemDAO getSolarSystemDAO();

    /**
     * 
     * @return the StarDAO
     */
    public abstract StarDAO getStarDAO();

    /**
     * 
     * @return the UserDAO
     */
    public abstract UserDAO getUserDAO();

    /**
     * 
     * @return the GameActivityDAO
     */
    public abstract GameActivityDAO getGameActivityDAO();
}
