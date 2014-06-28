/**
 * PlayerOpportunitiesPresenter.java
 * Created by pgirard at 1:12:07 PM on Dec 2, 2010
 * in the com.qagwaai.starmalaccamax.client.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.callbacks.PlayerOppGetShipsAsyncCallbackImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerOppCargoRecordClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.GetShipsForUser;
import com.qagwaai.starmalaccamax.shared.model.Ship;
import com.qagwaai.starmalaccamax.shared.model.ShipCargoDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipTravelStatus;
import com.qagwaai.starmalaccamax.shared.model.User;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class PlayerOpportunitiesPresenterImpl extends AbstractPresenter<PlayerOpportunitiesView, User> implements PlayerOpportunitiesPresenter {


    /**
     * 
     * @param eventBus
     *            the bus to publish/listen to events
     * @param view
     *            the associated view
     * @param model
     *            the user model
     */
    public PlayerOpportunitiesPresenterImpl(final EventBus eventBus, final PlayerOpportunitiesViewImpl view, final User model) {
        super(eventBus, view, model);
        view.setPresenter(this);
        view.layout();
        final PlayerOpportunitiesPresenter presenter = this;

        ServiceFactory.getShipService().execute(new GetShipsForUser((UserDTO) model),
            new PlayerOppGetShipsAsyncCallbackImplementation(view, this));
        view.getCargoGrid().addRecordClickHandler(new PlayerOppCargoRecordClickHandlerImplementation(view));
        view.getRefreshButton().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                view.getCargoGrid().setData(new ListGridRecord[] {});
                ServiceFactory.getShipService().execute(new GetShipsForUser((UserDTO) model),
                    new PlayerOppGetShipsAsyncCallbackImplementation(view, presenter));
            }
        });
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void destroyView() {
        getView().destroy();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void hideView() {
        getView().hide();

    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerOpportunitiesPresenter#loadCargoGrid(java.util.ArrayList)
	 */
    @Override
	public void loadCargoGrid(final ArrayList<ShipDTO> ships) {

        ArrayList<ListGridRecord> data = new ArrayList<ListGridRecord>();
        for (Ship ship : ships) {
            for (ShipCargoDTO cargo : ship.getCargo()) {
                ListGridRecord record = new ListGridRecord();
                record.setAttribute(PlayerOpportunitiesViewImpl.SHIP_NAME, ship.getName());
                record.setAttribute(PlayerOpportunitiesViewImpl.CARGO_COMMODITY, cargo.getCommodity());
                record.setAttribute(PlayerOpportunitiesViewImpl.CARGO_AMOUNT, cargo.getAmount());
                record.setAttribute(PlayerOpportunitiesViewImpl.CARGO_PURCHASE_PRICE, cargo.getPurchasePrice());
                record.setAttribute(PlayerOpportunitiesViewImpl.CARGO_PURCHASE_DATE, cargo.getPurchaseDate());
                record.setAttribute(PlayerOpportunitiesViewImpl.SHIP_OBJ, ship);
                record.setAttribute(PlayerOpportunitiesViewImpl.CARGO_OBJ, cargo);
                data.add(record);
            }
        }
        getView().getCargoGrid().setData(data.toArray(new ListGridRecord[data.size()]));
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerOpportunitiesPresenter#loadJobBoard(java.util.ArrayList)
	 */
    @Override
	public void loadJobBoard(final ArrayList<ShipDTO> ships) {
        for (Ship ship : ships) {
            if (ship.getShipTravelStatus().equals(ShipTravelStatus.Docked)) {
                // TODO use real job board data
                ArrayList<ListGridRecord> data = new ArrayList<ListGridRecord>();
                ListGridRecord record = new ListGridRecord();
                record.setAttribute("jobName", "Planet X needs cargo");
                record.setAttribute("jobType", "Transport");
                record.setAttribute("jobDescription", "Transport 50 units of livestock");
                record.setAttribute("jobPay", "1,342,234&nbsp;&OElig;");
                record.setAttribute("jobDuration", "5 days");
                data.add(record);
                getView().getJobGrid().setData(data.toArray(new ListGridRecord[data.size()]));
            }
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void renderView() {
        getView().render();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void showView() {
        getView().show();

    }

}
