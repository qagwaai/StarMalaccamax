package com.qagwaai.starmalaccamax.client.core.mvp.handlers;

import com.google.gwt.user.client.Window;
import com.qagwaai.starmalaccamax.client.core.LoginWatcher;
import com.qagwaai.starmalaccamax.client.event.CurrentUserChangedEvent;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * 
 * @author pgirard
 * 
 */
public final class LogoutLinkClickHandlerImpl implements ClickHandler {

    /**
	 * 
	 */
    private EventBus eventBus;

    /**
     * 
     * @param eventBus
     *            the bus to publish events on
     */
    public LogoutLinkClickHandlerImpl(final EventBus eventBus) {
        this.eventBus = eventBus;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final ClickEvent event) {

        if (LoginWatcher.getInstance().getLastEvent() != null) {
            if (LoginWatcher.getInstance().getLastEvent().getCurrentUser() != null) {
                Window.Location.assign(LoginWatcher.getInstance().getLastEvent().getLogoutUrl());
                return;
            } else {
                CurrentUserChangedEvent.fire(eventBus, null, LoginWatcher.getInstance().getLastEvent().getLoginUrl(),
                    "");
                Window.Location.assign(LoginWatcher.getInstance().getLastEvent().getLoginUrl());
                return;
            }
        }
    }
}
