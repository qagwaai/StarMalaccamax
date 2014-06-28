package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.qagwaai.starmalaccamax.client.admin.mvp.AdminView;
import com.qagwaai.starmalaccamax.client.admin.mvp.delegates.ShowDetailsDelegate;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * 
 * @author pgirard
 * 
 */
public final class MenuDetailClickHandlerImpl implements ClickHandler {
    private final AdminView view;
    private final EventBus eventBus;
    private final ShowDetailsDelegate delegate;

    /**
     * 
     * @param view the view to communicate with
     * @param eventBus the event bus to listen to, or publish events on
     * @param delegate the delegate method to call
     */
    public MenuDetailClickHandlerImpl(final AdminView view, final EventBus eventBus, final ShowDetailsDelegate delegate) {
        this.view = view;
        this.eventBus = eventBus;
        this.delegate = delegate;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final MenuItemClickEvent event) {
        delegate.showDetails(view, eventBus);
    }
}
