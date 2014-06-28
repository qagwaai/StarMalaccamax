/**
 * GetPlanetResponse.java
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
public final class GetStarsForSolarSystemResponseInJSON extends AbstractPolyResponse implements IsSerializable, GetStarsForSolarSystemResponse {
    /**
	 * 
	 */
    private String stars;

    /**
     * @return the stars
     */
    public String getStars() {
        return stars;
    }

    /**
     * @param stars
     *            the stars to set
     */
    public void setStars(final String stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
	return "GetStarsForSolarSystemResponseInJSON [stars=" + stars + ", toString()=" + super.toString() + "]";
    }

    public GetStarsForSolarSystemResponseInJSON() {
	super();
	super.setMimeType(MimeType.js);
    }


}
