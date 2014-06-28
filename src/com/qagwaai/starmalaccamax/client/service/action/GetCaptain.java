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
public final class GetCaptain implements IsSerializable, Action<GetCaptainResponse> {

    /**
	 * 
	 */
    private Long id;

    /**
	 * 
	 */
    public GetCaptain() {

    }

    /**
     * @param id
     *            the id to look for
     */
    public GetCaptain(final Long id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetCaptain [id=" + id + "]";
    }

}
