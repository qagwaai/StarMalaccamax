/**
 * UserRecord.java
 * Created by pgirard at 10:29:55 AM on Aug 20, 2010
 * in the com.qagwaai.starmalaccamax.shared.model.record package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.User;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class UserRecord extends ListGridRecord implements User {

    /**
	 * 
	 */
    public static final String APPENGINEUNIQUEID = "appEngineUniqueId";
    /**
	 * 
	 */
    public static final String EMAIL = "email";
    /**
	 * 
	 */
    public static final String ID = "id";
    /**
	 * 
	 */
    public static final String NOOB = "noob";
    /**
	 * 
	 */
    public static final String NICKNAME = "nickname";
    /**
	 * 
	 */
    public static final String PLAYERNAME = "playername";
    /**
	 * 
	 */
    public static final String REALNAME = "realname";
    /**
	 * 
	 */
    public static final String ADMIN = "admin";

    /**
	 * 
	 */
    public static final String RATING = "rating";

    /**
	 * 
	 */
    public static final String ACTIVE = "active";

    /**
	 * 
	 */
    public static final String CHANNEL_ID = "channelId";

    /**
	 * 
	 */
    public static final String TIMEZONE = "timezone";

    /**
	 * 
	 */
    public static final String LAST_LOGGED_IN = "lastLoggedIn";

    /**
	 * 
	 */
    public static final String ISNPC = "isNPC";

    /**
	 * 
	 */
    public static final String PROFILE_IMAGE_BLOB_KEY = "profileImageBlobKey";

    public static final String HAS_CAPTAINS = "hasCaptains";

    /**
	 * 
	 */
    public UserRecord() {

    }

    /**
     * 
     * @param user
     *            the user DTO
     */
    public UserRecord(final UserDTO user) {
        setAdmin(user.isAdmin());
        setNoob(user.isNoob());
        setAppengineUniqueId(user.getAppengineUniqueId());
        setEmail(user.getEmail());
        setId(user.getId());
        setNickname(user.getNickname());
        setPlayerName(user.getPlayerName());
        setRealName(user.getRealName());
        setRating(user.getRating());
        setTimezone(user.getTimezone());
        setLastLoggedin(user.getLastLoggedin());
        setNPC(user.isNPC());
        setProfileImageBlobKey(user.getProfileImageBlobKey());
        setHasCaptains(user.hasCaptains());
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getAppengineUniqueId() {
        return getAttributeAsString(APPENGINEUNIQUEID);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<CaptainDTO> getCaptains() {
        // TODO return the captains associated with this user
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getChannelId() {
        return getAttributeAsString(CHANNEL_ID);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getEmail() {
        return getAttributeAsString(EMAIL);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        try {
            return LongConverter.fromJavaScript(this, UserRecord.ID);
        } catch (DataException e) {
            GWT.log("Identity field could not be converted to Long", e);
        }
        return -1L;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getNickname() {
        return getAttributeAsString(NICKNAME);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getPlayerName() {
        return getAttributeAsString(PLAYERNAME);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getRating() {
        return getAttributeAsInt(RATING);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getRealName() {
        return getAttributeAsString(REALNAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUniqueId() {
        return getAttributeAsString(EMAIL) + "|" + getId().toString();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean isActive() {

        return getAttributeAsBoolean(ACTIVE);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean isAdmin() {
        return getAttributeAsBoolean(ADMIN);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean isNoob() {
        return getAttributeAsBoolean(NOOB);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setActive(final boolean active) {
        setAttribute(ACTIVE, active);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setAdmin(final boolean isAdmin) {
        setAttribute(ADMIN, isAdmin);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setAppengineUniqueId(final String appengineUniqueId) {
        setAttribute(APPENGINEUNIQUEID, appengineUniqueId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setChannelId(final String id) {
        setAttribute(CHANNEL_ID, id);

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setEmail(final String email) {
        setAttribute(EMAIL, email);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setId(final Long id) {
        setAttribute(ID, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setNoob(final boolean isNew) {
        setAttribute(NOOB, isNew);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setNickname(final String nickname) {
        setAttribute(NICKNAME, nickname);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setPlayerName(final String playerName) {
        setAttribute(PLAYERNAME, playerName);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setRating(final int rating) {
        setAttribute(RATING, rating);

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setRealName(final String realName) {
        setAttribute(REALNAME, realName);
    }

    /**
     * 
     * @return a user object from this record
     */
    public UserDTO toUser() {
        UserDTO user = new UserDTO();
        Long id = this.getId();
        user.setId(id);
        user.setEmail(this.getEmail());
        user.setRealName(this.getRealName());
        user.setNickname(this.getNickname());
        user.setPlayerName(this.getPlayerName());
        user.setAppengineUniqueId(this.getAppengineUniqueId());
        user.setNoob(this.isNoob());
        user.setAdmin(this.isAdmin());
        user.setRating(this.getRating());
        user.setTimezone(this.getTimezone());
        user.setLastLoggedin(this.getLastLoggedin());
        user.setNPC(this.isNPC());
        user.setProfileImageBlobKey(this.getProfileImageBlobKey());
        user.setHasCaptains(this.hasCaptains());
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTimezone() {
        return getAttribute(TIMEZONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLastLoggedin() {
        return getAttribute(LAST_LOGGED_IN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTimezone(final String timezone) {
        setAttribute(TIMEZONE, timezone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLastLoggedin(final String dateTime) {
        setAttribute(LAST_LOGGED_IN, dateTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNPC() {
        return getAttributeAsBoolean(ISNPC);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNPC(final boolean isNPC) {
        setAttribute(ISNPC, isNPC);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProfileImageBlobKey() {
        return getAttribute(PROFILE_IMAGE_BLOB_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProfileImageBlobKey(final String key) {
        setAttribute(PROFILE_IMAGE_BLOB_KEY, key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHasCaptains(boolean hasCaptains) {
        setAttribute(HAS_CAPTAINS, hasCaptains);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasCaptains() {

        return getAttributeAsBoolean(HAS_CAPTAINS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCaptains(ArrayList<CaptainDTO> captains) {
        // TODO set the captains for this user

    }

}
