package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.Presenter;
import com.qagwaai.starmalaccamax.shared.model.Ship;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public interface ShipWindowPresenter extends Presenter<ShipWindowView, Ship> {

	/**
	 * 
	 * @param commodity
	 *            the commodity to search for
	 * @return a list grid record of the commodity cargo or null
	 */
	ListGridRecord[] getCargoGridData(String commodity);

}