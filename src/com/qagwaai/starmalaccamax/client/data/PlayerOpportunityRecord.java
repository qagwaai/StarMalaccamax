/**
 * UserRecord.java
 * Created by pgirard at 10:29:55 AM on Aug 20, 2010
 * in the com.qagwaai.starmalaccamax.shared.model.record package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class PlayerOpportunityRecord extends ListGridRecord {

    /**
	 * 
	 */
    public static final String ID = "planetid";
    /**
	 * 
	 */
    public static final String SOLARSYSTEM_ID = "solarSystemId";
    /**
	 * 
	 */
    public static final String ORDER = "order";
    /**
	 * 
	 */
    public static final String TO_ID = "toSolarSystemID";

    /**
	 * 
	 */
    public PlayerOpportunityRecord() {

    }

    /**
     * 
     * @param closest
     *            the closest DTO
     */
    public PlayerOpportunityRecord(final ClosestDTO closest) {
        setId(closest.getId());
        setSolarSystemId(closest.getSolarSystemId());
        setOrder(closest.getNumberOfJumps());
        setToSolarSystemId(closest.getToSolarSystemId());
    }

    /**
     * 
     * {@inheritDoc}
     */
    public Long getId() {
        try {
            return LongConverter.fromJavaScript(this, PlayerOpportunityRecord.ID);
        } catch (DataException e) {
            GWT.log("Identity field could not be converted to Long", e);
        }
        return -1L;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public int getOrder() {
        return this.getAttributeAsInt(PlayerOpportunityRecord.ORDER);
    }

    /**
     * 
     * {@inheritDoc}
     */
    public Long getSolarSystemId() {
        try {
            return LongConverter.fromJavaScript(this, PlayerOpportunityRecord.SOLARSYSTEM_ID);
        } catch (DataException e) {
            GWT.log("Identity field could not be converted to Long", e);
        }
        return -1L;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public Long getToSolarSystemId() {
        try {
            return LongConverter.fromJavaScript(this, PlayerOpportunityRecord.TO_ID);
        } catch (DataException e) {
            GWT.log("to field could not be converted to Long", e);
        }
        return -1L;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void setId(final Long id) {
        setAttribute(PlayerOpportunityRecord.ID, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void setOrder(final int order) {
        setAttribute(PlayerOpportunityRecord.ORDER, order);

    }

    /**
     * 
     * {@inheritDoc}
     */
    public void setSolarSystemId(final Long solarSystemId) {
        setAttribute(PlayerOpportunityRecord.SOLARSYSTEM_ID, solarSystemId);
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void setToSolarSystemId(final Long toSolarSystemId) {
        setAttribute(PlayerOpportunityRecord.TO_ID, toSolarSystemId);

    }

    /**
     * 
     * @return a solar system object from this record
     */
    public ClosestDTO toClosest() {
        ClosestDTO p = new ClosestDTO();
        p.setId(getId());
        p.setSolarSystemId(getSolarSystemId());
        p.setNumberOfJumps(getOrder());
        p.setToSolarSystemId(getToSolarSystemId());

        return p;
    }

}
