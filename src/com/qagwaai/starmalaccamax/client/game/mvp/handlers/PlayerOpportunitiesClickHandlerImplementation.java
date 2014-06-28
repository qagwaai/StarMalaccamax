package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.core.HistoryManager;
import com.qagwaai.starmalaccamax.client.core.Locations;
import com.qagwaai.starmalaccamax.client.core.LoginWatcher;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * 
 */
public final class PlayerOpportunitiesClickHandlerImplementation implements ClickHandler {
    /**
	 * 
	 */
    private final EventBus eventBus;

    /**
     * 
     * @param eventBus
     *            the bus to publish on
     */
    public PlayerOpportunitiesClickHandlerImplementation(final EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void onClick(final ClickEvent event) {
        HistoryManager.present(Locations.getPlayerOpportunitiesPage(), eventBus, LoginWatcher.getInstance()
            .getLastEvent().getCurrentUser(), null);

    }
}