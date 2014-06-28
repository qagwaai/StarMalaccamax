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
import com.qagwaai.starmalaccamax.client.event.JobAddedEvent;
import com.qagwaai.starmalaccamax.client.event.JobRemovedEvent;
import com.qagwaai.starmalaccamax.client.event.JobUpdatedEvent;
import com.qagwaai.starmalaccamax.client.event.JobsLoadedEvent;
import com.qagwaai.starmalaccamax.client.service.JobServiceAsync;
import com.qagwaai.starmalaccamax.client.service.action.AddJob;
import com.qagwaai.starmalaccamax.client.service.action.AddJobResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllJobs;
import com.qagwaai.starmalaccamax.client.service.action.GetAllJobsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveJob;
import com.qagwaai.starmalaccamax.client.service.action.RemoveJobResponse;
import com.qagwaai.starmalaccamax.client.service.action.UpdateJob;
import com.qagwaai.starmalaccamax.client.service.action.UpdateJobResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.AddedJob;
import com.qagwaai.starmalaccamax.client.service.helpers.GotAllJobs;
import com.qagwaai.starmalaccamax.client.service.helpers.RemovedJob;
import com.qagwaai.starmalaccamax.client.service.helpers.UpdatedJob;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.JobDTO;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceDateField;
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
public final class JobDS extends GwtRpcDataSource {

    /**
	 * 
	 */
    public static final String DS_TYPE = "Job";

    /**
     * 
     * @param from
     *            to copy from
     * @param to
     *            to copy to
     * @throws DataException
     *             if the operation failed
     */
    private static void copyValues(final ListGridRecord from, final JobRecord to) throws DataException {
        to.setId(LongConverter.fromJavaScript(from, JobRecord.ID));
        to.setCommandClass(from.getAttributeAsString(JobRecord.COMMAND_CLASS));
        to.setDescription(from.getAttributeAsString(JobRecord.DESCRIPTION));
        to.setLastRun(from.getAttributeAsDate(JobRecord.LAST_RUN));
        to.setRecurrence(LongConverter.fromJavaScript(from, JobRecord.RECURRENCE));
        to.setStatus(from.getAttributeAsString(JobRecord.STATUS));
        to.setEnabled(from.getAttributeAsBoolean(JobRecord.ENABLED));
    }

    /**
     * 
     */
    private JobServiceAsync dataService = null;

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
    public JobDS(final String id, final JobServiceAsync dataService, final EventBus eventBus) {
        super();
        setID(id);

        this.dataService = dataService;
        this.eventBus = eventBus;
        this.setCanMultiSort(true);

        DataSourceIntegerField pkField = new DataSourceIntegerField(JobRecord.ID);
        pkField.setPrimaryKey(true);
        pkField.setCanEdit(false);

        DataSourceTextField commandClassField = new DataSourceTextField(JobRecord.COMMAND_CLASS);
        DataSourceTextField descriptionField = new DataSourceTextField(JobRecord.DESCRIPTION);
        DataSourceDateField lastRunField = new DataSourceDateField(JobRecord.LAST_RUN);
        DataSourceIntegerField recurrenceField = new DataSourceIntegerField(JobRecord.RECURRENCE);
        DataSourceTextField statusField = new DataSourceTextField(JobRecord.STATUS);
        DataSourceBooleanField enabledField = new DataSourceBooleanField(JobRecord.ENABLED);

        setFields(pkField, commandClassField, descriptionField, lastRunField, recurrenceField, statusField,
            enabledField);
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
        JobRecord jobRecord = new JobRecord();
        copyValues(rec, jobRecord);

        dataService.execute(new AddJob(jobRecord.toJob()), new AddedJob() {
            @Override
            public void got(final JobDTO job) {
                JobAddedEvent.fire(eventBus, job);

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
            public void onSuccess(final AddJobResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getJob() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    JobRecord[] records = new JobRecord[1];
                    records[0] = new JobRecord(result.getJob());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    JobRecord[] records = new JobRecord[0];
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

        dataService.execute(new GetAllJobs(startRow, endRow, criterionArray, fullSortString), new GotAllJobs() {

            public void got(final ArrayList<JobDTO> jobs) {
                JobsLoadedEvent.fire(eventBus, jobs);
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
            public void onSuccess(final GetAllJobsResponse result) {
                GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess");
                // DSResponse response = new DSResponse();
                ArrayList<JobDTO> jobs = result.getJobs();
                if (result.getJobs().size() > 0) {
                    response.setTotalRows(result.getTotalJobs());
                    response.setStartRow(startRow);
                    if (endRow > result.getTotalJobs()) {
                        response.setEndRow(result.getTotalJobs());
                    } else {
                        response.setEndRow(endRow);
                    }
                    JobRecord[] records = new JobRecord[jobs.size() + 1];
                    for (int i = 0; i < jobs.size(); i++) {
                        records[i] = new JobRecord(jobs.get(i));
                    }
                    GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess(start, end, total, size) = "
                        + response.getStartRow() + ", " + response.getEndRow() + ", " + result.getTotalJobs() + ", "
                        + jobs.size());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    JobRecord[] records = new JobRecord[0];
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
        JobRecord testRec = new JobRecord();
        copyValues(rec, testRec);

        dataService.execute(new RemoveJob(testRec.toJob()), new RemovedJob() {
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
            public void onSuccess(final RemoveJobResponse result) {
                ListGridRecord[] list = new ListGridRecord[1];
                // We do not receive removed record from server.
                // Return record from request.
                list[0] = rec;
                response.setData(list);
                processResponse(requestId, response);
                super.onSuccess(result);
            }

            @Override
            public void removed(final JobDTO job) {
                JobRemovedEvent.fire(eventBus, job);
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
        JobRecord jobRecord = new JobRecord();
        copyValues(rec, jobRecord);

        dataService.execute(new UpdateJob(jobRecord.toJob()), new UpdatedJob() {
            @Override
            public void got(final JobDTO job) {
                JobUpdatedEvent.fire(eventBus, job);

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
            public void onSuccess(final UpdateJobResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getJob() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    JobRecord[] records = new JobRecord[1];
                    records[0] = new JobRecord(result.getJob());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    JobRecord[] records = new JobRecord[0];
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
    public JobServiceAsync getDataService() {
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
    public void setDataService(final JobServiceAsync dataService) {
        this.dataService = dataService;
    }

}
