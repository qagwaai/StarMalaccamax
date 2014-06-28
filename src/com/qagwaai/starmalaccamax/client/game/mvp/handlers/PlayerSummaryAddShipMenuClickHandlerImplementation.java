package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenter;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.AddShip;
import com.qagwaai.starmalaccamax.client.service.action.AddShipResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.Captain;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;



/**
 * 
 * @author pgirard
 * 
 */
public final class PlayerSummaryAddShipMenuClickHandlerImplementation implements
    com.smartgwt.client.widgets.menu.events.ClickHandler {
    /**
	 * 
	 */
    private final PlayerSummaryViewImpl view;
    private final PlayerSummaryPresenter presenter;

    /**
     * 
     * @param view
     *            the view
     */
    public PlayerSummaryAddShipMenuClickHandlerImplementation(final PlayerSummaryViewImpl view, final PlayerSummaryPresenter presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final MenuItemClickEvent event) {
        ListGridRecord record = view.getListGrid().getSelectedRecord();
        Object objCaptain = record.getAttributeAsObject(PlayerSummaryPresenterImpl.CAPTAIN_OBJ);
        if (objCaptain instanceof Captain) {

            ShipDTO ship = new ShipDTO();
            ship.setName("Sample");
            ship.setShipTypeId(10L);
            ship.setOwnerId(((Captain) objCaptain).getId());
            ServiceFactory.getShipService().execute(new AddShip(ship), new BaseAsyncCallback<AddShipResponse>() {

                @Override
                public void onFailure(final Throwable caught) {
                    super.onFailure(caught);
                    ErrorPresenter.present(caught);
                }

                @Override
                public void onSuccess(final AddShipResponse result) {
                    super.onSuccess(result);
                    view.alert("Ship Added");
                    view.getListGrid().setData(new ListGridRecord[0]);
                    presenter.loadSummaryListGrid();
                }
            });
        }
    }
}
