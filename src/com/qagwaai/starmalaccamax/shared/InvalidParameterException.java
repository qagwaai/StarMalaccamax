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
public class InvalidParameterException extends Exception implements Serializable {

    /**
	 * 
	 */
    public InvalidParameterException() {
    }

    /**
     * @param message
     *            the error message
     */
    public InvalidParameterException(final String message) {
        super(message);
    }

    /**
     * @param message
     *            the error message
     * @param reason
     *            the inner exception
     */
    public InvalidParameterException(final String message, final Throwable reason) {
        super(message, reason);
    }

    /**
     * @param reason
     *            the inner exception
     */
    public InvalidParameterException(final Throwable reason) {
        super(reason);
    }

}
