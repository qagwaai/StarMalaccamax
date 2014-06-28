/**
 * ClientUtil.java
 * Created by pgirard at 1:47:07 PM on Jan 28, 2011
 * in the com.qagwaai.starmalaccamax.client.core.util package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.core.util;

import com.google.gwt.core.client.GWT;

/**
 * @author pgirard
 * 
 */
public final class ClientUtil {
    /**
     * 
     * @return the browser user agent property
     */
    public static native String getUserAgent() /*-{
                                               return navigator.userAgent.toLowerCase();
                                               }-*/;

    /**
	 * 
	 */
    private ClientUtil() {
    }

    public static void serviceCallStart(String message) {
        GWT.log("Service Call Start\t(" + System.currentTimeMillis() + "): " + message);
    }

    public static void serviceCallEnd(String message) {
        GWT.log("Service Call End\t(" + System.currentTimeMillis() + "):" + message);
    }
}
