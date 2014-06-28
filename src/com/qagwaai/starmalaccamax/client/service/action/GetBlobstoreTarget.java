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
public final class GetBlobstoreTarget implements IsSerializable, Action<GetBlobstoreTargetResponse> {

    /**
	 * 
	 */
    private String url;

    /**
	 * 
	 */
    public GetBlobstoreTarget() {

    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * @param url
     *            the url of where the blobstore is located
     */
    public GetBlobstoreTarget(final String url) {
        super();
        this.url = url;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetBlobstoreTarget [url=" + url + "]";
    }

}
