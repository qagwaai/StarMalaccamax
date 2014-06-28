package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;

/**
 * This event is sent to the {@link EventBus} when the current user information
 * has changed. For example, if the user logged in or logged out.
 * 
 * @author pgirard
 */
public final class CaptainAddedEvent extends GwtEvent<CaptainAddedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<CaptainAddedHandler> TYPE = new Type<CaptainAddedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param captain
     *            the Captain to include in the details
     */
    public static void fire(final EventBus eventBus, final CaptainDTO captain) {
        eventBus.fireEvent(new CaptainAddedEvent(captain));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<CaptainAddedHandler> getType() {
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
     *            the Captain
     */
    public CaptainAddedEvent(final CaptainDTO captain) {
        this.captain = captain;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final CaptainAddedHandler handler) {
        handler.onCaptainAdded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<CaptainAddedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current user attached to this event.
     * 
     * @return The {@link CurrentUser} describing the currently logged in user.
     */
    public CaptainDTO getCaptain() {
        return captain;
    }
}
