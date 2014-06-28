/**
 * UserRecord.java
 * Created by pgirard at 10:29:55 AM on Aug 20, 2010
 * in the com.qagwaai.starmalaccamax.shared.model.record package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.shared.model.GameEvent;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class GameEventRecord extends ListGridRecord implements GameEvent {

    /**
	 *
	 */
    public static final String ID = "id";
    /**
	 * 
	 */
    public static final String PLAYER_ID = "playerId";
    /**
	 * 
	 */
    public static final String STARTDATETIME = "startDateTime";
    /**
	 * 
	 */
    public static final String ENDDATETIME = "endDateTime";
    /**
	 * 
	 */
    public static final String SHORT_DESCRIPTION = "shortDescription";
    /**
	 * 
	 */
    public static final String FULL_DESCRIPTION = "fullDescription";
    /**
	 * 
	 */
    public static final String ACTION_CLASS = "actionClass";

    /**
	 * 
	 */
    public static final String EVENT_ENABLED = "eventEnabled";

    /**
	 * 
	 */
    public static final String ACTIVITY_TYPE = "activityType";

    /**
	 * 
	 */
    public static final String ACTIVITY_TYPE_ID = "activityTypeId";

    /**
	 * 
	 */
    public static final String ACTIVITY_COMPLETION_DATE = "activityCompletionDate";

    /**
	 * 
	 */
    public GameEventRecord() {

    }

    /**
     * 
     * @param gameEvent
     *            the gameEvent DTO
     */
    public GameEventRecord(final GameEventDTO gameEvent) {
        setId(gameEvent.getId());
        setPlayerId(gameEvent.getPlayerId());
        setStartDateTime(gameEvent.getStartDateTime());
        setEndDateTime(gameEvent.getEndDateTime());
        setShortDescription(gameEvent.getShortDescription());
        setFullDescription(gameEvent.getFullDescription());
        // setActivityClass(gameEvent.getActivityClass());
        setEventEnabled(gameEvent.isEventEnabled());
        setActivityType(gameEvent.getActivityType());
        setActivityTypeId(gameEvent.getActivityTypeId());
        setActivityCompletionDate(gameEvent.getActivityCompletionDate());
    }

    /**
     * 
     * {@inheritDoc}
     */
    /*
     * @Override public Text getActivityClass() { return (Text)
     * getAttributeAsObject(GameEventRecord.ACTION_CLASS); }
     */

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Date getEndDateTime() {
        return getAttributeAsDate(GameEventRecord.ENDDATETIME);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getFullDescription() {
        return getAttributeAsString(GameEventRecord.FULL_DESCRIPTION);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        try {
            return LongConverter.fromJavaScript(this, GameEventRecord.ID);
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
    public Long getPlayerId() {
        try {
            return LongConverter.fromJavaScript(this, GameEventRecord.PLAYER_ID);
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
    public String getShortDescription() {
        return getAttributeAsString(GameEventRecord.SHORT_DESCRIPTION);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Date getStartDateTime() {
        return getAttributeAsDate(GameEventRecord.STARTDATETIME);
    }

    /**
     * 
     * {@inheritDoc}
     */
    /*
     * @Override public void setActivityClass(final Text actionClass) {
     * setAttribute(GameEventRecord.ACTION_CLASS, actionClass); }
     */

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setEndDateTime(final Date eventEndDateTime) {
        setAttribute(GameEventRecord.ENDDATETIME, eventEndDateTime);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setFullDescription(final String description) {
        setAttribute(GameEventRecord.FULL_DESCRIPTION, description);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setId(final Long id) {
        setAttribute(GameEventRecord.ID, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setPlayerId(final Long playerId) {
        setAttribute(GameEventRecord.PLAYER_ID, playerId);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setShortDescription(final String description) {
        setAttribute(GameEventRecord.SHORT_DESCRIPTION, description);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setStartDateTime(final Date eventDateTime) {
        setAttribute(GameEventRecord.STARTDATETIME, eventDateTime);
    }

    /**
     * 
     * @return a solar system object from this record
     */
    public GameEventDTO toGameEvent() {
        GameEventDTO p = new GameEventDTO();
        p.setId(getId());
        p.setPlayerId(getPlayerId());
        p.setStartDateTime(getStartDateTime());
        p.setEndDateTime(getEndDateTime());
        p.setShortDescription(getShortDescription());
        p.setFullDescription(getFullDescription());
        // p.setActivityClass(getActivityClass());
        p.setEventEnabled(isEventEnabled());
        p.setActivityType(getActivityType());
        p.setActivityTypeId(getActivityTypeId());
        p.setActivityCompletionDate(getActivityCompletionDate());
        return p;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEventEnabled() {
        return getAttributeAsBoolean(GameEventRecord.EVENT_ENABLED);
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void setEventEnabled(final boolean enabled) {
        setAttribute(GameEventRecord.EVENT_ENABLED, enabled);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getActivityType() {
        return getAttributeAsString(GameEventRecord.ACTIVITY_TYPE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActivityType(final String type) {
        setAttribute(GameEventRecord.ACTIVITY_TYPE, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getActivityTypeId() {
        return getAttributeAsInt(GameEventRecord.ACTIVITY_TYPE_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActivityTypeId(final long id) {
        setAttribute(GameEventRecord.ACTIVITY_TYPE_ID, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getActivityCompletionDate() {
        return getAttributeAsDate(GameEventRecord.ACTIVITY_COMPLETION_DATE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActivityCompletionDate(final Date date) {
        setAttribute(GameEventRecord.ACTIVITY_COMPLETION_DATE, date);
    }

}
