package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.ClosestPlanetWindowViewImpl;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * 
 * @author pgirard
 * 
 */
public final class ClosestCloseClickHandlerImplementation implements ClickHandler {
    /**
	 * 
	 */
    private final ClosestPlanetWindowViewImpl view;

    /**
     * 
     * @param view
     *            the view
     */
    public ClosestCloseClickHandlerImplementation(final ClosestPlanetWindowViewImpl view) {
        this.view = view;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final ClickEvent event) {
        view.destroy();
    }
}