package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.JobDTO;

/**
 * This event is sent to the {@link EventBus} when the current job information
 * has changed. For example, if the job logged in or logged out.
 * 
 * @author pgirard
 */
public final class JobUpdatedEvent extends GwtEvent<JobUpdatedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<JobUpdatedHandler> TYPE = new Type<JobUpdatedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param job
     *            the job to include in the details
     */
    public static void fire(final EventBus eventBus, final JobDTO job) {
        eventBus.fireEvent(new JobUpdatedEvent(job));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<JobUpdatedHandler> getType() {
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
    public JobUpdatedEvent(final JobDTO job) {
        this.job = job;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final JobUpdatedHandler handler) {
        handler.onJobUpdated(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<JobUpdatedHandler> getAssociatedType() {
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
