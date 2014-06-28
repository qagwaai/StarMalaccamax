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
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.ClearFiltersClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.DeleteClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.ExportClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.ListGridDataArrivedHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.RefreshClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.RowContextClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.SelectAllClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.data.DataSourceFactory;
import com.qagwaai.starmalaccamax.client.data.UserDS;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.shared.InvalidParameterException;
import com.qagwaai.starmalaccamax.shared.model.User;
import com.smartgwt.client.data.DataSource;

/**
 * @author pgirard
 * 
 */
public final class UserAdminPresenterImpl extends AbstractPresenter<UserAdminView, User> implements UserAdminPresenter {
    private AdminConstants constants = GWT.create(AdminConstants.class);
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
    public UserAdminPresenterImpl(final EventBus eventBus, final UserAdminViewImpl view, final User model) {
        super(eventBus, view, model);
        // make sure to do the association before calling layout
        view.setPresenter(this);
        try {
            dataSource = DataSourceFactory.get(UserDS.DS_TYPE, eventBus);
        } catch (InvalidParameterException e) {
            view.alert("Could not load data source");
            return;
        }
        view.layout();

        // HTML5BulkLoadCommand loadCommand = new
        // HTML5BulkUserLoadCommand((AdminFileUpload) getView());
        // ((AdminFileUpload) getView()).addBrowseChangeHandler(new
        // BrowseBulkLoadChangeHandlerImplementation(((AdminFileUpload)
        // getView()), loadCommand));

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
     * com.qagwaai.starmalaccamax.client.admin.mvp.UserAdminPresenter#destroyView
     * ()
     */

    @Override
    public void destroyView() {
        getView().destroy();

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qagwaai.starmalaccamax.client.admin.mvp.UserAdminPresenter#getDataSource
     * ()
     */
    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qagwaai.starmalaccamax.client.admin.mvp.UserAdminPresenter#hideView()
     */
    @Override
    public void hideView() {
        getView().hide();

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qagwaai.starmalaccamax.client.admin.mvp.UserAdminPresenter#renderView
     * ()
     */

    @Override
    public void renderView() {
        getView().render();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qagwaai.starmalaccamax.client.admin.mvp.UserAdminPresenter#showView()
     */

    @Override
    public void showView() {
        getView().show();

    }

}
