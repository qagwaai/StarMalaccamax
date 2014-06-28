package com.qagwaai.starmalaccamax.client.admin.callbacks;

import com.qagwaai.starmalaccamax.client.service.action.UpdateShipResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.smartgwt.client.util.SC;

public final class UpdateShipAsyncCallback extends BaseAsyncCallback<UpdateShipResponse> {
    private final PlanetDTO planet;
    private final ShipDTO ship;

    public UpdateShipAsyncCallback(PlanetDTO planet, ShipDTO ship) {
        super();
        this.planet = planet;
        this.ship = ship;
    }

    @Override
    public void onFailure(final Throwable caught) {
        super.onFailure(caught);
        SC.say("Error", "Could not update ship [" + ship.getName() + "] with location " + planet.getLocation());

    }

    @Override
    public void onSuccess(final UpdateShipResponse result) {
        super.onSuccess(result);
        SC.say("Success", "Ship updated");

    }
}
