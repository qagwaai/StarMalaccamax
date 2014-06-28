package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.qagwaai.starmalaccamax.client.core.HistoryManager;
import com.qagwaai.starmalaccamax.client.core.Locations;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

public final class ManageClosestMenuClickHandlerImpl implements com.smartgwt.client.widgets.menu.events.ClickHandler {
    private final EventBus eventBus;

    public ManageClosestMenuClickHandlerImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void onClick(final MenuItemClickEvent event) {
        HistoryManager.present(Locations.getClosestAdminPage(), eventBus, new ClosestDTO(), null);

    }
}