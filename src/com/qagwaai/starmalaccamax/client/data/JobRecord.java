/**
 * UserRecord.java
 * Created by pgirard at 10:29:55 AM on Aug 20, 2010
 * in the com.qagwaai.starmalaccamax.shared.model.record package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.shared.model.Job;
import com.qagwaai.starmalaccamax.shared.model.JobDTO;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class JobRecord extends ListGridRecord implements Job {

    /**
	 * 
	 */
    public static final String ID = "id";
    /**
	 * 
	 */
    public static final String COMMAND_CLASS = "commandClass";
    /**
	 * 
	 */
    public static final String DESCRIPTION = "description";
    /**
	 * 
	 */
    public static final String LAST_RUN = "lastRun";

    /**
	 * 
	 */
    public static final String RECURRENCE = "recurrence";

    /**
	 * 
	 */
    public static final String STATUS = "status";

    /**
	 * 
	 */
    public static final String ENABLED = "enabled";

    /**
	 * 
	 */
    public JobRecord() {

    }

    /**
     * 
     * @param job
     *            the job DTO
     */
    public JobRecord(final JobDTO job) {
        setId(job.getId());
        setCommandClass(job.getCommandClass());
        setDescription(job.getDescription());
        setLastRun(job.getLastRun());
        setRecurrence(job.getRecurrence());
        setStatus(job.getStatus());
        setEnabled(job.isEnabled());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommandClass() {
        return getAttributeAsString(JobRecord.COMMAND_CLASS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return getAttributeAsString(JobRecord.DESCRIPTION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        try {
            return LongConverter.fromJavaScript(this, JobRecord.ID);
        } catch (DataException e) {
            GWT.log("Identity field could not be converted to Long", e);
        }
        return -1L;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getLastRun() {
        return getAttributeAsDate(JobRecord.LAST_RUN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getRecurrence() {
        try {
            return LongConverter.fromJavaScript(this, JobRecord.RECURRENCE);
        } catch (DataException e) {
            GWT.log("Identity field could not be converted to Long", e);
        }
        return -1L;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStatus() {
        return getAttributeAsString(JobRecord.STATUS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        return getAttributeAsBoolean(JobRecord.ENABLED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCommandClass(final String commandClass) {
        setAttribute(JobRecord.COMMAND_CLASS, commandClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDescription(final String description) {
        setAttribute(JobRecord.DESCRIPTION, description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnabled(final boolean enabled) {
        setAttribute(JobRecord.ENABLED, enabled);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final Long id) {
        setAttribute(JobRecord.ID, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLastRun(final Date lastRun) {
        setAttribute(JobRecord.LAST_RUN, lastRun);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRecurrence(final Long milliseconds) {
        setAttribute(JobRecord.RECURRENCE, milliseconds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStatus(final String status) {
        setAttribute(JobRecord.STATUS, status);
    }

    /**
     * 
     * @return a solar system object from this record
     */
    public JobDTO toJob() {
        JobDTO p = new JobDTO();
        p.setId(getId());
        p.setCommandClass(getCommandClass());
        p.setDescription(getDescription());
        p.setLastRun(getLastRun());
        p.setRecurrence(getRecurrence());
        p.setStatus(getStatus());
        p.setEnabled(isEnabled());
        return p;
    }
}
