/**
 * 
 */
package com.qagwaai.starmalaccamax.shared.model;

/**
 * @author pgirard
 * 
 */
public interface ChatRoom {
    /**
     * 
     * @return the unique id of the chat room
     */
    String getId();

    /**
     * 
     * @return the name of the chat room
     */
    String getName();

    /**
     * 
     * @return the type of the chat room
     */
    String getType();

    /**
     * 
     * @param id
     *            the unique id of the chat room
     */
    void setId(String id);

    /**
     * 
     * @param name
     *            the name of the chat room
     */
    void setName(String name);

    /**
     * 
     * @param type
     *            the type of the chat room
     */
    void setType(String type);
}
