package com.crm.CampaignTest;

import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.CampaignsInfoPage;
import com.crm.ObjectRepository.CampaignsPage;
import com.crm.ObjectRepository.CreateCampaignsPage;
import com.crm.ObjectRepository.CreateProductsPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.ProductsInfoPage;
import com.crm.ObjectRepository.ProductsPage;

public class CreateCampaignWithProductTestNGTest extends BaseClass{
	
	@Test
	public void createCampaignWithProductTestNGTest() throws Throwable
	{
	
	String campName = eLib.readDataFromExcelSheet("Campaign", 1, 2)+"_"+jLib.getRandomNuumber();
	String prodName = eLib.readDataFromExcelSheet("Campaign", 1, 3);
	
	//Step 1: Navigate to product link
	HomePage hp = new HomePage(driver);
    hp.clickOnProductLnk();
	    
	//Step 2: Click on create product button
	 ProductsPage pp = new ProductsPage(driver);
     pp.clickOnProductLookUpImg();
	    
   //Step 3: enter mandatory fields and save
    CreateProductsPage cpp = new CreateProductsPage(driver);
	cpp.createNewProduct(prodName);
		
    //Step 4: Verification
    ProductsInfoPage pip = new ProductsInfoPage(driver);
    String actProduct = pip.productNameInfo();
	if(actProduct.contains(prodName))
	{
		System.out.println(actProduct+"---->data verified");
	}
	else
	{
		System.out.println("data invalid");
	}
	
	
	//Step 5: Navigate to campaign link
	hp.clickOnCampaignLnk();
	
	//Step 6:click on Create campaign button
	CampaignsPage cp = new CampaignsPage(driver);
	cp.clickOnCreateCampaignsImg();      
	
	//Step 7:enter the details and save
	CreateCampaignsPage ccp = new CreateCampaignsPage(driver);
	ccp.createNewCampaign(driver, campName, prodName);
	
	//Step 8: Verification
	CampaignsInfoPage cip = new CampaignsInfoPage(driver);
	String actCampaign = cip.campaigntNameInfo();
	if(actCampaign.contains(campName))
	{
		System.out.println(actCampaign+"---->data verified");
	}
	else
	{
		System.out.println("data invalid");
	}
  }
}
