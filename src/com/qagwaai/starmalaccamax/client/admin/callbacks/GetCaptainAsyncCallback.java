package com.qagwaai.starmalaccamax.client.admin.callbacks;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.qagwaai.starmalaccamax.client.admin.constants.AdminConstants;
import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.CaptainWindowPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.CaptainWindowViewImpl;
import com.qagwaai.starmalaccamax.client.service.action.GetCaptainResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;

public final class GetCaptainAsyncCallback extends BaseAsyncCallback<GetCaptainResponse> {
    private final EventBus eventBus;
    private static AdminConstants constants = GWT.create(AdminConstants.class);

    public GetCaptainAsyncCallback(final EventBus eventBus) {
        super();
        this.eventBus = eventBus;
    }

    @Override
    public void onFailure(final Throwable caught) {
        super.onFailure(caught);
        Window.alert(constants.captainAdminPresenter_error_captain_details() + caught.getMessage());
        ErrorPresenter.present(caught);

    }

    @Override
    public void onSuccess(final GetCaptainResponse result) {
        super.onSuccess(result);
        CaptainWindowPresenterImpl captainPresenter =
            new CaptainWindowPresenterImpl(eventBus, new CaptainWindowViewImpl(), result.getCaptain());
        captainPresenter.getView().setTitle(constants.captainAdminPresenter_captainWindowTitle());
        captainPresenter.renderView();
    }
}