/**
 * JumpGateDTO.java
 * Created by pgirard at 1:38:42 PM on Oct 10, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;

import com.google.code.twig.annotation.Embedded;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
@Entity
public final class JumpGateDTO implements JumpGate, IsSerializable, Serializable {
    /**
	 * 
	 */
    @com.google.code.twig.annotation.Id
    @com.googlecode.objectify.annotation.Id
    @Index
    private Long id;
    /**
	 * 
	 */
    @Index
    private Long solarSystemId1;
    /**
	 * 
	 */
    @Index
    private Long solarSystemId2;

    /**
	 * 
	 */
    private int failurePct;

    /**
	 * 
	 */
    @Embedded
    private LocationDTO location1;
    /**
	 * 
	 */
    @Embedded
    private LocationDTO location2;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocationDTO getLocation1() {
        return location1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocationDTO getLocation2() {
        return location2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getSolarSystem1Id() {
        return solarSystemId1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getSolarSystem2Id() {
        return solarSystemId2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLocation1(final LocationDTO location) {
        location1 = location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLocation2(final LocationDTO location) {
        location2 = location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSolarSystem1Id(final Long id) {
        solarSystemId1 = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSolarSystem2Id(final Long id) {
        solarSystemId2 = id;
    }



    @Override
	public String toString() {
		return "JumpGateDTO [id=" + id + ", solarSystemId1=" + solarSystemId1
				+ ", solarSystemId2=" + solarSystemId2 + ", failurePct="
				+ failurePct + ", location1=" + location1 + ", location2="
				+ location2 + "]";
	}

	/**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getFailurePct() {
        return failurePct;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setFailurePct(final int pct) {
        failurePct = pct;
    }
    
    public static String getFieldGetter(String fieldName) {
    	String methodName = null;
    	
    	if (fieldName.equals("id")) {
    		methodName = "getId";
    	} else if (fieldName.equals("solarSystemId1")) {
    		methodName = "getSolarSystem1Id";
    	} else if (fieldName.equals("solarSystemId2")) {
    		methodName = "getSolarSystem2Id";
    	}
    	return methodName;
    }

}
