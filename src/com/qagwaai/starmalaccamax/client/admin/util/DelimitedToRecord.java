/**
 * CommaToSolarSystem.java
 * Created by pgirard at 2:54:31 PM on Nov 1, 2010
 * in the com.qagwaai.starmalaccamax.client.util package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.admin.util;

import com.qagwaai.starmalaccamax.shared.TransformationException;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;
import com.qagwaai.starmalaccamax.shared.model.JobDTO;
import com.qagwaai.starmalaccamax.shared.model.JumpGateDTO;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketCommodityDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipTypeDTO;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;
import com.qagwaai.starmalaccamax.shared.model.StarDTO;

/**
 * @author pgirard
 * 
 */
public final class DelimitedToRecord {
    private static final int CLOSEST_SOLAR_SYSTEM_ID = 1;
    private static final int CLOSEST_NUM_JUMPS = 2;
    private static final int CLOSEST_TO_SOLAR_SYSTEM_ID = 3;

    /**
     * 
     * @param s
     *            the string to convert
     * @return a boolean or false
     */
    private static Boolean toBoolean(final String s) {
        try {
            if (s.trim().equals("1")) {
                return Boolean.valueOf(true);
            }
            return Boolean.valueOf(s);
        } catch (Throwable t) {
            return Boolean.valueOf(false);
        }
    }

    /**
     * 
     * @param buffer
     *            the delimited string
     * @return the hydrated object or null
     * @throws TransformationException
     *             - if the id could not be converted to a Long
     * 
     */
    public static ClosestDTO toClosest(final String buffer) throws TransformationException {
        ClosestDTO record = new ClosestDTO();
        // String[] fields = buffer.split("\\|");
        String buf = buffer.replaceAll(";;", "; ;");
        buf = buf.replaceAll(";;", "; ;");
        buf = buf.replaceAll("\"", "");
        String[] fields = buf.split(";");
        if (fields.length > 0) {
            if (fields[0] != null) {
                record.setId(Long.valueOf(fields[0]));
            } else {
                throw new TransformationException("Error on record, null id\n");
            }
            record.setSolarSystemId(Long.valueOf(fields[CLOSEST_SOLAR_SYSTEM_ID].trim()));
            record.setNumberOfJumps(Integer.valueOf(fields[CLOSEST_NUM_JUMPS].trim()));
            record.setToSolarSystemId(Long.valueOf(fields[CLOSEST_TO_SOLAR_SYSTEM_ID].trim()));
        }
        return record;
    }

    /**
     * 
     * @param s
     *            the string to convert
     * @return a double value or -1
     */
    private static Double toDouble(final String s) {
        try {
            return Double.valueOf(s);
        } catch (Throwable t) {
            return Double.valueOf(-1);
        }
    }

    /**
     * 
     * @param s
     *            the string to convert
     * @return an integer or -1
     */
    private static Integer toInteger(final String s) {
        try {
            return Integer.valueOf(s);
        } catch (Throwable t) {
            return Integer.valueOf(-1);
        }
    }

    private static final int JOB_COMMAND_CLASS = 1;
    private static final int JOB_DESCRIPTION = 2;
    private static final int JOB_RECURRENCE = 3;
    private static final int JOB_STATUS = 4;
    private static final int JOB_ENABLED = 5;

    /**
     * 
     * @param buffer
     *            the delimited string
     * @return the hydrated object or null
     * @throws TransformationException
     *             - if the id could not be converted to a Long
     * 
     */
    public static JobDTO toJob(final String buffer) throws TransformationException {
        JobDTO record = new JobDTO();
        String[] fields = buffer.split("\\|");
        if (fields.length > 0) {
            if (fields[0] != null) {
                record.setId(Long.valueOf(fields[0]));
            } else {
                throw new TransformationException("Error on record, null id\n");
            }
            record.setCommandClass(fields[JOB_COMMAND_CLASS]);
            record.setDescription(fields[JOB_DESCRIPTION]);
            record.setRecurrence(Long.valueOf(fields[JOB_RECURRENCE]));
            record.setStatus(fields[JOB_STATUS]);
            record.setEnabled(toBoolean(fields[JOB_ENABLED]));
        }
        return record;
    }

    private static final int JUMP_GATE_SOLAR_SYSTEM_ID1 = 1;
    private static final int JUMP_GATE_SOLAR_SYSTEM_ID2 = 2;
    private static final int JUMP_GATE_LOCATION_1_X = 3;
    private static final int JUMP_GATE_LOCATION_1_Y = 4;
    private static final int JUMP_GATE_LOCATION_1_Z = 5;
    private static final int JUMP_GATE_LOCATION_2_X = 6;
    private static final int JUMP_GATE_LOCATION_2_Y = 7;
    private static final int JUMP_GATE_LOCATION_2_Z = 8;

    /**
     * 
     * @param buffer
     *            the delimited string
     * @return the hydrated object or null
     * @throws TransformationException
     *             - if the id could not be converted to a Long
     * 
     */
    public static JumpGateDTO toJumpGate(final String buffer) throws TransformationException {
        JumpGateDTO record = new JumpGateDTO();
        // String[] fields = buffer.split("\\|");
        String buf = buffer.replaceAll(";;", "; ;");
        buf = buf.replaceAll(";;", "; ;");
        buf = buf.replaceAll("\"", "");
        String[] fields = buf.split(";");
        if (fields.length > 0) {
            if (fields[0] != null) {
                record.setId(Long.valueOf(fields[0]));
            } else {
                throw new TransformationException("Error on record, null id\n");
            }
            record.setSolarSystem1Id(Long.valueOf(fields[JUMP_GATE_SOLAR_SYSTEM_ID1].trim()));
            record.setSolarSystem2Id(Long.valueOf(fields[JUMP_GATE_SOLAR_SYSTEM_ID2].trim()));
            LocationDTO location1 = new LocationDTO();
            location1.setSolarSystemId(record.getSolarSystem1Id());
            location1.setX(Long.valueOf(fields[JUMP_GATE_LOCATION_1_X].trim()));
            location1.setY(Long.valueOf(fields[JUMP_GATE_LOCATION_1_Y].trim()));
            location1.setZ(Long.valueOf(fields[JUMP_GATE_LOCATION_1_Z].trim()));
            record.setLocation1(location1);

            LocationDTO location2 = new LocationDTO();
            location2.setSolarSystemId(record.getSolarSystem2Id());
            location2.setX(Long.valueOf(fields[JUMP_GATE_LOCATION_2_X].trim()));
            location2.setY(Long.valueOf(fields[JUMP_GATE_LOCATION_2_Y].trim()));
            location2.setZ(Long.valueOf(fields[JUMP_GATE_LOCATION_2_Z].trim()));
            record.setLocation2(location2);
        }
        return record;
    }

    /**
     * 
     * @param s
     *            the string to convert
     * @return a long
     */
    private static Long toLong(final String s) {
        try {
            return Long.valueOf(s);
        } catch (Throwable t) {
            return Long.valueOf(-1);
        }
    }

    private static final int MARKET_PURCHASE_AMOUNT_WANTED = 1;
    private static final int MARKET_PURCHASE_PRICE = 2;
    private static final int MARKET_SELL_AMOUNT_WANTED = 3;
    private static final int MARKET_SELL_PRICE = 4;
    private static final int MARKET_FIELDS = 5;

    /**
     * 
     * @param buffer
     *            the delimited string
     * @return the hydrated object or null
     * @throws TransformationException
     *             if the id could not be converted to Long
     */
    public static MarketDTO toMarket(final String buffer) throws TransformationException {
        MarketDTO market = new MarketDTO();
        // String[] fields = buffer.split("\\|");
        String buf = buffer.replaceAll(";;", "; ;");
        buf = buf.replaceAll(";;", "; ;");
        buf = buf.replaceAll("\"", "");
        String[] fields = buf.split(";");
        if (fields.length > 0) {
            if (fields[0] != null) {
                market.setId(Long.valueOf(fields[0]));
            } else {
                throw new TransformationException("Error on record, null id\n");
            }
        }
        market.setPlanetId(Long.valueOf(fields[1]));
        int fieldIndex = 2;
        while (fieldIndex < fields.length) {
            String key = fields[fieldIndex];
            short paa = Short.valueOf(fields[fieldIndex + MARKET_PURCHASE_AMOUNT_WANTED]);
            short pp = Short.valueOf(fields[fieldIndex + MARKET_PURCHASE_PRICE]);
            short saa = Short.valueOf(fields[fieldIndex + MARKET_SELL_AMOUNT_WANTED]);
            short sp = Short.valueOf(fields[fieldIndex + MARKET_SELL_PRICE].trim());
            MarketCommodityDTO commodity = new MarketCommodityDTO();
            commodity.setName(key);
            commodity.setPurchaseAmountWanted(paa);
            commodity.setPurchasePrice(pp);
            commodity.setSellAmountAvailable(saa);
            commodity.setSellPrice(sp);
            market.getCommodities().put(key, commodity);
            fieldIndex += MARKET_FIELDS;
        }
        return market;
    }

    private static final int PLANET_SOLAR_SYSTEM_ID = 1;
    private static final int PLANET_NAME = 2;
    private static final int PLANET_ORBIT = 3;
    private static final int PLANET_SIZE = 4;
    private static final int PLANET_ATMOSPHERE = 5;
    private static final int PLANET_HYDROGRAPHICS = 6;
    private static final int PLANET_POPULATION = 7;
    private static final int PLANET_DOCK_QUALITY = 8;
    private static final int PLANET_GOVERNMENT = 9;
    private static final int PLANET_LAWLEVEL = 10;
    private static final int PLANET_IS_GAS_GIANT = 11;
    private static final int PLANET_TECH_LEVEL = 12;
    private static final int PLANET_IS_MAIN_WORLD = 13;
    private static final int PLANET_IS_SATELLITE = 14;
    private static final int PLANET_ORBIT_CENTER_X = 15;
    private static final int PLANET_ORBIT_CENTER_Y = 16;
    private static final int PLANET_ORBITAL_ECC = 17;
    private static final int PLANET_ORBIT_ROTATION = 18;
    private static final int PLANET_ORBIT_X_REL = 19;
    private static final int PLANET_ORBIT_Y_REL = 20;
    private static final int PLANET_LOCATION_X = 21;
    private static final int PLANET_LOCATION_Y = 22;
    private static final int PLANET_LOCATION_Z = 23;
    private static final int PLANET_ORBITAL_DISTANCE = 24;
    private static final int PLANET_SATELLITE_PARENT_ID = 25;

    /**
     * 
     * @param buffer
     *            the delimited string
     * @return the hydrated object or null
     * @throws TransformationException
     *             - if the id could not be converted to a Long
     * 
     */
    public static PlanetDTO toPlanet(final String buffer) throws TransformationException {
        PlanetDTO record = new PlanetDTO();
        // String[] fields = buffer.split("\\|");
        String buf = buffer.replaceAll(";;", "; ;");
        buf = buf.replaceAll(";;", "; ;");
        buf = buf.replaceAll("\"", "");
        String[] fields = buf.split(";");
        if (fields.length > 0) {
            if (fields[0] != null) {
                record.setId(Long.valueOf(fields[0]));
            } else {
                throw new TransformationException("Error on record, null id\n");
            }
            record.setSolarSystemId(Long.valueOf(fields[PLANET_SOLAR_SYSTEM_ID]));
            record.setName(fields[PLANET_NAME]);
            record.setOrbit(Integer.valueOf(fields[PLANET_ORBIT]));
            record.setSize(toInteger(fields[PLANET_SIZE]));
            record.setAtmosphere(toInteger(fields[PLANET_ATMOSPHERE]));
            record.setHydrographics(toDouble(fields[PLANET_HYDROGRAPHICS]));
            record.setPopulation(Long.valueOf(fields[PLANET_POPULATION]));
            record.setDockQuality(toInteger(fields[PLANET_DOCK_QUALITY]));
            record.setGovernment(toInteger(fields[PLANET_GOVERNMENT]));
            record.setLawLevel(toInteger(fields[PLANET_LAWLEVEL]));
            record.setGasGiant(toBoolean(fields[PLANET_IS_GAS_GIANT]));
            record.setTechLevel(toInteger(fields[PLANET_TECH_LEVEL]));
            record.setMainWorld(toBoolean(fields[PLANET_IS_MAIN_WORLD]));
            record.setSatellite(toBoolean(fields[PLANET_IS_SATELLITE]));
            record.setOrbitCenterX(toInteger(fields[PLANET_ORBIT_CENTER_X]));
            record.setOrbitCenterY(toInteger(fields[PLANET_ORBIT_CENTER_Y]));
            record.setOrbitalEccentricity(toDouble(fields[PLANET_ORBITAL_ECC]));
            record.setOrbitRotation(toInteger(fields[PLANET_ORBIT_ROTATION]));
            record.setOrbitXRel(toInteger(fields[PLANET_ORBIT_X_REL]));
            record.setOrbitYRel(toInteger(fields[PLANET_ORBIT_Y_REL]));

            LocationDTO location = new LocationDTO();
            location.setSolarSystemId(record.getSolarSystemId());
            location.setPlanetId(record.getId());
            location.setX(Long.valueOf(fields[PLANET_LOCATION_X]));
            location.setY(Long.valueOf(fields[PLANET_LOCATION_Y]));
            location.setZ(Long.valueOf(fields[PLANET_LOCATION_Z]));

            record.setLocation(location);
            record.setOrbitalDistance(toDouble(fields[PLANET_ORBITAL_DISTANCE]));

            record.setSatelliteParentId(toLong(fields[PLANET_SATELLITE_PARENT_ID].trim()));
        }
        return record;
    }

    private static final int SHIP_TYPE_NAME = 1;

    /**
     * 
     * @param buffer
     *            the delimited string
     * @return the hydrated object or null
     * @throws TransformationException
     *             - if the id could not be converted to a Long
     * 
     */
    public static ShipTypeDTO toShipType(final String buffer) throws TransformationException {
        ShipTypeDTO record = new ShipTypeDTO();
        String[] fields = buffer.split("\\|");
        if (fields.length > 0) {
            if (fields[0] != null) {
                record.setId(Long.valueOf(fields[0]));
            } else {
                throw new TransformationException("Error on record, null id\n");
            }
            record.setName(fields[SHIP_TYPE_NAME]);

        }
        return record;
    }

    private static final int SOLAR_SYSTEM_HIP = 1;
    private static final int SOLAR_SYSTEM_ALPHA = 2;
    private static final int SOLAR_SYSTEM_DELTA = 3;
    private static final int SOLAR_SYSTEM_PARALLAX = 4;
    private static final int SOLAR_SYSTEM_X = 5;
    private static final int SOLAR_SYSTEM_Y = 6;
    private static final int SOLAR_SYSTEM_Z = 7;
    private static final int SOLAR_SYSTEM_NAME = 8;
    private static final int SOLAR_SYSTEM_NUM_COMPONENTS = 9;
    private static final int SOLAR_SYSTEM_MAX_ORBITS = 10;
    private static final int SOLAR_SYSTEM_HABITABLE_ORBIT = 11;
    private static final int SOLAR_SYSTEM_NUM_GAS_GIANTS = 12;
    private static final int SOLAR_SYSTEM_NUM_PLANETOID_BELTS = 13;

    /**
     * 
     * @param buffer
     *            the delimited string
     * @return the hydrated object or null
     * @throws TransformationException
     *             - if the id could not be converted to a Long
     */
    public static SolarSystemDTO toSolarSystem(final String buffer) throws TransformationException {
        /*
         * String buf = buffer.replaceAll("\\|\\|", "| |"); buf =
         * buf.replaceAll("\\|\\|", "| |"); buf = buf.replaceAll("\\|$", "| ");
         * String[] fields = buf.split("\\|");
         */
        String buf = buffer.replaceAll(";;", "; ;");
        buf = buf.replaceAll(";;", "; ;");
        buf = buf.replaceAll("\"", "");
        String[] fields = buf.split(";");

        SolarSystemDTO record = new SolarSystemDTO();
        if (fields.length > 0) {
            if (fields[0] != null) {
                record.setId(Long.valueOf(fields[0]));
            } else {
                throw new TransformationException("Error on record, null id\n");
            }
            record.setHIP(toInteger(fields[SOLAR_SYSTEM_HIP]));
            record.setAlpha(toDouble(fields[SOLAR_SYSTEM_ALPHA]));
            record.setDelta(toDouble(fields[SOLAR_SYSTEM_DELTA]));
            record.setParallax(toDouble(fields[SOLAR_SYSTEM_PARALLAX]));
            record.setX(toDouble(fields[SOLAR_SYSTEM_X]));
            record.setY(toDouble(fields[SOLAR_SYSTEM_Y]));
            record.setZ(toDouble(fields[SOLAR_SYSTEM_Z]));
            record.setName(fields[SOLAR_SYSTEM_NAME]);
            record.setNumberOfComponents(toInteger(fields[SOLAR_SYSTEM_NUM_COMPONENTS]));
            record.setMaximumOrbits(toInteger(fields[SOLAR_SYSTEM_MAX_ORBITS]));
            record.setHabitableOrbit(toInteger(fields[SOLAR_SYSTEM_HABITABLE_ORBIT]));
            record.setNumberOfGasGiants(toInteger(fields[SOLAR_SYSTEM_NUM_GAS_GIANTS]));
            record.setNumberOfPlanetoidBelts(toInteger(fields[SOLAR_SYSTEM_NUM_PLANETOID_BELTS].trim()));
        }
        return record;
    }

    private static final int STAR_SOLAR_SYSTEM_ID = 1;
    private static final int STAR_SPECTRAL_TYPE = 2;
    private static final int STAR_SPECTRAL_TYPE_DECIMAL = 3;
    private static final int STAR_SIZE = 4;
    private static final int STAR_COMPONENT_ID = 5;
    private static final int STAR_IS_COMPANION = 6;

    /**
     * 
     * @param buffer
     *            the delimited string
     * @return the hydrated object or null
     * @throws TransformationException
     *             - if the id could not be converted to a Long
     * 
     */
    public static StarDTO toStar(final String buffer) throws TransformationException {
        StarDTO record = new StarDTO();
        // String[] fields = buffer.split("\\|");
        String buf = buffer.replaceAll(";;", "; ;");
        buf = buf.replaceAll(";;", "; ;");
        buf = buf.replaceAll("\"", "");
        String[] fields = buf.split(";");
        if (fields.length > 0) {
            if (fields[0] != null) {
                record.setId(Long.valueOf(fields[0]));
            } else {
                throw new TransformationException("Error on record, null id\n");
            }
            record.setSolarSystemId(Long.valueOf(fields[STAR_SOLAR_SYSTEM_ID].trim()));
            record.setSpectralType(fields[STAR_SPECTRAL_TYPE]);
            record.setSpectralTypeDec(toDouble(fields[STAR_SPECTRAL_TYPE_DECIMAL].trim()));
            record.setSize(fields[STAR_SIZE]);
            record.setComponentIdentifier(fields[STAR_COMPONENT_ID]);
            record.setCompanion(toBoolean(fields[STAR_IS_COMPANION].trim()));
        }
        return record;
    }

    /**
	 * 
	 */
    private DelimitedToRecord() {

    }
}
