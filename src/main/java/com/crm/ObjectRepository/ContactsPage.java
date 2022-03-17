package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class ContactsPage extends WebDriverUtility {
	
	//Step 1: Declaration
	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement createContactLookUpImg;
	
	//Step 2: Initialize
		public ContactsPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//Step 3: Utilization
		public WebElement getCreateContactLookUpImg() {
			return createContactLookUpImg;
		}
		
		//business library
		public void clickOnCreateContactImg()
	    {
	    	createContactLookUpImg.click();
	    }

}
