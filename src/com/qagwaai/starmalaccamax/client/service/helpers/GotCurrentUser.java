/**
 * GotCurrentUser.java
 * Created by pgirard at 3:59:28 PM on Jul 22, 2010
 * in the com.qagwaai.starmalaccamax.client.services package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.service.action.GetCurrentUserResponse;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public abstract class GotCurrentUser extends BaseAsyncCallback<GetCurrentUserResponse> {

    /**
     * notify that we got a new user
     * 
     * @param user
     *            the user found by the command or null
     * @param loginUrl
     *            the url to login to
     * @param logoutUrl
     *            the url to logout to
     */
    public abstract void got(final UserDTO user, final String loginUrl, final String logoutUrl);

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void onFailure(final Throwable caught) {
        super.onFailure(caught);
        ErrorPresenter.present(caught);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onSuccess(final GetCurrentUserResponse result) {
        super.onSuccess(result);
        got(result.getUser(), result.getLoginUrl(), result.getLogoutUrl());

    }

}
