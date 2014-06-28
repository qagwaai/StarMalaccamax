package com.qagwaai.starmalaccamax.client.event;

import java.util.ArrayList;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * This event is sent to the {@link EventBus} when the current planet
 * information has changed. For example, if the planet logged in or logged out.
 * 
 * @author pgirard
 */
public final class PlanetsLoadedEvent extends GwtEvent<PlanetsLoadedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<PlanetsLoadedHandler> TYPE = new Type<PlanetsLoadedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param planets
     *            the planets to include in the details
     */
    public static void fire(final EventBus eventBus, final ArrayList<PlanetDTO> planets) {
        eventBus.fireEvent(new PlanetsLoadedEvent(planets));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<PlanetsLoadedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final ArrayList<PlanetDTO> planets;

    /**
     * Constructor
     * 
     * @param planets
     *            the planets
     */
    public PlanetsLoadedEvent(final ArrayList<PlanetDTO> planets) {
        this.planets = planets;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final PlanetsLoadedHandler handler) {
        handler.onPlanetsLoaded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<PlanetsLoadedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current planet attached to this event.
     * 
     * @return The {@link CurrentPlanet} describing the currently logged in
     *         planet.
     */
    public ArrayList<PlanetDTO> getPlanets() {
        return planets;
    }
}
