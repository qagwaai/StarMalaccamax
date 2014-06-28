/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
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
public final class BulkAddClosest implements IsSerializable, Action<BulkAddClosestResponse> {

    /**
	 * 
	 */
    private ArrayList<ClosestDTO> closests;

    /**
	 * 
	 */
    public BulkAddClosest() {

    }

    /**
     * @param closests
     *            the Closests to add
     */
    public BulkAddClosest(final ArrayList<ClosestDTO> closests) {
        this.closests = closests;
    }

    /**
     * @return the closests
     */
    public ArrayList<ClosestDTO> getClosests() {
        return closests;
    }

    /**
     * @param closests
     *            the Closests to set
     */
    public void setClosests(final ArrayList<ClosestDTO> closests) {
        this.closests = closests;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final int maxLen = 10;
        return "BulkAddClosest [closests="
            + (closests != null ? closests.subList(0, Math.min(closests.size(), maxLen)) : null) + "]";
    }

}
