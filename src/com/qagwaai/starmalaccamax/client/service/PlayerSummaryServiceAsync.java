/**
 * PlayerSummaryAsync.java
 * Created by pgirard at 10:11:26 AM on Nov 17, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
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
public interface PlayerSummaryServiceAsync {
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
