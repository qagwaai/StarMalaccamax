/**
 * GetUserResponse.java
 * Created by pgirard at 1:40:09 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
public final class RemoveAllPlanetsResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private Boolean response;

    /**
     * @return the response
     */
    public Boolean getResponse() {
        return response;
    }

    /**
     * @param response
     *            the response to set
     */
    public void setResponse(final Boolean response) {
        this.response = response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RemoveAllPlanetsResponse [response=" + response + "]";
    }

}
