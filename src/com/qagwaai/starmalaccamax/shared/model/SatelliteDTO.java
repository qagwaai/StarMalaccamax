/**
 * SatelliteDTO.java
 * Created by pgirard at 10:01:56 AM on Sep 27, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
public final class SatelliteDTO implements IsSerializable, Satellite {

    /**
	 * 
	 */
    private Long id;
    /**
	 * 
	 */
    private Long mainPlanetId;
    /**
	 * 
	 */
    private Long satellitePlanetId;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getMainPlanetId() {
        return mainPlanetId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getSatellitePlanetId() {
        return satellitePlanetId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMainPlanetId(final Long id) {
        this.mainPlanetId = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSatellitePlanetId(final Long id) {
        this.satellitePlanetId = id;
    }

}
