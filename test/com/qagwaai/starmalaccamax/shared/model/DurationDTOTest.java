/**
 * DurationDTOTest.java
 * com.qagwaai.starmalaccamax.shared.model
 * StarMalaccamax
 * Created Mar 14, 2011 at 7:52:54 PM
 */
package com.qagwaai.starmalaccamax.shared.model;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author pgirard
 * 
 */
public class DurationDTOTest {

	/**
	 * Test method for {@link com.qagwaai.starmalaccamax.shared.model.DurationDTO#toMinutes()}.
	 */
	@Test
	public final void testToMinutes() {
		DurationDTO duration = new DurationDTO();
		duration.setYears(12);
		duration.setMonths(3);
		duration.setDays(6);
		duration.setHours(16);
		duration.setMinutes(54);

		System.out.println("duration.toMinutes = " + duration.toMinutes());
	}

	/**
	 * Test method for {@link com.qagwaai.starmalaccamax.shared.model.DurationDTO#fromMinutes(long)}.
	 */
	@Test
	public final void testFromMinutes() {
		DurationDTO duration = new DurationDTO();
		duration.fromMinutes(6452526);

		Assert.assertEquals(12, duration.getYears());
		Assert.assertEquals(3, duration.getMonths());
		Assert.assertEquals(6, duration.getDays());
		Assert.assertEquals(16, duration.getHours());
		// Assert.assertEquals(54, duration.getMinutes());
	}

}
