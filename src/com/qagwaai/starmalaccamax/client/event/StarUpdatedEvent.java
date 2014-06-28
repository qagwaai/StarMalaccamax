package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.StarDTO;

/**
 * This event is sent to the {@link EventBus} when the current star information
 * has changed. For example, if the star logged in or logged out.
 * 
 * @author pgirard
 */
public final class StarUpdatedEvent extends GwtEvent<StarUpdatedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<StarUpdatedHandler> TYPE = new Type<StarUpdatedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param star
     *            the star to include in the details
     */
    public static void fire(final EventBus eventBus, final StarDTO star) {
        eventBus.fireEvent(new StarUpdatedEvent(star));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<StarUpdatedHandler> getType() {
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
    public StarUpdatedEvent(final StarDTO star) {
        this.star = star;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final StarUpdatedHandler handler) {
        handler.onStarUpdated(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<StarUpdatedHandler> getAssociatedType() {
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
