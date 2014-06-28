package com.qagwaai.starmalaccamax.client.event;

import junit.framework.Assert;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * GWT JUnit tests must extend GWTTestCase.
 */
public class EventBusTest extends GWTTestCase {

	/**
	 * 
	 * @return Must refer to a valid module that sources this class.
	 */
	public String getModuleName() {
		return "com.qagwaai.starmalaccamax.StarMalaccamaxJUnit";
	}

	/**
	 * 
	 */
	public void testEventBus() {
		System.out.println("=================== Running testEventBus");
		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		EventBus eventBus = new DefaultEventBus();
		Assert.assertNotNull(eventBus);
		eventBus.addHandler(ServiceExceptionEvent.getType(), new ServiceExceptionHandler() {

			@Override
			public void onServiceException(final ServiceExceptionEvent event) {
				System.out.println("=================== Successful testEventBus");
				finishTest();
			}
		});

		ServiceExceptionEvent.fire(eventBus, new Throwable("tester"));
	}
}
