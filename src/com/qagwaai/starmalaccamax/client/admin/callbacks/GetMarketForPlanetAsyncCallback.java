package com.qagwaai.starmalaccamax.client.admin.callbacks;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.qagwaai.starmalaccamax.client.admin.constants.AdminConstants;
import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.MarketWindowPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.MarketWindowViewImpl;
import com.qagwaai.starmalaccamax.client.service.action.GetMarketForPlanetResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;

public final class GetMarketForPlanetAsyncCallback extends BaseAsyncCallback<GetMarketForPlanetResponse> {

    private static AdminConstants constants = GWT.create(AdminConstants.class);
    private EventBus eventBus;

    public GetMarketForPlanetAsyncCallback(EventBus eventBus) {
        super();
        this.eventBus = eventBus;
    }

    @Override
    public void onFailure(final Throwable caught) {
        super.onFailure(caught);
        Window.alert(constants.planetAdminPresenter_showMarketError() + " " + caught.getMessage());
        ErrorPresenter.present(caught);

    }

    @Override
    public void onSuccess(final GetMarketForPlanetResponse result) {
        super.onSuccess(result);
        if (result.getMarket() == null) {
            // there is no market entry for this planet, create one
            MarketDTO newMarket = new MarketDTO();
            newMarket.setLastVisited(new Date(System.currentTimeMillis()));
            newMarket.setPlanetId(result.getPlanet().getId());
            result.setMarket(newMarket);
        }
        MarketWindowPresenterImpl marketPresenter =
            new MarketWindowPresenterImpl(eventBus, new MarketWindowViewImpl(), result.getMarket());
        marketPresenter.getView().setTitle(
            constants.planetAdminPresenter_marketDetailsTitle() + result.getPlanet().getName());
        marketPresenter.renderView();
    }
}
