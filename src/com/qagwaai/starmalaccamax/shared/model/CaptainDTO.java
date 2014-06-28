/**
 * Captain.java
 * Created by pgirard at 9:56:04 PM on Jul 26, 2010
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
public final class CaptainDTO implements IsSerializable, Serializable, Captain {

    /**
	 * 
	 */
    @com.google.code.twig.annotation.Id
    @com.googlecode.objectify.annotation.Id
    private Long id;

    /**
	 * 
	 */
    @Index
    private Long ownerId;
    /**
	 * 
	 */
    @Index
    private String name;

    /**
	 * 
	 */
    private String color;
    private String gender;
    private String race;

    /**
	 * 
	 */
    @Embedded
    private CaptainSkills skills = new CaptainSkills();

    /**
	 * 
	 */
    @Embedded
    private CaptainAttributes attributes = new CaptainAttributes();

    /**
	 * 
	 */
    public CaptainDTO() {

    }

    /**
     * {@inheritDoc}
     */
    public CaptainAttributes getCaptainAttributes() {
        return attributes;
    }

    /**
     * {@inheritDoc}
     */
    public String getColor() {
        return color;
    }

    /**
     * {@inheritDoc}
     */
    public Long getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return name;
    }

    /**
     * @return the ownerId
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * {@inheritDoc}
     */
    public CaptainSkills getSkills() {
        return skills;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setCaptainAttributes(final CaptainAttributes attributes) {
        this.attributes = attributes;
    }

    /**
     * {@inheritDoc}
     */
    public void setColor(final String color) {
        this.color = color;
    }

    /**
     * {@inheritDoc}
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param ownerId
     *            the ownerId to set
     */
    public void setOwnerId(final Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setSkills(final CaptainSkills skills) {
        this.skills = skills;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CaptainDTO [attributes=");
        builder.append(attributes != null ? attributes : null);
        builder.append(", color=");
        builder.append(color);
        builder.append(", id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", skills=");
        builder.append(skills != null ? skills : null);
        builder.append("]");
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGender() {
        return gender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRace() {
        return race;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRace(String race) {
        this.race = race;
    }

}
