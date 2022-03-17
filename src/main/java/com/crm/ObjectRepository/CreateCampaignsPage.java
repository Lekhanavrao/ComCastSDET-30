package com.crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateCampaignsPage extends WebDriverUtility {
	
	//Step 1: Declaration
	@FindBy(name = "campaignname")
    private WebElement campaignsNameEdt;
	
	@FindBy(xpath = "//img[@alt='Select']")
	private WebElement productNameLookUpImg;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(id = "search_txt")
	private WebElement searchEdt;
	
	@FindBy(name = "search")
	private WebElement searchBtn;
	

	//Step 2: Initialize
		public CreateCampaignsPage(WebDriver driver)
		{	
		  PageFactory.initElements(driver, this);
		}
		

	//Step 3: Utilization
	public WebElement getCampaignsNameEdt() {
		return campaignsNameEdt;
	}

	public WebElement getProductNameLookUpImg() {
		return productNameLookUpImg;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}
	
	
	 //Business library
		public void createNewCampaign(String campaignname)
		{
			campaignsNameEdt.sendKeys(campaignname);
			saveBtn.click();
		}

	
		public void createNewCampaign(WebDriver driver, String campaignname, String productName)
		{
			campaignsNameEdt.sendKeys(campaignname);
			productNameLookUpImg.click();
			switchToWindow(driver, "Products");
			searchEdt.sendKeys(productName);
			searchBtn.click();
			driver.findElement(By.xpath("//a[text()='"+productName+"']")).click();
			switchToWindow(driver, "Campaigns");
			saveBtn.click();
		}
}
