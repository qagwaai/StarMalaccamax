/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import com.qagwaai.starmalaccamax.client.core.AsyncCallbackWatcher;
import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.service.action.AddCaptainResponse;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;

/**
 * @author pgirard
 * 
 */
public abstract class AddedCaptain extends BaseAsyncCallback<AddCaptainResponse> {
    /**
     * notify that we got a new captain
     * 
     * @param captain
     *            the captain found by the command or null
     */
    public abstract void got(final CaptainDTO captain);

    /**
     * Constructor
     */
    public AddedCaptain() {
        super();
    }

    /**
     * Constructor
     * @param watcher the watcher to notify
     */
    public AddedCaptain(final AsyncCallbackWatcher watcher) {
        super(watcher);
    }

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
    public void onSuccess(final AddCaptainResponse result) {
        super.onSuccess(result);
        got(result.getCaptain());

    }

}
