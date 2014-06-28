/**
 * UserAdminView.java
 * Created by pgirard at 1:25:44 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.admin.mvp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.admin.constants.AdminConstants;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarPresenterImpl;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarViewImpl;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.MultiSortCallback;
import com.smartgwt.client.data.MultiSortDialog;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.data.SortSpecifier;
import com.smartgwt.client.types.ExportDisplay;
import com.smartgwt.client.types.ExportFormat;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;
import com.smartgwt.client.widgets.grid.events.RowContextClickHandler;
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
public class CaptainAdminViewImpl extends AbstractView<CaptainAdminPresenter> implements CaptainAdminView {

    private static final int SHADOW_DEPTH = 10;

    // cannot get away from this internally because of the first paramter to
    // MultiSortDialog which is essentially the list Grid
    // SmartGWT broke Model View Presenter here.
    private final class AdminMultiLevelSortClickHandlerImplementation implements ClickHandler {
        private final AdminView view;

        public AdminMultiLevelSortClickHandlerImplementation(final AdminView view) {
            this.view = view;
        }

        public void onClick(final ClickEvent event) {
            MultiSortDialog.askForSort(listGrid, view.getListGridSort(), new MultiSortCallback() {
                public void execute(final SortSpecifier[] sortLevels) {
                    // if sortLevels is null, it means that the Cancel
                    // button was clicked
                    // in which case we simply want to dismiss the
                    // dialog
                    if (sortLevels != null) {
                        view.setListGridSortLevels(sortLevels);
                    }
                }
            });
        }
    }

    private AdminConstants constants = GWT.create(AdminConstants.class);
    private VLayout baseLayout = new VLayout(15);
    private LoginBarPresenterImpl loginBarPresenter;
    private ListGrid listGrid = new ListGrid();
    private IButton deleteButton = new IButton(constants.deleteButton());
    private IButton refreshButton = new IButton(constants.refreshButton());
    private IButton selectAllButton = new IButton(constants.selectAllButton());
    private IButton exportButton = new IButton(constants.exportButton());
    private IButton addNewButton = new IButton(constants.addNewButton());
    private IButton clearFiltersButton = new IButton(constants.clearFiltersButton());
    private MenuItem captains = new MenuItem(constants.captainAdminView_showCaptainDetailsButton());
    private IButton multiLevelSortButton = new IButton(constants.sortButton());
    private Menu contextMenu = new Menu();
    private final DynamicForm detailForm = new DynamicForm();
    private IButton saveButton = new IButton(constants.saveButton());
    private com.smartgwt.client.widgets.Label listGridLabel = new com.smartgwt.client.widgets.Label(
        "Total Records: loading...");

    public CaptainAdminViewImpl() {
        super();
    }

    /**
     * 
     * @param location
     *            the location of the page - used for history
     */
    public CaptainAdminViewImpl(final String location) {
        this();
        setLocation(location);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Widget asWidget() {
        return baseLayout;
    }

    public final void addDetailsMenuItemClickHandler(final com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        captains.addClickHandler(handler);
    }

    public final void addClearFiltersButtonClickHandler(final ClickHandler handler) {
        clearFiltersButton.addClickHandler(handler);
    }

    public final void addDeleteButtonClickHandler(final ClickHandler handler) {
        deleteButton.addClickHandler(handler);
    }

    public final void addExportButtonClickHandler(final ClickHandler handler) {
        exportButton.addClickHandler(handler);
    }

    public final void addRefreshButtonClickHandler(final ClickHandler handler) {
        refreshButton.addClickHandler(handler);
    }

    public final void addMultiLevelSortButtonClickHandler(final ClickHandler handler) {
        multiLevelSortButton.addClickHandler(handler);
    }

    public final void addAddNewButtonClickHandler(final ClickHandler handler) {
        addNewButton.addClickHandler(handler);
    }

    public final void addSaveButtonClickHandler(final ClickHandler handler) {
        saveButton.addClickHandler(handler);
    }

    public final void addListGridRowContextClickHandler(final RowContextClickHandler handler) {
        listGrid.addRowContextClickHandler(handler);
    }

    public final void addListGridDoubleClickHandler(final DoubleClickHandler handler) {
        listGrid.addDoubleClickHandler(handler);
    }

    public final ListGridRecord[] getListGridSelectedRecords() {
        return listGrid.getSelectedRecords();
    }

    public final void removeListGridData(final Record record) {
        listGrid.removeData(record);
    }

    public final void invalidListGridCache() {
        listGrid.invalidateCache();
    }

    public final RecordList getListGridRecordList() {
        return listGrid.getRecordList();
    }

    public final void selectListGridRecord(final Record record) {
        listGrid.selectRecord(record);
    }

    public final Record getSelectedListGridRecord() {
        return listGrid.getSelectedRecord();
    }

    public final int getListGridTotalRecords() {
        return listGrid.getRecords().length;
    }

    public final void exportListGridData() {
        DSRequest dsRequestProperties = new DSRequest();  
        dsRequestProperties.setExportAs(ExportFormat.CSV);  
        dsRequestProperties.setExportDisplay(ExportDisplay.DOWNLOAD);  

        listGrid.exportData(dsRequestProperties);
    }

    public final void clearListGridCriteria() {
        listGrid.clearCriteria();
    }

    public final void addListGridRecordClickHandler(final RecordClickHandler handler) {
        listGrid.addRecordClickHandler(handler);
    }

    public final void detailsFormReset() {
        detailForm.reset();
    }

    public final void detailsFormEditNewRecord() {
        detailForm.editNewRecord();
    }

    public final void detailsFormSave() {
        detailForm.saveData();
    }

    public final void editSelectedListGridData() {
        detailForm.editSelectedData(listGrid);
    }

    public final void setListGridSortLevels(final SortSpecifier[] sortLevels) {
        listGrid.setSort(sortLevels);
    }

    public final SortSpecifier[] getListGridSort() {
        return listGrid.getSort();
    }

    public final void addSelectAllButtonClickHandler(final ClickHandler handler) {
        selectAllButton.addClickHandler(handler);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Canvas getRootCanvas() {
        return baseLayout;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void layout() {

        baseLayout.setWidth100();
        loginBarPresenter =
            new LoginBarPresenterImpl(this.getPresenter().getEventBus(), new LoginBarViewImpl(), new UserDTO());
        baseLayout.addMember(loginBarPresenter.getView().asWidget());

        SectionStack sectionStack = new SectionStack();
        sectionStack.setWidth100();
        sectionStack.setHeight(230);

        String title = Canvas.imgHTML("silk/world.png") + " " + constants.captainAdminView_captainSectionTitle();
        SectionStackSection section = new SectionStackSection(title);

        section.setCanCollapse(false);
        section.setExpanded(true);

        detailForm.setIsGroup(true);
        detailForm.setGroupTitle(constants.formTitle());
        detailForm.setNumCols(6);
        detailForm.setDataSource(getPresenter().getDataSource());

        listGrid.setDrawAheadRatio(4);
        listGrid.setCanExpandRecords(true);
        listGrid.setShowFilterEditor(true);
        listGrid.setWidth100();
        listGrid.setHeight(200);
        listGrid.setDataSource(getPresenter().getDataSource());
        listGrid.setAutoFetchData(true);
        // listGrid.addRecordClickHandler(new
        // AdminViewRecordClickHandlerImplementation(form, listGrid));
        listGrid.setDataPageSize(50);
        listGrid.setCanMultiSort(true);

        listGridLabel.setHeight(25);

        section.setItems(listGrid, listGridLabel);
        sectionStack.setSections(section);

        multiLevelSortButton.setIcon("crystal/16/actions/sort_incr.png");
        multiLevelSortButton.setWidth(120);
        multiLevelSortButton.addClickHandler(new AdminMultiLevelSortClickHandlerImplementation(this));

        refreshButton.setIcon("icons/16/arrow_refresh.png");
        HLayout gridButtonBar = new HLayout();
        gridButtonBar.setWidth100();
        // addNewButton.addClickHandler(new
        // AdminAddNewButtonClickHandlerImplementation(this));
        gridButtonBar.addMember(addNewButton);
        gridButtonBar.addMember(deleteButton);
        gridButtonBar.addMember(refreshButton);
        gridButtonBar.addMember(selectAllButton);
        gridButtonBar.addMember(exportButton);
        gridButtonBar.addMember(multiLevelSortButton);
        gridButtonBar.addMember(clearFiltersButton);

        baseLayout.addMember(sectionStack);
        baseLayout.addMember(gridButtonBar);
        baseLayout.addMember(detailForm);

        // saveButton.addClickHandler(new
        // AdminSaveButtonClickHandlerImplementation(this));
        baseLayout.addMember(saveButton);

        contextMenu.setShowShadow(true);
        contextMenu.setShadowDepth(SHADOW_DEPTH);
        contextMenu.setItems(captains);
    }

    /**
     * 
     * @param event
     *            the event captured on the row
     */
    public final void showContextMenu(final RowContextClickEvent event) {
        contextMenu.setLeft(event.getX());
        contextMenu.setTop(event.getY());
        contextMenu.show();
        event.cancel();
    }

    @Override
    public void addListGridDataArrivedHandler(final DataArrivedHandler handler) {
        listGrid.addDataArrivedHandler(handler);
    }

    @Override
    public void setListGridCriteria(final Criteria criteria) {
        listGrid.setCriteria(criteria);
    }

    @Override
    public void setListGridLabelMessage(final String message) {
        listGridLabel.setContents(message);
    }

    @Override
    public boolean isListGridCreated() {
        return listGrid.isCreated();
    }
}
