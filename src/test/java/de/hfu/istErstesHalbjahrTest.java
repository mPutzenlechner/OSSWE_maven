package de.hfu;

import org.junit.Test;
import static org.junit.Assert.*;

public class istErstesHalbjahrTest {
	@Test(expected=IllegalArgumentException.class, timeout=1000)
	public void testUtilException() {
		Util.istErstesHalbjahr(0);
		Util.istErstesHalbjahr(13);
		Util.istErstesHalbjahr(-2);
	}
	
	
	@Test
	public void testUtil() {
		assertEquals(true, Util.istErstesHalbjahr(1));
		assertEquals(true, Util.istErstesHalbjahr(6));
		assertEquals(false, Util.istErstesHalbjahr(7));
		assertEquals(false, Util.istErstesHalbjahr(12));
	}
}
