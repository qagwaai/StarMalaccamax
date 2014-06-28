package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * This event is sent to the {@link EventBus} when the current user information
 * has changed. For example, if the user logged in or logged out.
 * 
 * @author pgirard
 */
public final class UserLoadedEvent extends GwtEvent<UserLoadedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<UserLoadedHandler> TYPE = new Type<UserLoadedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param user
     *            the user to include in the details
     */
    public static void fire(final EventBus eventBus, final UserDTO user) {
        eventBus.fireEvent(new UserLoadedEvent(user));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<UserLoadedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final UserDTO user;

    /**
     * Constructor
     * 
     * @param user
     *            the user
     */
    public UserLoadedEvent(final UserDTO user) {
        this.user = user;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final UserLoadedHandler handler) {
        handler.onUserLoaded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<UserLoadedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current user attached to this event.
     * 
     * @return The {@link CurrentUser} describing the currently logged in user.
     */
    public UserDTO getUser() {
        return user;
    }
}
