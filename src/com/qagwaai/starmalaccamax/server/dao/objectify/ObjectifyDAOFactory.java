/**
 * DataNucleusDAOFactory.java
 * Created by pgirard at 3:19:34 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao.objectify;

import java.util.logging.Logger;

import com.googlecode.objectify.ObjectifyService;
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
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;
import com.qagwaai.starmalaccamax.shared.model.JobDTO;
import com.qagwaai.starmalaccamax.shared.model.JumpGateDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipTypeDTO;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;
import com.qagwaai.starmalaccamax.shared.model.StarDTO;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public final class ObjectifyDAOFactory extends DAOFactory {

	private static Logger log = Logger.getLogger(ObjectifyDAOFactory.class.getName());
	
	static {
		log.severe("StarMalaccamax Objectify Registering classes");
    	try {
			//ObjectifyService.register(AppErrorDTO.class);
			ObjectifyService.register(CaptainDTO.class);
			//ObjectifyService.register(CaptainSkills.class);
			//ObjectifyService.register(CaptainAttributes.class);
			//ObjectifyService.register(CaptainSolarSystemDTO.class);
			ObjectifyService.register(ChatRoomDTO.class);
			ObjectifyService.register(ClosestDTO.class);
			//ObjectifyService.register(CountryDTO.class);
			//ObjectifyService.register(DistanceDTO.class);
			//ObjectifyService.register(DurationDTO.class);
			ObjectifyService.register(GameEventDTO.class);
			ObjectifyService.register(JobDTO.class);
			ObjectifyService.register(JumpGateDTO.class);
			//ObjectifyService.register(LocationDTO.class);
			ObjectifyService.register(MarketDTO.class);
			//ObjectifyService.register(MarketCommodityDTO.class);
			//ObjectifyService.register(MarketOpportunityForShipDTO.class);
			ObjectifyService.register(PlanetDTO.class);
			//ObjectifyService.register(PlanetDistanceDTO.class);
			//ObjectifyService.register(SatelliteDTO.class);
			ObjectifyService.register(ShipDTO.class);
			ObjectifyService.register(ShipTypeDTO.class);
			ObjectifyService.register(SolarSystemDTO.class);
			ObjectifyService.register(StarDTO.class);
			//ObjectifyService.register(TravelVectorDTO.class);
			ObjectifyService.register(UserDTO.class);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    /**
	 * 
	 */
    public ObjectifyDAOFactory() {

    }

    /**
     * 
     * {@inheritDoc}
     */
    public CaptainDAO getCaptainDAO() {
        return new ObjectifyCaptainDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public GameEventDAO getGameEventDAO() {
        return new ObjectifyGameEventDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public JobDAO getJobDAO() {
        return new ObjectifyJobDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public JumpGateDAO getJumpGateDAO() {
        return new ObjectifyJumpGateDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public MarketDAO getMarketDAO() {
        return new ObjectifyMarketDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public PlanetDAO getPlanetDAO() {
        return new ObjectifyPlanetDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ShipDAO getShipDAO() {
        return new ObjectifyShipDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ShipTypeDAO getShipTypeDAO() {
        return new ObjectifyShipTypeDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public SolarSystemDAO getSolarSystemDAO() {
        return new ObjectifySolarSystemDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public StarDAO getStarDAO() {
        return new ObjectifyStarDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public UserDAO getUserDAO() {
        return new ObjectifyUserDAO();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public GameActivityDAO getGameActivityDAO() {
        return new ObjectifyGameActivityDAO();
    }
}
