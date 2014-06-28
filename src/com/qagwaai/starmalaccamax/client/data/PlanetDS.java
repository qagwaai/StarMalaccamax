/**
 * PlanetDS.java
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
import com.qagwaai.starmalaccamax.client.event.PlanetAddedEvent;
import com.qagwaai.starmalaccamax.client.event.PlanetRemovedEvent;
import com.qagwaai.starmalaccamax.client.event.PlanetUpdatedEvent;
import com.qagwaai.starmalaccamax.client.event.PlanetsLoadedEvent;
import com.qagwaai.starmalaccamax.client.event.ServiceExceptionEvent;
import com.qagwaai.starmalaccamax.client.service.PlanetServiceAsync;
import com.qagwaai.starmalaccamax.client.service.action.AddPlanet;
import com.qagwaai.starmalaccamax.client.service.action.AddPlanetResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllPlanets;
import com.qagwaai.starmalaccamax.client.service.action.GetAllPlanetsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemovePlanet;
import com.qagwaai.starmalaccamax.client.service.action.RemovePlanetResponse;
import com.qagwaai.starmalaccamax.client.service.action.UpdatePlanet;
import com.qagwaai.starmalaccamax.client.service.action.UpdatePlanetResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.AddedPlanet;
import com.qagwaai.starmalaccamax.client.service.helpers.GotAllPlanets;
import com.qagwaai.starmalaccamax.client.service.helpers.RemovedPlanet;
import com.qagwaai.starmalaccamax.client.service.helpers.UpdatedPlanet;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
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
public final class PlanetDS extends GwtRpcDataSource {

    /**
	 * 
	 */
    public static final String DS_TYPE = "Planet";

    /**
     * 
     * @param from
     *            to copy from
     * @param to
     *            to copy to
     * @throws DataException
     *             if the operation failed
     */
    private static void copyValues(final ListGridRecord from, final PlanetRecord to) throws DataException {
        to.setId(LongConverter.fromJavaScript(from, PlanetRecord.ID));
        to.setSolarSystemId(LongConverter.fromJavaScript(from, PlanetRecord.SOLARSYSTEM_ID));
        to.setSize(NullConverter.toInt(from.getAttributeAsInt(PlanetRecord.SIZE)));
        to.setAtmosphere(NullConverter.toInt(from.getAttributeAsInt(PlanetRecord.ATMOSPHERE)));
        to.setOrbit(NullConverter.toInt(from.getAttributeAsInt(PlanetRecord.ORBIT)));
        to.setName(NullConverter.toString(from.getAttributeAsString(PlanetRecord.NAME)));
        to.setLawLevel(NullConverter.toInt(from.getAttributeAsInt(PlanetRecord.LAW_LEVEL)));
        to.setHydrographics(NullConverter.toDouble(from.getAttributeAsDouble(PlanetRecord.HYDROGRAPHICS)));
        to.setPopulation(LongConverter.fromJavaScript(from, PlanetRecord.POPULATION));
        to.setDockQuality(NullConverter.toInt(from.getAttributeAsInt(PlanetRecord.DOCK_QUALITY)));
        to.setGovernment(NullConverter.toInt(from.getAttributeAsInt(PlanetRecord.GOVERNMENT)));
        to.setGasGiant(from.getAttributeAsBoolean(PlanetRecord.IS_GAS_GIANT));
        to.setTechLevel(NullConverter.toInt(from.getAttributeAsInt(PlanetRecord.TECH_LEVEL)));
        to.setMainWorld(from.getAttributeAsBoolean(PlanetRecord.IS_MAIN_WORLD));
        to.setSatellite(from.getAttributeAsBoolean(PlanetRecord.IS_SATELLITE));
        to.setOrbitalDistance(from.getAttributeAsDouble(PlanetRecord.ORBITAL_DISTANCE));
        to.setSatelliteParentId(LongConverter.fromJavaScript(from, PlanetRecord.SATELLITE_PARENT_ID));

        to.setOrbitCenterX(NullConverter.toInt(from.getAttributeAsInt(PlanetRecord.ORBIT_CENTER_X)));
        to.setOrbitCenterY(NullConverter.toInt(from.getAttributeAsInt(PlanetRecord.ORBIT_CENTER_Y)));
        to.setOrbitalEccentricity(NullConverter.toDouble(from.getAttributeAsDouble(PlanetRecord.ORBITAL_ECCENTRICITY)));
        to.setOrbitRotation(NullConverter.toInt(from.getAttributeAsInt(PlanetRecord.ORBIT_ROTATION)));

        to.setOrbitXRel(NullConverter.toInt(from.getAttributeAsInt(PlanetRecord.ORBIT_X_RELATIVE)));
        to.setOrbitYRel(NullConverter.toInt(from.getAttributeAsInt(PlanetRecord.ORBIT_Y_RELATIVE)));
    }

    /**
     * 
     */
    private PlanetServiceAsync dataService = null;

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
    public PlanetDS(final String id, final PlanetServiceAsync dataService, final EventBus eventBus) {
        super();
        setID(id);

        this.dataService = dataService;
        this.eventBus = eventBus;
        this.setCanMultiSort(true);

        DataSourceIntegerField pkField = new DataSourceIntegerField(PlanetRecord.ID);
        pkField.setPrimaryKey(true);
        pkField.setCanEdit(false);

        DataSourceIntegerField fkSolarSystemIdField = new DataSourceIntegerField(PlanetRecord.SOLARSYSTEM_ID);
        DataSourceTextField nameField = new DataSourceTextField(PlanetRecord.NAME, "Name");
        DataSourceFloatField sizeField = new DataSourceFloatField(PlanetRecord.SIZE, "Size");
        DataSourceFloatField atmosphereField = new DataSourceFloatField(PlanetRecord.ATMOSPHERE, "Atmosphere");
        DataSourceIntegerField orbitField = new DataSourceIntegerField(PlanetRecord.ORBIT, "Orbit");
        DataSourceIntegerField lawLevelField = new DataSourceIntegerField(PlanetRecord.LAW_LEVEL, "Law Level");
        DataSourceFloatField hydrographicsField = new DataSourceFloatField(PlanetRecord.HYDROGRAPHICS, "Hydrographics");
        DataSourceFloatField populationField = new DataSourceFloatField(PlanetRecord.POPULATION, "Population");
        DataSourceFloatField dockQualityField = new DataSourceFloatField(PlanetRecord.DOCK_QUALITY, "Dock Quality");
        DataSourceFloatField governmentField = new DataSourceFloatField(PlanetRecord.GOVERNMENT, "Government");
        DataSourceBooleanField isGasGiantField = new DataSourceBooleanField(PlanetRecord.IS_GAS_GIANT, "Gas Giant?");
        DataSourceIntegerField techLevelField = new DataSourceIntegerField(PlanetRecord.TECH_LEVEL, "Tech Level");
        DataSourceBooleanField isMainWorldField = new DataSourceBooleanField(PlanetRecord.IS_MAIN_WORLD, "Main World?");
        DataSourceBooleanField isSatelliteField = new DataSourceBooleanField(PlanetRecord.IS_SATELLITE, "Satellite?");
        DataSourceFloatField orbitalDistanceField =
            new DataSourceFloatField(PlanetRecord.ORBITAL_DISTANCE, "Orbital Distance");
        DataSourceTextField locationField = new DataSourceTextField(PlanetRecord.LOCATION_OBJ, "Location");
        DataSourceIntegerField fkSatelliteParentIdField = new DataSourceIntegerField(PlanetRecord.SATELLITE_PARENT_ID);

        DataSourceIntegerField orbitCenterXField = new DataSourceIntegerField(PlanetRecord.ORBIT_CENTER_X, "Center X");
        DataSourceIntegerField orbitCenterYField = new DataSourceIntegerField(PlanetRecord.ORBIT_CENTER_Y, "Center Y");
        DataSourceFloatField orbitalEccentricityField =
            new DataSourceFloatField(PlanetRecord.ORBITAL_ECCENTRICITY, "Eccentricity");
        DataSourceIntegerField orbitRotationField = new DataSourceIntegerField(PlanetRecord.ORBIT_ROTATION, "Rotation");

        DataSourceIntegerField orbitXRelField = new DataSourceIntegerField(PlanetRecord.ORBIT_X_RELATIVE, "X Rel");
        DataSourceIntegerField orbitYRelField = new DataSourceIntegerField(PlanetRecord.ORBIT_Y_RELATIVE, "Y Rel");

        setFields(pkField, fkSolarSystemIdField, nameField, sizeField, atmosphereField, orbitField, lawLevelField,
            hydrographicsField, populationField, dockQualityField, governmentField, isGasGiantField, techLevelField,
            isMainWorldField, isSatelliteField, orbitalDistanceField, locationField, fkSatelliteParentIdField,
            orbitCenterXField, orbitCenterYField, orbitalEccentricityField, orbitRotationField, orbitXRelField,
            orbitYRelField);
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
        PlanetRecord planetRecord = new PlanetRecord();
        copyValues(rec, planetRecord);

        dataService.execute(new AddPlanet(planetRecord.toPlanet()), new AddedPlanet() {
            @Override
            public void got(final PlanetDTO planet) {
                PlanetAddedEvent.fire(eventBus, planet);
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
            public void onSuccess(final AddPlanetResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getPlanet() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    PlanetRecord[] records = new PlanetRecord[1];
                    records[0] = new PlanetRecord(result.getPlanet());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    PlanetRecord[] records = new PlanetRecord[0];
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
        GWT.log(DS_TYPE + "DS.transformRequest.FETCH Starting:" + request.getStartRow() + ", ending:"
            + request.getEndRow() + " sort: [" + fullSortString + "], criteria: [" + criterionArray + "]");

        dataService.execute(new GetAllPlanets(startRow, endRow, criterionArray, fullSortString), new GotAllPlanets() {

            public void got(final ArrayList<PlanetDTO> planets) {
                PlanetsLoadedEvent.fire(eventBus, planets);
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
            public void onSuccess(final GetAllPlanetsResponse result) {
                GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess");
                ArrayList<PlanetDTO> planets = result.getPlanets();
                if (result.getPlanets().size() > 0) {
                    response.setTotalRows(result.getTotalPlanets());
                    response.setStartRow(startRow);
                    if (endRow > result.getTotalPlanets()) {
                        response.setEndRow(result.getTotalPlanets());
                    } else {
                        response.setEndRow(endRow);
                    }
                    PlanetRecord[] records = new PlanetRecord[planets.size() + 1];
                    for (int i = 0; i < planets.size(); i++) {
                        records[i] = new PlanetRecord(planets.get(i));
                    }
                    GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess(start, end, total, size) = "
                        + response.getStartRow() + ", " + response.getEndRow() + ", " + result.getTotalPlanets() + ", "
                        + planets.size());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    PlanetRecord[] records = new PlanetRecord[0];
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
        PlanetRecord testRec = new PlanetRecord();
        copyValues(rec, testRec);

        dataService.execute(new RemovePlanet(testRec.toPlanet()), new RemovedPlanet() {
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
            public void onSuccess(final RemovePlanetResponse result) {
                ListGridRecord[] list = new ListGridRecord[1];
                // We do not receive removed record from server.
                // Return record from request.
                list[0] = rec;
                response.setData(list);
                processResponse(requestId, response);
                super.onSuccess(result);
            }

            @Override
            public void removed(final PlanetDTO planet) {
                PlanetRemovedEvent.fire(eventBus, planet);
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
        PlanetRecord planetRecord = new PlanetRecord();
        copyValues(rec, planetRecord);

        dataService.execute(new UpdatePlanet(planetRecord.toPlanet()), new UpdatedPlanet() {
            @Override
            public void got(final PlanetDTO planet) {
                PlanetUpdatedEvent.fire(eventBus, planet);

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
            public void onSuccess(final UpdatePlanetResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getPlanet() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    PlanetRecord[] records = new PlanetRecord[1];
                    records[0] = new PlanetRecord(result.getPlanet());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    PlanetRecord[] records = new PlanetRecord[0];
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
    public PlanetServiceAsync getDataService() {
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
    public void setDataService(final PlanetServiceAsync dataService) {
        this.dataService = dataService;
    }

}
