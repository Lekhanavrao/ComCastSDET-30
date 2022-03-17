package com.crm.CampaignTest;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CreateCampaignWithProductTest {
	@Test
	public void createCampaignwithProductTest() throws Throwable 
	{
		/*Generate random number  */
		Random r=new Random();
		int random = r.nextInt(500);
		
		/*Step 1: Read all necessary Data */
		//Read data from property file
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
		
		//READ data from excel sheet
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\Test Data.xlsx");
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet("Campaigns");
		Row ro = sh.getRow(1);
		Cell ce = ro.getCell(3);
		String proName = ce.getStringCellValue();
		String proNameR = proName+random;
		Cell c = ro.getCell(2);
		String camName = c.getStringCellValue();
		String camNameR = camName+random;
		
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
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(URL);
		
		/* Step 3:Login to app  */
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 4: Navigate to Products and create product
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("productname")).sendKeys(proNameR);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		/*Step 5: Verification Product */
		String header = driver.findElement(By.className("lvtHeaderText")).getText();
		if(header.contains(proNameR))
		{
			System.out.println(header);
			System.out.println("Product created");
		}
		else
		{
			System.out.println(header);
			System.out.println("Product not created");
		}
		
		//Step 6: Navigate to Campaigns link/
		driver.findElement(By.linkText("More")).click();
		driver.findElement(By.linkText("Campaigns")).click();
		
		//Step 7: Create Campaigns with product/
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("campaignname")).sendKeys(camNameR);
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		
		/*Step 8: Choose Product */
		Set<String> win = driver.getWindowHandles();
		for(String winId:win)
		{
			driver.switchTo().window(winId);
		}
		
		driver.findElement(By.name("search_text")).sendKeys(proNameR);
		driver.findElement(By.name("search")).click();
		
		driver.findElement(By.xpath("//a[text()='"+proNameR+"']")).click();
		
		Set<String> win1 = driver.getWindowHandles();
		for(String wi : win1)
		{
			driver.switchTo().window(wi);
		}
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 9: Verify Campaign
		String head = driver.findElement(By.className("dvHeaderText")).getText();
		if(head.contains(camNameR))
		{
			System.out.println(head);
			System.out.println("Campaign created");
		}
		else
		{
			System.out.println(head);
			System.out.println("Campaign not created");
		}
		
		
		//Step 10: logout and close the browser
		WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		
	}
}
