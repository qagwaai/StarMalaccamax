package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenter;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;



/**
 * 
 * @author pgirard
 * 
 */
public class PlayerSummaryShowPlanetPropertiesButtonClickHandlerImplementation implements ClickHandler {
    private final PlayerSummaryPresenter presenter;
    
    public PlayerSummaryShowPlanetPropertiesButtonClickHandlerImplementation(PlayerSummaryPresenter presenter) {
        super();
        this.presenter = presenter;
    }

    @Override
    public void onClick(final ClickEvent event) {
        presenter.showPlanetProperties();
    }
}