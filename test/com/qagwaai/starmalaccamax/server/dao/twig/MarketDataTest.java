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
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.MarketDAO;
import com.qagwaai.starmalaccamax.shared.model.Market;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * @author pgirard
 * 
 */
public final class MarketDataTest {
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
	private void addLotsOfRecords(final MarketDAO dao) throws Exception {
		for (int i = 0; i < 100; i++) {
			MarketDTO ss = new MarketDTO();
			Assert.assertNotNull(ss);
			ss.setPlanetId(Long.valueOf(i));
			ss.setLastVisited(new Date(System.currentTimeMillis()));
			dao.addMarket(ss);
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
		MarketDAO dao = factory.getMarketDAO();
		Assert.assertNotNull(dao);

		MarketDTO ss = new MarketDTO();
		Assert.assertNotNull(ss);
		ss.setLastVisited(new Date(System.currentTimeMillis()));

		Market addedSS = dao.addMarket(ss);
		Assert.assertNotNull(addedSS);

		Market foundSS = dao.getMarket(addedSS.getId());
		Assert.assertNotNull(foundSS);
		Assert.assertEquals(foundSS.getId(), addedSS.getId());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetMarketForPlanet() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.TWIG);
		MarketDAO dao = factory.getMarketDAO();
		Assert.assertNotNull(dao);
		addLotsOfRecords(dao);

		PlanetDTO planet = new PlanetDTO();
		planet.setId(Long.valueOf(5));
		Market found = dao.getMarketForPlanet(planet);

		Assert.assertNotNull(found);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetMarkets() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.TWIG);
		MarketDAO dao = factory.getMarketDAO();
		Assert.assertNotNull(dao);

		MarketDTO ss = new MarketDTO();
		Assert.assertNotNull(ss);
		ss.setLastVisited(new Date(System.currentTimeMillis()));
		MarketDTO ss2 = new MarketDTO();
		Assert.assertNotNull(ss2);
		ss2.setLastVisited(new Date(System.currentTimeMillis()));

		Market addedSS = dao.addMarket(ss);
		Assert.assertNotNull(addedSS);
		Market added2SS = dao.addMarket(ss2);
		Assert.assertNotNull(added2SS);

		ArrayList<MarketDTO> found = dao.getAllMarkets(0, 15, "");

		Assert.assertEquals(2, found.size());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testLastVisitedSort() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.TWIG);
		MarketDAO dao = factory.getMarketDAO();
		Assert.assertNotNull(dao);
		addLotsOfRecords(dao);

		ArrayList<MarketDTO> found = dao.getAllMarkets(0, 2000, "-lastVisited");
		Assert.assertNotSame(1L, found.get(0).getId());

	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testRemove() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.TWIG);
		MarketDAO dao = factory.getMarketDAO();
		Assert.assertNotNull(dao);

		MarketDTO ss = new MarketDTO();
		Assert.assertNotNull(ss);
		ss.setLastVisited(new Date(System.currentTimeMillis()));

		Market addedSS = dao.addMarket(ss);
		Assert.assertNotNull(addedSS);

		boolean removed = dao.removeMarket(ss);

		Assert.assertTrue(removed);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testTotal() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.TWIG);
		MarketDAO dao = factory.getMarketDAO();
		Assert.assertNotNull(dao);

		MarketDTO ss = new MarketDTO();
		Assert.assertNotNull(ss);
		ss.setLastVisited(new Date(System.currentTimeMillis()));
		MarketDTO ss2 = new MarketDTO();
		Assert.assertNotNull(ss2);
		ss2.setLastVisited(new Date(System.currentTimeMillis()));

		Market addedSS = dao.addMarket(ss);
		Assert.assertNotNull(addedSS);
		Market added2SS = dao.addMarket(ss2);
		Assert.assertNotNull(added2SS);

		int total = dao.getTotalMarkets();
		Assert.assertEquals(2, total);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testUpdate() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.TWIG);
		MarketDAO dao = factory.getMarketDAO();
		Assert.assertNotNull(dao);

		MarketDTO ss = new MarketDTO();
		Assert.assertNotNull(ss);
		ss.setLastVisited(new Date(System.currentTimeMillis()));

		Market addedSS = dao.addMarket(ss);
		Assert.assertNotNull(addedSS);

		ss.setLastVisited(new Date(System.currentTimeMillis()));
		Market updatedSS = dao.updateMarket(ss);
		Assert.assertEquals(addedSS.getLastVisited(), updatedSS.getLastVisited());
	}
}
