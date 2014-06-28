package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * 
 * @author qagwaai
 * 
 */
public interface ServiceExceptionHandler extends EventHandler {

    /**
     * 
     * @param event
     *            details about the event including the current user
     */
    void onServiceException(ServiceExceptionEvent event);

}
