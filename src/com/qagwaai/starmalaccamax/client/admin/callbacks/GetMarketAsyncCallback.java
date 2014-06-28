package com.qagwaai.starmalaccamax.client.admin.callbacks;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.qagwaai.starmalaccamax.client.admin.constants.AdminConstants;
import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.MarketWindowPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.MarketWindowViewImpl;
import com.qagwaai.starmalaccamax.client.service.action.GetMarketResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;

public final class GetMarketAsyncCallback extends BaseAsyncCallback<GetMarketResponse> {

    private static AdminConstants constants = GWT.create(AdminConstants.class);
    private EventBus eventBus;

    public GetMarketAsyncCallback(EventBus eventBus) {
        super();
        this.eventBus = eventBus;
    }

    @Override
    public void onFailure(final Throwable caught) {
        super.onFailure(caught);
        Window.alert(constants.marketAdminPresenter_showMarketDetailsError() + " " + caught.getMessage());
        ErrorPresenter.present(caught);

    }

    @Override
    public void onSuccess(final GetMarketResponse result) {
        super.onSuccess(result);
        MarketWindowPresenterImpl marketPresenter =
            new MarketWindowPresenterImpl(eventBus, new MarketWindowViewImpl(), result.getMarket());
        marketPresenter.getView().setTitle(constants.marketAdminPresenter_marketDetailsTitle());
        marketPresenter.renderView();
    }
}