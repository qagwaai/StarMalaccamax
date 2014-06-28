/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the CaptainMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;

/**
 * @author pgirard
 * 
 */
public final class RemoveCaptain implements IsSerializable, Action<RemoveCaptainResponse> {

    /**
	 * 
	 */
    private CaptainDTO captain;

    /**
	 * 
	 */
    public RemoveCaptain() {

    }

    /**
     * @param captain
     *            the Captain to remove
     */
    public RemoveCaptain(final CaptainDTO captain) {
        this.captain = captain;
    }

    /**
     * @return the captain
     */
    public CaptainDTO getCaptain() {
        return captain;
    }

    /**
     * @param captain
     *            the captain to set
     */
    public void setCaptain(final CaptainDTO captain) {
        this.captain = captain;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RemoveCaptain [captain=" + captain + "]";
    }

}
