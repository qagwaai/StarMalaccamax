/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.service.action.AddUserResponse;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public abstract class AddedUser extends BaseAsyncCallback<AddUserResponse> {
    /**
     * notify that we got a new user
     * 
     * @param user
     *            the user found by the command or null
     */
    public abstract void got(final UserDTO user);

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
    public void onSuccess(final AddUserResponse result) {
        super.onSuccess(result);
        got(result.getUser());

    }

}
