/**
 * GetCurrentUserResponse.java
 * Created by pgirard at 3:57:56 PM on Jul 22, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.actions package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * @author pgirard
 * 
 */
public final class GetClosestsForPlanetResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<ClosestDTO> closests;

    /**
	 * 
	 */
    private PlanetDTO planet;

    /**
     * @return the closest
     */
    public ArrayList<ClosestDTO> getClosests() {
        return closests;
    }

    /**
     * @return the planet
     */
    public PlanetDTO getPlanet() {
        return planet;
    }

    /**
     * @param closests
     *            the closest to set
     */
    public void setClosests(final ArrayList<ClosestDTO> closests) {
        this.closests = closests;
    }

    /**
     * @param planet
     *            the planet to set
     */
    public void setPlanet(final PlanetDTO planet) {
        this.planet = planet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetClosestsForPlanetResponse [closests=" + closests + ", planet=" + planet + "]";
    }

}
