/**
 * ShipStatus.java
 * Created by pgirard at 10:48:33 AM on Nov 17, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

/**
 * @author pgirard
 * 
 */
public enum ShipTravelStatus {

        /**
	 * 
	 */
        Docked,
        /**
	 * 
	 */
        Traveling,
        Docking,
        Undocking,
        Disabled;

    /**
	 * 
	 */
    ShipTravelStatus() {

    }
}
