package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowViewImpl;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;


/**
 * 
 * @author pgirard
 * 
 */
public final class PlanetWindowCloseClickHandlerImplementation implements ClickHandler {
    /**
	 * 
	 */
    private final PlanetWindowViewImpl view;

    /**
     * 
     * @param view
     *            the view
     */
    public PlanetWindowCloseClickHandlerImplementation(final PlanetWindowViewImpl view) {
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
