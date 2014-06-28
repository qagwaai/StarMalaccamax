/**
 * LoginWatcher.java
 * Created by pgirard at 11:23:22 AM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.client package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.core;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.qagwaai.starmalaccamax.client.event.CurrentUserChangedEvent;
import com.qagwaai.starmalaccamax.client.event.CurrentUserChangedHandler;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.GetCurrentUser;
import com.qagwaai.starmalaccamax.client.service.helpers.GotCurrentUser;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public final class LoginWatcher implements CurrentUserChangedHandler {

    /**
     * the singleton instance
     */
    private static LoginWatcher ref;

    /**
     * singleton - get instance
     * 
     * @return the LoginWatcher singleton
     */
    public static LoginWatcher getInstance() {
        if (ref == null) {
            ref = new LoginWatcher();
        }
        return ref;
    }

    /**
     * the currently logged in user
     */
    private CurrentUserChangedEvent lastEvent;
    /**
	 * 
	 */
    private EventBus eventBus;

    /**
	 * 
	 */
    private LoginWatcher() {

    }

    /**
     * @return the eventBus
     */
    public EventBus getEventBus() {
        return eventBus;
    }

    /**
     * @return the lastEvent
     */
    public CurrentUserChangedEvent getLastEvent() {
        return lastEvent;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onCurrentUserChanged(final CurrentUserChangedEvent event) {
        this.lastEvent = event;

    }

    /**
     * start up the service
     * 
     * @param eventBus
     *            the bus to publish events to
     */
    public void start(final EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.addHandler(CurrentUserChangedEvent.getType(), this);
        GWT.log("Checking login...");

        String currentLocation = Window.Location.getHref();

        ServiceFactory.getLoginService().execute(new GetCurrentUser(currentLocation), new GotCurrentUser() {
            public void got(final UserDTO user, final String loginUrl, final String logoutUrl) {
                GWT.log("returned login status");
                if (user == null) {
                	GWT.log("invalid user returned");
                	Window.alert("Invalid user");
                	return;
                }
                String fixedLoginUrl = loginUrl;
                if (loginUrl.endsWith("?null")) {
                    fixedLoginUrl = loginUrl.substring(0, loginUrl.indexOf("?null") - 1);
                }
                String fixedLogoutUrl = logoutUrl;
                if (logoutUrl.endsWith("?null")) {
                    fixedLogoutUrl = logoutUrl.substring(0, logoutUrl.indexOf("?null") - 1);
                }
                CurrentUserChangedEvent.fire(eventBus, user, fixedLoginUrl, fixedLogoutUrl);

            }
        });
    }

}
