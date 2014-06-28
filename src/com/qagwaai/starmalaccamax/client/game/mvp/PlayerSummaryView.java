package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.View;
import com.qagwaai.starmalaccamax.client.game.mvp.widget.PieChartWidget;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.calendar.Calendar;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;

public interface PlayerSummaryView extends View {

	/**
	 * @return the addCargoItemMenu
	 */
	MenuItem getAddCargoItemMenu();

	/**
	 * @return the addShipMenu
	 */
	MenuItem getAddShipMenu();

	/**
	 * @return the captainPropertiesButton
	 */
	IButton getCaptainPropertiesButton();

	/**
	 * @return the captainProperties
	 */
	MenuItem getCaptainPropertiesMenuItem();

	/**
	 * @return the costsPie
	 */
	PieChartWidget getCostsPie();

	/**
	 * @return the destinationsButton
	 */
	IButton getDestinationsButton();

	/**
	 * @return the destinationsMenu
	 */
	MenuItem getDestinationsMenu();

	/**
	 * @return the contextMenu
	 */
	Menu getListContextMenu();

	/**
	 * @return the captainListGrid
	 */
	ListGrid getListGrid();

	/**
	 * @return the planetPropertiesButton
	 */
	IButton getPlanetPropertiesButton();

	/**
	 * @return the planetPropertiesMenu
	 */
	MenuItem getPlanetPropertiesMenu();

	/**
	 * @return the refreshButton
	 */
	IButton getRefreshButton();

	/**
	 * @return the resourcesPie
	 */
	PieChartWidget getResourcesPie();

	/**
	 * @return the shipPropertiesButton
	 */
	IButton getShipPropertiesButton();

	/**
	 * @return the shipPropertiesMenu
	 */
	MenuItem getShipPropertiesMenu();

	/**
	 * 
	 * @param event
	 *            the event captured on the row click
	 */
	void showContextMenu(RowContextClickEvent event);

	/**
	 * @return the eventCalendar
	 */
	Calendar getEventCalendar();

	/**
	 * @return the visualizationButton
	 */
	IButton getVisualizationButton();

	/**
	 * @return the visualizationMenu
	 */
	MenuItem getVisualizationMenu();

}