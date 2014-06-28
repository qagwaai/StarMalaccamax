/**
 * GetAllUsersResponse.java
 * Created by pgirard at 2:07:29 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;

/**
 * @author pgirard
 * 
 */
public final class GetAllGameEventsResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<GameEventDTO> gameEvents;
    /**
	 * 
	 */
    private int totalGameEvents;

    /**
     * @return the users
     */
    public ArrayList<GameEventDTO> getGameEvents() {
        return gameEvents;
    }

    /**
     * @return the totalGameEvents
     */
    public int getTotalGameEvents() {
        return totalGameEvents;
    }

    /**
     * @param gameEvents
     *            the users to set
     */
    public void setGameEvents(final ArrayList<GameEventDTO> gameEvents) {
        this.gameEvents = gameEvents;
    }

    /**
     * @param totalGameEvents
     *            the totalGameEvents to set
     */
    public void setTotalGameEvents(final int totalGameEvents) {
        this.totalGameEvents = totalGameEvents;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetAllGameEventsResponse [gameEvents=" + gameEvents + ", totalGameEvents=" + totalGameEvents + "]";
    }

}
