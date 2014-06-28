/**
 * UserDS.java
 * Created by pgirard at 2:49:07 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.data package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import java.util.ArrayList;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Window;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.event.ShipAddedEvent;
import com.qagwaai.starmalaccamax.client.event.ShipRemovedEvent;
import com.qagwaai.starmalaccamax.client.event.ShipUpdatedEvent;
import com.qagwaai.starmalaccamax.client.event.ShipsLoadedEvent;
import com.qagwaai.starmalaccamax.client.game.mvp.ShipWindowViewImpl;
import com.qagwaai.starmalaccamax.client.service.ShipServiceAsync;
import com.qagwaai.starmalaccamax.client.service.action.AddShip;
import com.qagwaai.starmalaccamax.client.service.action.AddShipResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllShips;
import com.qagwaai.starmalaccamax.client.service.action.GetAllShipsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveShip;
import com.qagwaai.starmalaccamax.client.service.action.RemoveShipResponse;
import com.qagwaai.starmalaccamax.client.service.action.UpdateShip;
import com.qagwaai.starmalaccamax.client.service.action.UpdateShipResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.AddedShip;
import com.qagwaai.starmalaccamax.client.service.helpers.GotAllShips;
import com.qagwaai.starmalaccamax.client.service.helpers.RemovedShip;
import com.qagwaai.starmalaccamax.client.service.helpers.UpdatedShip;
import com.qagwaai.starmalaccamax.shared.model.Commodities;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.ShipAttributeBucket;
import com.qagwaai.starmalaccamax.shared.model.ShipCargoDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class ShipDS extends GwtRpcDataSource {

    /**
	 * 
	 */
    public static final String DS_TYPE = "Ship";

    /**
     * 
     * @param from
     *            to copy from
     * @param to
     *            to copy to
     * @throws DataException
     *             if the operation failed
     */
    private static void copyValues(final ListGridRecord from, final ShipRecord to) throws DataException {
        to.setId(LongConverter.fromJavaScript(from, ShipTypeRecord.ID));
        to.setOwnerId(LongConverter.fromJavaScript(from, ShipRecord.OWNER_ID));
        to.setName(from.getAttributeAsString(ShipRecord.NAME));
        to.setShipTypeId(LongConverter.fromJavaScript(from, ShipRecord.SHIP_TYPE_ID));
    }

    /**
     * 
     * @param name
     *            the name of the attribute
     * @param value
     *            the value of the attribute
     * @return a hydrated ListGridRecord
     */
    private static ListGridRecord createAttributeRow(final String name, final Integer value) {
        ListGridRecord record = new ListGridRecord();
        record.setAttribute(ShipWindowViewImpl.CARGO_COMMODITY, name);
        record.setAttribute(ShipWindowViewImpl.CARGO_AMOUNT, value);
        return record;

    }

    /**
     * 
     * @param cargo
     *            the cargo to create the row from
     * @return the hydrated row
     */
    public static ListGridRecord createCargoRow(final ShipCargoDTO cargo) {
        ListGridRecord record = new ListGridRecord();
        record.setAttribute(ShipWindowViewImpl.CARGO_COMMODITY, cargo.getCommodity());
        record.setAttribute(ShipWindowViewImpl.CARGO_PURCHASE_DATE, cargo.getPurchaseDate());
        record.setAttribute(ShipWindowViewImpl.CARGO_PURCHASE_PRICE, cargo.getPurchasePrice());
        record.setAttribute(ShipWindowViewImpl.CARGO_AMOUNT, cargo.getAmount());
        Commodities commodityType = Commodities.getFromKey(cargo.getCommodity());
        if (cargo.getCommodity() != null) {
            record.setAttribute("category", commodityType.getCategory());
        } else {
            record.setAttribute("category", "unknown");
        }
        return record;

    }

    /**
     * 
     * @param data
     *            the record to extract from
     * @return the hydrated commodity
     */
    private static Integer extractAttributeData(final ListGridRecord data) {
        return data.getAttributeAsInt(ShipWindowViewImpl.CARGO_AMOUNT);
    }

    /**
     * 
     * @param data
     *            the record to extract from
     * @return the hydrated commodity
     */
    private static ShipCargoDTO extractCargoData(final ListGridRecord data) {
        // TODO fix this
        /*
         * Object value = data.getAttributeAsObject("value"); if (value
         * instanceof Integer) { return (Integer) value; } if (value instanceof
         * String) { return Integer.valueOf((String) value); } if (value
         * instanceof Long) { return ((Long) value).intValue(); }
         */

        ShipCargoDTO cargo = new ShipCargoDTO();
        cargo.setCommodity(data.getAttributeAsString(ShipWindowViewImpl.CARGO_COMMODITY));
        cargo.setAmount(Integer.valueOf(data.getAttributeAsString(ShipWindowViewImpl.CARGO_AMOUNT)));
        cargo.setPurchaseDate(data.getAttributeAsDate(ShipWindowViewImpl.CARGO_PURCHASE_DATE));
        cargo.setPurchasePrice(data.getAttributeAsInt(ShipWindowViewImpl.CARGO_PURCHASE_PRICE));
        return cargo;
    }

    /**
     * 
     * @param ship
     *            the ship to update
     * @param data
     *            the data from the grid
     * @return the updated Ship
     */
    public static ShipDTO fromAttributeGrid(final ShipDTO ship, final ListGridRecord[] data) {
        for (ListGridRecord record : data) {
            String name = record.getAttributeAsString(ShipWindowViewImpl.CARGO_COMMODITY);
            // Integer previous = ship.getShipAttributes().getAttribute(name);
            // TODO commented out - is this still needed
            // ship.getShipAttributes().setAttribute(name,
            // extractAttributeData(record));
        }
        return ship;
    }

    /**
     * 
     * @param ship
     *            the ship to update
     * @param data
     *            the data from the grid
     * @return the updated Ship
     */
    public static ShipDTO fromCargoGrid(final ShipDTO ship, final ListGridRecord[] data) {
        for (ListGridRecord record : data) {
            String name = record.getAttributeAsString(ShipWindowViewImpl.CARGO_COMMODITY);
            // Integer previous = ship.getShipCargos().getCargo(name);
            // ship.getCargo().setCargo(name, extractCargoData(record));
            ShipCargoDTO cargo = extractCargoData(record);
            cargo.setCommodity(name);
            // ship.getCargo().put(name, extractCargoData(record));
            ship.getCargo().add(cargo);
        }
        return ship;
    }

    /**
     * 
     * @param ship
     *            the ship who's attributes will be transformed into List Grid
     *            Records
     * @return an array of hydrated records
     */
    public static ListGridRecord[] toAttributeGrid(final ShipDTO ship) {
        Map<String, Integer> attributes = ship.getShipAttributes().toMap();
        return transformAttributes(attributes);
    }

    /**
     * 
     * @param bucket
     *            the ship's attribute bucket that will be transformed into List
     *            Grid Records
     * @return an array of hydrated records
     */
    public static ListGridRecord[] toAttributeGrid(final ShipAttributeBucket bucket) {
        return transformAttributes(bucket.toMap());
    }

    /**
     * 
     * @param bucket
     *            the ship's attribute bucket that will be transformed into List
     *            Grid Records
     * @return an array of hydrated records
     */
    public static ListGridRecord[] toCargoGrid(final ArrayList<ShipCargoDTO> bucket) {
        return transformCargos(bucket);
    }

    /**
     * 
     * @param ship
     *            the ship who's attributes will be transformed into List Grid
     *            Records
     * @return an array of hydrated records
     */
    public static ListGridRecord[] toCargoGrid(final ShipDTO ship) {
        ArrayList<ShipCargoDTO> cargo = ship.getCargo();
        return transformCargos(cargo);
    }

    /**
     * 
     * @param attributes
     *            the attributes to transform into List Grid Records
     * @return an array of hydrated records
     */
    private static ListGridRecord[] transformAttributes(final Map<String, Integer> attributes) {
        ListGridRecord[] data = new ListGridRecord[attributes.size()];
        int index = 0;
        for (String key : attributes.keySet()) {
            Object value = attributes.get(key);

            if (value instanceof Integer) {
                data[index++] = createAttributeRow(key, (Integer) value);
            } else {
                data[index++] = createAttributeRow(key, ((Long) value).intValue());
            }

        }
        /*
         * for (Map.Entry<String, Integer> attributeEntry :
         * attributes.entrySet()) { // i have no idea about this bug // the cast
         * fails because it is actually a long type if
         * (attributeEntry.getValue() instanceof Integer) { data[index++] =
         * createRow(attributeEntry.getKey(), attributeEntry.getValue()); } else
         * { data[index++] = createRow(attributeEntry.getKey(),
         * Integer.valueOf(attributeEntry.getValue())); } }
         */

        return data;
    }

    /**
     * 
     * @param cargos
     *            the attributes to transform into List Grid Records
     * @return an array of hydrated records
     */
    private static ListGridRecord[] transformCargos(final ArrayList<ShipCargoDTO> cargos) {
        ListGridRecord[] data = new ListGridRecord[cargos.size() + Commodities.values().length];
        int index = 0;
        /*
         * for (Commodities entry : Commodities.values()) { ListGridRecord
         * record = new ListGridRecord();
         * record.setAttribute(ShipWindowView.CARGO_COMMODITY, entry.getKey());
         * record.setAttribute("category", entry.getCategory()); data[index++] =
         * record; }
         */
        for (ShipCargoDTO cargo : cargos) {
            data[index++] = createCargoRow(cargo);
        }
        /*
         * for (String key : cargos.keySet()) { Object value = cargos.get(key);
         * 
         * if (value instanceof ShipCargoDTO) { data[index++] =
         * createCargoRow(key, ((ShipCargoDTO) value).getAmount()); } else if
         * (value instanceof Integer) { data[index++] = createCargoRow(key,
         * (Integer) value); } else { data[index++] = createCargoRow(key,
         * ((Long) value).intValue()); }
         * 
         * }
         */

        return data;
    }

    /**
     * 
     */
    private ShipServiceAsync dataService = null;

    /**
     * 
     */
    private EventBus eventBus = null;

    /**
     * constructor and pull initial data set
     * 
     * @param id
     *            id of the data source
     * @param dataService
     *            the service to use to interact with the data store - this is a
     *            GWT RPC data source
     * @param eventBus
     *            the bus to publish events on
     */
    public ShipDS(final String id, final ShipServiceAsync dataService, final EventBus eventBus) {
        super();
        setID(id);

        this.dataService = dataService;
        this.eventBus = eventBus;
        this.setCanMultiSort(true);

        DataSourceIntegerField pkField = new DataSourceIntegerField(ShipRecord.ID);
        pkField.setPrimaryKey(true);
        pkField.setCanEdit(false);

        DataSourceIntegerField fkOwnerIdField = new DataSourceIntegerField(ShipRecord.OWNER_ID);
        DataSourceTextField nameField = new DataSourceTextField(ShipRecord.NAME);
        DataSourceIntegerField shipTypeIdField = new DataSourceIntegerField(ShipRecord.SHIP_TYPE_ID);

        setFields(pkField, fkOwnerIdField, nameField, shipTypeIdField);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void executeAdd(final String requestId, final DSRequest request, final DSResponse response)
        throws DataException {

        GWT.log(DS_TYPE + "DS.transformRequest.ADD");
        JavaScriptObject data = request.getData();
        ListGridRecord rec = new ListGridRecord(data);
        ShipRecord shipRecord = new ShipRecord();
        copyValues(rec, shipRecord);

        dataService.execute(new AddShip(shipRecord.toShip()), new AddedShip() {
            @Override
            public void got(final ShipDTO ship) {
                ShipAddedEvent.fire(eventBus, ship);

            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onFailure(final Throwable caught) {
                Window.alert("Add " + DS_TYPE + " failed");
                response.setStatus(-1);
                processResponse(requestId, response);
                super.onFailure(caught);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onSuccess(final AddShipResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getShip() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    ShipRecord[] records = new ShipRecord[1];
                    records[0] = new ShipRecord(result.getShip());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    ShipRecord[] records = new ShipRecord[0];
                    response.setData(records);
                }
                processResponse(requestId, response);
                super.onSuccess(result);
            }
        });
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void executeFetch(final String requestId, final DSRequest request, final DSResponse response)
        throws DataException {
        ArrayList<Filter> criterionArray = getCriterionArray(request.getCriteria());
        String fullSortString = request.getAttributeAsString("sortBy");
        final int startRow = getStartRow(request);
        final int endRow = getEndRow(request);
        GWT.log(DS_TYPE + "DS.transformRequest.FETCH Starting:" + startRow + ", ending:" + endRow + " sort: ["
            + fullSortString + "], criteria: [" + criterionArray + "]");

        dataService.execute(new GetAllShips(startRow, endRow, criterionArray, fullSortString), new GotAllShips() {

            public void got(final ArrayList<ShipDTO> ships) {
                ShipsLoadedEvent.fire(eventBus, ships);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onFailure(final Throwable caught) {
                Window.alert("Get " + DS_TYPE + " failed");
                response.setStatus(-1);
                processResponse(requestId, response);
                super.onFailure(caught);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onSuccess(final GetAllShipsResponse result) {
                GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess");
                // DSResponse response = new DSResponse();
                ArrayList<ShipDTO> ships = result.getShips();
                if (result.getShips().size() > 0) {
                    response.setTotalRows(result.getTotalShips());
                    response.setStartRow(startRow);
                    if (endRow > result.getTotalShips()) {
                        response.setEndRow(result.getTotalShips());
                    } else {
                        response.setEndRow(endRow);
                    }
                    ShipRecord[] records = new ShipRecord[ships.size() + 1];
                    for (int i = 0; i < ships.size(); i++) {
                        records[i] = new ShipRecord(ships.get(i));
                        records[i].setAttribute(ShipRecord.ATTRIBUTES, ships.get(i).getShipAttributes());
                    }
                    GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess(start, end, total, size) = "
                        + response.getStartRow() + ", " + response.getEndRow() + ", " + result.getTotalShips() + ", "
                        + ships.size());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    ShipRecord[] records = new ShipRecord[0];
                    response.setData(records);
                }
                processResponse(requestId, response);
                super.onSuccess(result);
            }
        });

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void executeRemove(final String requestId, final DSRequest request, final DSResponse response)
        throws DataException {
        GWT.log(DS_TYPE + "DS.transformRequest.REMOVE");
        // Retrieve record which should be removed.
        JavaScriptObject data = request.getData();
        final ListGridRecord rec = new ListGridRecord(data);
        ShipRecord testRec = new ShipRecord();
        copyValues(rec, testRec);

        dataService.execute(new RemoveShip(testRec.toShip()), new RemovedShip() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void onFailure(final Throwable caught) {
                Window.alert("Remove " + DS_TYPE + " failed");
                response.setStatus(-1);
                processResponse(requestId, response);
                super.onFailure(caught);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onSuccess(final RemoveShipResponse result) {
                ListGridRecord[] list = new ListGridRecord[1];
                // We do not receive removed record from server.
                // Return record from request.
                list[0] = rec;
                response.setData(list);
                processResponse(requestId, response);
                super.onSuccess(result);
            }

            @Override
            public void removed(final ShipDTO ship) {
                ShipRemovedEvent.fire(eventBus, ship);
            }
        });
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void executeUpdate(final String requestId, final DSRequest request, final DSResponse response)
        throws DataException {
        GWT.log(DS_TYPE + "DS.transformRequest.UPDATE");
        // Retrieve record which should be updated.
        JavaScriptObject data = request.getData();
        ListGridRecord rec = new ListGridRecord(data);
        // Find grid
        Canvas obj = Canvas.getById(request.getComponentId());
        if (obj instanceof DynamicForm) {
            // DynamicForm form = (DynamicForm) obj;
            // rec = (ListGridRecord) form.
        } else {
            ListGrid grid = (ListGrid) obj;
            // Get record with old and new values combined
            int index = grid.getRecordIndex(rec);
            rec = (ListGridRecord) grid.getEditedRecord(index);
        }
        ShipRecord shipRecord = new ShipRecord();
        copyValues(rec, shipRecord);

        dataService.execute(new UpdateShip(shipRecord.toShip()), new UpdatedShip() {
            @Override
            public void got(final ShipDTO ship) {
                ShipUpdatedEvent.fire(eventBus, ship);

            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onFailure(final Throwable caught) {
                Window.alert("Update " + DS_TYPE + " failed");
                response.setStatus(-1);
                processResponse(requestId, response);
                super.onFailure(caught);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onSuccess(final UpdateShipResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getShip() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    ShipRecord[] records = new ShipRecord[1];
                    records[0] = new ShipRecord(result.getShip());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    ShipRecord[] records = new ShipRecord[0];
                    response.setData(records);
                }
                processResponse(requestId, response);
                super.onSuccess(result);
            }
        });

    }

    /**
     * @return the dataService
     */
    public ShipServiceAsync getDataService() {
        return dataService;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected EventBus getEventBus() {
        return this.eventBus;
    }

    /**
     * @param dataService
     *            the dataService to set
     */
    public void setDataService(final ShipServiceAsync dataService) {
        this.dataService = dataService;
    }

}
