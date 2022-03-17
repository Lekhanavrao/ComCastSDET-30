package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsInfoPage {
	
	//Step 1: Declaration
		@FindBy(xpath = "//span[@class='lvtHeaderText']")
	    private WebElement productheaderTxt;
		
		//Step 2: Initialize
		public ProductsInfoPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		//Step 3: Utilization
		public WebElement getProductheaderTxt() {
			return productheaderTxt;
		}
		
		//Business library
		public String productNameInfo()
		{
			String ProdInfo = productheaderTxt.getText();
			return ProdInfo;
		}

		
}
