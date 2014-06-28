/**
 * GetUserResponse.java
 * Created by pgirard at 1:40:09 PM on Aug 19, 2010
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
public final class AddStarResponse extends AbstractResponse implements IsSerializable {
    /**
	 * 
	 */
    private StarDTO star;

    /**
     * @return the user
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
        return "AddStarResponse [star=" + star + "]";
    }

}
