package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.channel.Message;

/**
 * This event is sent to the {@link EventBus} when the current user information
 * has changed. For example, if the user logged in or logged out.
 * 
 * @author pgirard
 */
public final class PushMessageReceivedEvent extends GwtEvent<PushMessageReceivedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<PushMessageReceivedHandler> TYPE = new Type<PushMessageReceivedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param message
     *            the message to include in the details
     */
    public static void fire(final EventBus eventBus, final Message message) {
        eventBus.fireEvent(new PushMessageReceivedEvent(message));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<PushMessageReceivedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final Message message;

    /**
     * Constructor
     * 
     * @param message
     *            the message
     */
    public PushMessageReceivedEvent(final Message message) {
        this.message = message;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final PushMessageReceivedHandler handler) {
        handler.onPushMessageReceived(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<PushMessageReceivedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current user attached to this event.
     * 
     * @return The {@link CurrentUser} describing the currently logged in user.
     */
    public Message getMessage() {
        return message;
    }
}
