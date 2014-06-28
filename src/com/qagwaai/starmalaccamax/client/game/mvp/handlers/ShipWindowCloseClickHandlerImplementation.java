package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.ShipWindowViewImpl;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;


/**
 * 
 * @author pgirard
 * 
 */
public final class ShipWindowCloseClickHandlerImplementation implements ClickHandler {
    /**
     * 
     */
    private final ShipWindowViewImpl view;

    /**
     * 
     * @param view
     *            the view
     */
    public ShipWindowCloseClickHandlerImplementation(final ShipWindowViewImpl view) {
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
