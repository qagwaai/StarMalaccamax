package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.JumpGateDTO;

/**
 * This event is sent to the {@link EventBus} when the current user information
 * has changed. For example, if the user logged in or logged out.
 * 
 * @author pgirard
 */
public final class JumpGateAddedEvent extends GwtEvent<JumpGateAddedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<JumpGateAddedHandler> TYPE = new Type<JumpGateAddedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param jumpGate
     *            the JumpGate to include in the details
     */
    public static void fire(final EventBus eventBus, final JumpGateDTO jumpGate) {
        eventBus.fireEvent(new JumpGateAddedEvent(jumpGate));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<JumpGateAddedHandler> getType() {
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
     *            the JumpGate
     */
    public JumpGateAddedEvent(final JumpGateDTO jumpGate) {
        this.jumpGate = jumpGate;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final JumpGateAddedHandler handler) {
        handler.onJumpGateAdded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<JumpGateAddedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current user attached to this event.
     * 
     * @return The {@link CurrentUser} describing the currently logged in user.
     */
    public JumpGateDTO getJumpGate() {
        return jumpGate;
    }
}
