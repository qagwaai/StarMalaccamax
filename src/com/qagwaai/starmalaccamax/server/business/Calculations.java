/**
 * DistanceCalculation.java
 * Created by pgirard at 10:13:27 PM on Dec 19, 2010
 * in the com.qagwaai.starmalaccamax.server.util package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.business;

import java.util.Calendar;
import java.util.Date;

import com.qagwaai.starmalaccamax.shared.model.DurationDTO;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipAttributes;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.TravelVectorDTO;

/**
 * @author pgirard
 * 
 */
public final class Calculations {
    /**
     * 
     * @param location1
     *            point 1
     * @param location2
     *            point 2
     * @return the distance between the two points - assumed to be in the same
     *         solar system
     */
    public static double calculateDistanceInAU(final LocationDTO location1, final LocationDTO location2) {

        // http://en.wikipedia.org/wiki/Euclidean_distance

        Long xDistance = location1.getX() - location2.getX();
        Double xDistanceSquared = Double.valueOf(xDistance) * Double.valueOf(xDistance);

        Long yDistance = location1.getY() - location2.getY();
        Double yDistanceSquared = Double.valueOf(yDistance) * Double.valueOf(yDistance);

        Long zDistance = location1.getZ() - location2.getZ();
        Double zDistanceSquared = Double.valueOf(zDistance) * Double.valueOf(zDistance);

        return Math.sqrt(xDistanceSquared + yDistanceSquared + zDistanceSquared);
    }

    /**
     * 
     * @param location1
     *            location 1
     * @param location2
     *            location 2
     * @return the distation between the two locations
     */
    public static Long calculateDistanceInKm(final LocationDTO location1, final LocationDTO location2) {
        double distance = Calculations.calculateDistanceInAU(location1, location2);

        return (long) distance;
    }

    /**
     * 
     * @param ship
     *            the ship traveling
     * @param start
     *            the start position
     * @param destination
     *            the destination position
     * @return the duration required to travel
     */
    public static DurationDTO calculateTravelTime(final ShipDTO ship, final LocationDTO start, final LocationDTO destination) {
        ShipAttributes attributes = ship.getShipAttributes();
        Integer engines = attributes.getReactiveEngines();
        Integer computer = attributes.getComputer();

        // base speed is 100,000,000km / minute
        double base = 100000L;
        if (engines > 0) {
            base *= engines;
        }
        if (computer > 0) {
            base *= computer;
        }
        long distanceInKM = calculateDistanceInKm(start, destination);
        // TODO add computer into calculation
        DurationDTO duration = new DurationDTO();
        duration.fromMinutes((long) (distanceInKM / base));

        // return the duration of the travel
        return duration;
    }

    /**
     * 
     * @param duration
     *            a duration
     * @return the number of ticks over that duration
     */
    public static long durationToTicks(final DurationDTO duration) {
        return duration.toMinutes() / 5;
    }

    /**
     * 
     * @param start
     *            the starting location
     * @param destination
     *            the destination location
     * @param duration
     *            the duration to travel between the two points
     * @return the vector used within each tick to update the ships location
     */
    public static TravelVectorDTO getVector(final LocationDTO start, final LocationDTO destination, final DurationDTO duration) {
        double xSpan = start.getX() - destination.getX();
        double ySpan = start.getY() - destination.getY();
        double zSpan = start.getZ() - destination.getZ();

        long numTicks = durationToTicks(duration);

        TravelVectorDTO tv = new TravelVectorDTO();
        tv.setX(-xSpan / numTicks);
        tv.setY(-ySpan / numTicks);
        tv.setZ(-zSpan / numTicks);

        return tv;
    }

    /**
     * 
     * @param startDate
     *            the date to add to
     * @param duration
     *            the duration
     * @return a date updated with the amount of duration
     */
    public static Date addDateAndDuration(final Date startDate, final DurationDTO duration) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.YEAR, (int) duration.getYears());
        cal.add(Calendar.MONTH, (int) duration.getMonths());
        cal.add(Calendar.DATE, (int) duration.getDays());
        cal.add(Calendar.HOUR, (int) duration.getHours());
        cal.add(Calendar.MINUTE, (int) duration.getMinutes());
        cal.add(Calendar.SECOND, (int) duration.getSeconds());
        cal.add(Calendar.MILLISECOND, (int) duration.getMilliseconds());

        return cal.getTime();
    }

    /**
     * 
     * @param startDate
     *            the start date
     * @param endDate
     *            the end date
     * @return a duration created from diffing the two dates
     */
    public static DurationDTO fromTimeSpanToDuration(final Date startDate, final Date endDate) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(startDate);
        calendar2.setTime(endDate);
        long milliseconds1 = calendar1.getTimeInMillis();
        long milliseconds2 = calendar2.getTimeInMillis();
        long diff = milliseconds2 - milliseconds1;
        long diffMinutes = diff / (60 * 1000);
        DurationDTO duration = new DurationDTO();
        duration.fromMinutes(diffMinutes);
        return duration;
    }

    /**
	 * 
	 */
    private Calculations() {
    }

}
