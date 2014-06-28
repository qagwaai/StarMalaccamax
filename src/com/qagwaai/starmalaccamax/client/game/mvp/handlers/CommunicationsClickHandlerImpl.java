package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.core.HistoryManager;
import com.qagwaai.starmalaccamax.client.core.Locations;
import com.qagwaai.starmalaccamax.client.core.LoginWatcher;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public final class CommunicationsClickHandlerImpl implements ClickHandler {
    private final EventBus eventBus;

    public CommunicationsClickHandlerImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void onClick(final ClickEvent event) {
        HistoryManager.present(Locations.getPlayerCommuncationsPage(), eventBus, LoginWatcher.getInstance()
            .getLastEvent().getCurrentUser(), null);

    }
}