package com.qagwaai.starmalaccamax.client.event;

import java.util.ArrayList;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;

/**
 * This event is sent to the {@link EventBus} when the current solarSystem
 * information has changed. For example, if the solarSystem logged in or logged
 * out.
 * 
 * @author pgirard
 */
public final class SolarSystemsLoadedEvent extends GwtEvent<SolarSystemsLoadedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<SolarSystemsLoadedHandler> TYPE = new Type<SolarSystemsLoadedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param solarSystems
     *            the solarSystems to include in the details
     */
    public static void fire(final EventBus eventBus, final ArrayList<SolarSystemDTO> solarSystems) {
        eventBus.fireEvent(new SolarSystemsLoadedEvent(solarSystems));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<SolarSystemsLoadedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final ArrayList<SolarSystemDTO> solarSystems;

    /**
     * Constructor
     * 
     * @param solarSystems
     *            the solarSystems
     */
    public SolarSystemsLoadedEvent(final ArrayList<SolarSystemDTO> solarSystems) {
        this.solarSystems = solarSystems;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final SolarSystemsLoadedHandler handler) {
        handler.onSolarSystemsLoaded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<SolarSystemsLoadedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current solarSystem attached to this event.
     * 
     * @return The {@link CurrentSolarSystem} describing the currently logged in
     *         solarSystem.
     */
    public ArrayList<SolarSystemDTO> getSolarSystems() {
        return solarSystems;
    }
}
