/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import com.qagwaai.starmalaccamax.client.service.action.RemoveGameEventResponse;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;

/**
 * @author pgirard
 * 
 */
public abstract class RemovedGameEvent extends BaseAsyncCallback<RemoveGameEventResponse> {
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onFailure(final Throwable caught) {
        super.onFailure(caught);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onSuccess(final RemoveGameEventResponse result) {
        super.onSuccess(result);
        removed(result.getGameEvent());

    }

    /**
     * notify that we got a new gameEvent
     * 
     * @param gameEvent
     *            the gameEvent found by the command or null
     */
    public abstract void removed(final GameEventDTO gameEvent);

}
