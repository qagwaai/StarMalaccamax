/**
 * Market.java
 * Created by pgirard at 3:27:17 PM on Oct 25, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.util.Date;
import java.util.Map;

/**
 * @author pgirard
 * 
 */
public interface Market extends Model {
    /**
     * 
     * @return the commodities offered at this market
     */
    Map<String, MarketCommodityDTO> getCommodities();

    /**
     * @return the id
     */
    Long getId();

    /**
     * 
     * @return the date that the market was last visited
     */
    Date getLastVisited();

    /**
     * 
     * @return the id of the "owner" solar system
     */
    Long getPlanetId();

    /**
     * 
     * @param commodities
     *            the commodities offered at this market
     */
    void setCommodities(Map<String, MarketCommodityDTO> commodities);

    /**
     * @param id
     *            the id to set
     */
    void setId(final Long id);

    /**
     * 
     * @param lastVisited
     *            the date that the market was last visited
     */
    void setLastVisited(Date lastVisited);

    /**
     * 
     * @param id
     *            the id of the "owner" solar system
     */
    void setPlanetId(final Long id);

}
