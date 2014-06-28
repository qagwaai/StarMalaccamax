package com.qagwaai.starmalaccamax.client.admin.callbacks;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.ShipWindowPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.ShipWindowViewImpl;
import com.qagwaai.starmalaccamax.client.service.action.GetShipResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.smartgwt.client.util.SC;

public final class GetShipAsyncCallback extends BaseAsyncCallback<GetShipResponse> {
    private final EventBus eventBus;

    public GetShipAsyncCallback(EventBus eventBus) {
        super();
        this.eventBus = eventBus;
    }

    @Override
    public void onFailure(final Throwable caught) {
        super.onFailure(caught);
        SC.say("Could not show ship details: " + caught.getMessage());
        ErrorPresenter.present(caught);

    }

    @Override
    public void onSuccess(final GetShipResponse result) {
        super.onSuccess(result);
        ShipWindowPresenterImpl shipPresenter = new ShipWindowPresenterImpl(eventBus, new ShipWindowViewImpl(), result.getShip());
        shipPresenter.getView().setTitle("Ship Detail ");
        shipPresenter.renderView();
    }
}
