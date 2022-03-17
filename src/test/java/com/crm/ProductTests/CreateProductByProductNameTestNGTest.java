package com.crm.ProductTests;

import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.CreateProductsPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.ProductsInfoPage;
import com.crm.ObjectRepository.ProductsPage;

public class CreateProductByProductNameTestNGTest extends BaseClass {
	
	@Test
	public void createProductByProductNameTestNGTest() throws Throwable
	{
		
	String prodName = eLib.readDataFromExcelSheet("Products", 1, 2)+"_"+jLib.getRandomNuumber();
				
	//Step 3: Navigate to product link
	HomePage hp = new HomePage(driver);
    hp.clickOnProductLnk();
    
  //Step 5: Click on create product button
    ProductsPage pp = new ProductsPage(driver);
    pp.clickOnProductLookUpImg();
    
    //Step 6: enter mandatory fields and save
	CreateProductsPage cpp = new CreateProductsPage(driver);
	cpp.createNewProduct(prodName);
	
	//Step 7: Verification
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
    

}


}
