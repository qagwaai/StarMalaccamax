/**
 * GetAllUsersResponse.java
 * Created by pgirard at 2:07:29 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;

/**
 * @author pgirard
 * 
 */
public final class GetAllClosestsResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<ClosestDTO> closests;
    /**
	 * 
	 */
    private int totalClosests;

    /**
     * @return the users
     */
    public ArrayList<ClosestDTO> getClosests() {
        return closests;
    }

    /**
     * @return the totalClosests
     */
    public int getTotalClosests() {
        return totalClosests;
    }

    /**
     * @param closests
     *            the users to set
     */
    public void setClosests(final ArrayList<ClosestDTO> closests) {
        this.closests = closests;
    }

    /**
     * @param totalClosests
     *            the totalClosests to set
     */
    public void setTotalClosests(final int totalClosests) {
        this.totalClosests = totalClosests;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetAllClosestsResponse [closests=" + closests + ", totalClosests=" + totalClosests + "]";
    }

}
