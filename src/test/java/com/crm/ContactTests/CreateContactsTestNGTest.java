package com.crm.ContactTests;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.ContactsInfoPage;
import com.crm.ObjectRepository.ContactsPage;
import com.crm.ObjectRepository.CreateContactsPage;
import com.crm.ObjectRepository.HomePage;

public class CreateContactsTestNGTest extends BaseClass{
	
	@Test
	public void createContactsTestNGTest() throws Throwable
	{
		String lastName = eLib.readDataFromExcelSheet("Contacts", 1, 2)+"_"+jLib.getRandomNuumber();	
	
		//Step 4: Navigate to contact link
		HomePage hp = new HomePage(driver);
		hp.clickOnContactLnk();
		
		//Step 5: Click on create organization button
        ContactsPage cp = new ContactsPage(driver);
        cp.clickOnCreateContactImg();
        
        
        //Step 6: enter mandatory fields and save
    	CreateContactsPage ccp = new CreateContactsPage(driver);
    	ccp.createNewContact(lastName);
    	
    	//Step 7: Verification
    	ContactsInfoPage cip = new ContactsInfoPage(driver);
    	String actContact = cip.contactNameInfo();
    	Assert.assertTrue(actContact.contains(lastName));
    	System.out.println("Contact created");
	}

}
