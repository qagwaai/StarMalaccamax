/**
 * Copyright 2010 Philippe Beaudoin
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * This event is sent to the {@link EventBus} when the current user information
 * has changed. For example, if the user logged in or logged out.
 * 
 * @author Philippe Beaudoin
 * @author pgirard
 */
public final class CurrentUserChangedEvent extends GwtEvent<CurrentUserChangedHandler> {

    /**
     * this class's type stored for the registration
     */
    private static final Type<CurrentUserChangedHandler> TYPE = new Type<CurrentUserChangedHandler>();

    /**
     * fire this event
     * 
     * @param eventBus
     *            the bus to fire on
     * @param currentUser
     *            the user to include in the details
     * @param loginUrl
     *            the url to login to
     * @param logoutUrl
     *            the url to logout to
     */
    public static void fire(final EventBus eventBus, final UserDTO currentUser, final String loginUrl,
        final String logoutUrl) {
        eventBus.fireEvent(new CurrentUserChangedEvent(currentUser, loginUrl, logoutUrl));
    }

    /**
     * 
     * @return this event's type
     */
    public static Type<CurrentUserChangedHandler> getType() {
        return TYPE;
    }

    /**
	 * 
	 */
    private final UserDTO currentUser;

    /**
	 * 
	 */
    private final String loginUrl;

    /**
	 * 
	 */
    private final String logoutUrl;

    /**
     * Constructor
     * 
     * @param currentUser
     *            the user
     * @param loginUrl
     *            the url to login to
     * @param logoutUrl
     *            the url to logout to
     */
    public CurrentUserChangedEvent(final UserDTO currentUser, final String loginUrl, final String logoutUrl) {
        this.currentUser = currentUser;
        this.loginUrl = loginUrl;
        this.logoutUrl = logoutUrl;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(final CurrentUserChangedHandler handler) {
        handler.onCurrentUserChanged(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Type<CurrentUserChangedHandler> getAssociatedType() {
        return getType();
    }

    /**
     * Access the current user attached to this event.
     * 
     * @return The {@link CurrentUser} describing the currently logged in user.
     */
    public UserDTO getCurrentUser() {
        return currentUser;
    }

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
}
