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
import com.qagwaai.starmalaccamax.client.event.JumpGateAddedEvent;
import com.qagwaai.starmalaccamax.client.event.JumpGateRemovedEvent;
import com.qagwaai.starmalaccamax.client.event.JumpGateUpdatedEvent;
import com.qagwaai.starmalaccamax.client.service.JumpGateServiceAsync;
import com.qagwaai.starmalaccamax.client.service.action.AddJumpGate;
import com.qagwaai.starmalaccamax.client.service.action.AddJumpGateResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllJumpGates;
import com.qagwaai.starmalaccamax.client.service.action.GetAllJumpGatesResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveJumpGate;
import com.qagwaai.starmalaccamax.client.service.action.RemoveJumpGateResponse;
import com.qagwaai.starmalaccamax.client.service.action.UpdateJumpGate;
import com.qagwaai.starmalaccamax.client.service.action.UpdateJumpGateResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.AddedJumpGate;
import com.qagwaai.starmalaccamax.client.service.helpers.GotAllJumpGates;
import com.qagwaai.starmalaccamax.client.service.helpers.RemovedJumpGate;
import com.qagwaai.starmalaccamax.client.service.helpers.UpdatedJumpGate;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.JumpGateDTO;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
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
public final class JumpGateDS extends GwtRpcDataSource {

    /**
	 * 
	 */
    public static final String DS_TYPE = "JumpGate";

    /**
     * 
     * @param from
     *            to copy from
     * @param to
     *            to copy to
     * @throws DataException
     *             if the operation failed
     */
    private static void copyValues(final ListGridRecord from, final JumpGateRecord to) throws DataException {
        to.setId(LongConverter.fromJavaScript(from, ShipTypeRecord.ID));
        to.setSolarSystem1Id(LongConverter.fromJavaScript(from, JumpGateRecord.SOLARSYSTEM_1_ID));
        to.setSolarSystem2Id(LongConverter.fromJavaScript(from, JumpGateRecord.SOLARSYSTEM_2_ID));
        to.setLocation1((LocationDTO) from.getAttributeAsObject(JumpGateRecord.LOCATION_1));
        to.setLocation2((LocationDTO) from.getAttributeAsObject(JumpGateRecord.LOCATION_2));
        to.setFailurePct(from.getAttributeAsInt(JumpGateRecord.FAILURE_PCT));
    }

    /**
     * 
     */
    private JumpGateServiceAsync dataService = null;

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
    public JumpGateDS(final String id, final JumpGateServiceAsync dataService, final EventBus eventBus) {
        super();
        setID(id);

        this.dataService = dataService;
        this.eventBus = eventBus;
        this.setCanMultiSort(true);

        DataSourceIntegerField pkField = new DataSourceIntegerField(JumpGateRecord.ID);
        pkField.setPrimaryKey(true);
        pkField.setCanEdit(false);

        DataSourceIntegerField fkSolarSystemId1Field = new DataSourceIntegerField(JumpGateRecord.SOLARSYSTEM_1_ID);
        DataSourceIntegerField fkSolarSystemId2Field = new DataSourceIntegerField(JumpGateRecord.SOLARSYSTEM_2_ID);
        DataSourceTextField location1Field = new DataSourceTextField(JumpGateRecord.LOCATION_1);
        location1Field.setCanEdit(false);
        DataSourceTextField location2Field = new DataSourceTextField(JumpGateRecord.LOCATION_2);
        location2Field.setCanEdit(false);

        DataSourceIntegerField failurePctField = new DataSourceIntegerField(JumpGateRecord.FAILURE_PCT);

        setFields(pkField, fkSolarSystemId1Field, fkSolarSystemId2Field, location1Field, location2Field,
            failurePctField);
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
        JumpGateRecord jumpGateRecord = new JumpGateRecord();
        copyValues(rec, jumpGateRecord);

        dataService.execute(new AddJumpGate(jumpGateRecord.toJumpGate()), new AddedJumpGate() {
            @Override
            public void got(final JumpGateDTO jumpGate) {
                JumpGateAddedEvent.fire(eventBus, jumpGate);

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
            public void onSuccess(final AddJumpGateResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getJumpGate() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    JumpGateRecord[] records = new JumpGateRecord[1];
                    records[0] = new JumpGateRecord(result.getJumpGate());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    JumpGateRecord[] records = new JumpGateRecord[0];
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

        dataService.execute(new GetAllJumpGates(startRow, endRow, criterionArray, fullSortString),
            new GotAllJumpGates() {

                public void got(final ArrayList<JumpGateDTO> jumpGates) {
                    // JumpGatesLoadedEvent.fire(eventBus, jumpGates);
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
                public void onSuccess(final GetAllJumpGatesResponse result) {
                    GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess");
                    // DSResponse response = new DSResponse();
                    ArrayList<JumpGateDTO> jumpGates = result.getJumpGates();
                    if (result.getJumpGates().size() > 0) {
                        response.setTotalRows(result.getTotalJumpGates());
                        response.setStartRow(startRow);
                        if (endRow > result.getTotalJumpGates()) {
                            response.setEndRow(result.getTotalJumpGates());
                        } else {
                            response.setEndRow(endRow);
                        }
                        JumpGateRecord[] records = new JumpGateRecord[jumpGates.size() + 1];
                        for (int i = 0; i < jumpGates.size(); i++) {
                            records[i] = new JumpGateRecord(jumpGates.get(i));
                        }
                        GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess(start, end, total, size) = "
                            + response.getStartRow() + ", " + response.getEndRow() + ", " + result.getTotalJumpGates()
                            + ", " + jumpGates.size());
                        response.setData(records);
                    } else {
                        response.setTotalRows(0);
                        response.setStartRow(0);
                        response.setEndRow(0);
                        JumpGateRecord[] records = new JumpGateRecord[0];
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
        JumpGateRecord testRec = new JumpGateRecord();
        copyValues(rec, testRec);

        dataService.execute(new RemoveJumpGate(testRec.toJumpGate()), new RemovedJumpGate() {
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
            public void onSuccess(final RemoveJumpGateResponse result) {
                ListGridRecord[] list = new ListGridRecord[1];
                // We do not receive removed record from server.
                // Return record from request.
                list[0] = rec;
                response.setData(list);
                processResponse(requestId, response);
                super.onSuccess(result);
            }

            @Override
            public void removed(final JumpGateDTO jumpGate) {
                JumpGateRemovedEvent.fire(eventBus, jumpGate);
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
        JumpGateRecord jumpGateRecord = new JumpGateRecord();
        copyValues(rec, jumpGateRecord);

        dataService.execute(new UpdateJumpGate(jumpGateRecord.toJumpGate()), new UpdatedJumpGate() {
            @Override
            public void got(final JumpGateDTO jumpGate) {
                JumpGateUpdatedEvent.fire(eventBus, jumpGate);

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
            public void onSuccess(final UpdateJumpGateResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getJumpGate() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    JumpGateRecord[] records = new JumpGateRecord[1];
                    records[0] = new JumpGateRecord(result.getJumpGate());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    JumpGateRecord[] records = new JumpGateRecord[0];
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
    public JumpGateServiceAsync getDataService() {
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
    public void setDataService(final JumpGateServiceAsync dataService) {
        this.dataService = dataService;
    }

}
