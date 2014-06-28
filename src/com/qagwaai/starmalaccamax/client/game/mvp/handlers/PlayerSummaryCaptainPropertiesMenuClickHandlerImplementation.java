package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenter;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;



/**
 * 
 * @author pgirard
 * 
 */
public final class PlayerSummaryCaptainPropertiesMenuClickHandlerImplementation implements
    com.smartgwt.client.widgets.menu.events.ClickHandler {
	private final PlayerSummaryPresenter presenter;
	
    public PlayerSummaryCaptainPropertiesMenuClickHandlerImplementation(
			PlayerSummaryPresenter presenter) {
		super();
		this.presenter = presenter;
	}

	/**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final MenuItemClickEvent event) {
        presenter.showCaptainProperties();
    }
}
