package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.CaptainWindowView;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * 
 * @author pgirard
 * 
 */
public final class CaptainCancelClickHandlerImplementation implements ClickHandler {
    /**
	 * 
	 */
    private final CaptainWindowView view;

    /**
     * 
     * @param view
     *            the view
     */
    public CaptainCancelClickHandlerImplementation(final CaptainWindowView view) {
        this.view = view;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final ClickEvent event) {
        view.closeWindow();

    }
}
