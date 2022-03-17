package com.crm.CampaignTest;

import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.CampaignsInfoPage;
import com.crm.ObjectRepository.CampaignsPage;
import com.crm.ObjectRepository.CreateCampaignsPage;
import com.crm.ObjectRepository.HomePage;

public class CreateCampaignTestNGTest extends BaseClass {
	@Test
	public void createCampaignPOMTest() throws Throwable
	{
		
		String campname = eLib.readDataFromExcelSheet("Campaign", 1, 2)+"_"+jLib.getRandomNuumber();
	
		//Step 3: Navigate to campaign link
		HomePage hp = new HomePage(driver);
		hp.clickOnCampaignLnk();
		
		//Step 4:click on Create campaign button
		CampaignsPage cp = new CampaignsPage(driver);
		cp.clickOnCreateCampaignsImg();
		
		//Step 5:enter the details and save
		CreateCampaignsPage ccp = new CreateCampaignsPage(driver);
		ccp.createNewCampaign(campname);
		
		//Step 6: Verification
    	CampaignsInfoPage cip = new CampaignsInfoPage(driver);
    	String actCampaign = cip.campaigntNameInfo();
    	if(actCampaign.contains(campname))
    	{
    		System.out.println(actCampaign+"---->data verified");
    	}
    	else
    	{
    		System.out.println("data invalid");
    	}
        
	}
}
