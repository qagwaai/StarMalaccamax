/**
 * Response.java
 * Created by pgirard at 3:52:15 PM on Jul 22, 2010
 * in the com.qagwaai.starmalaccamax.shared.services package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

/**
 * @author pgirard
 * 
 */
public interface Response {
    /**
     * 
     * @return any debug information that is ok to pass to the client
     */
    String getDebugString();

    /**
     * 
     * @return true if the action was successful
     */
    boolean isSuccessful();

    /**
     * 
     * @param message
     *            any debug information that is ok to pass to the client
     */
    void setDebugString(String message);

    /**
     * 
     * @param successfulAction
     *            true if the action was successful
     */
    void setSuccessful(boolean successfulAction);
}
