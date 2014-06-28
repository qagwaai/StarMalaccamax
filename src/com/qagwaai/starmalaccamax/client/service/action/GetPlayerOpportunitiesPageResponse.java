/**
 * GetAllUsersResponse.java
 * Created by pgirard at 2:07:29 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the CaptainMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.MarketOpportunityForShipDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDistanceDTO;

/**
 * @author pgirard
 * 
 */
public final class GetPlayerOpportunitiesPageResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<PlanetDistanceDTO> planets;

    /**
	 * 
	 */
    private ArrayList<MarketOpportunityForShipDTO> markets;

    /**
     * @return the markets
     */
    public ArrayList<MarketOpportunityForShipDTO> getMarketOpportunities() {
        return markets;
    }

    /**
     * @return the planets
     */
    public ArrayList<PlanetDistanceDTO> getPlanets() {
        return planets;
    }

    /**
     * @param markets
     *            the markets to set
     */
    public void setMarketOpportunities(final ArrayList<MarketOpportunityForShipDTO> markets) {
        this.markets = markets;
    }

    /**
     * @param planets
     *            the planets to set
     */
    public void setPlanets(final ArrayList<PlanetDistanceDTO> planets) {
        this.planets = planets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetPlayerOpportunitiesPageResponse [planets=" + planets + ", markets=" + markets + "]";
    }

}
