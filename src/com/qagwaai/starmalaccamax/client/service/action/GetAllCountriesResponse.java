/**
 * GetAllUsersResponse.java
 * Created by pgirard at 2:07:29 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the CaptainMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.CountryDTO;

/**
 * @author pgirard
 * 
 */
public final class GetAllCountriesResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<CountryDTO> countries;
    /**
	 * 
	 */
    private int totalCountries;

    /**
     * @return the Countries
     */
    public ArrayList<CountryDTO> getCountries() {
        return countries;
    }

    /**
     * @return the totalCountries
     */
    public int getTotalCountries() {
        return totalCountries;
    }

    /**
     * @param countries
     *            the users to set
     */
    public void setCountries(final ArrayList<CountryDTO> countries) {
        this.countries = countries;
    }

    /**
     * @param totalCountries
     *            the totalCountries to set
     */
    public void setTotalCountries(final int totalCountries) {
        this.totalCountries = totalCountries;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final int maxLen = 10;
        return "GetAllCountriesResponse [countries="
            + (countries != null ? countries.subList(0, Math.min(countries.size(), maxLen)) : null)
            + ", totalCountries=" + totalCountries + "]";
    }

}
