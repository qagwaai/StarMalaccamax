package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.StarDTO;

/**
 * This event is sent to the {@link EventBus} when the current star information
 * has changed. For example, if the star logged in or logged out.
 * 
 * @author pgirard
 */
public final class StarRemovedEvent extends GwtEvent<StarRemovedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<StarRemovedHandler> TYPE = new Type<StarRemovedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param star
     *            the star to include in the details
     */
    public static void fire(final EventBus eventBus, final StarDTO star) {
        eventBus.fireEvent(new StarRemovedEvent(star));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<StarRemovedHandler> getType() {
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
     *            the star
     */
    public StarRemovedEvent(final StarDTO star) {
        this.star = star;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final StarRemovedHandler handler) {
        handler.onStarRemoved(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<StarRemovedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current star attached to this event.
     * 
     * @return The {@link CurrentStar} describing the currently logged in star.
     */
    public StarDTO getStar() {
        return star;
    }
}
