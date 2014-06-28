package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * 
 * @author qagwaai
 * 
 */
public interface JobUpdatedHandler extends EventHandler {

    /**
     * 
     * @param event
     *            details about the event including the current solarSystem
     */
    void onJobUpdated(JobUpdatedEvent event);

}
