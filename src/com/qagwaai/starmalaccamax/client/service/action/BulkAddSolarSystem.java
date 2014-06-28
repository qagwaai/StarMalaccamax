/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;

/**
 * @author pgirard
 * 
 */
public final class BulkAddSolarSystem implements IsSerializable, Action<BulkAddSolarSystemResponse> {

    /**
	 * 
	 */
    private ArrayList<SolarSystemDTO> solarSystems;

    /**
	 * 
	 */
    public BulkAddSolarSystem() {

    }

    /**
     * @param solarSystems
     *            the SolarSystems to add
     */
    public BulkAddSolarSystem(final ArrayList<SolarSystemDTO> solarSystems) {
        this.solarSystems = solarSystems;
    }

    /**
     * @return the solarSystems
     */
    public ArrayList<SolarSystemDTO> getSolarSystems() {
        return solarSystems;
    }

    /**
     * @param solarSystems
     *            the SolarSystems to set
     */
    public void setSolarSystems(final ArrayList<SolarSystemDTO> solarSystems) {
        this.solarSystems = solarSystems;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final int maxLen = 10;
        return "BulkAddSolarSystem [solarSystems="
            + (solarSystems != null ? solarSystems.subList(0, Math.min(solarSystems.size(), maxLen)) : null) + "]";
    }

}
