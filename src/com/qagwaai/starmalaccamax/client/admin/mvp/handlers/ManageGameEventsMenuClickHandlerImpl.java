package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.qagwaai.starmalaccamax.client.admin.mvp.GameEventAdminPresenterImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.GameEventAdminViewImpl;
import com.qagwaai.starmalaccamax.client.core.HistoryManager;
import com.qagwaai.starmalaccamax.client.core.Locations;
import com.qagwaai.starmalaccamax.client.core.mvp.Presenter;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * 
 * @author pgirard
 * 
 */
public final class ManageGameEventsMenuClickHandlerImpl implements com.smartgwt.client.widgets.menu.events.ClickHandler {
    /**
	 * 
	 */
    private EventBus eventBus;

    /**
     * 
     * @param eventBus
     *            the bus to publish events on
     */
    public ManageGameEventsMenuClickHandlerImpl(final EventBus eventBus) {
        this.eventBus = eventBus;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final MenuItemClickEvent event) {
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            @Override
            public void execute() {
                HistoryManager.hideCurrentPresenter();
                Presenter presenter = HistoryManager.getFromCache(Locations.getGameEventAdminPage());
                if (presenter == null) {
                    presenter =
                        new GameEventAdminPresenterImpl(eventBus, new GameEventAdminViewImpl(Locations
                            .getGameEventAdminPage()), new GameEventDTO());
                    presenter.renderView();
                } else {
                    presenter.showView();
                }
                HistoryManager.pushHistory(presenter, Locations.getGameEventAdminPage());
            }
        });
    }
}
