package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;

/**
 * This event is sent to the {@link EventBus} when the current user information
 * has changed. For example, if the user logged in or logged out.
 * 
 * @author pgirard
 */
public final class MarketAddedEvent extends GwtEvent<MarketAddedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<MarketAddedHandler> TYPE = new Type<MarketAddedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param market
     *            the Market to include in the details
     */
    public static void fire(final EventBus eventBus, final MarketDTO market) {
        eventBus.fireEvent(new MarketAddedEvent(market));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<MarketAddedHandler> getType() {
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
    public MarketAddedEvent(final MarketDTO market) {
        this.market = market;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final MarketAddedHandler handler) {
        handler.onMarketAdded(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<MarketAddedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current user attached to this event.
     * 
     * @return The {@link CurrentUser} describing the currently logged in user.
     */
    public MarketDTO getMarket() {
        return market;
    }
}
