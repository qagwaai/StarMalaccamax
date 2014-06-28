/**
 * GetAllUsers.java
 * Created by pgirard at 2:06:53 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
public final class GetLocationForNewCaptain implements IsSerializable, Action<GetLocationForNewCaptainResponse> {

    /**
	 * 
	 */
    public GetLocationForNewCaptain() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetLocationForNewCaptain []";
    }

}
