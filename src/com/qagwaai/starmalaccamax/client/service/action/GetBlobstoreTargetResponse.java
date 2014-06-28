/**
 * GetCurrentUserResponse.java
 * Created by pgirard at 3:57:56 PM on Jul 22, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.actions package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
public final class GetBlobstoreTargetResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private String blobStoreUrl;

    /**
     * @return the blobStoreUrl
     */
    public String getBlobStoreUrl() {
        return blobStoreUrl;
    }

    /**
     * @param blobStoreUrl
     *            the blobStoreUrl to set
     */
    public void setBlobStoreUrl(final String blobStoreUrl) {
        this.blobStoreUrl = blobStoreUrl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetBlobstoreTargetResponse [blobStoreUrl=" + blobStoreUrl + "]";
    }

}
