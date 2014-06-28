package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * This event is sent to the {@link EventBus} when the current planet
 * information has changed. For example, if the planet logged in or logged out.
 * 
 * @author pgirard
 */
public final class PlanetUpdatedEvent extends GwtEvent<PlanetUpdatedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<PlanetUpdatedHandler> TYPE = new Type<PlanetUpdatedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param planet
     *            the planet to include in the details
     */
    public static void fire(final EventBus eventBus, final PlanetDTO planet) {
        eventBus.fireEvent(new PlanetUpdatedEvent(planet));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<PlanetUpdatedHandler> getType() {
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
     *            the planet
     */
    public PlanetUpdatedEvent(final PlanetDTO planet) {
        this.planet = planet;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final PlanetUpdatedHandler handler) {
        handler.onPlanetUpdated(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<PlanetUpdatedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current planet attached to this event.
     * 
     * @return The {@link CurrentPlanet} describing the currently logged in
     *         planet.
     */
    public PlanetDTO getPlanet() {
        return planet;
    }
}
