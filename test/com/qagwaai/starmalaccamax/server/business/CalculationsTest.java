/**
 * CalculationsTest.java
 * com.qagwaai.starmalaccamax.server.business
 * StarMalaccamax
 * Created Mar 14, 2011 at 8:58:38 PM
 */
package com.qagwaai.starmalaccamax.server.business;

import org.junit.Test;

import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipTravelStatus;

/**
 * @author pgirard
 * 
 */
public class CalculationsTest {

	private static final int SHIP_DESTINATION_Z = 852986;
    private static final long SHIP_DESTINATION_Y = 227434319L;
    private static final long SHIP_DESTINATION_X = 12524518L;
    private static final int SHIP_REACTIVE_ENGINES = 9;
    private static final int SHIP_COMPUTER = 15;
    private static final long SHIPZ = -463322L;
    private static final long SHIPX = 2157553627L;
    private static final long SHIPY = -1981549371L;

    /**
	 * Test method for
	 * {@link com.qagwaai.starmalaccamax.server.business.Calculations#calculateTravelTime
	 * 		(com.qagwaai.starmalaccamax.shared.model.Ship, 
	 * 		com.qagwaai.starmalaccamax.shared.model.Location, 
	 * 		com.qagwaai.starmalaccamax.shared.model.Location)}
	 * .
	 */
	@Test
	public final void testCalculateTravelTime() {
		ShipDTO ship = new ShipDTO();

		ship.setName("myTest");
		ship.setId(1L);
		ship.getShipAttributes().setComputer(SHIP_COMPUTER);
		ship.getShipAttributes().setReactiveEngines(SHIP_REACTIVE_ENGINES);
		ship.setShipTravelStatus(ShipTravelStatus.Traveling);

		LocationDTO shipLocation = new LocationDTO();
		shipLocation.setSolarSystemId(1L);
		shipLocation.setX(SHIPX);
		shipLocation.setY(SHIPY);
		shipLocation.setZ(SHIPZ);

		ship.setLocation(shipLocation);

		LocationDTO destination = new LocationDTO();
		destination.setX(SHIP_DESTINATION_X);
		destination.setY(SHIP_DESTINATION_Y);
		destination.setZ(SHIP_DESTINATION_Z);

		System.out.println("Distance in KM: " + Calculations.calculateDistanceInKm(ship.getLocation(), destination));
		System.out.println("Duration: " + Calculations.calculateTravelTime(ship, shipLocation, destination));
	}

}
