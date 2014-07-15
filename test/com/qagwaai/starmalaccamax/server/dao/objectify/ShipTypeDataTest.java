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
import com.qagwaai.starmalaccamax.server.dao.ShipTypeDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.ShipType;
import com.qagwaai.starmalaccamax.shared.model.ShipTypeDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

/**
 * @author pgirard
 * 
 */
public final class ShipTypeDataTest {
	
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
	private void addLotsOfRecords(final ShipTypeDAO dao) throws Exception {
		int ownerId = 0;
		for (int i = 0; i < 100; i++) {
			ShipTypeDTO shipType = new ShipTypeDTO();
			shipType.setName(String.valueOf(ownerId++));
			assertNotNull(shipType);
			dao.addShipType(shipType);
			//if (ownerId > 15) {
			//	ownerId = 0;
			//}
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
		ShipTypeDAO dao = factory.getShipTypeDAO();
		assertNotNull(dao);

		ShipTypeDTO shipType = new ShipTypeDTO();
		assertNotNull(shipType);
		shipType.setName(String.valueOf(1));

		ShipType addedShipType = dao.addShipType(shipType);
		assertNotNull(addedShipType);

		ShipType foundShipType = dao.getShipType(addedShipType.getId());
		assertNotNull(foundShipType);
		assertNotNull(addedShipType.getId());
		assertEquals(foundShipType.getId(), addedShipType.getId());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetShipTypes() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		ShipTypeDAO dao = factory.getShipTypeDAO();
		assertNotNull(dao);

		ShipTypeDTO shipType = new ShipTypeDTO();
		assertNotNull(shipType);
		shipType.setName(String.valueOf(1));
		ShipTypeDTO ship2 = new ShipTypeDTO();
		assertNotNull(ship2);
		ship2.setName(String.valueOf(2));

		ShipType addedSS = dao.addShipType(shipType);
		assertNotNull(addedSS);
		ShipType added2SS = dao.addShipType(ship2);
		assertNotNull(added2SS);

		ArrayList<ShipTypeDTO> found = dao.getAllShipTypes(0, 15, "");

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
		ShipTypeDAO dao = factory.getShipTypeDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("name");
		filter.setValue("10");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<ShipTypeDTO> found = dao.getAllShipTypes(0, 2000, filterAll, "name");
		assertTrue("Could not find any records on sort", found.size() > 0);
		assertEquals(String.valueOf(10), found.get(0).getName());
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
		ShipTypeDAO dao = factory.getShipTypeDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		ArrayList<ShipTypeDTO> found = dao.getAllShipTypes(0, 2000, "-name");
		//ArrayList<ShipTypeDTO> found = dao.getAllShipTypes();
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
		ShipTypeDAO dao = factory.getShipTypeDAO();
		assertNotNull(dao);

		ShipTypeDTO shipType = new ShipTypeDTO();
		assertNotNull(shipType);
		shipType.setName(String.valueOf(50));

		ShipType addedSS = dao.addShipType(shipType);
		assertNotNull(addedSS);

		boolean removed = dao.removeShipType(shipType);

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
		ShipTypeDAO dao = factory.getShipTypeDAO();
		assertNotNull(dao);

		ShipTypeDTO shipType = new ShipTypeDTO();
		assertNotNull(shipType);
		shipType.setName(String.valueOf(50));
		ShipTypeDTO ship2 = new ShipTypeDTO();
		assertNotNull(ship2);
		ship2.setName(String.valueOf(50));

		ShipType addedSS = dao.addShipType(shipType);
		assertNotNull(addedSS);
		ShipType added2SS = dao.addShipType(ship2);
		assertNotNull(added2SS);

		int total = dao.getTotalShipTypes();
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
		ShipTypeDAO dao = factory.getShipTypeDAO();
		assertNotNull(dao);

		ShipTypeDTO shipType = new ShipTypeDTO();
		assertNotNull(shipType);
		shipType.setName(String.valueOf(50));

		ShipType addedSS = dao.addShipType(shipType);
		assertNotNull(addedSS);

		shipType.setName(String.valueOf(55));
		ShipType updatedShipType = dao.updateShipType(shipType);
		assertEquals(addedSS.getName(), updatedShipType.getName());
		assertNotNull(updatedShipType.getId());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetAllShipTypes() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		ShipTypeDAO dao = factory.getShipTypeDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		int total = dao.getTotalShipTypes();

		ArrayList<ShipTypeDTO> found = dao.getAllShipTypes();

		assertEquals(total, found.size());
	}
	
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testDeleteAllShipTypes() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		ShipTypeDAO dao = factory.getShipTypeDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		int total = dao.getTotalShipTypes();
		assertTrue(total > 0);
		dao.deleteAllShipTypes();

		ArrayList<ShipTypeDTO> found = dao.getAllShipTypes();

		assertEquals(0, found.size());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetTotalShipTypesWithFilter() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		ShipTypeDAO dao = factory.getShipTypeDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("name");
		filter.setValue("5");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<ShipTypeDTO> found = dao.getAllShipTypes(0, 2000, filterAll, "name");
		assertTrue("Could not find any records on sort", found.size() > 0);
		assertEquals(found.get(0).getName(), String.valueOf(5));
		assertTrue((found.size() > 0));
		
		int foundCount = dao.getTotalShipTypesWithFilter(filterAll);
		
		assertEquals(found.size(), foundCount);
	}

	
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetTotalShipTypesWithAllFilters() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		ShipTypeDAO dao = factory.getShipTypeDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		Reflections reflections = 
				new Reflections(new ConfigurationBuilder()
					.setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
					.setUrls(ClasspathHelper.forPackage("com.qagwaai.starmalaccamax.shared.model"))
					.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("com.qagwaai.starmalaccamax.shared.model.ShipTypeDTO"))));
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

				ArrayList<ShipTypeDTO> found = null;
				if (field.getName().equals("id")) {
					found = dao.getAllShipTypes(0, 2000, filterAll, "__key__");
				} else {
					found = dao.getAllShipTypes(0, 2000, filterAll, field.getName());
				}
				
				Method getValueMethod = clazz.getMethod(ShipTypeDTO.getFieldGetter(field.getName()), null);
				Object fieldObject = getValueMethod.invoke(found.get(0), null);
				//Object fieldObject = field.get(found.get(0));
				if (field.getName().equals("name")) {
					assertEquals(field.getName() + " search does not exist", String.valueOf(5), fieldObject);	
				} else {
					assertEquals(field.getName() + " search does not exist", Long.valueOf(5), fieldObject);
				}
				//assertTrue((found.size() == 1));
				
				int foundCount = dao.getTotalShipTypesWithFilter(filterAll);
				
				assertEquals(found.size(), foundCount);				
			}
		}

	}
}
