package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenter;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;



/**
 * 
 * @author pgirard
 * 
 */
public final class PlayerOppRefreshClickHandlerImplementation implements ClickHandler {
    /**
     * 
     */
    private final PlayerSummaryView view;
    private final PlayerSummaryPresenter presenter;

    /**
     * 
     * @param view
     *            the view
     */
    public PlayerOppRefreshClickHandlerImplementation(final PlayerSummaryView view, final PlayerSummaryPresenter presenter) {
        this.presenter = presenter;
        this.view = view;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final ClickEvent event) {
        view.getListGrid().setData(new ListGridRecord[0]);
        presenter.loadSummaryListGrid();

    }
}