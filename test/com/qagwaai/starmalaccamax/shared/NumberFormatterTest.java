/**
 * NumberFormatterTest.java
 * com.qagwaai.starmalaccamax.shared
 * StarMalaccamax
 * Created Mar 15, 2011 at 10:51:54 AM
 */
package com.qagwaai.starmalaccamax.shared;

import org.junit.Test;

/**
 * @author pgirard
 * 
 */
public class NumberFormatterTest {

	/**
	 * Test method for {@link com.qagwaai.starmalaccamax.shared.NumberFormatter#formatNumber(long, int, int)}.
	 */
	@Test
	public final void testFormatNumber() {
		long num = 9221120237041090560L;

		System.out.println(NumberFormatter.formatNumber(num, 5, 3) + "m");
		System.out.println(NumberFormatter.formatNumber(num, 1, 12) + "m");
	}

}
