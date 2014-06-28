/**
 * PlanetDistanceDTO.java
 * Created by pgirard at 9:54:16 AM on Dec 17, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class MarketDistanceDTO implements IsSerializable, Serializable {
    /**
	 * 
	 */
    private MarketDTO market;
    /**
	 * 
	 */
    private DistanceDTO distance;

    /**
	 * 
	 */
    public MarketDistanceDTO() {

    }

    /**
     * @param market
     *            the market
     * @param distance
     *            and the distance to that planet
     */
    public MarketDistanceDTO(final MarketDTO market, final DistanceDTO distance) {
        this.market = market;
        this.distance = distance;
    }

    /**
     * @return the distance
     */
    public DistanceDTO getDistance() {
        return distance;
    }

    /**
     * @return the market
     */
    public MarketDTO getMarket() {
        return market;
    }

    /**
     * @param distance
     *            the distance to set
     */
    public void setDistance(final DistanceDTO distance) {
        this.distance = distance;
    }

    /**
     * @param market
     *            the market to set
     */
    public void setMarket(final MarketDTO market) {
        this.market = market;
    }

}
