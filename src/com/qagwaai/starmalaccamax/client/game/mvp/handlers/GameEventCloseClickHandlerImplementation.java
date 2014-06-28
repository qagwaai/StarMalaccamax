package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.GameEventWindowViewImpl;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;


/**
 * 
 * @author pgirard
 * 
 */
public final class GameEventCloseClickHandlerImplementation implements ClickHandler {
    /**
	 * 
	 */
    private final GameEventWindowViewImpl view;

    /**
     * 
     * @param view
     *            the view
     */
    public GameEventCloseClickHandlerImplementation(final GameEventWindowViewImpl view) {
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