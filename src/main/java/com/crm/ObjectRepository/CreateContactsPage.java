package com.crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateContactsPage extends WebDriverUtility{

	//Step 1: Declaration
	@FindBy(name = "lastname")
	private WebElement lastNameEdt;
	
	@FindBy(xpath = "//img[@alt='Select']")
	private WebElement orgNameLookUpImg;
	
	@FindBy(name = "leadsource")
	private WebElement leadSourceDropDown;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name = "search_text")
	private WebElement searchEdt;
	
	@FindBy(name = "search")
	private WebElement searchBtn;
	
	//Step 2: Initialize
	public CreateContactsPage(WebDriver driver)
	{	
	  PageFactory.initElements(driver, this);
	}

	//Step 3: Utilization
	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}

	public WebElement getOrgNameLookUpImg() {
		return orgNameLookUpImg;
	}	
	
	public WebElement getLeadSourceDropDown() {
		return leadSourceDropDown;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	} 

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
   //Business library
	public void createNewContact(String lastName)
	{
		lastNameEdt.sendKeys(lastName);
		saveBtn.click();
	}
	
	public void createNewContact(String lastName, String leadSource)
	{
	lastNameEdt.sendKeys(lastName);
	select(leadSource, leadSourceDropDown);
	saveBtn.click();
	}
	
	public void createNewContact(WebDriver driver, String lastName, String orgName)
	{
	lastNameEdt.sendKeys(lastName);
	orgNameLookUpImg.click();
	switchToWindow(driver, "Accounts");
	searchEdt.sendKeys(orgName);
	searchBtn.click();
	driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
	switchToWindow(driver, "Contacts");
	saveBtn.click();
	}
	
	public void createNewContact(WebDriver driver, String lastName, String orgName, String leadSource)
	{
		lastNameEdt.sendKeys(lastName);
		orgNameLookUpImg.click();
		switchToWindow(driver, "Accounts");
		searchEdt.sendKeys(orgName);
		searchBtn.click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		switchToWindow(driver, "Contacts");
		select(leadSource, leadSourceDropDown);
		saveBtn.click();
	}
	
	
}










