/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;

/**
 * @author pgirard
 * 
 */
public final class RemoveGameEvent implements IsSerializable, Action<RemoveGameEventResponse> {

    /**
	 * 
	 */
    private GameEventDTO gameEvent;

    /**
	 * 
	 */
    public RemoveGameEvent() {

    }

    /**
     * @param gameEvent
     *            the GameEvent to remove
     */
    public RemoveGameEvent(final GameEventDTO gameEvent) {
        this.gameEvent = gameEvent;
    }

    /**
     * @return the gameEvent
     */
    public GameEventDTO getGameEvent() {
        return gameEvent;
    }

    /**
     * @param gameEvent
     *            the gameEvent to set
     */
    public void setGameEvent(final GameEventDTO gameEvent) {
        this.gameEvent = gameEvent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RemoveGameEvent [gameEvent=" + gameEvent + "]";
    }

}
