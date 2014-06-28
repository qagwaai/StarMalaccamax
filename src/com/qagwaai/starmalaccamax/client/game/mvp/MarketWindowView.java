package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.View;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.grid.ListGrid;

public interface MarketWindowView extends View {

	/**
	 * @return the closeButton
	 */
	IButton getCloseButton();

	/**
	 * @return the commodityGrid
	 */
	ListGrid getCommodityGrid();

	/**
	 * @return the form
	 */
	DynamicForm getForm();

	/**
	 * @return the lastVisitedItem
	 */
	DateItem getLastVisitedItem();

	/**
	 * @return the saveButton
	 */
	IButton getSaveButton();

	/**
	 * 
	 * @param windowTitle
	 *            the title of the window
	 */
	void setTitle(String windowTitle);

}