package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.qagwaai.starmalaccamax.client.core.HistoryManager;
import com.qagwaai.starmalaccamax.client.core.Locations;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.shared.model.ShipTypeDTO;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * 
 * @author pgirard
 * 
 */
public final class ManageShipTypesMenuClickHandlerImpl implements com.smartgwt.client.widgets.menu.events.ClickHandler {
    /**
	 * 
	 */
    private EventBus eventBus;

    /**
     * 
     * @param eventBus
     *            the bus to publish events on
     */
    public ManageShipTypesMenuClickHandlerImpl(final EventBus eventBus) {
        this.eventBus = eventBus;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final MenuItemClickEvent event) {
        HistoryManager.present(Locations.getShipTypeAdminPage(), eventBus, new ShipTypeDTO(), null);
    }
}
