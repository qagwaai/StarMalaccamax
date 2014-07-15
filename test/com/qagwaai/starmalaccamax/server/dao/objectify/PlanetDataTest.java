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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

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
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.PlanetDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.Planet;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

/**
 * @author pgirard
 * 
 */
public final class PlanetDataTest {
	
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
	private void addLotsOfRecords(final PlanetDAO dao) throws Exception {
		int ownerId = 0;
		for (int i = 0; i < 100; i++) {
			PlanetDTO planet = new PlanetDTO();
			planet.setSolarSystemId(Long.valueOf(ownerId++));
			planet.setGasGiant(((ownerId & 1) == 0));
			assertNotNull(planet);
			dao.addPlanet(planet);
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
	public void testCreateAndLoad() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		PlanetDAO dao = factory.getPlanetDAO();
		assertNotNull(dao);

		PlanetDTO planet = new PlanetDTO();
		assertNotNull(planet);
		planet.setSolarSystemId(Long.valueOf(1));

		Planet addedPlanet = dao.addPlanet(planet);
		assertNotNull(addedPlanet);

		Planet foundPlanet = dao.getPlanet(addedPlanet.getId());
		assertNotNull(foundPlanet);
		assertNotNull(addedPlanet.getId());
		assertEquals(foundPlanet.getId(), addedPlanet.getId());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetPlanets() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		PlanetDAO dao = factory.getPlanetDAO();
		assertNotNull(dao);

		PlanetDTO planet = new PlanetDTO();
		assertNotNull(planet);
		planet.setSolarSystemId(Long.valueOf(1));
		PlanetDTO planet2 = new PlanetDTO();
		assertNotNull(planet2);
		planet2.setSolarSystemId(Long.valueOf(2));

		Planet addedSS = dao.addPlanet(planet);
		assertNotNull(addedSS);
		Planet added2SS = dao.addPlanet(planet2);
		assertNotNull(added2SS);

		ArrayList<PlanetDTO> found = dao.getAllPlanets(0, 15, "");

		assertEquals(2, found.size());
	}

	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testFilter() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		PlanetDAO dao = factory.getPlanetDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("solarSystemId");
		filter.setValue("10");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<PlanetDTO> found = dao.getAllPlanets(0, 2000, filterAll, "solarSystemId");
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
	public void testSort() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		PlanetDAO dao = factory.getPlanetDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		ArrayList<PlanetDTO> found = dao.getAllPlanets(0, 2000, "-solarSystemId");
		//ArrayList<PlanetDTO> found = dao.getAllPlanets();
		assertTrue("Could not find any records on sort", found.size() > 0);
		assertNotSame(1L, found.get(0).getId());

	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testRemove() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		PlanetDAO dao = factory.getPlanetDAO();
		assertNotNull(dao);

		PlanetDTO planet = new PlanetDTO();
		assertNotNull(planet);
		planet.setSolarSystemId(Long.valueOf(50));

		Planet addedSS = dao.addPlanet(planet);
		assertNotNull(addedSS);

		boolean removed = dao.removePlanet(planet);

		assertTrue(removed);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testTotal() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		PlanetDAO dao = factory.getPlanetDAO();
		assertNotNull(dao);

		PlanetDTO planet = new PlanetDTO();
		assertNotNull(planet);
		planet.setSolarSystemId(Long.valueOf(50));
		PlanetDTO planet2 = new PlanetDTO();
		assertNotNull(planet2);
		planet2.setSolarSystemId(Long.valueOf(50));

		Planet addedSS = dao.addPlanet(planet);
		assertNotNull(addedSS);
		Planet added2SS = dao.addPlanet(planet2);
		assertNotNull(added2SS);

		int total = dao.getTotalPlanets();
		assertEquals(2, total);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testUpdate() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		PlanetDAO dao = factory.getPlanetDAO();
		assertNotNull(dao);

		PlanetDTO planet = new PlanetDTO();
		assertNotNull(planet);
		planet.setSolarSystemId(Long.valueOf(50));

		Planet addedSS = dao.addPlanet(planet);
		assertNotNull(addedSS);

		planet.setSolarSystemId(Long.valueOf(55));
		Planet updatedPlanet = dao.updatePlanet(planet);
		assertEquals(addedSS.getSolarSystemId(), updatedPlanet.getSolarSystemId());
		assertNotNull(updatedPlanet.getId());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetAllPlanets() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		PlanetDAO dao = factory.getPlanetDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		int total = dao.getTotalPlanets();

		ArrayList<PlanetDTO> found = dao.getAllPlanets();

		assertEquals(total, found.size());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testDeleteAllPlanets() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		PlanetDAO dao = factory.getPlanetDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		int total = dao.getTotalPlanets();
		assertTrue(total > 0);
		dao.deleteAllPlanets();

		ArrayList<PlanetDTO> found = dao.getAllPlanets();

		assertEquals(0, found.size());
	}
	
	@Test 
	public void testGetPlanetsForSolarSystem() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		PlanetDAO dao = factory.getPlanetDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		ArrayList<PlanetDTO> found = dao.getPlanetsForSolarSystem(25L);
		assertTrue(found.size() > 0);
	}
	
	@Test
	public void testGetNonGasGiantPlanetsForSolarSystem() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		PlanetDAO dao = factory.getPlanetDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		ArrayList<PlanetDTO> found = dao.getNonGasGiantPlanetsForSolarSystem(10L);
		assertTrue(found.size() > 0);
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetTotalPlanetsWithFilter() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		PlanetDAO dao = factory.getPlanetDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("solarSystemId");
		filter.setValue("5");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<PlanetDTO> found = dao.getAllPlanets(0, 2000, filterAll, "solarSystemId");
		assertTrue("Could not find any records on sort", found.size() > 0);
		assertEquals(found.get(0).getSolarSystemId(), Long.valueOf(5));
		assertTrue((found.size() > 0));
		
		int foundCount = dao.getTotalPlanetsWithFilter(filterAll);
		
		assertEquals(found.size(), foundCount);
	}

	
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetTotalPlanetsWithAllFilters() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		PlanetDAO dao = factory.getPlanetDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		Reflections reflections = 
				new Reflections(new ConfigurationBuilder()
					.setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
					.setUrls(ClasspathHelper.forPackage("com.qagwaai.starmalaccamax.shared.model"))
					.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("com.qagwaai.starmalaccamax.shared.model.PlanetDTO"))));
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
				if (field.getName().equalsIgnoreCase("isGasGiant")) {
					filter.setValue("true");
				} else {
					filter.setValue("5");
				}
				ArrayList<Filter> filterAll = new ArrayList<Filter>();
				filterAll.add(filter);

				ArrayList<PlanetDTO> found = null;
				if (field.getName().equals("id")) {
					found = dao.getAllPlanets(0, 2000, filterAll, "__key__");
				} else {
					found = dao.getAllPlanets(0, 2000, filterAll, field.getName());
				}
				
				Method getValueMethod = clazz.getMethod(PlanetDTO.getFieldGetter(field.getName()), null);
				Object fieldObject = getValueMethod.invoke(found.get(0), null);
				//Object fieldObject = field.get(found.get(0));
				if (field.getName().equalsIgnoreCase("isGasGiant")) {
					assertEquals(field.getName() + " search does not exist", true, fieldObject);
				} else {
					assertEquals(field.getName() + " search does not exist", Long.valueOf(5), fieldObject);
				}
				//assertTrue((found.size() == 1));
				
				int foundCount = dao.getTotalPlanetsWithFilter(filterAll);
				
				assertEquals(found.size(), foundCount);				
			}
		}

	}
}
