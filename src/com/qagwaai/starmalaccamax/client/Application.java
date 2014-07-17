/**
 * Application.java
 * com.qagwaai.starmalaccamax.client
 * StarMalaccamax
 * Created Apr 12, 2011 at 3:34:14 PM
 */
package com.qagwaai.starmalaccamax.client;

import com.google.api.gwt.oauth2.client.Auth;
import com.qagwaai.starmalaccamax.client.event.EventBus;

/**
 * @author pgirard
 * 
 */
public final class Application {

    /**
     * the version of the application
     */
    public static final String VERSION = "1.0.1.3";
	// TODO #05: add constants for OAuth2 (don't forget to update GOOGLE_CLIENT_ID)
	private static final Auth AUTH = Auth.get();
	public static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
	public static final String GOOGLE_CLIENT_ID = "688666984611-49p8uh23718hlhpv5pjf8jn6sicvb8ce.apps.googleusercontent.com";
	public static final String PLUS_ME_SCOPE = "https://www.googleapis.com/auth/userinfo.profile";
	// TODO #05:> end
	
    
    private EventBus eventBus;

    /**
     * Constructor
     */
    private Application() {
    }

    private static Application ref;

    /**
     * singleton - get instance
     * 
     * @return the Application singleton
     */
    public static Application getInstance() {
        if (ref == null) {
            ref = new Application();
        }
        return ref;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(final EventBus eventBus) {
        this.eventBus = eventBus;
    }
    
    public Auth getAuth() {
    	return AUTH;
    }
}
