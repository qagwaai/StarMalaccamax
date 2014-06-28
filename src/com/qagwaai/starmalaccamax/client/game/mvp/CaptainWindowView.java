package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.View;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.form.fields.ColorPickerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;

public interface CaptainWindowView extends View {

	/**
	 * 
	 */
	void closeWindow();

	/**
	 * @return the attributesListGrid
	 */
	ListGrid getAttributesListGrid();

	/**
	 * @return the cancelButton
	 */
	IButton getCancelButton();

	/**
	 * @return the colorPickerItem
	 */
	ColorPickerItem getColorPickerItem();

	/**
	 * @return the nameItem
	 */
	TextItem getNameItem();

	/**
	 * @return the saveButton
	 */
	IButton getSaveButton();

	/**
	 * @return the skillsListGrid
	 */
	ListGrid getSkillsListGrid();

	/**
	 * 
	 * @param title
	 *            the title of the window
	 */
	void setTitle(String title);

}