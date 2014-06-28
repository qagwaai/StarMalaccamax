/**
 * GetAllUsersResponse.java
 * Created by pgirard at 2:07:29 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the CaptainMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;

/**
 * @author pgirard
 * 
 */
public final class GetAllCaptainsResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<CaptainDTO> captains;
    /**
	 * 
	 */
    private int totalCaptains;

    /**
     * @return the users
     */
    public ArrayList<CaptainDTO> getCaptains() {
        return captains;
    }

    /**
     * @return the totalCaptains
     */
    public int getTotalCaptains() {
        return totalCaptains;
    }

    /**
     * @param captains
     *            the users to set
     */
    public void setCaptains(final ArrayList<CaptainDTO> captains) {
        this.captains = captains;
    }

    /**
     * @param totalCaptains
     *            the totalCaptains to set
     */
    public void setTotalCaptains(final int totalCaptains) {
        this.totalCaptains = totalCaptains;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetAllCaptainsResponse [captains=" + captains + ", totalCaptains=" + totalCaptains + "]";
    }

}
