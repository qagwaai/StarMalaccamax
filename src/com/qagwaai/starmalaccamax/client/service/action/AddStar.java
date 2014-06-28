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
public final class AddStar implements IsSerializable, Action<AddStarResponse> {

    /**
	 * 
	 */
    private StarDTO star;

    /**
	 * 
	 */
    public AddStar() {

    }

    /**
     * @param star
     *            the Star to add
     */
    public AddStar(final StarDTO star) {
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
     *            the Star to set
     */
    public void setStar(final StarDTO star) {
        this.star = star;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "AddStar [star=" + star + "]";
    }

}