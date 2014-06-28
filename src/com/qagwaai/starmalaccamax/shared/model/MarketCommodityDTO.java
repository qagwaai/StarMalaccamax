/**
 * CommodityDTO.java
 * Created by pgirard at 3:50:03 PM on Oct 25, 2010
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
public final class MarketCommodityDTO implements MarketCommodity, IsSerializable, Serializable {
    /**
	 * 
	 */
    private int purchaseAmountWanted = 0;
    /**
	 * 
	 */
    private int purchasePrice = 0;
    /**
	 * 
	 */
    private int sellAmountAvailable = 0;
    /**
	 * 
	 */
    private int sellPrice = 0;

    /**
	 * 
	 */
    private String name;

    /**
	 * 
	 */

    public MarketCommodityDTO() {

    }

    /**
     * @param purchaseAmountWanted
     *            the amount wanted to purchase
     * @param purchasePrice
     *            the price wanted
     * @param sellAmountAvailable
     *            the amount available to sell
     * @param sellPrice
     *            the price wanted
     * @param name
     *            the name of the commodity
     */
    public MarketCommodityDTO(final int purchaseAmountWanted, final int purchasePrice, final int sellAmountAvailable,
        final int sellPrice, final String name) {
        this.purchaseAmountWanted = purchaseAmountWanted;
        this.purchasePrice = purchasePrice;
        this.sellAmountAvailable = sellAmountAvailable;
        this.sellPrice = sellPrice;
        this.name = name;
    }

    /**
     * @param name
     *            the name of the commodity
     */
    public MarketCommodityDTO(final String name) {
        this.name = name;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPurchaseAmountWanted() {
        return purchaseAmountWanted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSellAmountAvailable() {
        return sellAmountAvailable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSellPrice() {
        return sellPrice;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPurchaseAmountWanted(final int amountWanted) {
        this.purchaseAmountWanted = amountWanted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPurchasePrice(final int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSellAmountAvailable(final int sellAmountAvailable) {
        this.sellAmountAvailable = sellAmountAvailable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSellPrice(final int sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CommodityDTO [name=");
        builder.append(name);
        builder.append(", purchaseAmountWanted=");
        builder.append(purchaseAmountWanted);
        builder.append(", purchasePrice=");
        builder.append(purchasePrice);
        builder.append(", sellAmountAvailable=");
        builder.append(sellAmountAvailable);
        builder.append(", sellPrice=");
        builder.append(sellPrice);
        builder.append("]");
        return builder.toString();
    }

}
