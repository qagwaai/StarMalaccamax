/**
 * 
 */
package com.qagwaai.starmalaccamax.server.business.model;

import com.qagwaai.starmalaccamax.shared.model.ChannelUserId;

/**
 * @author pgirard
 * 
 */
public final class RoomUserDTO {
    /**
	 * 
	 */
    private String name;
    /**
	 * 
	 */
    private int status;
    /**
	 * 
	 */
    private ChannelUserId channelUserId;

    /**
     * @return the captain
     */

    /**
     * @return the channelUserId
     */
    public ChannelUserId getChannelUserId() {
        return channelUserId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param channelUserId
     *            the channelUserId to set
     */
    public void setChannelUserId(final ChannelUserId channelUserId) {
        this.channelUserId = channelUserId;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final int status) {
        this.status = status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RoomUser [name=" + name + ", status=" + status + ", channelUserId=" + channelUserId + "]";
    }

}
