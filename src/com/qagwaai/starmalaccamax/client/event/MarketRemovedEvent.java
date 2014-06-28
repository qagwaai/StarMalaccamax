package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;

/**
 * This event is sent to the {@link EventBus} when the current market
 * information has changed. For example, if the market logged in or logged out.
 * 
 * @author pgirard
 */
public final class MarketRemovedEvent extends GwtEvent<MarketRemovedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<MarketRemovedHandler> TYPE = new Type<MarketRemovedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param market
     *            the market to include in the details
     */
    public static void fire(final EventBus eventBus, final MarketDTO market) {
        eventBus.fireEvent(new MarketRemovedEvent(market));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<MarketRemovedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final MarketDTO market;

    /**
     * Constructor
     * 
     * @param market
     *            the market
     */
    public MarketRemovedEvent(final MarketDTO market) {
        this.market = market;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final MarketRemovedHandler handler) {
        handler.onMarketRemoved(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<MarketRemovedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current market attached to this event.
     * 
     * @return The {@link CurrentMarket} describing the currently logged in
     *         market.
     */
    public MarketDTO getMarket() {
        return market;
    }
}
