package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * 
 * @author qagwaai
 * 
 */
public interface PlanetUpdatedHandler extends EventHandler {

    /**
     * 
     * @param event
     *            details about the event including the current solarSystem
     */
    void onPlanetUpdated(PlanetUpdatedEvent event);

}
