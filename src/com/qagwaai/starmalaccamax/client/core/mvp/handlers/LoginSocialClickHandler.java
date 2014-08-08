/**
 * 
 */
package com.qagwaai.starmalaccamax.client.core.mvp.handlers;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.qagwaai.starmalaccamax.client.core.util.ClientUtils;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.GetAuthorizationUrl;
import com.qagwaai.starmalaccamax.client.service.action.GetAuthorizationUrlResponse;
import com.qagwaai.starmalaccamax.shared.model.CredentialDTO;

/**
 * @author ehldxae
 *
 */
public class LoginSocialClickHandler implements ClickHandler {

	private String provider;
	
	
	public LoginSocialClickHandler(String provider) {
		super();
		this.provider = provider;
	}


	/* (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
	 */
	@Override
	public void onClick(ClickEvent event) {
        final int authProvider = ClientUtils.getAuthProvider(provider);
        getAuthorizationUrl(authProvider);
	}
	
    private void getAuthorizationUrl(final int authProvider)
    {
        String authProviderName = ClientUtils.getAuthProviderName(authProvider);
        final String callbackUrl = ClientUtils.getCallbackUrl();
        GWT.log("Getting authorization url");
        
        final CredentialDTO credential = new CredentialDTO();
        credential.setRedirectUrl(callbackUrl);
        credential.setAuthProvider(authProvider);
        
        GetAuthorizationUrl request = new GetAuthorizationUrl(credential);
        ServiceFactory.getUserService().execute(request, new AsyncCallback<GetAuthorizationUrlResponse>(){

			@Override
			public void onFailure(Throwable caught) {
                ClientUtils.handleException(caught);
			}

			@Override
			public void onSuccess(GetAuthorizationUrlResponse result) {
                String authorizationUrl = result.getAuthorizationUrl();
                GWT.log("Authorization url: " + authorizationUrl);
                
                // clear all cookes first
                ClientUtils.clearCookies(); 
                
                // save the auth provider to cookie
                ClientUtils.saveAuthProvider(authProvider);
                
                // save the redirect url to a cookie as well
                // we need to redirect there after logout
                ClientUtils.saveRediretUrl(callbackUrl);
                
                //Window.alert("Redirecting to: " + authorizationUrl);
                ClientUtils.redirect(authorizationUrl);				
			}});
    }
}
