package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenter;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;


/**
 * 
 * @author pgirard
 * 
 */
public final class PlayerSummaryDestinationsButtonClickHandlerImplementation implements ClickHandler {
	private final PlayerSummaryPresenter presenter;
	
	
    public PlayerSummaryDestinationsButtonClickHandlerImplementation(
			PlayerSummaryPresenter presenter) {
		super();
		this.presenter = presenter;
	}


	@Override
    public void onClick(final ClickEvent event) {
        presenter.showClosestDestinations();

    }
}
