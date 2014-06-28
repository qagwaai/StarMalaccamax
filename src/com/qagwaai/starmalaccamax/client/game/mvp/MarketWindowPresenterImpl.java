/**
 * MarketWindowPresenter.java
 * Created by pgirard at 11:50:16 AM on Oct 26, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import java.util.Map;

import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.data.MarketDS;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.MarketWindowCloseClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.MarketWindowSaveClickHandlerImplementation;
import com.qagwaai.starmalaccamax.shared.model.Market;
import com.qagwaai.starmalaccamax.shared.model.MarketCommodityDTO;

/**
 * @author pgirard
 * 
 */
public class MarketWindowPresenterImpl extends AbstractPresenter<MarketWindowView, Market> implements MarketWindowPresenter {


    /**
     * 
     * @param eventBus
     *            the bus to publish events on
     * @param view
     *            the view that this presenter will attach to
     * @param model
     *            the model to present, if any
     */
    public MarketWindowPresenterImpl(final EventBus eventBus, final MarketWindowViewImpl view, final Market model) {
        super(eventBus, view, model);
        view.setPresenter(this);
        view.layout();

        view.getSaveButton().addClickHandler(new MarketWindowSaveClickHandlerImplementation(model, view));

        view.getCloseButton().addClickHandler(new MarketWindowCloseClickHandlerImplementation(view));

        if (model != null) {
            view.getCommodityGrid().setData(MarketDS.toGrid((Map<String, MarketCommodityDTO>) model));
            if (model.getLastVisited() != null) {
                view.getForm().setValue("lastVisited", model.getLastVisited().toString());
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

}
