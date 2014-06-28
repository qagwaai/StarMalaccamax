/**
 * GetPlanetResponse.java
 * Created by pgirard at 1:40:09 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.MimeType;
import com.qagwaai.starmalaccamax.shared.model.StarDTO;

/**
 * @author pgirard
 * 
 */
public final class GetStarsForSolarSystemResponseInRPC extends AbstractPolyResponse implements IsSerializable, GetStarsForSolarSystemResponse {
    /**
	 * 
	 */
    private ArrayList<StarDTO> stars;

    /**
     * @return the stars
     */
    public ArrayList<StarDTO> getStars() {
        return stars;
    }

    /**
     * @param stars
     *            the stars to set
     */
    public void setStars(final ArrayList<StarDTO> stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
	final int maxLen = 10;
	return "GetStarsForSolarSystemResponseInRPC [stars=" + (stars != null ? stars.subList(0, Math.min(stars.size(), maxLen)) : null) + ", toString()="
		+ super.toString() + "]";
    }

    public GetStarsForSolarSystemResponseInRPC() {
	super();
	super.setMimeType(MimeType.rpc);
    }


}
