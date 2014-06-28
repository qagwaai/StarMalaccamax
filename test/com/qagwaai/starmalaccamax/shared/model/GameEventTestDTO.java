/**
 * GameEventDTO.java
 * Created by pgirard at 10:56:35 AM on Nov 17, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.google.code.twig.annotation.Child;
import com.google.code.twig.annotation.Id;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.gameevent.GameActivity;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class GameEventTestDTO implements GameEventTest, IsSerializable, Serializable {
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
	@Id
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
	private boolean enabled;

	/**
	 * 
	 */
	private String type;

	/**
	 * 
	 */
	@Child
	private GameActivity activity;

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
	 * @return a debug string
	 */
	@Override
	public String toString() {
		return "GameEventDTO [startDateTime=" + startDateTime + ", endDateTime=" + endDateTime + ", fullDescription=" + fullDescription + ", id=" + id + ", playerId="
				+ playerId + ", shortDescription=" + shortDescription + ", activity=" + activity + "]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setActivity(final GameActivity activity) {
		this.activity = activity;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GameActivity getActivity() {
		return activity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEventEnabled() {
		return enabled;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEventEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getType() {
		return type;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void setType(final String type) {
		this.type = type;
	}

}
