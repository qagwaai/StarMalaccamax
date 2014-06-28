/**
 * CalendarWindowView.java
 * Created by pgirard at 11:50:58 AM on Oct 26, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.calendar.Calendar;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author pgirard
 * 
 */
public class CalendarWindowViewImpl extends AbstractView<CalendarWindowPresenter> implements CalendarWindowView {

    private static final int CALENDAR_CONTROL_HEIGHT = 470;

    private static final int VIEW_HEIGHT = 550;

    private static final int VIEW_WIDTH = 600;

    private static final int VIEW_ROOT_MARGIN = 10;

    /**
	 * 
	 */
    private Window rootWindow;

    /**
	 * 
	 */
    private Calendar calendar = new Calendar();

    /**
     * 
     */
    private IButton saveButton = new IButton("Save");

    /**
     * 
     */
    private IButton closeButton = new IButton("Close");

    /**
	 * 
	 */
    public CalendarWindowViewImpl() {
        rootWindow = new Window();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Widget asWidget() {
        return rootWindow;
    }

    /* (non-Javadoc)
     * @see com.qagwaai.starmalaccamax.client.game.mvp.CalendarWindowView#getCloseButton()
     */
    @Override
    public final IButton getCloseButton() {
        return closeButton;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Canvas getRootCanvas() {
        return rootWindow;
    }

    /* (non-Javadoc)
     * @see com.qagwaai.starmalaccamax.client.game.mvp.CalendarWindowView#getSaveButton()
     */
    @Override
    public final IButton getSaveButton() {
        return saveButton;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void layout() {
        VLayout baseLayout = new VLayout(VIEW_ROOT_MARGIN);
        rootWindow.setWidth(VIEW_WIDTH);
        rootWindow.setHeight(VIEW_HEIGHT);
        rootWindow.setTitle("Calendar");
        rootWindow.setIsModal(true);
        rootWindow.setShowModalMask(true);
        rootWindow.setCanDragResize(true);
        rootWindow.setCanDragReposition(true);
        rootWindow.setShowMinimizeButton(false);

        calendar.setWidth100();
        calendar.setHeight(CALENDAR_CONTROL_HEIGHT);
        calendar.setDisableWeekends(false);

        baseLayout.addMember(calendar);
        baseLayout.addMember(closeButton);

        rootWindow.addItem(baseLayout);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void render() {
        rootWindow.setAutoCenter(true);
        rootWindow.show();
    }

    /* (non-Javadoc)
     * @see com.qagwaai.starmalaccamax.client.game.mvp.CalendarWindowView#setTitle(java.lang.String)
     */
    @Override
    public final void setTitle(final String windowTitle) {
        rootWindow.setTitle(windowTitle);
    }

    /* (non-Javadoc)
     * @see com.qagwaai.starmalaccamax.client.game.mvp.CalendarWindowView#getCalendar()
     */
    @Override
    public final Calendar getCalendar() {
        return calendar;
    }

}
