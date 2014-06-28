package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;

/**
 * This event is sent to the {@link EventBus} when the current Ship information
 * has changed. For example, if the Ship logged in or logged out.
 * 
 * @author pgirard
 */
public final class ShipUpdatedEvent extends GwtEvent<ShipUpdatedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<ShipUpdatedHandler> TYPE = new Type<ShipUpdatedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param ship
     *            the Ship to include in the details
     */
    public static void fire(final EventBus eventBus, final ShipDTO ship) {
        eventBus.fireEvent(new ShipUpdatedEvent(ship));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<ShipUpdatedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final ShipDTO ship;

    /**
     * Constructor
     * 
     * @param ship
     *            the Ship
     */
    public ShipUpdatedEvent(final ShipDTO ship) {
        this.ship = ship;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final ShipUpdatedHandler handler) {
        handler.onShipUpdated(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<ShipUpdatedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current Ship attached to this event.
     * 
     * @return The {@link CurrentShip} describing the currently logged in Ship.
     */
    public ShipDTO getShip() {
        return ship;
    }
}
