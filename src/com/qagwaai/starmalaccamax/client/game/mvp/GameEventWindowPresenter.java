package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.Presenter;
import com.qagwaai.starmalaccamax.shared.model.GameEvent;
import com.smartgwt.client.data.DataSource;

public interface GameEventWindowPresenter extends Presenter<GameEventWindowView, GameEvent> {

	/**
	 * @return the dataSource
	 */
	DataSource getDataSource();

}