package com.qagwaai.starmalaccamax.server.OAuth;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FlickrApi;
import org.scribe.builder.api.Foursquare2Api;
import org.scribe.builder.api.LiveApi;
import org.scribe.builder.api.TumblrApi;
import org.scribe.builder.api.TwitterApi;
import org.scribe.builder.api.VimeoApi;
import org.scribe.builder.api.YahooApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.qagwaai.starmalaccamax.client.core.util.ClientUtils;
import com.qagwaai.starmalaccamax.server.util.ServerUtils;
import com.qagwaai.starmalaccamax.shared.ServiceException;
import com.qagwaai.starmalaccamax.shared.model.CredentialDTO;
import com.qagwaai.starmalaccamax.shared.model.SocialUserDTO;

public class OAuthHelper {

	/**
	 * Logger for this service
	 */
	private static Logger log = Logger.getLogger(OAuthHelper.class.getName());

	// private final String SESSION = "GWTOAuthLoginDemo_session";
	private final String SESSION_ID = "GWTOAuthLoginDemo_sessionid";
	private final String SESSION_REQUEST_TOKEN = "GWTOAuthLoginDemo_request_token";
	private final String SESSION_NONCE = "GWTOAuthLoginDemo_nonce";
	private final String SESSION_PROTECTED_URL = "GWTOAuthLoginDemo_protected_url";
	private final String SESSION_ACCESS_TOKEN = "GWTOAuthLoginDemo_access_token";
	private final String SESSION_YAHOO_GUID = "GWTOAuthLoginDemo_yahoo_guid";
	private final String SESSION_AUTH_PROVIDER = "GWTOAuthLoginDemo_auth_provider";

	private final String DEFAULT_USERNAME = "test";
	private final String DEFAULT_PASSWORD = "secret";
	private final String DEFAULT_JSON = "{" + "\n" + "  \":username:\" " + "\""
			+ DEFAULT_USERNAME + "\"" + "\n" + "}";
	
	private final HttpSession session;


	public OAuthHelper(HttpSession session) {
		super();
		this.session = session;
	}

	public String getAuthorizationUrl(CredentialDTO credential)
			throws ServiceException {

		log.info("callback url: " + credential.getRedirectUrl());
		String authorizationUrl = null;
		Token requestToken = null;

		int authProvider = credential.getAuthProvider();

		OAuthService service = getOAuthService(authProvider);
		if (service == null) {
			throw new ServiceException("Could not build OAuthService");
		}

		if (authProvider == ClientUtils.TWITTER
				|| authProvider == ClientUtils.FLICKR
				|| authProvider == ClientUtils.TUMBLR
				|| authProvider == ClientUtils.YAHOO
				|| authProvider == ClientUtils.AWEBER
				|| authProvider == ClientUtils.VIMEO) {
			String authProviderName = ClientUtils
					.getAuthProviderName(authProvider);
			log.info(authProviderName
					+ " requires Request token first.. obtaining..");
			try {
				requestToken = service.getRequestToken();
				log.info("Got request token: " + requestToken);
				// we must save in the session. It will be required to
				// get the access token
				saveRequestTokenToSession(requestToken);
			} catch (Exception e) {
				System.out.println("Exception getting request token: " + e);

				String stackTrace = stackTraceToString(e);
				throw new ServiceException("Could not get request token for "
						+ authProvider + " " + stackTrace);
			}

		}

		log.info("Getting Authorization url...");
		try {
			if (requestToken != null) {
				log.info("Using request token: " + requestToken);
			}
			authorizationUrl = service.getAuthorizationUrl(requestToken);
			log.info("Got Authorization URL" + authorizationUrl);
			// if the provider supports "state", save it to session
			if (authProvider == ClientUtils.LINKEDIN
					|| authProvider == ClientUtils.GITHUB
					|| authProvider == ClientUtils.FACEBOOK
					|| authProvider == ClientUtils.IMGUR
					|| authProvider == ClientUtils.INSTAGRAM) {
				log.info("Auth URL should have state in QUERY_STRING");
				log.info("Extract state from URL");
				String state = ServerUtils.getQueryStringValueFromUrl(
						authorizationUrl, "state");
				if (state != null) {
					log.info("state: " + state);
					log.info("Save state to session");
					saveStateToSession(state);
				}
			}
		} catch (Exception e) {
			log.severe("Exception caught: " + e);
			String st = stackTraceToString(e);
			throw new ServiceException("Could not get Authorization url: " + st);
		}

		if (authProvider == ClientUtils.FLICKR) {
			authorizationUrl += "&perms=read";
		}
		log.info("Returning: " + authorizationUrl);
		return authorizationUrl;
	}

	public SocialUserDTO verifySocialUser(CredentialDTO credential)
			throws ServiceException {
		int authProvider = credential.getAuthProvider();
		log.info("authProvider: " + authProvider);
		String authProviderName = ClientUtils.getAuthProviderName(authProvider);
		log.info("Verifying social usr from " + authProviderName);

		Token requestToken = null;
		String yahooGuid = null;
		String protectedResourceUrl = ClientUtils
				.getProctedResourceUrl(authProvider);

		if (authProvider == ClientUtils.FACEBOOK
				|| authProvider == ClientUtils.INSTAGRAM
				|| authProvider == ClientUtils.IMGUR
				|| authProvider == ClientUtils.LINKEDIN) {
			log.info("Verifying state: " + credential.getState());
			verifyState(credential.getState());
		}

		// if there is any request token in session, get it
		// it can be null
		requestToken = getRequestTokenFromSession();

		OAuthService service = null;
		Verifier verifier = null;
		Token accessToken = null;

		/* Get Access Token */
		if (authProvider != ClientUtils.DEFAULT) {
			service = getOAuthService(authProvider);
			verifier = new Verifier(credential.getVerifier());
			log.info("Requesting access token with requestToken: "
					+ requestToken);
			log.info("verifier=" + verifier);
			try {
				accessToken = service.getAccessToken(requestToken, verifier);
				if (accessToken == null) {
					log.severe("Could not get Access Token for "
							+ authProviderName);
					throw new ServiceException("Could not get Access Token");
				}
			} catch (Exception e) {
				log.info("Exception received getting Access Token: " + e);
				throw new ServiceException(
						"Exception received getting Access Token: " + e);
			}
			log.info("Got the access token: " + accessToken);
			log.info(" Token: " + accessToken.getToken());
			log.info(" Secret: " + accessToken.getSecret());
			log.info(" Raw: " + accessToken.getRawResponse());
		} else {
			/*
			 * * Default provider.* The info will probably come from database.
			 * Password will* probably some kind of salted hash. We're just hard
			 * coding* "test" and "secret" for the demo.
			 */
			log.info("Handing default login..");
			String username = credential.getLoginName();
			String password = credential.getPassword();
			if (username == null) {
				throw new ServiceException("Default Username can not be empty");
			}
			if (password == null) {
				throw new ServiceException("Default Password not be empty");
			}
			if (username.equals(getDefaultUsername())
					&& password.equals(getDefaultPassword())) {

			} else {
				throw new ServiceException("Please use " + getDefaultUsername()
						+ "  and " + getDefaultPassword()
						+ " as Default Credential!");
			}

		}

		if (authProvider == ClientUtils.INSTAGRAM) {
			InstagramToken instagramToken = null;

			try {
				instagramToken = InstagramToken.parse(accessToken
						.getRawResponse());
			} catch (ParseException e) {
				throw new ServiceException("Could not parse "
						+ authProviderName + " Json AccessToken");
			}
			log.info("Getting Instragram Access Token");
			log.info(" access token" + instagramToken.getAcessToken());
			log.info(" userId: " + instagramToken.getUserId());
			log.info(" full name: " + instagramToken.getFullName());
			log.info(" username: " + instagramToken.getFullName());
			log.info(" raw: " + instagramToken.getRawResponse());

			// replace userId and access token in protected resource url
			protectedResourceUrl = ClientUtils
					.getProctedResourceUrl(authProvider);
			log.info("Instragram protected resource url: "
					+ protectedResourceUrl);
			protectedResourceUrl = String.format(protectedResourceUrl,
					instagramToken.getUserId(), instagramToken.getAcessToken());
			log.info("Instragram protected resource url: "
					+ protectedResourceUrl);
		}

		if (authProvider == ClientUtils.GITHUB
				|| authProvider == ClientUtils.FOURSQUARE
				|| authProvider == ClientUtils.LINKEDIN) {
			protectedResourceUrl = String.format(protectedResourceUrl,
					accessToken.getToken());
		}

		log.info("Protected resource url: " + protectedResourceUrl);

		if (authProvider == ClientUtils.YAHOO) {
			/* we need to replace <GUID> */
			yahooGuid = getQueryStringValue(accessToken.getRawResponse(),
					"xoauth_yahoo_guid");
			if (yahooGuid == null) {
				throw new ServiceException(
						"Could not get Yahoo GUID from Query String");
			}
			// must save it to session. we'll use to get the user profile
			saveYahooGuidToSession(yahooGuid);

			protectedResourceUrl = ClientUtils
					.getProctedResourceUrl(authProvider);
			protectedResourceUrl = String.format(protectedResourceUrl,
					yahooGuid);
			log.info("Yahoo protected resource url: " + protectedResourceUrl);
		}

		// make session id
		String sessionId = makeRandomString();

		// must save session id to session
		saveSessionIdToSession(sessionId);

		// must save authProvider to session
		saveAuthProviderToSession(authProvider);

		SocialUserDTO socialUser = null;
		if (authProvider != ClientUtils.DEFAULT) {
			// must save access token to session
			saveAccessTokenToSession(accessToken);

			// must save the protected resource url to session
			saveProtectedResourceUrlToSession(protectedResourceUrl);

			// now request protected resource
			log.info("Getting protected resource");
			log.info("Protected resource url: " + protectedResourceUrl);
			try {
				OAuthRequest oauthrequest = new OAuthRequest(Verb.GET,
						protectedResourceUrl);
				service.signRequest(accessToken, oauthrequest);

				org.scribe.model.Response oauthresponse = oauthrequest.send();
				log.info("Status code: " + oauthresponse.getCode());
				log.info("Body: " + oauthresponse.getBody());

				String json = oauthresponse.getBody();
				socialUser = getSocialUserFromJson(json, authProvider);
			} catch (Exception e) {
				log.severe("Could not retrieve protected resource: " + e);
				throw new ServiceException(
						"Could not retrieve protected resource: " + e);
			}
		} else {
			socialUser = new SocialUserDTO();
			socialUser.setName(getDefaultUsername());

			socialUser.setJson(getDefaultJson());
		}
		socialUser.setSessionId(sessionId);
		return socialUser;
	}

	private OAuthService getOAuthService(int authProvider)
			throws ServiceException {
		OAuthService service = null;
		switch (authProvider) {
		/*
		 * case ClientUtils.AWEBER: { service = new ServiceBuilder()
		 * .provider(AWeberApi.class) .apiKey(OurOAuthParams.AWEBER_API_KEY)
		 * .apiSecret(OurOAuthParams.AWEBER_API_SECRET)
		 * .callback(ClientUtils.getCallbackUrl()) .build(); break; }
		 */
		case ClientUtils.FACEBOOK: {
			service = new ServiceBuilder().provider(FacebookApiWithState.class)
					.apiKey(OurOAuthParams.FACEBOOK_API_KEY)
					.apiSecret(OurOAuthParams.FACEBOOK_API_SECRET)
					.callback(ClientUtils.getCallbackUrl()).build();
			break;
		}

		case ClientUtils.GOOGLE: {
			/*
			 * service = new ServiceBuilder() .provider(GoogleApi20.class)
			 * .apiKey(ServerUtils.GOOGLE_API_KEY)
			 * .apiSecret(ServerUtils.GOOGLE_API_SECRET)
			 * .scope(ServerUtils.GOOGLE_SCOPE)
			 * .callback(ClientUtils.getCallbackUrl())
			 * .grantType(OAuthConstants.AUTHORIZATION_CODE) .build();
			 */

			/*
			 * * Used to use Google oauth 2.0 api form a* forked version of
			 * scribe. Now we use* scribe 1.3.6 and extended Google API* from
			 * yincrash. The Google2Api.java is in* Google2Api.java in
			 * server/OAuth directory* Jun-08-2014
			 */
			service = new ServiceBuilder().provider(Google2Api.class)
					.apiKey(OurOAuthParams.GOOGLE_API_KEY)
					.apiSecret(OurOAuthParams.GOOGLE_API_SECRET)
					.scope(OurOAuthParams.GOOGLE_SCOPE)
					.callback(ClientUtils.getCallbackUrl()).build();
			break;
		}

		case ClientUtils.TWITTER: {
			service = new ServiceBuilder().provider(TwitterApi.class)
					.apiKey(OurOAuthParams.TWITTER_API_KEY)
					.apiSecret(OurOAuthParams.TWITTER_API_SECRET)
					.callback(ClientUtils.getCallbackUrl()).build();
			break;
		}
		case ClientUtils.YAHOO: {
			/*
			 * Note: yahoo does not support custom port in redirect URL. So we
			 * won't be able to test the app in localhost.
			 */
			service = new ServiceBuilder().provider(YahooApi.class)
					.apiKey(OurOAuthParams.YAHOO_API_KEY)
					.apiSecret(OurOAuthParams.YAHOO_API_SECRET)
					.callback(ClientUtils.getCallbackUrl()).build();
			break;
		}

		case ClientUtils.LINKEDIN: {
			/*
			 * service = new ServiceBuilder() .provider(LinkedInApi.class)
			 * .apiKey(ServerUtils.LINKEDIN_API_KEY)
			 * .apiSecret(ServerUtils.LINKEDIN_API_SECRET)
			 * .callback(ClientUtils.getCallbackUrl()) .build();
			 */
			service = new ServiceBuilder().provider(Linkedin2Api.class)
					.apiKey(OurOAuthParams.LINKEDIN_API_KEY)
					.apiSecret(OurOAuthParams.LINKEDIN_API_SECRET)
					.callback(ClientUtils.getCallbackUrl()).build();
			break;
		}

		case ClientUtils.INSTAGRAM: {
			/*
			 * service = new ServiceBuilder() .provider(InstagramApi.class)
			 * .apiKey(ServerUtils.INSTAGRAM_API_KEY)
			 * .apiSecret(ServerUtils.INSTAGRAM_API_SECRET)
			 * .grantType(OAuthConstants.AUTHORIZATION_CODE)
			 * .callback(ClientUtils.getCallbackUrl()) .build();
			 */
			/* Jun-14-2014, using scribe 1.3.6 */
			service = new ServiceBuilder().provider(Instagram2Api.class)
					.apiKey(OurOAuthParams.INSTAGRAM_API_KEY)
					.apiSecret(OurOAuthParams.INSTAGRAM_API_SECRET)
					.callback(ClientUtils.getCallbackUrl()).build();

			break;
		}

		case ClientUtils.GITHUB: {
			service = new ServiceBuilder().provider(GithubApi.class)
					.apiKey(OurOAuthParams.GITHUB_API_KEY)
					.apiSecret(OurOAuthParams.GITHUB_API_SECRET)
					.callback(ClientUtils.getCallbackUrl()).build();
			break;

		}

		case ClientUtils.FLICKR: {
			service = new ServiceBuilder().provider(FlickrApi.class)
					.apiKey(OurOAuthParams.FLICKR_API_KEY)
					.apiSecret(OurOAuthParams.FLICKR_API_SECRET)
					.callback(ClientUtils.getCallbackUrl()).build();
			break;
		}

		case ClientUtils.VIMEO: {
			service = new ServiceBuilder().provider(VimeoApi.class)
					.apiKey(OurOAuthParams.VIMEO_API_KEY)
					.apiSecret(OurOAuthParams.VIMEO_API_SECRET)
					.callback(ClientUtils.getCallbackUrl()).build();
			break;
		}

		case ClientUtils.WINDOWS_LIVE: {
			// a Scope must be specified
			service = new ServiceBuilder().provider(LiveApi.class)
					.apiKey(OurOAuthParams.WINDOWSLIVE_API_KEY)
					.apiSecret(OurOAuthParams.WINDOWSLIVE_API_SECRET)
					.callback(ClientUtils.getCallbackUrl()).scope("wl.basic")
					.build();
			break;
		}

		case ClientUtils.TUMBLR: {
			service = new ServiceBuilder().provider(TumblrApi.class)
					.apiKey(OurOAuthParams.TUMBLRLIVE_API_KEY)
					.apiSecret(OurOAuthParams.TUMBLRLIVE_API_SECRET)
					.callback(ClientUtils.getCallbackUrl()).build();
			break;
		}

		case ClientUtils.FOURSQUARE: {
			service = new ServiceBuilder().provider(Foursquare2Api.class)
					.apiKey(OurOAuthParams.FOURSQUARE_API_KEY)
					.apiSecret(OurOAuthParams.FOURSQUARE_API_SECRET)
					.callback(ClientUtils.getCallbackUrl()).build();
			break;
		}

		default: {
			return null;
		}

		}
		return service;
	}

	private void saveRequestTokenToSession(Token requestToken)
			throws ServiceException {
		HttpSession session = getHttpSession();
		if (session == null) {
			throw new ServiceException(ClientUtils.SESSION_EXPIRED_MESSAGE);
		}
		session.setAttribute(SESSION_REQUEST_TOKEN, requestToken);
	}

	private HttpSession getHttpSession() {
		return this.session;
	}

	public static String stackTraceToString(Throwable caught) {
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement e : caught.getStackTrace()) {
			sb.append(e.toString()).append("\n");
		}
		return sb.toString();
	}

	private void saveStateToSession(String state) throws ServiceException {
		HttpSession session = getHttpSession();
		if (session == null) {
			throw new ServiceException(ClientUtils.SESSION_EXPIRED_MESSAGE);
		}
		session.setAttribute(SESSION_NONCE, state);
	}

	private void verifyState(String state) throws ServiceException {
		String stateInSession = getStateFromSession();
		if (stateInSession == null) {
			throw new ServiceException("Could not find state in session");
		}
		if (!stateInSession.equals(state)) {
			throw new ServiceException("State mismatch in session, expected: "
					+ stateInSession + " Passed: " + state);
		}
	}

	private String getStateFromSession() throws ServiceException {
		HttpSession session = getHttpSession();
		if (session == null) {
			throw new ServiceException(ClientUtils.SESSION_EXPIRED_MESSAGE);
		}
		return (String) session.getAttribute(SESSION_NONCE);
	}

	private Token getRequestTokenFromSession() throws ServiceException {
		HttpSession session = getHttpSession();
		if (session == null) {
			throw new ServiceException(ClientUtils.SESSION_EXPIRED_MESSAGE);
		}
		return (Token) session.getAttribute(SESSION_REQUEST_TOKEN);
	}

	private String getDefaultUsername() {
		return DEFAULT_USERNAME;
	}

	private String getDefaultPassword() {
		return DEFAULT_PASSWORD;
	}

	private static Map<String, String> parseQueryString(String qs) {
		String[] ps = qs.split("&");
		Map<String, String> map = new HashMap<String, String>();

		for (String p : ps) {
			String k = p.split("=")[0];
			String v = p.split("=")[1];
			map.put(k, v);
		}
		return map;
	}

	private String getQueryStringValue(String qs, String name) {
		Map<String, String> map = parseQueryString(qs);
		return map.get(name);
	}

	private void saveYahooGuidToSession(String guid) throws ServiceException {
		HttpSession session = getHttpSession();
		if (session == null) {
			throw new ServiceException(ClientUtils.SESSION_EXPIRED_MESSAGE);
		}
		session.setAttribute(SESSION_YAHOO_GUID, guid);
	}

	private String makeRandomString() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	private void saveSessionIdToSession(String sessionId)
			throws ServiceException {
		HttpSession session = getHttpSession();
		if (session == null) {
			throw new ServiceException(ClientUtils.SESSION_EXPIRED_MESSAGE);
		}
		session.setAttribute(SESSION_ID, sessionId);
	}

	private void saveAuthProviderToSession(int authProvider)
			throws ServiceException {
		HttpSession session = getHttpSession();
		if (session == null) {
			throw new ServiceException(ClientUtils.SESSION_EXPIRED_MESSAGE);
		}
		session.setAttribute(SESSION_AUTH_PROVIDER, authProvider);
	}

	private void saveAccessTokenToSession(Token accessToken)
			throws ServiceException {
		HttpSession session = getHttpSession();
		if (session == null) {
			throw new ServiceException(ClientUtils.SESSION_EXPIRED_MESSAGE);
		}
		session.setAttribute(SESSION_ACCESS_TOKEN, accessToken);

	}

	private void saveProtectedResourceUrlToSession(String url)
			throws ServiceException {
		HttpSession session = getHttpSession();
		if (session == null) {
			throw new ServiceException(ClientUtils.SESSION_EXPIRED_MESSAGE);
		}
		session.setAttribute(SESSION_PROTECTED_URL, url);
	}

	private SocialUserDTO getSocialUserFromJson(String json, int authProvider)
			throws ServiceException {
		String authProviderName = ClientUtils.getAuthProviderName(authProvider);
		log.info("Auth provider: " + authProviderName);

		JSONParser jsonParser = new JSONParser();
		Object obj = null;
		SocialUserDTO socialUser = new SocialUserDTO();
		json = ServerUtils.prettyPrintJsonString(json);
		switch (authProvider) {
		case ClientUtils.FACEBOOK: {
			/*
			 * --Facebook-- { "id":"537157209", "name":"Muhammad Muquit",
			 * "first_name":"Muhammad", "last_name":"Muquit",
			 * "link":"http:\/\/www.facebook.com\/muhammad.muquit",
			 * "username":"muhammad.muquit", "gender":"male",
			 * "timezone":-5,"locale":"en_US", "verified":true,
			 * "updated_time":"2012-11-10T23:13:04+0000"} }
			 */
			try {
				obj = jsonParser.parse(json);
				JSONObject jsonObj = (JSONObject) obj;

				socialUser.setName((String) jsonObj.get("name"));
				socialUser.setFirstName((String) jsonObj.get("first_name"));
				socialUser.setLastName((String) jsonObj.get("last_name"));
				socialUser.setGender((String) jsonObj.get("gender"));

				socialUser.setJson(json);

				return socialUser;
			} catch (ParseException e) {
				throw new ServiceException("Could not parse JSON data from "
						+ authProviderName + ":" + e.getMessage());
			}
		}

		case ClientUtils.YAHOO: {
			/*
			 * --YAHOO--
			 * http://developer.yahoo.com/social/rest_api_guide/extended
			 * -profile-resource.html# { "profile": { "uri":
			 * "http:\/\/social.yahooapis.com\/v1\/user\/ECUFIYO7BLY5FOV54XAPEQDC3Y\/profile",
			 * "guid": "ECUFIYO7BLY5FOAPEQDC3Y", "birthYear": 1969, "created":
			 * "2010-01-23T13:07:10Z", "displayAge": 89, "gender": "M", "image":
			 * { "height": 192, "imageUrl":
			 * "http:\/\/l.yimg.com\/a\/i\/identity2\/profile_192c.png", "size":
			 * "192x192", "width": 192 }, "location":
			 * "Philadelphia, Pennsylvania", "memberSince":
			 * "2006-08-04T13:27:58Z", "nickname": "jdoe", "profileUrl":
			 * "http:\/\/profile.yahoo.com\/ECUFIYO7BLY5FOV54XAPEQDC3Y",
			 * "searchable": false, "updated": "2011-04-16T07:28:00Z",
			 * "isConnected": false } }
			 */
			try {
				obj = jsonParser.parse(json);
				JSONObject jsonObj = (JSONObject) obj;
				// get profile object
				JSONObject jsonObjPeople = (JSONObject) jsonObj.get("profile");

				socialUser.setJson(json);

				socialUser.setNickname((String) jsonObjPeople.get("nickname"));
				socialUser.setGender((String) jsonObjPeople.get("gender"));
				socialUser
						.setFirstName((String) jsonObjPeople.get("givenName"));
				socialUser
						.setLastName((String) jsonObjPeople.get("familyName"));

				return socialUser;
			} catch (ParseException e) {
				throw new ServiceException("Could not parse JSON data from "
						+ authProviderName + ":" + e.getMessage());
			}
		}

		case ClientUtils.GOOGLE: {
			/*
			 * --Google-- { "id": "116397076041912827850", "name":
			 * "Muhammad Muquit", "given_name": "Muhammad", "family_name":
			 * "Muquit", "link":
			 * "https://plus.google.com/116397076041912827850", "gender":
			 * "male", "locale": "en-US" }
			 */

			try {
				obj = jsonParser.parse(json);
				JSONObject jsonObj = (JSONObject) obj;

				socialUser.setJson(json);

				socialUser.setName((String) jsonObj.get("name"));
				socialUser.setFirstName((String) jsonObj.get("given_name"));
				socialUser.setLastName((String) jsonObj.get("family_name"));
				socialUser.setGender((String) jsonObj.get("gender"));

				return socialUser;
			} catch (ParseException e) {
				throw new ServiceException("Could not parse JSON data from "
						+ authProviderName + ":" + e.getMessage());
			}
		}

		case ClientUtils.LINKEDIN: {
			/*
			 * --Linkedin-- { "firstName": "Muhammad", "headline":
			 * "Sr. Software Engineer at British Telecom", "lastName": "Muquit",
			 * }
			 */
			try {
				obj = jsonParser.parse(json);
				JSONObject jsonObj = (JSONObject) obj;

				socialUser.setJson(json);

				socialUser.setFirstName((String) jsonObj.get("firstName"));
				socialUser.setLastName((String) jsonObj.get("lastName"));

				return socialUser;
			} catch (ParseException e) {
				throw new ServiceException("Could not parse JSON data from "
						+ authProviderName + ":" + e.getMessage());
			}
		}

		case ClientUtils.TWITTER: {
			/*
			 * --Twitter -- { "id":955924206, "contributors_enabled":false,
			 * "profile_use_background_image":true,
			 * "time_zone":"Eastern Time (US & Canada)", "following":false,
			 * "friends_count":3, "profile_text_color": "333333",
			 * "geo_enabled":false,
			 * "created_at":"Sun Nov 18 17:54:22 +0000 2012",
			 * "utc_offset":-18000, "follow_request_sent":false,
			 * "name":"Muhammad Muquit", "id_str":"955924206",
			 * "default_profile_image":true, "verified":false,
			 * "profile_sidebar_border_color":"C0DEED", "url":null,
			 * "favourites_count":0, .. "lang":"en",
			 * "profile_background_color":"C0DEED", "screen_name":"mmqt2012", ..
			 * }
			 */

			try {
				obj = jsonParser.parse(json);
				JSONObject jsonObj = (JSONObject) obj;

				socialUser.setJson(json);

				socialUser.setName((String) jsonObj.get("name"));
				socialUser.setGender((String) jsonObj.get("gender"));

				return socialUser;
			} catch (ParseException e) {
				throw new ServiceException("Could not parse JSON data from "
						+ authProviderName + ":" + e.getMessage());
			}
		}

		case ClientUtils.IMGUR: {
			try {
				log.info("ImgUr JSON: " + json);
				obj = jsonParser.parse(json);
				JSONObject jsonObj = (JSONObject) obj;
				// get profile object
				JSONObject jsonObjData = (JSONObject) jsonObj.get("data");

				socialUser.setJson(json);
				socialUser.setName((String) jsonObjData.get("username"));

				return socialUser;

			} catch (ParseException e) {
				throw new ServiceException("Could not parse JSON data from "
						+ authProviderName + ":" + e.getMessage());
			}
		}

		case ClientUtils.INSTAGRAM: {
			/*
			 * -- Instragram -- { "data": { "id": "1574083", "username":
			 * "snoopdogg", "full_name": "Snoop Dogg", "profile_picture":
			 * "http://distillery.s3.amazonaws.com/profiles/profile_1574083_75sq_1295469061.jpg"
			 * , "bio": "This is my bio", "website": "http://snoopdogg.com",
			 * "counts": { "media": 1320, "follows": 420, "followed_by": 3410 }
			 * }
			 */

			try {
				log.info("Instragram JSON: " + json);
				obj = jsonParser.parse(json);
				JSONObject jsonObj = (JSONObject) obj;
				// get profile object
				JSONObject jsonObjData = (JSONObject) jsonObj.get("data");

				socialUser.setJson(json);
				socialUser.setName((String) jsonObjData.get("username"));

				return socialUser;

			} catch (ParseException e) {
				throw new ServiceException("Could not parse JSON data from "
						+ authProviderName + ":" + e.getMessage());
			}

		}

		case ClientUtils.GITHUB: {
			/*
			 * -- github -- { "plan":{ "private_repos":0, "space":307200,
			 * "name":"free", "collaborators":0 }, "followers":0, "type":"User",
			 * "events_url"
			 * :"https://api.github.com/users/oauthdemo2012/events{/privacy}",
			 * "owned_private_repos":0, "public_gists":0, "avatar_url":
			 * "https://secure.gravatar.com/avatar/e0cb08c2b353cc1c3022dc65ebd060d1?d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-user-420.png"
			 * , "received_events_url":
			 * "https://api.github.com/users/oauthdemo2012/received_events",
			 * "private_gists":0, "disk_usage":0,
			 * "url":"https://api.github.com/users/oauthdemo2012",
			 * "followers_url"
			 * :"https://api.github.com/users/oauthdemo2012/followers",
			 * "login":"oauthdemo2012", "created_at":"2012-12-20T01:36:36Z",
			 * "following_url"
			 * :"https://api.github.com/users/oauthdemo2012/following",
			 * "organizations_url"
			 * :"https://api.github.com/users/oauthdemo2012/orgs",
			 * "following":0, "starred_url":
			 * "https://api.github.com/users/oauthdemo2012/starred{/owner}{/repo}"
			 * , "collaborators":0, "public_repos":0,
			 * "repos_url":"https://api.github.com/users/oauthdemo2012/repos",
			 * "gists_url"
			 * :"https://api.github.com/users/oauthdemo2012/gists{/gist_id}",
			 * "id":3085592, "total_private_repos":0,
			 * "html_url":"https://github.com/oauthdemo2012",
			 * "subscriptions_url"
			 * :"https://api.github.com/users/oauthdemo2012/subscriptions",
			 * "gravatar_id":"e0cb08c2b353cc1c3022dc65ebd060d1" }
			 */
			log.info("github JSON: " + json);
			try {
				obj = jsonParser.parse(json);
				JSONObject jsonObj = (JSONObject) obj;

				socialUser.setJson(json);
				socialUser.setName((String) jsonObj.get("login"));

				return socialUser;

			} catch (ParseException e) {
				throw new ServiceException("Could not parse JSON data from "
						+ authProviderName + ":" + e.getMessage());
			}
		}

		case ClientUtils.FLICKR: {
			/*
			 * -- flickr -- { "user": { "id": "91390211@N06", "username": {
			 * "_content": "oauthdemo2012" } }, "stat": "ok" }
			 */
			log.info("Flickr JSON: " + json);
			try {
				obj = jsonParser.parse(json);
				JSONObject jsonObj = (JSONObject) obj;
				JSONObject jsonObjUser = (JSONObject) jsonObj.get("user");
				JSONObject jsonObjUsername = (JSONObject) jsonObjUser
						.get("username");
				socialUser.setName((String) jsonObjUsername.get("_content"));
				socialUser.setJson(json);

				return socialUser;
			} catch (ParseException e) {
				throw new ServiceException("Could not parse JSON data from "
						+ authProviderName + ":" + e.getMessage());
			}
		}

		case ClientUtils.VIMEO: {
			/*
			 * --Vimeo starts -- { "generated_in": "0.0698", "stat": "ok",
			 * "person": { "created_on": "2012-12-22 23:37:55", "id":
			 * "15432968", "is_contact": "0", "is_plus": "0", "is_pro": "0",
			 * "is_staff": "0", "is_subscribed_to": "0", "username":
			 * "user15432968", "display_name": "oauthdemo2012", "location": "",
			 * "url": [ "" ], ..... } }
			 */
			log.info("Vimeo JSON: " + json);
			try {
				obj = jsonParser.parse(json);
				JSONObject jsonObj = (JSONObject) obj;
				JSONObject jsonObjPerson = (JSONObject) jsonObj.get("person");
				String userName = (String) jsonObjPerson.get("username");
				String displayName = (String) jsonObjPerson.get("display_name");

				if (displayName != null) {
					socialUser.setName(displayName);
				} else if (userName != null) {
					socialUser.setName(userName);
				} else {
					socialUser.setName("Unknown");
				}
				socialUser.setJson(json);

				return socialUser;
			} catch (ParseException e) {
				throw new ServiceException("Could not parse JSON data from "
						+ authProviderName + ":" + e.getMessage());
			}
		}

		case ClientUtils.WINDOWS_LIVE: {
			/*
			 * Windows Live --starts -- { "id" :
			 * "contact.c1678ab4000000000000000000000000", "first_name" :
			 * "Roberto", "last_name" : "Tamburello", "name" :
			 * "Roberto Tamburello", "gender" : "male", "locale" : "en_US" }
			 */
			log.info("Windows Live JSON: " + json);
			try {
				obj = jsonParser.parse(json);
				JSONObject jsonObj = (JSONObject) obj;
				JSONObject jsonErrorObj = (JSONObject) jsonObj.get("error");
				if (jsonErrorObj != null) {
					/*
					 * { "error": { "code": "request_token_too_many", "message":
					 * "The request includes more than one access token. Only one access token is allowed."
					 * } }
					 */
					String message = (String) jsonErrorObj.get("message");
					throw new ServiceException("Error: " + message);
				}
				socialUser.setName((String) jsonObj.get("name"));
				socialUser.setLastName((String) jsonObj.get("last_name"));
				socialUser.setFirstName((String) jsonObj.get("first_name"));
				socialUser.setJson(json);

				return socialUser;
			} catch (ParseException e) {
				throw new ServiceException("Could not parse JSON data from "
						+ authProviderName + ":" + e.getMessage());
			}
		}

		case ClientUtils.TUMBLR: {
			/*
			 * tumblr. -- { "meta": { "status": 200, "msg": "OK" }, "response":
			 * { "user": { "name": "oauthdemo2012", "likes": 0, "following": 1,
			 * "default_post_format": "html", "blogs": [ { "name":
			 * "oauthdemo2012", "url": "http:\/\/oauthdemo2012.tumblr.com\/",
			 * "followers": 0, "primary": true, "title": "Untitled",
			 * "description": "", "admin": true, "updated": 0, "posts": 0,
			 * "messages": 0, "queue": 0, "drafts": 0, "share_likes": true,
			 * "ask": false, "tweet": "N", "facebook": "N",
			 * "facebook_opengraph_enabled": "N", "type": "public" } ] } } }
			 */
			log.info("tumblr JSON: " + json);
			try {
				obj = jsonParser.parse(json);
				JSONObject jsonObj = (JSONObject) obj;
				JSONObject jsonObjResponse = (JSONObject) jsonObj
						.get("response");
				JSONObject jsonObjUser = (JSONObject) jsonObjResponse
						.get("user");
				String userName = (String) jsonObjUser.get("name");
				socialUser.setName(userName);
				socialUser.setJson(json);

				return socialUser;
			} catch (ParseException e) {
				throw new ServiceException("Could not parse JSON data from "
						+ authProviderName + ":" + e.getMessage());
			}
		}

		case ClientUtils.FOURSQUARE: {

			/*
			 * foursquare -- { "meta": { "code": 200, "errorType": "deprecated",
			 * "errorDetail":
			 * "Please provide an API version to avoid future errors.See http://bit.ly/vywCav"
			 * }, "notifications": [ { "type": "notificationTray", "item": {
			 * "unreadCount": 0 } } ], "response": { "user": { "id": "43999331",
			 * "firstName": "OAuth", "lastName": "Demo", "gender": "none",
			 * "relationship": "self", "photo":
			 * "https://foursquare.com/img/blank_boy.png", "friends": { "count":
			 * 0, "groups": [ { "type": "friends", "name": "Mutual friends",
			 * "count": 0, "items": [] }, { "type": "others", "name":
			 * "Other friends", "count": 0, "items": [] } ] }, ...... } } }
			 */
			try {
				obj = jsonParser.parse(json);
				JSONObject jsonObj = (JSONObject) obj;
				JSONObject jsonObjResponse = (JSONObject) jsonObj
						.get("response");
				JSONObject jsonObjUser = (JSONObject) jsonObjResponse
						.get("user");
				String firstName = (String) jsonObjUser.get("firstName");
				String lastName = (String) jsonObjUser.get("lastName");
				if (firstName != null && lastName != null) {
					socialUser.setName(firstName + " " + lastName);
				} else {
					socialUser.setName("UNKNOWN");
				}
				socialUser.setJson(json);

				return socialUser;
			} catch (ParseException e) {
				throw new ServiceException("Could not parse JSON data from "
						+ authProviderName + ":" + e.getMessage());
			}
		}

		default: {
			throw new ServiceException("Unknown Auth Provider: "
					+ authProviderName);
		}
		}

		/*
		 * We don't use Gson() anymore as it choked on nested Facebook JSON data
		 * Dec-03-2012
		 */

		/*
		 * // map json to SocialUser try { Gson gson = new Gson(); SocialUser
		 * user = gson.fromJson(json,SocialUser.class); // pretty print json
		 * //gson = new GsonBuilder().setPrettyPrinting().create(); //String
		 * jsonPretty = gson.toJson(json); user.setJson(json); return user; }
		 * catch (Exception e) { e.printStackTrace(); throw new
		 * ServiceException("Could not map userinfo JSON to SocialUser class: "
		 * + e); }
		 */
	}

	private String getDefaultJson() {
		return DEFAULT_JSON;
	}

}
