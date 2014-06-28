package com.qagwaai.starmalaccamax.client.admin.mvp;

import com.google.gwt.user.client.ui.Widget;
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

public class CaptainAdminViewMock implements AdminView {

	@Override
	public void showContextMenu(RowContextClickEvent event) {

	}

	@Override
	public void addClearFiltersButtonClickHandler(ClickHandler handler) {

	}

	@Override
	public void addDeleteButtonClickHandler(ClickHandler handler) {

	}

	@Override
	public void addExportButtonClickHandler(ClickHandler handler) {
	}

	@Override
	public void addRefreshButtonClickHandler(ClickHandler handler) {

	}

	@Override
	public void addSelectAllButtonClickHandler(ClickHandler handler) {

	}

	@Override
	public void addDetailsMenuItemClickHandler(
			com.smartgwt.client.widgets.menu.events.ClickHandler handler) {

	}

	@Override
	public void addListGridRowContextClickHandler(RowContextClickHandler handler) {

	}

	@Override
	public void addListGridDoubleClickHandler(DoubleClickHandler handler) {

	}

	@Override
	public void addAddNewButtonClickHandler(ClickHandler handler) {

	}

	@Override
	public void addSaveButtonClickHandler(ClickHandler handler) {

	}

	@Override
	public ListGridRecord[] getListGridSelectedRecords() {
		return null;
	}

	@Override
	public void removeListGridData(Record record) {

	}

	@Override
	public void invalidListGridCache() {

	}

	@Override
	public RecordList getListGridRecordList() {
		return null;
	}

	@Override
	public void selectListGridRecord(Record record) {

	}

	@Override
	public Record getSelectedListGridRecord() {
		return null;
	}

	@Override
	public int getListGridTotalRecords() {
		return 0;
	}

	@Override
	public void exportListGridData() {

	}

	@Override
	public void clearListGridCriteria() {

	}

	@Override
	public void addListGridRecordClickHandler(RecordClickHandler handler) {

	}

	@Override
	public void detailsFormReset() {

	}

	@Override
	public void detailsFormEditNewRecord() {

	}

	@Override
	public void detailsFormSave() {

	}

	@Override
	public void editSelectedListGridData() {

	}

	@Override
	public void setListGridSortLevels(SortSpecifier[] sortLevels) {

	}

	@Override
	public SortSpecifier[] getListGridSort() {
		return null;
	}

	@Override
	public void addMultiLevelSortButtonClickHandler(ClickHandler handler) {

	}

	@Override
	public Widget asWidget() {
		return null;
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void layout() {
		
	}

	@Override
	public void render() {
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void alert(String message) {
		
	}

	@Override
	public void addListGridDataArrivedHandler(DataArrivedHandler handler) {
		
	}

	@Override
	public void setListGridCriteria(Criteria criteria) {
		
	}

	@Override
	public boolean isListGridCreated() {
		return false;
	}

	@Override
	public void setListGridLabelMessage(String message) {
		
	}

	@Override
	public void say(String title, String message) {
		// TODO Auto-generated method stub
		
	}

}
