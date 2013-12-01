package libraries;

import junit.framework.Assert;

public class Assertions {

	public static void assertTitle(String expected, String actual, String msg){
		Assert.assertEquals(expected, actual);
		System.out.println(msg);
	}
	public static void assertText(String expected, String actual, String msg){
		Assert.assertEquals(expected, actual);
		System.out.println(msg);
	}
}
