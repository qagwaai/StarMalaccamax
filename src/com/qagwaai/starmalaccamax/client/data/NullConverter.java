/**
 * NullConverter.java
 * Created by pgirard at 2:48:24 PM on Nov 12, 2010
 * in the com.qagwaai.starmalaccamax.client.data package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import java.util.Date;

/**
 * @author pgirard
 * 
 */
public final class NullConverter {
    /**
     * 
     * @param nullable
     *            the possible null value
     * @return a non null value
     */
    public static Date toDate(final Date nullable) {
        if (nullable == null) {
            return new Date(0);
        }
        return nullable;
    }

    /**
     * 
     * @param nullable
     *            the possible null value
     * @return a non null value
     */
    public static Double toDouble(final Double nullable) {
        if (nullable == null) {
            return new Double(0);
        }
        return nullable;
    }

    /**
     * 
     * @param nullable
     *            the possible null value
     * @return a non null value
     */
    public static Integer toInt(final Integer nullable) {
        if (nullable == null) {
            return new Integer(0);
        }
        return nullable;
    }

    /**
     * 
     * @param nullable
     *            the possible null value
     * @return a non null value
     */
    public static String toString(final String nullable) {
        if (nullable == null) {
            return "";
        }
        if ("".equals(nullable)) {
            return "";
        }
        return nullable;
    }

    /**
	 * 
	 */
    private NullConverter() {

    }
}
