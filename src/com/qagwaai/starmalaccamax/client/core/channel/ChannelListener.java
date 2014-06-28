/**
 * ChannelListener.java
 * com.qagwaai.starmalaccamax.client.core.channel
 * StarMalaccamax
 * Created Mar 1, 2011 at 11:48:10 AM
 */
package com.qagwaai.starmalaccamax.client.core.channel;

import com.google.gwt.appengine.channel.client.SocketError;
import com.google.gwt.appengine.channel.client.SocketListener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamFactory;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.event.PushMessageReceivedEvent;
import com.qagwaai.starmalaccamax.shared.model.channel.Message;

/**
 * @author pgirard
 * 
 */
public final class ChannelListener implements SocketListener {
    /**
	 * 
	 */
    private SerializationStreamFactory pushServiceStreamFactory;
    /**
	 * 
	 */
    private EventBus eventBus;

    /**
     * 
     * @param eventBus
     *            where to publish events
     */
    public ChannelListener(final EventBus eventBus) {
        super();
        pushServiceStreamFactory = (SerializationStreamFactory) PushService.App.getInstance();
        this.eventBus = eventBus;
    }

    /**
     * @see com.google.gwt.appengine.channel.client.SocketListener#onClose()
     */
    @Override
    public void onClose() {
        Window.alert("Channel closed");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onError(final SocketError error) {
        Window.alert("Channel error: " + error.getDescription());

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onMessage(final String encodedData) {
        try {
            SerializationStreamReader reader = pushServiceStreamFactory.createStreamReader(encodedData);
            Message message = (Message) reader.readObject();
            PushMessageReceivedEvent.fire(eventBus, message);
        } catch (SerializationException e) {
            throw new RuntimeException("Unable to deserialize " + encodedData, e);
        }

    }

    /**
     * @see com.google.gwt.appengine.channel.client.SocketListener#onOpen()
     */
    @Override
    public void onOpen() {
        GWT.log("Channel Listener opened");

    }

}
