/**
 * Commodity.java
 * Created by pgirard at 3:31:22 PM on Oct 25, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

/**
 * @author pgirard
 * 
 */
public interface MarketCommodity extends Model {

    /**
     * 
     * @return the name of the commodity
     */
    String getName();

    /**
     * 
     * @return the amount that this market wants to purchase
     */
    int getPurchaseAmountWanted();

    /**
     * 
     * @return the price at which the market will purchase this commodity
     */
    int getPurchasePrice();

    /**
     * 
     * @return the amount of product available for sale
     */
    int getSellAmountAvailable();

    /**
     * 
     * @return the price at which the market will sell this commodity
     */
    int getSellPrice();

    /**
     * 
     * @param name
     *            the name of the commodity
     */
    void setName(String name);

    /**
     * 
     * @param amountWanted
     *            the amount that this market wants to purchase
     */
    void setPurchaseAmountWanted(int amountWanted);

    /**
     * 
     * @param purchasePrice
     *            the price at which the market will purchase this commodity
     */
    void setPurchasePrice(int purchasePrice);

    /**
     * 
     * @param sellAmountAvailable
     *            the amount of product available for sale
     */
    void setSellAmountAvailable(int sellAmountAvailable);

    /**
     * 
     * @param sellPrice
     *            the price at which the market will sell this commodity
     */
    void setSellPrice(int sellPrice);
}
