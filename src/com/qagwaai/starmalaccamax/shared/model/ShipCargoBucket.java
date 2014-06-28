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
 * 
 */
@SuppressWarnings("serial")
public final class ShipCargoBucket implements IsSerializable, Serializable, Model {
    /**
	 * 
	 */
    private Map<String, ShipCargoDTO> cargo = new HashMap<String, ShipCargoDTO>();

    /**
	 * 
	 */
    public ShipCargoBucket() {

    }

    /**
     * 
     * @param key
     *            the key to look for
     * @return the cargo amount if any
     */
    public ShipCargoDTO getCargo(final Commodities key) {
        return cargo.get(key.getKey());
    }

    /**
     * 
     * @param key
     *            the key to look for
     * @return the amount of cargo if any
     */
    public ShipCargoDTO getCargo(final String key) {
        return cargo.get(key);
    }

    /**
	 * 
	 */
    public void hydrate() {
        for (Commodities entry : Commodities.values()) {
            cargo.put(entry.getKey(), new ShipCargoDTO());
        }
    }

    /**
     * 
     * @param key
     *            the key to set
     * @param shipCargo
     *            the shipCargo to set
     */
    public void setCargo(final Commodities key, final ShipCargoDTO shipCargo) {
        cargo.put(key.getKey(), shipCargo);
    }

    /**
     * 
     * @param key
     *            the unique key to set
     * @param shipCargo
     *            the shipCargo
     */
    public void setCargo(final String key, final ShipCargoDTO shipCargo) {
        cargo.put(key, shipCargo);
    }

    /**
     * 
     * @return the commodities in an array list
     */
    public Map<String, ShipCargoDTO> toMap() {
        return cargo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final int maxLen = 10;
        return "ShipCargoBucket [cargo=" + (cargo != null ? toString(cargo.entrySet(), maxLen) : null) + "]";
    }

    /**
     * 
     * @param collection
     *            the collection of cargo
     * @param maxLen
     *            the maximum number of entries
     * @return the formatted string
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
