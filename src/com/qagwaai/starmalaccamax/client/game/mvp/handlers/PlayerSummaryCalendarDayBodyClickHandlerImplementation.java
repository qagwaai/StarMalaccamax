package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.CalendarWindowPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.CalendarWindowViewImpl;
import com.qagwaai.starmalaccamax.shared.model.CalendarEvents;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.calendar.CalendarEvent;
import com.smartgwt.client.widgets.calendar.events.DayBodyClickEvent;
import com.smartgwt.client.widgets.calendar.events.DayBodyClickHandler;


/**
 * @author pgirard
 * 
 */
public final class PlayerSummaryCalendarDayBodyClickHandlerImplementation implements DayBodyClickHandler {
    /**
	 * 
	 */
    private final EventBus eventBus;

    /**
     * @param eventBus
     *            the bus to publish events on
     */
    public PlayerSummaryCalendarDayBodyClickHandlerImplementation(final EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void onDayBodyClick(final DayBodyClickEvent event) {
        CalendarEvent[] events = event.getEvents();
        if (events.length == 0) {
            SC.say("No events");
        } else {
            CalendarEvents calEvents = new CalendarEvents();
            for (int i = 0; i < events.length; i++) {
                calEvents.add(events[i]);
            }
            CalendarWindowPresenterImpl calendarPresenter =
                new CalendarWindowPresenterImpl(eventBus, new CalendarWindowViewImpl(), calEvents);
            calendarPresenter.renderView();
        }
    }
}
