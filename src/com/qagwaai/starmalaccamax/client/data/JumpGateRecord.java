/**
 * UserRecord.java
 * Created by pgirard at 10:29:55 AM on Aug 20, 2010
 * in the com.qagwaai.starmalaccamax.shared.model.record package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.shared.model.JumpGate;
import com.qagwaai.starmalaccamax.shared.model.JumpGateDTO;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class JumpGateRecord extends ListGridRecord implements JumpGate {

    /**
	 * 
	 */
    public static final String ID = "id";
    /**
	 * 
	 */
    public static final String SOLARSYSTEM_1_ID = "solarSystem1Id";
    /**
	 * 
	 */
    public static final String SOLARSYSTEM_2_ID = "solarSystem2Id";

    /**
	 * 
	 */
    public static final String LOCATION_1 = "location1";
    /**
	 * 
	 */
    public static final String LOCATION_2 = "location2";

    /**
	 * 
	 */
    public static final String FAILURE_PCT = "failurePct";

    /**
	 * 
	 */
    public JumpGateRecord() {

    }

    /**
     * 
     * @param jumpGate
     *            the jumpGate DTO
     */
    public JumpGateRecord(final JumpGateDTO jumpGate) {
        setId(jumpGate.getId());
        setSolarSystem1Id(jumpGate.getSolarSystem1Id());
        setSolarSystem2Id(jumpGate.getSolarSystem2Id());
        setLocation1(jumpGate.getLocation1());
        setLocation2(jumpGate.getLocation2());
        setFailurePct(jumpGate.getFailurePct());
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        GWT.log("JumpGateRecord.getId");
        try {
            Object objId = getAttributeAsObject(JumpGateRecord.ID);
            if (objId instanceof Long) {
                return (Long) objId;
            }
        } catch (Throwable t) {
            GWT.log("Identity Field exception", t);
        }
        GWT.log("Identity field could not be converted to Long", new Exception("Conversion Error"));

        return -1L;
    }

    @Override
    public LocationDTO getLocation1() {
        GWT.log("JumpGateRecord.getLocation1");
        try {
            return (LocationDTO) getAttributeAsObject(JumpGateRecord.LOCATION_1);
        } catch (Throwable t) {
            GWT.log("location 1 exception", t);
        }
        return null;
    }

    @Override
    public LocationDTO getLocation2() {
        GWT.log("JumpGateRecord.getLocation2");
        try {
            return (LocationDTO) getAttributeAsObject(JumpGateRecord.LOCATION_2);
        } catch (Throwable t) {
            GWT.log("location 2 Field exception", t);
        }
        return null;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getSolarSystem1Id() {
        GWT.log("JumpGateRecord.getSolarSystem1Id");
        try {
            Object objId = getAttributeAsObject(JumpGateRecord.SOLARSYSTEM_1_ID);
            if (objId instanceof Long) {
                return (Long) objId;
            }
        } catch (Throwable t) {
            GWT.log("solar system id 1 Field exception", t);
        }
        GWT.log("SolarSystem ID field could not be converted to Long", new Exception("Conversion Error"));

        return -1L;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getSolarSystem2Id() {
        GWT.log("JumpGateRecord.getSolarSystem2Id");
        try {
            Object objId = getAttributeAsObject(JumpGateRecord.SOLARSYSTEM_2_ID);
            if (objId instanceof Long) {
                return (Long) objId;
            }
        } catch (Throwable t) {
            GWT.log("solar system id 2 Field exception", t);
        }
        GWT.log("SolarSystem ID field could not be converted to Long", new Exception("Conversion Error"));

        return -1L;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setId(final Long id) {
        GWT.log("JumpGateRecord.setId");
        setAttribute(JumpGateRecord.ID, id);
    }

    @Override
    public void setLocation1(final LocationDTO location) {
        GWT.log("JumpGateRecord.setLocation1");
        setAttribute(JumpGateRecord.LOCATION_1, location);
    }

    @Override
    public void setLocation2(final LocationDTO location) {
        GWT.log("JumpGateRecord.setLocation2");
        setAttribute(JumpGateRecord.LOCATION_2, location);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setSolarSystem1Id(final Long solarSystemId) {
        GWT.log("JumpGateRecord.setSolarSystem1Id");
        setAttribute(JumpGateRecord.SOLARSYSTEM_1_ID, solarSystemId);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setSolarSystem2Id(final Long solarSystemId) {
        GWT.log("JumpGateRecord.setSolarSystem2Id");
        setAttribute(JumpGateRecord.SOLARSYSTEM_2_ID, solarSystemId);
    }

    /**
     * 
     * @return a solar system object from this record
     */
    public JumpGateDTO toJumpGate() {
        GWT.log("JumpGateRecord.toJumpGate");
        JumpGateDTO p = new JumpGateDTO();
        p.setId(getId());
        p.setSolarSystem1Id(getSolarSystem1Id());
        p.setSolarSystem2Id(getSolarSystem2Id());
        p.setLocation1(getLocation1());
        p.setLocation2(getLocation2());
        p.setFailurePct(getFailurePct());
        return p;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getFailurePct() {
        GWT.log("JumpGateRecord.getFailurePct");
        return getAttributeAsInt(JumpGateRecord.FAILURE_PCT);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setFailurePct(final int pct) {
        GWT.log("JumpGateRecord.setFailurePct");
        setAttribute(JumpGateRecord.FAILURE_PCT, pct);
    }

}
