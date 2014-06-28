package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.MarketWindowViewImpl;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;


/**
 * 
 * @author pgirard
 * 
 */
public final class MarketWindowCloseClickHandlerImplementation implements ClickHandler {
    /**
	 * 
	 */
    private final MarketWindowViewImpl view;

    /**
     * 
     * @param view
     *            the view
     */
    public MarketWindowCloseClickHandlerImplementation(final MarketWindowViewImpl view) {
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