package com.qagwaai.starmalaccamax.client.admin.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.View;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.data.SortSpecifier;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;
import com.smartgwt.client.widgets.grid.events.RowContextClickHandler;

/**
 * 
 * @author ehldxae
 *
 */
public interface AdminView extends View {

    // ListGrid getListGrid();

    /**
     * 
     * @param event the event passed
     */
    void showContextMenu(final RowContextClickEvent event);

    /**
     * 
     * @param handler add a handler to the clear filters button
     */
    void addClearFiltersButtonClickHandler(final ClickHandler handler);

    /**
     * 
     * @param handler add a handler to the delete button
     */
    void addDeleteButtonClickHandler(final ClickHandler handler);

    /**
     * 
     * @param handler add a handler to the export button
     */
    void addExportButtonClickHandler(final ClickHandler handler);

    /**
     * 
     * @param handler add a handler to the refresh button
     */
    void addRefreshButtonClickHandler(final ClickHandler handler);

    /**
     * 
     * @param handler add a handler to the select all button
     */
    void addSelectAllButtonClickHandler(final ClickHandler handler);

    /**
     * 
     * @param handler add a handler to the details menu item
     */
    void addDetailsMenuItemClickHandler(final com.smartgwt.client.widgets.menu.events.ClickHandler handler);

    /**
     * 
     * @param handler add a handler to the row click
     */
    void addListGridRowContextClickHandler(final RowContextClickHandler handler);

    /**
     * 
     * @param handler add a handler to the row double click
     */
    void addListGridDoubleClickHandler(final DoubleClickHandler handler);

    /**
     * 
     * @param handler add a handler to the add new button
     */
    void addAddNewButtonClickHandler(final ClickHandler handler);

    /**
     * 
     * @param handler add a handler to the save button
     */
    void addSaveButtonClickHandler(final ClickHandler handler);

    /**
     * 
     * @return a list of selected records from the grid
     */
    ListGridRecord[] getListGridSelectedRecords();

    /**
     * 
     * @param record remove the provided record
     */
    void removeListGridData(final Record record);

    /**
     * 
     * @param handler add a handler to the data arrived event
     */
    void addListGridDataArrivedHandler(final DataArrivedHandler handler);

    /**
     * 
     * @param criteria set the criteria of the grid
     */
    void setListGridCriteria(final Criteria criteria);

    /**
     * 
     * @return true if the grid has been created
     */
    boolean isListGridCreated();

    /**
     * invalidate all data from the grid
     */
    void invalidListGridCache();

    /**
     * 
     * @return return the whole list of records
     */
    RecordList getListGridRecordList();

    /**
     * 
     * @param record select the provided record
     */
    void selectListGridRecord(final Record record);

    /**
     * 
     * @return get the selected record
     */
    Record getSelectedListGridRecord();

    /**
     * 
     * @return get the total number of records loaded.  this is not the total number of records available in the datasource
     */
    int getListGridTotalRecords();

    /**
     * export the data in the grid
     */
    void exportListGridData();

    /**
     * clear the criteria in the grid
     */
    void clearListGridCriteria();

    /**
     * 
     * @param handler add a handler to the list grid record click event
     */
    void addListGridRecordClickHandler(final RecordClickHandler handler);

    /**
     * reset the details form to the original data values from the record
     */
    void detailsFormReset();

    /**
     * add a new "blank" record to the details form
     */
    void detailsFormEditNewRecord();

    /**
     * save the entries in the details form
     */
    void detailsFormSave();

    /**
     * edit the selected record
     */
    void editSelectedListGridData();

    /**
     * 
     * @param sortLevels set the sort levels of the grid - called from the complex sort builder
     */
    void setListGridSortLevels(final SortSpecifier[] sortLevels);

    /**
     * 
     * @return get the sort levels of the grid
     */
    SortSpecifier[] getListGridSort();

    /**
     * 
     * @param handler add a handler to the multi level sort button
     */
    void addMultiLevelSortButtonClickHandler(ClickHandler handler);

    /**
     * 
     * @param message set the grid label
     */
    void setListGridLabelMessage(String message);
}
