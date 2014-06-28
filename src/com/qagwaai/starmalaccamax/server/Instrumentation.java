/**
 * Instrumentation.java
 * Created by pgirard at 1:24:19 PM on Jan 25, 2011
 * in the com.qagwaai.starmalaccamax.server package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server;

import com.vladium.utils.IObjectProfileNode;
import com.vladium.utils.ObjectProfiler;

/**
 * @author pgirard
 * 
 */
public final class Instrumentation {
    // private static final Method dummyMethod = getDummyMethod();

    /**
     * 
     * @param message the message to log
     * @param start the time in milliseconds to start the operation
     * @param actionName the name of the action
     * @param response any response
     */
    public static void callEnd(final String message, final long start, final String actionName, final Object response) {
        long current = System.currentTimeMillis();
        System.out.println("Service Call End\t(" + current + "[" + (current - start) + "]){" + sizeOf(response) + "}<"
            + actionName + ">:" + message);
    }

    /**
     * 
     * @param message the message to post
     * @return the call start time
     */
    public static long callStart(final String message) {
        long current = System.currentTimeMillis();
        System.out.println("Service Call Start\t(" + current + "): " + message);
        return current;
    }

    /**
     * 
     * @param obj
     *            the object to calculate the size of
     * @return the size
     */
    private static int sizeOf(final Object obj) {
        if (obj == null) {
            return 0;
        }

        /*
         * String response = null; try { response =
         * RPC.encodeResponseForSuccess(dummyMethod, obj); } catch
         * (SerializationException e) { return 0; } if (response != null) {
         * return response.length(); } return 0;
         */

        /*
         * try { return Sizeof.sizeof(obj); } catch (Exception e) { return 0; }
         */

        try {
            IObjectProfileNode profile = ObjectProfiler.profile(obj);
            return profile.size();
        } catch (Throwable t) {
            return 0;
        }
    }

    /**
     * This method exists to make GWT RPC happy.
     * <p>
     * {@link RPC#encodeResponseForSuccess(java.lang.reflect.Method, Object)} insists that we pass it a Method that has
     * a return type equal to the object we're encoding. What we really want to use is
     * {@link RPC#encodeResponse(Class, Object, boolean, int, com.google.gwt.user.server.rpc.SerializationPolicy)} , but
     * it is unfortunately private.
     * 
     * @return dummy message
     */
    /*
     * @SuppressWarnings("unused") private Response dummyMethod() { throw new
     * UnsupportedOperationException("This should never be called."); }
     */

    /*
     * private static Method getDummyMethod() { try { return
     * Instrumentation.class.getDeclaredMethod("dummyMethod"); } catch
     * (NoSuchMethodException e) { throw new
     * RuntimeException("Unable to find the dummy RPC method."); } }
     */

    /**
	 * 
	 */
    private Instrumentation() {

    }
}
