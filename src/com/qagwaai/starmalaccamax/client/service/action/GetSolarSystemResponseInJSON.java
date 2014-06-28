/**
 * GetSolarSystemResponse.java
 * Created by pgirard at 1:40:09 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.MimeType;

/**
 * @author pgirard
 * 
 */
public final class GetSolarSystemResponseInJSON extends AbstractPolyResponse implements IsSerializable, GetSolarSystemResponse {
    /**
	 * 
	 */
    private String solarSystem;

    /**
     * @return the solarSystem
     */
    public String getSolarSystem() {
	return solarSystem;
    }

    /**
     * @param solarSystem
     *            the solarSystem to set
     */
    public void setSolarSystem(final String solarSystem) {
	this.solarSystem = solarSystem;
    }

    @Override
    public String toString() {
	return "GetSolarSystemResponseInJSON [solarSystem=" + solarSystem + ", getMimeType()=" + getMimeType() + "]";
    }

    public GetSolarSystemResponseInJSON() {
	super();
	super.setMimeType(MimeType.js);
    }

}
