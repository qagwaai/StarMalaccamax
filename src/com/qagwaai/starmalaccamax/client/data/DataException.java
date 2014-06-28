/**
 * DataException.java
 * Created by pgirard at 12:28:22 PM on Aug 25, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.data package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public class DataException extends Exception {

    /**
	 * 
	 */
    public DataException() {
    }

    /**
     * @param message
     *            the message explaining the error
     */
    public DataException(final String message) {
        super(message);
    }

    /**
     * @param message
     *            the message explaining the error
     * @param reason
     *            any internal throwable
     */
    public DataException(final String message, final Throwable reason) {
        super(message, reason);
    }

    /**
     * @param reason
     *            the internal error
     */
    public DataException(final Throwable reason) {
        super(reason);
    }

}
