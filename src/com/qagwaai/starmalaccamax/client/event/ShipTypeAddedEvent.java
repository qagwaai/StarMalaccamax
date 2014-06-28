package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.ShipTypeDTO;

/**
 * This event is sent to the {@link EventBus} when the current user information
 * has changed. For example, if the user logged in or logged out.
 * 
 * @author pgirard
 */
public final class ShipTypeAddedEvent extends GwtEvent<ShipTypeAddedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<ShipTypeAddedHandler> TYPE = new Type<ShipTypeAddedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param shipType
     *            the ShipType to include in the details
     */
    public static void fire(final EventBus eventBus, final ShipTypeDTO shipType) {
        eventBus.fireEvent(new ShipTypeAddedEvent(shipType));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<ShipTypeAddedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final ShipTypeDTO shipType;

    /**
     * Constructor
     * 
     * @param shipType
     *            the ShipType
     */
    public ShipTypeAddedEvent(final ShipTypeDTO shipType) {
        this.shipType = shipType;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final ShipTypeAddedHandler handler) {
        handler.onShipTypeAdded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<ShipTypeAddedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current user attached to this event.
     * 
     * @return The {@link CurrentUser} describing the currently logged in user.
     */
    public ShipTypeDTO getShipType() {
        return shipType;
    }
}
