/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
public final class RemoveAllGameEvents implements IsSerializable, Action<RemoveAllGameEventsResponse> {

    /**
	 * 
	 */
    public RemoveAllGameEvents() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RemoveAllGameEvents []";
    }

}
