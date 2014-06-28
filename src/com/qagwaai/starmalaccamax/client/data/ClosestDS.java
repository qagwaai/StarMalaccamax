/**
 * ClosestDS.java
 * Created by pgirard at 2:49:07 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.data package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Window;
import com.qagwaai.starmalaccamax.client.core.constants.CoreConstants;
import com.qagwaai.starmalaccamax.client.event.ClosestAddedEvent;
import com.qagwaai.starmalaccamax.client.event.ClosestRemovedEvent;
import com.qagwaai.starmalaccamax.client.event.ClosestUpdatedEvent;
import com.qagwaai.starmalaccamax.client.event.ClosestsLoadedEvent;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.event.ServiceExceptionEvent;
import com.qagwaai.starmalaccamax.client.service.SolarSystemServiceAsync;
import com.qagwaai.starmalaccamax.client.service.action.AddClosest;
import com.qagwaai.starmalaccamax.client.service.action.AddClosestResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllClosests;
import com.qagwaai.starmalaccamax.client.service.action.GetAllClosestsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveClosest;
import com.qagwaai.starmalaccamax.client.service.action.RemoveClosestResponse;
import com.qagwaai.starmalaccamax.client.service.action.UpdateClosest;
import com.qagwaai.starmalaccamax.client.service.action.UpdateClosestResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.AddedClosest;
import com.qagwaai.starmalaccamax.client.service.helpers.GotAllClosests;
import com.qagwaai.starmalaccamax.client.service.helpers.RemovedClosest;
import com.qagwaai.starmalaccamax.client.service.helpers.UpdatedClosest;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class ClosestDS extends GwtRpcDataSource {
    /**
	 * 
	 */
    private CoreConstants constants = GWT.create(CoreConstants.class);
    /**
	 * 
	 */
    public static final String DS_TYPE = "Closest";

    /**
     * 
     * @param from
     *            to copy from
     * @param to
     *            to copy to
     * @throws DataException
     *             if the operation failed
     */
    private static void copyValues(final ListGridRecord from, final ClosestRecord to) throws DataException {
        to.setId(LongConverter.fromJavaScript(from, ClosestRecord.ID));
        to.setSolarSystemId(LongConverter.fromJavaScript(from, ClosestRecord.SOLARSYSTEM_ID));
        to.setNumberOfJumps(NullConverter.toInt(from.getAttributeAsInt(ClosestRecord.NUMBER_OF_JUMPS)));
        to.setToSolarSystemId(LongConverter.fromJavaScript(from, ClosestRecord.TO_ID));
    }

    /**
     * 
     */
    private SolarSystemServiceAsync dataService = null;

    /**
     * 
     */
    private EventBus eventBus;

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
    public ClosestDS(final String id, final SolarSystemServiceAsync dataService, final EventBus eventBus) {
        super();
        setID(id);

        this.dataService = dataService;
        this.eventBus = eventBus;
        this.setCanMultiSort(true);

        DataSourceIntegerField pkField = new DataSourceIntegerField(ClosestRecord.ID);
        pkField.setPrimaryKey(true);
        pkField.setCanEdit(false);

        DataSourceIntegerField fkSolarSystemIdField = new DataSourceIntegerField(ClosestRecord.SOLARSYSTEM_ID);
        DataSourceIntegerField numJumpsField =
            new DataSourceIntegerField(ClosestRecord.NUMBER_OF_JUMPS, constants.closestNumberOfJumps());
        DataSourceIntegerField fktoSolarSystemIdField = new DataSourceIntegerField(ClosestRecord.TO_ID);
        setFields(pkField, fkSolarSystemIdField, numJumpsField, fktoSolarSystemIdField);
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
        ClosestRecord closestRecord = new ClosestRecord();
        copyValues(rec, closestRecord);

        dataService.execute(new AddClosest(closestRecord.toClosest()), new AddedClosest() {
            @Override
            public void got(final ClosestDTO closest) {
                ClosestAddedEvent.fire(eventBus, closest);
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
            public void onSuccess(final AddClosestResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getClosest() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    ClosestRecord[] records = new ClosestRecord[1];
                    records[0] = new ClosestRecord(result.getClosest());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    ClosestRecord[] records = new ClosestRecord[0];
                    response.setData(records);
                }
                processResponse(requestId, response);
                super.onSuccess(result);
            }

			@Override
			public String getAsyncName() {
				return "com.qagwaai.starmalaccamax.client.data.ClosestDS.executeAdd.AddClosest";
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
        GWT.log(DS_TYPE + "DS.transformRequest.FETCH Starting:" + request.getStartRow() + ", ending:"
            + request.getEndRow() + " sort: [" + fullSortString + "], criteria: [" + criterionArray + "]");

        dataService.execute(new GetAllClosests(startRow, endRow, criterionArray, fullSortString), new GotAllClosests() {

            public void got(final ArrayList<ClosestDTO> closests) {
                ClosestsLoadedEvent.fire(eventBus, closests);
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
            public void onSuccess(final GetAllClosestsResponse result) {
                GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess");
                ArrayList<ClosestDTO> closests = result.getClosests();
                if (result.getClosests().size() > 0) {
                    response.setTotalRows(result.getTotalClosests());
                    response.setStartRow(startRow);
                    if (endRow > result.getTotalClosests()) {
                        response.setEndRow(result.getTotalClosests());
                    } else {
                        response.setEndRow(endRow);
                    }
                    ClosestRecord[] records = new ClosestRecord[closests.size() + 1];
                    for (int i = 0; i < closests.size(); i++) {
                        records[i] = new ClosestRecord(closests.get(i));
                    }
                    GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess(start, end, total, size) = "
                        + response.getStartRow() + ", " + response.getEndRow() + ", " + result.getTotalClosests()
                        + ", " + closests.size());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    ClosestRecord[] records = new ClosestRecord[0];
                    response.setData(records);
                }
                processResponse(requestId, response);
                super.onSuccess(result);
            }

			@Override
			public String getAsyncName() {
				return "com.qagwaai.starmalaccamax.client.data.ClosestDS.executeFetch.GetAllClosests";
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
        ClosestRecord testRec = new ClosestRecord();
        copyValues(rec, testRec);

        dataService.execute(new RemoveClosest(testRec.toClosest()), new RemovedClosest() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void onFailure(final Throwable caught) {
                ServiceExceptionEvent.fire(eventBus, caught);
                Window.alert("Remove " + DS_TYPE + " failed");
                response.setStatus(-1);
                processResponse(requestId, response);
                super.onFailure(caught);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onSuccess(final RemoveClosestResponse result) {
                ListGridRecord[] list = new ListGridRecord[1];
                // We do not receive removed record from server.
                // Return record from request.
                list[0] = rec;
                response.setData(list);
                processResponse(requestId, response);
                super.onSuccess(result);
            }

            @Override
            public void removed(final ClosestDTO closest) {
                ClosestRemovedEvent.fire(eventBus, closest);
            }

			@Override
			public String getAsyncName() {
				return "com.qagwaai.starmalaccamax.client.data.ClosestDS.executeRemove.RemoveClosest";
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
        ClosestRecord closestRecord = new ClosestRecord();
        copyValues(rec, closestRecord);

        dataService.execute(new UpdateClosest(closestRecord.toClosest()), new UpdatedClosest() {
            @Override
            public void got(final ClosestDTO closest) {
                ClosestUpdatedEvent.fire(eventBus, closest);

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
            public void onSuccess(final UpdateClosestResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getClosest() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    ClosestRecord[] records = new ClosestRecord[1];
                    records[0] = new ClosestRecord(result.getClosest());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    ClosestRecord[] records = new ClosestRecord[0];
                    response.setData(records);
                }
                processResponse(requestId, response);
                super.onSuccess(result);
            }

			@Override
			public String getAsyncName() {
				return "com.qagwaai.starmalaccamax.client.data.ClosestDS.executeUpdate.UpdateClosest";
			}
        });

    }

    /**
     * @return the dataService
     */
    public SolarSystemServiceAsync getDataService() {
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
    public void setDataService(final SolarSystemServiceAsync dataService) {
        this.dataService = dataService;
    }

}
