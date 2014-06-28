package com.qagwaai.starmalaccamax.client.game.callbacks;

import com.qagwaai.starmalaccamax.client.game.mvp.PlayerOpportunitiesPresenter;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerOpportunitiesViewImpl;
import com.qagwaai.starmalaccamax.client.service.action.GetShipsForUserResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;



/**
 * @author pgirard
 * 
 */
public final class PlayerOppGetShipsAsyncCallbackImplementation extends BaseAsyncCallback<GetShipsForUserResponse> {
    /**
	 * 
	 */
    private final PlayerOpportunitiesViewImpl view;
    private final PlayerOpportunitiesPresenter presenter;

    /**
     * @param view
     *            the associated view
     */
    public PlayerOppGetShipsAsyncCallbackImplementation(final PlayerOpportunitiesViewImpl view, final PlayerOpportunitiesPresenter presenter) {
        super();
        this.view = view;
        this.presenter = presenter;
    }

    @Override
    public void onFailure(final Throwable caught) {
        super.onFailure(caught);
        view.say("Error", "Could not load ship data: " + caught.getMessage());

    }

    @Override
    public void onSuccess(final GetShipsForUserResponse result) {
        super.onSuccess(result);
        presenter.loadCargoGrid(result.getShips());
        presenter.loadJobBoard(result.getShips());
    }
}
