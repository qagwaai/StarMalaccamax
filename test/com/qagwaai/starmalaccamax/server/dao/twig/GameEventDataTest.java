/**
 * ModelTest.java
 * Created by pgirard at 1:57:30 PM on Aug 11, 2010
 * in the com.qagwaai.starmalaccamax.server package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao.twig;

import java.util.ArrayList;
import java.util.Date;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.qagwaai.starmalaccamax.client.Application;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.GameEventDAO;
import com.qagwaai.starmalaccamax.shared.model.GameEvent;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;

/**
 * @author pgirard
 * 
 */
public final class GameEventDataTest {
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
	private void addLotsOfRecords(final GameEventDAO dao) throws Exception {
		for (int i = 0; i < 100; i++) {
			GameEventDTO ss = new GameEventDTO();
			Assert.assertNotNull(ss);
			ss.setPlayerId(Long.valueOf(i));
			ss.setStartDateTime(new Date(System.currentTimeMillis()));
			dao.addGameEvent(ss);
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
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		GameEventDAO dao = factory.getGameEventDAO();
		Assert.assertNotNull(dao);

		GameEventDTO ss = new GameEventDTO();
		Assert.assertNotNull(ss);
		ss.setShortDescription("tester");

		GameEvent addedSS = dao.addGameEvent(ss);
		Assert.assertNotNull(addedSS);

		GameEvent foundSS = dao.getGameEvent(addedSS.getId());
		Assert.assertNotNull(foundSS);
		Assert.assertEquals(foundSS.getId(), addedSS.getId());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetGameEvents() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		GameEventDAO dao = factory.getGameEventDAO();
		Assert.assertNotNull(dao);

		GameEventDTO ss = new GameEventDTO();
		Assert.assertNotNull(ss);
		ss.setShortDescription("tester");
		GameEventDTO ss2 = new GameEventDTO();
		Assert.assertNotNull(ss2);
		ss2.setShortDescription("tester2");

		GameEvent addedSS = dao.addGameEvent(ss);
		Assert.assertNotNull(addedSS);
		GameEvent added2SS = dao.addGameEvent(ss2);
		Assert.assertNotNull(added2SS);

		ArrayList<GameEventDTO> found = dao.getAllGameEvents(0, 15, "");

		Assert.assertEquals(2, found.size());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testNameSort() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		GameEventDAO dao = factory.getGameEventDAO();
		Assert.assertNotNull(dao);

		addLotsOfRecords(dao);

		ArrayList<GameEventDTO> found = dao.getAllGameEvents(0, 2000, "shortDescription");
		Assert.assertNotSame(1L, found.get(0).getId());

	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testRemove() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		GameEventDAO dao = factory.getGameEventDAO();
		Assert.assertNotNull(dao);

		GameEventDTO ss = new GameEventDTO();
		Assert.assertNotNull(ss);
		ss.setShortDescription("tester");

		GameEvent addedSS = dao.addGameEvent(ss);
		Assert.assertNotNull(addedSS);

		boolean removed = dao.removeGameEvent(ss);

		Assert.assertTrue(removed);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testTotal() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		GameEventDAO dao = factory.getGameEventDAO();
		Assert.assertNotNull(dao);

		GameEventDTO ss = new GameEventDTO();
		Assert.assertNotNull(ss);
		ss.setShortDescription("tester");
		GameEventDTO ss2 = new GameEventDTO();
		Assert.assertNotNull(ss2);
		ss2.setShortDescription("tester2");

		GameEvent addedSS = dao.addGameEvent(ss);
		Assert.assertNotNull(addedSS);
		GameEvent added2SS = dao.addGameEvent(ss2);
		Assert.assertNotNull(added2SS);

		int total = dao.getTotalGameEvents();
		Assert.assertEquals(2, total);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testUpdate() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		GameEventDAO dao = factory.getGameEventDAO();
		Assert.assertNotNull(dao);

		GameEventDTO ss = new GameEventDTO();
		Assert.assertNotNull(ss);
		ss.setShortDescription("tester");

		GameEvent addedSS = dao.addGameEvent(ss);
		Assert.assertNotNull(addedSS);

		ss.setShortDescription("tester2");
		GameEvent updatedSS = dao.updateGameEvent(ss);
		Assert.assertEquals(addedSS.getShortDescription(), updatedSS.getShortDescription());
	}
}
