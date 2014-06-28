package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.View;
import com.smartgwt.client.widgets.calendar.Calendar;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;

public interface PlayerCalendarView extends View {

	/**
	 * @return the calendarContextMenu
	 */
	Menu getCalendarContextMenu();

	/**
	 * @return the eventCalendar
	 */
	Calendar getEventCalendar();

	/**
	 * @return the zoomCalendarMenu
	 */
	MenuItem getZoomCalendarMenu();

}