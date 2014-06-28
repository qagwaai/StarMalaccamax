/**
 * UtilityServiceAsync.java
 * com.qagwaai.starmalaccamax.client.service
 * StarMalaccamax
 * Created Mar 4, 2011 at 1:28:08 PM
 */
package com.qagwaai.starmalaccamax.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.qagwaai.starmalaccamax.client.service.action.Action;
import com.qagwaai.starmalaccamax.client.service.action.Response;

/**
 * @author pgirard
 * 
 */
public interface UtilityServiceAsync {
    /**
     * 
     * @param <T>
     *            a command response type
     * @param action
     *            the command itself
     * @param callback
     *            the callback to return the response
     */
    <T extends Response> void execute(Action<T> action, AsyncCallback<T> callback);
}
