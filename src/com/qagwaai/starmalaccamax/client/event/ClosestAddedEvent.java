package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;

/**
 * This event is sent to the {@link EventBus} when the current user information
 * has changed. For example, if the user logged in or logged out.
 * 
 * @author pgirard
 */
public final class ClosestAddedEvent extends GwtEvent<ClosestAddedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<ClosestAddedHandler> TYPE = new Type<ClosestAddedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param closest
     *            the Closest to include in the details
     */
    public static void fire(final EventBus eventBus, final ClosestDTO closest) {
        eventBus.fireEvent(new ClosestAddedEvent(closest));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<ClosestAddedHandler> getType() {
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
     *            the Closest
     */
    public ClosestAddedEvent(final ClosestDTO closest) {
        this.closest = closest;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final ClosestAddedHandler handler) {
        handler.onClosestAdded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<ClosestAddedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current user attached to this event.
     * 
     * @return The {@link CurrentUser} describing the currently logged in user.
     */
    public ClosestDTO getClosest() {
        return closest;
    }
}
