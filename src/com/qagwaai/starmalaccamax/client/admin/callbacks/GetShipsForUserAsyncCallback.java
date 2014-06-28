package com.qagwaai.starmalaccamax.client.admin.callbacks;

import com.qagwaai.starmalaccamax.client.admin.mvp.PlanetAdminViewImpl;
import com.qagwaai.starmalaccamax.client.service.action.GetShipsForUserResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;

public final class GetShipsForUserAsyncCallback extends BaseAsyncCallback<GetShipsForUserResponse> {

    private PlanetAdminViewImpl view;

    public GetShipsForUserAsyncCallback(PlanetAdminViewImpl view) {
        super();
        this.view = view;
    }

    @Override
    public void onFailure(final Throwable caught) {
        super.onFailure(caught);
        view.say("Error", "Could not load ships for this user: " + caught.getMessage());
    }

    @Override
    public void onSuccess(final GetShipsForUserResponse result) {
        super.onSuccess(result);
        for (ShipDTO ship : result.getShips()) {
            view.addContextMenuItem(ship);
        }

    }
}
