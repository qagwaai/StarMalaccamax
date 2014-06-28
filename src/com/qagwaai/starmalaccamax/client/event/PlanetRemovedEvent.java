package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * This event is sent to the {@link EventBus} when the current planet
 * information has changed. For example, if the planet logged in or logged out.
 * 
 * @author pgirard
 */
public final class PlanetRemovedEvent extends GwtEvent<PlanetRemovedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<PlanetRemovedHandler> TYPE = new Type<PlanetRemovedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param planet
     *            the planet to include in the details
     */
    public static void fire(final EventBus eventBus, final PlanetDTO planet) {
        eventBus.fireEvent(new PlanetRemovedEvent(planet));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<PlanetRemovedHandler> getType() {
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
    public PlanetRemovedEvent(final PlanetDTO planet) {
        this.planet = planet;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final PlanetRemovedHandler handler) {
        handler.onPlanetRemoved(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<PlanetRemovedHandler> getAssociatedType() {
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
