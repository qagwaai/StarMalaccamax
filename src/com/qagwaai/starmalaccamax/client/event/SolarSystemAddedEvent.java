package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;

/**
 * This event is sent to the {@link EventBus} when the current user information
 * has changed. For example, if the user logged in or logged out.
 * 
 * @author pgirard
 */
public final class SolarSystemAddedEvent extends GwtEvent<SolarSystemAddedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<SolarSystemAddedHandler> TYPE = new Type<SolarSystemAddedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param solarSystem
     *            the solarSystem to include in the details
     */
    public static void fire(final EventBus eventBus, final SolarSystemDTO solarSystem) {
        eventBus.fireEvent(new SolarSystemAddedEvent(solarSystem));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<SolarSystemAddedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final SolarSystemDTO solarSystem;

    /**
     * Constructor
     * 
     * @param solarSystem
     *            the SolarSystem
     */
    public SolarSystemAddedEvent(final SolarSystemDTO solarSystem) {
        this.solarSystem = solarSystem;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final SolarSystemAddedHandler handler) {
        handler.onSolarSystemAdded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<SolarSystemAddedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current user attached to this event.
     * 
     * @return The {@link CurrentUser} describing the currently logged in user.
     */
    public SolarSystemDTO getSolarSystem() {
        return solarSystem;
    }
}
