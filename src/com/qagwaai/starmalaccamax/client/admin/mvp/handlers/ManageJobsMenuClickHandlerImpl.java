package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.qagwaai.starmalaccamax.client.core.HistoryManager;
import com.qagwaai.starmalaccamax.client.core.Locations;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.shared.model.JobDTO;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * 
 * @author pgirard
 * 
 */
public final class ManageJobsMenuClickHandlerImpl implements com.smartgwt.client.widgets.menu.events.ClickHandler {
    /**
	 * 
	 */
    private EventBus eventBus;

    /**
     * 
     * @param eventBus
     *            the bus to publish events on
     */
    public ManageJobsMenuClickHandlerImpl(final EventBus eventBus) {
        this.eventBus = eventBus;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final MenuItemClickEvent event) {
        HistoryManager.present(Locations.getJobAdminPage(), eventBus, new JobDTO(), null);
    }
}