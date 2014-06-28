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
import com.qagwaai.starmalaccamax.client.Application;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.ShipDAO;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.Ship;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;

/**
 * @author pgirard
 * 
 */
public final class ShipDataTest {
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
	private void addLotsOfRecords(final ShipDAO dao) throws Exception {
		for (int i = 0; i < 100; i++) {
			ShipDTO ss = new ShipDTO();
			Assert.assertNotNull(ss);
			ss.setOwnerId(Long.valueOf(i));
			ss.setName("ship" + i);
			dao.addShip(ss);
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
		ShipDAO dao = factory.getShipDAO();
		Assert.assertNotNull(dao);

		ShipDTO ss = new ShipDTO();
		Assert.assertNotNull(ss);
		ss.setName("ship1");

		Ship addedSS = dao.addShip(ss);
		Assert.assertNotNull(addedSS);

		Ship foundSS = dao.getShip(addedSS.getId());
		Assert.assertNotNull(foundSS);
		Assert.assertEquals(foundSS.getId(), addedSS.getId());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetShipForCaptain() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		ShipDAO dao = factory.getShipDAO();
		Assert.assertNotNull(dao);
		addLotsOfRecords(dao);

		CaptainDTO captain = new CaptainDTO();
		captain.setId(Long.valueOf(5));
		Ship found = dao.getShipForCaptain(captain);

		Assert.assertNotNull(found);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetShips() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		ShipDAO dao = factory.getShipDAO();
		Assert.assertNotNull(dao);

		ShipDTO ss = new ShipDTO();
		Assert.assertNotNull(ss);
		ss.setName("ship1");
		ShipDTO ss2 = new ShipDTO();
		Assert.assertNotNull(ss2);
		ss2.setName("ship2");

		Ship addedSS = dao.addShip(ss);
		Assert.assertNotNull(addedSS);
		Ship added2SS = dao.addShip(ss2);
		Assert.assertNotNull(added2SS);

		ArrayList<ShipDTO> found = dao.getAllShips(0, 15, "");

		Assert.assertEquals(2, found.size());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testLastVisitedSort() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		ShipDAO dao = factory.getShipDAO();
		Assert.assertNotNull(dao);
		addLotsOfRecords(dao);

		ArrayList<ShipDTO> found = dao.getAllShips(0, 2000, "-name");
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
		ShipDAO dao = factory.getShipDAO();
		Assert.assertNotNull(dao);

		ShipDTO ss = new ShipDTO();
		Assert.assertNotNull(ss);
		ss.setName("ship1");

		Ship addedSS = dao.addShip(ss);
		Assert.assertNotNull(addedSS);

		boolean removed = dao.removeShip(ss);

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
		ShipDAO dao = factory.getShipDAO();
		Assert.assertNotNull(dao);

		ShipDTO ss = new ShipDTO();
		Assert.assertNotNull(ss);
		ss.setName("ship1");
		ShipDTO ss2 = new ShipDTO();
		Assert.assertNotNull(ss2);
		ss2.setName("ship2");

		Ship addedSS = dao.addShip(ss);
		Assert.assertNotNull(addedSS);
		Ship added2SS = dao.addShip(ss2);
		Assert.assertNotNull(added2SS);

		int total = dao.getTotalShips();
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
		ShipDAO dao = factory.getShipDAO();
		Assert.assertNotNull(dao);

		ShipDTO ss = new ShipDTO();
		Assert.assertNotNull(ss);
		ss.setName("ship1");

		Ship addedSS = dao.addShip(ss);
		Assert.assertNotNull(addedSS);

		ss.setName("ship2");
		Ship updatedSS = dao.updateShip(ss);
		Assert.assertEquals(addedSS.getName(), updatedSS.getName());
	}
}
