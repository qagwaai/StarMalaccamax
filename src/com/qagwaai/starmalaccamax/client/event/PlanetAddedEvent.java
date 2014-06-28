package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * This event is sent to the {@link EventBus} when the current user information
 * has changed. For example, if the user logged in or logged out.
 * 
 * @author pgirard
 */
public final class PlanetAddedEvent extends GwtEvent<PlanetAddedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<PlanetAddedHandler> TYPE = new Type<PlanetAddedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param planet
     *            the Planet to include in the details
     */
    public static void fire(final EventBus eventBus, final PlanetDTO planet) {
        eventBus.fireEvent(new PlanetAddedEvent(planet));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<PlanetAddedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final PlanetDTO planet;

    /**
     * Constructor
     * 
     * @param planet
     *            the Planet
     */
    public PlanetAddedEvent(final PlanetDTO planet) {
        this.planet = planet;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final PlanetAddedHandler handler) {
        handler.onPlanetAdded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<PlanetAddedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current user attached to this event.
     * 
     * @return The {@link CurrentUser} describing the currently logged in user.
     */
    public PlanetDTO getPlanet() {
        return planet;
    }
}
