/**
 * ServiceException.java
 * Created by pgirard at 1:53:14 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.shared package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public class DAOException extends Exception implements IsSerializable {

    /**
	 * 
	 */
    public DAOException() {
    }

    /**
     * @param message
     *            the error message
     */
    public DAOException(final String message) {
        super(message);
    }

    /**
     * @param message
     *            the error message
     * @param reason
     *            the inner exception
     */
    public DAOException(final String message, final Throwable reason) {
        super(message, reason);
    }

    /**
     * @param reason
     *            the inner exception
     */
    public DAOException(final Throwable reason) {
        super(reason);
    }

}
