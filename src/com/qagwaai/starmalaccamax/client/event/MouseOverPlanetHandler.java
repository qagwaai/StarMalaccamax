package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * 
 * @author qagwaai
 * 
 */
public interface MouseOverPlanetHandler extends EventHandler {

    /**
     * 
     * @param event
     *            details about the event including the current user
     */
    void onMouseOverPlanet(MouseOverPlanetEvent event);

}
