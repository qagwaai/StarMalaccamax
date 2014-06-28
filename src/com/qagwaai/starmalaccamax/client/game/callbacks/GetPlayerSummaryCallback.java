package com.qagwaai.starmalaccamax.client.game.callbacks;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.client.game.mvp.PlayerCalendarView;
import com.qagwaai.starmalaccamax.client.service.action.GetPlayerSummaryPageResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.GameEvent;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;
import com.smartgwt.client.widgets.calendar.CalendarEvent;


/**
 * 
 * @author pgirard
 * 
 */
public final class GetPlayerSummaryCallback extends BaseAsyncCallback<GetPlayerSummaryPageResponse> {
    /**
	 * 
	 */
    private final PlayerCalendarView view;

    /**
     * 
     * @param view
     *            the view
     */
    public GetPlayerSummaryCallback(final PlayerCalendarView view) {
        super();
        this.view = view;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onFailure(final Throwable caught) {
        super.onFailure(caught);
        view.alert("Could not load player summary");
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onSuccess(final GetPlayerSummaryPageResponse result) {
        super.onSuccess(result);
        ArrayList<GameEventDTO> events = result.getEvents();
        CalendarEvent[] eventRecords = new CalendarEvent[events.size()];
        if (events.size() > 0) {
            int i = 0;
            for (GameEvent event : events) {
                if (event != null) {
                    eventRecords[i++] =
                        new CalendarEvent(i, event.getShortDescription(), event.getFullDescription(),
                            event.getStartDateTime(), event.getEndDateTime(), false);
                }
            }
            view.getEventCalendar().setData(eventRecords);
        }
    }
}
