package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;

/**
 * This event is sent to the {@link EventBus} when the current user information
 * has changed. For example, if the user logged in or logged out.
 * 
 * @author pgirard
 */
public final class GameEventAddedEvent extends GwtEvent<GameEventAddedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<GameEventAddedHandler> TYPE = new Type<GameEventAddedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param gameEvent
     *            the GameEvent to include in the details
     */
    public static void fire(final EventBus eventBus, final GameEventDTO gameEvent) {
        eventBus.fireEvent(new GameEventAddedEvent(gameEvent));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<GameEventAddedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final GameEventDTO gameEvent;

    /**
     * Constructor
     * 
     * @param gameEvent
     *            the GameEvent
     */
    public GameEventAddedEvent(final GameEventDTO gameEvent) {
        this.gameEvent = gameEvent;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final GameEventAddedHandler handler) {
        handler.onGameEventAdded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<GameEventAddedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current user attached to this event.
     * 
     * @return The {@link CurrentUser} describing the currently logged in user.
     */
    public GameEventDTO getGameEvent() {
        return gameEvent;
    }
}
