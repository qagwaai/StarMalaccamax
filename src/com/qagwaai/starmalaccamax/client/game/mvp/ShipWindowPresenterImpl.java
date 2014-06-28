/**
 * ShipWindowPresenter.java
 * Created by pgirard at 11:50:16 AM on Oct 26, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.data.ShipDS;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.ShipWindowCloseClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.ShipWindowSaveClickHandlerImplementation;
import com.qagwaai.starmalaccamax.shared.model.Ship;
import com.qagwaai.starmalaccamax.shared.model.ShipCargoDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public class ShipWindowPresenterImpl extends AbstractPresenter<ShipWindowView, Ship> implements ShipWindowPresenter {


    /**
     * 
     * @param eventBus
     *            the bus to publish events on
     * @param view
     *            the view that this presenter will attach to
     * @param model
     *            the model to present, if any
     */
    public ShipWindowPresenterImpl(final EventBus eventBus, final ShipWindowViewImpl view, final Ship model) {
        super(eventBus, view, model);
        view.setPresenter(this);
        view.layout();

        view.getSaveButton().addClickHandler(new ShipWindowSaveClickHandlerImplementation(model, view));

        view.getCloseButton().addClickHandler(new ShipWindowCloseClickHandlerImplementation(view));

        if (model != null) {
            view.getAttributeGrid().setData(ShipDS.toAttributeGrid((ShipDTO) model));
            //view.getCargoGrid().setData(ShipDS.toCargoGrid(model));

            view.getNamePropertyField().setValue(model.getName());
            view.getLocationItem().setValue(model.getLocation());
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

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ShipWindowPresenter#getCargoGridData(java.lang.String)
	 */
    @Override
	public final ListGridRecord[] getCargoGridData(final String commodity) {
        if (getModel() != null) {
            Ship ship = getModel();
            ArrayList<ListGridRecord> data = new ArrayList<ListGridRecord>();
            for (ShipCargoDTO cargo : ship.getCargo()) {
                if (cargo.getCommodity().equals(commodity)) {
                    data.add(ShipDS.createCargoRow(cargo));
                }
            }
            if (data.size() > 0) {
                return data.toArray(new ListGridRecord[1]);
            } else {
                return new ListGridRecord[1];
            }

        }
        return new ListGridRecord[1];
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
