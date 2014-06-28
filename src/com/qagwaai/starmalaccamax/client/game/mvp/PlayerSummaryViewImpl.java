/**
 * PlayerSummaryView.java
 * Created by pgirard at 1:33:58 PM on Aug 31, 2010
 * in the com.qagwaai.starmalaccamax.client.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import java.util.Date;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.qagwaai.starmalaccamax.client.core.LoginWatcher;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarPresenterImpl;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarViewImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.widget.PieChartWidget;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.calendar.Calendar;
import com.smartgwt.client.widgets.calendar.CalendarEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;

/**
 * @author pgirard
 * 
 */
public class PlayerSummaryViewImpl extends AbstractView<PlayerSummaryPresenter> implements PlayerSummaryView {

    /**
	 * 
	 */
    private VLayout baseLayout = new VLayout(15);
    /**
	 * 
	 */
    private VerticalPanel chartResourcePanel = new VerticalPanel();
    /**
	 * 
	 */
    private VerticalPanel chartCostsPanel = new VerticalPanel();
    /**
     * 
     */
    private ListGrid listGrid = new ListGrid();
    /**
     * 
     */
    private LoginBarPresenterImpl loginBarPresenter;
    /**
	 * 
	 */
    private Menu listContextMenu = new Menu();

    /**
	 * 
	 */
    private IButton captainPropertiesButton = new IButton("Captain Properties");
    /**
	 * 
	 */
    private IButton shipPropertiesButton = new IButton("Ship Properties");
    /**
	 * 
	 */
    private IButton planetPropertiesButton = new IButton("Planet Properties");
    /**
	 * 
	 */
    private IButton destinationsButton = new IButton("Destinations");
    /**
	 * 
	 */
    private IButton visualizationButton = new IButton("Visualization");
    /**
	 * 
	 */
    private IButton refreshButton = new IButton("Refresh");

    /**
	 * 
	 */
    private MenuItem captainPropertiesMenu = new MenuItem("Captain Properties");
    /**
	 * 
	 */
    private MenuItem shipPropertiesMenu = new MenuItem("Ship Properties");
    /**
	 * 
	 */
    private MenuItem planetPropertiesMenu = new MenuItem("Planet Properties");
    /**
	 * 
	 */
    private MenuItem destinationsMenu = new MenuItem("Destinations");
    /**
	 * 
	 */
    private MenuItem visualizationMenu = new MenuItem("Visualization");
    /**
	 * 
	 */
    private MenuItem addShipMenu = new MenuItem("Add Sample Ship");
    /**
	 * 
	 */
    private MenuItem addCargoItemMenu = new MenuItem("Add Cargo to Ship");

    /**
	 * 
	 */
    private PieChartWidget resourcesPie;

    /**
	 * 
	 */
    private PieChartWidget costsPie;

    /**
	 * 
	 */
    private Calendar eventCalendar = new Calendar() {
        @Override
        protected String getDayBodyHTML(final Date date, final CalendarEvent[] events, final Calendar calendar,
            final int rowNum, final int colNum) {
            @SuppressWarnings("deprecation") String returnStr = date.getDate() + "";
            if (events != null && events.length > 0) {
                returnStr += imgHTML("icons/16/approved.png", 16, 16, "image", "style='margin-top:6px'", null);
            }
            return returnStr;
        }
    };

    /**
	 * 
	 */
    public PlayerSummaryViewImpl() {
        super();
    }

    /**
     * 
     * @param location
     *            the location of the page - used for history
     */
    public PlayerSummaryViewImpl(final String location) {
        this();
        setLocation(location);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Widget asWidget() {
        return baseLayout;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#getAddCargoItemMenu()
	 */
    @Override
	public final MenuItem getAddCargoItemMenu() {
        return addCargoItemMenu;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#getAddShipMenu()
	 */
    @Override
	public final MenuItem getAddShipMenu() {
        return addShipMenu;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#getCaptainPropertiesButton()
	 */
    @Override
	public final IButton getCaptainPropertiesButton() {
        return captainPropertiesButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#getCaptainPropertiesMenuItem()
	 */
    @Override
	public final MenuItem getCaptainPropertiesMenuItem() {
        return captainPropertiesMenu;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#getCostsPie()
	 */
    @Override
	public final PieChartWidget getCostsPie() {
        return costsPie;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#getDestinationsButton()
	 */
    @Override
	public final IButton getDestinationsButton() {
        return destinationsButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#getDestinationsMenu()
	 */
    @Override
	public final MenuItem getDestinationsMenu() {
        return destinationsMenu;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#getListContextMenu()
	 */
    @Override
	public final Menu getListContextMenu() {
        return listContextMenu;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#getListGrid()
	 */
    @Override
	public final ListGrid getListGrid() {
        return listGrid;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#getPlanetPropertiesButton()
	 */
    @Override
	public final IButton getPlanetPropertiesButton() {
        return planetPropertiesButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#getPlanetPropertiesMenu()
	 */
    @Override
	public final MenuItem getPlanetPropertiesMenu() {
        return planetPropertiesMenu;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#getRefreshButton()
	 */
    @Override
	public final IButton getRefreshButton() {
        return refreshButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#getResourcesPie()
	 */
    @Override
	public final PieChartWidget getResourcesPie() {
        return resourcesPie;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Canvas getRootCanvas() {
        return baseLayout;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#getShipPropertiesButton()
	 */
    @Override
	public final IButton getShipPropertiesButton() {
        return shipPropertiesButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#getShipPropertiesMenu()
	 */
    @Override
	public final MenuItem getShipPropertiesMenu() {
        return shipPropertiesMenu;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void layout() {
        baseLayout.setWidth("100%");
        loginBarPresenter =
            new LoginBarPresenterImpl(this.getPresenter().getEventBus(), new LoginBarViewImpl(), new UserDTO());

        /********************* View Label **********************/

        HTMLPane header = new HTMLPane();
        header.setWidth100();
        header.setHeight(15);
        header.setContents("<center><b>Fleet Summary</b></center>");

        /********************* Captains Grid *******************/
        SectionStack sectionStack = new SectionStack();
        sectionStack.setWidth100();
        sectionStack.setHeight(160);

        String title = Canvas.imgHTML("silk/world.png") + " Captains";
        SectionStackSection section = new SectionStackSection(title);

        section.setCanCollapse(false);
        section.setExpanded(true);

        listGrid.setWidth100();
        listGrid.setHeight(100);
        listGrid.setShowEmptyMessage(true);
        // captainListGrid.setAutoFetchData(true);
        // Criteria criteria = new Criteria();

        // limit the captain listing to only those of the user current logged in
        // criteria.addCriteria(CaptainRecord.OWNER_ID,
        // String.valueOf(LoginWatcher.getInstance().getLastEvent().getCurrentUser().getId()));
        // captainListGrid.setCriteria(criteria);
        // captainListGrid.setDataSource(getPresenter().getCaptainDataSource());
        listGrid.addSelectionChangedHandler(new SelectionChangedHandler() {

            @Override
            public void onSelectionChanged(SelectionEvent event) {
                ListGridRecord record = event.getSelectedRecord();
                if (record == null) {
                    captainPropertiesButton.disable();
                    shipPropertiesButton.disable();
                    planetPropertiesButton.disable();
                    destinationsButton.disable();
                    visualizationButton.disable();
                } else {
                    captainPropertiesButton.enable();
                    shipPropertiesButton.enable();
                    planetPropertiesButton.enable();
                    destinationsButton.enable();
                    visualizationButton.enable();
                }
            }
        });
        // captainListGrid.setDataPageSize(50);
        ListGridField captainNameField = new ListGridField(PlayerSummaryPresenterImpl.CAPTAIN_NAME, "Captain", 150);
        // ListGridField colorField = new ListGridField(CaptainRecord.COLOR);
        ListGridField shipNameField = new ListGridField(PlayerSummaryPresenterImpl.SHIP_NAME, "Ship Name", 100);
        ListGridField shipLocationField = new ListGridField(PlayerSummaryPresenterImpl.SHIP_LOCATION, "Location");
        ListGridField shipStatusField = new ListGridField(PlayerSummaryPresenterImpl.SHIP_STATUS, "Status");
        ListGridField actionsField = new ListGridField(PlayerSummaryPresenterImpl.ACTIONS, "Actions");

        listGrid.setFields(captainNameField, shipNameField, shipLocationField, shipStatusField, actionsField);

        HLayout buttonBar = new HLayout();
        buttonBar.setHeight(25);
        buttonBar.setWidth100();
        captainPropertiesButton.disable();
        shipPropertiesButton.disable();
        planetPropertiesButton.disable();
        destinationsButton.disable();
        visualizationButton.disable();
        refreshButton.setIcon("icons/16/arrow_refresh.png");

        buttonBar.addMember(captainPropertiesButton);
        buttonBar.addMember(shipPropertiesButton);
        buttonBar.addMember(planetPropertiesButton);
        buttonBar.addMember(destinationsButton);
        buttonBar.addMember(visualizationButton);
        buttonBar.addMember(refreshButton);
        section.setItems(listGrid, buttonBar);
        sectionStack.setSections(section);

        /**************************** Charts **********************/
        HLayout chartLayout = new HLayout();
        chartResourcePanel.setWidth("400px");
        chartResourcePanel.setBorderWidth(1);
        chartCostsPanel.setWidth("400px");
        chartCostsPanel.setBorderWidth(1);

        chartLayout.addMember(chartResourcePanel);
        chartLayout.addMember(chartCostsPanel);
        chartLayout.setHeight(250);

        listContextMenu.setShowShadow(true);
        listContextMenu.setShadowDepth(10);
        if (LoginWatcher.getInstance().getLastEvent().getCurrentUser().isAdmin()) {
            listContextMenu.setItems(captainPropertiesMenu, shipPropertiesMenu, planetPropertiesMenu, destinationsMenu,
                visualizationMenu, addShipMenu, addCargoItemMenu);
        } else {
            listContextMenu.setItems(captainPropertiesMenu, shipPropertiesMenu, planetPropertiesMenu, destinationsMenu,
                visualizationMenu);
        }

        VisualizationUtils.loadVisualizationApi(new Runnable() {

            @Override
            public void run() {
                // chartResourcePanel.getElement().getStyle().setPropertyPx("margin",
                // 15);
                resourcesPie = new PieChartWidget("Currently Owned Resources");
                chartResourcePanel.add(resourcesPie);
                costsPie = new PieChartWidget("Current Costs");
                chartCostsPanel.add(costsPie);
            }

        }, PieChart.PACKAGE);

        eventCalendar.setWidth(500);
        eventCalendar.setHeight(220);
        eventCalendar.setShowDayView(false);
        eventCalendar.setShowWeekView(false);
        eventCalendar.setShowOtherDays(false);
        eventCalendar.setShowDayHeaders(false);
        eventCalendar.setShowDatePickerButton(false);
        eventCalendar.setShowAddEventButton(false);
        eventCalendar.setDisableWeekends(false);
        eventCalendar.setShowDateChooser(false);
        eventCalendar.setCanCreateEvents(false);

        baseLayout.addMember(loginBarPresenter.getView().asWidget());
        baseLayout.addMember(header);
        baseLayout.addMember(sectionStack);
        baseLayout.addMember(chartLayout);
        baseLayout.addMember(eventCalendar);

    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#showContextMenu(com.smartgwt.client.widgets.grid.events.RowContextClickEvent)
	 */
    @Override
	public final void showContextMenu(final RowContextClickEvent event) {
        listContextMenu.setLeft(event.getX());
        listContextMenu.setTop(event.getY());
        listContextMenu.show();
        event.cancel();
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#getEventCalendar()
	 */
    @Override
	public final Calendar getEventCalendar() {
        return eventCalendar;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#getVisualizationButton()
	 */
    @Override
	public IButton getVisualizationButton() {
        return visualizationButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView#getVisualizationMenu()
	 */
    @Override
	public MenuItem getVisualizationMenu() {
        return visualizationMenu;
    }

}
