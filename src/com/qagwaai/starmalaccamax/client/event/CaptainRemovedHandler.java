package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * 
 * @author qagwaai
 * 
 */
public interface CaptainRemovedHandler extends EventHandler {

    /**
     * 
     * @param event
     *            details about the event including the current user
     */
    void onCaptainRemoved(CaptainRemovedEvent event);

}
