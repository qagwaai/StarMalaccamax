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
import com.qagwaai.starmalaccamax.client.admin.util.HTML5BulkJobLoadCommand;
import com.qagwaai.starmalaccamax.client.admin.util.HTML5BulkLoadCommand;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.data.DataSourceFactory;
import com.qagwaai.starmalaccamax.client.data.JobDS;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.shared.InvalidParameterException;
import com.qagwaai.starmalaccamax.shared.model.Job;
import com.smartgwt.client.data.DataSource;

/**
 * @author pgirard
 * 
 */
public final class JobAdminPresenterImpl extends AbstractPresenter<JobAdminView, Job> implements JobAdminPresenter {

    private static AdminConstants constants = GWT.create(AdminConstants.class);
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
    public JobAdminPresenterImpl(final EventBus eventBus, final JobAdminViewImpl view, final Job model) {
        super(eventBus, view, model);
        // make sure to do the association before calling layout
        view.setPresenter(this);
        try {
            dataSource = DataSourceFactory.get(JobDS.DS_TYPE, eventBus);
        } catch (InvalidParameterException e) {
            view.alert(JobAdminPresenterImpl.constants.dataSourceError());
            return;
        }
        view.layout();

        HTML5BulkLoadCommand loadCommand = new HTML5BulkJobLoadCommand((AdminFileUpload) getView());
        ((AdminFileUpload) getView()).addBrowseChangeHandler(new BrowseBulkLoadChangeHandlerImpl(
            ((AdminFileUpload) getView()), loadCommand));

        view.addDeleteButtonClickHandler(new DeleteClickHandlerImpl(view));
        view.addRefreshButtonClickHandler(new RefreshClickHandlerImpl(view));
        view.addSelectAllButtonClickHandler(new SelectAllClickHandlerImpl(view));
        view.addExportButtonClickHandler(new ExportClickHandlerImpl(view));
        view.addClearFiltersButtonClickHandler(new ClearFiltersClickHandlerImpl(view));
        view.addListGridRowContextClickHandler(new RowContextClickHandlerImpl(view));
        view.addListGridRecordClickHandler(new AdminViewRecordClickHandlerImpl(view));
        view.addAddNewButtonClickHandler(new AdminAddNewButtonClickHandlerImpl(view));
        view.addSaveButtonClickHandler(new AdminSaveButtonClickHandlerImpl(view));
        view.addListGridDataArrivedHandler(new ListGridDataArrivedHandlerImpl(getView()));
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
     * 
     * {@inheritDoc}
     */

    @Override
    public void showView() {
        getView().show();

    }

}
