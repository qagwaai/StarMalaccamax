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
public final class BulkAddJobResponse extends AbstractResponse implements IsSerializable {
    /**
	 * 
	 */
    private boolean success;

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success
     *            the success to set
     */
    public void setSuccess(final boolean success) {
        this.success = success;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "BulkAddJobResponse [success=" + success + "]";
    }

}
