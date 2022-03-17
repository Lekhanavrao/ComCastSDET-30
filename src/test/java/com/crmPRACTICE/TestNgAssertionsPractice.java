package com.crmPRACTICE;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNgAssertionsPractice {

	@Test
	public void asssertionPractice()
	{
		SoftAssert sa = new SoftAssert();
		System.out.println("this is test1 ");
		Assert.assertEquals(1, 0);
		System.out.println("Fail");
	}
}
