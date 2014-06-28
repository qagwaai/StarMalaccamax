package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;

/**
 * This event is sent to the {@link EventBus} when the current gameEvent
 * information has changed. For example, if the gameEvent logged in or logged
 * out.
 * 
 * @author pgirard
 */
public final class GameEventUpdatedEvent extends GwtEvent<GameEventUpdatedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<GameEventUpdatedHandler> TYPE = new Type<GameEventUpdatedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param gameEvent
     *            the gameEvent to include in the details
     */
    public static void fire(final EventBus eventBus, final GameEventDTO gameEvent) {
        eventBus.fireEvent(new GameEventUpdatedEvent(gameEvent));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<GameEventUpdatedHandler> getType() {
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
     *            the gameEvent
     */
    public GameEventUpdatedEvent(final GameEventDTO gameEvent) {
        this.gameEvent = gameEvent;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final GameEventUpdatedHandler handler) {
        handler.onGameEventUpdated(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<GameEventUpdatedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current gameEvent attached to this event.
     * 
     * @return The {@link CurrentGameEvent} describing the currently logged in
     *         gameEvent.
     */
    public GameEventDTO getGameEvent() {
        return gameEvent;
    }
}
