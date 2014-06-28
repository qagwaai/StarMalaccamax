/**
 * ModelTest.java
 * Created by pgirard at 1:57:30 PM on Aug 11, 2010
 * in the com.qagwaai.starmalaccamax.server package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao.objectify;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.qagwaai.starmalaccamax.server.dao.CaptainDAO;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.shared.model.Captain;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public final class CaptainDataTest {
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
	private void addLotsOfRecords(final CaptainDAO dao) throws Exception {
		int ownerId = 0;
		for (int i = 0; i < 100; i++) {
			CaptainDTO ss = new CaptainDTO();
			ss.setOwnerId(Long.valueOf(ownerId++));
			Assert.assertNotNull(ss);
			ss.setName("tester" + i);
			dao.addCaptain(ss);
			if (ownerId > 5) {
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
		CaptainDAO dao = factory.getCaptainDAO();
		Assert.assertNotNull(dao);

		CaptainDTO ss = new CaptainDTO();
		Assert.assertNotNull(ss);
		ss.setName("tester1");

		Captain addedSS = dao.addCaptain(ss);
		Assert.assertNotNull(addedSS);

		Captain foundSS = dao.getCaptain(addedSS.getId());
		Assert.assertNotNull(foundSS);
		Assert.assertNotNull(addedSS.getId());
		Assert.assertEquals(foundSS.getId(), addedSS.getId());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetCaptains() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		CaptainDAO dao = factory.getCaptainDAO();
		Assert.assertNotNull(dao);

		CaptainDTO ss = new CaptainDTO();
		Assert.assertNotNull(ss);
		ss.setName("tester1");
		CaptainDTO ss2 = new CaptainDTO();
		Assert.assertNotNull(ss2);
		ss2.setName("tester2");

		Captain addedSS = dao.addCaptain(ss);
		Assert.assertNotNull(addedSS);
		Captain added2SS = dao.addCaptain(ss2);
		Assert.assertNotNull(added2SS);

		ArrayList<CaptainDTO> found = dao.getAllCaptains(0, 15, "");

		Assert.assertEquals(2, found.size());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testNameFilter() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		CaptainDAO dao = factory.getCaptainDAO();
		Assert.assertNotNull(dao);
		addLotsOfRecords(dao);

		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("name");
		filter.setValue("tester50");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<CaptainDTO> found = dao.getAllCaptains(0, 2000, filterAll, "name");
		Assert.assertNotSame("tester1", found.get(0).getName());
		Assert.assertEquals(found.get(0).getName(), "tester50");
		Assert.assertEquals(found.size(), 1);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testNameSort() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		CaptainDAO dao = factory.getCaptainDAO();
		Assert.assertNotNull(dao);
		addLotsOfRecords(dao);

		ArrayList<CaptainDTO> found = dao.getAllCaptains(0, 2000, "-name");
		Assert.assertNotSame(1L, found.get(0).getId());

	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testRemove() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		CaptainDAO dao = factory.getCaptainDAO();
		Assert.assertNotNull(dao);

		CaptainDTO ss = new CaptainDTO();
		Assert.assertNotNull(ss);
		ss.setName("tester1");

		Captain addedSS = dao.addCaptain(ss);
		Assert.assertNotNull(addedSS);

		boolean removed = dao.removeCaptain(ss);

		Assert.assertTrue(removed);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testTotal() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		CaptainDAO dao = factory.getCaptainDAO();
		Assert.assertNotNull(dao);

		CaptainDTO ss = new CaptainDTO();
		Assert.assertNotNull(ss);
		ss.setName("tester1");
		CaptainDTO ss2 = new CaptainDTO();
		Assert.assertNotNull(ss2);
		ss2.setName("tester2");

		Captain addedSS = dao.addCaptain(ss);
		Assert.assertNotNull(addedSS);
		Captain added2SS = dao.addCaptain(ss2);
		Assert.assertNotNull(added2SS);

		int total = dao.getTotalCaptains();
		Assert.assertEquals(2, total);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testUpdate() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		CaptainDAO dao = factory.getCaptainDAO();
		Assert.assertNotNull(dao);

		CaptainDTO ss = new CaptainDTO();
		Assert.assertNotNull(ss);
		ss.setName("tester1");

		Captain addedSS = dao.addCaptain(ss);
		Assert.assertNotNull(addedSS);

		ss.setName("tester2");
		Captain updatedSS = dao.updateCaptain(ss);
		Assert.assertEquals(addedSS.getName(), updatedSS.getName());
		Assert.assertNotNull(updatedSS.getId());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetAllCaptains() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		CaptainDAO dao = factory.getCaptainDAO();
		Assert.assertNotNull(dao);
		addLotsOfRecords(dao);

		int total = dao.getTotalCaptains();

		ArrayList<CaptainDTO> found = dao.getAllCaptains();

		Assert.assertEquals(total, found.size());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetCaptainsForUser() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		CaptainDAO dao = factory.getCaptainDAO();
		Assert.assertNotNull(dao);
		addLotsOfRecords(dao);

		UserDTO user = new UserDTO();
		user.setId(Long.valueOf(3));
		ArrayList<CaptainDTO> found = dao.getCaptainsForUser(user);
		
		Assert.assertTrue(found.size() > 0);
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testDeleteAllCaptains() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		CaptainDAO dao = factory.getCaptainDAO();
		Assert.assertNotNull(dao);
		addLotsOfRecords(dao);

		int total = dao.getTotalCaptains();
		dao.deleteAllCaptains();

		ArrayList<CaptainDTO> found = dao.getAllCaptains();

		Assert.assertEquals(0, found.size());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetTotalCaptainsWithFilter() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		CaptainDAO dao = factory.getCaptainDAO();
		Assert.assertNotNull(dao);
		addLotsOfRecords(dao);

		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("name");
		filter.setValue("tester50");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<CaptainDTO> found = dao.getAllCaptains(0, 2000, filterAll, "name");
		Assert.assertNotSame("tester1", found.get(0).getName());
		Assert.assertEquals(found.get(0).getName(), "tester50");
		Assert.assertEquals(found.size(), 1);
		
		int foundCount = dao.getTotalCaptainsWithFilter(filterAll);
		
		Assert.assertEquals(found.size(), foundCount);
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testCaptainNameExists() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		CaptainDAO dao = factory.getCaptainDAO();
		Assert.assertNotNull(dao);
		addLotsOfRecords(dao);
		
		Assert.assertEquals(true, dao.captainNameExists("tester50"));
		Assert.assertEquals(false, dao.captainNameExists("asdf"));
	}
}
