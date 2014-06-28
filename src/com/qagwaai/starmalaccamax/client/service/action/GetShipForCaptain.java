/**
 * GetCurrentUser.java
 * Created by pgirard at 3:57:10 PM on Jul 22, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.actions package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;

/**
 * @author pgirard
 * 
 */
public final class GetShipForCaptain implements IsSerializable, Action<GetShipForCaptainResponse> {
    /**
	 * 
	 */
    private CaptainDTO captain;

    /**
	 * 
	 */
    public GetShipForCaptain() {

    }

    /**
     * 
     * @param captain
     *            the ship's captain
     */
    public GetShipForCaptain(final CaptainDTO captain) {
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
        return "GetShipForCaptain [captain=" + captain + "]";
    }

}
