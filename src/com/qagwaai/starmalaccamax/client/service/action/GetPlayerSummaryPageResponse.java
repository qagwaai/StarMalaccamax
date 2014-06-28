/**
 * GetAllUsersResponse.java
 * Created by pgirard at 2:07:29 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the CaptainMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketCommodityBucket;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;

/**
 * @author pgirard
 * 
 */
public final class GetPlayerSummaryPageResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<CaptainDTO> captains;

    /**
	 * 
	 */
    private ArrayList<ShipDTO> ships;

    /**
	 * 
	 */
    private MarketCommodityBucket commodities;

    /**
	 * 
	 */
    private ArrayList<GameEventDTO> events;
    /**
	 * 
	 */
    private int totalCaptains;

    /**
     * @return the users
     */
    public ArrayList<CaptainDTO> getCaptains() {
        return captains;
    }

    /**
     * @return the commodities
     */
    public MarketCommodityBucket getCommodities() {
        return commodities;
    }

    /**
     * @return the events
     */
    public ArrayList<GameEventDTO> getEvents() {
        return events;
    }

    /**
     * @return the ships
     */
    public ArrayList<ShipDTO> getShips() {
        return ships;
    }

    /**
     * @return the totalCaptains
     */
    public int getTotalCaptains() {
        return totalCaptains;
    }

    /**
     * @param captains
     *            the users to set
     */
    public void setCaptains(final ArrayList<CaptainDTO> captains) {
        this.captains = captains;
    }

    /**
     * @param commodities
     *            the commodities to set
     */
    public void setCommodities(final MarketCommodityBucket commodities) {
        this.commodities = commodities;
    }

    /**
     * @param events
     *            the events to set
     */
    public void setEvents(final ArrayList<GameEventDTO> events) {
        this.events = events;
    }

    /**
     * @param ships
     *            the ships to set
     */
    public void setShips(final ArrayList<ShipDTO> ships) {
        this.ships = ships;
    }

    /**
     * @param totalCaptains
     *            the totalCaptains to set
     */
    public void setTotalCaptains(final int totalCaptains) {
        this.totalCaptains = totalCaptains;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetPlayerSummaryPageResponse [captains=" + captains + ", ships=" + ships + ", commodities="
            + commodities + ", events=" + events + ", totalCaptains=" + totalCaptains + "]";
    }

}
