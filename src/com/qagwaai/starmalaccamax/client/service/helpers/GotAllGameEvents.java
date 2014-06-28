/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.service.action.GetAllGameEventsResponse;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;

/**
 * @author pgirard
 * 
 */
public abstract class GotAllGameEvents extends BaseAsyncCallback<GetAllGameEventsResponse> {
    /**
     * notify that we got a list of GameEvents
     * 
     * @param gameEvents
     *            the GameEvents found by the command or null
     */
    public abstract void got(final ArrayList<GameEventDTO> gameEvents);

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
    public void onSuccess(final GetAllGameEventsResponse result) {
        super.onSuccess(result);
        got(result.getGameEvents());

    }

}
