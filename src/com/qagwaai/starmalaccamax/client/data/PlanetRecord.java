/**
 * UserRecord.java
 * Created by pgirard at 10:29:55 AM on Aug 20, 2010
 * in the com.qagwaai.starmalaccamax.shared.model.record package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.qagwaai.starmalaccamax.shared.model.Planet;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public class PlanetRecord extends ListGridRecord implements Planet {

    /**
	 * 
	 */
    public static final String ID = "id";
    /**
	 * 
	 */
    public static final String SOLARSYSTEM_ID = "solarSystemId";
    /**
	 * 
	 */
    public static final String NAME = "name";
    /**
	 * 
	 */
    public static final String ORBIT = "orbit";
    /**
	 * 
	 */
    public static final String SIZE = "size";
    /**
	 * 
	 */
    public static final String ATMOSPHERE = "atmosphere";
    /**
	 * 
	 */
    public static final String HYDROGRAPHICS = "hydrographics";
    /**
	 * 
	 */
    public static final String POPULATION = "population";
    /**
	 * 
	 */
    public static final String DOCK_QUALITY = "dockQuality";
    /**
	 * 
	 */
    public static final String GOVERNMENT = "government";
    /**
	 * 
	 */
    public static final String LAW_LEVEL = "lawLevel";

    /**
	 * 
	 */
    public static final String IS_GAS_GIANT = "isGasGiant";

    /**
	 * 
	 */
    public static final String TECH_LEVEL = "techLevel";

    /**
	 * 
	 */
    public static final String IS_MAIN_WORLD = "isMainWorld";

    /**
	 * 
	 */
    public static final String IS_SATELLITE = "isSatellite";

    /**
	 * 
	 */
    public static final String ORBITAL_DISTANCE = "orbitalDistance";

    /**
	 * 
	 */
    public static final String LOCATION_OBJ = "locationObj";

    /**
	 * 
	 */
    public static final String SATELLITE_PARENT_ID = "satelliteParentId";

    public static final String ORBIT_CENTER_X = "orbitCenterX";
    public static final String ORBIT_CENTER_Y = "orbitCenterY";
    public static final String ORBITAL_ECCENTRICITY = "orbitalEccentricity";
    public static final String ORBIT_ROTATION = "orbitRotation";

    public static final String ORBIT_X_RELATIVE = "orbitXRel";
    public static final String ORBIT_Y_RELATIVE = "orbitYRel";

    /**
	 * 
	 */
    public PlanetRecord() {

    }

    /**
     * 
     * @param planet
     *            the planet DTO
     */
    public PlanetRecord(final PlanetDTO planet) {
        setId(planet.getId());
        setSolarSystemId(planet.getSolarSystemId());
        setSize(planet.getSize());
        setAtmosphere(planet.getAtmosphere());
        setHydrographics(planet.getHydrographics());
        setPopulation(planet.getPopulation());
        setDockQuality(planet.getDockQuality());
        setGovernment(planet.getGovernment());
        setLawLevel(planet.getLawLevel());
        setTechLevel(planet.getTechLevel());
        setOrbit(planet.getOrbit());
        setGasGiant(planet.isGasGiant());
        setName(planet.getName());
        setMainWorld(planet.isMainWorld());
        setSatellite(planet.isSatellite());
        setOrbitalDistance(planet.getOrbitalDistance());
        setLocation(planet.getLocation());
        setSatelliteParentId(planet.getSatelliteParentId());

        setOrbitCenterX(planet.getOrbitCenterX());
        setOrbitCenterY(planet.getOrbitCenterY());
        setOrbitalEccentricity(planet.getOrbitalEccentricity());
        setOrbitRotation(planet.getOrbitRotation());

        setOrbitXRel(planet.getOrbitXRel());
        setOrbitYRel(planet.getOrbitYRel());
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final int getAtmosphere() {
        return getAttributeAsInt(PlanetRecord.ATMOSPHERE);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final int getDockQuality() {
        return getAttributeAsInt(PlanetRecord.DOCK_QUALITY);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final int getGovernment() {
        return getAttributeAsInt(PlanetRecord.GOVERNMENT);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Double getHydrographics() {
        return getAttributeAsDouble(PlanetRecord.HYDROGRAPHICS);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Long getId() {
        try {
            return LongConverter.fromJavaScript(this, PlanetRecord.ID);
        } catch (DataException e) {
            GWT.log("Identity field could not be converted to Long", e);
        }
        return -1L;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final int getLawLevel() {
        return getAttributeAsInt(PlanetRecord.LAW_LEVEL);
    }

    @Override
    public final LocationDTO getLocation() {
        return (LocationDTO) getAttributeAsObject(PlanetRecord.LOCATION_OBJ);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final String getName() {
        return getAttributeAsString(PlanetRecord.NAME);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final int getOrbit() {
        return getAttributeAsInt(PlanetRecord.ORBIT);
    }

    @Override
    public final Double getOrbitalDistance() {
        return getAttributeAsDouble(PlanetRecord.ORBITAL_DISTANCE);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Long getPopulation() {
        try {
            return LongConverter.fromJavaScript(this, PlanetRecord.POPULATION);
        } catch (DataException e) {
            GWT.log("Identity field could not be converted to Long", e);
        }
        return -1L;
    }

    @Override
    public final Long getSatelliteParentId() {
        try {
            return LongConverter.fromJavaScript(this, PlanetRecord.SATELLITE_PARENT_ID);
        } catch (DataException e) {
            GWT.log("Identity field could not be converted to Long", e);
        }
        return -1L;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final int getSize() {
        return getAttributeAsInt(PlanetRecord.SIZE);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Long getSolarSystemId() {
        try {
            return LongConverter.fromJavaScript(this, PlanetRecord.SOLARSYSTEM_ID);
        } catch (DataException e) {
            GWT.log("Identity field could not be converted to Long", e);
        }
        return -1L;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final int getTechLevel() {
        return getAttributeAsInt(PlanetRecord.TECH_LEVEL);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final boolean isGasGiant() {
        return getAttributeAsBoolean(PlanetRecord.IS_GAS_GIANT);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final boolean isMainWorld() {
        return getAttributeAsBoolean(PlanetRecord.IS_MAIN_WORLD);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final boolean isSatellite() {
        return getAttributeAsBoolean(PlanetRecord.IS_SATELLITE);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void setAtmosphere(final int atmosphere) {
        setAttribute(PlanetRecord.ATMOSPHERE, atmosphere);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void setDockQuality(final int dockQuality) {
        setAttribute(PlanetRecord.DOCK_QUALITY, dockQuality);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void setGasGiant(final boolean isGasGiant) {
        setAttribute(PlanetRecord.IS_GAS_GIANT, isGasGiant);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void setGovernment(final int government) {
        setAttribute(PlanetRecord.GOVERNMENT, government);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void setHydrographics(final Double hydrographics) {
        setAttribute(PlanetRecord.HYDROGRAPHICS, hydrographics);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void setId(final Long id) {
        setAttribute(PlanetRecord.ID, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void setLawLevel(final int lawLevel) {
        setAttribute(PlanetRecord.LAW_LEVEL, lawLevel);
    }

    @Override
    public final void setLocation(final LocationDTO location) {
        setAttribute(PlanetRecord.LOCATION_OBJ, location);

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void setMainWorld(final boolean isMainWorld) {
        setAttribute(PlanetRecord.IS_MAIN_WORLD, isMainWorld);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void setName(final String name) {
        setAttribute(PlanetRecord.NAME, name);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void setOrbit(final int orbit) {
        setAttribute(PlanetRecord.ORBIT, orbit);
    }

    @Override
    public final void setOrbitalDistance(final Double orbitalDistance) {
        setAttribute(PlanetRecord.ORBITAL_DISTANCE, orbitalDistance);

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void setPopulation(final Long population) {
        setAttribute(PlanetRecord.POPULATION, population);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void setSatellite(final boolean isSatellite) {
        setAttribute(PlanetRecord.IS_SATELLITE, isSatellite);
    }

    @Override
    public final void setSatelliteParentId(final Long planetId) {
        setAttribute(PlanetRecord.SATELLITE_PARENT_ID, planetId);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void setSize(final int size) {
        setAttribute(PlanetRecord.SIZE, size);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void setSolarSystemId(final Long solarSystemId) {
        setAttribute(PlanetRecord.SOLARSYSTEM_ID, solarSystemId);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void setTechLevel(final int techLevel) {
        setAttribute(PlanetRecord.TECH_LEVEL, techLevel);
    }

    /**
     * 
     * @return a solar system object from this record
     */
    public final PlanetDTO toPlanet() {
        PlanetDTO p = new PlanetDTO();
        p.setId(getId());
        p.setSolarSystemId(getSolarSystemId());
        p.setSize(getSize());
        p.setAtmosphere(getAtmosphere());
        p.setHydrographics(getHydrographics());
        p.setPopulation(getPopulation());
        p.setDockQuality(getDockQuality());
        p.setGovernment(getGovernment());
        p.setLawLevel(getLawLevel());
        p.setTechLevel(getTechLevel());
        p.setOrbit(getOrbit());
        p.setGasGiant(isGasGiant());
        p.setName(getName());
        p.setMainWorld(isMainWorld());
        p.setSatellite(isSatellite());
        p.setOrbitalDistance(getOrbitalDistance());
        p.setLocation(getLocation());
        p.setSatelliteParentId(getSatelliteParentId());

        p.setOrbitCenterX(getOrbitCenterX());
        p.setOrbitCenterY(getOrbitCenterY());
        p.setOrbitalEccentricity(getOrbitalEccentricity());
        p.setOrbitRotation(getOrbitRotation());

        p.setOrbitXRel(getOrbitXRel());
        p.setOrbitYRel(getOrbitYRel());
        return p;
    }

    @Override
    public int getOrbitCenterX() {
        return getAttributeAsInt(ORBIT_CENTER_X);
    }

    @Override
    public void setOrbitCenterX(int x) {
        setAttribute(ORBIT_CENTER_X, x);
    }

    @Override
    public int getOrbitCenterY() {
        return getAttributeAsInt(ORBIT_CENTER_Y);
    }

    @Override
    public void setOrbitCenterY(int y) {
        setAttribute(ORBIT_CENTER_Y, y);
    }

    @Override
    public Double getOrbitalEccentricity() {
        return getAttributeAsDouble(ORBITAL_ECCENTRICITY);
    }

    @Override
    public void setOrbitalEccentricity(Double eccentricity) {
        setAttribute(ORBITAL_ECCENTRICITY, eccentricity);
    }

    @Override
    public int getOrbitRotation() {
        return getAttributeAsInt(ORBIT_ROTATION);
    }

    @Override
    public void setOrbitRotation(int angle) {
        setAttribute(ORBIT_ROTATION, angle);
    }

    @Override
    public int getOrbitXRel() {
        return getAttributeAsInt(ORBIT_X_RELATIVE);
    }

    @Override
    public void setOrbitXRel(int xrel) {
        setAttribute(ORBIT_X_RELATIVE, xrel);
    }

    @Override
    public int getOrbitYRel() {
        return getAttributeAsInt(ORBIT_Y_RELATIVE);
    }

    @Override
    public void setOrbitYRel(int yrel) {
        setAttribute(ORBIT_Y_RELATIVE, yrel);
    }

}
