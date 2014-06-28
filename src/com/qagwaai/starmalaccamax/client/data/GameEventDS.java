/**
 * GameEventDS.java
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
import com.qagwaai.starmalaccamax.client.event.GameEventAddedEvent;
import com.qagwaai.starmalaccamax.client.event.GameEventRemovedEvent;
import com.qagwaai.starmalaccamax.client.event.GameEventUpdatedEvent;
import com.qagwaai.starmalaccamax.client.event.GameEventsLoadedEvent;
import com.qagwaai.starmalaccamax.client.event.ServiceExceptionEvent;
import com.qagwaai.starmalaccamax.client.service.GameEventServiceAsync;
import com.qagwaai.starmalaccamax.client.service.action.AddGameEvent;
import com.qagwaai.starmalaccamax.client.service.action.AddGameEventResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllGameEvents;
import com.qagwaai.starmalaccamax.client.service.action.GetAllGameEventsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveGameEvent;
import com.qagwaai.starmalaccamax.client.service.action.RemoveGameEventResponse;
import com.qagwaai.starmalaccamax.client.service.action.UpdateGameEvent;
import com.qagwaai.starmalaccamax.client.service.action.UpdateGameEventResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.AddedGameEvent;
import com.qagwaai.starmalaccamax.client.service.helpers.GotAllGameEvents;
import com.qagwaai.starmalaccamax.client.service.helpers.RemovedGameEvent;
import com.qagwaai.starmalaccamax.client.service.helpers.UpdatedGameEvent;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceDateTimeField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.calendar.CalendarEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class GameEventDS extends GwtRpcDataSource {

    /**
	 * 
	 */
    public static final String DS_TYPE = "GameEvent";

    /**
     * 
     * @param from
     *            to copy from
     * @param to
     *            to copy to
     * @throws DataException
     *             if the operation failed
     */
    private static void copyValues(final ListGridRecord from, final GameEventRecord to) throws DataException {
        to.setId(LongConverter.fromJavaScript(from, GameEventRecord.ID));
        to.setPlayerId(LongConverter.fromJavaScript(from, GameEventRecord.PLAYER_ID));
        to.setStartDateTime(NullConverter.toDate(from.getAttributeAsDate(GameEventRecord.STARTDATETIME)));
        to.setEndDateTime(NullConverter.toDate(from.getAttributeAsDate(GameEventRecord.ENDDATETIME)));
        to.setShortDescription(NullConverter.toString(from.getAttributeAsString(GameEventRecord.SHORT_DESCRIPTION)));
        to.setFullDescription(NullConverter.toString(from.getAttributeAsString(GameEventRecord.FULL_DESCRIPTION)));
        // to.setActivityClass(NullConverter.toString(from.getAttributeAsString(GameEventRecord.ACTION_CLASS)));
	to.setEventEnabled(from.getAttributeAsBoolean(GameEventRecord.EVENT_ENABLED));
        to.setActivityType(from.getAttributeAsString(GameEventRecord.ACTIVITY_TYPE));
        to.setActivityTypeId(from.getAttributeAsInt(GameEventRecord.ACTIVITY_TYPE_ID));
        to.setActivityCompletionDate(from.getAttributeAsDate(GameEventRecord.ACTIVITY_COMPLETION_DATE));
    }

    /**
     * 
     * @param event
     *            the event to convert
     * @return the hydrated DTO
     */
    public static GameEventDTO fromCalendarEvent(final CalendarEvent event) {
        GameEventDTO ge = new GameEventDTO();
        ge.setStartDateTime(event.getStartDate());
        ge.setEndDateTime(event.getEndDate());
        ge.setId(Long.valueOf(event.getEventId()));
        ge.setShortDescription(event.getName());
        ge.setFullDescription(event.getDescription());
	ge.setEventEnabled(true);
        return ge;
    }

    /**
     * 
     */
    private GameEventServiceAsync dataService = null;

    /**
     * 
     */
    private final EventBus eventBus;

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
    public GameEventDS(final String id, final GameEventServiceAsync dataService, final EventBus eventBus) {
        super();
        setID(id);

        this.dataService = dataService;
        this.eventBus = eventBus;
        this.setCanMultiSort(true);

        DataSourceIntegerField pkField = new DataSourceIntegerField(GameEventRecord.ID);
        pkField.setPrimaryKey(true);
        pkField.setCanEdit(false);

        DataSourceIntegerField fkPlanetIdField = new DataSourceIntegerField(GameEventRecord.PLAYER_ID);
        DataSourceDateTimeField startDateTimeField =
            new DataSourceDateTimeField(GameEventRecord.STARTDATETIME, "Start Date Time");
        DataSourceDateTimeField endDateTimeField =
            new DataSourceDateTimeField(GameEventRecord.ENDDATETIME, "End Date Time");
        DataSourceTextField shortDescriptionField = new DataSourceTextField(GameEventRecord.SHORT_DESCRIPTION, "Short");
        DataSourceTextField fullDescriptionField = new DataSourceTextField(GameEventRecord.FULL_DESCRIPTION, "Full");
        TextAreaItem textAreaItem = new TextAreaItem();
        textAreaItem.setHeight(70);
        textAreaItem.setWidth(200);
        fullDescriptionField.setEditorType(textAreaItem);

        // DataSourceTextField actionClassField = new
        // DataSourceTextField(GameEventRecord.ACTION_CLASS, "Action Class");
	DataSourceBooleanField enabledField = new DataSourceBooleanField(GameEventRecord.EVENT_ENABLED, "Event Enabled");
        DataSourceTextField activityTypeField = new DataSourceTextField(GameEventRecord.ACTIVITY_TYPE, "Activity Type");
        DataSourceIntegerField activityTypeIdField =
            new DataSourceIntegerField(GameEventRecord.ACTIVITY_TYPE_ID, "Activity Type Id");
        DataSourceDateTimeField activityCompletionDateField =
            new DataSourceDateTimeField(GameEventRecord.ACTIVITY_COMPLETION_DATE, "Activity Completion Date");

        setFields(pkField, fkPlanetIdField, startDateTimeField, endDateTimeField, shortDescriptionField,
            fullDescriptionField, enabledField, activityTypeField, activityTypeIdField, activityCompletionDateField);
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
        GameEventRecord gameEventRecord = new GameEventRecord();
        copyValues(rec, gameEventRecord);

        dataService.execute(new AddGameEvent(gameEventRecord.toGameEvent()), new AddedGameEvent() {
            @Override
            public void got(final GameEventDTO gameEvent) {
                GameEventAddedEvent.fire(eventBus, gameEvent);
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
            public void onSuccess(final AddGameEventResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getGameEvent() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    GameEventRecord[] records = new GameEventRecord[1];
                    records[0] = new GameEventRecord(result.getGameEvent());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    GameEventRecord[] records = new GameEventRecord[0];
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

        dataService.execute(new GetAllGameEvents(startRow, endRow, criterionArray, fullSortString),
            new GotAllGameEvents() {

                @Override
		public void got(final ArrayList<GameEventDTO> gameEvents) {
                    GameEventsLoadedEvent.fire(eventBus, gameEvents);
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
                public void onSuccess(final GetAllGameEventsResponse result) {
                    GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess");
                    ArrayList<GameEventDTO> gameEvents = result.getGameEvents();
                    if (result.getGameEvents().size() > 0) {
                        response.setTotalRows(result.getTotalGameEvents());
                        response.setStartRow(startRow);
                        if (endRow > result.getTotalGameEvents()) {
                            response.setEndRow(result.getTotalGameEvents());
                        } else {
                            response.setEndRow(endRow);
                        }
                        GameEventRecord[] records = new GameEventRecord[gameEvents.size() + 1];
                        for (int i = 0; i < gameEvents.size(); i++) {
                            records[i] = new GameEventRecord(gameEvents.get(i));
                        }
                        GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess(start, end, total, size) = "
                            + response.getStartRow() + ", " + response.getEndRow() + ", " + result.getTotalGameEvents()
                            + ", " + gameEvents.size());
                        response.setData(records);
                    } else {
                        response.setTotalRows(0);
                        response.setStartRow(0);
                        response.setEndRow(0);
                        GameEventRecord[] records = new GameEventRecord[0];
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
        GameEventRecord testRec = new GameEventRecord();
        copyValues(rec, testRec);

        dataService.execute(new RemoveGameEvent(testRec.toGameEvent()), new RemovedGameEvent() {
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
            public void onSuccess(final RemoveGameEventResponse result) {
                ListGridRecord[] list = new ListGridRecord[1];
                // We do not receive removed record from server.
                // Return record from request.
                list[0] = rec;
                response.setData(list);
                processResponse(requestId, response);
                super.onSuccess(result);
            }

            @Override
            public void removed(final GameEventDTO gameEvent) {
                GameEventRemovedEvent.fire(eventBus, gameEvent);
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
        GameEventRecord gameEventRecord = new GameEventRecord();
        copyValues(rec, gameEventRecord);

        dataService.execute(new UpdateGameEvent(gameEventRecord.toGameEvent()), new UpdatedGameEvent() {
            @Override
            public void got(final GameEventDTO gameEvent) {
                GameEventUpdatedEvent.fire(eventBus, gameEvent);

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
            public void onSuccess(final UpdateGameEventResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getGameEvent() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    GameEventRecord[] records = new GameEventRecord[1];
                    records[0] = new GameEventRecord(result.getGameEvent());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    GameEventRecord[] records = new GameEventRecord[0];
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
    public GameEventServiceAsync getDataService() {
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
    public void setDataService(final GameEventServiceAsync dataService) {
        this.dataService = dataService;
    }
}
