package com.qagwaai.starmalaccamax.client.event;

import java.util.ArrayList;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.JumpGateDTO;

/**
 * This event is sent to the {@link EventBus} when the current jumpGate
 * information has changed. For example, if the jumpGate logged in or logged
 * out.
 * 
 * @author pgirard
 */
public final class JumpGatesLoadedEvent extends GwtEvent<JumpGatesLoadedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<JumpGatesLoadedHandler> TYPE = new Type<JumpGatesLoadedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param jumpGates
     *            the jumpGate to include in the details
     */
    public static void fire(final EventBus eventBus, final ArrayList<JumpGateDTO> jumpGates) {
        eventBus.fireEvent(new JumpGatesLoadedEvent(jumpGates));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<JumpGatesLoadedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final ArrayList<JumpGateDTO> jumpGates;

    /**
     * Constructor
     * 
     * @param jumpGates
     *            the jumpGates
     */
    public JumpGatesLoadedEvent(final ArrayList<JumpGateDTO> jumpGates) {
        this.jumpGates = jumpGates;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final JumpGatesLoadedHandler handler) {
        handler.onJumpGatesLoaded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<JumpGatesLoadedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current jumpGate attached to this event.
     * 
     * @return The {@link CurrentJumpGate} describing the currently logged in
     *         jumpGate.
     */
    public ArrayList<JumpGateDTO> getJumpGates() {
        return jumpGates;
    }
}
