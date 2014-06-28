package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.data.ShipDS;
import com.qagwaai.starmalaccamax.client.game.mvp.ShipWindowViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.UpdateShip;
import com.qagwaai.starmalaccamax.client.service.action.UpdateShipResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.Ship;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;



/**
 * 
 * @author pgirard
 * 
 */
public final class ShipWindowSaveClickHandlerImplementation implements ClickHandler {
    /**
     * 
     */
    private final Ship model;
    /**
     * 
     */
    private final ShipWindowViewImpl view;

    /**
     * 
     * @param model
     *            the model to update
     * @param view
     *            the view
     */
    public ShipWindowSaveClickHandlerImplementation(final Ship model, final ShipWindowViewImpl view) {
        this.model = model;
        this.view = view;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final ClickEvent event) {

        model.getLocation().setSolarSystemId(
            Long.valueOf((String) view.getLocationItem().getSolarSystemItem().getValue()));
        model.getLocation().setPlanetId(Long.valueOf((String) view.getLocationItem().getPlanetItem().getValue()));
        model.getLocation().setX(Long.valueOf((String) view.getLocationItem().getXItem().getValue()));
        model.getLocation().setY(Long.valueOf((String) view.getLocationItem().getYItem().getValue()));
        model.getLocation().setZ(Long.valueOf((String) view.getLocationItem().getZItem().getValue()));
        ShipDS.fromAttributeGrid((ShipDTO) model, view.getAttributeGrid().getRecords());
        ShipDS.fromCargoGrid((ShipDTO) model, view.getCargoGrid().getRecords());

        ServiceFactory.getShipService().execute(new UpdateShip((ShipDTO) model), new BaseAsyncCallback<UpdateShipResponse>() {

            @Override
            public void onFailure(final Throwable caught) {
                super.onFailure(caught);
                view.alert("Could not save ship: " + caught.getMessage());

            }

            @Override
            public void onSuccess(final UpdateShipResponse result) {
                super.onSuccess(result);
                view.destroy();
            }
        });
    }
}
