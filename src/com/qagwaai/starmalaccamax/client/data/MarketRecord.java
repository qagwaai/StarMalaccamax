/**
 * UserRecord.java
 * Created by pgirard at 10:29:55 AM on Aug 20, 2010
 * in the com.qagwaai.starmalaccamax.shared.model.record package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import java.util.Date;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.shared.model.Market;
import com.qagwaai.starmalaccamax.shared.model.MarketCommodityDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class MarketRecord extends ListGridRecord implements Market {

    /**
	 * 
	 */
    public static final String ID = "id";
    /**
	 * 
	 */
    public static final String LAST_VISITED = "lastVisited";
    /**
	 * 
	 */
    public static final String PLANET_ID = "planetId";

    /**
	 * 
	 */
    public static final String COMMODITIES = "commodities";

    /**
	 * 
	 */
    public MarketRecord() {

    }

    /**
     * 
     * @param market
     *            the market DTO
     */
    public MarketRecord(final MarketDTO market) {
        setId(market.getId());
        setLastVisited(market.getLastVisited());
        setPlanetId(market.getPlanetId());
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Map<String, MarketCommodityDTO> getCommodities() {
        // TODO build out return of commodities map?
        return null;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        try {
            return LongConverter.fromJavaScript(this, MarketRecord.ID);
        } catch (DataException e) {
            GWT.log("Identity field could not be converted to Long", e);
        }
        return -1L;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Date getLastVisited() {
        return getAttributeAsDate(MarketRecord.LAST_VISITED);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getPlanetId() {
        try {
            return LongConverter.fromJavaScript(this, MarketRecord.PLANET_ID);
        } catch (DataException e) {
            GWT.log("Identity field could not be converted to Long", e);
        }
        return -1L;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setCommodities(final Map<String, MarketCommodityDTO> commodities) {
        // TODO accept commodities map?

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setId(final Long id) {
        setAttribute(MarketRecord.ID, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setLastVisited(final Date lastVisited) {
        setAttribute(MarketRecord.LAST_VISITED, lastVisited);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setPlanetId(final Long id) {
        this.setAttribute(MarketRecord.PLANET_ID, id);
    }

    /**
     * 
     * @return a solar system object from this record
     */
    public MarketDTO toMarket() {
        MarketDTO p = new MarketDTO();
        p.setId(getId());
        p.setLastVisited(getLastVisited());
        p.setPlanetId(getPlanetId());
        return p;
    }

}
