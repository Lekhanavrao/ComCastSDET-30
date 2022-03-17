package com.crm.GenericLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	//Create object for all utilities
	public DatabaseUtility dLib = new DatabaseUtility();
	public ExcelFileUtility eLib = new ExcelFileUtility();
	public JavaUtility jLib = new JavaUtility();
	public PropertyFileUtility pLib = new PropertyFileUtility();
	public WebDriverUtility wlib = new WebDriverUtility();
	public WebDriver driver;
	public static WebDriver sDriver;
	
	
	@BeforeSuite (groups = {"smokeSuite","regressionSuite"})
	public void connectionDataBase() throws Throwable
	{
		//dLib.connectToDb();
		Reporter.log("====db connection successful====", true);
	}
	
	@BeforeClass (groups = {"smokeSuite","regressionSuite"})
	//@BeforeTest
	//@Parameters("browser")
	public void launchTheBrowser() throws Throwable
	{
		//read the data from property file
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		
		//create run time polymorphysm
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
		{
		System.out.println("invalid browser");	
		}
		
		sDriver = driver;
		
		wlib.maximizewindow(driver);
		wlib.waitForPageLoad(driver);
		driver.get(URL);
		Reporter.log("====browser launch successful====", true);
	}
	
	@BeforeMethod (groups = {"smokeSuite","regressionSuite"})
	public void login() throws Throwable
	{
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		LoginPage lPage = new LoginPage(driver);
		lPage.loginToApp(USERNAME, PASSWORD);
		Reporter.log("====login successfull====", true);
	}
	
	@AfterMethod (groups = {"smokeSuite","regressionSuite"})
	public void logout()
	{
		HomePage hP = new HomePage(driver);
		hP.signOutOfApp(driver);
		Reporter.log("====logout successfull====", true);
	}
	
	@AfterClass (groups = {"smokeSuite","regressionSuite"})
	//@AfterTest
	public void closeBrowser()
	{
		driver.quit();
		Reporter.log("====browser close successfull====", true);
	}
	
	@AfterSuite (groups = {"smokeSuite","regressionSuite"})
	public void closeDb() throws Throwable
	{
		dLib.closeDB();
		Reporter.log("====browser close successfull====", true);
	}
}
