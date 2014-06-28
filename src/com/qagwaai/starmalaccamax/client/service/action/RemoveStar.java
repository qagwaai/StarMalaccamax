/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.StarDTO;

/**
 * @author pgirard
 * 
 */
public final class RemoveStar implements IsSerializable, Action<RemoveStarResponse> {

    /**
	 * 
	 */
    private StarDTO star;

    /**
	 * 
	 */
    public RemoveStar() {

    }

    /**
     * @param star
     *            the Star to remove
     */
    public RemoveStar(final StarDTO star) {
        this.star = star;
    }

    /**
     * @return the star
     */
    public StarDTO getStar() {
        return star;
    }

    /**
     * @param star
     *            the star to set
     */
    public void setStar(final StarDTO star) {
        this.star = star;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RemoveStar [star=" + star + "]";
    }

}
