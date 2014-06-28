package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.core.HistoryManager;
import com.qagwaai.starmalaccamax.client.core.Locations;
import com.qagwaai.starmalaccamax.client.core.LoginWatcher;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * 
 * @author pgirard
 * 
 */
public final class PlayerSummaryClickHandlerImplementation implements ClickHandler {
    /**
	 * 
	 */
    private final EventBus eventBus;

    /**
     * 
     * @param eventBus
     *            the bus
     */
    public PlayerSummaryClickHandlerImplementation(final EventBus eventBus) {
        this.eventBus = eventBus;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final ClickEvent event) {
        HistoryManager.present(Locations.getPlayerSummaryPage(), eventBus, LoginWatcher.getInstance()
            .getLastEvent().getCurrentUser(), null);
    }
}
