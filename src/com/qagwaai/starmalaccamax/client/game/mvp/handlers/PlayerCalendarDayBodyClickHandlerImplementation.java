package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.PlayerCalendarViewImpl;
import com.smartgwt.client.widgets.calendar.CalendarEvent;
import com.smartgwt.client.widgets.calendar.events.DayBodyClickEvent;
import com.smartgwt.client.widgets.calendar.events.DayBodyClickHandler;


/**
 * 
 * @author pgirard
 * 
 */
public final class PlayerCalendarDayBodyClickHandlerImplementation implements DayBodyClickHandler {
    /**
	 * 
	 */
    private final PlayerCalendarViewImpl view;

    /**
     * 
     * @param view
     *            the view
     */
    public PlayerCalendarDayBodyClickHandlerImplementation(final PlayerCalendarViewImpl view) {
        this.view = view;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void onDayBodyClick(final DayBodyClickEvent event) {
        String nameStr = "";
        CalendarEvent[] events = event.getEvents();
        if (events.length == 0) {
            nameStr = "No events";
        } else {
            for (CalendarEvent calEvent : events) {
                nameStr += calEvent.getName() + "<br/>";
            }
        }
        view.say("Calendar", nameStr);
    }
}