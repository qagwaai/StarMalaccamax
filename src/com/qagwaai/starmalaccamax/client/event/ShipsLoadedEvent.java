package com.qagwaai.starmalaccamax.client.event;

import java.util.ArrayList;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;

/**
 * This event is sent to the {@link EventBus} when the current ship information
 * has changed. For example, if the ship logged in or logged out.
 * 
 * @author pgirard
 */
public final class ShipsLoadedEvent extends GwtEvent<ShipsLoadedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<ShipsLoadedHandler> TYPE = new Type<ShipsLoadedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param ships
     *            the ship to include in the details
     */
    public static void fire(final EventBus eventBus, final ArrayList<ShipDTO> ships) {
        eventBus.fireEvent(new ShipsLoadedEvent(ships));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<ShipsLoadedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final ArrayList<ShipDTO> ships;

    /**
     * Constructor
     * 
     * @param ships
     *            the ships
     */
    public ShipsLoadedEvent(final ArrayList<ShipDTO> ships) {
        this.ships = ships;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final ShipsLoadedHandler handler) {
        handler.onShipsLoaded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<ShipsLoadedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current ship attached to this event.
     * 
     * @return The {@link CurrentShip} describing the currently logged in ship.
     */
    public ArrayList<ShipDTO> getShips() {
        return ships;
    }
}
