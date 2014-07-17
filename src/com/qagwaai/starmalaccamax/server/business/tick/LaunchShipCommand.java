/**
 * LaunchShipCommand.java
 * com.qagwaai.starmalaccamax.server.tick
 * StarMalaccamax
 * Created Mar 11, 2011 at 2:18:03 PM
 */
package com.qagwaai.starmalaccamax.server.business.tick;

import java.util.Date;
import java.util.logging.Logger;

import com.qagwaai.starmalaccamax.server.business.Calculations;
import com.qagwaai.starmalaccamax.server.config.Configuration;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.GameActivityDAO;
import com.qagwaai.starmalaccamax.server.dao.GameEventDAO;
import com.qagwaai.starmalaccamax.server.dao.ShipDAO;
import com.qagwaai.starmalaccamax.shared.model.DurationDTO;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipTravelStatus;
import com.qagwaai.starmalaccamax.shared.model.gameevent.ShipLaunchActivity;
import com.qagwaai.starmalaccamax.shared.model.gameevent.ShipTravelingActivity;

/**
 * @author pgirard
 * 
 */
public final class LaunchShipCommand implements TickCommand {
    /**
	 * 
	 */
    private static Logger log = Logger.getLogger(LaunchShipCommand.class.getName());
    /**
	 * 
	 */
    private ShipLaunchActivity launchActivity;
    /**
	 * 
	 */
    private GameEventDTO gameEvent;

    /**
     * @return the launchActivity
     */
    public ShipLaunchActivity getLaunchActivity() {
        return launchActivity;
    }

    /**
     * @param launchActivity
     *            the launchActivity to set
     */
    public void setLaunchActivity(final ShipLaunchActivity launchActivity) {
        this.launchActivity = launchActivity;
    }

    /**
     * @return the gameEvent
     */
    public GameEventDTO getGameEvent() {
        return gameEvent;
    }

    /**
     * @param gameEvent
     *            the gameEvent to set
     */
    public void setGameEvent(final GameEventDTO gameEvent) {
        this.gameEvent = gameEvent;
    }

    /**
     * @param launchActivity
     *            the activity
     * @param gameEvent
     *            the event
     */
    public LaunchShipCommand(final ShipLaunchActivity launchActivity, final GameEventDTO gameEvent) {
        super();
        this.launchActivity = launchActivity;
        this.gameEvent = gameEvent;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean execute() throws TickException, DAOException {
        if (launchActivity == null || gameEvent == null) {
            return false;
        }
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        ShipDAO shipDAO = factory.getShipDAO();
        GameEventDAO gameEventDAO = factory.getGameEventDAO();
        GameActivityDAO gameActivityDAO = factory.getGameActivityDAO();
        ShipDTO toLaunch = shipDAO.getShip(launchActivity.getShipId());
        log.info("Launching ship " + toLaunch);
        toLaunch.setShipTravelStatus(ShipTravelStatus.Undocking);
        shipDAO.updateShip(toLaunch);

        LocationDTO shipLocation = toLaunch.getLocation();
        LocationDTO destination = launchActivity.getDestination();

        GameEventDTO travelEvent = new GameEventDTO();
        travelEvent.setEventEnabled(true);
        DurationDTO travelTime = Calculations.calculateTravelTime(toLaunch, shipLocation, destination);
        travelEvent.setEndDateTime(new Date(System.currentTimeMillis()));
        log.info("Launch Ship: duration = " + travelTime + ", completed: " + travelEvent.getEndDateTime());
        travelEvent.setPlayerId(gameEvent.getPlayerId());
        travelEvent.setShortDescription("Ship travelling...");
        travelEvent.setStartDateTime(new Date(System.currentTimeMillis()));

        ShipTravelingActivity travelActivity = new ShipTravelingActivity();
        travelActivity.setShipId(toLaunch.getId());
        travelActivity.setDestination(destination);
        travelActivity.setArrival(Calculations.addDateAndDuration(new Date(System.currentTimeMillis()), travelTime));
        travelEvent.setFullDescription("Ship " + toLaunch.getName() + " will arrive at " + destination + " at "
            + travelActivity.getArrival().toString());
        travelActivity.setVector(Calculations.getVector(shipLocation, destination, travelTime));
        log.info("Launch Ship: will travel on vector: " + travelActivity.getVector());
        travelActivity.setNumTicks(Calculations.durationToTicks(travelTime));
        travelActivity = (ShipTravelingActivity) gameActivityDAO.addGameActivity(travelActivity);
        travelEvent.setActivityType(ShipTravelingActivity.class.getName());
        travelEvent.setActivityTypeId(travelActivity.getId());
        travelEvent.setActivityCompletionDate(travelActivity.getArrival());

        // travelEvent.setActivity(travelActivity);
        gameEventDAO.addGameEvent(travelEvent);

        return true;
    }

}
