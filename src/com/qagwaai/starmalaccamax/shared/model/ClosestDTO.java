/**
 * ClosestDTO.java
 * Created by pgirard at 11:44:11 AM on Dec 3, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.Entity;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
@Entity
public final class ClosestDTO implements Closest, IsSerializable, Serializable {
    /**
	 * 
	 */
    @com.google.code.twig.annotation.Id
    @com.googlecode.objectify.annotation.Id
    @com.googlecode.objectify.annotation.Index
    private Long id;

    /**
	 * 
	 */
    @com.googlecode.objectify.annotation.Index
    private Long originId;
    /**
	 * 
	 */
    @com.googlecode.objectify.annotation.Index
    private int numJumps;

    /**
	 * 
	 */
    @com.googlecode.objectify.annotation.Index
    private Long toId;

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
    public int getNumberOfJumps() {
        return numJumps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getSolarSystemId() {
        return originId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getToSolarSystemId() {
        return toId;
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
    public void setNumberOfJumps(final int order) {
        this.numJumps = order;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSolarSystemId(final Long solarSystemId) {
        this.originId = solarSystemId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setToSolarSystemId(final Long toSolarSystemId) {
        this.toId = toSolarSystemId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ClosestDTO [id=");
        builder.append(id);
        builder.append(", numJumps=");
        builder.append(numJumps);
        builder.append(", originId=");
        builder.append(originId);
        builder.append(", toId=");
        builder.append(toId);
        builder.append("]");
        return builder.toString();
    }

    public static String getFieldGetter(String fieldName) {
    	String methodName = null;
    	
    	if (fieldName.equals("id")) {
    		methodName = "getId";
    	} else if (fieldName.equals("originId")) {
    		methodName = "getSolarSystemId";
    	} else if (fieldName.equals("toId")) {
    		methodName = "getToSolarSystemId";
    	}
    	
    	return methodName;
    }

}
