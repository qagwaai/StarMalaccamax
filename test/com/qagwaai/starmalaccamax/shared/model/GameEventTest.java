/**
 * GameEvent.java
 * Created by pgirard at 10:56:04 AM on Nov 17, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.util.Date;

import com.qagwaai.starmalaccamax.shared.model.gameevent.GameActivity;

/**
 * @author pgirard
 * 
 */
public interface GameEventTest extends Model {

	/**
	 * 
	 * @return the time at which the event "ends"
	 */
	Date getEndDateTime();

	/**
	 * 
	 * @return the full description of the event
	 */
	String getFullDescription();

	/**
	 * 
	 * @return the event unique identifier
	 */
	Long getId();

	/**
	 * 
	 * @return if the event is associated with a specific player
	 */
	Long getPlayerId();

	/**
	 * 
	 * @return a short description of the event
	 */
	String getShortDescription();

	/**
	 * 
	 * @return the time at which the event "fires"
	 */
	Date getStartDateTime();

	/**
	 * 
	 * @param eventEndDateTime
	 *            the time at which the event "end"
	 */
	void setEndDateTime(Date eventEndDateTime);

	/**
	 * 
	 * @param description
	 *            the full description of the event
	 */
	void setFullDescription(String description);

	/**
	 * 
	 * @param id
	 *            the event unique identifier
	 */
	void setId(Long id);

	/**
	 * 
	 * @param playerId
	 *            if the event is associated with a specific player
	 */
	void setPlayerId(Long playerId);

	/**
	 * 
	 * @param description
	 *            a short description of the event
	 */
	void setShortDescription(String description);

	/**
	 * 
	 * @param eventStartDateTime
	 *            the time at which the event "fires"
	 */
	void setStartDateTime(Date eventStartDateTime);

	/**
	 * 
	 * @param activity the activity
	 */
	void setActivity(GameActivity activity);

	/**
	 * 
	 * @return the activity
	 */
	GameActivity getActivity();

	/**
	 * 
	 * @return true if the event is enabled
	 */
	boolean isEventEnabled();

	/**
	 * 
	 * @param enabled set if the event is enabled
	 */
	void setEventEnabled(boolean enabled);

	/**
	 * 
	 * @return the event type
	 */
	String getType();

	/**
	 * 
	 * @param type the event type
	 */
	void setType(String type);
}
