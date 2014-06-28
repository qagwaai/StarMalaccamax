package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.View;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;

public interface ClosestPlanetWindowView extends View {

	/**
	 * @return the closeButton
	 */
	IButton getCloseButton();

	/**
	 * @return the currentLocationLabel
	 */
	Label getCurrentLocationLabel();

	/**
	 * @return the planetGrid
	 */
	ListGrid getPlanetGrid();

	/**
	 * 
	 * @param windowTitle
	 *            the title of the window
	 */
	void setTitle(String windowTitle);

}