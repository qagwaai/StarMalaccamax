/**
 * Country.java
 * com.qagwaai.starmalaccamax.shared.model
 * StarMalaccamax
 * Created Mar 4, 2011 at 1:22:19 PM
 */
package com.qagwaai.starmalaccamax.shared.model;

/**
 * @author pgirard
 * 
 */
public interface Country {
    /**
     * 
     * @return the name of the country
     */
    String getName();

    /**
     * 
     * @param name
     *            the name of the country
     */
    void setName(String name);

    /**
     * 
     * @return the abbreviation of the country
     */
    String getAbbreviation();

    /**
     * 
     * @param abb
     *            the abbreviation of the country
     */
    void setAbbreviation(String abb);
}
