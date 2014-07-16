/**
 * ModelTest.java
 * Created by pgirard at 1:57:30 PM on Aug 11, 2010
 * in the com.qagwaai.starmalaccamax.server package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao.objectify;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.reflections.ReflectionUtils.getAllFields;
import static org.reflections.ReflectionUtils.withAnnotation;
import static org.reflections.ReflectionUtils.withModifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

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
import com.qagwaai.starmalaccamax.shared.model.Closest;
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
	 * 
	 * @param dao
	 *            where to add the records
	 * @throws Exception
	 *             if the add doesn' t work
	 */
	private void addLotsOfSolarSystemRecords(final SolarSystemDAO dao) throws Exception {
		int ownerId = 0;
		for (int i = 0; i < 100; i++) {
			SolarSystemDTO solarSystem = new SolarSystemDTO();
			solarSystem.setName(String.valueOf(ownerId++));
			solarSystem.setX(Double.valueOf(ownerId));
			solarSystem.setY(Double.valueOf(ownerId));
			solarSystem.setZ(Double.valueOf(ownerId));
			assertNotNull(solarSystem);
			dao.addSolarSystem(solarSystem);
			//if (ownerId > 15) {
			//	ownerId = 0;
			//}
		}
	}

	/**
	 * 
	 * @param dao
	 *            where to add the records
	 * @throws Exception
	 *             if the add doesn' t work
	 */
	private void addLotsOfClosestRecords(final SolarSystemDAO dao) throws Exception {
		int ownerId = 0;
		for (int i = 0; i < 100; i++) {
			ClosestDTO closest = new ClosestDTO();
			closest.setSolarSystemId(Long.valueOf(ownerId++));
			closest.setToSolarSystemId(Long.valueOf(ownerId));
			assertNotNull(closest);
			dao.addClosest(closest);
			if (ownerId > 15) {
				ownerId = 0;
			}
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
	public void testSolarSystemCreateAndLoad() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);

		SolarSystemDTO solarSystem = new SolarSystemDTO();
		assertNotNull(solarSystem);
		solarSystem.setName(String.valueOf(1));

		SolarSystem addedSolarSystem = dao.addSolarSystem(solarSystem);
		assertNotNull(addedSolarSystem);

		SolarSystem foundSolarSystem = dao.getSolarSystem(addedSolarSystem.getId());
		assertNotNull(foundSolarSystem);
		assertNotNull(addedSolarSystem.getId());
		assertEquals(foundSolarSystem.getId(), addedSolarSystem.getId());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testBulkLoad() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);

		URL batch = this.getClass().getResource("/ExportSolarSystemSmall.csv");
		File batchFile = new File(batch.toURI());
		FileReader fr = new FileReader(batchFile);
		BufferedReader br = new BufferedReader(fr);
		String strLine;
		ArrayList<SolarSystemDTO> cache = new ArrayList<SolarSystemDTO>();
		strLine = br.readLine(); // skip first line
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

		assertEquals(cache.size(), found.size());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetSolarSystems() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);

		SolarSystemDTO solarSystem = new SolarSystemDTO();
		assertNotNull(solarSystem);
		solarSystem.setName(String.valueOf(1));
		SolarSystemDTO solarSystem2 = new SolarSystemDTO();
		assertNotNull(solarSystem2);
		solarSystem2.setName(String.valueOf(2));

		SolarSystem addedSS = dao.addSolarSystem(solarSystem);
		assertNotNull(addedSS);
		SolarSystem added2SS = dao.addSolarSystem(solarSystem2);
		assertNotNull(added2SS);

		ArrayList<SolarSystemDTO> found = dao.getAllSolarSystems(0, 15, "");

		assertEquals(2, found.size());
	}

	
	@Test
	public void testClosestMarketsToPlanet() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO solarSystemDao = factory.getSolarSystemDAO();
		assertNotNull(solarSystemDao);
		
		URL solarSystemBatch = this.getClass().getResource("/ExportSolarSystemSmall.csv");
		File solarSystemBatchFile = new File(solarSystemBatch.toURI());
		FileReader solarSystemFR = new FileReader(solarSystemBatchFile);
		BufferedReader solarSystemBR = new BufferedReader(solarSystemFR);
		String strLine;
		ArrayList<SolarSystemDTO> solarSystemCache = new ArrayList<SolarSystemDTO>();
		strLine = solarSystemBR.readLine(); // skip first line
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

		PlanetDAO planetDao = factory.getPlanetDAO();
		assertNotNull(planetDao);

		URL planetBatch = this.getClass().getResource("/ExportPlanetSmallNew.csv");
		File planetBatchFile = new File(planetBatch.toURI());
		FileReader planetFR = new FileReader(planetBatchFile);
		BufferedReader planetBR = new BufferedReader(planetFR);
		ArrayList<PlanetDTO> planetCache = new ArrayList<PlanetDTO>();
		strLine = planetBR.readLine(); // skip first line
		while ((strLine = planetBR.readLine()) != null) {
			try {
				planetCache.add(DelimitedToRecord.toPlanet(strLine));
			} catch (TransformationException e) {
				System.out.println(e);
			}
		}
		planetBR.close();
		planetFR.close();

		planetDao.bulkAddPlanet(planetCache);

		URL closestBatch = this.getClass().getResource("/ExportClosestSmall.csv");
		File closestBatchFile = new File(closestBatch.toURI());
		FileReader closestFR = new FileReader(closestBatchFile);
		BufferedReader closestBR = new BufferedReader(closestFR);
		ArrayList<ClosestDTO> closestCache = new ArrayList<ClosestDTO>();
		strLine = closestBR.readLine(); // skip first line
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
		assertNotNull(marketDAO);

		URL marketBatch = this.getClass().getResource("/ExportMarketSmall.csv");
		File marketBatchFile = new File(marketBatch.toURI());
		FileReader marketFR = new FileReader(marketBatchFile);
		BufferedReader marketBR = new BufferedReader(marketFR);
		ArrayList<MarketDTO> marketCache = new ArrayList<MarketDTO>();
		int line = 0;
		strLine = marketBR.readLine(); // skip first line
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

		assertEquals(2, shipDAO.getAllShips().size());

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
	public void testSolarSystemFilter() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);
		addLotsOfSolarSystemRecords(dao);

		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("name");
		filter.setValue("10");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<SolarSystemDTO> found = dao.getAllSolarSystems(0, 2000, filterAll, "name");
		assertTrue("Could not find any records on sort", found.size() > 0);
		assertEquals(found.get(0).getName(), String.valueOf(10));
		assertTrue(found.size() > 0);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testSolarSystemSort() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);
		addLotsOfSolarSystemRecords(dao);

		ArrayList<SolarSystemDTO> found = dao.getAllSolarSystems(0, 2000, "-name");
		//ArrayList<SolarSystemDTO> found = dao.getAllSolarSystems();
		assertTrue("Could not find any records on sort", found.size() > 0);
		assertNotSame(1L, found.get(0).getId());

	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testSolarSystemRemove() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);

		SolarSystemDTO solarSystem = new SolarSystemDTO();
		assertNotNull(solarSystem);
		solarSystem.setName(String.valueOf(50));

		SolarSystem addedSS = dao.addSolarSystem(solarSystem);
		assertNotNull(addedSS);

		boolean removed = dao.removeSolarSystem(solarSystem);

		assertTrue(removed);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testSolarSystemTotal() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);

		SolarSystemDTO solarSystem = new SolarSystemDTO();
		assertNotNull(solarSystem);
		solarSystem.setName(String.valueOf(50));
		SolarSystemDTO solarSystem2 = new SolarSystemDTO();
		assertNotNull(solarSystem2);
		solarSystem2.setName(String.valueOf(50));

		SolarSystem addedSS = dao.addSolarSystem(solarSystem);
		assertNotNull(addedSS);
		SolarSystem added2SS = dao.addSolarSystem(solarSystem2);
		assertNotNull(added2SS);

		int total = dao.getTotalSolarSystems();
		assertEquals(2, total);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testSolarSystemUpdate() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);

		SolarSystemDTO solarSystem = new SolarSystemDTO();
		assertNotNull(solarSystem);
		solarSystem.setName(String.valueOf(50));

		SolarSystem addedSS = dao.addSolarSystem(solarSystem);
		assertNotNull(addedSS);

		solarSystem.setName(String.valueOf(55));
		SolarSystem updatedSolarSystem = dao.updateSolarSystem(solarSystem);
		assertEquals(addedSS.getName(), updatedSolarSystem.getName());
		assertNotNull(updatedSolarSystem.getId());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetAllSolarSystems() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);
		addLotsOfSolarSystemRecords(dao);

		int total = dao.getTotalSolarSystems();

		ArrayList<SolarSystemDTO> found = dao.getAllSolarSystems();

		assertEquals(total, found.size());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testDeleteAllSolarSystems() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);
		addLotsOfSolarSystemRecords(dao);

		int total = dao.getTotalSolarSystems();
		assertTrue(total > 0);
		dao.deleteAllSolarSystems();

		ArrayList<SolarSystemDTO> found = dao.getAllSolarSystems();

		assertEquals(0, found.size());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetTotalSolarSystemsWithFilter() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);
		addLotsOfSolarSystemRecords(dao);

		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("name");
		filter.setValue("5");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<SolarSystemDTO> found = dao.getAllSolarSystems(0, 2000, filterAll, "name");
		assertTrue("Could not find any records on sort", found.size() > 0);
		assertEquals(found.get(0).getName(), String.valueOf(5));
		assertTrue((found.size() > 0));
		
		int foundCount = dao.getTotalSolarSystemsWithFilter(filterAll);
		
		assertEquals(found.size(), foundCount);
	}

	
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetTotalSolarSystemsWithAllFilters() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);
		addLotsOfSolarSystemRecords(dao);

		Reflections reflections = 
				new Reflections(new ConfigurationBuilder()
					.setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
					.setUrls(ClasspathHelper.forPackage("com.qagwaai.starmalaccamax.shared.model"))
					.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO"))));
		Set<Class<? extends Object>> classes = reflections.getSubTypesOf(Object.class);
		ArrayList<Class<? extends Object>> sortedList = new ArrayList<Class<? extends Object>>(classes);
		Collections.sort(sortedList, new Comparator<Class<? extends Object>>(){

			@Override
			public int compare(Class<? extends Object> o1,
					Class<? extends Object> o2) {
				String str1 = o1.getName();
				String str2 = o2.getName();
				int x = String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
				if (x == 0) {
					x = str1.compareTo(str2);
				}
				return x;
			}});
		
		for (Class clazz : sortedList) {
			Set<Field> fields = getAllFields(clazz, withModifier(Modifier.PRIVATE), withAnnotation(com.googlecode.objectify.annotation.Index.class));
			for (Field field : fields) {
				
				Class returnType = field.getType();
				if (returnType.isPrimitive() || returnType.getName().equalsIgnoreCase("java.lang.String") || returnType.getName().equalsIgnoreCase("java.lang.Long")) {
					// continue
				} else {
					// next
					continue;
				}
				SimpleFilterItem filter = new SimpleFilterItem();
				filter.setField(field.getName());
				filter.setValue("5");
				ArrayList<Filter> filterAll = new ArrayList<Filter>();
				filterAll.add(filter);

				ArrayList<SolarSystemDTO> found = null;
				if (field.getName().equals("id")) {
					found = dao.getAllSolarSystems(0, 2000, filterAll, "__key__");
				} else {
					found = dao.getAllSolarSystems(0, 2000, filterAll, field.getName());
				}
				
				Method getValueMethod = clazz.getMethod(SolarSystemDTO.getFieldGetter(field.getName()), null);
				Object fieldObject = getValueMethod.invoke(found.get(0), null);
				//Object fieldObject = field.get(found.get(0));
				if (field.getName().equals("x") || field.getName().equals("y") || field.getName().equals("z")) {
					assertEquals(field.getName() + " search does not exist", Double.valueOf(5), fieldObject);
				} else if (field.getName().equals("name")) {
					assertEquals(field.getName() + " search does not exist", String.valueOf(5), fieldObject);
				} else {
					assertEquals(field.getName() + " search does not exist", Long.valueOf(5), fieldObject);
				}
				//assertTrue((found.size() == 1));
				
				int foundCount = dao.getTotalSolarSystemsWithFilter(filterAll);
				
				assertEquals(found.size(), foundCount);				
			}
		}

	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testClosestCreateAndLoad() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);

		ClosestDTO closest = new ClosestDTO();
		assertNotNull(closest);
		closest.setSolarSystemId(Long.valueOf(1));

		Closest addedClosest = dao.addClosest(closest);
		assertNotNull(addedClosest);

		Closest foundClosest = dao.getClosest(addedClosest.getId());
		assertNotNull(foundClosest);
		assertNotNull(addedClosest.getId());
		assertEquals(foundClosest.getId(), addedClosest.getId());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetClosests() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);

		ClosestDTO closest = new ClosestDTO();
		assertNotNull(closest);
		closest.setSolarSystemId(Long.valueOf(1));
		ClosestDTO closest2 = new ClosestDTO();
		assertNotNull(closest2);
		closest2.setSolarSystemId(Long.valueOf(2));

		Closest addedSS = dao.addClosest(closest);
		assertNotNull(addedSS);
		Closest added2SS = dao.addClosest(closest2);
		assertNotNull(added2SS);

		ArrayList<ClosestDTO> found = dao.getAllClosests(0, 15, "");

		assertEquals(2, found.size());
	}

	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testClosestFilter() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);
		addLotsOfClosestRecords(dao);

		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("originId");
		filter.setValue("10");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<ClosestDTO> found = dao.getAllClosests(0, 2000, filterAll, "originId");
		assertTrue("Could not find any records on sort", found.size() > 0);
		assertEquals(found.get(0).getSolarSystemId(), Long.valueOf(10));
		assertTrue(found.size() > 0);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testClosestSort() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);
		addLotsOfClosestRecords(dao);

		ArrayList<ClosestDTO> found = dao.getAllClosests(0, 2000, "-originId");
		//ArrayList<ClosestDTO> found = dao.getAllClosests();
		assertTrue("Could not find any records on sort", found.size() > 0);
		assertNotSame(1L, found.get(0).getId());

	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testClosestRemove() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);

		ClosestDTO closest = new ClosestDTO();
		assertNotNull(closest);
		closest.setSolarSystemId(Long.valueOf(50));

		Closest addedSS = dao.addClosest(closest);
		assertNotNull(addedSS);

		boolean removed = dao.removeClosest(closest);

		assertTrue(removed);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testClosestTotal() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);

		ClosestDTO closest = new ClosestDTO();
		assertNotNull(closest);
		closest.setSolarSystemId(Long.valueOf(50));
		ClosestDTO closest2 = new ClosestDTO();
		assertNotNull(closest2);
		closest2.setSolarSystemId(Long.valueOf(50));

		Closest addedSS = dao.addClosest(closest);
		assertNotNull(addedSS);
		Closest added2SS = dao.addClosest(closest2);
		assertNotNull(added2SS);

		int total = dao.getTotalClosests();
		assertEquals(2, total);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testClosestUpdate() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);

		ClosestDTO closest = new ClosestDTO();
		assertNotNull(closest);
		closest.setSolarSystemId(Long.valueOf(50));

		Closest addedSS = dao.addClosest(closest);
		assertNotNull(addedSS);

		closest.setSolarSystemId(Long.valueOf(55));
		Closest updatedClosest = dao.updateClosest(closest);
		assertEquals(addedSS.getSolarSystemId(), updatedClosest.getSolarSystemId());
		assertNotNull(updatedClosest.getId());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetAllClosests() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);
		addLotsOfClosestRecords(dao);

		int total = dao.getTotalClosests();

		ArrayList<ClosestDTO> found = dao.getAllClosests();

		assertEquals(total, found.size());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testDeleteAllClosests() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);
		addLotsOfClosestRecords(dao);

		int total = dao.getTotalClosests();
		assertTrue(total > 0);
		dao.deleteAllClosest();

		ArrayList<ClosestDTO> found = dao.getAllClosests();

		assertEquals(0, found.size());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetTotalClosestsWithFilter() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);
		addLotsOfClosestRecords(dao);

		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("originId");
		filter.setValue("5");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<ClosestDTO> found = dao.getAllClosests(0, 2000, filterAll, "originId");
		assertTrue("Could not find any records on sort", found.size() > 0);
		assertEquals(found.get(0).getSolarSystemId(), Long.valueOf(5));
		assertTrue((found.size() > 0));
		
		int foundCount = dao.getTotalClosestsWithFilter(filterAll);
		
		assertEquals(found.size(), foundCount);
	}

	
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetTotalClosestsWithAllFilters() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		SolarSystemDAO dao = factory.getSolarSystemDAO();
		assertNotNull(dao);
		addLotsOfClosestRecords(dao);

		Reflections reflections = 
				new Reflections(new ConfigurationBuilder()
					.setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
					.setUrls(ClasspathHelper.forPackage("com.qagwaai.starmalaccamax.shared.model"))
					.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("com.qagwaai.starmalaccamax.shared.model.ClosestDTO"))));
		Set<Class<? extends Object>> classes = reflections.getSubTypesOf(Object.class);
		ArrayList<Class<? extends Object>> sortedList = new ArrayList<Class<? extends Object>>(classes);
		Collections.sort(sortedList, new Comparator<Class<? extends Object>>(){

			@Override
			public int compare(Class<? extends Object> o1,
					Class<? extends Object> o2) {
				String str1 = o1.getName();
				String str2 = o2.getName();
				int x = String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
				if (x == 0) {
					x = str1.compareTo(str2);
				}
				return x;
			}});
		
		for (Class clazz : sortedList) {
			Set<Field> fields = getAllFields(clazz, withModifier(Modifier.PRIVATE), withAnnotation(com.googlecode.objectify.annotation.Index.class));
			for (Field field : fields) {
				
				Class returnType = field.getType();
				if (returnType.isPrimitive() || returnType.getName().equalsIgnoreCase("java.lang.String") || returnType.getName().equalsIgnoreCase("java.lang.Long")) {
					// continue
				} else {
					// next
					continue;
				}
				SimpleFilterItem filter = new SimpleFilterItem();
				filter.setField(field.getName());
				filter.setValue("5");
				ArrayList<Filter> filterAll = new ArrayList<Filter>();
				filterAll.add(filter);

				ArrayList<ClosestDTO> found = null;
				if (field.getName().equals("id")) {
					found = dao.getAllClosests(0, 2000, filterAll, "__key__");
				} else {
					found = dao.getAllClosests(0, 2000, filterAll, field.getName());
				}
				
				Method getValueMethod = clazz.getMethod(ClosestDTO.getFieldGetter(field.getName()), null);
				Object fieldObject = getValueMethod.invoke(found.get(0), null);
				//Object fieldObject = field.get(found.get(0));
				if (field.getName().equals("x") || field.getName().equals("y") || field.getName().equals("z")) {
					assertEquals(field.getName() + " search does not exist", Double.valueOf(5), fieldObject);
				} else if (field.getName().equals("name")) {
					assertEquals(field.getName() + " search does not exist", String.valueOf(5), fieldObject);
				} else {
					assertEquals(field.getName() + " search does not exist", Long.valueOf(5), fieldObject);
				}
				//assertTrue((found.size() == 1));
				
				int foundCount = dao.getTotalClosestsWithFilter(filterAll);
				
				assertEquals(found.size(), foundCount);				
			}
		}

	}

}
