package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.data.MarketDS;
import com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowPresenter;
import com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.UpdateMarket;
import com.qagwaai.starmalaccamax.client.service.action.UpdateMarketResponse;
import com.qagwaai.starmalaccamax.client.service.action.UpdatePlanet;
import com.qagwaai.starmalaccamax.client.service.action.UpdatePlanetResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.qagwaai.starmalaccamax.shared.model.Planet;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;



/**
 * 
 * @author pgirard
 * 
 */
public final class PlanetWindowSaveClickHandlerImplementation implements ClickHandler {
    /**
	 * 
	 */
    private final Planet model;
    /**
	 * 
	 */
    private final PlanetWindowViewImpl view;
    
    private final PlanetWindowPresenter presenter;

    /**
     * 
     * @param model
     *            the model to update
     * @param view
     *            the view
     */
    public PlanetWindowSaveClickHandlerImplementation(final Planet model, final PlanetWindowViewImpl view, final PlanetWindowPresenter presenter) {
        this.model = model;
        this.view = view;
        this.presenter = presenter;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final ClickEvent event) {
        model.setName((String) view.getNamePropertyField().getValue());
        model.setAtmosphere((Integer) view.getAtmosphereField().getValue());
        model.setDockQuality((Integer) view.getDockQualityField().getValue());
        model.setGovernment((Integer) view.getGovernmentField().getValue());
        model.setHydrographics(Double.valueOf((Integer) view.getHydrographicsField().getValue()));
        model.setLawLevel((Integer) view.getLawLevelField().getValue());
        model.setOrbit((Integer) view.getOrbitField().getValue());
        model.setPopulation(Long.valueOf((Integer) view.getPopulationField().getValue()));
        model.setSize((Integer) view.getSizeField().getValue());
        model.setTechLevel((Integer) view.getTechLevelField().getValue());
        model.setGasGiant((Boolean) view.getGasGiantField().getValue());
        model.setMainWorld((Boolean) view.getMainWorldField().getValue());
        model.setSatellite((Boolean) view.getSatelliteField().getValue());
        ServiceFactory.getPlanetService().execute(new UpdatePlanet((PlanetDTO) model),
            new BaseAsyncCallback<UpdatePlanetResponse>() {

                @Override
                public void onFailure(final Throwable caught) {
                    super.onFailure(caught);
                    view.alert("Could not save planet: " + caught.getMessage());

                }

                @Override
                public void onSuccess(final UpdatePlanetResponse result) {
                    super.onSuccess(result);
                }
            });
        if (presenter.getMarketModel() != null) {
            ListGridRecord[] data = view.getCommodityGrid().getRecords();
            MarketDS.fromGrid((MarketDTO) presenter.getMarketModel(), data);

            ServiceFactory.getMarketService().execute(new UpdateMarket((MarketDTO) presenter.getMarketModel()),
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
}
