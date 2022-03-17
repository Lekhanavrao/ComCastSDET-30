package com.crm.ContactTests;

import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.ContactsInfoPage;
import com.crm.ObjectRepository.ContactsPage;
import com.crm.ObjectRepository.CreateContactsPage;
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationsPage;

public class CreateContactOrgLeadTestNGTest extends BaseClass{
	@Test
	public void createContactOrgLeadTestNGTest() throws Throwable
	{
		
		String lastName = eLib.readDataFromExcelSheet("Contacts", 3, 2)+"_"+jLib.getRandomNuumber();
	    String OrgName = eLib.readDataFromExcelSheet("Contacts", 3, 3)+"_"+jLib.getRandomNuumber();
	    String leadSource = eLib.readDataFromExcelSheet("Contacts", 3, 4);
		
		//Step 4: Navigate to organization link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrgLnk();
				
		//Step 5: Click on create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrgImg();
				
	    //Step 6: enter mandatory fields and save
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createNewOrg(OrgName);
		
		//Step 7: verification
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String header = oip.OrgNameInfo();
		if(header.contains(OrgName))
			{
				System.out.println(header+"---->Org created");
			}
			else
			{
				System.out.println("org not created");
			}
	
		//Step 8: navigate to contacts link
		hp.clickOnContactLnk();
		
		//Step 9: Click on create contact link
        ContactsPage cp = new ContactsPage(driver);
        cp.clickOnCreateContactImg();
        
        //Step 10: enter mandatory fields and save
        CreateContactsPage ccp = new CreateContactsPage(driver);
        ccp.createNewContact(driver, lastName, OrgName, leadSource);
        
      //Step 11:verify for contact
    	ContactsInfoPage cip = new ContactsInfoPage(driver);
    	String contactHeader = cip.contactNameInfo();
    	if(contactHeader.contains(lastName))
    	{
    		System.out.println(contactHeader+"---->contact created");
    	}
    	else
    	{
    		System.out.println("contact not created");
    	}
	}
}
