package com.qagwaai.starmalaccamax.client.event;

import java.util.ArrayList;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.StarDTO;

/**
 * This event is sent to the {@link EventBus} when the current star information
 * has changed. For example, if the star logged in or logged out.
 * 
 * @author pgirard
 */
public final class StarsLoadedEvent extends GwtEvent<StarsLoadedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<StarsLoadedHandler> TYPE = new Type<StarsLoadedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param stars
     *            the Stars to include in the details
     */
    public static void fire(final EventBus eventBus, final ArrayList<StarDTO> stars) {
        eventBus.fireEvent(new StarsLoadedEvent(stars));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<StarsLoadedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final ArrayList<StarDTO> stars;

    /**
     * Constructor
     * 
     * @param stars
     *            the Stars
     */
    public StarsLoadedEvent(final ArrayList<StarDTO> stars) {
        this.stars = stars;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final StarsLoadedHandler handler) {
        handler.onStarsLoaded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<StarsLoadedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current star attached to this event.
     * 
     * @return The {@link CurrentStar} describing the currently logged in star.
     */
    public ArrayList<StarDTO> getStars() {
        return stars;
    }
}
