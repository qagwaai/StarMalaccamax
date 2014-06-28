/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;

/**
 * @author pgirard
 * 
 */
public final class BulkAddMarket implements IsSerializable, Action<BulkAddMarketResponse> {

    /**
	 * 
	 */
    private ArrayList<MarketDTO> markets;

    /**
	 * 
	 */
    public BulkAddMarket() {

    }

    /**
     * @param markets
     *            the Markets to add
     */
    public BulkAddMarket(final ArrayList<MarketDTO> markets) {
        this.markets = markets;
    }

    /**
     * @return the markets
     */
    public ArrayList<MarketDTO> getMarkets() {
        return markets;
    }

    /**
     * @param markets
     *            the Markets to set
     */
    public void setMarkets(final ArrayList<MarketDTO> markets) {
        this.markets = markets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final int maxLen = 10;
        return "BulkAddMarket [markets="
            + (markets != null ? markets.subList(0, Math.min(markets.size(), maxLen)) : null) + "]";
    }

}
