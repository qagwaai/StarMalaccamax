/**
 * GetCaptain.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.CredentialDTO;

/**
 * @author pgirard
 * 
 */
public final class VerifySocialUser implements IsSerializable, Action<VerifySocialUserResponse> {
	
	private CredentialDTO credential;

	public VerifySocialUser() {
		super();
	}

	public VerifySocialUser(CredentialDTO credential) {
		super();
		this.credential = credential;
	}

	public CredentialDTO getCredential() {
		return credential;
	}

	public void setCredential(CredentialDTO credential) {
		this.credential = credential;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((credential == null) ? 0 : credential.hashCode());
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
		VerifySocialUser other = (VerifySocialUser) obj;
		if (credential == null) {
			if (other.credential != null)
				return false;
		} else if (!credential.equals(other.credential))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VerifySocialUser [credential=" + credential + "]";
	}
	
	
}
