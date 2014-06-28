/**
 * UserRecord.java
 * Created by pgirard at 10:29:55 AM on Aug 20, 2010
 * in the com.qagwaai.starmalaccamax.shared.model.record package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.client.core.constants.CoreConstants;
import com.qagwaai.starmalaccamax.shared.model.Captain;
import com.qagwaai.starmalaccamax.shared.model.CaptainAttributes;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.CaptainSkills;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class CaptainRecord extends ListGridRecord implements Captain {
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
    public static final String NAME = "name";
    /**
	 * 
	 */
    public static final String COLOR = "color";

    /**
	 * 
	 */
    public static final String SKILLS = "skills";
    /**
	 * 
	 */
    public static final String ATTRIBUTES = "attributes";

    /**
	 * 
	 */
    public static final String OWNER_ID = "ownerId";

    public static final String GENDER = "gender";

    public static final String RACE = "race";

    /**
	 * 
	 */
    public CaptainRecord() {

    }

    /**
     * 
     * @param captain
     *            the captain DTO
     */
    public CaptainRecord(final CaptainDTO captain) {
        setId(captain.getId());
        setName(captain.getName());
        setColor(captain.getColor());
        setSkills(captain.getSkills());
        setCaptainAttributes(captain.getCaptainAttributes());
        setOwnerId(captain.getOwnerId());
        setGender(captain.getGender());
        setRace(captain.getRace());
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CaptainAttributes getCaptainAttributes() {
        return (CaptainAttributes) getAttributeAsObject(CaptainRecord.ATTRIBUTES);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getColor() {
        return getAttributeAsString(CaptainRecord.COLOR);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        try {
            return LongConverter.fromJavaScript(this, CaptainRecord.ID);
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
    public String getName() {
        return getAttributeAsString(CaptainRecord.NAME);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getOwnerId() {
        try {
            return LongConverter.fromJavaScript(this, CaptainRecord.OWNER_ID);
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
    public CaptainSkills getSkills() {
        return (CaptainSkills) getAttributeAsObject(CaptainRecord.SKILLS);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setCaptainAttributes(final CaptainAttributes attributes) {
        setAttribute(CaptainRecord.ATTRIBUTES, attributes);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setColor(final String color) {
        setAttribute(CaptainRecord.COLOR, color);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setId(final Long id) {
        setAttribute(CaptainRecord.ID, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setName(final String name) {
        setAttribute(CaptainRecord.NAME, name);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setOwnerId(final Long id) {
        setAttribute(CaptainRecord.OWNER_ID, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setSkills(final CaptainSkills skills) {
        setAttribute(CaptainRecord.SKILLS, skills);
    }

    /**
     * 
     * @return a solar system object from this record
     */
    public CaptainDTO toCaptain() {
        CaptainDTO p = new CaptainDTO();
        p.setId(getId());
        p.setName(getName());
        p.setColor(getColor());
        p.setSkills(getSkills());
        p.setCaptainAttributes(getCaptainAttributes());
        p.setOwnerId(getOwnerId());
        p.setGender(getGender());
        p.setRace(getRace());
        return p;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGender() {
        return getAttributeAsString(CaptainRecord.GENDER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGender(String gender) {
        setAttribute(CaptainRecord.GENDER, gender);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRace() {
        return getAttributeAsString(CaptainRecord.RACE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRace(String race) {
        setAttribute(CaptainRecord.RACE, race);
    }

}
