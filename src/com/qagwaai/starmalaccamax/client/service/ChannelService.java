/**
 * 
 */
package com.qagwaai.starmalaccamax.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.qagwaai.starmalaccamax.client.service.action.Action;
import com.qagwaai.starmalaccamax.client.service.action.Response;
import com.qagwaai.starmalaccamax.shared.ServiceException;

/**
 * @author pgirard
 * 
 */
@RemoteServiceRelativePath("channel")
public interface ChannelService extends RemoteService {
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
