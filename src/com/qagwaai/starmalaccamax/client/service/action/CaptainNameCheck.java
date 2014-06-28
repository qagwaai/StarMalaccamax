/**
 * GetCaptain.java
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
public final class CaptainNameCheck implements IsSerializable, Action<CaptainNameCheckResponse> {

    private String name;

    /**
	 * 
	 */
    public CaptainNameCheck() {

    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "CaptainNameCheck [name=" + name + "]";
    }

    /**
     * @param name the name to check
     */
    public CaptainNameCheck(final String name) {
        super();
        this.name = name;
    }

}
