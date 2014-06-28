/**
 * UserRecord.java
 * Created by pgirard at 10:29:55 AM on Aug 20, 2010
 * in the com.qagwaai.starmalaccamax.shared.model.record package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.shared.model.Star;
import com.qagwaai.starmalaccamax.shared.model.StarDTO;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class StarRecord extends ListGridRecord implements Star {

    /**
	 * 
	 */
    public static final String ID = "id";
    /**
	 * 
	 */
    public static final String SOLARSYSTEM_ID = "starId";
    /**
	 * 
	 */
    public static final String SPECTRAL_TYPE = "spectralType";
    /**
	 * 
	 */
    public static final String SPECTRAL_TYPE_DEC = "spectralTypeDecimal";
    /**
	 * 
	 */
    public static final String SIZE = "size";
    /**
	 * 
	 */
    public static final String COMPONENT_ID = "componentId";
    /**
	 * 
	 */
    public static final String ORBIT = "orbit";
    /**
	 * 
	 */
    public static final String IS_COMPANION = "isCompanion";

    /**
	 * 
	 */
    public StarRecord() {

    }

    /**
     * 
     * @param star
     *            the star DTO
     */
    public StarRecord(final StarDTO star) {
        setId(star.getId());
        setSolarSystemId(star.getSolarSystemId());
        setSpectralType(star.getSpectralType());
        setSpectralTypeDec(star.getSpectralTypeDec());
        setSize(star.getSize());
        setComponentIdentifier(star.getComponentIdentifier());
        setOrbit(star.getOrbit());
        setCompanion(star.isCompanion());
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getComponentIdentifier() {
        return getAttributeAsString(StarRecord.COMPONENT_ID);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        try {
            return LongConverter.fromJavaScript(this, StarRecord.ID);
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
    public int getOrbit() {
        return getAttributeAsInt(StarRecord.ORBIT);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getSize() {
        return getAttributeAsString(StarRecord.SIZE);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getSolarSystemId() {
        try {
            return LongConverter.fromJavaScript(this, StarRecord.SOLARSYSTEM_ID);
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
    public String getSpectralType() {
        return getAttributeAsString(StarRecord.SPECTRAL_TYPE);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public double getSpectralTypeDec() {
        return getAttributeAsDouble(StarRecord.SPECTRAL_TYPE_DEC);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean isCompanion() {
        return getAttributeAsBoolean(StarRecord.IS_COMPANION);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setCompanion(final boolean isCompanion) {
        setAttribute(StarRecord.IS_COMPANION, isCompanion);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setComponentIdentifier(final String componentId) {
        setAttribute(StarRecord.COMPONENT_ID, componentId);
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
    public void setOrbit(final int orbit) {
        setAttribute(StarRecord.ORBIT, orbit);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setSize(final String size) {
        setAttribute(StarRecord.SIZE, size);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setSolarSystemId(final Long solarSystemId) {
        setAttribute(StarRecord.SOLARSYSTEM_ID, solarSystemId);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setSpectralType(final String spectralType) {
        setAttribute(StarRecord.SPECTRAL_TYPE, spectralType);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setSpectralTypeDec(final double spectralTypeDec) {
        setAttribute(StarRecord.SPECTRAL_TYPE_DEC, spectralTypeDec);
    }

    /**
     * 
     * @return a solar system object from this record
     */
    public StarDTO toStar() {
        StarDTO ss = new StarDTO();
        ss.setId(getId());
        ss.setSolarSystemId(getSolarSystemId());
        ss.setSpectralType(getSpectralType());
        ss.setSpectralTypeDec(getSpectralTypeDec());
        ss.setSize(getSize());
        ss.setComponentIdentifier(getComponentIdentifier());
        ss.setCompanion(isCompanion());
        return ss;
    }
}
