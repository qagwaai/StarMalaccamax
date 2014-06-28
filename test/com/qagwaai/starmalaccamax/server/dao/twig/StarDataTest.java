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
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.StarDAO;
import com.qagwaai.starmalaccamax.shared.TransformationException;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;
import com.qagwaai.starmalaccamax.shared.model.Star;
import com.qagwaai.starmalaccamax.shared.model.StarDTO;

/**
 * @author pgirard
 * 
 */
public final class StarDataTest {
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
		StarDAO dao = factory.getStarDAO();
		Assert.assertNotNull(dao);

		URL batch = this.getClass().getResource("/Top_100_Star_Query.txt");
		File batchFile = new File(batch.toURI());
		FileReader fr = new FileReader(batchFile);
		BufferedReader br = new BufferedReader(fr);
		String strLine;
		ArrayList<StarDTO> cache = new ArrayList<StarDTO>();
		while ((strLine = br.readLine()) != null) {
			try {
				cache.add(DelimitedToRecord.toStar(strLine));
			} catch (TransformationException e) {
				System.out.println(e);
			}
		}
		br.close();
		fr.close();

		dao.bulkAddStar(cache);

		ArrayList<StarDTO> found = dao.getAllStars(0, 2000, "");

		Assert.assertEquals(cache.size(), found.size());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testCreateAndLoad() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		StarDAO dao = factory.getStarDAO();
		Assert.assertNotNull(dao);

		StarDTO ss = new StarDTO();
		Assert.assertNotNull(ss);
		ss.setSize("V");

		Star addedSS = dao.addStar(ss);
		Assert.assertNotNull(addedSS);

		Star foundSS = dao.getStar(addedSS.getId());
		Assert.assertNotNull(foundSS);
		Assert.assertEquals(foundSS.getId(), addedSS.getId());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetStars() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		StarDAO dao = factory.getStarDAO();
		Assert.assertNotNull(dao);

		StarDTO ss = new StarDTO();
		Assert.assertNotNull(ss);
		ss.setSize("V");
		StarDTO ss2 = new StarDTO();
		Assert.assertNotNull(ss2);
		ss2.setSize("IV");

		Star addedSS = dao.addStar(ss);
		Assert.assertNotNull(addedSS);
		Star added2SS = dao.addStar(ss2);
		Assert.assertNotNull(added2SS);

		ArrayList<StarDTO> found = dao.getAllStars(0, 15, "");

		Assert.assertEquals(2, found.size());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testRemove() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		StarDAO dao = factory.getStarDAO();
		Assert.assertNotNull(dao);

		StarDTO ss = new StarDTO();
		Assert.assertNotNull(ss);
		ss.setSize("V");

		Star addedSS = dao.addStar(ss);
		Assert.assertNotNull(addedSS);

		boolean removed = dao.removeStar(ss);

		Assert.assertTrue(removed);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testSizeFilter() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		StarDAO dao = factory.getStarDAO();
		Assert.assertNotNull(dao);

		URL batch = this.getClass().getResource("/Top_100_Star_Query.txt");
		File batchFile = new File(batch.toURI());
		FileReader fr = new FileReader(batchFile);
		BufferedReader br = new BufferedReader(fr);
		String strLine;
		ArrayList<StarDTO> cache = new ArrayList<StarDTO>();
		while ((strLine = br.readLine()) != null) {
			try {
				cache.add(DelimitedToRecord.toStar(strLine));
			} catch (TransformationException e) {
				System.out.println(e);
			}
		}
		br.close();
		fr.close();

		dao.bulkAddStar(cache);
		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("size");
		filter.setValue("D");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<StarDTO> found = dao.getAllStars(0, 2000, filterAll, "size");
		Assert.assertNotSame(cache.get(0).getId(), found.get(0).getId());
		Assert.assertEquals(found.get(0).getSize(), "D");
		Assert.assertEquals(found.size(), 4);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testSizeSort() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		StarDAO dao = factory.getStarDAO();
		Assert.assertNotNull(dao);

		URL batch = this.getClass().getResource("/Top_100_Star_Query.txt");
		File batchFile = new File(batch.toURI());
		FileReader fr = new FileReader(batchFile);
		BufferedReader br = new BufferedReader(fr);
		String strLine;
		ArrayList<StarDTO> cache = new ArrayList<StarDTO>();
		while ((strLine = br.readLine()) != null) {
			try {
				cache.add(DelimitedToRecord.toStar(strLine));
			} catch (TransformationException e) {
				System.out.println(e);
			}
		}
		br.close();
		fr.close();

		dao.bulkAddStar(cache);

		ArrayList<StarDTO> found = dao.getAllStars(0, 2000, "size");
		Assert.assertNotSame(cache.get(0).getId(), found.get(0).getId());

	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testTotal() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		StarDAO dao = factory.getStarDAO();
		Assert.assertNotNull(dao);

		StarDTO ss = new StarDTO();
		Assert.assertNotNull(ss);
		ss.setSize("V");
		StarDTO ss2 = new StarDTO();
		Assert.assertNotNull(ss2);
		ss2.setSize("IV");

		Star addedSS = dao.addStar(ss);
		Assert.assertNotNull(addedSS);
		Star added2SS = dao.addStar(ss2);
		Assert.assertNotNull(added2SS);

		int total = dao.getTotalStars();
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
		StarDAO dao = factory.getStarDAO();
		Assert.assertNotNull(dao);

		StarDTO ss = new StarDTO();
		Assert.assertNotNull(ss);
		ss.setSize("V");

		Star addedSS = dao.addStar(ss);
		Assert.assertNotNull(addedSS);

		ss.setSize("IV");
		Star updatedSS = dao.updateStar(ss);
		Assert.assertEquals(addedSS.getSize(), updatedSS.getSize());
	}

}
