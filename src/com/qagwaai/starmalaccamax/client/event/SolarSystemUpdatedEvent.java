package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;

/**
 * This event is sent to the {@link EventBus} when the current solarSystem
 * information has changed. For example, if the solarSystem logged in or logged
 * out.
 * 
 * @author pgirard
 */
public final class SolarSystemUpdatedEvent extends GwtEvent<SolarSystemUpdatedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<SolarSystemUpdatedHandler> TYPE = new Type<SolarSystemUpdatedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param solarSystem
     *            the solarSystem to include in the details
     */
    public static void fire(final EventBus eventBus, final SolarSystemDTO solarSystem) {
        eventBus.fireEvent(new SolarSystemUpdatedEvent(solarSystem));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<SolarSystemUpdatedHandler> getType() {
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
     *            the solarSystem
     */
    public SolarSystemUpdatedEvent(final SolarSystemDTO solarSystem) {
        this.solarSystem = solarSystem;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final SolarSystemUpdatedHandler handler) {
        handler.onSolarSystemUpdated(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<SolarSystemUpdatedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current solarSystem attached to this event.
     * 
     * @return The {@link CurrentSolarSystem} describing the currently logged in
     *         solarSystem.
     */
    public SolarSystemDTO getSolarSystem() {
        return solarSystem;
    }
}
