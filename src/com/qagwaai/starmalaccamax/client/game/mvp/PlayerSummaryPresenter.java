package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.Presenter;
import com.qagwaai.starmalaccamax.client.event.CaptainUpdatedEvent;
import com.qagwaai.starmalaccamax.shared.model.User;
import com.smartgwt.client.data.DataSource;

public interface PlayerSummaryPresenter extends Presenter<PlayerSummaryView, User> {

	/**
	 * @return the dataSource
	 */
	DataSource getCaptainDataSource();

	/**
	 * 
	 */
	void loadSummaryListGrid();

	/**
	 * 
	 * {@inheritDoc}
	 */
	void onCaptainUpdated(CaptainUpdatedEvent event);

	/**
	 * 
	 */
	void showCaptainProperties();

	void showSolarSystemVisualization();

	/**
	 * 
	 */
	void showClosestDestinations();

	/**
	 * 
	 */
	void showPlanetProperties();

	/**
	 * 
	 */
	void showShipProperties();

}