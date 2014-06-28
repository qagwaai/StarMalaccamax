package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenter;





/**
 * 
 * @author pgirard
 * 
 */
public final class PlayerSummaryCaptainPropertiesButtonClickHandlerImplementation implements
    com.smartgwt.client.widgets.events.ClickHandler {
	
	private final PlayerSummaryPresenter presenter;
	
    public PlayerSummaryCaptainPropertiesButtonClickHandlerImplementation(
			PlayerSummaryPresenter presenter) {
		super();
		this.presenter = presenter;
	}

	/**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final com.smartgwt.client.widgets.events.ClickEvent event) {
        presenter.showCaptainProperties();
    }
}
