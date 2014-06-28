/**
 * AbstractResponse.java
 * Created by pgirard at 11:56:45 AM on Nov 16, 2010
 * in the com.qagwaai.starmalaccamax.client.service.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

/**
 * @author pgirard
 * 
 */
public abstract class AbstractResponse implements Response {

    /**
	 * 
	 */
    private String debug;
    /**
	 * 
	 */
    private boolean successful = true;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final String getDebugString() {
        return debug;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final boolean isSuccessful() {
        return successful;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void setDebugString(final String message) {
        debug = message;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void setSuccessful(final boolean successfulAction) {
        successful = successfulAction;
    }

}
