package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class OrganizationInfoPage extends WebDriverUtility {
	
	//Step 1: Declaration
			@FindBy(xpath = "//span[@class='dvHeaderText']")
			private WebElement headerTxt;
			
			//Step 2: Initialize
			public OrganizationInfoPage(WebDriver driver)
			{
				PageFactory.initElements(driver, this);
			}
			
			//Step 3: Utilization
			public WebElement getHeaderTxt() {
				return headerTxt;
			}
			
			//Business library
			public String OrgNameInfo()
			{
				String OrgInfo = headerTxt.getText();
				return OrgInfo;
			}


}
