package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.View;
import com.qagwaai.starmalaccamax.client.game.mvp.widget.LocationItem;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.SectionStack;

public interface PlanetWindowView extends View {

	/**
	 * @return the atmosphereField
	 */
	TextItem getAtmosphereField();

	/**
	 * @return the closeButton
	 */
	IButton getCloseButton();

	/**
	 * @return the commodityGrid
	 */
	ListGrid getCommodityGrid();

	/**
	 * @return the dockQualityField
	 */
	TextItem getDockQualityField();

	/**
	 * @return the form
	 */
	DynamicForm getForm();

	/**
	 * @return the gasGiantField
	 */
	CheckboxItem getGasGiantField();

	/**
	 * @return the governmentField
	 */
	TextItem getGovernmentField();

	/**
	 * @return the hydrographicsField
	 */
	TextItem getHydrographicsField();

	/**
	 * @return the lawLevelField
	 */
	TextItem getLawLevelField();

	/**
	 * @return the location
	 */
	LocationItem getLocationItem();

	/**
	 * @return the mainWorldField
	 */
	CheckboxItem getMainWorldField();

	/**
	 * @return the namePropertyField
	 */
	TextItem getNamePropertyField();

	/**
	 * @return the orbitField
	 */
	TextItem getOrbitField();

	/**
	 * @return the populationField
	 */
	TextItem getPopulationField();

	/**
	 * @return the satelliteField
	 */
	CheckboxItem getSatelliteField();

	/**
	 * @return the saveButton
	 */
	IButton getSaveButton();

	/**
	 * @return the sizeField
	 */
	TextItem getSizeField();

	/**
	 * @return the techLevelField
	 */
	TextItem getTechLevelField();

	/**
	 * 
	 * @param windowTitle
	 *            the title of the window
	 */
	void setTitle(String windowTitle);

	/**
	 * @return the planetImage
	 */
	Img getPlanetImage();

	/**
	 * @return the sectionStack
	 */
	SectionStack getSectionStack();

}