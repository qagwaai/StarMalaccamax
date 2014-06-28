/**
 * GameEventDTO.java
 * Created by pgirard at 10:56:35 AM on Nov 17, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.google.code.twig.annotation.Id;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.Entity;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
@Entity
public final class GameEventDTO implements GameEvent, IsSerializable, Serializable {
    /**
	 * 
	 */
    private Date startDateTime;
    /**
	 * 
	 */
    private Date endDateTime;
    /**
	 * 
	 */
    private String fullDescription;
    /**
	 * 
	 */
    @com.google.code.twig.annotation.Id
    @com.googlecode.objectify.annotation.Id
    private Long id;
    /**
	 * 
	 */
    private Long playerId;
    /**
	 * 
	 */
    private String shortDescription;

    /**
	 * 
	 */
    private boolean eventEnabled;

    /**
	 * 
	 */
    private String activityType;

    /**
	 * 
	 */
    private long activityTypeId;

    /**
	 * 
	 */
    private Date activityCompletionDate;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Date getEndDateTime() {
        return endDateTime;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getFullDescription() {
        return fullDescription;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getPlayerId() {
        return playerId;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Date getStartDateTime() {
        return startDateTime;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setEndDateTime(final Date eventEndDateTime) {
        this.endDateTime = eventEndDateTime;

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setFullDescription(final String description) {
        this.fullDescription = description;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setPlayerId(final Long playerId) {
        this.playerId = playerId;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setShortDescription(final String description) {
        this.shortDescription = description;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setStartDateTime(final Date eventDateTime) {
        this.startDateTime = eventDateTime;
    }

    /**
     * 
     * {@inheritDoc}
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEventEnabled() {
        return eventEnabled;
    }

    @Override
    public String toString() {
	return "GameEventDTO [startDateTime=" + startDateTime + ", endDateTime=" + endDateTime + ", fullDescription=" + fullDescription + ", id=" + id + ", playerId="
		+ playerId + ", shortDescription=" + shortDescription + ", eventEnabled=" + eventEnabled + ", activityType=" + activityType + ", activityTypeId="
		+ activityTypeId + ", activityCompletionDate=" + activityCompletionDate + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEventEnabled(final boolean enabled) {
        this.eventEnabled = enabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getActivityType() {
        return activityType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActivityType(final String type) {
        this.activityType = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getActivityTypeId() {
        return activityTypeId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActivityTypeId(final long id) {
        this.activityTypeId = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getActivityCompletionDate() {
        return activityCompletionDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActivityCompletionDate(final Date date) {
        this.activityCompletionDate = date;
    }

}
