package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	
	//Step 1: Declaration
	@FindBy(xpath = "//img[@alt='Create Product...']")
	private WebElement createProductLookUpImg;
	
	//Step 2: Initialize
	public ProductsPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	//Step 3: Utilization
	public WebElement getCreateProductLookUpImg() {
		return createProductLookUpImg;
	}

	//Business library
	public void clickOnProductLookUpImg()
	{
		createProductLookUpImg.click();
	}
	
	
}
