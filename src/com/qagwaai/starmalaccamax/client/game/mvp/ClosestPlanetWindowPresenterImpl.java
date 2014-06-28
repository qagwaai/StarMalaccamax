/**
 * ClosestPlanetWindowPresenter.java
 * Created by pgirard at 11:50:16 AM on Oct 26, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.data.PlanetDistanceRecord;
import com.qagwaai.starmalaccamax.client.data.PlanetRecord;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.ClosestCloseClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.GetClosestPlanetsForPlanet;
import com.qagwaai.starmalaccamax.client.service.action.GetClosestPlanetsForPlanetResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.Planet;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDistanceDTO;

/**
 * @author pgirard
 * 
 */
public class ClosestPlanetWindowPresenterImpl extends AbstractPresenter<ClosestPlanetWindowView, Planet> implements ClosestPlanetWindowPresenter {



    /**
     * 
     * @param eventBus
     *            the bus to publish events on
     * @param view
     *            the view that this presenter will attach to
     * @param model
     *            the model to present, if any
     */
    public ClosestPlanetWindowPresenterImpl(final EventBus eventBus, final ClosestPlanetWindowViewImpl view, final Planet model) {
        super(eventBus, view, model);
        view.setPresenter(this);
        view.layout();

        ServiceFactory.getSolarSystemService().execute(new GetClosestPlanetsForPlanet((PlanetDTO) model),
            new BaseAsyncCallback<GetClosestPlanetsForPlanetResponse>() {

                @Override
                public void onFailure(final Throwable caught) {
                    super.onFailure(caught);
                    view.say("data", "Could not get closest planets");
                }

                @Override
                public void onSuccess(final GetClosestPlanetsForPlanetResponse result) {
                    super.onSuccess(result);
                    ArrayList<PlanetDistanceDTO> planets = result.getPlanets();
                    PlanetRecord[] records = new PlanetRecord[planets.size() + 1];
                    for (int i = 0; i < planets.size(); i++) {
                        records[i] = new PlanetDistanceRecord(planets.get(i));
                    }

                    view.getPlanetGrid().setData(records);
                }
            });
        view.getCloseButton().addClickHandler(new ClosestCloseClickHandlerImplementation(view));
        view.getCurrentLocationLabel().setContents("Current Location: " + model.getLocation());

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

}
