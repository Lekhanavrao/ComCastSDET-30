package com.crm.OpportunitiesTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;


public class CreateOpportunitiesWithContactAndCampaignTest {
	@Test
	public void createOpportunitiesWithContactAndCampaignTest() throws Throwable
	{
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		
		
		/*Step 1: Read all necessary Data */
		 PropertyFileUtility pLib = new PropertyFileUtility();
		 WebDriverUtility wLib = new WebDriverUtility();
		 ExcelFileUtility eLib = new ExcelFileUtility();
		
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
		//READ data from excel sheet
		String lastName = eLib.readDataFromExcelSheet("Opportunities", 1, 3);
		String camName = eLib.readDataFromExcelSheet("Opportunities", 1, 2);
		String oppName = eLib.readDataFromExcelSheet("Opportunities", 1, 4);
		
		/* Step 2: launch the browser */
		WebDriver driver=null;
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
		
		/* Step 3:Login to application  */
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		/*Step 4: NAVIGATE TO Contact link */
		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		
		//Step 5: Click on create Contact/
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		/*Step 6: Enter Mandatory field and Save */
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		
		/*Step 7: Verification Contact */
		String header = driver.findElement(By.className("dvHeaderText")).getText();
		if(header.contains(lastName))
		{
			System.out.println(header);
			System.out.println("Contact is created");
		}
		else
		{
			System.out.println(header);
			System.out.println("Contact is not created");
		}
		
		//Step 8: Navigate to Campaigns link/
		driver.findElement(By.linkText("More")).click();
		driver.findElement(By.linkText("Campaigns")).click();
		
		/*Step 9: Create Campaigns */
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("campaignname")).sendKeys(camName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 10: Verify Campaign/
		String head = driver.findElement(By.className("dvHeaderText")).getText();
		if(head.contains(camName))
		{
			System.out.println(head);
			System.out.println("Campaign created");
		}
		else
		{
			System.out.println(head);
			System.out.println("Campaign not created");
		}
		
		/*Step 11: Create Opportunities */
		driver.findElement(By.linkText("Opportunities")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("potentialname")).sendKeys(oppName);
		
		//Step 12: Select Contacts in Related to DropDown/
		WebElement ele = driver.findElement(By.name("related_to_type"));
		wLib.select("Contacts", ele);
		driver.findElement(By.xpath("//input[@name='related_to_display']/following-sibling::img")).click();
		
		//Step 12: Switch to Contact Pop-Up/
		wLib.switchToWindow(driver, "Contacts");
		driver.findElement(By.name("search_text")).sendKeys(lastName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText(lastName)).click();
		
		//Step 13: Switch back to main window and select Employee in Lead Source Dropdown/
		wLib.switchToWindow(driver, "Potentials");
		WebElement ele1 = driver.findElement(By.name("leadsource"));
		wLib.select("Employee", ele1);
		driver.findElement(By.xpath("//input[@name='campaignname']/following-sibling::img")).click();
		
		//Step 14: Switch to Campaigns Pop-up and Select Campaign/
		wLib.switchToWindow(driver, "Campaigns");
		driver.findElement(By.name("search_text")).sendKeys(lastName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText(camName)).click();
		
		/*Step 15: Switch back to main window and save */
		wLib.switchToWindow(driver, "Potentials");
	//	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 16: Verify Campaign/
		/*String title = driver.findElement(By.className("dvHeaderText")).getText();
		if(title.contains(oppName))
		{
			System.out.println(title);
			System.out.println("Opportunity created");
		}
		else
		{
			System.out.println(title);
			System.out.println("Opportunity not created");
		}*/
		
		//Step 17: logout and close the browser/
		WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wLib.mouseHover(driver, element);
		
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();	
	}

}
