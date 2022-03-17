package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class HomePage extends WebDriverUtility {

	//Step 1: Declaration
	@FindBy(linkText="Organizations")
	private WebElement organizationLnk;
	
	@FindBy(linkText="Contacts")
	private WebElement contactLnk;
	
	@FindBy(linkText="Opportunities")
	private WebElement opportunitieslnk;
	
	@FindBy(linkText="Products")
	private WebElement productsLnk;
	
	@FindBy(xpath="//a[.='More']")
	private WebElement moreLnk;
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignsLnk;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOutLnk;
	
	//Step 2: Initialization - use constructor
		public HomePage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

	//Step 3: Utilization - provide getters
		public WebElement getOrganizationLnk() {
			return organizationLnk;
		}

		public WebElement getContactLnk() {
			return contactLnk;
		}

		public WebElement getOpportunitieslnk() {
			return opportunitieslnk;
		}

		public WebElement getProductsLnk() {
			return productsLnk;
		}

		public WebElement getMoreLnk() {
			return moreLnk;
		}

		public WebElement getCampaignsLnk() {
			return campaignsLnk;
		}

		public WebElement getAdministratorImg() {
			return administratorImg;
		}

		public WebElement getSignOutLnk() {
			return signOutLnk;
		}
	
//Business library
		public void clickOnOrgLnk()
		{
			organizationLnk.click();
		}
		
		public void clickOnContactLnk()
		{
			contactLnk.click();
		}
		
		public void clickOnCampaignLnk()
		{
			moreLnk.click();
			campaignsLnk.click();
		}
		
		public void clickOnProductLnk()
		{
			productsLnk.click();
		}
		
		public void signOutOfApp(WebDriver driver)
		{
			mouseHover(driver, administratorImg);
			signOutLnk.click();
		}
		
		
		
		
		
		
		
		
		
		
}
