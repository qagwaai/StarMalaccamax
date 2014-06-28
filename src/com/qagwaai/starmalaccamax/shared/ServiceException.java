/**
 * ServiceException.java
 * Created by pgirard at 1:53:14 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.shared package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared;

import java.io.Serializable;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public class ServiceException extends Exception implements Serializable {

    /**
	 * 
	 */
    public ServiceException() {
    }

    /**
     * @param message
     *            the error message
     */
    public ServiceException(final String message) {
        super(message);
    }

    /**
     * @param message
     *            the error message
     * @param reason
     *            the inner exception
     */
    public ServiceException(final String message, final Throwable reason) {
        super(message, reason);
    }

    /**
     * @param reason
     *            the inner exception
     */
    public ServiceException(final Throwable reason) {
        super(reason);
    }

}
