package com.crm.ProductTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.ObjectRepository.CreateProductsPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.ProductsInfoPage;
import com.crm.ObjectRepository.ProductsPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductByProductNameTest {
	@Test
	public void createProductByProductNameTest() throws Throwable
	{
	/*read data*/
	PropertyFileUtility pLib = new PropertyFileUtility();
	JavaUtility jLib = new JavaUtility();
	ExcelFileUtility eLib = new ExcelFileUtility();
	WebDriverUtility wLib = new WebDriverUtility();
	
	/*Step 1: read all necessary data*/ 
	String BROWSER = pLib.readDataFromPropertyFile("browser");
	String URL = pLib.readDataFromPropertyFile("url");
	String USERNAME = pLib.readDataFromPropertyFile("username");
	String PASSWORD = pLib.readDataFromPropertyFile("password");
	
	String prodName = eLib.readDataFromExcelSheet("Products", 1, 2)+"_"+jLib.getRandomNuumber();
				
	//Step 2: launch the browser
	WebDriverManager.chromedriver().setup();
	WebDriverManager.firefoxdriver().setup();
				
	WebDriver driver = null;
	if(BROWSER.equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
	}
	else if(BROWSER.equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
	}
	else 
	{
		System.out.println("invalid browser");	
	}
				
	wLib.maximizewindow(driver);
	wLib.waitForPageLoad(driver);
	driver.get(URL);
				
	//Step 3: login to application
	LoginPage lp = new LoginPage(driver);
	lp.loginToApp(USERNAME, PASSWORD);
				
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
    
	//Step 8: logout of application
	hp.signOutOfApp(driver);
	
	//Step 9: Close the browser
	driver.quit();	
}
				
		
}
