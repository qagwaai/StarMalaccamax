/**
 * SimpleFilterItem.java
 * Created by pgirard at 10:10:37 AM on Oct 5, 2010
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
public final class SimpleFilterItem implements IsSerializable, Serializable, Model, Filter {
    /**
	 * 
	 */
    private String field;
    /**
	 * 
	 */
    private String value;

    /**
	 * 
	 */
    public SimpleFilterItem() {

    }

    /**
     * @param field
     *            the field name to filter on
     * @param value
     *            the value to filter on using equals
     */
    public SimpleFilterItem(final String field, final String value) {
        this.field = field;
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SimpleFilterItem other = (SimpleFilterItem) obj;
        if (field == null) {
            if (other.field != null) {
                return false;
            }
        } else if (!field.equals(other.field)) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }

    /**
     * @return the field
     */
    public String getField() {
        return field;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((field == null) ? 0 : field.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    /**
     * @param field
     *            the field to set
     */
    public void setField(final String field) {
        this.field = field;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "SimpleFilterItem [field=" + field + ", value=" + value + "]";
    }

}
