/**
 * UserRecord.java
 * Created by pgirard at 10:29:55 AM on Aug 20, 2010
 * in the com.qagwaai.starmalaccamax.shared.model.record package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.client.core.constants.CoreConstants;
import com.qagwaai.starmalaccamax.shared.model.Closest;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class ClosestRecord extends ListGridRecord implements Closest {
    /**
	 * 
	 */
    private CoreConstants constants = GWT.create(CoreConstants.class);
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
    public static final String NUMBER_OF_JUMPS = "numberOfJumps";
    /**
	 * 
	 */
    public static final String TO_ID = "toSolarSystemID";

    /**
	 * 
	 */
    public ClosestRecord() {

    }

    /**
     * 
     * @param closest
     *            the closest DTO
     */
    public ClosestRecord(final ClosestDTO closest) {
        setId(closest.getId());
        setSolarSystemId(closest.getSolarSystemId());
        setNumberOfJumps(closest.getNumberOfJumps());
        setToSolarSystemId(closest.getToSolarSystemId());
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        try {
            return LongConverter.fromJavaScript(this, ClosestRecord.ID);
        } catch (DataException e) {
            GWT.log(constants.idLongConversionError(), e);
        }
        return -1L;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfJumps() {
        return this.getAttributeAsInt(ClosestRecord.NUMBER_OF_JUMPS);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getSolarSystemId() {
        try {
            return LongConverter.fromJavaScript(this, ClosestRecord.SOLARSYSTEM_ID);
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
    public Long getToSolarSystemId() {
        try {
            return LongConverter.fromJavaScript(this, ClosestRecord.TO_ID);
        } catch (DataException e) {
            GWT.log("to field could not be converted to Long", e);
        }
        return -1L;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setId(final Long id) {
        setAttribute(ClosestRecord.ID, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setNumberOfJumps(final int order) {
        setAttribute(ClosestRecord.NUMBER_OF_JUMPS, order);

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setSolarSystemId(final Long solarSystemId) {
        setAttribute(ClosestRecord.SOLARSYSTEM_ID, solarSystemId);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setToSolarSystemId(final Long toSolarSystemId) {
        setAttribute(ClosestRecord.TO_ID, toSolarSystemId);

    }

    /**
     * 
     * @return a solar system object from this record
     */
    public ClosestDTO toClosest() {
        ClosestDTO p = new ClosestDTO();
        p.setId(getId());
        p.setSolarSystemId(getSolarSystemId());
        p.setNumberOfJumps(getNumberOfJumps());
        p.setToSolarSystemId(getToSolarSystemId());

        return p;
    }

}
