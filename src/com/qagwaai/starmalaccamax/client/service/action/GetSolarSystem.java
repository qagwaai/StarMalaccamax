/**
 * GetSolarSystem.java
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
public final class GetSolarSystem extends AbstractPolyAction implements IsSerializable, Action<GetSolarSystemResponse> {

    /**
	 * 
	 */
    private Long id;

    /**
	 * 
	 */
    public GetSolarSystem() {

    }

    /**
     * @param id
     *            the id to look for
     */
    public GetSolarSystem(final Long id) {
	this.id = id;
	super.setMimeType(MimeType.rpc);
    }

    public GetSolarSystem(final MimeType mimeType, final Long id) {
	super(mimeType);
	this.id = id;
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

    @Override
    public String toString() {
	return "GetSolarSystem [id=" + id + ", super.toString=" + super.toString() + "]";
    }

}
