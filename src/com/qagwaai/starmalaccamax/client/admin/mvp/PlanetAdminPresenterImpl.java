/**
 * UserAdminPresenter.java
 * Created by pgirard at 1:28:40 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.admin.mvp;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.client.admin.callbacks.GetMarketForPlanetAsyncCallback;
import com.qagwaai.starmalaccamax.client.admin.callbacks.GetShipsForUserAsyncCallback;
import com.qagwaai.starmalaccamax.client.admin.callbacks.UpdateShipAsyncCallback;
import com.qagwaai.starmalaccamax.client.admin.constants.AdminConstants;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.AdminAddNewButtonClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.AdminSaveButtonClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.AdminViewRecordClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.BrowseBulkLoadChangeHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.ClearFiltersClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.DeleteClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.ExportClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.ListGridDataArrivedHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.RefreshClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.RowContextClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.SelectAllClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.util.HTML5BulkLoadCommand;
import com.qagwaai.starmalaccamax.client.admin.util.HTML5BulkPlanetLoadCommand;
import com.qagwaai.starmalaccamax.client.core.LoginWatcher;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.data.DataSourceFactory;
import com.qagwaai.starmalaccamax.client.data.PlanetDS;
import com.qagwaai.starmalaccamax.client.data.PlanetRecord;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.GetMarketForPlanet;
import com.qagwaai.starmalaccamax.client.service.action.GetShipsForUser;
import com.qagwaai.starmalaccamax.client.service.action.UpdateShip;
import com.qagwaai.starmalaccamax.shared.InvalidParameterException;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.Planet;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * @author pgirard
 * 
 */
public final class PlanetAdminPresenterImpl extends AbstractPresenter<PlanetAdminView, Planet> implements
    PlanetAdminPresenter {

    /**
     * 
     * @author pgirard
     * 
     */
    private static final class MarketsForPlanetClickHandlerImplementation implements
        com.smartgwt.client.widgets.menu.events.ClickHandler {

        /**
		 * 
		 */
        private final EventBus eventBus;
        /**
		 * 
		 */
        private final PlanetAdminViewImpl view;

        /**
         * 
         * @param eventBus
         *            the bus to publish on
         * @param view
         *            the view
         */
        private MarketsForPlanetClickHandlerImplementation(final EventBus eventBus, final PlanetAdminViewImpl view) {
            this.eventBus = eventBus;
            this.view = view;
        }

        /**
         * 
         * {@inheritDoc}
         */
        @Override
        public void onClick(final MenuItemClickEvent event) {

            ServiceFactory.getMarketService().execute(
                new GetMarketForPlanet(((PlanetRecord) view.getSelectedListGridRecord()).toPlanet()),
                new GetMarketForPlanetAsyncCallback(eventBus));

        }
    }

    /**
	 * 
	 */
    private static AdminConstants constants = GWT.create(AdminConstants.class);

    /**
	 * 
	 */
    private DataSource dataSource;

    /**
     * 
     * @param eventBus
     *            the bus to publish events on
     * @param view
     *            the view that this presenter will attach to
     * @param model
     *            the model to present, if any
     */
    public PlanetAdminPresenterImpl(final EventBus eventBus, final PlanetAdminViewImpl view, final Planet model) {
        super(eventBus, view, model);
        // make sure to do the association before calling layout
        view.setPresenter(this);
        try {
            dataSource = DataSourceFactory.get(PlanetDS.DS_TYPE, getEventBus());
        } catch (InvalidParameterException e) {
            getView().alert(PlanetAdminPresenterImpl.constants.dataSourceError());
            return;
        }
        setupView();
    }

    /**
     * 
     * @param eventBus
     *            the bus to publish events on
     * @param view
     *            the view that this presenter will attach to
     * @param model
     *            the model to present, if any
     * @param filter
     *            a filter
     */
    public PlanetAdminPresenterImpl(final EventBus eventBus, final PlanetAdminViewImpl view, final Planet model,
        final Filter filter) {
        super(eventBus, view, model, filter);
        view.setPresenter(this);
        try {
            dataSource = DataSourceFactory.get(PlanetDS.DS_TYPE, getEventBus());
        } catch (InvalidParameterException e) {
            getView().alert(PlanetAdminPresenterImpl.constants.dataSourceError());
            return;
        }
        setFilter(filter);
        setupView();
        Criteria criteria = new Criteria();
        criteria.addCriteria(filter.getField(), filter.getValue());
        getView().setListGridCriteria(criteria);

    }

    /**
     * 
     * @param ship
     *            the ship to update
     * @param planet
     *            the planet location to assign
     */
    @Override
    public void assignLocationToShip(final ShipDTO ship, final PlanetDTO planet) {
        ship.setLocation(planet.getLocation());
        ServiceFactory.getShipService().execute(new UpdateShip(ship), new UpdateShipAsyncCallback(planet, ship));
    }

    /**
     * 
     * {@inheritDoc}
     */

    @Override
    public void destroyView() {
        getView().destroy();

    }

    /**
     * @return the dataSource
     */
    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 
     * {@inheritDoc}
     */

    @Override
    public void hideView() {
        getView().hide();

    }

    /**
     * 
     * {@inheritDoc}
     */

    @Override
    public void renderView() {
        getView().render();
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void setFilter(final Filter filter) {
        super.setFilter(filter);
        if (filter != null) {
            Criteria criteria = new Criteria();
            criteria.addCriteria(filter.getField(), filter.getValue());
            // dataSource.filterData(criteria);
            if (getView().isListGridCreated()) {
                getView().clearListGridCriteria();
                getView().setListGridCriteria(criteria);
            }
        }
    }

    /*
     * private void processFiles(FileList fileList) { for (int i = 0; i <
     * fileList.length(); i++) { File file = fileList.get(i); if (file == null)
     * break; // processFile(file); Scheduler.get().scheduleDeferred(new
     * HTML5BulkPlanetLoadCommand(file, getView(),
     * Integer.valueOf(getView().getRecordsPerTick().getValue()))); } }
     * 
     * private void processFile(File file) { final String fileName =
     * file.getName(); final String fileType = file.getType();
     * 
     * FileReader reader = FileReader.create(); reader.readAsBinaryString(file,
     * new ProgressCallback() {
     * 
     * @Override public void onError(ProgressEvent e) {
     * 
     * }
     * 
     * @Override public void onLoad(ProgressEvent e) { byte[] bytes = new
     * byte[e.getTotal()]; String result = e.getResult(); for (int i = 0; i <
     * bytes.length; i++) { bytes[i] = (byte) result.charAt(i); }
     * Window.alert("Loaded file: " + fileName + " with " + bytes.length +
     * " bytes"); // listener.onLoadFile(fileName, bytes); } }); }
     */

    /**
	 * 
	 */
    private void setupView() {

        getView().layout();

        ((PlanetAdminViewImpl) getView()).getMarketsForPlanetMenuItem().addClickHandler(
            new MarketsForPlanetClickHandlerImplementation(getEventBus(), (PlanetAdminViewImpl) getView()));

        ServiceFactory.getShipService().execute(
            new GetShipsForUser(LoginWatcher.getInstance().getLastEvent().getCurrentUser()),
            new GetShipsForUserAsyncCallback((PlanetAdminViewImpl) getView()));

        HTML5BulkLoadCommand loadCommand = new HTML5BulkPlanetLoadCommand((AdminFileUpload) getView());
        ((AdminFileUpload) getView()).addBrowseChangeHandler(new BrowseBulkLoadChangeHandlerImpl(
            ((AdminFileUpload) getView()), loadCommand));

        getView().addDeleteButtonClickHandler(new DeleteClickHandlerImpl(getView()));
        getView().addRefreshButtonClickHandler(new RefreshClickHandlerImpl(getView()));
        getView().addSelectAllButtonClickHandler(new SelectAllClickHandlerImpl(getView()));
        getView().addExportButtonClickHandler(new ExportClickHandlerImpl(getView()));
        getView().addClearFiltersButtonClickHandler(new ClearFiltersClickHandlerImpl(getView()));
        getView().addListGridRowContextClickHandler(new RowContextClickHandlerImpl(getView()));
        getView().addListGridRecordClickHandler(new AdminViewRecordClickHandlerImpl(getView()));
        getView().addAddNewButtonClickHandler(new AdminAddNewButtonClickHandlerImpl(getView()));
        getView().addSaveButtonClickHandler(new AdminSaveButtonClickHandlerImpl(getView()));
        getView().addListGridDataArrivedHandler(new ListGridDataArrivedHandlerImpl(getView()));
    }

    /**
     * 
     * {@inheritDoc}
     */

    @Override
    public void showView() {
        getView().show();

    }

}
