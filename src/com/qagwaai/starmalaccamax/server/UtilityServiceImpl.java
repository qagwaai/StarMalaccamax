package com.qagwaai.starmalaccamax.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Logger;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.qagwaai.starmalaccamax.client.service.UtilityService;
import com.qagwaai.starmalaccamax.client.service.action.Action;
import com.qagwaai.starmalaccamax.client.service.action.GetAllCountries;
import com.qagwaai.starmalaccamax.client.service.action.GetAllCountriesResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllTimezones;
import com.qagwaai.starmalaccamax.client.service.action.GetAllTimezonesResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetBlobstoreTarget;
import com.qagwaai.starmalaccamax.client.service.action.GetBlobstoreTargetResponse;
import com.qagwaai.starmalaccamax.client.service.action.Response;
import com.qagwaai.starmalaccamax.shared.ServiceException;
import com.qagwaai.starmalaccamax.shared.model.CountryDTO;

/**
 * 
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class UtilityServiceImpl extends RemoteServiceServlet implements UtilityService {
    /**
	 * 
	 */
    private static Logger log = Logger.getLogger(UtilityServiceImpl.class.getName());

    /**
     * @param <T>
     *            the response type
     * @param action
     *            the action to be executed
     * @throws ServiceException
     *             if the action cannot be completed
     * @return the response of executing the action
     * @see com.qagwaai.starmalaccamax.client.service.UtilityService#execute(com.qagwaai.starmalaccamax.client.service.action.Action)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Response> T execute(final Action<T> action) throws ServiceException {
        Object response = null;
        long startTime = Instrumentation.callStart("UtilityServiceImpl: " + action);
        com.google.appengine.api.users.UserService userService =
            com.google.appengine.api.users.UserServiceFactory.getUserService();
        try {
            if (!userService.isUserLoggedIn()) {
                log.warning("Not logged in user tried to execute action " + action.getClass().getName());
                throw new ServiceException("User is not logged in");
            }
        } catch (NullPointerException npe) {
            // assume that we are in a test unit
            LocalServiceTestHelper helper =
                new LocalServiceTestHelper(new LocalUserServiceTestConfig(), new LocalDatastoreServiceTestConfig())
                    .setEnvIsAdmin(true).setEnvIsLoggedIn(true);
            helper.setUp();
        }
        if (!userService.isUserLoggedIn()) {
            throw new ServiceException("User is not logged in");
        }
        if (action instanceof GetAllCountries) {
            response = (T) executeGetAllCountries((GetAllCountries) action);
        } else if (action instanceof GetAllTimezones) {
            response = (T) executeGetAllTimezones((GetAllTimezones) action);
        } else if (action instanceof GetBlobstoreTarget) {
            response = (T) executeGetBlobstoreTarget((GetBlobstoreTarget) action);
        }

        if (response != null) {
            Instrumentation.callEnd("UtilityServiceImpl: " + response, startTime, action.getClass().getSimpleName(),
                response);
            return (T) response;
        }

        log.severe("Action not Implemented: " + action.getClass().getName());
        throw new ServiceException("Action not Implemented");
    }

    /**
     * 
     * @param action
     *            the GetBlobstoreTarget request
     * @return an GetBlobstoreTargetResponse with the url of the blobstore
     */
    private GetBlobstoreTargetResponse executeGetBlobstoreTarget(final GetBlobstoreTarget action) {
        GetBlobstoreTargetResponse response = new GetBlobstoreTargetResponse();
        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

        response.setBlobStoreUrl(blobstoreService.createUploadUrl(action.getUrl()));
        return response;
    }

    /**
     * 
     * @param action
     *            the GetAllTimezones action
     * @return an GetAllTimezonesResponse with a list of timezones
     */
    private GetAllTimezonesResponse executeGetAllTimezones(final GetAllTimezones action) {
        GetAllTimezonesResponse response = new GetAllTimezonesResponse();
        String[] timezones = TimeZone.getAvailableIDs();
        Arrays.sort(timezones);
        ArrayList<String> all = new ArrayList<String>();
        for (String timezone : timezones) {
            all.add(timezone);
        }
        response.setTimezones(all);

        return response;
    }

    /**
     * 
     * @param action
     *            the GetAllCountries action
     * @return an GetAllCountriesResponse with a list of all countries
     */
    private GetAllCountriesResponse executeGetAllCountries(final GetAllCountries action) {
        GetAllCountriesResponse response = new GetAllCountriesResponse();

        Locale[] allLocales = Locale.getAvailableLocales();
        ArrayList<CountryDTO> countries = new ArrayList<CountryDTO>();
        for (Locale locale : allLocales) {
            countries.add(new CountryDTO(locale.getDisplayCountry(), locale.getCountry()));
        }
        response.setCountries(countries);
        return response;
    }
}
