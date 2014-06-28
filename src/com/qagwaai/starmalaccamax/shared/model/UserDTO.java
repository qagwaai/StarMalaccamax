package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.Entity;

/**
 * 
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
@Entity
public final class UserDTO implements IsSerializable, Serializable, User {

    /**
	 * 
	 */
    @com.google.code.twig.annotation.Id
    @com.googlecode.objectify.annotation.Id
    private Long id;
    /**
	 * 
	 */
    private String email;
    /**
	 * 
	 */
    private String playerName;

    /**
	 * 
	 */
    private String appengineUniqueId;
    /**
	 * 
	 */
    private String nickname;

    /**
	 * 
	 */
    private String realName;

    /**
	 * 
	 */
    private int rating;

    /**
	 * 
	 */
    private boolean active;

    /**
	 * 
	 */
    private transient ArrayList<CaptainDTO> captains = new ArrayList<CaptainDTO>();

    /**
	 * 
	 */
    private boolean isNoob;

    /**
	 * 
	 */
    private boolean isAdmin;

    /**
	 * 
	 */
    private String channelId;

    /**
	 * 
	 */
    private String timezone;

    /**
	 * 
	 */
    private String lastLoggedIn;

    /**
	 * 
	 */
    private boolean isNPC;

    /**
	 * 
	 */
    private String profileImageBlobKey;

    private boolean hasCaptains;

    /**
	 * 
	 */
    public UserDTO() {

    }

    /**
     * 
     * @param email
     *            the users email address
     */
    public UserDTO(final String email) {
        this.email = email;
        playerName = "";
        nickname = "";
    }

    /**
     * 
     * @param user
     *            the user to hydrate this object from
     */
    public UserDTO(final UserDTO user) {
        id = user.getId();
        email = user.getEmail();
        copyEditableFields(user);
    }

    /**
     * 
     * @param user
     *            the User to copy from
     */
    private void copyEditableFields(final User user) {
        playerName = user.getRealName();
        nickname = user.getNickname();
    }

    /**
     * {@inheritDoc}
     */
    public String getAppengineUniqueId() {
        return appengineUniqueId;
    }

    /**
     * {@inheritDoc}
     */
    public ArrayList<CaptainDTO> getCaptains() {
        return captains;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getChannelId() {
        return channelId;
    }

    /**
     * {@inheritDoc}
     */
    public String getEmail() {
        return email;
    }

    /**
     * {@inheritDoc}
     */
    public Long getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * {@inheritDoc}
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getRating() {

        return rating;
    }

    /**
     * {@inheritDoc}
     */
    public String getRealName() {
        return realName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUniqueId() {
        return getEmail() + "|" + getId().toString();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean isActive() {
        return active;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isNoob() {
        return isNoob;
    }

    /**
     * @param active
     *            the active to set
     */
    public void setActive(final boolean active) {
        this.active = active;
    }

    /**
     * {@inheritDoc}
     */
    public void setAdmin(final boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * {@inheritDoc}
     */
    public void setAppengineUniqueId(final String appengineUniqueId) {
        this.appengineUniqueId = appengineUniqueId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setChannelId(final String id) {
        this.channelId = id;
    }

    /**
     * {@inheritDoc}
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * {@inheritDoc}
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    public void setNoob(final boolean isNew) {
        this.isNoob = isNew;
    }

    /**
     * {@inheritDoc}
     */
    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    /**
     * {@inheritDoc}
     */
    public void setPlayerName(final String playerName) {
        this.playerName = playerName;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setRating(final int rating) {
        this.rating = rating;

    }

    /**
     * {@inheritDoc}
     */
    public void setRealName(final String realName) {
        this.realName = realName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "UserDTO [id=" + id + ", email=" + email + ", playerName=" + playerName + ", appengineUniqueId="
            + appengineUniqueId + ", nickname=" + nickname + ", realName=" + realName + ", rating=" + rating
            + ", active=" + active + ", captains=" + captains + ", isNew=" + isNoob + ", isAdmin=" + isAdmin
            + ", channelId=" + channelId + ", getUniqueId()=" + getUniqueId() + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTimezone() {
        return timezone;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTimezone(final String timezone) {
        this.timezone = timezone;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLastLoggedin() {
        return lastLoggedIn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLastLoggedin(final String dateTime) {
        this.lastLoggedIn = dateTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNPC() {
        return isNPC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNPC(final boolean isNPC) {
        this.isNPC = isNPC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProfileImageBlobKey() {
        return profileImageBlobKey;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProfileImageBlobKey(final String key) {
        this.profileImageBlobKey = key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHasCaptains(boolean hasCaptains) {
        this.hasCaptains = hasCaptains;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasCaptains() {
        return this.hasCaptains;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCaptains(ArrayList<CaptainDTO> captains) {
        this.captains = captains;
    }

}
