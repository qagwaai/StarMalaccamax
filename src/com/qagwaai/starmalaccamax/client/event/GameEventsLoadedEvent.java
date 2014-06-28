package com.qagwaai.starmalaccamax.client.event;

import java.util.ArrayList;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;

/**
 * This event is sent to the {@link EventBus} when the current gameEvent
 * information has changed. For example, if the gameEvent logged in or logged
 * out.
 * 
 * @author pgirard
 */
public final class GameEventsLoadedEvent extends GwtEvent<GameEventsLoadedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<GameEventsLoadedHandler> TYPE = new Type<GameEventsLoadedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param gameEvents
     *            the gameEvents to include in the details
     */
    public static void fire(final EventBus eventBus, final ArrayList<GameEventDTO> gameEvents) {
        eventBus.fireEvent(new GameEventsLoadedEvent(gameEvents));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<GameEventsLoadedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final ArrayList<GameEventDTO> gameEvents;

    /**
     * Constructor
     * 
     * @param gameEvents
     *            the gameEvents
     */
    public GameEventsLoadedEvent(final ArrayList<GameEventDTO> gameEvents) {
        this.gameEvents = gameEvents;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final GameEventsLoadedHandler handler) {
        handler.onGameEventsLoaded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<GameEventsLoadedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current gameEvent attached to this event.
     * 
     * @return The {@link CurrentGameEvent} describing the currently logged in
     *         gameEvent.
     */
    public ArrayList<GameEventDTO> getGameEvents() {
        return gameEvents;
    }
}
