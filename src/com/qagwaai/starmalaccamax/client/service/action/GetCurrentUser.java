/**
 * GetCurrentUser.java
 * Created by pgirard at 3:57:10 PM on Jul 22, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.actions package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
public final class GetCurrentUser implements IsSerializable, Action<GetCurrentUserResponse> {
    /**
	 * 
	 */
    private String currentLocation;

    /**
	 * 
	 */
    public GetCurrentUser() {
        this.currentLocation = "(null)";
    }

    /**
     * @param currentLocation
     *            the location to return to in the url
     */
    public GetCurrentUser(final String currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * @return the currentLocation
     */
    public String getCurrentLocation() {
        return currentLocation;
    }

    /**
     * @param currentLocation
     *            the currentLocation to set
     */
    public void setCurrentLocation(final String currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetCurrentUser [currentLocation=" + currentLocation + "]";
    }

}
