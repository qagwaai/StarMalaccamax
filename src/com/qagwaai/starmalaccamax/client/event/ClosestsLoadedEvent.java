package com.qagwaai.starmalaccamax.client.event;

import java.util.ArrayList;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;

/**
 * This event is sent to the {@link EventBus} when the current closest
 * information has changed. For example, if the closest logged in or logged out.
 * 
 * @author pgirard
 */
public final class ClosestsLoadedEvent extends GwtEvent<ClosestsLoadedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<ClosestsLoadedHandler> TYPE = new Type<ClosestsLoadedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param closests
     *            the closests to include in the details
     */
    public static void fire(final EventBus eventBus, final ArrayList<ClosestDTO> closests) {
        eventBus.fireEvent(new ClosestsLoadedEvent(closests));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<ClosestsLoadedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final ArrayList<ClosestDTO> closests;

    /**
     * Constructor
     * 
     * @param closests
     *            the closests
     */
    public ClosestsLoadedEvent(final ArrayList<ClosestDTO> closests) {
        this.closests = closests;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final ClosestsLoadedHandler handler) {
        handler.onClosestsLoaded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<ClosestsLoadedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current closest attached to this event.
     * 
     * @return The {@link CurrentClosest} describing the currently logged in
     *         closest.
     */
    public ArrayList<ClosestDTO> getClosests() {
        return closests;
    }
}
