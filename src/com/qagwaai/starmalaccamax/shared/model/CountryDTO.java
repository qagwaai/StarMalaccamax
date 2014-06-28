/**
 * CountryDTO.java
 * com.qagwaai.starmalaccamax.shared.model
 * StarMalaccamax
 * Created Mar 4, 2011 at 1:25:13 PM
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class CountryDTO implements Country, IsSerializable, Serializable {

    /**
	 * 
	 */
    private String name;
    /**
	 * 
	 */
    private String abbreviation;

    /**
     * @return the full name of the country
     * @see com.qagwaai.starmalaccamax.shared.model.Country#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the full name of the country
     * @see com.qagwaai.starmalaccamax.shared.model.Country#setName(java.lang.String)
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the country abbreviation
     * @see com.qagwaai.starmalaccamax.shared.model.Country#getAbbreviation()
     */
    @Override
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * @param abb
     *            the country abbreviation
     * @see com.qagwaai.starmalaccamax.shared.model.Country#setAbbreviation(java.lang.String)
     */
    @Override
    public void setAbbreviation(final String abb) {
        this.abbreviation = abb;
    }

    /**
     * @param name
     *            the full name of the country
     * @param abbreviation
     *            the country abbreviation
     */
    public CountryDTO(final String name, final String abbreviation) {
        super();
        this.name = name;
        this.abbreviation = abbreviation;
    }

    /**
	 * 
	 */
    public CountryDTO() {
        super();
    }

    /**
     * @return a debug version of this object
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CountryDTO [name=" + name + ", abbreviation=" + abbreviation + "]";
    }

}
