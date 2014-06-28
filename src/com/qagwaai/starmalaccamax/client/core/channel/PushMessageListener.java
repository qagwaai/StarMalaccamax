/**
 * PushMessageListener.java
 * com.qagwaai.starmalaccamax.client.core.channel
 * StarMalaccamax
 * Created Mar 1, 2011 at 12:21:47 PM
 */
package com.qagwaai.starmalaccamax.client.core.channel;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.client.event.PushMessageReceivedEvent;
import com.qagwaai.starmalaccamax.client.event.PushMessageReceivedHandler;

/**
 * @author pgirard
 * 
 */
public final class PushMessageListener implements PushMessageReceivedHandler {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onPushMessageReceived(final PushMessageReceivedEvent event) {
        GWT.log("Message received: " + event.getMessage());

    }

}
