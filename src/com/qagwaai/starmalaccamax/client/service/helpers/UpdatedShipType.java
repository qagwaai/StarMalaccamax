/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.service.action.UpdateShipTypeResponse;
import com.qagwaai.starmalaccamax.shared.model.ShipTypeDTO;

/**
 * @author pgirard
 * 
 */
public abstract class UpdatedShipType extends BaseAsyncCallback<UpdateShipTypeResponse> {
    /**
     * notify that we got a new shipType
     * 
     * @param shipType
     *            the shipType found by the command or null
     */
    public abstract void got(final ShipTypeDTO shipType);

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
    public void onSuccess(final UpdateShipTypeResponse result) {
        super.onSuccess(result);
        got(result.getShipType());

    }

}
