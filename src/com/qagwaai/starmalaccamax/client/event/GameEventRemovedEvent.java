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
public final class GameEventRemovedEvent extends GwtEvent<GameEventRemovedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<GameEventRemovedHandler> TYPE = new Type<GameEventRemovedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param gameEvent
     *            the gameEvent to include in the details
     */
    public static void fire(final EventBus eventBus, final GameEventDTO gameEvent) {
        eventBus.fireEvent(new GameEventRemovedEvent(gameEvent));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<GameEventRemovedHandler> getType() {
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
    public GameEventRemovedEvent(final GameEventDTO gameEvent) {
        this.gameEvent = gameEvent;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final GameEventRemovedHandler handler) {
        handler.onGameEventRemoved(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<GameEventRemovedHandler> getAssociatedType() {
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
