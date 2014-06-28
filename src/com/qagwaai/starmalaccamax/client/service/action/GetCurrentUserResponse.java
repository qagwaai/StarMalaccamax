/**
 * GetCurrentUserResponse.java
 * Created by pgirard at 3:57:56 PM on Jul 22, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.actions package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public final class GetCurrentUserResponse extends AbstractResponse implements IsSerializable {
    /**
     * the user captured during the command execution null if not signed in
     */
    private UserDTO user;

    /**
     * the google appworks login url generated from the user service
     */
    private String loginUrl;

    /**
     * the google appworks logout url generated from the user service
     */
    private String logoutUrl;

    /**
     * @return the loginUrl
     */
    public String getLoginUrl() {
        return loginUrl;
    }

    /**
     * @return the logoutUrl
     */
    public String getLogoutUrl() {
        return logoutUrl;
    }

    /**
     * @return the user
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * @param loginUrl
     *            the loginUrl to set
     */
    public void setLoginUrl(final String loginUrl) {
        this.loginUrl = loginUrl;
    }

    /**
     * @param logoutUrl
     *            the logoutUrl to set
     */
    public void setLogoutUrl(final String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(final UserDTO user) {
        this.user = user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetCurrentUserResponse [user=" + user + ", loginUrl=" + loginUrl + ", logoutUrl=" + logoutUrl + "]";
    }

}
