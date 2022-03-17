package com.crmPRACTICE;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PracticeRetryAnalyser {

	@Test(retryAnalyzer= com.crm.GenericLibrary.RetryAnalyserImplementation.class)
	public void practiceRetry()
	{
		System.out.println("lekhana is bad boy");
		Assert.fail();
		System.out.println("This is passed");
	}
}
