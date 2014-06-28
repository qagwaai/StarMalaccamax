/**
 * GetAllUsersResponse.java
 * Created by pgirard at 2:07:29 PM on Aug 19, 2010
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
public final class GetAllStarsResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<StarDTO> stars;
    /**
	 * 
	 */
    private int totalStars;

    /**
     * @return the users
     */
    public ArrayList<StarDTO> getStars() {
        return stars;
    }

    /**
     * @return the totalStars
     */
    public int getTotalStars() {
        return totalStars;
    }

    /**
     * @param stars
     *            the users to set
     */
    public void setStars(final ArrayList<StarDTO> stars) {
        this.stars = stars;
    }

    /**
     * @param totalStars
     *            the totalStars to set
     */
    public void setTotalStars(final int totalStars) {
        this.totalStars = totalStars;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetAllStarsResponse [stars=" + stars + ", totalStars=" + totalStars + "]";
    }

}
