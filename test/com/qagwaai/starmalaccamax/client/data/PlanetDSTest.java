package com.qagwaai.starmalaccamax.client.data;

import junit.framework.Assert;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.qagwaai.starmalaccamax.client.event.DefaultEventBus;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.event.PlanetAddedEvent;
import com.qagwaai.starmalaccamax.client.event.PlanetAddedHandler;
import com.qagwaai.starmalaccamax.client.event.PlanetRemovedEvent;
import com.qagwaai.starmalaccamax.client.event.PlanetRemovedHandler;
import com.qagwaai.starmalaccamax.client.event.PlanetsLoadedEvent;
import com.qagwaai.starmalaccamax.client.event.PlanetsLoadedHandler;
import com.qagwaai.starmalaccamax.client.event.ServiceExceptionEvent;
import com.qagwaai.starmalaccamax.client.event.ServiceExceptionHandler;
import com.qagwaai.starmalaccamax.client.service.PlanetService;
import com.qagwaai.starmalaccamax.client.service.PlanetServiceAsync;
import com.qagwaai.starmalaccamax.shared.InvalidParameterException;

/**
 * GWT JUnit tests must extend GWTTestCase.
 */
public class PlanetDSTest extends GWTTestCase {

	/**
	 * 
	 */
	private EventBus eventBus;

	/**
	 * 
	 * @return Must refer to a valid module that sources this class.
	 */
	public String getModuleName() {
		return "com.qagwaai.starmalaccamax.StarMalaccamaxJUnit";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void gwtSetUp() throws Exception {
		super.gwtSetUp();
		eventBus = new DefaultEventBus();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void gwtTearDown() throws Exception {
		super.gwtTearDown();
		eventBus = null;
	}

	/**
	 * 
	 */
	public void testBadPlanetRemoveDS() {
		System.out.println("=================== Running testBadRemoveDS;");
		// Create the service that we will test.
		PlanetServiceAsync planetService = GWT.create(PlanetService.class);
		ServiceDefTarget target = (ServiceDefTarget) planetService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "test/planet");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 5 seconds before timing out.
		delayTestFinish(5000);

		PlanetDS ds = null;
		try {
			ds = (PlanetDS) DataSourceFactory.get(PlanetDS.DS_TYPE, null);
		} catch (InvalidParameterException e) {
			fail();
		}
		ds.setDataService(planetService);
		EventBus eventBus = ds.getEventBus();
		eventBus.addHandler(ServiceExceptionEvent.getType(), new ServiceExceptionHandler() {
			@Override
			public void onServiceException(final ServiceExceptionEvent event) {
				System.out.println("=============== failed to Removed Successfully");
				finishTest();
			}
		});
		eventBus.addHandler(PlanetRemovedEvent.getType(), new PlanetRemovedHandler() {
			@Override
			public void onPlanetRemoved(final PlanetRemovedEvent event) {
				// finishTest();
				fail("Remove completed successfully");
			}
		});

		Assert.assertNotNull(ds);
		PlanetRecord pr = new PlanetRecord();
		Assert.assertNotNull(pr);
		pr.setName("PlanetName");
		ds.removeData(pr);

	}

	/**
   * 
   */
	public void testPlanetAddDS() {
		System.out.println("=================== Running testPlanetAddDS;");
		// Create the service that we will test.
		PlanetServiceAsync planetService = GWT.create(PlanetService.class);
		ServiceDefTarget target = (ServiceDefTarget) planetService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "test/planet");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 5 seconds before timing out.
		delayTestFinish(5000);

		Assert.assertNotNull(eventBus);
		eventBus.addHandler(PlanetAddedEvent.getType(), new PlanetAddedHandler() {
			@Override
			public void onPlanetAdded(final PlanetAddedEvent event) {
				System.out.println("=============== Added Successfully ====================");
				finishTest();
			}
		});

		PlanetDS ds = null;
		try {
			ds = (PlanetDS) DataSourceFactory.get(PlanetDS.DS_TYPE, eventBus);
		} catch (InvalidParameterException e) {
			fail();
		}
		ds.setDataService(planetService);
		Assert.assertNotNull(ds);
		PlanetRecord pr = new PlanetRecord();
		Assert.assertNotNull(pr);
		pr.setName("PlanetName");
		ds.addData(pr);

	}

	/**
	   * 
	   */
	public void testPlanetGetDS() {
		System.out.println("=================== Running testPlanetGetDS;");
		// Create the service that we will test.
		PlanetServiceAsync planetService = GWT.create(PlanetService.class);
		ServiceDefTarget target = (ServiceDefTarget) planetService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "test/planet");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		PlanetDS ds = null;
		try {
			ds = (PlanetDS) DataSourceFactory.get(PlanetDS.DS_TYPE, null);
		} catch (InvalidParameterException e) {
			fail();
		}
		EventBus eventBus = ds.getEventBus();
		Assert.assertNotNull(eventBus);
		ds.setDataService(planetService);
		Assert.assertNotNull(ds);
		PlanetRecord pr = new PlanetRecord();
		Assert.assertNotNull(pr);
		pr.setName("PlanetName");
		ds.addData(pr);

		PlanetRecord pr2 = new PlanetRecord();
		Assert.assertNotNull(pr2);
		pr2.setName("PlanetName2");
		ds.addData(pr2);

		eventBus.addHandler(PlanetsLoadedEvent.getType(), new PlanetsLoadedHandler() {
			@Override
			public void onPlanetsLoaded(PlanetsLoadedEvent event) {
				Assert.assertEquals(2, event.getPlanets().size());
				finishTest();
			}
		});

		ds.invalidateCache();
		//ds.fetchData();
	}

	/**
	   * 
	   */
	public void testPlanetRemoveDS() {
		System.out.println("=================== Running testPlanetRemoveDS;");
		// Create the service that we will test.
		PlanetServiceAsync planetService = GWT.create(PlanetService.class);
		ServiceDefTarget target = (ServiceDefTarget) planetService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "test/planet");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 5 seconds before timing out.
		delayTestFinish(10000);

		PlanetDS ds = null;
		try {
			ds = (PlanetDS) DataSourceFactory.get(PlanetDS.DS_TYPE, null);
		} catch (InvalidParameterException e) {
			fail();
		}
		EventBus eventBus = ds.getEventBus();
		Assert.assertNotNull(eventBus);
		ds.setDataService(planetService);
		Assert.assertNotNull(ds);
		PlanetRecord pr = new PlanetRecord();
		Assert.assertNotNull(pr);
		pr.setName("PlanetName");
		ds.addData(pr);

		eventBus.addHandler(PlanetRemovedEvent.getType(), new PlanetRemovedHandler() {
			@Override
			public void onPlanetRemoved(final PlanetRemovedEvent event) {
				finishTest();
			}
		});

		ds.removeData(pr);
	}
}
