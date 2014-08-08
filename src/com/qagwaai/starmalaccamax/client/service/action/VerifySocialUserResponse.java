/**
 * GetCaptainResponse.java
 * Created by pgirard at 1:40:09 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.SocialUserDTO;

/**
 * @author pgirard
 * 
 */
public final class VerifySocialUserResponse extends AbstractResponse implements IsSerializable {
    /**
	 * 
	 */
    private SocialUserDTO socialUser;

	public VerifySocialUserResponse() {
		super();
	}

	public VerifySocialUserResponse(SocialUserDTO socialUser) {
		super();
		this.socialUser = socialUser;
	}

	public SocialUserDTO getSocialUser() {
		return socialUser;
	}

	public void setSocialUser(SocialUserDTO socialUser) {
		this.socialUser = socialUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((socialUser == null) ? 0 : socialUser.hashCode());
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
		VerifySocialUserResponse other = (VerifySocialUserResponse) obj;
		if (socialUser == null) {
			if (other.socialUser != null)
				return false;
		} else if (!socialUser.equals(other.socialUser))
			return false;
		return true;
	}
    
    
}
