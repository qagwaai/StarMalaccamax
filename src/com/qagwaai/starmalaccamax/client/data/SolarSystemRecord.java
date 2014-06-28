/**
 * UserRecord.java
 * Created by pgirard at 10:29:55 AM on Aug 20, 2010
 * in the com.qagwaai.starmalaccamax.shared.model.record package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.shared.model.SolarSystem;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class SolarSystemRecord extends ListGridRecord implements SolarSystem {

    /**
	 * 
	 */
    public static final String ID = "id";
    /**
	 * 
	 */
    public static final String NAME = "name";
    /**
	 * 
	 */
    public static final String HIP = "hip";
    /**
	 * 
	 */
    public static final String ALPHA = "alpha";
    /**
	 * 
	 */
    public static final String DELTA = "delta";
    /**
	 * 
	 */
    public static final String PARALLAX = "parallax";
    /**
	 * 
	 */
    public static final String X = "x";
    /**
	 * 
	 */
    public static final String Y = "y";
    /**
	 * 
	 */
    public static final String Z = "z";
    /**
	 * 
	 */
    public static final String NUMBER_OF_COMPONENTS = "numberOfComponents";

    /**
	 * 
	 */
    public static final String MAXIMUM_ORBITS = "maximumOrbits";

    /**
	 * 
	 */
    public static final String HABITABLE_ORBIT = "habitableOrbit";

    /**
	 * 
	 */
    public static final String GAS_GIANTS = "gasGiants";

    /**
	 * 
	 */
    public static final String PLANETOID_BELTS = "planetoidBelts";

    /**
	 * 
	 */
    public SolarSystemRecord() {

    }

    /**
     * 
     * @param solarSystem
     *            the solarSystem DTO
     */
    public SolarSystemRecord(final SolarSystemDTO solarSystem) {
        setAlpha(solarSystem.getAlpha());
        setDelta(solarSystem.getDelta());
        setHIP(solarSystem.getHIP());
        setId(solarSystem.getId());
        setName(solarSystem.getName());
        setNumberOfComponents(solarSystem.getNumberOfComponents());
        setParallax(solarSystem.getParallax());
        setX(solarSystem.getX());
        setY(solarSystem.getY());
        setZ(solarSystem.getZ());
        setMaximumOrbits(solarSystem.getMaximumOrbits());
        setHabitableOrbit(solarSystem.getHabitableOrbit());
        setNumberOfGasGiants(solarSystem.getNumberOfGasGiants());
        setNumberOfPlanetoidBelts(solarSystem.getNumberOfPlanetoidBelts());
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public double getAlpha() {
        return getAttributeAsDouble(ALPHA);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public double getDelta() {
        return getAttributeAsDouble(DELTA);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getHabitableOrbit() {
        return getAttributeAsInt(HABITABLE_ORBIT);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getHIP() {
        return getAttributeAsInt(HIP);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        try {
            return LongConverter.fromJavaScript(this, SolarSystemRecord.ID);
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
    public int getMaximumOrbits() {
        return getAttributeAsInt(MAXIMUM_ORBITS);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return getAttributeAsString(NAME);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfComponents() {
        return getAttributeAsInt(NUMBER_OF_COMPONENTS);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfGasGiants() {
        return getAttributeAsInt(GAS_GIANTS);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfPlanetoidBelts() {
        return getAttributeAsInt(PLANETOID_BELTS);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public double getParallax() {
        return getAttributeAsDouble(PARALLAX);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public double getX() {
        return getAttributeAsDouble(X);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public double getY() {
        return getAttributeAsDouble(Y);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public double getZ() {
        return getAttributeAsDouble(Z);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setAlpha(final double alpha) {
        setAttribute(ALPHA, alpha);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setDelta(final double delta) {
        setAttribute(DELTA, delta);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setHabitableOrbit(final int habitableOrbit) {
        setAttribute(HABITABLE_ORBIT, habitableOrbit);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setHIP(final int hip) {
        setAttribute(HIP, hip);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setId(final Long id) {
        setAttribute(ID, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setMaximumOrbits(final int maximumOrbits) {
        setAttribute(MAXIMUM_ORBITS, maximumOrbits);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setName(final String name) {
        setAttribute(NAME, name);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setNumberOfComponents(final int numberOfComponents) {
        setAttribute(NUMBER_OF_COMPONENTS, numberOfComponents);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setNumberOfGasGiants(final int gasGiants) {
        setAttribute(GAS_GIANTS, gasGiants);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setNumberOfPlanetoidBelts(final int planetoidBelts) {
        setAttribute(PLANETOID_BELTS, planetoidBelts);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setParallax(final double parallax) {
        setAttribute(PARALLAX, parallax);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setX(final double x) {
        setAttribute(X, x);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setY(final double y) {
        setAttribute(Y, y);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setZ(final double z) {
        setAttribute(Z, z);
    }

    /**
     * 
     * @return a solar system object from this record
     */
    public SolarSystemDTO toSolarSystem() {
        SolarSystemDTO ss = new SolarSystemDTO();
        ss.setAlpha(getAlpha());
        ss.setDelta(getDelta());
        ss.setHIP(getHIP());
        ss.setId(getId());
        ss.setName(getName());
        ss.setNumberOfComponents(getNumberOfComponents());
        ss.setParallax(getParallax());
        ss.setX(getX());
        ss.setY(getY());
        ss.setZ(getZ());
        ss.setMaximumOrbits(getMaximumOrbits());
        ss.setHabitableOrbit(getHabitableOrbit());
        ss.setNumberOfGasGiants(getNumberOfGasGiants());
        ss.setNumberOfPlanetoidBelts(getNumberOfPlanetoidBelts());
        return ss;
    }
}
