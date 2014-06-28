/**
 * PlayerCalendarView.java
 * Created by pgirard at 1:11:14 PM on Dec 2, 2010
 * in the com.qagwaai.starmalaccamax.client.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarPresenterImpl;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarViewImpl;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.calendar.Calendar;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;

/**
 * @author pgirard
 * 
 */
public class PlayerCalendarViewImpl extends AbstractView<PlayerCalendarPresenter> implements PlayerCalendarView {
    /**
	 * 
	 */
    private VLayout baseLayout = new VLayout(15);
    /**
     * 
     */
    private LoginBarPresenterImpl loginBarPresenter;
    /**
	 * 
	 */
    private Menu calendarContextMenu = new Menu();
    /**
	 * 
	 */
    private MenuItem zoomCalendarMenu = new MenuItem("Zoom");
    /**
	 * 
	 */
    private Calendar eventCalendar = new Calendar();

    /**
	 * 
	 */
    public PlayerCalendarViewImpl() {
        super();
    }

    /**
     * 
     * @param location
     *            the location of the page - used for history
     */
    public PlayerCalendarViewImpl(final String location) {
        this();
        setLocation(location);
    }

    @Override
    public final Widget asWidget() {
        return baseLayout;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerCalendarView#getCalendarContextMenu()
	 */
    @Override
	public final Menu getCalendarContextMenu() {
        return calendarContextMenu;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerCalendarView#getEventCalendar()
	 */
    @Override
	public final Calendar getEventCalendar() {
        return eventCalendar;
    }

    @Override
    public final Canvas getRootCanvas() {
        return baseLayout;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerCalendarView#getZoomCalendarMenu()
	 */
    @Override
	public final MenuItem getZoomCalendarMenu() {
        return zoomCalendarMenu;
    }

    @Override
    public final void layout() {
        baseLayout.setWidth100();
        baseLayout.setHeight100();
        loginBarPresenter =
            new LoginBarPresenterImpl(this.getPresenter().getEventBus(), new LoginBarViewImpl(), new UserDTO());

        // eventCalendar.setWidth(500);
        eventCalendar.setHeight(500);
        /*
         * eventCalendar.setShowDayView(false);
         * eventCalendar.setShowWeekView(false);
         * eventCalendar.setShowOtherDays(false);
         * eventCalendar.setShowDayHeaders(false);
         * eventCalendar.setShowDatePickerButton(false);
         * eventCalendar.setShowAddEventButton(false);
         * eventCalendar.setDisableWeekends(false);
         * eventCalendar.setShowDateChooser(false);
         */
        eventCalendar.setCanCreateEvents(false);

        calendarContextMenu.setShowShadow(true);
        calendarContextMenu.setShadowDepth(10);
        calendarContextMenu.setItems(zoomCalendarMenu);
        eventCalendar.setContextMenu(calendarContextMenu);

        HLayout calendarLayout = new HLayout();
        calendarLayout.setAlign(Alignment.CENTER);
        calendarLayout.setWidth100();
        calendarLayout.addMember(eventCalendar);

        baseLayout.addMember(loginBarPresenter.getView().asWidget());
        baseLayout.addMember(calendarLayout);

    }

}
