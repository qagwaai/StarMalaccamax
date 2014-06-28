package com.qagwaai.starmalaccamax.client.game.mvp;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.client.core.mvp.Presenter;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.User;

public interface PlayerOpportunitiesPresenter extends Presenter<PlayerOpportunitiesView, User> {

	/**
	 * 
	 * @param ships
	 *            the ships with cargo to display
	 */
	void loadCargoGrid(ArrayList<ShipDTO> ships);

	void loadJobBoard(ArrayList<ShipDTO> ships);

}