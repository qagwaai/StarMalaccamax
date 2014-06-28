package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenter;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;



/**
 * 
 * @author pgirard
 * 
 */
public class PlayerSummaryShipPropertiesMenuClickHandlerImplementation implements
    com.smartgwt.client.widgets.menu.events.ClickHandler {
    private final PlayerSummaryPresenter presenter;
    
    public PlayerSummaryShipPropertiesMenuClickHandlerImplementation(PlayerSummaryPresenter presenter) {
        super();
        this.presenter = presenter;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final MenuItemClickEvent event) {
        presenter.showShipProperties();

    }
}