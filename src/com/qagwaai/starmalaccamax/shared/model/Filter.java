/**
 * Filter.java
 * Created by pgirard at 10:16:42 AM on Oct 5, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

/**
 * @author pgirard
 * 
 */
public interface Filter {
    /**
     * 
     * @return the field to filter on
     */
    String getField();

    /**
     * 
     * @return the value of the filter
     */
    String getValue();

    /**
     * 
     * @param field
     *            the field to filter on
     */
    void setField(String field);

    /**
     * 
     * @param value
     *            the value of the filter
     */
    void setValue(String value);
}
