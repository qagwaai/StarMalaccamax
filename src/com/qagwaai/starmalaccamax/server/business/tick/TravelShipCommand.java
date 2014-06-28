/**
 * TravelShipCommand.java
 * com.qagwaai.starmalaccamax.server.tick
 * StarMalaccamax
 * Created Mar 14, 2011 at 3:11:29 PM
 */
package com.qagwaai.starmalaccamax.server.business.tick;

import java.util.Date;
import java.util.logging.Logger;

import com.qagwaai.starmalaccamax.client.Application;
import com.qagwaai.starmalaccamax.server.business.Calculations;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.GameActivityDAO;
import com.qagwaai.starmalaccamax.server.dao.GameEventDAO;
import com.qagwaai.starmalaccamax.server.dao.ShipDAO;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;
import com.qagwaai.starmalaccamax.shared.model.Location;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipTravelStatus;
import com.qagwaai.starmalaccamax.shared.model.TravelVector;
import com.qagwaai.starmalaccamax.shared.model.gameevent.ShipTravelingActivity;

/**
 * @author pgirard
 * 
 */
public final class TravelShipCommand implements TickCommand {

    /**
	 * 
	 */
    private static Logger log = Logger.getLogger(TravelShipCommand.class.getName());
    /**
	 * 
	 */
    private ShipTravelingActivity travelActivity;
    /**
	 * 
	 */
    private GameEventDTO gameEvent;

    /**
     * @return the travelActivity
     */
    public ShipTravelingActivity getTravelActivity() {
        return travelActivity;
    }

    /**
     * @param travelActivity
     *            the travelActivity to set
     */
    public void setTravelActivity(final ShipTravelingActivity travelActivity) {
        this.travelActivity = travelActivity;
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
     * @param travelActivity
     *            the activity
     * @param gameEvent
     *            the event
     */
    public TravelShipCommand(final ShipTravelingActivity travelActivity, final GameEventDTO gameEvent) {
        super();
        this.travelActivity = travelActivity;
        this.gameEvent = gameEvent;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean execute() throws TickException, DAOException {

        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        ShipDAO shipDAO = factory.getShipDAO();
        GameEventDAO gameEventDAO = factory.getGameEventDAO();
        GameActivityDAO gameActivityDAO = factory.getGameActivityDAO();
        ShipDTO ship = shipDAO.getShip(travelActivity.getShipId());

        if (ship != null) {
            // check to see if duration is correct to meet arrival time
            Location shipLocation = ship.getLocation();
            Location destination = travelActivity.getDestination();
            TravelVector travelVector = travelActivity.getVector();
            ship.setShipTravelStatus(ShipTravelStatus.Traveling);

            Date arrivalDate = travelActivity.getArrival();
            // Duration delta = Calculations.fromTimeSpanToDuration(new
            // Date(System.currentTimeMillis()), arrivalDate);
            long numTicks = travelActivity.getNumTicks();
            long currentTicks =
                Calculations.durationToTicks(Calculations.fromTimeSpanToDuration(new Date(System.currentTimeMillis()),
                    arrivalDate));
            if (Math.abs(numTicks - currentTicks) > 10) {
                log.info("resetting location for event: " + gameEvent);
                // we are off by 5 * 10 - more than 50 minutes between when we
                // originally estimated the arrival time
                // and the number of ticks required to arrive on time now.
                // Something must have happened to not
                // fire the TravelShipCommand. If a pirate attacks, then that
                // command will need to update this activity.
                // Lets fix it now

                // need to keep the original arrival time
                // but modify the location of the ship and the number of ticks
                // left
                travelActivity.setNumTicks(currentTicks);

                // reverse down the vector # of ticks
                long updatedX = destination.getX() - Math.round((currentTicks * travelVector.getX()));
                long updatedY = destination.getY() - Math.round((currentTicks * travelVector.getY()));
                long updatedZ = destination.getZ() - Math.round((currentTicks * travelVector.getZ()));
                LocationDTO updatedLocation = new LocationDTO();
                updatedLocation.setX(updatedX);
                updatedLocation.setY(updatedY);
                updatedLocation.setZ(updatedZ);
                ship.setLocation(updatedLocation);

            } else {
                LocationDTO updatedLocation = new LocationDTO();
                updatedLocation.setX(Math.round(shipLocation.getX() + travelVector.getX()));
                updatedLocation.setY(Math.round(shipLocation.getY() + travelVector.getY()));
                updatedLocation.setZ(Math.round(shipLocation.getZ() + travelVector.getZ()));
                log.info("moving ship from : " + ship.getLocation() + " to " + updatedLocation + " for event: "
                    + gameEvent);
                ship.setLocation(updatedLocation);

                travelActivity.setNumTicks(numTicks - 1);
            }
            shipDAO.updateShip(ship);
            gameActivityDAO.updateGameActivity(travelActivity);
            // update changes to this event
            // gameEvent.setActivity(travelActivity);

        } else {
            log.info("Could not find ship for game event: " + gameEvent);
        }

        gameEventDAO.updateGameEvent(gameEvent);
        return true;
    }

}
