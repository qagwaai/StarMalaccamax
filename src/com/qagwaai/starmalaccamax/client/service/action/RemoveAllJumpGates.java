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
public final class RemoveAllJumpGates implements IsSerializable, Action<RemoveAllJumpGatesResponse> {

    /**
	 * 
	 */
    public RemoveAllJumpGates() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RemoveAllJumpGates []";
    }

}
