package google.tests;

import google.base.*;
import google.pages.*;
import google.values.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTests extends Base {

	LoginPage loginPage;


	@Test(enabled= true, priority=0, groups = {"Google tests"}, description= "Check that attachment size cannot be more than 25 MB")
	public void verifySizeOfAttachment() throws Exception{
		loginPage = new LoginPage(driver);
        loginPage.loginToTestEmailAccount(Global.GMAIL_EMAIL, Global.GMAIL_PASSWORD); 
        
        loginPage.clickComposeButton();
        loginPage.addElementsToNewEmail();
        loginPage.clickAttachmentButton();
        loginPage.addAttachmentFileToEmail();

        System.out.println("Text of error message: " + loginPage.getErrorText());
        Assert.assertEquals(loginPage.getErrorText(), "The file you are trying to send exceeds the 25MB attachment limit.");
	}

	
	@Test(enabled= true, priority=1, groups = {"Google tests"}, description= "Check that there is a possibility to add link to email")
	public void includeLinkToEmail() throws Exception{
		loginPage = new LoginPage(driver);
        loginPage.loginToTestEmailAccount(Global.GMAIL_EMAIL, Global.GMAIL_PASSWORD); 
        
        loginPage.clickComposeButton();
        loginPage.addElementsToNewEmail();       
        loginPage.clickInsertLinkButton();
        loginPage.insertLinkToEmail();
        loginPage.clickSendEmailButton();
        loginPage.openSentEmailPage();
        loginPage.openSentEmail();    
        loginPage.openInsertLink();
        switchToWindow();
        loginPage.getPageTitle();
        
        System.out.println("Title of page is " + loginPage.getPageTitle());
        Assert.assertEquals(loginPage.getPageTitle(), "Google");
	}
	
}
