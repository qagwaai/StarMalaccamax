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
import com.qagwaai.starmalaccamax.client.event.StarAddedEvent;
import com.qagwaai.starmalaccamax.client.event.StarRemovedEvent;
import com.qagwaai.starmalaccamax.client.event.StarUpdatedEvent;
import com.qagwaai.starmalaccamax.client.event.StarsLoadedEvent;
import com.qagwaai.starmalaccamax.client.service.StarServiceAsync;
import com.qagwaai.starmalaccamax.client.service.action.AddStar;
import com.qagwaai.starmalaccamax.client.service.action.AddStarResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllStars;
import com.qagwaai.starmalaccamax.client.service.action.GetAllStarsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveStar;
import com.qagwaai.starmalaccamax.client.service.action.RemoveStarResponse;
import com.qagwaai.starmalaccamax.client.service.action.UpdateStar;
import com.qagwaai.starmalaccamax.client.service.action.UpdateStarResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.AddedStar;
import com.qagwaai.starmalaccamax.client.service.helpers.GotAllStars;
import com.qagwaai.starmalaccamax.client.service.helpers.RemovedStar;
import com.qagwaai.starmalaccamax.client.service.helpers.UpdatedStar;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.StarDTO;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceFloatField;
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
public final class StarDS extends GwtRpcDataSource {

    /**
	 * 
	 */
    public static final String DS_TYPE = "Star";

    /**
     * 
     * @param from
     *            to copy from
     * @param to
     *            to copy to
     * @throws DataException
     *             if the operation failed
     */
    private static void copyValues(final ListGridRecord from, final StarRecord to) throws DataException {
        to.setId(LongConverter.fromJavaScript(from, StarRecord.ID));
        to.setSolarSystemId(LongConverter.fromJavaScript(from, StarRecord.SOLARSYSTEM_ID));
        to.setSpectralType(from.getAttributeAsString(StarRecord.SPECTRAL_TYPE));
        to.setSpectralTypeDec(from.getAttributeAsDouble(StarRecord.SPECTRAL_TYPE_DEC));
        to.setSize(from.getAttributeAsString(StarRecord.SIZE));
        to.setComponentIdentifier(from.getAttributeAsString(StarRecord.COMPONENT_ID));
        to.setOrbit(from.getAttributeAsInt(StarRecord.ORBIT));
        to.setCompanion(from.getAttributeAsBoolean(StarRecord.IS_COMPANION));
    }

    /**
     * 
     */
    private StarServiceAsync dataService = null;

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
    public StarDS(final String id, final StarServiceAsync dataService, final EventBus eventBus) {
        super();
        setID(id);

        this.dataService = dataService;
        this.eventBus = eventBus;
        this.setCanMultiSort(true);

        DataSourceIntegerField pkField = new DataSourceIntegerField(StarRecord.ID);
        // pkField.setHidden(true);
        pkField.setPrimaryKey(true);
        pkField.setCanEdit(false);

        DataSourceIntegerField fkField = new DataSourceIntegerField(StarRecord.SOLARSYSTEM_ID, "Solar System Id");

        DataSourceTextField spectralTypeField = new DataSourceTextField(StarRecord.SPECTRAL_TYPE, "Spectral Type");
        DataSourceFloatField spectralTypeDecField =
            new DataSourceFloatField(StarRecord.SPECTRAL_TYPE_DEC, "Spectral Type Dec");
        DataSourceTextField sizeField = new DataSourceTextField(StarRecord.SIZE, "Size");
        DataSourceTextField componentIdField = new DataSourceTextField(StarRecord.COMPONENT_ID, "Component Identifier");
        DataSourceIntegerField orbitField = new DataSourceIntegerField(StarRecord.ORBIT, "Orbit");
        DataSourceBooleanField companionField = new DataSourceBooleanField(StarRecord.IS_COMPANION, "Companion?");

        setFields(pkField, fkField, spectralTypeField, spectralTypeDecField, sizeField, componentIdField, orbitField,
            companionField);
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
        StarRecord starRecord = new StarRecord();
        copyValues(rec, starRecord);

        dataService.execute(new AddStar(starRecord.toStar()), new AddedStar() {
            @Override
            public void got(final StarDTO star) {
                StarAddedEvent.fire(eventBus, star);

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
            public void onSuccess(final AddStarResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getStar() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    StarRecord[] records = new StarRecord[1];
                    records[0] = new StarRecord(result.getStar());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    StarRecord[] records = new StarRecord[0];
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

        dataService.execute(new GetAllStars(startRow, endRow, criterionArray, fullSortString), new GotAllStars() {

            public void got(final ArrayList<StarDTO> stars) {
                StarsLoadedEvent.fire(eventBus, stars);
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
            public void onSuccess(final GetAllStarsResponse result) {
                GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess");
                // DSResponse response = new DSResponse();
                ArrayList<StarDTO> stars = result.getStars();
                if (result.getStars().size() > 0) {
                    response.setTotalRows(result.getTotalStars());
                    response.setStartRow(startRow);
                    if (endRow > result.getTotalStars()) {
                        response.setEndRow(result.getTotalStars());
                    } else {
                        response.setEndRow(endRow);
                    }
                    StarRecord[] records = new StarRecord[stars.size() + 1];
                    for (int i = 0; i < stars.size(); i++) {
                        records[i] = new StarRecord(stars.get(i));
                    }
                    GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess(start, end, total, size) = "
                        + response.getStartRow() + ", " + response.getEndRow() + ", " + result.getTotalStars() + ", "
                        + stars.size());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    StarRecord[] records = new StarRecord[0];
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
        StarRecord testRec = new StarRecord();
        copyValues(rec, testRec);

        dataService.execute(new RemoveStar(testRec.toStar()), new RemovedStar() {
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
            public void onSuccess(final RemoveStarResponse result) {
                ListGridRecord[] list = new ListGridRecord[1];
                // We do not receive removed record from server.
                // Return record from request.
                list[0] = rec;
                response.setData(list);
                processResponse(requestId, response);
                super.onSuccess(result);
            }

            @Override
            public void removed(final StarDTO star) {
                StarRemovedEvent.fire(eventBus, star);
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
        StarRecord starRecord = new StarRecord();
        copyValues(rec, starRecord);

        dataService.execute(new UpdateStar(starRecord.toStar()), new UpdatedStar() {
            @Override
            public void got(final StarDTO star) {
                StarUpdatedEvent.fire(eventBus, star);

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
            public void onSuccess(final UpdateStarResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getStar() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    StarRecord[] records = new StarRecord[1];
                    records[0] = new StarRecord(result.getStar());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    StarRecord[] records = new StarRecord[0];
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
    public StarServiceAsync getDataService() {
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
    public void setDataService(final StarServiceAsync dataService) {
        this.dataService = dataService;
    }

}
