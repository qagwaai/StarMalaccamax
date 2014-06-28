/**
 * GetSolarSystemResponse.java
 * Created by pgirard at 1:40:09 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.MimeType;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;

/**
 * @author pgirard
 * 
 */
public final class GetSolarSystemResponseInRPC extends AbstractPolyResponse implements IsSerializable, GetSolarSystemResponse {
    /**
	 * 
	 */
    private SolarSystemDTO solarSystem;

    /**
     * @return the solarSystem
     */
    public SolarSystemDTO getSolarSystem() {
        return solarSystem;
    }

    /**
     * @param solarSystem
     *            the solarSystem to set
     */
    public void setSolarSystem(final SolarSystemDTO solarSystem) {
        this.solarSystem = solarSystem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetSolarSystemResponse [solarSystem=" + solarSystem + "]";
    }

    public GetSolarSystemResponseInRPC() {
	super();
	super.setMimeType(MimeType.rpc);
    }

}
