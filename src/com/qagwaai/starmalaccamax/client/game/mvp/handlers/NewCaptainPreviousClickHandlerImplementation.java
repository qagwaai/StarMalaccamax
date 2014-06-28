package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;



/**
 * 
 * @author pgirard
 * 
 */
public final class NewCaptainPreviousClickHandlerImplementation implements ClickHandler {
    /**
	 * 
	 */
    private final NewCaptainView view;

    /**
     * 
     * @param view
     *            the associated view
     */
    public NewCaptainPreviousClickHandlerImplementation(final NewCaptainView view) {
        this.view = view;
    }

    @Override
    public void onClick(final ClickEvent event) {
        int currentPage = view.getCurrentPage();
        if (currentPage > 0) {
            view.displayPage(--currentPage);
            view.getNextButton().setTitle("Next");
        } else {
            view.getPreviousButton().disable();
        }

    }
}
