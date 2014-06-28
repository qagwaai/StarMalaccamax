package com.qagwaai.starmalaccamax.client.event;

import java.util.ArrayList;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;

/**
 * This event is sent to the {@link EventBus} when the current captain
 * information has changed. For example, if the captain logged in or logged out.
 * 
 * @author pgirard
 */
public final class CaptainsLoadedEvent extends GwtEvent<CaptainsLoadedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<CaptainsLoadedHandler> TYPE = new Type<CaptainsLoadedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param captains
     *            the captain to include in the details
     */
    public static void fire(final EventBus eventBus, final ArrayList<CaptainDTO> captains) {
        eventBus.fireEvent(new CaptainsLoadedEvent(captains));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<CaptainsLoadedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final ArrayList<CaptainDTO> captains;

    /**
     * Constructor
     * 
     * @param captains
     *            the captains
     */
    public CaptainsLoadedEvent(final ArrayList<CaptainDTO> captains) {
        this.captains = captains;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final CaptainsLoadedHandler handler) {
        handler.onCaptainsLoaded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<CaptainsLoadedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current captain attached to this event.
     * 
     * @return The {@link CurrentCaptain} describing the currently logged in
     *         captain.
     */
    public ArrayList<CaptainDTO> getCaptains() {
        return captains;
    }
}
