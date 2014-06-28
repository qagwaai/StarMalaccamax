/**
 * GetPlanet.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.MimeType;

/**
 * @author pgirard
 * 
 */
public final class GetPlanetsForSolarSystem extends AbstractPolyAction implements IsSerializable, Action<GetPlanetsForSolarSystemResponse> {

    /**
	 * 
	 */
    private Long id;

    /**
	 * 
	 */
    public GetPlanetsForSolarSystem() {

    }

    /**
     * @param id
     *            the id to look for
     */
    public GetPlanetsForSolarSystem(final Long id) {
        this.id = id;
        super.setMimeType(MimeType.rpc);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    public GetPlanetsForSolarSystem(final MimeType mimeType, final Long id) {
	super(mimeType);
	this.id = id;
    }

    @Override
    public String toString() {
	return "GetPlanetsForSolarSystem [id=" + id + ", toString()=" + super.toString() + "]";
    }



}
