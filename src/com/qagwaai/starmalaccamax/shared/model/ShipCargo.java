/**
 * ShipCargo.java
 * Created by pgirard at 12:41:28 PM on Jan 5, 2011
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.util.Date;

/**
 * @author pgirard
 * 
 */
public interface ShipCargo extends Model {
    /**
     * 
     * @return the amount of the cargo in the ship
     */
    int getAmount();

    /**
     * 
     * @return the name of the commodity
     */
    String getCommodity();

    /**
     * 
     * @return when the cargo was purchased
     */
    Date getPurchaseDate();

    /**
     * 
     * @return how much was paid for the cargo
     */
    int getPurchasePrice();

    /**
     * 
     * @param amount
     *            the amount of the cargo in the ship
     */
    void setAmount(int amount);

    /**
     * 
     * @param commodity
     *            the name of the commodity
     */
    void setCommodity(String commodity);

    /**
     * 
     * @param date
     *            when the cargo was purchased
     */
    void setPurchaseDate(Date date);

    /**
     * 
     * @param price
     *            how much was paid for the cargo
     */
    void setPurchasePrice(int price);
}
