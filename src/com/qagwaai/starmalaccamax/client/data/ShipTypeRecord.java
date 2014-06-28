/**
 * UserRecord.java
 * Created by pgirard at 10:29:55 AM on Aug 20, 2010
 * in the com.qagwaai.starmalaccamax.shared.model.record package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.shared.model.ShipType;
import com.qagwaai.starmalaccamax.shared.model.ShipTypeDTO;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class ShipTypeRecord extends ListGridRecord implements ShipType {

    /**
	 * 
	 */
    public static final String ID = "id";
    /**
	 * 
	 */
    public static final String NAME = "name";

    /**
	 * 
	 */
    public ShipTypeRecord() {

    }

    /**
     * 
     * @param shipType
     *            the shipType DTO
     */
    public ShipTypeRecord(final ShipTypeDTO shipType) {
        setId(shipType.getId());
        setName(shipType.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        try {
            return LongConverter.fromJavaScript(this, ShipTypeRecord.ID);
        } catch (DataException e) {
            GWT.log("Identity field could not be converted to Long", e);
        }
        return -1L;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return getAttributeAsString(ShipTypeRecord.NAME);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setId(final Long id) {
        setAttribute(ShipTypeRecord.ID, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setName(final String name) {
        setAttribute(ShipTypeRecord.NAME, name);
    }

    /**
     * 
     * @return a solar system object from this record
     */
    public ShipTypeDTO toShipType() {
        ShipTypeDTO p = new ShipTypeDTO();
        p.setId(getId());
        p.setName(getName());
        return p;
    }

}
