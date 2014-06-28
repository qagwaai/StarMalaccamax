package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * This event is sent to the {@link EventBus} when the current user information
 * has changed. For example, if the user logged in or logged out.
 * 
 * @author pgirard
 */
public final class MouseOverPlanetEvent extends GwtEvent<MouseOverPlanetHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<MouseOverPlanetHandler> TYPE = new Type<MouseOverPlanetHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param market
     *            the Market to include in the details
     */
    public static void fire(final EventBus eventBus, final String planetName) {
        eventBus.fireEvent(new MouseOverPlanetEvent(planetName));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<MouseOverPlanetHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final String planetName;

    /**
     * Constructor
     * 
     * @param market
     *            the Market
     */
    public MouseOverPlanetEvent(final String planetName) {
        this.planetName = planetName;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final MouseOverPlanetHandler handler) {
        handler.onMouseOverPlanet(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<MouseOverPlanetHandler> getAssociatedType() {
        return getType();
    }

    public String getPlanetName() {
        return planetName;
    }


}
