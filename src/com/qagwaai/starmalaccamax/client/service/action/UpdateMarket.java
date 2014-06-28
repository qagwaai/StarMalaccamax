/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;

/**
 * @author pgirard
 * 
 */
public final class UpdateMarket implements IsSerializable, Action<UpdateMarketResponse> {

    /**
	 * 
	 */
    private MarketDTO market;

    /**
	 * 
	 */
    public UpdateMarket() {

    }

    /**
     * @param market
     *            the Market to update
     */
    public UpdateMarket(final MarketDTO market) {
        this.market = market;
    }

    /**
     * @return the market
     */
    public MarketDTO getMarket() {
        return market;
    }

    /**
     * @param market
     *            the market to set
     */
    public void setMarket(final MarketDTO market) {
        this.market = market;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "UpdateMarket [market=" + market + "]";
    }

}
