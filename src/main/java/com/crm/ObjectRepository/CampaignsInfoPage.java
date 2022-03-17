package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignsInfoPage {
	
	//Step 1: Declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement campaignheaderTxt;
	
	//Step 2: Initialize
		public CampaignsInfoPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		
	//Step 3: Utilization
		public WebElement getCampaignheaderTxt() {
			return campaignheaderTxt;
		}
		
		//Business library
		public String campaigntNameInfo()
		{
			String CampaignInfo = campaignheaderTxt.getText();
			return CampaignInfo;
		}
		

}
