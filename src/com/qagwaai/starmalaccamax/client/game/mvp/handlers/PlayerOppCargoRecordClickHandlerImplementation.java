package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.client.core.util.ClientUtil;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerOpportunitiesViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.GetLocalOpportunitiesForCargo;
import com.qagwaai.starmalaccamax.client.service.action.GetLocalOpportunitiesForCargoResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.MarketOpportunityForShipDTO;
import com.qagwaai.starmalaccamax.shared.model.Ship;
import com.qagwaai.starmalaccamax.shared.model.ShipCargoDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;


/**
 * @author pgirard
 * 
 */
public final class PlayerOppCargoRecordClickHandlerImplementation implements RecordClickHandler {
    /**
	 * 
	 */
    private final PlayerOpportunitiesViewImpl view;

    /**
     * @param view
     *            the associated view
     */
    public PlayerOppCargoRecordClickHandlerImplementation(final PlayerOpportunitiesViewImpl view) {
        this.view = view;
    }

    @Override
    public void onRecordClick(final RecordClickEvent event) {
        ClientUtil.serviceCallStart("GetLocalOpportunitiesForCargo");
        ListGridRecord record = view.getCargoGrid().getSelectedRecord();
        Object shipObject = record.getAttributeAsObject(PlayerOpportunitiesViewImpl.SHIP_OBJ);
        Object cargoObject = record.getAttributeAsObject(PlayerOpportunitiesViewImpl.CARGO_OBJ);
        if ((shipObject instanceof Ship) && (cargoObject instanceof ShipCargoDTO)) {
            view.getOpportunityGrid().setEmptyMessage("Loading records...");
            view.getOpportunityGrid().setData(new ListGridRecord[] {});
            ShipDTO ship = (ShipDTO) shipObject;
            ShipCargoDTO cargo = (ShipCargoDTO) cargoObject;
            ServiceFactory.getMarketService().execute(new GetLocalOpportunitiesForCargo(cargo, ship),
                new BaseAsyncCallback<GetLocalOpportunitiesForCargoResponse>() {

                    @Override
                    public void onFailure(final Throwable caught) {
                        super.onFailure(caught);
                        view.say("Error", "Could not get opportunities for cargo: " + caught.getMessage());
                    }

                    @Override
                    public void onSuccess(final GetLocalOpportunitiesForCargoResponse result) {
                        super.onSuccess(result);
                        ClientUtil.serviceCallEnd("GetLocalOpportunitiesForCargo Data Serialized");
                        if (result.getOpportunities().size() == 0) {
                            view.getOpportunityGrid().setEmptyMessage("No records...");
                            view.setOpportunityGridTitle(result.getCargo().getCommodity());
                        } else {
                            ArrayList<ListGridRecord> opportunities = new ArrayList<ListGridRecord>();
                            for (MarketOpportunityForShipDTO opportunity : result.getOpportunities()) {
                                ListGridRecord record = new ListGridRecord();
                                // record.setAttribute(PlayerOpportunitiesView.PLANET_OBJ,
                                // opportunity.getPlanet());
                                record.setAttribute(PlayerOpportunitiesViewImpl.PLANET_NAME, opportunity.getPlanet()
                                    .getName());
                                record.setAttribute(PlayerOpportunitiesViewImpl.DISTANCE, opportunity.getDistance()
                                    .getDistanceInKM());
                                record.setAttribute(PlayerOpportunitiesViewImpl.CARGO_AMOUNT,
                                    opportunity.getAmountAvailable());
                                record.setAttribute(PlayerOpportunitiesViewImpl.CARGO_SALE_PRICE,
                                    opportunity.getSalePrice());
                                record.setAttribute(PlayerOpportunitiesViewImpl.CARGO_PROFIT,
                                    opportunity.getSalePrice() - result.getCargo().getPurchasePrice());
                                record.setAttribute(PlayerOpportunitiesViewImpl.DISTANCE, opportunity.getDistance()
                                    .getDistanceInKM());
                                record.setAttribute(PlayerOpportunitiesViewImpl.LOCATION, opportunity.getPlanet()
                                    .getLocation());
                                opportunities.add(record);
                            }

                            view.getOpportunityGrid().setData(
                                opportunities.toArray(new ListGridRecord[opportunities.size()]));
                            view.setOpportunityGridTitle(result.getCargo().getCommodity());
                        }
                        ClientUtil.serviceCallEnd("GetLocalOpportunitiesForCargo Data Displayed");
                    }
                });

        } else {
            view.say("Configuration Error", "Cargo did not store correct ship");
        }

    }
}
