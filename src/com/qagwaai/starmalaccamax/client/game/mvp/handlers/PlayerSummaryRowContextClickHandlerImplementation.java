package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView;
import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;
import com.smartgwt.client.widgets.grid.events.RowContextClickHandler;



/**
 * 
 * @author pgirard
 * 
 */
public final class PlayerSummaryRowContextClickHandlerImplementation implements RowContextClickHandler {
    /**
     * 
     */
    private final PlayerSummaryView view;

    /**
     * 
     * @param view
     *            the view
     */
    public PlayerSummaryRowContextClickHandlerImplementation(final PlayerSummaryView view) {
        this.view = view;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onRowContextClick(final RowContextClickEvent event) {
        view.showContextMenu(event);
    }
}