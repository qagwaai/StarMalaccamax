/**
 * UserAdminView.java
 * Created by pgirard at 1:25:44 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.admin.mvp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.admin.constants.AdminConstants;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarPresenterImpl;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarViewImpl;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.MultiSortCallback;
import com.smartgwt.client.data.MultiSortDialog;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.data.SortSpecifier;
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
public class MarketAdminViewImpl extends AbstractView<MarketAdminPresenterImpl> implements AdminFileUpload,
    MarketAdminView {
    private static final int SHADOW_DEPTH = 10;
    private AdminConstants constants = GWT.create(AdminConstants.class);
    private VLayout baseLayout = new VLayout(15);
    private LoginBarPresenterImpl loginBarPresenter;
    private ListGrid listGrid = new ListGrid() /*
                                                * { protected Canvas
                                                * getExpansionComponent(final
                                                * ListGridRecord record) {
                                                * final ListGrid commodityGrid
                                                * = new ListGrid(); final
                                                * ListGrid marketGrid = this;
                                                * VLayout layout = new
                                                * VLayout(5);
                                                * layout.setPadding(5);
                                                * 
                                                * commodityGrid.setWidth100();
                                                * commodityGrid.setHeight(224);
                                                * commodityGrid.setTop(50);
                                                * commodityGrid
                                                * .setShowAllRecords(true);
                                                * commodityGrid
                                                * .setShowEmptyMessage(true);
                                                * commodityGrid
                                                * .setCanEdit(true); Object obj
                                                * =
                                                * record.getAttributeAsObject(
                                                * MarketRecord.COMMODITIES); if
                                                * (obj != null) { if (obj
                                                * instanceof Map<?, ?>) {
                                                * commodityGrid
                                                * .setData(MarketDS
                                                * .toGrid((Map<String,
                                                * MarketCommodityDTO>) obj)); }
                                                * }
                                                * 
                                                * ListGridField nameField = new
                                                * ListGridField
                                                * ("commodityName", "Name",
                                                * 120); ListGridField
                                                * sellPriceField = new
                                                * ListGridField("sellPrice",
                                                * "Sell Price"); ListGridField
                                                * purchasePriceField = new
                                                * ListGridField
                                                * ("purchasePrice",
                                                * "Purchase Price");
                                                * ListGridField
                                                * sellAvailableField = new
                                                * ListGridField
                                                * ("sellAvailable",
                                                * "Sell Available");
                                                * ListGridField
                                                * purchaseWantedField = new
                                                * ListGridField
                                                * ("purchaseWanted",
                                                * "Purchase Wanted");
                                                * commodityGrid
                                                * .setFields(nameField,
                                                * sellPriceField,
                                                * purchasePriceField,
                                                * sellAvailableField,
                                                * purchaseWantedField);
                                                * 
                                                * IButton saveButton = new
                                                * IButton("Save");
                                                * saveButton.addClickHandler
                                                * (new ClickHandler() { public
                                                * void onClick(final ClickEvent
                                                * event) { SC.say("Save!"); }
                                                * });
                                                * 
                                                * IButton cancelButton = new
                                                * IButton("Done");
                                                * cancelButton.
                                                * addClickHandler(new
                                                * ClickHandler() { public void
                                                * onClick(final ClickEvent
                                                * event) {
                                                * marketGrid.collapseRecord
                                                * (record); } });
                                                * 
                                                * HLayout hLayout = new
                                                * HLayout(10);
                                                * hLayout.setAlign(
                                                * Alignment.CENTER);
                                                * hLayout.addMember
                                                * (saveButton);
                                                * hLayout.addMember
                                                * (cancelButton);
                                                * 
                                                * layout.addMember(commodityGrid
                                                * ); layout.addMember(hLayout);
                                                * return layout; } }
                                                */;

    private FileUpload browse = new FileUpload();
    private Button upload = new Button(constants.uploadButton());
    private TextBox selected = new TextBox();
    private TextBox recordsPerTick = new TextBox();
    private TextArea status = new TextArea();
    private IButton deleteButton = new IButton(constants.deleteButton());
    private IButton refreshButton = new IButton(constants.refreshButton());
    private IButton selectAllButton = new IButton(constants.selectAllButton());
    private IButton exportButton = new IButton(constants.exportButton());
    private IButton addNewButton = new IButton(constants.addNewButton());
    private IButton clearFiltersButton = new IButton(constants.clearFiltersButton());
    private MenuItem markets = new MenuItem(constants.marketAdminView_showMarketDetails());
    private Menu contextMenu = new Menu();
    private final DynamicForm detailForm = new DynamicForm();
    private IButton multiLevelSortButton = new IButton(constants.sortButton());
    private IButton saveButton = new IButton(constants.saveButton());
    private com.smartgwt.client.widgets.Label listGridLabel = new com.smartgwt.client.widgets.Label(
        "Total Records: loading...");

    /**
	 * 
	 */
    public MarketAdminViewImpl() {
        super();
    }

    /**
     * 
     * @param location
     *            the location of the page - used for history
     */
    public MarketAdminViewImpl(final String location) {
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

    /**
     * @return the markets
     */
    /*
     * @Override public final MenuItem getMarketDetailsMenuItem() { return
     * markets; }
     */
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

        String title = Canvas.imgHTML("silk/world.png") + " " + constants.marketAdminView_marketSectionTitle();
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
        listGrid.setDataPageSize(25);
        listGrid.setCanMultiSort(true);

        listGridLabel.setHeight(25);

        section.setItems(listGrid, listGridLabel);
        sectionStack.setSections(section);

        multiLevelSortButton.setIcon("crystal/16/actions/sort_incr.png");
        multiLevelSortButton.setWidth(120);
        multiLevelSortButton.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                MultiSortDialog.askForSort(listGrid, listGrid.getSort(), new MultiSortCallback() {
                    public void execute(final SortSpecifier[] sortLevels) {
                        // if sortLevels is null, it means that the
                        // Cancel button was clicked
                        // in which case we simply want to dismiss the
                        // dialog
                        if (sortLevels != null) {
                            listGrid.setSort(sortLevels);
                        }
                    }
                });
            }
        });

        HLayout gridButtonBar = new HLayout();
        gridButtonBar.setWidth100();

        refreshButton.setIcon("icons/16/arrow_refresh.png");

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
        baseLayout.addMember(saveButton);

        final VLayout fileAdminLayout = new VLayout();
        fileAdminLayout.setShowEdges(true);
        final HLayout loadLayout = new HLayout();

        status.setWidth("400px");
        status.setHeight("100px");
        upload.setEnabled(false);

        selected.setEnabled(false);

        final SimplePanel progressInner = new SimplePanel();
        progressInner.addStyleName("progressInner");

        final SimplePanel progressGauge = new SimplePanel();
        progressGauge.addStyleName("progressGauge");
        progressGauge.add(progressInner);

        loadLayout.addMember(selected);
        loadLayout.addMember(browse);
        loadLayout.addMember(upload);
        loadLayout.addMember(status);
        loadLayout.addMember(new Label("Records per Tick:"));
        recordsPerTick.setValue("25");
        recordsPerTick.setTitle("Records per Tick");
        loadLayout.addMember(recordsPerTick);
        loadLayout.setHeight(100);
        progressGauge.setHeight("25px");

        fileAdminLayout.addMember(loadLayout);
        fileAdminLayout.addMember(progressGauge);

        baseLayout.addMember(fileAdminLayout);

        contextMenu.setShowShadow(true);
        contextMenu.setShadowDepth(SHADOW_DEPTH);
        contextMenu.setItems(markets);
    }

    /**
     * 
     * @param event
     *            the event captured on the row
     */
    @Override
    public final void showContextMenu(final RowContextClickEvent event) {
        contextMenu.setLeft(event.getX());
        contextMenu.setTop(event.getY());
        contextMenu.show();
        event.cancel();
    }

    public final void addDetailsMenuItemClickHandler(final com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
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
        listGrid.exportData();
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

    @Override
    public void addBrowseChangeHandler(final ChangeHandler handler) {
        browse.addChangeHandler(handler);
    }

    @Override
    public void setSelectedFile(final String filename) {
        selected.setText(filename);

    }

    /**
     * 
     * @param message
     *            the bulk loading message to add
     */
    public final void addStatus(final String message) {
        String buf = status.getText() + message;
        status.setText(buf);
        status.setCursorPos(buf.length());
    }

    /**
     * @return the recordsPerTick
     */
    public final int getRecordsPerTick() {
        return Integer.valueOf(recordsPerTick.getValue());
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
