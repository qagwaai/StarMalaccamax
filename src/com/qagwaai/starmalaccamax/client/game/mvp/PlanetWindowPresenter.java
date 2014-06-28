package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.Presenter;
import com.qagwaai.starmalaccamax.shared.model.Market;
import com.qagwaai.starmalaccamax.shared.model.Planet;

public interface PlanetWindowPresenter extends Presenter<PlanetWindowView, Planet> {

	Market getMarketModel();

	void setMarketModel(Market marketModel);

}