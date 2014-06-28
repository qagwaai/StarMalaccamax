/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.service.action.RemoveJumpGateResponse;
import com.qagwaai.starmalaccamax.shared.model.JumpGateDTO;

/**
 * @author pgirard
 * 
 */
public abstract class RemovedJumpGate extends BaseAsyncCallback<RemoveJumpGateResponse> {
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
    public void onSuccess(final RemoveJumpGateResponse result) {
        super.onSuccess(result);
        removed(result.getJumpGate());

    }

    /**
     * notify that we got a new jumpGate
     * 
     * @param jumpGate
     *            the jumpGate found by the command or null
     */
    public abstract void removed(final JumpGateDTO jumpGate);

}