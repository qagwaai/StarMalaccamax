/**
 * GetAllUsersResponse.java
 * Created by pgirard at 2:07:29 PM on Aug 19, 2010
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
public final class GetAllSolarSystemsResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<SolarSystemDTO> solarSystems;
    /**
	 * 
	 */
    private int totalSolarSystems;

    /**
     * @return the users
     */
    public ArrayList<SolarSystemDTO> getSolarSystems() {
        return solarSystems;
    }

    /**
     * @return the totalSolarSystems
     */
    public int getTotalSolarSystems() {
        return totalSolarSystems;
    }

    /**
     * @param solarSystems
     *            the users to set
     */
    public void setSolarSystems(final ArrayList<SolarSystemDTO> solarSystems) {
        this.solarSystems = solarSystems;
    }

    /**
     * @param totalSolarSystems
     *            the totalSolarSystems to set
     */
    public void setTotalSolarSystems(final int totalSolarSystems) {
        this.totalSolarSystems = totalSolarSystems;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetAllSolarSystemsResponse [solarSystems=" + solarSystems + ", totalSolarSystems=" + totalSolarSystems
            + "]";
    }

}
