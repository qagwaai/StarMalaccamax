/**
 * ModelTest.java
 * Created by pgirard at 1:57:30 PM on Aug 11, 2010
 * in the com.qagwaai.starmalaccamax.server package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao.twig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.qagwaai.starmalaccamax.client.Application;
import com.qagwaai.starmalaccamax.client.admin.util.DelimitedToRecord;
import com.qagwaai.starmalaccamax.client.service.action.GetPlayerOpportunitiesPage;
import com.qagwaai.starmalaccamax.client.service.action.GetPlayerOpportunitiesPageResponse;
import com.qagwaai.starmalaccamax.server.PlayerSummaryServiceImpl;
import com.qagwaai.starmalaccamax.server.dao.CaptainDAO;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.MarketDAO;
import com.qagwaai.starmalaccamax.server.dao.PlanetDAO;
import com.qagwaai.starmalaccamax.server.dao.ShipDAO;
import com.qagwaai.starmalaccamax.server.dao.SolarSystemDAO;
import com.qagwaai.starmalaccamax.server.dao.UserDAO;
import com.qagwaai.starmalaccamax.shared.TransformationException;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;
import com.qagwaai.starmalaccamax.shared.model.SolarSystem;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public final class SolarSystemDataTest {
	/**
	 * 
	 */
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalUserServiceTestConfig(), new LocalDatastoreServiceTestConfig()).setEnvIsAdmin(true)
			.setEnvIsLoggedIn(true);

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
	public void testBulkLoad() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		Assert.assertNotNull(dao);

		URL batch = this.getClass().getResource("/Top_100_System_Query.txt");
		File batchFile = new File(batch.toURI());
		FileReader fr = new FileReader(batchFile);
		BufferedReader br = new BufferedReader(fr);
		String strLine;
		ArrayList<SolarSystemDTO> cache = new ArrayList<SolarSystemDTO>();
		while ((strLine = br.readLine()) != null) {
			try {
				cache.add(DelimitedToRecord.toSolarSystem(strLine));
			} catch (TransformationException e) {
				System.out.println(e);
			}
		}
		br.close();
		fr.close();

		dao.bulkAddSolarSystem(cache);

		ArrayList<SolarSystemDTO> found = dao.getAllSolarSystems(0, 2000, "");

		Assert.assertEquals(cache.size(), found.size());
	}

	@Test
	public void testClosestMarketsToPlanet() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		SolarSystemDAO solarSystemDao = factory.getSolarSystemDAO();
		Assert.assertNotNull(solarSystemDao);

		URL solarSystemBatch = this.getClass().getResource("/ExportSolarSystemSmall.txt");
		File solarSystemBatchFile = new File(solarSystemBatch.toURI());
		FileReader solarSystemFR = new FileReader(solarSystemBatchFile);
		BufferedReader solarSystemBR = new BufferedReader(solarSystemFR);
		String strLine;
		ArrayList<SolarSystemDTO> solarSystemCache = new ArrayList<SolarSystemDTO>();
		while ((strLine = solarSystemBR.readLine()) != null) {
			try {
				solarSystemCache.add(DelimitedToRecord.toSolarSystem(strLine));
			} catch (TransformationException e) {
				System.out.println(e);
			}
		}
		solarSystemBR.close();
		solarSystemFR.close();

		solarSystemDao.bulkAddSolarSystem(solarSystemCache);

		PlanetDAO dao = factory.getPlanetDAO();
		Assert.assertNotNull(dao);

		URL planetBatch = this.getClass().getResource("/ExportPlanetSmall.txt");
		File planetBatchFile = new File(planetBatch.toURI());
		FileReader planetFR = new FileReader(planetBatchFile);
		BufferedReader planetBR = new BufferedReader(planetFR);
		ArrayList<PlanetDTO> planetCache = new ArrayList<PlanetDTO>();
		while ((strLine = planetBR.readLine()) != null) {
			try {
				planetCache.add(DelimitedToRecord.toPlanet(strLine));
			} catch (TransformationException e) {
				System.out.println(e);
			}
		}
		planetBR.close();
		planetFR.close();

		dao.bulkAddPlanet(planetCache);

		URL closestBatch = this.getClass().getResource("/ExportClosestSmall.txt");
		File closestBatchFile = new File(closestBatch.toURI());
		FileReader closestFR = new FileReader(closestBatchFile);
		BufferedReader closestBR = new BufferedReader(closestFR);
		ArrayList<ClosestDTO> closestCache = new ArrayList<ClosestDTO>();
		while ((strLine = closestBR.readLine()) != null) {
			try {
				closestCache.add(DelimitedToRecord.toClosest(strLine));
			} catch (TransformationException e) {
				System.out.println(e);
			}
		}
		closestBR.close();
		closestFR.close();

		solarSystemDao.bulkAddClosest(closestCache);

		MarketDAO marketDAO = factory.getMarketDAO();
		Assert.assertNotNull(marketDAO);

		URL marketBatch = this.getClass().getResource("/ExportMarketSmall.txt");
		File marketBatchFile = new File(marketBatch.toURI());
		FileReader marketFR = new FileReader(marketBatchFile);
		BufferedReader marketBR = new BufferedReader(marketFR);
		ArrayList<MarketDTO> marketCache = new ArrayList<MarketDTO>();
		int line = 0;
		while ((strLine = marketBR.readLine()) != null) {
			try {
				marketCache.add(DelimitedToRecord.toMarket(strLine));
				line++;
			} catch (Throwable e) {
				System.out.println("Line: " + line + ": " + e);
			}
		}
		marketBR.close();
		marketFR.close();
		marketDAO.bulkAddMarket(marketCache);

		UserDTO user = new UserDTO();
		user.setId(1L);
		user.setEmail("t@test.com");
		UserDAO userDAO = factory.getUserDAO();
		userDAO.addUser(user);

		CaptainDTO captain1 = new CaptainDTO();
		captain1.setOwnerId(user.getId());
		captain1.setName("Captain1");

		CaptainDTO captain2 = new CaptainDTO();
		captain2.setOwnerId(user.getId());
		captain2.setName("Captain2");

		CaptainDAO captainDAO = factory.getCaptainDAO();
		captainDAO.addCaptain(captain1);
		captainDAO.addCaptain(captain2);

		ShipDTO ship1 = new ShipDTO();
		ship1.setOwnerId(captain1.getId());
		LocationDTO location1 = new LocationDTO();
		location1.setSolarSystemId(1L);
		location1.setPlanetId(371036L);
		ship1.setLocation(location1);

		ShipDTO ship2 = new ShipDTO();
		ship2.setOwnerId(captain2.getId());
		LocationDTO location2 = new LocationDTO();
		location2.setSolarSystemId(63503L);
		ship2.setLocation(location2);

		ShipDAO shipDAO = factory.getShipDAO();
		shipDAO.addShip(ship1);
		shipDAO.addShip(ship2);

		Assert.assertEquals(2, shipDAO.getAllShips().size());

		/****************** call service ***********************/

		PlayerSummaryServiceImpl service = new PlayerSummaryServiceImpl();
		GetPlayerOpportunitiesPage action = new GetPlayerOpportunitiesPage(user);

		GetPlayerOpportunitiesPageResponse response = service.execute(action);
		/*
		 * MarketDAO marketDAO = factory.getMarketDAO(); for (Planet planet : response.getPlanets()) { MarketDTO market = new MarketDTO();
		 * market.setPlanetId(planet.getId()); market.getCommodities().put(Commodities.AGRICULTURE.getKey(), new MarketCommodityDTO( new Double(1.5), new Double(3.0), new
		 * Double(5.5), new Double(5.0), Commodities.AGRICULTURE.getKey())); marketDAO.addMarket(market); }
		 */
		System.out.println("Planets: " + response.getPlanets().size());
		System.out.println("Markets: " + response.getMarketOpportunities().size());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testCreateAndLoad() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		Assert.assertNotNull(dao);

		SolarSystemDTO ss = new SolarSystemDTO();
		Assert.assertNotNull(ss);
		ss.setName("tester");

		SolarSystem addedSS = dao.addSolarSystem(ss);
		Assert.assertNotNull(addedSS);

		SolarSystem foundSS = dao.getSolarSystem(addedSS.getId());
		Assert.assertNotNull(foundSS);
		Assert.assertEquals(foundSS.getId(), addedSS.getId());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetSolarSystems() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		Assert.assertNotNull(dao);

		SolarSystemDTO ss = new SolarSystemDTO();
		Assert.assertNotNull(ss);
		ss.setName("tester");
		SolarSystemDTO ss2 = new SolarSystemDTO();
		Assert.assertNotNull(ss2);
		ss2.setName("tester2");

		SolarSystem addedSS = dao.addSolarSystem(ss);
		Assert.assertNotNull(addedSS);
		SolarSystem added2SS = dao.addSolarSystem(ss2);
		Assert.assertNotNull(added2SS);

		ArrayList<SolarSystemDTO> found = dao.getAllSolarSystems(0, 15, "");

		Assert.assertEquals(2, found.size());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testNameFilter() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		Assert.assertNotNull(dao);

		URL batch = this.getClass().getResource("/Top_100_System_Query.txt");
		File batchFile = new File(batch.toURI());
		FileReader fr = new FileReader(batchFile);
		BufferedReader br = new BufferedReader(fr);
		String strLine;
		ArrayList<SolarSystemDTO> cache = new ArrayList<SolarSystemDTO>();
		while ((strLine = br.readLine()) != null) {
			try {
				cache.add(DelimitedToRecord.toSolarSystem(strLine));
			} catch (TransformationException e) {
				System.out.println(e);
			}
		}
		br.close();
		fr.close();

		dao.bulkAddSolarSystem(cache);
		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("name");
		filter.setValue("Beers Hollow");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<SolarSystemDTO> found = dao.getAllSolarSystems(0, 2000, filterAll, "name");
		Assert.assertNotSame(cache.get(0).getName(), found.get(0).getName());
		Assert.assertEquals(found.get(0).getName(), "Beers Hollow");
		Assert.assertEquals(found.size(), 1);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testNameSort() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		Assert.assertNotNull(dao);

		URL batch = this.getClass().getResource("/Top_100_System_Query.txt");
		File batchFile = new File(batch.toURI());
		FileReader fr = new FileReader(batchFile);
		BufferedReader br = new BufferedReader(fr);
		String strLine;
		ArrayList<SolarSystemDTO> cache = new ArrayList<SolarSystemDTO>();
		while ((strLine = br.readLine()) != null) {
			try {
				cache.add(DelimitedToRecord.toSolarSystem(strLine));
			} catch (TransformationException e) {
				System.out.println(e);
			}
		}
		br.close();
		fr.close();

		dao.bulkAddSolarSystem(cache);

		ArrayList<SolarSystemDTO> found = dao.getAllSolarSystems(0, 2000, "name");
		Assert.assertNotSame(cache.get(0).getName(), found.get(0).getName());

	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testRemove() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		Assert.assertNotNull(dao);

		SolarSystemDTO ss = new SolarSystemDTO();
		Assert.assertNotNull(ss);
		ss.setName("tester");

		SolarSystem addedSS = dao.addSolarSystem(ss);
		Assert.assertNotNull(addedSS);

		boolean removed = dao.removeSolarSystem(ss);

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
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		Assert.assertNotNull(dao);

		SolarSystemDTO ss = new SolarSystemDTO();
		Assert.assertNotNull(ss);
		ss.setName("tester");
		SolarSystemDTO ss2 = new SolarSystemDTO();
		Assert.assertNotNull(ss2);
		ss2.setName("tester2");

		SolarSystem addedSS = dao.addSolarSystem(ss);
		Assert.assertNotNull(addedSS);
		SolarSystem added2SS = dao.addSolarSystem(ss2);
		Assert.assertNotNull(added2SS);

		int total = dao.getTotalSolarSystems();
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
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		Assert.assertNotNull(dao);

		SolarSystemDTO ss = new SolarSystemDTO();
		Assert.assertNotNull(ss);
		ss.setName("tester");

		SolarSystem addedSS = dao.addSolarSystem(ss);
		Assert.assertNotNull(addedSS);

		ss.setName("tester2");
		SolarSystem updatedSS = dao.updateSolarSystem(ss);
		Assert.assertEquals(addedSS.getName(), updatedSS.getName());
	}

}
