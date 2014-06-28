/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.service.action.UpdateGameEventResponse;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;

/**
 * @author pgirard
 * 
 */
public abstract class UpdatedGameEvent extends BaseAsyncCallback<UpdateGameEventResponse> {
    /**
     * notify that we got a new gameEvent
     * 
     * @param gameEvent
     *            the gameEvent found by the command or null
     */
    public abstract void got(final GameEventDTO gameEvent);

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onFailure(final Throwable caught) {
        super.onFailure(caught);
        ErrorPresenter.present(caught);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onSuccess(final UpdateGameEventResponse result) {
        super.onSuccess(result);
        got(result.getGameEvent());

    }

}
