package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.ShipTypeDTO;

/**
 * This event is sent to the {@link EventBus} when the current shipType
 * information has changed. For example, if the shipType logged in or logged
 * out.
 * 
 * @author pgirard
 */
public final class ShipTypeRemovedEvent extends GwtEvent<ShipTypeRemovedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<ShipTypeRemovedHandler> TYPE = new Type<ShipTypeRemovedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param shipType
     *            the shipType to include in the details
     */
    public static void fire(final EventBus eventBus, final ShipTypeDTO shipType) {
        eventBus.fireEvent(new ShipTypeRemovedEvent(shipType));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<ShipTypeRemovedHandler> getType() {
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
     *            the shipType
     */
    public ShipTypeRemovedEvent(final ShipTypeDTO shipType) {
        this.shipType = shipType;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final ShipTypeRemovedHandler handler) {
        handler.onShipTypeRemoved(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<ShipTypeRemovedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current shipType attached to this event.
     * 
     * @return The {@link CurrentShipType} describing the currently logged in
     *         shipType.
     */
    public ShipTypeDTO getShipType() {
        return shipType;
    }
}
