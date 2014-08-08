/**
 * UserServiceImple.java
 * Created by pgirard at 1:37:52 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.server package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.qagwaai.starmalaccamax.client.service.UserService;
import com.qagwaai.starmalaccamax.client.service.action.Action;
import com.qagwaai.starmalaccamax.client.service.action.AddUser;
import com.qagwaai.starmalaccamax.client.service.action.AddUserResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllUsers;
import com.qagwaai.starmalaccamax.client.service.action.GetAllUsersResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAuthorizationUrl;
import com.qagwaai.starmalaccamax.client.service.action.GetAuthorizationUrlResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetUser;
import com.qagwaai.starmalaccamax.client.service.action.GetUserResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllUsers;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllUsersResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveUser;
import com.qagwaai.starmalaccamax.client.service.action.RemoveUserResponse;
import com.qagwaai.starmalaccamax.client.service.action.Response;
import com.qagwaai.starmalaccamax.client.service.action.UpdateUser;
import com.qagwaai.starmalaccamax.client.service.action.UpdateUserResponse;
import com.qagwaai.starmalaccamax.client.service.action.VerifySocialUser;
import com.qagwaai.starmalaccamax.client.service.action.VerifySocialUserResponse;
import com.qagwaai.starmalaccamax.server.OAuth.OAuthHelper;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.UserDAO;
import com.qagwaai.starmalaccamax.shared.ServiceException;
import com.qagwaai.starmalaccamax.shared.model.CredentialDTO;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class UserServiceImpl extends RemoteServiceServlet implements UserService {
    /**
     * Logger for this service
     */
    private static Logger log = Logger.getLogger(UserServiceImpl.class.getName());
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Response> T execute(final Action<T> action) throws ServiceException {
        Object response = null;
        long startTime = Instrumentation.callStart("UserServiceImpl: " + action);
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
        if (action instanceof GetUser) {
            response = (T) executeGetUser((GetUser) action);
        } else if (action instanceof GetAllUsers) {
            response = (T) executeGetAllUsers((GetAllUsers) action);
        } else if (action instanceof AddUser) {
            response = (T) executeAddUser((AddUser) action);
        } else if (action instanceof UpdateUser) {
            response = (T) executeUpdateUser((UpdateUser) action);
        } else if (action instanceof RemoveUser) {
            response = (T) executeRemoveUser((RemoveUser) action);
        } else if (action instanceof RemoveAllUsers) {
            response = (T) executeRemoveAllUsers((RemoveAllUsers) action);
		} else if (action instanceof GetAuthorizationUrl) {
			response = (T) executeGetAuthorizationUrl((GetAuthorizationUrl) action);
		} else if (action instanceof VerifySocialUser) {
			response = (T) executeVerifySocialUser((VerifySocialUser) action);
        }

        if (response != null) {
            Instrumentation.callEnd("UserServiceImpl: " + response, startTime, action.getClass().getSimpleName(),
                response);
            return (T) response;
        }

        log.severe("Action not Implemented: " + action.getClass().getName());
        throw new ServiceException("Action not Implemented");
    }

	private VerifySocialUserResponse executeVerifySocialUser(
			final VerifySocialUser action) throws ServiceException {
		VerifySocialUserResponse response = new VerifySocialUserResponse();

		CredentialDTO credential = action.getCredential();

		OAuthHelper helper = new OAuthHelper(getThreadLocalRequest()
				.getSession());

		response.setSocialUser(helper.verifySocialUser(credential));
		return response;
	}

	private GetAuthorizationUrlResponse executeGetAuthorizationUrl(
			final GetAuthorizationUrl action) throws ServiceException {
		GetAuthorizationUrlResponse response = new GetAuthorizationUrlResponse();

		CredentialDTO credential = action.getCredential();

		OAuthHelper helper = new OAuthHelper(getThreadLocalRequest()
				.getSession());

		response.setAuthorizationUrl(helper.getAuthorizationUrl(credential));
		return response;
	}

    /**
     * 
     * @param action
     *            the AddUser action
     * @return an AddUserResponse object with the added User
     * @throws ServiceException
     *             if the datastore could not add the User
     */
    private AddUserResponse executeAddUser(final AddUser action) throws ServiceException {
        AddUserResponse response = new AddUserResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        UserDAO userDAO = factory.getUserDAO();
        UserDTO foundUser = null;
        try {
            foundUser = userDAO.addUser(action.getUser());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetUser.");
        }
        response.setUser(foundUser);

        return response;
    }

    /**
     * 
     * @param action
     *            the get all users action
     * @return the get all users response object with the array list of all
     *         users enclosed
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetAllUsersResponse executeGetAllUsers(final GetAllUsers action) throws ServiceException {
        GetAllUsersResponse response = new GetAllUsersResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        UserDAO userDAO = factory.getUserDAO();

        ArrayList<UserDTO> foundUsers = null;
        try {
            if (action.usePaging()) {
                foundUsers = userDAO.getAllUsers(action.getStartRow(), action.getEndRow());
            } else {
                foundUsers = userDAO.getAllUsers();
            }
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetAllUsers.");
        }
        response.setUsers(foundUsers);
        return response;
    }

    /**
     * Get the user from the id
     * 
     * @param action
     *            the get current user action
     * @return the filled out response - the user object if logged in, or null
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetUserResponse executeGetUser(final GetUser action) throws ServiceException {

        GetUserResponse response = new GetUserResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        UserDAO userDAO = factory.getUserDAO();
        UserDTO foundUser = null;
        try {
            foundUser = userDAO.getUser(action.getId());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command GetUser.");
        }
        response.setUser(foundUser);

        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveAllUsers action
     * @return an RemoveAllUsersResponse with the status of the removal
     * @throws ServiceException
     *             if the action fails
     */
    private RemoveAllUsersResponse executeRemoveAllUsers(final RemoveAllUsers action) throws ServiceException {
        RemoveAllUsersResponse response = new RemoveAllUsersResponse();
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        UserDAO userDAO = factory.getUserDAO();
        Boolean result = null;
        try {
            result = userDAO.deleteAllUsers();
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveAllUsers.");
        }
        response.setResponse(result);

        return response;
    }

    /**
     * 
     * @param action
     *            the RemoveUser action
     * @return a RemoveUserResponse object with the User removed
     * @throws ServiceException
     *             if the datastore could not remove the User
     */
    private RemoveUserResponse executeRemoveUser(final RemoveUser action) throws ServiceException {
        RemoveUserResponse response = new RemoveUserResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        UserDAO userDAO = factory.getUserDAO();
        // boolean foundUser = false;
        try {
            userDAO.removeUser(action.getUser());
        } catch (Throwable e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command RemoveUser.");
        }
        response.setUser(action.getUser());

        return response;
    }

    /**
     * 
     * @param action
     *            the UpdateUser action
     * @return an UpdateUserResponse object with the updated User
     * @throws ServiceException
     *             if the datastore could not update the User
     */
    private UpdateUserResponse executeUpdateUser(final UpdateUser action) throws ServiceException {
        UpdateUserResponse response = new UpdateUserResponse();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
        UserDAO userDAO = factory.getUserDAO();
        UserDTO foundUser = null;
        try {
            foundUser = userDAO.updateUser(action.getUser());
        } catch (DAOException e) {
            log.severe(e.toString());
            throw new ServiceException("Could not complete command UpdateUser.");
        }
        response.setUser(foundUser);

        return response;
    }

}
