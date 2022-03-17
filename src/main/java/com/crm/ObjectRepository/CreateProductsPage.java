package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProductsPage {
	
	@FindBy(name = "productname")
	private WebElement productNameEdt;
	
	@FindBy(id = "productcode")
	private WebElement partNumberEdt;
	
	@FindBy(id ="commissionrate")
	private WebElement commRateEdt;
	
	@FindBy(id = "qtyinstock")
	private WebElement qtyInStockEdt;

	@FindBy(id = "product_no")
	private WebElement productNumberEdt;
	
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement saveBtn;
	
	public CreateProductsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getProductEdt() {
		return productNameEdt;
	}

	public WebElement getPartNumberEdt() {
		return partNumberEdt;
	}

	public WebElement getCommRateEdt() {
		return commRateEdt;
	}

	public WebElement getQtyInStockEdt() {
		return qtyInStockEdt;
	}

	public WebElement getProductNumberEdt() {
		return productNumberEdt;
	}
	
	 public WebElement getSaveBtn() {
		return saveBtn;
	}

	//business library
	public void createNewProduct(String prodName)
	{
		productNameEdt.sendKeys(prodName);
		saveBtn.click();
	}
	
	public void createNewProduct(String prodName, String partNo)
	{
		productNameEdt.sendKeys(prodName);
		productNumberEdt.sendKeys(partNo);
		saveBtn.click();
	}


	
	
	
}
