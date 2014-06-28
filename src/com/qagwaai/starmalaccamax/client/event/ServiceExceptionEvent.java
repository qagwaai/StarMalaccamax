package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * This event is sent to the {@link EventBus} when the current user information
 * has changed. For example, if the user logged in or logged out.
 * 
 * @author pgirard
 */
public final class ServiceExceptionEvent extends GwtEvent<ServiceExceptionHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<ServiceExceptionHandler> TYPE = new Type<ServiceExceptionHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param exception
     *            the Exception to include in the details
     */
    public static void fire(final EventBus eventBus, final Throwable exception) {
        eventBus.fireEvent(new ServiceExceptionEvent(exception));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<ServiceExceptionHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final Throwable exception;

    /**
     * Constructor
     * 
     * @param exception
     *            the exception
     */
    public ServiceExceptionEvent(final Throwable exception) {
        this.exception = exception;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final ServiceExceptionHandler handler) {
        handler.onServiceException(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<ServiceExceptionHandler> getAssociatedType() {
        return getType();
    }

    /**
     * @return Access the exception attached to this event.
     */
    public Throwable getThrowable() {
        return exception;
    }
}
