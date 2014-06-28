package com.qagwaai.starmalaccamax.client.core.mvp.handlers;

import com.qagwaai.starmalaccamax.client.core.LoginWatcher;
import com.qagwaai.starmalaccamax.client.core.mvp.AboutWindowPresenter;
import com.qagwaai.starmalaccamax.client.core.mvp.AboutWindowView;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * 
 * @author ehldxae
 *
 */
public final class AboutButtonClickHandlerImpl implements ClickHandler {
    private final EventBus eventBus;

    /**
     * Constructor
     * @param eventBus the event bus
     */
    public AboutButtonClickHandlerImpl(final EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void onClick(final ClickEvent event) {
        AboutWindowPresenter aboutPresenter =
            new AboutWindowPresenter(eventBus, new AboutWindowView(), LoginWatcher.getInstance().getLastEvent()
                .getCurrentUser());
        aboutPresenter.renderView();
    }
}
