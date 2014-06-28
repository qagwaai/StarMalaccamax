package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.core.HistoryManager;
import com.qagwaai.starmalaccamax.client.core.Locations;
import com.qagwaai.starmalaccamax.client.core.LoginWatcher;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public final class PrimerClickHandlerImpl implements ClickHandler {
    private final EventBus eventBus;

    public PrimerClickHandlerImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void onClick(ClickEvent event) {
        HistoryManager.present(Locations.getPrimerPage(), eventBus, LoginWatcher.getInstance().getLastEvent()
            .getCurrentUser(), null);
    }
}
