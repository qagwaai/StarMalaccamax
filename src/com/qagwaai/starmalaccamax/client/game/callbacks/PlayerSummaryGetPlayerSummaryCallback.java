package com.qagwaai.starmalaccamax.client.game.callbacks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.visualization.client.DataTable;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryView;
import com.qagwaai.starmalaccamax.client.game.mvp.widget.PieChartWidget;
import com.qagwaai.starmalaccamax.client.service.action.GetPlayerSummaryPageResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.Captain;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.Commodities;
import com.qagwaai.starmalaccamax.shared.model.GameEvent;
import com.qagwaai.starmalaccamax.shared.model.Ship;
import com.qagwaai.starmalaccamax.shared.model.ShipCargoDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.smartgwt.client.widgets.calendar.CalendarEvent;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * 
 * @author pgirard
 * 
 */
public final class PlayerSummaryGetPlayerSummaryCallback extends BaseAsyncCallback<GetPlayerSummaryPageResponse> {
    /**
     * 
     * @author pgirard
     * 
     */
    private final class PieChartTimerExtension extends Timer {
        /**
		 * 
		 */
        private final Map<String, Integer> ownedResources;

        /**
         * 
         * @param ownedResources
         *            the resources to display in the chart
         */
        private PieChartTimerExtension(final Map<String, Integer> ownedResources) {
            this.ownedResources = ownedResources;
        }

        @Override
        public void run() {
            PieChartWidget resourcesPie = view.getResourcesPie();
            if (resourcesPie == null) {
                GWT.log("still waiting on pie charts...");
                this.schedule(1000);
            } else {
                DataTable ownedData = view.getResourcesPie().getData();
                ownedData.removeRows(0, ownedData.getNumberOfRows());
                // ownedData.addColumn(ColumnType.STRING, "Resource");
                // ownedData.addColumn(ColumnType.NUMBER, "Count");
                ownedData.addRows(ownedResources.size());
                int row = 0;
                for (Map.Entry<String, Integer> cargoEntry : ownedResources.entrySet()) {
                    ownedData.setValue(row, 0, cargoEntry.getKey());
                    ownedData.setValue(row, 1, cargoEntry.getValue());
                    row++;
                }
                resourcesPie.redraw();
                // view.getResourcesPie().draw(ownedData);

                DataTable costsData = view.getCostsPie().getData();
                costsData.removeRows(0, costsData.getNumberOfRows());
                costsData.addRows(3);
                costsData.setValue(0, 0, "Fuel");
                costsData.setValue(0, 1, 57);
                costsData.setValue(1, 0, "Salaries");
                costsData.setValue(1, 1, 105);
                costsData.setValue(2, 0, "Docking Fees");
                costsData.setValue(2, 1, 76);

                view.getCostsPie().redraw();
                this.cancel();
            }
        }
    }

    /**
	 * 
	 */
    private final PlayerSummaryView view;

    /**
     * 
     * @param view
     *            the view
     */
    public PlayerSummaryGetPlayerSummaryCallback(final PlayerSummaryView view) {
        super();
        this.view = view;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onFailure(final Throwable caught) {
        super.onFailure(caught);
        view.alert("Could not load player summary");
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onSuccess(final GetPlayerSummaryPageResponse result) {
        super.onSuccess(result);
        // Summary Grid
        ArrayList<ListGridRecord> data = new ArrayList<ListGridRecord>();
        ListGridRecord[] records = new ListGridRecord[1];
        ArrayList<CaptainDTO> captains = result.getCaptains();
        if (captains.size() > 0) {
            for (Captain captain : captains) {
                ListGridRecord record = new ListGridRecord();
                record.setAttribute(PlayerSummaryPresenterImpl.CAPTAIN_NAME, captain.getName());
                record.setAttribute(PlayerSummaryPresenterImpl.CAPTAIN_ID, captain.getId());
                record.setAttribute(PlayerSummaryPresenterImpl.CAPTAIN_OBJ, captain);
                ArrayList<ShipDTO> ships = result.getShips();
                if (ships.size() > 0) {
                    boolean found = false;
                    for (Ship ship : ships) {
                        if (ship != null) {
                            if (ship.getOwnerId().equals(captain.getId())) {
                                found = true;
                                record.setAttribute(PlayerSummaryPresenterImpl.SHIP_ID, ship.getId());
                                record.setAttribute(PlayerSummaryPresenterImpl.SHIP_NAME, ship.getName());
                                record.setAttribute(PlayerSummaryPresenterImpl.SHIP_STATUS, ship.getShipTravelStatus()
                                    .toString());
                                record.setAttribute(PlayerSummaryPresenterImpl.SHIP_LOCATION, ship.getLocation()
                                    .toString());
                                record.setAttribute(PlayerSummaryPresenterImpl.SHIP_OBJ, ship);
                                record.setAttribute(PlayerSummaryPresenterImpl.LOCATION_OBJ, ship.getLocation());
                            }
                        }
                    }
                    if (!found) {
                        record.setAttribute(PlayerSummaryPresenterImpl.SHIP_NAME, "<None>");
                    }
                }
                data.add(record);
            }
            records = data.toArray(new ListGridRecord[captains.size()]);
        }
        view.getListGrid().setData(records);

        // charts
        // TODO do we want to move this to the backend?
        final Map<String, Integer> ownedResources = new HashMap<String, Integer>();
        for (Commodities entry : Commodities.values()) {
            ownedResources.put(entry.getKey(), new Integer(0));
        }
        for (Ship ship : result.getShips()) {
            if (ship != null) {
                ArrayList<ShipCargoDTO> commodities = ship.getCargo();
                for (ShipCargoDTO cargo : commodities) {
                    Integer current = ownedResources.get(cargo.getCommodity());
                    ownedResources.put(cargo.getCommodity(), current + cargo.getAmount());
                }
            }
        }

        CalendarEvent[] calData = new CalendarEvent[result.getEvents().size()];
        for (int i = 0; i < result.getEvents().size(); i++) {
            GameEvent gameEvent = result.getEvents().get(i);
            CalendarEvent newEvent =
                new CalendarEvent(gameEvent.getId().intValue(), gameEvent.getShortDescription(),
                    gameEvent.getFullDescription(), gameEvent.getStartDateTime(),
                    gameEvent.getActivityCompletionDate());
            newEvent.setAttribute(PlayerSummaryPresenterImpl.GAME_EVENT_OBJ, gameEvent);
            calData[i] = newEvent;
        }
        view.getEventCalendar().setData(calData);

        PieChartWidget resourcesPie = view.getResourcesPie();
        Timer t = new PieChartTimerExtension(ownedResources);
        if (resourcesPie == null) {
            GWT.log("pie chart not there... waiting 1000ms");
            // not there yet...
            t.schedule(1000);
        } else {
            t.run();
            GWT.log("pie chart widget available at beginning of load");
        }

    }
}
