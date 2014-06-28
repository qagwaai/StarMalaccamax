/**
 * GetCurrentUserResponse.java
 * Created by pgirard at 3:57:56 PM on Jul 22, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.actions package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * @author pgirard
 * 
 */
public final class GetMarketForPlanetResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private MarketDTO market;

    /**
	 * 
	 */
    private PlanetDTO planet;

    /**
     * @return the market
     */
    public MarketDTO getMarket() {
        return market;
    }

    /**
     * @return the planet
     */
    public PlanetDTO getPlanet() {
        return planet;
    }

    /**
     * @param market
     *            the market to set
     */
    public void setMarket(final MarketDTO market) {
        this.market = market;
    }

    /**
     * @param planet
     *            the planet to set
     */
    public void setPlanet(final PlanetDTO planet) {
        this.planet = planet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetMarketForPlanetResponse [market=" + market + ", planet=" + planet + "]";
    }

}
