package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;

/**
 * This event is sent to the {@link EventBus} when the current ship information
 * has changed. For example, if the ship logged in or logged out.
 * 
 * @author pgirard
 */
public final class ShipRemovedEvent extends GwtEvent<ShipRemovedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<ShipRemovedHandler> TYPE = new Type<ShipRemovedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param ship
     *            the ship to include in the details
     */
    public static void fire(final EventBus eventBus, final ShipDTO ship) {
        eventBus.fireEvent(new ShipRemovedEvent(ship));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<ShipRemovedHandler> getType() {
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
     *            the ship
     */
    public ShipRemovedEvent(final ShipDTO ship) {
        this.ship = ship;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final ShipRemovedHandler handler) {
        handler.onShipRemoved(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<ShipRemovedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current ship attached to this event.
     * 
     * @return The {@link CurrentShip} describing the currently logged in ship.
     */
    public ShipDTO getShip() {
        return ship;
    }
}
