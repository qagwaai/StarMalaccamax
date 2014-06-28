package com.qagwaai.starmalaccamax.client.event;

import java.util.ArrayList;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.JobDTO;

/**
 * This event is sent to the {@link EventBus} when the current job information
 * has changed. For example, if the job logged in or logged out.
 * 
 * @author pgirard
 */
public final class JobsLoadedEvent extends GwtEvent<JobsLoadedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<JobsLoadedHandler> TYPE = new Type<JobsLoadedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param jobs
     *            the job to include in the details
     */
    public static void fire(final EventBus eventBus, final ArrayList<JobDTO> jobs) {
        eventBus.fireEvent(new JobsLoadedEvent(jobs));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<JobsLoadedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final ArrayList<JobDTO> jobs;

    /**
     * Constructor
     * 
     * @param jobs
     *            the jobs
     */
    public JobsLoadedEvent(final ArrayList<JobDTO> jobs) {
        this.jobs = jobs;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final JobsLoadedHandler handler) {
        handler.onJobsLoaded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<JobsLoadedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current job attached to this event.
     * 
     * @return The {@link CurrentJob} describing the currently logged in job.
     */
    public ArrayList<JobDTO> getJobs() {
        return jobs;
    }
}
