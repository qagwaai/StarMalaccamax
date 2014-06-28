/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.StarDTO;

/**
 * @author pgirard
 * 
 */
public final class BulkAddStar implements IsSerializable, Action<BulkAddStarResponse> {

    /**
	 * 
	 */
    private ArrayList<StarDTO> stars;

    /**
	 * 
	 */
    public BulkAddStar() {

    }

    /**
     * @param stars
     *            the Stars to add
     */
    public BulkAddStar(final ArrayList<StarDTO> stars) {
        this.stars = stars;
    }

    /**
     * @return the stars
     */
    public ArrayList<StarDTO> getStars() {
        return stars;
    }

    /**
     * @param stars
     *            the Stars to set
     */
    public void setStars(final ArrayList<StarDTO> stars) {
        this.stars = stars;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final int maxLen = 10;
        return "BulkAddStar [stars=" + (stars != null ? stars.subList(0, Math.min(stars.size(), maxLen)) : null) + "]";
    }

}
