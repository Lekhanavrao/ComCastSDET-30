package com.crm.OrganizationTests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationsPage;
import com.mysql.cj.exceptions.AssertionFailedException;
@Listeners(com.crm.GenericLibrary.ListnerImplimentationClass.class)

public class CreateOrganizationTestNGTest extends BaseClass {

	@Test(retryAnalyzer= com.crm.GenericLibrary.RetryAnalyserImplementation.class)
	public void createorganizationTestNGTest() throws Throwable
	{
		String OrgName = eLib.readDataFromExcelSheet("Org", 1, 2)+"_"+jLib.getRandomNuumber();
	
		//Step 1: Navigate to organization link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrgLnk();
		Assert.fail();
		
		//Step 2: Click on create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrgImg();
		
		//Step 3: enter mandatory fields and save
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createNewOrg(OrgName);
		
		//Step 4: verification
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.OrgNameInfo();
		if(actOrgName.contains(OrgName))
		{
			System.out.println(actOrgName+"---->data verified");
		}
		else
		{
			System.out.println("data invalid");
		}

	}

}
