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
import com.qagwaai.starmalaccamax.client.core.constants.CoreConstants;
import com.qagwaai.starmalaccamax.client.event.CaptainAddedEvent;
import com.qagwaai.starmalaccamax.client.event.CaptainRemovedEvent;
import com.qagwaai.starmalaccamax.client.event.CaptainUpdatedEvent;
import com.qagwaai.starmalaccamax.client.event.CaptainsLoadedEvent;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.service.CaptainServiceAsync;
import com.qagwaai.starmalaccamax.client.service.action.AddCaptain;
import com.qagwaai.starmalaccamax.client.service.action.AddCaptainResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllCaptains;
import com.qagwaai.starmalaccamax.client.service.action.GetAllCaptainsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveCaptain;
import com.qagwaai.starmalaccamax.client.service.action.RemoveCaptainResponse;
import com.qagwaai.starmalaccamax.client.service.action.UpdateCaptain;
import com.qagwaai.starmalaccamax.client.service.action.UpdateCaptainResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.AddedCaptain;
import com.qagwaai.starmalaccamax.client.service.helpers.GotAllCaptains;
import com.qagwaai.starmalaccamax.client.service.helpers.RemovedCaptain;
import com.qagwaai.starmalaccamax.client.service.helpers.UpdatedCaptain;
import com.qagwaai.starmalaccamax.shared.model.CaptainAttributes;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.CaptainSkills;
import com.qagwaai.starmalaccamax.shared.model.Filter;
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
public final class CaptainDS extends GwtRpcDataSource {

    /**
	 * 
	 */
    private CoreConstants constants = GWT.create(CoreConstants.class);
    /**
	 * 
	 */
    public static final String DS_TYPE = "Captain";

    /**
     * 
     * @param from
     *            to copy from
     * @param to
     *            to copy to
     * @throws DataException
     *             if the operation failed
     */
    private static void copyValues(final ListGridRecord from, final CaptainRecord to) throws DataException {
        to.setId(LongConverter.fromJavaScript(from, CaptainRecord.ID));
        to.setName(from.getAttributeAsString(CaptainRecord.NAME));
        to.setColor(from.getAttributeAsString(CaptainRecord.COLOR));
        to.setGender(from.getAttributeAsString(CaptainRecord.GENDER));
        to.setRace(from.getAttributeAsString(CaptainRecord.RACE));
        to.setCaptainAttributes((CaptainAttributes) from.getAttributeAsObject(CaptainRecord.ATTRIBUTES));
        to.setSkills((CaptainSkills) from.getAttributeAsObject(CaptainRecord.SKILLS));
        to.setOwnerId(LongConverter.fromJavaScript(from, CaptainRecord.OWNER_ID));
    }

    /**
     * 
     */
    private CaptainServiceAsync dataService = null;

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
    public CaptainDS(final String id, final CaptainServiceAsync dataService, final EventBus eventBus) {
        super();
        setID(id);

        this.dataService = dataService;
        this.eventBus = eventBus;
        this.setCanMultiSort(true);

        DataSourceIntegerField pkField = new DataSourceIntegerField(CaptainRecord.ID);
        pkField.setPrimaryKey(true);
        pkField.setCanEdit(false);

        DataSourceTextField nameField = new DataSourceTextField(CaptainRecord.NAME, constants.captainName());
        DataSourceTextField colorField = new DataSourceTextField(CaptainRecord.COLOR, constants.captainColor());
        DataSourceTextField genderField = new DataSourceTextField(CaptainRecord.GENDER, "Gender");
        DataSourceTextField raceField = new DataSourceTextField(CaptainRecord.RACE, "Race");
        DataSourceIntegerField ownerIdField = new DataSourceIntegerField(CaptainRecord.OWNER_ID);

        setFields(pkField, nameField, colorField, genderField, raceField, ownerIdField);
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
        CaptainRecord captainRecord = new CaptainRecord();
        copyValues(rec, captainRecord);

        dataService.execute(new AddCaptain(captainRecord.toCaptain()), new AddedCaptain() {
            @Override
            public void got(final CaptainDTO captain) {
                CaptainAddedEvent.fire(eventBus, captain);

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
            public void onSuccess(final AddCaptainResponse result) {
                super.onSuccess(result);

                if (result.getCaptain() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    CaptainRecord[] records = new CaptainRecord[1];
                    records[0] = new CaptainRecord(result.getCaptain());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    CaptainRecord[] records = new CaptainRecord[0];
                    response.setData(records);
                }
                processResponse(requestId, response);
            }

			@Override
			public String getAsyncName() {
				return "com.qagwaai.starmalaccamax.client.data.CaptainDS.executeAdd.AddCaptain";
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

        dataService.execute(new GetAllCaptains(startRow, endRow, criterionArray, fullSortString), new GotAllCaptains() {

            public void got(final ArrayList<CaptainDTO> captains) {
                CaptainsLoadedEvent.fire(eventBus, captains);
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
            public void onSuccess(final GetAllCaptainsResponse result) {
                GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess");
                super.onSuccess(result);
                // DSResponse response = new DSResponse();
                ArrayList<CaptainDTO> captains = result.getCaptains();
                if (result.getCaptains().size() > 0) {
                    response.setTotalRows(result.getTotalCaptains());
                    response.setStartRow(startRow);
                    if (endRow > result.getTotalCaptains()) {
                        response.setEndRow(result.getTotalCaptains());
                    } else {
                        response.setEndRow(endRow);
                    }
                    CaptainRecord[] records = new CaptainRecord[captains.size() + 1];
                    for (int i = 0; i < captains.size(); i++) {
                        records[i] = new CaptainRecord(captains.get(i));
                    }
                    GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess(start, end, total, size) = "
                        + response.getStartRow() + ", " + response.getEndRow() + ", " + result.getTotalCaptains()
                        + ", " + captains.size());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    CaptainRecord[] records = new CaptainRecord[0];
                    response.setData(records);
                }
                processResponse(requestId, response);
            }

			@Override
			public String getAsyncName() {
				return "com.qagwaai.starmalaccamax.client.data.CaptainDS.executeFetch.GetAllCaptains";
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
        CaptainRecord testRec = new CaptainRecord();
        copyValues(rec, testRec);

        dataService.execute(new RemoveCaptain(testRec.toCaptain()), new RemovedCaptain() {
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
            public void onSuccess(final RemoveCaptainResponse result) {
                super.onSuccess(result);
                ListGridRecord[] list = new ListGridRecord[1];
                // We do not receive removed record from server.
                // Return record from request.
                list[0] = rec;
                response.setData(list);
                processResponse(requestId, response);
            }

            @Override
            public void removed(final CaptainDTO captain) {
                CaptainRemovedEvent.fire(eventBus, captain);
            }

			@Override
			public String getAsyncName() {
				return "com.qagwaai.starmalaccamax.client.data.CaptainDS.executeRemove.RemoveCaptain";
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
        CaptainRecord captainRecord = new CaptainRecord();
        copyValues(rec, captainRecord);

        dataService.execute(new UpdateCaptain(captainRecord.toCaptain()), new UpdatedCaptain() {
            @Override
            public void got(final CaptainDTO captain) {
                CaptainUpdatedEvent.fire(eventBus, captain);

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
            public void onSuccess(final UpdateCaptainResponse result) {
                super.onSuccess(result);

                if (result.getCaptain() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    CaptainRecord[] records = new CaptainRecord[1];
                    records[0] = new CaptainRecord(result.getCaptain());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    CaptainRecord[] records = new CaptainRecord[0];
                    response.setData(records);
                }
                processResponse(requestId, response);
            }

			@Override
			public String getAsyncName() {
				return "com.qagwaai.starmalaccamax.client.data.CaptainDS.executeUpdate.UpdateCaptain";
			}
        });

    }

    /**
     * @return the dataService
     */
    public CaptainServiceAsync getDataService() {
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
    public void setDataService(final CaptainServiceAsync dataService) {
        this.dataService = dataService;
    }

}
