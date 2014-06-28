package com.qagwaai.starmalaccamax.client.event;

import java.util.ArrayList;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;

/**
 * This event is sent to the {@link EventBus} when the current market
 * information has changed. For example, if the market logged in or logged out.
 * 
 * @author pgirard
 */
public final class MarketsLoadedEvent extends GwtEvent<MarketsLoadedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<MarketsLoadedHandler> TYPE = new Type<MarketsLoadedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param markets
     *            the market to include in the details
     */
    public static void fire(final EventBus eventBus, final ArrayList<MarketDTO> markets) {
        eventBus.fireEvent(new MarketsLoadedEvent(markets));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<MarketsLoadedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final ArrayList<MarketDTO> markets;

    /**
     * Constructor
     * 
     * @param markets
     *            the markets
     */
    public MarketsLoadedEvent(final ArrayList<MarketDTO> markets) {
        this.markets = markets;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final MarketsLoadedHandler handler) {
        handler.onMarketsLoaded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<MarketsLoadedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current market attached to this event.
     * 
     * @return The {@link CurrentMarket} describing the currently logged in
     *         market.
     */
    public ArrayList<MarketDTO> getMarkets() {
        return markets;
    }
}
