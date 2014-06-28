package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.JobDTO;

/**
 * This event is sent to the {@link EventBus} when the current user information
 * has changed. For example, if the user logged in or logged out.
 * 
 * @author pgirard
 */
public final class JobAddedEvent extends GwtEvent<JobAddedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<JobAddedHandler> TYPE = new Type<JobAddedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param job
     *            the Job to include in the details
     */
    public static void fire(final EventBus eventBus, final JobDTO job) {
        eventBus.fireEvent(new JobAddedEvent(job));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<JobAddedHandler> getType() {
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
     *            the Job
     */
    public JobAddedEvent(final JobDTO job) {
        this.job = job;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final JobAddedHandler handler) {
        handler.onJobAdded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<JobAddedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current user attached to this event.
     * 
     * @return The {@link CurrentUser} describing the currently logged in user.
     */
    public JobDTO getJob() {
        return job;
    }
}
