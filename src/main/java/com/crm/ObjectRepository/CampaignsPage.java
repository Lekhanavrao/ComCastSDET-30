package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CampaignsPage extends WebDriverUtility{
	//Step 1: declaration
	@FindBy(xpath = "//img[@alt='Create Campaign...']")
	private WebElement createCampaignsLookUpImg;
	
	
	//Step 2: Initialize
	public CampaignsPage(WebDriver driver)
	{	
	  PageFactory.initElements(driver, this);
	}
			

	//Step 3: Utilization
	public WebElement getCreateCampaignsLookUpImg() 
	{
		return createCampaignsLookUpImg;
	}
	
	
	
	//business library
	public void clickOnCreateCampaignsImg()
    {
		createCampaignsLookUpImg.click();
    }
}
