package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class ContactsInfoPage {
	
	//Step 1: Declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
    private WebElement contactheaderTxt;
	
	//Step 2: Initialize
	public ContactsInfoPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	//Step 3: Utilization
	public WebElement getHeaderTxt() {
		return contactheaderTxt;
	}
	
	//Business library
	public String contactNameInfo()
	{
		String ConInfo = contactheaderTxt.getText();
		return ConInfo;
	}

	
}
