package com.qagwaai.starmalaccamax.client.core.events;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.client.event.EventBus;

/**
 * This event is sent to the {@link EventBus} when the current user information
 * has changed. For example, if the user logged in or logged out.
 * 
 * @author pgirard
 */
public final class WindowResizedEvent extends GwtEvent<WindowResizedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<WindowResizedHandler> TYPE = new Type<WindowResizedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     */
    public static void fire(final EventBus eventBus) {
        eventBus.fireEvent(new WindowResizedEvent());
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<WindowResizedHandler> getType() {
        return TYPE;
    }

    /**
     * Constructor
     * 
     */
    public WindowResizedEvent() {
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final WindowResizedHandler handler) {
        handler.onWindowResized(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<WindowResizedHandler> getAssociatedType() {
        return getType();
    }
}
