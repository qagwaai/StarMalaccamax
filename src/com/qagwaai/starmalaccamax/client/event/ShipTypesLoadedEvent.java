package com.qagwaai.starmalaccamax.client.event;

import java.util.ArrayList;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.ShipTypeDTO;

/**
 * This event is sent to the {@link EventBus} when the current shipType
 * information has changed. For example, if the shipType logged in or logged
 * out.
 * 
 * @author pgirard
 */
public final class ShipTypesLoadedEvent extends GwtEvent<ShipTypesLoadedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<ShipTypesLoadedHandler> TYPE = new Type<ShipTypesLoadedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param shipTypes
     *            the shipTypes to include in the details
     */
    public static void fire(final EventBus eventBus, final ArrayList<ShipTypeDTO> shipTypes) {
        eventBus.fireEvent(new ShipTypesLoadedEvent(shipTypes));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<ShipTypesLoadedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final ArrayList<ShipTypeDTO> shipTypes;

    /**
     * Constructor
     * 
     * @param shipTypes
     *            the shipTypes
     */
    public ShipTypesLoadedEvent(final ArrayList<ShipTypeDTO> shipTypes) {
        this.shipTypes = shipTypes;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final ShipTypesLoadedHandler handler) {
        handler.onShipTypesLoaded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<ShipTypesLoadedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current shipType attached to this event.
     * 
     * @return The {@link CurrentShipType} describing the currently logged in
     *         shipType.
     */
    public ArrayList<ShipTypeDTO> getShipTypes() {
        return shipTypes;
    }
}
