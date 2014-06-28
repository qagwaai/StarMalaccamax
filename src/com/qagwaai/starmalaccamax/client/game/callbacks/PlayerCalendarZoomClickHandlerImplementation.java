package com.qagwaai.starmalaccamax.client.game.callbacks;

import com.qagwaai.starmalaccamax.client.data.GameEventDS;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.GameEventWindowPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.GameEventWindowViewImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerCalendarView;
import com.smartgwt.client.widgets.calendar.CalendarEvent;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;




/**
 * 
 * @author pgirard
 * 
 */
public final class PlayerCalendarZoomClickHandlerImplementation implements com.smartgwt.client.widgets.menu.events.ClickHandler {
    /**
	 * 
	 */
    private final EventBus eventBus;
    /**
	 * 
	 */
    private final PlayerCalendarView view;

    /**
     * 
     * @param eventBus
     *            where to publish events
     * @param view
     *            the associated view
     */
    public PlayerCalendarZoomClickHandlerImplementation(final EventBus eventBus, final PlayerCalendarView view) {
        this.eventBus = eventBus;
        this.view = view;
    }

    @Override
    public void onClick(final MenuItemClickEvent event) {
        // TODO fix
        CalendarEvent[] events = view.getEventCalendar().getData();
        GameEventWindowPresenterImpl presenter =
            new GameEventWindowPresenterImpl(eventBus, new GameEventWindowViewImpl(),
                GameEventDS.fromCalendarEvent(events[0]));
        presenter.showView();
    }
}
