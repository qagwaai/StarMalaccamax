package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;

/**
 * This event is sent to the {@link EventBus} when the current captain
 * information has changed. For example, if the captain logged in or logged out.
 * 
 * @author pgirard
 */
public final class CaptainRemovedEvent extends GwtEvent<CaptainRemovedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<CaptainRemovedHandler> TYPE = new Type<CaptainRemovedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param captain
     *            the captain to include in the details
     */
    public static void fire(final EventBus eventBus, final CaptainDTO captain) {
        eventBus.fireEvent(new CaptainRemovedEvent(captain));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<CaptainRemovedHandler> getType() {
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
    public CaptainRemovedEvent(final CaptainDTO captain) {
        this.captain = captain;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final CaptainRemovedHandler handler) {
        handler.onCaptainRemoved(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<CaptainRemovedHandler> getAssociatedType() {
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
