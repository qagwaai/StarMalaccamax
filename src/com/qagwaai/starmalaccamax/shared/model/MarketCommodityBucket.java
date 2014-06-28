/**
 * Commodities.java
 * Created by pgirard at 3:29:21 PM on Oct 25, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class MarketCommodityBucket implements IsSerializable, Serializable, Model {

    /**
	 * 
	 */
    private Map<String, MarketCommodityDTO> commodities = new HashMap<String, MarketCommodityDTO>();

    /**
     * 
     * @param key
     *            the unique key for the commodity
     * @return the commodity from the list of all, or null
     */
    public MarketCommodityDTO getCommodity(final Commodities key) {
        return commodities.get(key.getKey());
    }

    /**
     * 
     * @param key
     *            the unique identifier
     * @return the commodity from the list of all, or null
     */
    public MarketCommodityDTO getCommodity(final String key) {
        return commodities.get(key);
    }

    /**
	 * 
	 */
    public void hydrate() {
        for (Commodities entry : Commodities.values()) {
            commodities.put(entry.getKey(), new MarketCommodityDTO(entry.getKey()));
        }
    }

    /**
     * 
     * @param key
     *            the unique identifier
     * @param commodity
     *            the commodity object
     */
    public void setCommodity(final Commodities key, final MarketCommodityDTO commodity) {
        commodities.put(key.getKey(), commodity);
    }

    /**
     * 
     * @param key
     *            the unique identifier
     * @param commodity
     *            the commodity
     */
    public void setCommodity(final String key, final MarketCommodityDTO commodity) {
        commodities.put(key, commodity);
    }

    /**
     * 
     * @return the commodities in an array list
     */
    public ArrayList<MarketCommodityDTO> toArrayList() {
        ArrayList<MarketCommodityDTO> list = new ArrayList<MarketCommodityDTO>();
        for (Map.Entry<String, MarketCommodityDTO> commodityEntry : commodities.entrySet()) {
            list.add(commodityEntry.getValue());
        }
        return list;
    }

    /**
     * 
     * @return the commodities in an array list
     */
    public Map<String, MarketCommodityDTO> toMap() {
        return commodities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final int maxLen = 10;
        StringBuilder builder = new StringBuilder();
        builder.append("MarketCommodityBucket max" + maxLen + " [commodities=");
        builder.append(commodities != null ? toString(commodities.entrySet(), maxLen) : null);
        builder.append("]");
        return builder.toString();
    }

    /**
     * 
     * @param collection
     *            the collection to build
     * @param maxLen
     *            the maximum number of elements
     * @return the concatenated string
     */
    private String toString(final Collection<?> collection, final int maxLen) {
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

}
