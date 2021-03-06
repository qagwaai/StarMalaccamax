package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * 
 * @author qagwaai
 * 
 */
public interface UserLoadedHandler extends EventHandler {

    /**
     * 
     * @param event
     *            details about the event including the current user
     */
    void onUserLoaded(UserLoadedEvent event);

}
