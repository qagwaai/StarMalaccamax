/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.service.action.RemoveMarketResponse;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;

/**
 * @author pgirard
 * 
 */
public abstract class RemovedMarket extends BaseAsyncCallback<RemoveMarketResponse> {
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
    public void onSuccess(final RemoveMarketResponse result) {
        super.onSuccess(result);
        removed(result.getMarket());

    }

    /**
     * notify that we got a new market
     * 
     * @param market
     *            the market found by the command or null
     */
    public abstract void removed(final MarketDTO market);

}
