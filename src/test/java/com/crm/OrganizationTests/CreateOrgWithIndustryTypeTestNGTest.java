package com.crm.OrganizationTests;

import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationsPage;

public class CreateOrgWithIndustryTypeTestNGTest extends BaseClass{
	@Test
	public void createOrgWithIndTypeTestNGTest() throws Throwable
	{
		String OrgName = eLib.readDataFromExcelSheet("Org", 4, 2)+"_"+jLib.getRandomNuumber();
	    String indType = eLib.readDataFromExcelSheet("Org", 4, 3);
		
		//Step 1: Navigate to organization link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrgLnk();
		
		//Step 2: Click on create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrgImg();
		
		//Step 3: enter mandatory fields and save
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createNewOrg(OrgName, indType);
		
		//Step 4: verification
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actHeader = oip.OrgNameInfo();
		if(actHeader.contains(OrgName))
		{
		System.out.println(actHeader +"---->Organization created");	
		}
		else
		{
		System.out.println("organization is not created");
		}
	}
}
