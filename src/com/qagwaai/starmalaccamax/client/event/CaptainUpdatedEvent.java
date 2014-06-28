package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;

/**
 * This event is sent to the {@link EventBus} when the current captain
 * information has changed. For example, if the captain logged in or logged out.
 * 
 * @author pgirard
 */
public final class CaptainUpdatedEvent extends GwtEvent<CaptainUpdatedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<CaptainUpdatedHandler> TYPE = new Type<CaptainUpdatedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param captain
     *            the captain to include in the details
     */
    public static void fire(final EventBus eventBus, final CaptainDTO captain) {
        eventBus.fireEvent(new CaptainUpdatedEvent(captain));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<CaptainUpdatedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final CaptainDTO captain;

    /**
     * Constructor
     * 
     * @param captain
     *            the captain
     */
    public CaptainUpdatedEvent(final CaptainDTO captain) {
        this.captain = captain;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final CaptainUpdatedHandler handler) {
        handler.onCaptainUpdated(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<CaptainUpdatedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current captain attached to this event.
     * 
     * @return The {@link CurrentCaptain} describing the currently logged in
     *         captain.
     */
    public CaptainDTO getCaptain() {
        return captain;
    }
}
