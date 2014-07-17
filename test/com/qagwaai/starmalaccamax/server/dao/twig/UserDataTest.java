/**
 * ModelTest.java
 * Created by pgirard at 1:57:30 PM on Aug 11, 2010
 * in the com.qagwaai.starmalaccamax.server package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao.twig;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.UserDAO;
import com.qagwaai.starmalaccamax.shared.model.User;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public final class UserDataTest {
	/**
	 * 
	 */
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalUserServiceTestConfig(), new LocalDatastoreServiceTestConfig()).setEnvIsAdmin(true)
			.setEnvIsLoggedIn(true);

	/**
	 * 
	 * @param dao
	 *            where to add the records
	 * @throws Exception
	 *             if the add doesn' t work
	 */
	private void addLotsOfRecords(final UserDAO dao) throws Exception {
		for (int i = 0; i < 100; i++) {
			UserDTO ss = new UserDTO();
			Assert.assertNotNull(ss);
			ss.setEmail("a" + i + "@b");
			dao.addUser(ss);
		}
	}

	/**
	 * @throws Exception
	 *             if the environment cannot be setup
	 */
	@Before
	public void setUp() throws Exception {
		helper.setUp();

	}

	/**
	 * @throws Exception
	 *             if the environment cannot be torn down
	 */
	@After
	public void tearDown() throws Exception {
		helper.tearDown();

	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testCreateAndLoad() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.TWIG);
		UserDAO dao = factory.getUserDAO();
		Assert.assertNotNull(dao);

		UserDTO ss = new UserDTO();
		Assert.assertNotNull(ss);
		ss.setEmail("a@b");

		User addedSS = dao.addUser(ss);
		Assert.assertNotNull(addedSS);

		User foundSS = dao.getUser(addedSS.getId());
		Assert.assertNotNull(foundSS);
		Assert.assertEquals(foundSS.getId(), addedSS.getId());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testFindUserByEmail() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.TWIG);
		UserDAO dao = factory.getUserDAO();
		Assert.assertNotNull(dao);

		addLotsOfRecords(dao);

		User foundUser = dao.findUserByEmail("a5@b");

		Assert.assertNotNull(foundUser);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetUsers() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.TWIG);
		UserDAO dao = factory.getUserDAO();
		Assert.assertNotNull(dao);

		UserDTO ss = new UserDTO();
		Assert.assertNotNull(ss);
		ss.setEmail("a@b");
		UserDTO ss2 = new UserDTO();
		Assert.assertNotNull(ss2);
		ss2.setEmail("b@b");

		User addedSS = dao.addUser(ss);
		Assert.assertNotNull(addedSS);
		User added2SS = dao.addUser(ss2);
		Assert.assertNotNull(added2SS);

		ArrayList<UserDTO> found = dao.getAllUsers(0, 15);

		Assert.assertEquals(2, found.size());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testRemove() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.TWIG);
		UserDAO dao = factory.getUserDAO();
		Assert.assertNotNull(dao);

		UserDTO ss = new UserDTO();
		Assert.assertNotNull(ss);
		ss.setEmail("a@b");

		User addedSS = dao.addUser(ss);
		Assert.assertNotNull(addedSS);

		boolean removed = dao.removeUser(ss);

		Assert.assertTrue(removed);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testUpdate() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.TWIG);
		UserDAO dao = factory.getUserDAO();
		Assert.assertNotNull(dao);

		UserDTO ss = new UserDTO();
		Assert.assertNotNull(ss);
		ss.setEmail("a@b");

		User addedSS = dao.addUser(ss);
		Assert.assertNotNull(addedSS);

		ss.setEmail("b@b");
		User updatedSS = dao.updateUser(ss);
		Assert.assertEquals(addedSS.getEmail(), updatedSS.getEmail());
	}
}
