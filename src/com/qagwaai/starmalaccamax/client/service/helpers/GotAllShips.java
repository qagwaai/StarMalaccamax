/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.service.action.GetAllShipsResponse;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;

/**
 * @author pgirard
 * 
 */
public abstract class GotAllShips extends BaseAsyncCallback<GetAllShipsResponse> {
    /**
     * notify that we got a new user
     * 
     * @param ships
     *            the ships found by the command or null
     */
    public abstract void got(final ArrayList<ShipDTO> ships);

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
    public void onSuccess(final GetAllShipsResponse result) {
        super.onSuccess(result);
        got(result.getShips());

    }

}
