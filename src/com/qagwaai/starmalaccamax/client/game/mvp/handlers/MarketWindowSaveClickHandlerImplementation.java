package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import java.util.Date;

import com.qagwaai.starmalaccamax.client.data.MarketDS;
import com.qagwaai.starmalaccamax.client.game.mvp.MarketWindowViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.UpdateMarket;
import com.qagwaai.starmalaccamax.client.service.action.UpdateMarketResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.Market;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;



/**
 * 
 * @author pgirard
 * 
 */
public final class MarketWindowSaveClickHandlerImplementation implements ClickHandler {
    /**
	 * 
	 */
    private final Market model;
    /**
	 * 
	 */
    private final MarketWindowViewImpl view;

    /**
     * 
     * @param model
     *            the model to update
     * @param view
     *            the view
     */
    public MarketWindowSaveClickHandlerImplementation(final Market model, final MarketWindowViewImpl view) {
        this.model = model;
        this.view = view;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final ClickEvent event) {
        Object obj = view.getForm().getValue("lastVisited");
        if (obj instanceof Date) {
            model.setLastVisited((Date) obj);
        }
        ListGridRecord[] data = view.getCommodityGrid().getRecords();
        MarketDS.fromGrid((MarketDTO) model, data);

        ServiceFactory.getMarketService().execute(new UpdateMarket((MarketDTO) model),
            new BaseAsyncCallback<UpdateMarketResponse>() {

                @Override
                public void onFailure(final Throwable caught) {
                    super.onFailure(caught);
                    view.alert("Could not save market: " + caught.getMessage());

                }

                @Override
                public void onSuccess(final UpdateMarketResponse result) {
                    super.onSuccess(result);
                    view.destroy();
                }
            });
    }
}
