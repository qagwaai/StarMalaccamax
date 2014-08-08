package com.qagwaai.starmalaccamax.client.core.mvp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.qagwaai.starmalaccamax.client.core.mvp.handlers.LoginSocialClickHandler;
import com.qagwaai.starmalaccamax.client.core.util.ClientUtils;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.VerifySocialUser;
import com.qagwaai.starmalaccamax.client.service.action.VerifySocialUserResponse;
import com.qagwaai.starmalaccamax.shared.model.CredentialDTO;

public class LoginPresenter {

	private LoginView view;

	public LoginPresenter(LoginView view) {
		super();
		this.view = view;

		connectHandlers();
		handleRedirect();
	}

	private void connectHandlers() {
		view.getFacebookImage().addClickHandler(
				new LoginSocialClickHandler("Facebook"));
		view.getGoogleImage().addClickHandler(
				new LoginSocialClickHandler("Google"));
		view.getTwitterImage().addClickHandler(
				new LoginSocialClickHandler("Twitter"));
		view.getYahooImage().addClickHandler(
				new LoginSocialClickHandler("Yahoo!"));
		view.getLinkedinImage().addClickHandler(
				new LoginSocialClickHandler("Linkedin"));
		view.getInstagramImage().addClickHandler(
				new LoginSocialClickHandler("Instagram"));
		view.getVimeoImage().addClickHandler(
				new LoginSocialClickHandler("Vimeo"));
		view.getGithubImage().addClickHandler(
				new LoginSocialClickHandler("github"));
		view.getFlickrImage().addClickHandler(
				new LoginSocialClickHandler("flickr"));
		view.getLiveImage().addClickHandler(
				new LoginSocialClickHandler("Windows Live"));
		view.getTumblrImage().addClickHandler(
				new LoginSocialClickHandler("tumblr."));
		view.getFoursquareImage().addClickHandler(
				new LoginSocialClickHandler("foursquare"));

		view.getPasswordTextBox().addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
					ClientUtils.clearCookies();
					ClientUtils.saveAuthProvider(ClientUtils.DEFAULT);
					verifySocialUser();
				}
			}
		});

		view.getBtnLogin().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				// clear all cookes first
				ClientUtils.clearCookies();
				// save the auth provider to cookie
				ClientUtils.saveAuthProvider(ClientUtils.DEFAULT);

				verifySocialUser();

			}
		});

	}

	private void verifySocialUser() {
		final String authProviderName = ClientUtils
				.getAuthProviderNameFromCookie();
		final int authProvider = ClientUtils.getAuthProviderFromCookieAsInt();
		GWT.log("Verifying " + authProviderName + " user ...");
		final String userName = view.getUsernameTextBox().getText();
		final String password = view.getPasswordTextBox().getText();

		if (authProvider == ClientUtils.DEFAULT) {
			if (userName.length() == 0) {
				Window.alert("Username is empty");
				return;
			}
			if (password.length() == 0) {
				Window.alert("Password is empty");
				return;
			} else {
				/*
				 * if (loginDialog == null) { // we're using login screen
				 * showApp(APPSCREEN_MAIN); }
				 */

			}

		}
		
		
		CredentialDTO credential = null;
		try {
			credential = ClientUtils.getCredential();
		} catch (Exception e) {
			Window.alert(e.getMessage());
		}
		if (credential == null) {
			GWT.log("verifySocialUser: Could not get credential for "
					+ authProvider + " user");
			return;
		}

		if (authProvider == ClientUtils.DEFAULT) {
			credential.setLoginName(userName);
			credential.setPassword(password);
		}
		VerifySocialUser request = new VerifySocialUser(credential);
		
		ServiceFactory.getUserService().execute(request, new AsyncCallback<VerifySocialUserResponse>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Coult not verify" + authProvider + " user."
						+ caught);			}

			@Override
			public void onSuccess(VerifySocialUserResponse result) {
				ClientUtils.saveSessionId(result.getSocialUser().getSessionId());

				String name = "";
				if (result.getSocialUser().getName() != null) {
					name = result.getSocialUser().getName();
				} else if (result.getSocialUser().getNickname() != null) // yahoo
				{
					name = result.getSocialUser().getNickname();
				} else if (result.getSocialUser().getFirstName() != null) // linkedin
				{
					name = result.getSocialUser().getFirstName();
					String lastName = result.getSocialUser().getLastName();
					if (lastName != null) {
						name = name + " " + lastName;
					}
				}

				GWT.log(authProviderName + " user '" + name + "' is verified!\n"
						+ result.getSocialUser().getJson());
				ClientUtils.saveUsername(name);
				//updateLoginStatus();				
			}});

	}

	private void handleRedirect() {
		if (ClientUtils.redirected()) {
			if (!ClientUtils.alreadyLoggedIn()) {
				verifySocialUser();
			}
		} else {
			// Window.alert("No redirection..");
		}
		// updateLoginStatus();
	}
}
