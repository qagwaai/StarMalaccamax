/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.service.action.GetAllJumpGatesResponse;
import com.qagwaai.starmalaccamax.shared.model.JumpGateDTO;

/**
 * @author pgirard
 * 
 */
public abstract class GotAllJumpGates extends BaseAsyncCallback<GetAllJumpGatesResponse> {
    /**
     * notify that we got a new user
     * 
     * @param jumpGates
     *            the jumpGates found by the command or null
     */
    public abstract void got(final ArrayList<JumpGateDTO> jumpGates);

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
    public void onSuccess(final GetAllJumpGatesResponse result) {
        super.onSuccess(result);
        got(result.getJumpGates());

    }

}
