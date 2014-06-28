/**
 * Attributes.java
 * Created by pgirard at 8:13:34 PM on Aug 29, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * @deprecated
 */
@SuppressWarnings("serial")
public final class ShipAttributeBucket implements IsSerializable, Serializable, Model {
    /**
	 * 
	 */
    private Map<String, Integer> attributes = new HashMap<String, Integer>();

    /**
	 * 
	 */
    public ShipAttributeBucket() {

    }

    /**
     * 
     * @param key
     *            the attribute key
     * @return the value
     */
    public Integer getAttribute(final ShipAttributes key) {
        /*
         * Object obj = attributes.get(key.getKey()); if (obj instanceof Long) {
         * // TODO why does this happen? return ((Long) obj).intValue(); }
         * return (Integer) obj;
         */
        return null;
    }

    /**
     * 
     * @param key
     *            the key to search for
     * @return the value if any
     */
    public Integer getAttribute(final String key) {
        return attributes.get(key);
    }

    /**
	 * 
	 */
    public void hydrate() {
        /*
         * for (ShipAttributes entry : ShipAttributes.values()) {
         * attributes.put(entry.getKey(), Integer.valueOf(0)); }
         */
    }

    /**
     * 
     * @param key
     *            the key to set
     * @param attribute
     *            the value of the attribute
     */
    public void setAttribute(final ShipAttributes key, final Integer attribute) {
        // attributes.put(key.getKey(), attribute);
    }

    /**
     * 
     * @param key
     *            the key to set
     * @param attribute
     *            the value of the attribute
     */
    public void setAttribute(final String key, final Integer attribute) {
        attributes.put(key, attribute);
    }

    /**
     * 
     * @return the commodities in an array list
     */
    public Map<String, Integer> toMap() {
        return attributes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final int maxLen = 10;
        StringBuilder builder = new StringBuilder();
        builder.append("ShipAttributeBucket [attributes=");
        builder.append(attributes != null ? toString(attributes.entrySet(), maxLen) : null);
        builder.append("]");
        return builder.toString();
    }

    /**
     * 
     * @param collection
     *            the collection to check
     * @param maxLen
     *            the maximum number of elements
     * @return the concated string
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
