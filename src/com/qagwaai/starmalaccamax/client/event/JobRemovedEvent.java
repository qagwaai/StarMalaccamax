package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.JobDTO;

/**
 * This event is sent to the {@link EventBus} when the current job information
 * has changed. For example, if the job logged in or logged out.
 * 
 * @author pgirard
 */
public final class JobRemovedEvent extends GwtEvent<JobRemovedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<JobRemovedHandler> TYPE = new Type<JobRemovedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param job
     *            the job to include in the details
     */
    public static void fire(final EventBus eventBus, final JobDTO job) {
        eventBus.fireEvent(new JobRemovedEvent(job));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<JobRemovedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final JobDTO job;

    /**
     * Constructor
     * 
     * @param job
     *            the job
     */
    public JobRemovedEvent(final JobDTO job) {
        this.job = job;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final JobRemovedHandler handler) {
        handler.onJobRemoved(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<JobRemovedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current job attached to this event.
     * 
     * @return The {@link CurrentJob} describing the currently logged in job.
     */
    public JobDTO getJob() {
        return job;
    }
}
