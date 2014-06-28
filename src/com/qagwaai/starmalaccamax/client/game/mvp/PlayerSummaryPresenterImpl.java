/**
 * PlayerSummaryPresenter.java
 * Created by pgirard at 1:34:21 PM on Aug 31, 2010
 * in the com.qagwaai.starmalaccamax.client.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.HistoryManager;
import com.qagwaai.starmalaccamax.client.core.Locations;
import com.qagwaai.starmalaccamax.client.core.LoginWatcher;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.data.CaptainDS;
import com.qagwaai.starmalaccamax.client.data.DataSourceFactory;
import com.qagwaai.starmalaccamax.client.event.CaptainUpdatedEvent;
import com.qagwaai.starmalaccamax.client.event.CaptainUpdatedHandler;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.callbacks.PlayerSummaryGetPlayerSummaryCallback;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerOppRefreshClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerSummaryAddCargoMenuClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerSummaryAddShipMenuClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerSummaryCalendarDayBodyClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerSummaryCaptainPropertiesButtonClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerSummaryCaptainPropertiesMenuClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerSummaryDestinationsButtonClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerSummaryDestinationsMenuClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerSummaryRowContextClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerSummaryShipPropertiesButtonClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerSummaryShipPropertiesMenuClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerSummaryShowPlanetPropertiesButtonClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerSummaryShowPlanetPropertiesMenuClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.GetPlanet;
import com.qagwaai.starmalaccamax.client.service.action.GetPlanetResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetPlayerSummaryPage;
import com.qagwaai.starmalaccamax.client.service.action.GetSolarSystem;
import com.qagwaai.starmalaccamax.client.service.action.GetSolarSystemResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetSolarSystemResponseInRPC;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.InvalidParameterException;
import com.qagwaai.starmalaccamax.shared.MimeType;
import com.qagwaai.starmalaccamax.shared.model.Captain;
import com.qagwaai.starmalaccamax.shared.model.Location;
import com.qagwaai.starmalaccamax.shared.model.Ship;
import com.qagwaai.starmalaccamax.shared.model.User;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class PlayerSummaryPresenterImpl extends AbstractPresenter<PlayerSummaryView, User> implements CaptainUpdatedHandler, PlayerSummaryPresenter {


    /**
	 * 
	 */
    public static final String CAPTAIN_NAME = "captainName";

    /**
	 * 
	 */
    public static final String SHIP_NAME = "shipName";

    /**
	 * 
	 */
    public static final String SHIP_LOCATION = "shipLocation";

    /**
	 * 
	 */
    public static final String SHIP_STATUS = "shipStatus";

    /**
	 * 
	 */
    public static final String ACTIONS = "actions";

    /**
	 * 
	 */
    public static final String SHIP_ID = "shipId";

    /**
	 * 
	 */
    public static final String CAPTAIN_ID = "captainId";

    /**
	 * 
	 */
    public static final String CAPTAIN_OBJ = "captainObj";

    /**
	 * 
	 */
    public static final String SHIP_OBJ = "shipObj";

    /**
	 * 
	 */
    public static final String LOCATION_OBJ = "locationObj";

    /**
	 * 
	 */
    public static final String GAME_EVENT_OBJ = "gameEventObj";

    /**
	 * 
	 */
    private DataSource captainDataSource;

    /**
     * 
     * @param eventBus
     *            the bus to publish events on
     * @param view
     *            the view that this presenter will attach to
     * @param model
     *            the model to present, if any
     */
    public PlayerSummaryPresenterImpl(final EventBus eventBus, final PlayerSummaryViewImpl view, final User model) {
        super(eventBus, view, model);
        view.setPresenter(this);
        view.layout();

        loadSummaryListGrid();
        try {
            captainDataSource = DataSourceFactory.get(CaptainDS.DS_TYPE, eventBus);
        } catch (InvalidParameterException e) {
            view.alert("Could not load captain data source");
            return;
        }

        eventBus.addHandler(CaptainUpdatedEvent.getType(), this);
        view.getListGrid().addRowContextClickHandler(new PlayerSummaryRowContextClickHandlerImplementation(view));
        view.getCaptainPropertiesMenuItem().addClickHandler(new PlayerSummaryCaptainPropertiesMenuClickHandlerImplementation(this));
        view.getCaptainPropertiesButton().addClickHandler(new PlayerSummaryCaptainPropertiesButtonClickHandlerImplementation(this));
        view.getAddShipMenu().addClickHandler(new PlayerSummaryAddShipMenuClickHandlerImplementation(view, this));
        view.getRefreshButton().addClickHandler(new PlayerOppRefreshClickHandlerImplementation(view, this));
        view.getShipPropertiesMenu().addClickHandler(new PlayerSummaryShipPropertiesMenuClickHandlerImplementation(this));
        view.getShipPropertiesButton().addClickHandler(new PlayerSummaryShipPropertiesButtonClickHandlerImplementation(this));
        view.getPlanetPropertiesMenu().addClickHandler(new PlayerSummaryShowPlanetPropertiesMenuClickHandlerImplementation(this));
        view.getPlanetPropertiesButton().addClickHandler(new PlayerSummaryShowPlanetPropertiesButtonClickHandlerImplementation(this));
        view.getDestinationsButton().addClickHandler(new PlayerSummaryDestinationsButtonClickHandlerImplementation(this));

        view.getDestinationsMenu().addClickHandler(new PlayerSummaryDestinationsMenuClickHandlerImplementation(this));

        view.getAddCargoItemMenu().addClickHandler(new PlayerSummaryAddCargoMenuClickHandlerImplementation(view, this));

        view.getEventCalendar().addDayBodyClickHandler(new PlayerSummaryCalendarDayBodyClickHandlerImplementation(eventBus));

        view.getVisualizationButton().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                showSolarSystemVisualization();
            }
        });
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void destroyView() {
        getView().destroy();

    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenter#getCaptainDataSource()
	 */
    @Override
	public final DataSource getCaptainDataSource() {
        return captainDataSource;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void hideView() {
        getView().hide();

    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenter#loadSummaryListGrid()
	 */
    @Override
	public void loadSummaryListGrid() {
        getView().getListGrid().setEmptyMessage("Loading...");
        ServiceFactory.getPlayerSummaryService().execute(
            new GetPlayerSummaryPage(LoginWatcher.getInstance().getLastEvent().getCurrentUser()),
            new PlayerSummaryGetPlayerSummaryCallback(getView()));
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenter#onCaptainUpdated(com.qagwaai.starmalaccamax.client.event.CaptainUpdatedEvent)
	 */
	@Override
    public final void onCaptainUpdated(final CaptainUpdatedEvent event) {
        getView().getListGrid().invalidateCache();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void renderView() {
        getView().render();
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenter#showCaptainProperties()
	 */
    @Override
	public void showCaptainProperties() {
        ListGridRecord record = getView().getListGrid().getSelectedRecord();
        Object objCaptain = record.getAttributeAsObject(PlayerSummaryPresenterImpl.CAPTAIN_OBJ);
        if (objCaptain instanceof Captain) {
            CaptainWindowPresenterImpl captainPresenter =
                new CaptainWindowPresenterImpl(getEventBus(), new CaptainWindowViewImpl(), (Captain) objCaptain);
            captainPresenter.getView().setTitle("Captain " + ((Captain) objCaptain).getName());
            captainPresenter.renderView();
        }
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenter#showSolarSystemVisualization()
	 */
    @Override
	public void showSolarSystemVisualization() {
        ListGridRecord record = getView().getListGrid().getSelectedRecord();
        Object objLocation = record.getAttributeAsObject(PlayerSummaryPresenterImpl.LOCATION_OBJ);
        if (objLocation != null) {
            if (objLocation instanceof Location) {
                Location location = (Location) objLocation;
                if (location.getSolarSystemId() > 0) {
                    ServiceFactory.getSolarSystemService().execute(new GetSolarSystem(MimeType.rpc, location.getSolarSystemId()),
                        new BaseAsyncCallback<GetSolarSystemResponse>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                super.onFailure(caught);
                                getView().say("Error", "Could not load solar system: " + caught.getMessage());
                            }

                            @Override
                            public void onSuccess(GetSolarSystemResponse result) {
                                super.onSuccess(result);

                                HistoryManager.present(Locations.getSolarSystemVisualizationPage(), getEventBus(),
                                    ((GetSolarSystemResponseInRPC) result).getSolarSystem(), null);
                            }
                        });
                }
            }
        }
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenter#showClosestDestinations()
	 */
    @Override
	public void showClosestDestinations() {
        ListGridRecord record = getView().getListGrid().getSelectedRecord();
        Object objLocation = record.getAttributeAsObject(PlayerSummaryPresenterImpl.LOCATION_OBJ);
        // TODO get planet properties
        if (objLocation != null) {
            if (objLocation instanceof Location) {
                Location location = (Location) objLocation;
                ServiceFactory.getPlanetService().execute(new GetPlanet(location.getPlanetId()),
                    new BaseAsyncCallback<GetPlanetResponse>() {
                        @Override
                        public void onFailure(final Throwable caught) {
                            super.onFailure(caught);
                            getView().alert("Could not get planet details: " + caught.getMessage());
                        }

                        @Override
                        public void onSuccess(final GetPlanetResponse result) {
                            super.onSuccess(result);
                            ClosestPlanetWindowPresenterImpl shipPresenter =
                                new ClosestPlanetWindowPresenterImpl(getEventBus(), new ClosestPlanetWindowViewImpl(), result
                                    .getPlanet());
                            shipPresenter.getView().setTitle(
                                "Planets closest to Planet " + result.getPlanet().getName());
                            shipPresenter.renderView();
                        }
                    });

            } else {
                getView().say("Planet", "Location not available : " + objLocation.getClass().getName());
            }
        }
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenter#showPlanetProperties()
	 */
    @Override
	public void showPlanetProperties() {
        ListGridRecord record = getView().getListGrid().getSelectedRecord();
        Object objLocation = record.getAttributeAsObject(PlayerSummaryPresenterImpl.LOCATION_OBJ);
        // TODO get planet properties
        if (objLocation != null) {
            if (objLocation instanceof Location) {
                Location location = (Location) objLocation;
                ServiceFactory.getPlanetService().execute(new GetPlanet(location.getPlanetId()),
                    new BaseAsyncCallback<GetPlanetResponse>() {
                        @Override
                        public void onFailure(final Throwable caught) {
                            super.onFailure(caught);
                            getView().alert("Could not get planet details: " + caught.getMessage());
                        }

                        @Override
                        public void onSuccess(final GetPlanetResponse result) {
                            super.onSuccess(result);
                            PlanetWindowPresenterImpl planetWindowPresenter =
                                new PlanetWindowPresenterImpl(getEventBus(), new PlanetWindowViewImpl(), result.getPlanet());
                            planetWindowPresenter.getView().setTitle("Planet " + result.getPlanet().getName());
                            planetWindowPresenter.renderView();
                        }
                    });

            } else {
                getView().say("Planet", "Location not available : " + objLocation.getClass().getName());
            }
        }

    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenter#showShipProperties()
	 */
    @Override
	public void showShipProperties() {
        ListGridRecord record = getView().getListGrid().getSelectedRecord();
        Object objShip = record.getAttributeAsObject(PlayerSummaryPresenterImpl.SHIP_OBJ);
        if (objShip != null) {
            if (objShip instanceof Ship) {
                ShipWindowPresenterImpl shipPresenter =
                    new ShipWindowPresenterImpl(getEventBus(), new ShipWindowViewImpl(), (Ship) objShip);
                shipPresenter.getView().setTitle("Ship " + ((Ship) objShip).getName());
                shipPresenter.renderView();
            } else {
                SC.say("Ship not stored : " + objShip.getClass().getName());
            }
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void showView() {
        getView().show();

    }

}
