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
import com.qagwaai.starmalaccamax.client.event.SolarSystemAddedEvent;
import com.qagwaai.starmalaccamax.client.event.SolarSystemRemovedEvent;
import com.qagwaai.starmalaccamax.client.event.SolarSystemUpdatedEvent;
import com.qagwaai.starmalaccamax.client.event.SolarSystemsLoadedEvent;
import com.qagwaai.starmalaccamax.client.service.SolarSystemServiceAsync;
import com.qagwaai.starmalaccamax.client.service.action.AddSolarSystem;
import com.qagwaai.starmalaccamax.client.service.action.AddSolarSystemResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllSolarSystems;
import com.qagwaai.starmalaccamax.client.service.action.GetAllSolarSystemsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveSolarSystem;
import com.qagwaai.starmalaccamax.client.service.action.RemoveSolarSystemResponse;
import com.qagwaai.starmalaccamax.client.service.action.UpdateSolarSystem;
import com.qagwaai.starmalaccamax.client.service.action.UpdateSolarSystemResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.AddedSolarSystem;
import com.qagwaai.starmalaccamax.client.service.helpers.GotAllSolarSystems;
import com.qagwaai.starmalaccamax.client.service.helpers.RemovedSolarSystem;
import com.qagwaai.starmalaccamax.client.service.helpers.UpdatedSolarSystem;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
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
public final class SolarSystemDS extends GwtRpcDataSource {

    /**
	 * 
	 */
    public static final String DS_TYPE = "SolarSystem";

    /**
     * 
     * @param from
     *            to copy from
     * @param to
     *            to copy to
     * @throws DataException
     *             if the operation failed
     */
    private static void copyValues(final ListGridRecord from, final SolarSystemRecord to) throws DataException {
        to.setId(LongConverter.fromJavaScript(from, SolarSystemRecord.ID));
        to.setAlpha(from.getAttributeAsDouble(SolarSystemRecord.ALPHA));
        to.setDelta(from.getAttributeAsDouble(SolarSystemRecord.DELTA));
        to.setHIP(from.getAttributeAsInt(SolarSystemRecord.HIP));
        to.setName(from.getAttributeAsString(SolarSystemRecord.NAME));
        to.setNumberOfComponents(from.getAttributeAsInt(SolarSystemRecord.NUMBER_OF_COMPONENTS));
        to.setParallax(from.getAttributeAsDouble(SolarSystemRecord.PARALLAX));
        to.setX(from.getAttributeAsDouble(SolarSystemRecord.X));
        to.setY(from.getAttributeAsDouble(SolarSystemRecord.Y));
        to.setZ(from.getAttributeAsDouble(SolarSystemRecord.Z));
        to.setMaximumOrbits(from.getAttributeAsInt(SolarSystemRecord.MAXIMUM_ORBITS));
        to.setHabitableOrbit(from.getAttributeAsInt(SolarSystemRecord.HABITABLE_ORBIT));
        to.setNumberOfGasGiants(from.getAttributeAsInt(SolarSystemRecord.GAS_GIANTS));
        to.setNumberOfPlanetoidBelts(from.getAttributeAsInt(SolarSystemRecord.PLANETOID_BELTS));
    }

    /**
     * 
     */
    private SolarSystemServiceAsync dataService = null;

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
    public SolarSystemDS(final String id, final SolarSystemServiceAsync dataService, final EventBus eventBus) {
        super();
        setID(id);

        this.dataService = dataService;
        this.eventBus = eventBus;
        this.setCanMultiSort(true);

        DataSourceIntegerField pkField = new DataSourceIntegerField(SolarSystemRecord.ID);
        // pkField.setHidden(true);
        pkField.setPrimaryKey(true);
        pkField.setCanEdit(false);

        DataSourceTextField nameField = new DataSourceTextField(SolarSystemRecord.NAME, "Name");

        DataSourceFloatField alphaField = new DataSourceFloatField(SolarSystemRecord.ALPHA, "Alpha");
        DataSourceFloatField deltaField = new DataSourceFloatField(SolarSystemRecord.DELTA, "Delta");
        DataSourceIntegerField hipField = new DataSourceIntegerField(SolarSystemRecord.HIP, "HIP");
        DataSourceIntegerField numberOfComponentsField =
            new DataSourceIntegerField(SolarSystemRecord.NUMBER_OF_COMPONENTS, "Components");
        DataSourceFloatField parallaxField = new DataSourceFloatField(SolarSystemRecord.PARALLAX, "Parallax");
        DataSourceFloatField xField = new DataSourceFloatField(SolarSystemRecord.X, "X");
        DataSourceFloatField yField = new DataSourceFloatField(SolarSystemRecord.Y, "Y");
        DataSourceFloatField zField = new DataSourceFloatField(SolarSystemRecord.Z, "Z");
        DataSourceIntegerField maximumOrbitsField =
            new DataSourceIntegerField(SolarSystemRecord.MAXIMUM_ORBITS, "Max Orbits");
        DataSourceIntegerField habitableOrbitField =
            new DataSourceIntegerField(SolarSystemRecord.HABITABLE_ORBIT, "Habitable Orbit");
        DataSourceIntegerField gasGiantsField = new DataSourceIntegerField(SolarSystemRecord.GAS_GIANTS, "Gas Giants");
        DataSourceIntegerField planetoidBeltsField =
            new DataSourceIntegerField(SolarSystemRecord.PLANETOID_BELTS, "Planetoid Belts");

        setFields(pkField, nameField, alphaField, deltaField, hipField, numberOfComponentsField, parallaxField, xField,
            yField, zField, maximumOrbitsField, habitableOrbitField, gasGiantsField, planetoidBeltsField);
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
        SolarSystemRecord solarSystemRecord = new SolarSystemRecord();
        copyValues(rec, solarSystemRecord);

        dataService.execute(new AddSolarSystem(solarSystemRecord.toSolarSystem()), new AddedSolarSystem() {
            @Override
            public void got(final SolarSystemDTO solarSystem) {
                SolarSystemAddedEvent.fire(eventBus, solarSystem);

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
            public void onSuccess(final AddSolarSystemResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getSolarSystem() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    SolarSystemRecord[] records = new SolarSystemRecord[1];
                    records[0] = new SolarSystemRecord(result.getSolarSystem());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    SolarSystemRecord[] records = new SolarSystemRecord[0];
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

        dataService.execute(new GetAllSolarSystems(startRow, endRow, criterionArray, fullSortString),
            new GotAllSolarSystems() {

                public void got(final ArrayList<SolarSystemDTO> solarSystems) {
                    SolarSystemsLoadedEvent.fire(eventBus, solarSystems);
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
                public void onSuccess(final GetAllSolarSystemsResponse result) {
                    GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess");
                    // DSResponse response = new DSResponse();
                    ArrayList<SolarSystemDTO> solarSystems = result.getSolarSystems();
                    if (result.getSolarSystems().size() > 0) {
                        response.setTotalRows(result.getTotalSolarSystems());
                        response.setStartRow(startRow);
                        if (endRow > result.getTotalSolarSystems()) {
                            response.setEndRow(result.getTotalSolarSystems());
                        } else {
                            response.setEndRow(endRow);
                        }
                        SolarSystemRecord[] records = new SolarSystemRecord[solarSystems.size() + 1];
                        for (int i = 0; i < solarSystems.size(); i++) {
                            records[i] = new SolarSystemRecord(solarSystems.get(i));
                        }
                        GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess(start, end, total, size) = "
                            + response.getStartRow() + ", " + response.getEndRow() + ", "
                            + result.getTotalSolarSystems() + ", " + solarSystems.size());
                        response.setData(records);
                    } else {
                        response.setTotalRows(0);
                        response.setStartRow(0);
                        response.setEndRow(0);
                        SolarSystemRecord[] records = new SolarSystemRecord[0];
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
        SolarSystemRecord testRec = new SolarSystemRecord();
        copyValues(rec, testRec);

        dataService.execute(new RemoveSolarSystem(testRec.toSolarSystem()), new RemovedSolarSystem() {
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
            public void onSuccess(final RemoveSolarSystemResponse result) {
                ListGridRecord[] list = new ListGridRecord[1];
                // We do not receive removed record from server.
                // Return record from request.
                list[0] = rec;
                response.setData(list);
                processResponse(requestId, response);
                super.onSuccess(result);
            }

            @Override
            public void removed(final SolarSystemDTO solarSystem) {
                SolarSystemRemovedEvent.fire(eventBus, solarSystem);
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
        SolarSystemRecord solarSystemRecord = new SolarSystemRecord();
        copyValues(rec, solarSystemRecord);

        dataService.execute(new UpdateSolarSystem(solarSystemRecord.toSolarSystem()), new UpdatedSolarSystem() {
            @Override
            public void got(final SolarSystemDTO solarSystem) {
                SolarSystemUpdatedEvent.fire(eventBus, solarSystem);

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
            public void onSuccess(final UpdateSolarSystemResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getSolarSystem() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    SolarSystemRecord[] records = new SolarSystemRecord[1];
                    records[0] = new SolarSystemRecord(result.getSolarSystem());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    SolarSystemRecord[] records = new SolarSystemRecord[0];
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
