/**
 * MarketDTO.java
 * Created by pgirard at 3:44:25 PM on Oct 25, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.code.twig.annotation.Embedded;
import com.google.code.twig.annotation.Index;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.Entity;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
@Entity
public final class MarketDTO implements IsSerializable, Serializable, Market {

    /**
	 * 
	 */
    @com.google.code.twig.annotation.Id
    @com.googlecode.objectify.annotation.Id
    @Index
    @com.googlecode.objectify.annotation.Index
    private Long id;
    /**
	 * 
	 */
    @com.googlecode.objectify.annotation.Index
    private Long planetId;

    /**
	 * 
	 */
    @Embedded
    //@Container
    private Map<String, MarketCommodityDTO> commodities = new HashMap<String, MarketCommodityDTO>();

    /**
	 * 
	 */
    @Index
    private Date lastVisited;

    /**
	 * 
	 */
    public MarketDTO() {
        MarketCommodityBucket cb = new MarketCommodityBucket();
        cb.hydrate();
        commodities = cb.toMap();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Map<String, MarketCommodityDTO> getCommodities() {
        return commodities;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Date getLastVisited() {
        return lastVisited;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getPlanetId() {
        return planetId;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setCommodities(final Map<String, MarketCommodityDTO> commodities) {
        this.commodities = commodities;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setId(final Long id) {
        this.id = id;

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setLastVisited(final Date lastVisited) {
        this.lastVisited = lastVisited;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setPlanetId(final Long id) {
        this.planetId = id;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final int maxLen = 5;
        return "MarketDTO [id=" + id + ", planetId=" + planetId + ", commodities="
            + (commodities != null ? toString(commodities.entrySet(), maxLen) : null) + ", lastVisited=" + lastVisited
            + "]";
    }

    private String toString(Collection<?> collection, int maxLen) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        int i = 0;
        for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(iterator.next());
        }
        builder.append("]");
        return builder.toString();
    }
    
    public static String getFieldGetter(String fieldName) {
    	String methodName = null;
    	
    	if (fieldName.equals("id")) {
    		methodName = "getId";
    	} else if (fieldName.equals("planetId")) {
    		methodName = "getPlanetId";
    	} 
    	return methodName;
    }

}
