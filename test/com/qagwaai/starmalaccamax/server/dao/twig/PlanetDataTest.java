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
import com.qagwaai.starmalaccamax.client.admin.util.DelimitedToRecord;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.PlanetDAO;
import com.qagwaai.starmalaccamax.shared.TransformationException;
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
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.TWIG);
		PlanetDAO dao = factory.getPlanetDAO();
		Assert.assertNotNull(dao);

		URL batch = this.getClass().getResource("/Top_100_Planet_Query.txt");
		File batchFile = new File(batch.toURI());
		FileReader fr = new FileReader(batchFile);
		BufferedReader br = new BufferedReader(fr);
		String strLine;
		ArrayList<PlanetDTO> cache = new ArrayList<PlanetDTO>();
		while ((strLine = br.readLine()) != null) {
			try {
				cache.add(DelimitedToRecord.toPlanet(strLine));
			} catch (TransformationException e) {
				System.out.println(e);
			}
		}
		br.close();
		fr.close();

		dao.bulkAddPlanet(cache);

		ArrayList<PlanetDTO> found = dao.getAllPlanets(0, 2000, "");

		Assert.assertEquals(cache.size(), found.size());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testCreateAndLoad() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.TWIG);
		PlanetDAO dao = factory.getPlanetDAO();
		Assert.assertNotNull(dao);

		PlanetDTO ss = new PlanetDTO();
		Assert.assertNotNull(ss);
		ss.setName("tester");

		Planet addedSS = dao.addPlanet(ss);
		Assert.assertNotNull(addedSS);

		Planet foundSS = dao.getPlanet(addedSS.getId());
		Assert.assertNotNull(foundSS);
		Assert.assertEquals(foundSS.getId(), addedSS.getId());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetPlanets() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.TWIG);
		PlanetDAO dao = factory.getPlanetDAO();
		Assert.assertNotNull(dao);

		PlanetDTO ss = new PlanetDTO();
		Assert.assertNotNull(ss);
		ss.setName("tester");
		PlanetDTO ss2 = new PlanetDTO();
		Assert.assertNotNull(ss2);
		ss2.setName("tester2");

		Planet addedSS = dao.addPlanet(ss);
		Assert.assertNotNull(addedSS);
		Planet added2SS = dao.addPlanet(ss2);
		Assert.assertNotNull(added2SS);

		ArrayList<PlanetDTO> found = dao.getAllPlanets(0, 15, "");

		Assert.assertEquals(2, found.size());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testNameFilter() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.TWIG);
		PlanetDAO dao = factory.getPlanetDAO();
		Assert.assertNotNull(dao);

		URL batch = this.getClass().getResource("/Top_100_Planet_Query.txt");
		File batchFile = new File(batch.toURI());
		FileReader fr = new FileReader(batchFile);
		BufferedReader br = new BufferedReader(fr);
		String strLine;
		ArrayList<PlanetDTO> cache = new ArrayList<PlanetDTO>();
		while ((strLine = br.readLine()) != null) {
			try {
				cache.add(DelimitedToRecord.toPlanet(strLine));
			} catch (TransformationException e) {
				System.out.println(e);
			}
		}
		br.close();
		fr.close();

		dao.bulkAddPlanet(cache);
		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("name");
		filter.setValue("Beers Hollow VIII");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<PlanetDTO> found = dao.getAllPlanets(0, 2000, filterAll, "name");
		Assert.assertNotSame(cache.get(0).getName(), found.get(0).getName());
		Assert.assertEquals(found.get(0).getName(), "Beers Hollow VIII");
		Assert.assertEquals(found.size(), 1);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testNameSort() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.TWIG);
		PlanetDAO dao = factory.getPlanetDAO();
		Assert.assertNotNull(dao);

		URL batch = this.getClass().getResource("/Top_100_Planet_Query.txt");
		File batchFile = new File(batch.toURI());
		FileReader fr = new FileReader(batchFile);
		BufferedReader br = new BufferedReader(fr);
		String strLine;
		ArrayList<PlanetDTO> cache = new ArrayList<PlanetDTO>();
		while ((strLine = br.readLine()) != null) {
			try {
				cache.add(DelimitedToRecord.toPlanet(strLine));
			} catch (TransformationException e) {
				System.out.println(e);
			}
		}
		br.close();
		fr.close();

		dao.bulkAddPlanet(cache);

		ArrayList<PlanetDTO> found = dao.getAllPlanets(0, 2000, "name");
		Assert.assertNotSame(cache.get(0).getName(), found.get(0).getName());

	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testRemove() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.TWIG);
		PlanetDAO dao = factory.getPlanetDAO();
		Assert.assertNotNull(dao);

		PlanetDTO ss = new PlanetDTO();
		Assert.assertNotNull(ss);
		ss.setName("tester");

		Planet addedSS = dao.addPlanet(ss);
		Assert.assertNotNull(addedSS);

		boolean removed = dao.removePlanet(ss);

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
		PlanetDAO dao = factory.getPlanetDAO();
		Assert.assertNotNull(dao);

		PlanetDTO ss = new PlanetDTO();
		Assert.assertNotNull(ss);
		ss.setName("tester");
		PlanetDTO ss2 = new PlanetDTO();
		Assert.assertNotNull(ss2);
		ss2.setName("tester2");

		Planet addedSS = dao.addPlanet(ss);
		Assert.assertNotNull(addedSS);
		Planet added2SS = dao.addPlanet(ss2);
		Assert.assertNotNull(added2SS);

		int total = dao.getTotalPlanets();
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
		PlanetDAO dao = factory.getPlanetDAO();
		Assert.assertNotNull(dao);

		PlanetDTO ss = new PlanetDTO();
		Assert.assertNotNull(ss);
		ss.setName("tester");

		Planet addedSS = dao.addPlanet(ss);
		Assert.assertNotNull(addedSS);

		ss.setName("tester2");
		Planet updatedSS = dao.updatePlanet(ss);
		Assert.assertEquals(addedSS.getName(), updatedSS.getName());
	}

}
