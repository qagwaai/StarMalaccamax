package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.StarDTO;

/**
 * This event is sent to the {@link EventBus} when the current user information
 * has changed. For example, if the user logged in or logged out.
 * 
 * @author pgirard
 */
public final class StarAddedEvent extends GwtEvent<StarAddedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<StarAddedHandler> TYPE = new Type<StarAddedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param star
     *            the Star to include in the details
     */
    public static void fire(final EventBus eventBus, final StarDTO star) {
        eventBus.fireEvent(new StarAddedEvent(star));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<StarAddedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final StarDTO star;

    /**
     * Constructor
     * 
     * @param star
     *            the Star
     */
    public StarAddedEvent(final StarDTO star) {
        this.star = star;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final StarAddedHandler handler) {
        handler.onStarAdded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<StarAddedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current user attached to this event.
     * 
     * @return The {@link CurrentUser} describing the currently logged in user.
     */
    public StarDTO getStar() {
        return star;
    }
}
