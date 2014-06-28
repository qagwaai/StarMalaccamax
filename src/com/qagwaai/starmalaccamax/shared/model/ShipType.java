/**
 * ShipType.java
 * Created by pgirard at 7:28:11 AM on Nov 5, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

/**
 * @author pgirard
 * 
 */
public interface ShipType extends Model {
    /**
     * @return the id
     */
    Long getId();

    /**
     * 
     * @return the type name
     */
    String getName();

    /**
     * @param id
     *            the id to set
     */
    void setId(final Long id);

    /**
     * 
     * @param name
     *            the type name
     */
    void setName(String name);
}
