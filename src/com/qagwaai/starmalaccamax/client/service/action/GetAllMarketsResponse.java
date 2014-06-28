/**
 * GetAllUsersResponse.java
 * Created by pgirard at 2:07:29 PM on Aug 19, 2010
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
public final class GetAllMarketsResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<MarketDTO> markets;
    /**
	 * 
	 */
    private int totalMarkets;

    /**
     * @return the users
     */
    public ArrayList<MarketDTO> getMarkets() {
        return markets;
    }

    /**
     * @return the totalMarkets
     */
    public int getTotalMarkets() {
        return totalMarkets;
    }

    /**
     * @param markets
     *            the users to set
     */
    public void setMarkets(final ArrayList<MarketDTO> markets) {
        this.markets = markets;
    }

    /**
     * @param totalMarkets
     *            the totalMarkets to set
     */
    public void setTotalMarkets(final int totalMarkets) {
        this.totalMarkets = totalMarkets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetAllMarketsResponse [markets=" + markets + ", totalMarkets=" + totalMarkets + "]";
    }

}
