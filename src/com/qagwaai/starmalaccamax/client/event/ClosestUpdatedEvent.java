package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;

/**
 * This event is sent to the {@link EventBus} when the current closest
 * information has changed. For example, if the closest logged in or logged out.
 * 
 * @author pgirard
 */
public final class ClosestUpdatedEvent extends GwtEvent<ClosestUpdatedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<ClosestUpdatedHandler> TYPE = new Type<ClosestUpdatedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param closest
     *            the closest to include in the details
     */
    public static void fire(final EventBus eventBus, final ClosestDTO closest) {
        eventBus.fireEvent(new ClosestUpdatedEvent(closest));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<ClosestUpdatedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final ClosestDTO closest;

    /**
     * Constructor
     * 
     * @param closest
     *            the closest
     */
    public ClosestUpdatedEvent(final ClosestDTO closest) {
        this.closest = closest;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final ClosestUpdatedHandler handler) {
        handler.onClosestUpdated(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<ClosestUpdatedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current closest attached to this event.
     * 
     * @return The {@link CurrentClosest} describing the currently logged in
     *         closest.
     */
    public ClosestDTO getClosest() {
        return closest;
    }
}
