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
    private Long id;
    /**
	 * 
	 */
    private Long systemId1;
    /**
	 * 
	 */
    private Long systemId2;

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
        return systemId1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getSolarSystem2Id() {
        return systemId2;
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
        this.location1 = location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLocation2(final LocationDTO location) {
        this.location2 = location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSolarSystem1Id(final Long id) {
        this.systemId1 = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSolarSystem2Id(final Long id) {
        this.systemId2 = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("JumpGateDTO [id=");
        builder.append(id);
        builder.append(", location1=");
        builder.append(location1);
        builder.append(", location2=");
        builder.append(location2);
        builder.append(", systemId1=");
        builder.append(systemId1);
        builder.append(", systemId2=");
        builder.append(systemId2);
        builder.append("]");
        return builder.toString();
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
        this.failurePct = pct;
    }

}
