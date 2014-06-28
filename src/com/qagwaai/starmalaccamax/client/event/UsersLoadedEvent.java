package com.qagwaai.starmalaccamax.client.event;

import java.util.ArrayList;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * This event is sent to the {@link EventBus} when the current user information
 * has changed. For example, if the user logged in or logged out.
 * 
 * @author pgirard
 */
public final class UsersLoadedEvent extends GwtEvent<UsersLoadedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<UsersLoadedHandler> TYPE = new Type<UsersLoadedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param users
     *            the Users to include in the details
     */
    public static void fire(final EventBus eventBus, final ArrayList<UserDTO> users) {
        eventBus.fireEvent(new UsersLoadedEvent(users));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<UsersLoadedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final ArrayList<UserDTO> users;

    /**
     * Constructor
     * 
     * @param users
     *            the users
     */
    public UsersLoadedEvent(final ArrayList<UserDTO> users) {
        this.users = users;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final UsersLoadedHandler handler) {
        handler.onUsersLoaded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<UsersLoadedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current user attached to this event.
     * 
     * @return The {@link CurrentUser} describing the currently logged in user.
     */
    public ArrayList<UserDTO> getUsers() {
        return users;
    }
}
