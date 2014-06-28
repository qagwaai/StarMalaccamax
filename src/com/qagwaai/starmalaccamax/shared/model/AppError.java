/**
 * Error.java
 * Created by pgirard at 10:22:12 AM on Oct 1, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.util.Set;

/**
 * @author pgirard
 * 
 */
public interface AppError extends Model {

    /**
     * 
     * @return the detail of the error
     */
    String getDetail();

    /**
     * 
     * @return the priority of the error
     */
    int getPriority();

    /**
     * 
     * @return the short description of the error
     */
    String getShortMessage();

    /**
     * 
     * @param detail
     *            the detail of the error
     */
    void setDetail(String detail);

    /**
     * 
     * @param error
     *            the detail of the error
     */
    void setDetail(Throwable error);

    /**
     * 
     * @param errors load a set of errors - from a encapsulated error
     */
    void setDetails(final Set<Throwable> errors);

    /**
     * 
     * @param priority
     *            the priority of the error
     */
    void setPriority(int priority);

    /**
     * 
     * @param message
     *            the short description of the error
     */
    void setShortMessage(String message);

}
