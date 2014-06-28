package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import java.util.Date;

import com.google.gwt.user.client.Random;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenter;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.UpdateShip;
import com.qagwaai.starmalaccamax.client.service.action.UpdateShipResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.Commodities;
import com.qagwaai.starmalaccamax.shared.model.Ship;
import com.qagwaai.starmalaccamax.shared.model.ShipCargoDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;



/**
 * 
 * @author pgirard
 * 
 */
public final class PlayerSummaryAddCargoMenuClickHandlerImplementation implements
    com.smartgwt.client.widgets.menu.events.ClickHandler {
    /**
	 * 
	 */
    private final PlayerSummaryViewImpl view;
    private final PlayerSummaryPresenter presenter;

    /**
     * 
     * @param view
     *            the associated view
     */
    public PlayerSummaryAddCargoMenuClickHandlerImplementation(final PlayerSummaryViewImpl view, final PlayerSummaryPresenter presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    @Override
    public void onClick(final MenuItemClickEvent event) {

        ListGridRecord record = view.getListGrid().getSelectedRecord();
        Object objShip = record.getAttributeAsObject(PlayerSummaryPresenterImpl.SHIP_OBJ);
        if (objShip != null) {
            if (objShip instanceof Ship) {
                ShipDTO ship = (ShipDTO) objShip;
                ShipCargoDTO newItem = new ShipCargoDTO();
                newItem.setAmount(Random.nextInt(100));
                int commodityIndex = Random.nextInt(Commodities.values().length);
                Commodities commodity = Commodities.values()[commodityIndex];
                newItem.setCommodity(commodity.getKey());
                newItem.setPurchaseDate(new Date(System.currentTimeMillis()));
                newItem.setPurchasePrice(Random.nextInt(100));
                ship.getCargo().add(newItem);
                ServiceFactory.getShipService().execute(new UpdateShip(ship),
                    new BaseAsyncCallback<UpdateShipResponse>() {

                        @Override
                        public void onFailure(final Throwable caught) {
                            super.onFailure(caught);
                            view.say("Error", "Could not add cargo: " + caught.getMessage());

                        }

                        @Override
                        public void onSuccess(final UpdateShipResponse result) {
                            super.onSuccess(result);
                            view.say("Success", "Cargo added");
                            view.getListGrid().setData(new ListGridRecord[0]);
                            presenter.loadSummaryListGrid();
                        }
                    });
            }
        }
    }
}
