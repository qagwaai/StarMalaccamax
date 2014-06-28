/**
 * ServiceException.java
 * Created by pgirard at 1:53:14 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.shared package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.business.tick;

import java.io.Serializable;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public class TickException extends Exception implements Serializable {

    /**
	 * 
	 */
    public TickException() {
    }

    /**
     * @param message
     *            the error message
     */
    public TickException(final String message) {
        super(message);
    }

    /**
     * @param message
     *            the error message
     * @param reason
     *            the inner exception
     */
    public TickException(final String message, final Throwable reason) {
        super(message, reason);
    }

    /**
     * @param reason
     *            the inner exception
     */
    public TickException(final Throwable reason) {
        super(reason);
    }

}
