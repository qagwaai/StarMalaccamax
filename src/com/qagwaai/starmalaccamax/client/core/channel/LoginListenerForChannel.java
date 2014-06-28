/**
 * LoginListenerForChannel.java
 * com.qagwaai.starmalaccamax.client.core.channel
 * StarMalaccamax
 * Created Mar 1, 2011 at 11:47:45 AM
 */
package com.qagwaai.starmalaccamax.client.core.channel;

import com.google.gwt.appengine.channel.client.Channel;
import com.google.gwt.appengine.channel.client.ChannelFactory;
import com.google.gwt.appengine.channel.client.ChannelFactory.ChannelCreatedCallback;
import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.client.event.CurrentUserChangedEvent;
import com.qagwaai.starmalaccamax.client.event.CurrentUserChangedHandler;
import com.qagwaai.starmalaccamax.client.event.EventBus;

/**
 * @author pgirard
 * 
 */
public final class LoginListenerForChannel implements CurrentUserChangedHandler {

    /**
	 * 
	 */
    private EventBus eventBus;

    /**
     * @param eventBus
     *            where to publish events
     */
    public LoginListenerForChannel(final EventBus eventBus) {
        super();
        this.eventBus = eventBus;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onCurrentUserChanged(final CurrentUserChangedEvent event) {
        if (event.getCurrentUser().getChannelId() != null) {
            GWT.log("Creating client channel id: " + event.getCurrentUser().getChannelId());
            ChannelFactory.createChannel(event.getCurrentUser().getChannelId(), new ChannelCreatedCallback() {

                @Override
                public void onChannelCreated(final Channel channel) {
                    GWT.log("channel opened for " + event.getCurrentUser().getEmail() + " with channel id "
                        + event.getCurrentUser().getChannelId());
                    channel.open(new ChannelListener(eventBus));
                }
            });
        } else {
            GWT.log("NOT creating channel - empty channel id - for user " + event.getCurrentUser().getEmail());
        }
    }

}
