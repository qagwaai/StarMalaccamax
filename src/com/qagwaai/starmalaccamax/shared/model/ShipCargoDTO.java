/**
 * ShipCargoDTO.java
 * Created by pgirard at 12:43:35 PM on Jan 5, 2011
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class ShipCargoDTO implements IsSerializable, Serializable, ShipCargo {

    /**
	 * 
	 */
    private int amount;
    /**
	 * 
	 */
    private Date date;
    /**
	 * 
	 */
    private int purchasePrice;
    /**
	 * 
	 */
    private String commodity;

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @return the commodity
     */
    public String getCommodity() {
        return commodity;
    }

    /**
     * @return the date
     */
    public Date getPurchaseDate() {
        return date;
    }

    /**
     * @return the purchasePrice
     */
    public int getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(final int amount) {
        this.amount = amount;
    }

    /**
     * @param commodity
     *            the commodity to set
     */
    public void setCommodity(final String commodity) {
        this.commodity = commodity;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setPurchaseDate(final Date date) {
        this.date = date;
    }

    /**
     * @param purchasePrice
     *            the purchasePrice to set
     */
    public void setPurchasePrice(final int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ShipCargoDTO [amount=" + amount + ", commodity=" + commodity + ", date=" + date + ", purchasePrice="
            + purchasePrice + "]";
    }

}
