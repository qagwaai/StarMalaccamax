/**
 * ChannelManager.java
 * com.qagwaai.starmalaccamax.server.business
 * StarMalaccamax
 * Created Mar 1, 2011 at 9:48:28 AM
 */
package com.qagwaai.starmalaccamax.server.business;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.SerializationPolicy;
import com.google.gwt.user.server.rpc.SerializationPolicyLoader;
import com.qagwaai.starmalaccamax.shared.model.ChannelUserId;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.qagwaai.starmalaccamax.shared.model.channel.Message;

/**
 * @author pgirard
 * 
 */
public final class ChannelManager {
    /**
	 * 
	 */
    private static Logger log = Logger.getLogger(ChannelManager.class.getName());
    /**
	 * 
	 */
    private static final Method dummyMethod = getDummyMethod();

    /**
	 * 
	 */
    private static SerializationPolicy serializationPolicy = createPushSerializationPolicy();

    /**
     * 
     * @param user
     *            the user to create the channel for
     * @return the channel key
     */
    public static String createChannel(final UserDTO user) {
        String channelId = ChannelServiceFactory.getChannelService().createChannel(user.getUniqueId());
        log.info("Created new channel: " + channelId + " for userUniqueId [" + user.getUniqueId() + "]");
        return channelId;
    }

    /**
     * Creates a new SerializationPolicy for push RPC.
     * 
     * @return the merged serialization policy
     */
    private static SerializationPolicy createPushSerializationPolicy() {

        File[] files = new File("starmalaccamax").listFiles(new FilenameFilter() {
            public boolean accept(final File dir, final String name) {
                System.out.println("trying [" + name + "] in serialization policy: " + name.endsWith(".gwt.rpc"));
                return name.endsWith(".gwt.rpc");
            }
        });

        List<SerializationPolicy> policies = new ArrayList<SerializationPolicy>();

        for (File f : files) {
            try {
                System.out.println("Including " + f.getAbsolutePath() + " in serialization policy");
                BufferedInputStream input = new BufferedInputStream(new FileInputStream(f));
                policies.add(SerializationPolicyLoader.loadFromStream(input, null));
            } catch (Exception e) {
                throw new RuntimeException("Unable to load a policy file: " + f.getAbsolutePath());
            }
        }

        return new MergedSerializationPolicy(policies);
    }

    /**
     * 
     * @param msg
     *            the message to encode
     * @return the string to send over the wire
     */
    private static String encodeMessage(final Message msg) {
        try {
            return RPC.encodeResponseForSuccess(dummyMethod, msg, serializationPolicy);
        } catch (SerializationException e) {
            throw new RuntimeException("Unable to encode a message for push.\n" + msg, e);
        }
    }

    /**
     * 
     * @return a dummy method
     */
    private static Method getDummyMethod() {
        try {
            return ChannelManager.class.getDeclaredMethod("dummyMethod");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unable to find the dummy RPC method.");
        }
    }

    /**
     * 
     * @param toUsers
     *            the users to push to
     * @param message
     *            the message to push
     */
    public static void pushMessage(final ArrayList<ChannelUserId> toUsers, final Message message) {
        String encodedMessage = encodeMessage(message);
        for (CharSequence userUniqueId : toUsers) {
            try {
                log.info("Trying to send message to " + userUniqueId + ": [" + message + "]");
                ChannelServiceFactory.getChannelService().sendMessage(
                    new ChannelMessage(userUniqueId.toString(), encodedMessage));
            } catch (Exception e) {
                log.log(Level.SEVERE, "Failed to push the message " + message + " to client " + userUniqueId, e);
            }
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
    @SuppressWarnings("unused")
    private Message dummyMethod() {
        throw new UnsupportedOperationException("This should never be called.");
    }

    /**
     * 
     * @author pgirard
     * 
     */
    private static class MergedSerializationPolicy extends SerializationPolicy {
        /**
		 * 
		 */
        List<SerializationPolicy> policies;

        /**
         * 
         * @param policies
         *            the policies to add
         */
        MergedSerializationPolicy(final List<SerializationPolicy> policies) {
            this.policies = policies;

            for (SerializationPolicy policy : policies) {
                System.out.println("MergedPolicy: " + policy);
            }

        }

        @Override
        public boolean shouldDeserializeFields(final Class<?> clazz) {
            for (SerializationPolicy p : policies) {
                if (p.shouldDeserializeFields(clazz)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 
         * {@inheritDoc}
         */
        @Override
        public boolean shouldSerializeFields(final Class<?> clazz) {
            for (SerializationPolicy p : policies) {
                if (p.shouldSerializeFields(clazz)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 
         * {@inheritDoc}
         */
        @Override
        public void validateDeserialize(final Class<?> clazz) throws SerializationException {
            SerializationException se = null;
            for (SerializationPolicy p : policies) {
                try {
                    p.validateDeserialize(clazz);
                    return;
                } catch (SerializationException e) {
                    se = e;
                }
            }
            throw se;
        }

        /**
         * 
         * {@inheritDoc}
         */
        @Override
        public void validateSerialize(final Class<?> clazz) throws SerializationException {
            SerializationException se = null;
            for (SerializationPolicy p : policies) {
                try {
                    p.validateSerialize(clazz);
                    return;
                } catch (SerializationException e) {
                    se = e;
                }
            }
            throw se;
        }
    }

}
