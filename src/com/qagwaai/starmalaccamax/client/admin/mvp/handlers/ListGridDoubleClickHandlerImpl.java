package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.qagwaai.starmalaccamax.client.admin.mvp.AdminView;
import com.qagwaai.starmalaccamax.client.admin.mvp.delegates.ShowDetailsDelegate;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;

/**
 * 
 * @author pgirard
 * 
 */
public final class ListGridDoubleClickHandlerImpl implements DoubleClickHandler {
    private final AdminView view;
    private final EventBus eventBus;
    private final ShowDetailsDelegate delegate;

    /**
     * 
     * @param view the view to communicate with
     * @param eventBus the event bus to listen to, or publish events on
     * @param delegate the delegate method to call
     */
    public ListGridDoubleClickHandlerImpl(final AdminView view, final EventBus eventBus, final ShowDetailsDelegate delegate) {
        this.view = view;
        this.eventBus = eventBus;
        this.delegate = delegate;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onDoubleClick(final DoubleClickEvent event) {
        delegate.showDetails(view, eventBus);
    }
}
