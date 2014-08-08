package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CredentialDTO implements Serializable
{
    private String authProviderName;
    private int    authProvider;
    private String redirectUrl;
    private String state;        // facebook
    private String verifier;     // code for facebook
    private String loginName;
    private String password;
    private String email;
    
    public String getAuthProviderName()
    {
        return authProviderName;
    }
    public void setAuthProviderName(String authProviderName)
    {
        this.authProviderName=authProviderName;
    }
    public int getAuthProvider()
    {
        return authProvider;
    }
    public void setAuthProvider(int authProvider)
    {
        this.authProvider=authProvider;
    }
    public String getRedirectUrl()
    {
        return redirectUrl;
    }
    public void setRedirectUrl(String redirectUrl)
    {
        this.redirectUrl=redirectUrl;
    }
    public String getState()
    {
        return state;
    }
    public void setState(String state)
    {
        this.state=state;
    }
    public String getVerifier()
    {
        return verifier;
    }
    public void setVerifier(String verifier)
    {
        this.verifier=verifier;
    }
    public String getLoginName()
    {
        return loginName;
    }
    public void setLoginName(String loginName)
    {
        this.loginName=loginName;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
	@Override
	public String toString() {
		return "CredentialDTO [authProviderName=" + authProviderName
				+ ", authProvider=" + authProvider + ", redirectUrl="
				+ redirectUrl + ", state=" + state + ", verifier=" + verifier
				+ ", loginName=" + loginName + ", password=" + password
				+ ", email=" + email + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + authProvider;
		result = prime
				* result
				+ ((authProviderName == null) ? 0 : authProviderName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((loginName == null) ? 0 : loginName.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((redirectUrl == null) ? 0 : redirectUrl.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result
				+ ((verifier == null) ? 0 : verifier.hashCode());
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
		CredentialDTO other = (CredentialDTO) obj;
		if (authProvider != other.authProvider)
			return false;
		if (authProviderName == null) {
			if (other.authProviderName != null)
				return false;
		} else if (!authProviderName.equals(other.authProviderName))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (redirectUrl == null) {
			if (other.redirectUrl != null)
				return false;
		} else if (!redirectUrl.equals(other.redirectUrl))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (verifier == null) {
			if (other.verifier != null)
				return false;
		} else if (!verifier.equals(other.verifier))
			return false;
		return true;
	}
    
}
