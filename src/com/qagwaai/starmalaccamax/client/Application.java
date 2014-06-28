/**
 * Application.java
 * com.qagwaai.starmalaccamax.client
 * StarMalaccamax
 * Created Apr 12, 2011 at 3:34:14 PM
 */
package com.qagwaai.starmalaccamax.client;

import com.qagwaai.starmalaccamax.client.event.EventBus;

/**
 * @author pgirard
 * 
 */
public final class Application {

    /**
     * the version of the application
     */
    public static final String VERSION = "1.0.0.30";
    
    private EventBus eventBus;
    private int DAOFactory;

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

	public int getDAOFactory() {
		return DAOFactory;
	}

	public void setDAOFactory(int dAOFactory) {
		DAOFactory = dAOFactory;
	}

    
}
