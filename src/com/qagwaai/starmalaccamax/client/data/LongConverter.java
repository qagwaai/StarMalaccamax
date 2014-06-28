/**
 * LongConverter.java
 * Created by pgirard at 2:00:22 PM on Nov 5, 2010
 * in the com.qagwaai.starmalaccamax.client.data package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class LongConverter {
    /**
     * 
     * @param record
     *            the record that contains the Long
     * @param attributeName
     *            the attribute name of the Long
     * @return a Long
     * @throws DataException
     *             if the conversion failed
     */
    public static Long fromJavaScript(final ListGridRecord record, final String attributeName) throws DataException {
        if (record.getAttribute(attributeName) != null) {
            Object objId = null;
            try {
                objId = record.getAttributeAsObject(attributeName);
                if (objId instanceof Long) {
                    return (Long) objId;
		} else if (objId instanceof Integer) {
		    return Long.valueOf((Integer) objId);
                }
            } catch (Throwable t) {
                // object id is not of type Long
            }
            if (objId == null) {
                try {
                    objId = record.getAttributeAsInt(attributeName);
                    if (objId instanceof Integer) {
                        return Long.valueOf((Integer) objId);
                    }
                } catch (Throwable t) {
                    // exit
                }
            }
        } else {
            // in an add operation
            return null;
        }
        throw new DataException("Could not convert id from javascript object");
    }

    /**
	 * 
	 */
    private LongConverter() {

    }
}
