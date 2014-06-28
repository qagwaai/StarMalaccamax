/**
 * GetCaptainResponse.java
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
public final class CaptainNameCheckResponse extends AbstractResponse implements IsSerializable {
    private boolean exists;

    /**
     * @return the exists
     */
    public boolean exists() {
        return exists;
    }

    /**
     * @param exists
     *            the exists to set
     */
    public void setExists(final boolean exists) {
        this.exists = exists;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "CaptainNameCheckResponse [exists=" + exists + "]";
    }

}
