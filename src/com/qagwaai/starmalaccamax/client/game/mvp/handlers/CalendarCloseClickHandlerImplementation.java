package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.CalendarWindowViewImpl;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * 
 * @author pgirard
 * 
 */
public final class CalendarCloseClickHandlerImplementation implements ClickHandler {
    /**
	 * 
	 */
    private final CalendarWindowViewImpl view;

    /**
     * 
     * @param view
     *            the view
     */
    public CalendarCloseClickHandlerImplementation(final CalendarWindowViewImpl view) {
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
