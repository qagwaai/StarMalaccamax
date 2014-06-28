/**
 * JobService.java
 * Created by pgirard at 1:33:35 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.qagwaai.starmalaccamax.client.service.action.Action;
import com.qagwaai.starmalaccamax.client.service.action.Response;
import com.qagwaai.starmalaccamax.shared.ServiceException;

/**
 * 
 * @author pgirard
 * 
 */
@RemoteServiceRelativePath("JobService")
public interface JobService extends RemoteService {
    /**
     * 
     * @param <T>
     *            a command response type
     * @param action
     *            the command itself
     * @return the response
     * @throws ServiceException
     *             if the action cannot be executed
     */
    <T extends Response> T execute(Action<T> action) throws ServiceException;

}
