package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.View;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.grid.ListGrid;

public interface PlayerOpportunitiesView extends View {

	/**
	 * @return the cargoGrid
	 */
	ListGrid getCargoGrid();

	/**
	 * @return the launchShipButton
	 */
	IButton getLaunchShipButton();

	/**
	 * @return the opportunityGrid
	 */
	ListGrid getOpportunityGrid();

	/**
	 * @return the planetPropertiesButton
	 */
	IButton getPlanetPropertiesButton();

	/**
	 * @return the refreshButton
	 */
	IButton getRefreshButton();

	/**
	 * @return the shipPropertiesButton
	 */
	IButton getShipPropertiesButton();

	/**
	 * 
	 * @param cargo
	 *            the cargo title to set after reloading
	 */
	void setOpportunityGridTitle(String cargo);

	/**
	 * @return the jobGrid
	 */
	ListGrid getJobGrid();

}