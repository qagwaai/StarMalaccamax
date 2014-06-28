/**
 * UserDS.java
 * Created by pgirard at 2:49:07 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.data package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Window;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.event.ShipTypeAddedEvent;
import com.qagwaai.starmalaccamax.client.event.ShipTypeRemovedEvent;
import com.qagwaai.starmalaccamax.client.event.ShipTypeUpdatedEvent;
import com.qagwaai.starmalaccamax.client.event.ShipTypesLoadedEvent;
import com.qagwaai.starmalaccamax.client.service.ShipServiceAsync;
import com.qagwaai.starmalaccamax.client.service.action.AddShipType;
import com.qagwaai.starmalaccamax.client.service.action.AddShipTypeResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllShipTypes;
import com.qagwaai.starmalaccamax.client.service.action.GetAllShipTypesResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveShipType;
import com.qagwaai.starmalaccamax.client.service.action.RemoveShipTypeResponse;
import com.qagwaai.starmalaccamax.client.service.action.UpdateShipType;
import com.qagwaai.starmalaccamax.client.service.action.UpdateShipTypeResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.AddedShipType;
import com.qagwaai.starmalaccamax.client.service.helpers.GotAllShipTypes;
import com.qagwaai.starmalaccamax.client.service.helpers.RemovedShipType;
import com.qagwaai.starmalaccamax.client.service.helpers.UpdatedShipType;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.ShipTypeDTO;
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
public final class ShipTypeDS extends GwtRpcDataSource {

    /**
	 * 
	 */
    public static final String DS_TYPE = "ShipType";

    /**
     * 
     * @param from
     *            to copy from
     * @param to
     *            to copy to
     * @throws DataException
     *             if the operation failed
     */
    private static void copyValues(final ListGridRecord from, final ShipTypeRecord to) throws DataException {
        to.setId(LongConverter.fromJavaScript(from, ShipTypeRecord.ID));
        to.setName(from.getAttributeAsString(ShipTypeRecord.NAME));
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
    public ShipTypeDS(final String id, final ShipServiceAsync dataService, final EventBus eventBus) {
        super();
        setID(id);

        this.dataService = dataService;
        this.eventBus = eventBus;
        this.setCanMultiSort(true);

        DataSourceIntegerField pkField = new DataSourceIntegerField(ShipTypeRecord.ID);
        pkField.setPrimaryKey(true);
        pkField.setCanEdit(false);

        DataSourceTextField nameField = new DataSourceTextField(ShipTypeRecord.NAME);

        setFields(pkField, nameField);
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
        ShipTypeRecord shipTypeRecord = new ShipTypeRecord();
        copyValues(rec, shipTypeRecord);

        dataService.execute(new AddShipType(shipTypeRecord.toShipType()), new AddedShipType() {
            @Override
            public void got(final ShipTypeDTO shipType) {
                ShipTypeAddedEvent.fire(eventBus, shipType);

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
            public void onSuccess(final AddShipTypeResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getShipType() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    ShipTypeRecord[] records = new ShipTypeRecord[1];
                    records[0] = new ShipTypeRecord(result.getShipType());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    ShipTypeRecord[] records = new ShipTypeRecord[0];
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

        dataService.execute(new GetAllShipTypes(startRow, endRow, criterionArray, fullSortString),
            new GotAllShipTypes() {

                public void got(final ArrayList<ShipTypeDTO> shipTypes) {
                    ShipTypesLoadedEvent.fire(eventBus, shipTypes);
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
                public void onSuccess(final GetAllShipTypesResponse result) {
                    GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess");
                    // DSResponse response = new DSResponse();
                    ArrayList<ShipTypeDTO> shipTypes = result.getShipTypes();
                    if (result.getShipTypes().size() > 0) {
                        response.setTotalRows(result.getTotalShipTypes());
                        response.setStartRow(startRow);
                        if (endRow > result.getTotalShipTypes()) {
                            response.setEndRow(result.getTotalShipTypes());
                        } else {
                            response.setEndRow(endRow);
                        }
                        ShipTypeRecord[] records = new ShipTypeRecord[shipTypes.size() + 1];
                        for (int i = 0; i < shipTypes.size(); i++) {
                            records[i] = new ShipTypeRecord(shipTypes.get(i));
                        }
                        GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess(start, end, total, size) = "
                            + response.getStartRow() + ", " + response.getEndRow() + ", " + result.getTotalShipTypes()
                            + ", " + shipTypes.size());
                        response.setData(records);
                    } else {
                        response.setTotalRows(0);
                        response.setStartRow(0);
                        response.setEndRow(0);
                        ShipTypeRecord[] records = new ShipTypeRecord[0];
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
        ShipTypeRecord testRec = new ShipTypeRecord();
        copyValues(rec, testRec);

        dataService.execute(new RemoveShipType(testRec.toShipType()), new RemovedShipType() {
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
            public void onSuccess(final RemoveShipTypeResponse result) {
                ListGridRecord[] list = new ListGridRecord[1];
                // We do not receive removed record from server.
                // Return record from request.
                list[0] = rec;
                response.setData(list);
                processResponse(requestId, response);
                super.onSuccess(result);
            }

            @Override
            public void removed(final ShipTypeDTO shipType) {
                ShipTypeRemovedEvent.fire(eventBus, shipType);
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
        ShipTypeRecord shipTypeRecord = new ShipTypeRecord();
        copyValues(rec, shipTypeRecord);

        dataService.execute(new UpdateShipType(shipTypeRecord.toShipType()), new UpdatedShipType() {
            @Override
            public void got(final ShipTypeDTO shipType) {
                ShipTypeUpdatedEvent.fire(eventBus, shipType);

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
            public void onSuccess(final UpdateShipTypeResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getShipType() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    ShipTypeRecord[] records = new ShipTypeRecord[1];
                    records[0] = new ShipTypeRecord(result.getShipType());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    ShipTypeRecord[] records = new ShipTypeRecord[0];
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
