/**
 * GetAllUsersResponse.java
 * Created by pgirard at 2:07:29 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the CaptainMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
public final class GetAllTimezonesResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<String> timezones;
    /**
	 * 
	 */
    private int totalTimezones;

    /**
     * @return the Timezones
     */
    public ArrayList<String> getTimezones() {
        return timezones;
    }

    /**
     * @return the totalTimezones
     */
    public int getTotalTimezones() {
        return totalTimezones;
    }

    /**
     * @param timezones
     *            the users to set
     */
    public void setTimezones(final ArrayList<String> timezones) {
        this.timezones = timezones;
    }

    /**
     * @param totalTimezones
     *            the totalTimezones to set
     */
    public void setTotalTimezones(final int totalTimezones) {
        this.totalTimezones = totalTimezones;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final int maxLen = 10;
        return "GetAllTimezonesResponse [timezones="
            + (timezones != null ? timezones.subList(0, Math.min(timezones.size(), maxLen)) : null)
            + ", totalTimezones=" + totalTimezones + "]";
    }

}
