package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.View;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.calendar.Calendar;

public interface CalendarWindowView extends View {

    /**
     * @return the closeButton
     */
    IButton getCloseButton();

    /**
     * @return the saveButton
     */
    IButton getSaveButton();

    /**
     * 
     * @param windowTitle
     *            the title of the window
     */
    void setTitle(final String windowTitle);

    /**
     * @return the calendar
     */
    Calendar getCalendar();

}