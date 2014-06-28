package com.qagwaai.starmalaccamax.client.core;

import com.google.gwt.user.client.rpc.AsyncCallback;
/**
 * 
 * @author ehldxae
 *
 */
public interface AsyncCallbackWatcher {
    /**
     * notification that the watcher has timed out
     */
    void timeout();

    /**
     * 
     * @param callback who to call when the watcher times out
     */
    void setCallback(@SuppressWarnings("rawtypes") AsyncCallback callback);

    /**
     * 
     * @return who to call when the watcher times out
     */
    @SuppressWarnings("rawtypes")
    AsyncCallback getCallback();

    /**
     * 
     * @return the unique identifier of the call
     */
    int getUnique();

    /**
     * 
     * @param unique the unique identifier of the call
     */
    void setUnique(int unique);

    /**
     * 
     * @return when the call started
     */
    long getStartMillis();

    /**
     * 
     * @param millis when the call started
     */
    void setStartMillis(long millis);
}
