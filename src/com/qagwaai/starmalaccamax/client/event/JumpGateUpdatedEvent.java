package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.JumpGateDTO;

/**
 * This event is sent to the {@link EventBus} when the current jumpGate
 * information has changed. For example, if the jumpGate logged in or logged
 * out.
 * 
 * @author pgirard
 */
public final class JumpGateUpdatedEvent extends GwtEvent<JumpGateUpdatedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<JumpGateUpdatedHandler> TYPE = new Type<JumpGateUpdatedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param jumpGate
     *            the jumpGate to include in the details
     */
    public static void fire(final EventBus eventBus, final JumpGateDTO jumpGate) {
        eventBus.fireEvent(new JumpGateUpdatedEvent(jumpGate));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<JumpGateUpdatedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final JumpGateDTO jumpGate;

    /**
     * Constructor
     * 
     * @param jumpGate
     *            the jumpGate
     */
    public JumpGateUpdatedEvent(final JumpGateDTO jumpGate) {
        this.jumpGate = jumpGate;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final JumpGateUpdatedHandler handler) {
        handler.onJumpGateUpdated(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<JumpGateUpdatedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current jumpGate attached to this event.
     * 
     * @return The {@link CurrentJumpGate} describing the currently logged in
     *         jumpGate.
     */
    public JumpGateDTO getJumpGate() {
        return jumpGate;
    }
}
