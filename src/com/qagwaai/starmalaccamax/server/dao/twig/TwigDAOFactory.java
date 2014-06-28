/**
 * DataNucleusDAOFactory.java
 * Created by pgirard at 3:19:34 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao.twig;

import com.qagwaai.starmalaccamax.server.dao.CaptainDAO;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.GameActivityDAO;
import com.qagwaai.starmalaccamax.server.dao.GameEventDAO;
import com.qagwaai.starmalaccamax.server.dao.JobDAO;
import com.qagwaai.starmalaccamax.server.dao.JumpGateDAO;
import com.qagwaai.starmalaccamax.server.dao.MarketDAO;
import com.qagwaai.starmalaccamax.server.dao.PlanetDAO;
import com.qagwaai.starmalaccamax.server.dao.ShipDAO;
import com.qagwaai.starmalaccamax.server.dao.ShipTypeDAO;
import com.qagwaai.starmalaccamax.server.dao.SolarSystemDAO;
import com.qagwaai.starmalaccamax.server.dao.StarDAO;
import com.qagwaai.starmalaccamax.server.dao.UserDAO;

/**
 * @author pgirard
 * 
 */
public final class TwigDAOFactory extends DAOFactory {

    /**
	 * 
	 */
    public TwigDAOFactory() {

    }

    /**
     * 
     * {@inheritDoc}
     */
    public CaptainDAO getCaptainDAO() {
        return new TwigCaptainDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public GameEventDAO getGameEventDAO() {
        return new TwigGameEventDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public JobDAO getJobDAO() {
        return new TwigJobDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public JumpGateDAO getJumpGateDAO() {
        return new TwigJumpGateDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public MarketDAO getMarketDAO() {
        return new TwigMarketDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public PlanetDAO getPlanetDAO() {
        return new TwigPlanetDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ShipDAO getShipDAO() {
        return new TwigShipDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ShipTypeDAO getShipTypeDAO() {
        return new TwigShipTypeDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public SolarSystemDAO getSolarSystemDAO() {
        return new TwigSolarSystemDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public StarDAO getStarDAO() {
        return new TwigStarDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public UserDAO getUserDAO() {
        return new TwigUserDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public GameActivityDAO getGameActivityDAO() {
        return new TwigGameActivityDAO();
    }
}
