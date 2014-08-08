/**
 * GetCaptainResponse.java
 * Created by pgirard at 1:40:09 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
public final class GetAuthorizationUrlResponse extends AbstractResponse implements IsSerializable {

	private String authorizationUrl;

	public GetAuthorizationUrlResponse(String authorizationUrl) {
		super();
		this.authorizationUrl = authorizationUrl;
	}

	public GetAuthorizationUrlResponse() {
		super();
	}

	public String getAuthorizationUrl() {
		return authorizationUrl;
	}

	public void setAuthorizationUrl(String authorizationUrl) {
		this.authorizationUrl = authorizationUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((authorizationUrl == null) ? 0 : authorizationUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetAuthorizationUrlResponse other = (GetAuthorizationUrlResponse) obj;
		if (authorizationUrl == null) {
			if (other.authorizationUrl != null)
				return false;
		} else if (!authorizationUrl.equals(other.authorizationUrl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GetAuthorizationUrlResponse [authorizationUrl="
				+ authorizationUrl + "]";
	}
	
	

}
