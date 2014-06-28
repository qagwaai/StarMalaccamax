/**
 * GetCurrentUserResponse.java
 * Created by pgirard at 3:57:56 PM on Jul 22, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.actions package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.MarketOpportunityForShipDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipCargoDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;

/**
 * @author pgirard
 * 
 */
public final class GetLocalOpportunitiesForCargoResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ShipDTO ship;
    /**
	 * 
	 */
    private ShipCargoDTO cargo;
    /**
	 * 
	 */
    private ArrayList<MarketOpportunityForShipDTO> opportunities = new ArrayList<MarketOpportunityForShipDTO>();

    /**
     * @return the cargo
     */
    public ShipCargoDTO getCargo() {
        return cargo;
    }

    /**
     * @return the opportunities
     */
    public ArrayList<MarketOpportunityForShipDTO> getOpportunities() {
        return opportunities;
    }

    /**
     * @return the ship
     */
    public ShipDTO getShip() {
        return ship;
    }

    /**
     * @param cargo
     *            the cargo to set
     */
    public void setCargo(final ShipCargoDTO cargo) {
        this.cargo = cargo;
    }

    /**
     * @param opportunities
     *            the opportunities to set
     */
    public void setOpportunities(final ArrayList<MarketOpportunityForShipDTO> opportunities) {
        this.opportunities = opportunities;
    }

    /**
     * @param ship
     *            the ship to set
     */
    public void setShip(final ShipDTO ship) {
        this.ship = ship;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final int maxLen = 5;
        return "GetLocalOpportunitiesForCargoResponse [ship=" + ship + ", cargo=" + cargo + ", opportunities="
            + (opportunities != null ? opportunities.subList(0, Math.min(opportunities.size(), maxLen)) : null) + "]";
    }

}
