/**
 * PlanetWindowPresenter.java
 * Created by pgirard at 11:50:16 AM on Oct 26, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import java.util.Date;

import com.google.gwt.user.client.Window;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.data.MarketDS;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlanetWindowCloseClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlanetWindowSaveClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.GetMarketForPlanet;
import com.qagwaai.starmalaccamax.client.service.action.GetMarketForPlanetResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.Market;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.qagwaai.starmalaccamax.shared.model.Planet;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * @author pgirard
 * 
 */
public class PlanetWindowPresenterImpl extends AbstractPresenter<PlanetWindowView, Planet> implements PlanetWindowPresenter {


    /**
	 * 
	 */
    private Market marketModel;

    /**
     * 
     * @param eventBus
     *            the bus to publish events on
     * @param view
     *            the view that this presenter will attach to
     * @param model
     *            the model to present, if any
     */
    public PlanetWindowPresenterImpl(final EventBus eventBus, final PlanetWindowViewImpl view, final Planet model) {
        super(eventBus, view, model);
        view.setPresenter(this);
        view.layout();

        view.getSaveButton().addClickHandler(new PlanetWindowSaveClickHandlerImplementation(model, view, this));

        view.getCloseButton().addClickHandler(new PlanetWindowCloseClickHandlerImplementation(view));

        if (model != null) {
            // view.getAttributeGrid().setData(PlanetDS.toAttributeGrid(model));
            // view.getCargoGrid().setData(PlanetDS.toCargoGrid(model));
            if (model.isGasGiant()) {
                view.getPlanetImage().setSrc("Jupiter-june.gif");
                view.getPlanetImage().setWidth(75);
                view.getPlanetImage().setHeight(75);
            } else {
                view.getPlanetImage().setSrc("Earth-02-june.gif");
                view.getPlanetImage().setHeight(85);
                view.getPlanetImage().setWidth(180);
            }

            view.getNamePropertyField().setValue(model.getName());
            view.getAtmosphereField().setValue(model.getAtmosphere());
            view.getDockQualityField().setValue(model.getDockQuality());
            view.getGovernmentField().setValue(model.getGovernment());
            view.getHydrographicsField().setValue(model.getHydrographics());
            view.getLawLevelField().setValue(model.getLawLevel());
            view.getOrbitField().setValue(model.getOrbit());
            view.getPopulationField().setValue(model.getPopulation());
            view.getSizeField().setValue(model.getSize());
            view.getTechLevelField().setValue(model.getTechLevel());
            view.getGasGiantField().setValue(model.isGasGiant());
            view.getMainWorldField().setValue(model.isMainWorld());
            view.getSatelliteField().setValue(model.isSatellite());
            if (!model.isGasGiant()) {
                ServiceFactory.getMarketService().execute(new GetMarketForPlanet((PlanetDTO) model),
                    new BaseAsyncCallback<GetMarketForPlanetResponse>() {

                        @Override
                        public void onFailure(final Throwable caught) {
                            super.onFailure(caught);
                            Window.alert("Could not show markets for planet: " + caught.getMessage());
                            ErrorPresenter.present(caught);

                        }

                        @Override
                        public void onSuccess(final GetMarketForPlanetResponse result) {
                            super.onSuccess(result);
                            if (result.getMarket() == null) {
                                // there is no market entry for this planet,
                                // create one
                                MarketDTO newMarket = new MarketDTO();
                                newMarket.setLastVisited(new Date(System.currentTimeMillis()));
                                newMarket.setPlanetId(result.getPlanet().getId());
                                result.setMarket(newMarket);
                            }
                            view.getCommodityGrid().setData(MarketDS.toGrid(result.getMarket()));
                            marketModel = result.getMarket();
                        }
                    });
            } else {
                view.getSectionStack().removeSection(2);
            }
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void destroyView() {
        getView().destroy();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void hideView() {
        getView().hide();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void renderView() {
        getView().render();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void showView() {
        getView().show();
    }

	/* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowPresenter#getMarketModel()
	 */
	@Override
	public Market getMarketModel() {
		return marketModel;
	}

	/* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowPresenter#setMarketModel(com.qagwaai.starmalaccamax.shared.model.Market)
	 */
	@Override
	public void setMarketModel(Market marketModel) {
		this.marketModel = marketModel;
	}

}
