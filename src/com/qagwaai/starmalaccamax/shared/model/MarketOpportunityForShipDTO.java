/**
 * MarketDistanceDTO.java
 * Created by pgirard at 11:00:51 AM on Dec 17, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class MarketOpportunityForShipDTO implements IsSerializable, Serializable {
    /**
	 * 
	 */
    private ShipDTO ship;
    /**
	 * 
	 */
    private MarketDTO market;
    /**
	 * 
	 */
    private DistanceDTO distance;

    /**
	 * 
	 */
    private String commodity;

    /**
	 * 
	 */
    private int salePrice;

    /**
	 * 
	 */
    private int amountAvailable;

    /**
	 * 
	 */
    private ShipCargoDTO cargo;

    /**
	 * 
	 */
    private String marker;

    /**
	 * 
	 */
    private PlanetDTO planet;

    /**
	 * 
	 */
    public MarketOpportunityForShipDTO() {

    }

    /**
     * @param ship
     *            the ship for this opportunity
     * @param market
     *            the market for this opportunity
     * @param distance
     *            the distance from the ship
     */
    public MarketOpportunityForShipDTO(final ShipDTO ship, final MarketDTO market, final DistanceDTO distance) {
        this.ship = ship;
        this.market = market;
        this.distance = distance;
    }

    /**
     * @return the amountAvailable
     */
    public int getAmountAvailable() {
        return amountAvailable;
    }

    /**
     * @return the cargo
     */
    public ShipCargoDTO getCargo() {
        return cargo;
    }

    /**
     * @return the commodity
     */
    public String getCommodity() {
        return commodity;
    }

    /**
     * @return the distance
     */
    public DistanceDTO getDistance() {
        return distance;
    }

    /**
     * @return the marker
     */
    public String getMarker() {
        return marker;
    }

    /**
     * @return the market
     */
    public MarketDTO getMarket() {
        return market;
    }

    /**
     * @return the planet
     */
    public PlanetDTO getPlanet() {
        return planet;
    }

    /**
     * @return the salePrice
     */
    public int getSalePrice() {
        return salePrice;
    }

    /**
     * @return the ship
     */
    public ShipDTO getShip() {
        return ship;
    }

    /**
     * @param amountAvailable
     *            the amountAvailable to set
     */
    public void setAmountAvailable(final int amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    /**
     * @param cargo
     *            the cargo to set
     */
    public void setCargo(final ShipCargoDTO cargo) {
        this.cargo = cargo;
    }

    /**
     * @param commodity
     *            the commodity to set
     */
    public void setCommodity(final String commodity) {
        this.commodity = commodity;
    }

    /**
     * @param distance
     *            the distance to set
     */
    public void setDistance(final DistanceDTO distance) {
        this.distance = distance;
    }

    /**
     * @param marker
     *            the marker to set
     */
    public void setMarker(final String marker) {
        this.marker = marker;
    }

    /**
     * @param market
     *            the market to set
     */
    public void setMarket(final MarketDTO market) {
        this.market = market;
    }

    /**
     * @param planet
     *            the planet to set
     */
    public void setPlanet(final PlanetDTO planet) {
        this.planet = planet;
    }

    /**
     * @param salePrice
     *            the salePrice to set
     */
    public void setSalePrice(final int salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * @param ship
     *            the ship to set
     */
    public void setShip(final ShipDTO ship) {
        this.ship = ship;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "MarketOpportunityForShipDTO [ship=" + ship + ", market=" + market + ", distance=" + distance
            + ", commodity=" + commodity + ", salePrice=" + salePrice + ", amountAvailable=" + amountAvailable
            + ", cargo=" + cargo + ", marker=" + marker + ", planet=" + planet + "]";
    }

}
