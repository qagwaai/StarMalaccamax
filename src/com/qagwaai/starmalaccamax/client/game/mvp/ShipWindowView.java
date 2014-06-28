package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.View;
import com.qagwaai.starmalaccamax.client.game.mvp.widget.LocationItem;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;

public interface ShipWindowView extends View {

	/**
	 * @return the commodityGrid
	 */
	ListGrid getAttributeGrid();

	/**
	 * @return the commoditiesGrid
	 */
	ListGrid getCargoGrid();

	/**
	 * @return the closeButton
	 */
	IButton getCloseButton();

	/**
	 * @return the form
	 */
	DynamicForm getForm();

	/**
	 * @return the location
	 */
	LocationItem getLocationItem();

	/**
	 * @return the namePropertyField
	 */
	TextItem getNamePropertyField();

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