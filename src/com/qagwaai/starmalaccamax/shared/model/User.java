package com.qagwaai.starmalaccamax.shared.model;

import java.util.ArrayList;

/**
 * 
 * @author qagwaai
 * 
 */
/**
 * @author pgirard
 * 
 */
public interface User extends Model {

    /**
     * @return the users app engine unique id
     */
    String getAppengineUniqueId();

    /**
     * @return a list of captains
     */
    ArrayList<CaptainDTO> getCaptains();

    /**
     * 
     * @return the channel identifier used for push messages
     */
    String getChannelId();

    /**
     * @return the users email address
     */
    String getEmail();

    /**
     * @return the unique identifier
     */
    Long getId();

    /**
     * @return the users nickname
     */
    String getNickname();

    /**
     * @return the users player name
     */
    String getPlayerName();

    /**
     * 
     * @return the users rating
     */
    int getRating();

    /**
     * @return the users real name
     */
    String getRealName();

    /**
     * 
     * @return the unique id
     */
    String getUniqueId();

    /**
     * 
     * @return preferred timezone
     */
    String getTimezone();

    /**
     * 
     * @return last logged in
     */
    String getLastLoggedin();

    /**
     * 
     * @return true if the user entry is a non-player character
     */
    boolean isNPC();

    /**
     * 
     * @return true if the user is able to play
     */
    boolean isActive();

    /**
     * @return if the user is an admin
     */
    boolean isAdmin();

    /**
     * @return if this is the first time the user has loggedin
     */
    boolean isNoob();

    /**
     * 
     * @param active
     *            true if the user can play
     */
    void setActive(boolean active);

    /**
     * @param isAdmin
     *            if the user is an admin
     */
    void setAdmin(boolean isAdmin);

    /**
     * @param appengineUniqueId
     *            the users app engine unique id
     */
    void setAppengineUniqueId(String appengineUniqueId);

    /**
     * 
     * @param id
     *            the channel identifier used for push messages
     */
    void setChannelId(String id);

    /**
     * @param email
     *            the users email address
     */
    void setEmail(final String email);

    /**
     * @param id
     *            the unique identifier
     */
    void setId(final Long id);

    /**
     * @param isNoob
     *            if this is the first time the user has loggedin
     */
    void setNoob(boolean isNoob);

    /**
     * @param nickname
     *            the users nickname
     */
    void setNickname(final String nickname);

    /**
     * @param playerName
     *            the users player name
     */
    void setPlayerName(String playerName);

    /**
     * 
     * @param rating
     *            the users rating
     */
    void setRating(int rating);

    /**
     * @param realName
     *            the users real name
     */
    void setRealName(String realName);

    /**
     * 
     * @param timezone
     *            the preferred timezone
     */
    void setTimezone(String timezone);

    /**
     * 
     * @param dateTime
     *            last logged in
     */
    void setLastLoggedin(String dateTime);

    /**
     * 
     * @param isNPC
     *            true if the user entry is a non-player character
     */
    void setNPC(boolean isNPC);

    /**
     * 
     * @return the key into the blobstore for a profile picture
     */
    String getProfileImageBlobKey();

    /**
     * 
     * @param key
     *            the key into the blobstore for a profile picture
     */
    void setProfileImageBlobKey(String key);

    void setHasCaptains(boolean hasCaptains);

    boolean hasCaptains();

    void setCaptains(ArrayList<CaptainDTO> captains);
}