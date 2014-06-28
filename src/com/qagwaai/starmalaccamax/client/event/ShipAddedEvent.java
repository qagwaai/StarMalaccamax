package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;

/**
 * This event is sent to the {@link EventBus} when the current user information
 * has changed. For example, if the user logged in or logged out.
 * 
 * @author pgirard
 */
public final class ShipAddedEvent extends GwtEvent<ShipAddedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<ShipAddedHandler> TYPE = new Type<ShipAddedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param ship
     *            the Ship to include in the details
     */
    public static void fire(final EventBus eventBus, final ShipDTO ship) {
        eventBus.fireEvent(new ShipAddedEvent(ship));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<ShipAddedHandler> getType() {
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
    public ShipAddedEvent(final ShipDTO ship) {
        this.ship = ship;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final ShipAddedHandler handler) {
        handler.onShipAdded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<ShipAddedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current user attached to this event.
     * 
     * @return The {@link CurrentUser} describing the currently logged in user.
     */
    public ShipDTO getShip() {
        return ship;
    }
}
