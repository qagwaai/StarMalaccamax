/**
 * TickManager.java
 * com.qagwaai.starmalaccamax.server.business
 * StarMalaccamax
 * Created Mar 10, 2011 at 12:12:55 PM
 */
package com.qagwaai.starmalaccamax.server.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import com.qagwaai.starmalaccamax.server.Instrumentation;
import com.qagwaai.starmalaccamax.server.business.tick.LaunchShipCommand;
import com.qagwaai.starmalaccamax.server.business.tick.TickException;
import com.qagwaai.starmalaccamax.server.business.tick.TravelShipCommand;
import com.qagwaai.starmalaccamax.server.config.Configuration;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.GameActivityDAO;
import com.qagwaai.starmalaccamax.server.dao.GameEventDAO;
import com.qagwaai.starmalaccamax.server.dao.ShipDAO;
import com.qagwaai.starmalaccamax.shared.ServiceException;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.qagwaai.starmalaccamax.shared.model.Ship;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.gameevent.ShipLaunchActivity;
import com.qagwaai.starmalaccamax.shared.model.gameevent.ShipTravelingActivity;

/**
 * @author pgirard
 * 
 */
public final class TickManager {
    /**
	 * 
	 */
    private static Logger log = Logger.getLogger(TickManager.class.getName());
    /**
	 * 
	 */
    private static TickManager ref;

    /**
     * 
     * @return the singleton instance of TickManager
     */
    public static TickManager getInstance() {
        if (ref == null) {
            ref = new TickManager();
        }
        return ref;
    }

    /**
	 * 
	 */
    private TickManager() {
    }

    /**
     * called from the cron service
     * 
     * @throws ServiceException
     *             if there is a load activity failure
     */
    public void tick() throws ServiceException {
        long startTime = Instrumentation.callStart("TickManager.tick");
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        GameEventDAO gameEventDAO = factory.getGameEventDAO();
        GameActivityDAO gameActivityDAO = factory.getGameActivityDAO();

        try {
            ArrayList<GameEventDTO> expired = gameEventDAO.getEndedGameEvents();
            for (GameEventDTO event : expired) {
                log.info("ticking: " + event);
                if (event.getActivityType().equals(ShipLaunchActivity.class.getName())) {
                    ShipLaunchActivity activity =
                        gameActivityDAO.getGameActivity(ShipLaunchActivity.class, event.getActivityTypeId());
                    if (activity != null) {
                        if (new LaunchShipCommand(activity, event).execute()) {
                            event.setEventEnabled(false);
                            gameEventDAO.updateGameEvent(event);
                        } else {
                            log.info("Could not process " + event + " for some reason");
                        }
                    } else {
                        throw new ServiceException("Could not load activity [" + ShipLaunchActivity.class.getName()
                            + "] with id = " + event.getActivityTypeId());
                    }
                }
                if (event.getActivityType().equals(ShipTravelingActivity.class.getName())) {
                    ShipTravelingActivity activity =
                        gameActivityDAO.getGameActivity(ShipTravelingActivity.class, event.getActivityTypeId());
                    if (activity != null) {
                        if (new TravelShipCommand(activity, event).execute()) {
                        }
                    } else {
                        throw new ServiceException("Could not load activity [" + ShipLaunchActivity.class.getName()
                            + "] with id = " + event.getActivityTypeId());
                    }
                }
            }
        } catch (TickException e) {
            log.severe("Could not process expired events: " + e.getMessage());
        } catch (DAOException de) {
            log.severe("Could not process expired events: " + de.getMessage());
        }

        Instrumentation.callEnd("TickManager.tick", startTime, "tick", null);
    }

    /**
     * for test data
     */
    public void insertSampleGameEvents() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        GameEventDAO gameEventDAO = factory.getGameEventDAO();
        GameActivityDAO gameActivityDAO = factory.getGameActivityDAO();
        ShipDAO shipDAO = factory.getShipDAO();

        try {
            ArrayList<ShipDTO> ships = shipDAO.getAllShips();

            for (Ship ship : ships) {
                ShipLaunchActivity activity = new ShipLaunchActivity();
                LocationDTO destination = new LocationDTO();
                destination.setSolarSystemId(1L);

                destination.setX(5L);
                destination.setY(5L);
                destination.setZ(5L);

                activity.setShipId(ship.getId());
                activity.setDestination(destination);
                activity = (ShipLaunchActivity) gameActivityDAO.addGameActivity(activity);

                GameEventDTO gameEvent = new GameEventDTO();
                gameEvent.setStartDateTime(new Date(System.currentTimeMillis()));
                // check again at next tick
                gameEvent.setEndDateTime(new Date(System.currentTimeMillis()));

                gameEvent.setActivityType(ShipLaunchActivity.class.getName());
                gameEvent.setActivityTypeId(activity.getId());
                gameEvent.setPlayerId(14L);
                gameEvent.setShortDescription("Ship Launch");
                gameEvent.setEventEnabled(true);
                // it will still take time to undock
                gameEvent.setActivityCompletionDate(new Date(System.currentTimeMillis() + 500000));
                gameEvent.setFullDescription("Ship " + ship.getName() + " will launch towards "
                    + destination.toString() + " at " + gameEvent.getActivityCompletionDate().toString());

                gameEventDAO.addGameEvent(gameEvent);
            }
            ArrayList<GameEventDTO> inserted = gameEventDAO.getAllGameEvents();
            System.out.println("inserted " + inserted.size() + " records");
        } catch (DAOException e) {
            log.severe(e.getMessage());
            e.printStackTrace();
        }

    }
}
