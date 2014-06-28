/**
 * LoginServiceAsync.java
 * Created by pgirard at 3:51:07 PM on Jul 22, 2010
 * in the com.qagwaai.starmalaccamax.shared.services package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.qagwaai.starmalaccamax.client.service.action.Action;
import com.qagwaai.starmalaccamax.client.service.action.Response;

/**
 * @author pgirard
 * 
 */
public interface LoginServiceAsync {
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
