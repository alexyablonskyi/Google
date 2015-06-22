package google.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailPage {
	WebDriver driver;
	
	public GmailPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	 /*
     * Login to Test email account
     */
    @FindBy (xpath = ".//input[@id='Email']")
    WebElement emailFieldForTestAcc;
    
    @FindBy (xpath = ".//input[@id='Passwd']")
    WebElement passwordFieldForTestAcc;
    
    @FindBy (xpath = ".//input[@id='signIn']")
    WebElement signInButtonForTestAcc;
    
    @FindBy (xpath = ".//input[@id='next']")
    WebElement nextButtonForTestAcc;
    
    public void loginToTestEmailAccount(String email, String password){
    	emailFieldForTestAcc.sendKeys(email);
    	nextButtonForTestAcc.click();
    	passwordFieldForTestAcc.sendKeys(password);
    	signInButtonForTestAcc.click();
    }
    
    /*
     * Compose button
     */
    @FindBy(xpath = ".//div[contains(text(), 'COMPOSE')]")
    WebElement composeButton;
    
    public void clickComposeButton(){
    	composeButton.click();
    }
    
    
    /*
     * New email elements + method that fills email fields
     */
    
    @FindBy(xpath = ".//textarea[@name='to']")
    WebElement recipientField;
    
    @FindBy(xpath = ".//div[@class='aoD az6']//input[@class='aoT']")
    WebElement subjectField;
    
    @FindBy(xpath = ".//div[@class='Am Al editable LW-avf']")
    WebElement bodyField;

    String randomNumbers = RandomStringUtils.randomNumeric(9);
    
    public void addElementsToNewEmail(){
    	recipientField.click();
    	recipientField.sendKeys("alexjablonskiy@rambler.ru");
    	subjectField.click();
    	subjectField.sendKeys("Automation test " + randomNumbers);
    	bodyField.click();
    	bodyField.sendKeys("My automation tests  ");
    }
    
    
    /*
     * Send email button
     */
    @FindBy (xpath = ".//div[contains(text(), 'Send')]")
    WebElement sendEmailButton;
    
    public void clickSendEmailButton(){
    	sendEmailButton.click();
    }
    
    
    /*
     * Attach files button
     */
    @FindBy(xpath = ".//div[@class='a1 aaA aMZ']")
    WebElement addAttachmentButton;
    
    public void clickAttachmentButton(){
    	addAttachmentButton.click();
    }
    
    /*
     * Get text of error message for 
     */
    @FindBy (xpath = ".//span[@class='Kj-JD-K7-K0']")
    WebElement errorText;
    
    public String getErrorText(){
    	return errorText.getText();
    }
    
    
    /*
     * Add attach file via Robot class
     */
    public void addAttachmentFileToEmail() throws Exception{
	    StringSelection ss = new StringSelection("C:\\Users\\oyablonskyi\\workspace\\google\\attach.zip");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL); 
	    robot.keyPress(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	    Thread.sleep(4000);
	    robot.keyPress(KeyEvent.VK_ENTER); 
	    robot.keyRelease(KeyEvent.VK_ENTER); 
    }
    
    /*
     * Insert Link button
     */
    @FindBy(xpath = ".//*[@class='e5 aaA aMZ']")
    WebElement insertLinkButton;
    
    public void clickInsertLinkButton(){
    	insertLinkButton.click();
    }
    
    /*
     * Add link to email (Text to display and Web Address fields)
     */
    @FindBy(xpath = ".//*[@id='linkdialog-text']")
    WebElement textToDisplayField;

    @FindBy(xpath = ".//*[@id='linkdialog-onweb-tab-input']")
    WebElement webAddressField;
    
    @FindBy(xpath = ".//button[@name='ok']")
    WebElement okButton;
    
    public void insertLinkToEmail(){
    	textToDisplayField.click();
    	textToDisplayField.sendKeys("GOOGLE");
    	webAddressField.click();
    	webAddressField.sendKeys("www.google.com");
    	okButton.click();
    }
    

    /*
     * Sent email
     */
    @FindBy(xpath = ".//a[contains(text(), 'Sent Mail')]")
    WebElement sentEmailLink;
    
    public void openSentEmailPage(){
    	sentEmailLink.click();
    }
    
    
    /*
     * Open sent email
     */
    @FindBy(xpath = ".//span[contains(text(), 'Automation test 714587345')]") 
    WebElement openSentEmailLink;
    
    public String getEmailXpath(){
    	return ".//span[contains(text(), 'Automation test "+randomNumbers+"')]";
    }
    
    public void openSentEmail(){
    	driver.findElement(By.xpath(getEmailXpath())).click();
    }
    
    /*
     * Find insert link in email and click
     */
    @FindBy(xpath = ".//a[@href='http://www.google.com']")
    WebElement googleLink;
    
    public void openInsertLink(){
    	googleLink.click();
    }
    
    /*
     * Get Title of page
     */
    public String getPageTitle(){
    	return driver.getTitle();
    }  
    
}
