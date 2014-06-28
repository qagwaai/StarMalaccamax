package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;

/**
 * This event is sent to the {@link EventBus} when the current Market
 * information has changed. For example, if the Market logged in or logged out.
 * 
 * @author pgirard
 */
public final class MarketUpdatedEvent extends GwtEvent<MarketUpdatedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<MarketUpdatedHandler> TYPE = new Type<MarketUpdatedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param market
     *            the Market to include in the details
     */
    public static void fire(final EventBus eventBus, final MarketDTO market) {
        eventBus.fireEvent(new MarketUpdatedEvent(market));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<MarketUpdatedHandler> getType() {
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
     *            the Market
     */
    public MarketUpdatedEvent(final MarketDTO market) {
        this.market = market;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final MarketUpdatedHandler handler) {
        handler.onMarketUpdated(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<MarketUpdatedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current Market attached to this event.
     * 
     * @return The {@link CurrentMarket} describing the currently logged in
     *         Market.
     */
    public MarketDTO getMarket() {
        return market;
    }
}
