/**
 * ChannelUserId.java
 * com.qagwaai.starmalaccamax.shared.model
 * StarMalaccamax
 * Created Mar 1, 2011 at 10:38:47 AM
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class ChannelUserId implements IsSerializable, Serializable, CharSequence {

    /**
	 * 
	 */
    private String buffer;

    /**
	 * 
	 */
    public ChannelUserId() {
        super();
    }

    /**
     * @param buffer
     *            create from a previously created string
     */
    public ChannelUserId(final String buffer) {
        super();
        this.buffer = buffer;
    }

    /**
     * @see java.lang.CharSequence#charAt(int)
     * @param index
     *            the position
     * @return a character
     */
    @Override
    public char charAt(final int index) {
        return buffer.charAt(index);
    }

    /**
     * @see java.lang.CharSequence#length()
     * @return the length of the string
     */
    @Override
    public int length() {
        return buffer.length();
    }

    /**
     * @see java.lang.CharSequence#subSequence(int, int)
     * @param start
     *            where to start for the substring
     * @param end
     *            where to end for the substring
     * @return the substring
     */
    @Override
    public CharSequence subSequence(final int start, final int end) {
        return buffer.subSequence(start, end);
    }

    /**
     * @see java.lang.Object#toString()
     * @return the string
     */
    @Override
    public String toString() {
        return buffer;
    }

}
