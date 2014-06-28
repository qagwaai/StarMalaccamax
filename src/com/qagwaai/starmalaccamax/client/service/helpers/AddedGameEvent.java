/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import com.qagwaai.starmalaccamax.client.service.action.AddGameEventResponse;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;

/**
 * @author pgirard
 * 
 */
public abstract class AddedGameEvent extends BaseAsyncCallback<AddGameEventResponse> {
    /**
     * notify that we got a new GameEvent
     * 
     * @param gameEvent
     *            the GameEvent found by the command or null
     */
    public abstract void got(final GameEventDTO gameEvent);

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onFailure(final Throwable caught) {
        super.onFailure(caught);
        System.out.println("Failed to add gameEvent");
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onSuccess(final AddGameEventResponse result) {
        super.onSuccess(result);
        got(result.getGameEvent());

    }

}
