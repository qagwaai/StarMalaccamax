/**
 * UserAdminPresenter.java
 * Created by pgirard at 1:28:40 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.admin.mvp;

import com.google.gwt.core.client.GWT;
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
import com.qagwaai.starmalaccamax.client.admin.util.HTML5BulkSolarSystemLoadCommand;
import com.qagwaai.starmalaccamax.client.core.HistoryManager;
import com.qagwaai.starmalaccamax.client.core.Locations;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.data.DataSourceFactory;
import com.qagwaai.starmalaccamax.client.data.SolarSystemDS;
import com.qagwaai.starmalaccamax.client.data.SolarSystemRecord;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.shared.InvalidParameterException;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;
import com.qagwaai.starmalaccamax.shared.model.SolarSystem;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * @author pgirard
 * 
 */
public final class SolarSystemAdminPresenterImpl extends AbstractPresenter<SolarSystemAdminView, SolarSystem> implements
    SolarSystemAdminPresenter {
    /**
     * 
     * @author pgirard
     * 
     */
    private final class PlanetsForSolarSystemMenuClickHandlerImplementation implements
        com.smartgwt.client.widgets.menu.events.ClickHandler {
        /**
		 * 
		 */
        private final EventBus eventBus;
        /**
		 * 
		 */
        private final SolarSystemAdminViewImpl view;

        /**
         * 
         * @param eventBus
         *            where to publish events
         * @param view
         *            the view associated with this handler
         */
        private PlanetsForSolarSystemMenuClickHandlerImplementation(final EventBus eventBus,
            final SolarSystemAdminViewImpl view) {
            this.eventBus = eventBus;
            this.view = view;
        }

        @Override
        public void onClick(final MenuItemClickEvent event) {
            SolarSystemRecord selected = (SolarSystemRecord) view.getSelectedListGridRecord();
            Filter filter = new SimpleFilterItem();
            filter.setField("solarSystemId");
            filter.setValue(selected.getAttributeAsString(SolarSystemRecord.ID));
            HistoryManager.present(Locations.getPlanetAdminPage(), eventBus, new PlanetDTO(), filter);

        }
    }

    /**
     * @author pgirard
     * 
     */
    private final class VisualizeButtonClickHandlerImplementation implements
        com.smartgwt.client.widgets.events.ClickHandler {
        /**
		 * 
		 */
        private final EventBus eventBus;

        /**
         * @param eventBus
         *            where to publish events
         */
        private VisualizeButtonClickHandlerImplementation(final EventBus eventBus) {
            this.eventBus = eventBus;
        }

        /**
         * 
         * {@inheritDoc}
         */
        @Override
        public void onClick(final com.smartgwt.client.widgets.events.ClickEvent event) {
            SolarSystemRecord selected = (SolarSystemRecord) getView().getSelectedListGridRecord();
            HistoryManager.present(Locations.getSolarSystemVisualizationPage(), eventBus, selected.toSolarSystem(),
                null);

        }
    }

    /**
     * @author pgirard
     * 
     */
    private final class VisualizeMenuClickHandlerImplementation implements
        com.smartgwt.client.widgets.menu.events.ClickHandler {
        /**
		 * 
		 */
        private final EventBus eventBus;

        /**
         * @param eventBus
         *            where to publish events
         */
        private VisualizeMenuClickHandlerImplementation(final EventBus eventBus) {
            this.eventBus = eventBus;
        }

        @Override
        public void onClick(final MenuItemClickEvent event) {
            SolarSystemRecord selected = (SolarSystemRecord) getView().getSelectedListGridRecord();
            HistoryManager.present(Locations.getSolarSystemVisualizationPage(), eventBus, selected.toSolarSystem(),
                null);

        }
    }

    /**
	 * 
	 */
    private AdminConstants constants = GWT.create(AdminConstants.class);

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
    public SolarSystemAdminPresenterImpl(final EventBus eventBus, final SolarSystemAdminViewImpl view,
        final SolarSystem model) {
        super(eventBus, view, model);
        // make sure to do the association before calling layout
        view.setPresenter(this);
        try {
            dataSource = DataSourceFactory.get(SolarSystemDS.DS_TYPE, eventBus);
        } catch (InvalidParameterException e) {
            view.alert("Could not load data source");
            return;
        }
        view.layout();

        view.getPlanetsMenu().addClickHandler(new PlanetsForSolarSystemMenuClickHandlerImplementation(eventBus, view));
        view.getVisualizePlanetsMenu().addClickHandler(new VisualizeMenuClickHandlerImplementation(eventBus));
        view.getVisualizeButton().addClickHandler(new VisualizeButtonClickHandlerImplementation(eventBus));

        HTML5BulkLoadCommand loadCommand = new HTML5BulkSolarSystemLoadCommand((AdminFileUpload) getView());
        ((AdminFileUpload) getView()).addBrowseChangeHandler(new BrowseBulkLoadChangeHandlerImpl(
            ((AdminFileUpload) getView()), loadCommand));

        view.addDeleteButtonClickHandler(new DeleteClickHandlerImpl(view));
        view.addRefreshButtonClickHandler(new RefreshClickHandlerImpl(view));
        view.addSelectAllButtonClickHandler(new SelectAllClickHandlerImpl(view));
        view.addExportButtonClickHandler(new ExportClickHandlerImpl(view));
        view.addClearFiltersButtonClickHandler(new ClearFiltersClickHandlerImpl(view));
        view.addListGridRowContextClickHandler(new RowContextClickHandlerImpl(view));
        // view.addDetailsMenuItemClickHandler(new
        // MenuDetailClickHandlerImplementation(view, eventBus, this));
        // view.addListGridDoubleClickHandler(new
        // ListGridDoubleClickHandlerImplementation(view, eventBus, this));
        view.addListGridRecordClickHandler(new AdminViewRecordClickHandlerImpl(view));
        view.addAddNewButtonClickHandler(new AdminAddNewButtonClickHandlerImpl(view));
        view.addSaveButtonClickHandler(new AdminSaveButtonClickHandlerImpl(view));
        view.addListGridDataArrivedHandler(new ListGridDataArrivedHandlerImpl(getView()));

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qagwaai.starmalaccamax.client.admin.mvp.SolarSystemAdminPresenter
     * #destroyView()
     */
    @Override
    public void destroyView() {
        getView().destroy();

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qagwaai.starmalaccamax.client.admin.mvp.SolarSystemAdminPresenter
     * #getDataSource()
     */
    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qagwaai.starmalaccamax.client.admin.mvp.SolarSystemAdminPresenter
     * #hideView()
     */

    @Override
    public void hideView() {
        getView().hide();

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qagwaai.starmalaccamax.client.admin.mvp.SolarSystemAdminPresenter
     * #renderView()
     */

    @Override
    public void renderView() {
        getView().render();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qagwaai.starmalaccamax.client.admin.mvp.SolarSystemAdminPresenter
     * #showView()
     */
    @Override
    public void showView() {
        getView().show();

    }

}
