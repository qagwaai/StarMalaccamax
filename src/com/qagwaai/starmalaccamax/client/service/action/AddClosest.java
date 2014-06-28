/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;

/**
 * @author pgirard
 * 
 */
public final class AddClosest implements IsSerializable, Action<AddClosestResponse> {

    /**
	 * 
	 */
    private ClosestDTO closest;

    /**
	 * 
	 */
    public AddClosest() {

    }

    /**
     * @param closest
     *            the Closest to add
     */
    public AddClosest(final ClosestDTO closest) {
        this.closest = closest;
    }

    /**
     * @return the closest
     */
    public ClosestDTO getClosest() {
        return closest;
    }

    /**
     * @param closest
     *            the Closest to set
     */
    public void setClosest(final ClosestDTO closest) {
        this.closest = closest;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "AddClosest [closest=" + closest + "]";
    }

}
