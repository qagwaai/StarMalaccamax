package com.qagwaai.starmalaccamax.client.core.mvp.handlers;

import com.qagwaai.starmalaccamax.client.core.HistoryManager;
import com.qagwaai.starmalaccamax.client.core.Locations;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.shared.model.SolarSystem;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

public final class ViewSolarSystem1049MenuClickHandlerImpl implements
    com.smartgwt.client.widgets.menu.events.ClickHandler {
    private final EventBus eventBus;

    public ViewSolarSystem1049MenuClickHandlerImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void onClick(final MenuItemClickEvent event) {
        SolarSystem ss = new SolarSystemDTO();
        ss.setId(Long.valueOf(1049));
        HistoryManager.present(Locations.getSolarSystemVisualizationPage(), eventBus, ss, null);

    }
}