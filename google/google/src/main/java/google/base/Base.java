package google.base;

import google.base.*;
import google.pages.*;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class Base {

	protected static WebDriver driver;
	
	public String googleMail = "https://gmail.com";
	
 	@BeforeMethod
	public void driverSetup(){
 		driver = new FirefoxDriver();
 		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
 		driver.manage().window().maximize();
 		driver.get(googleMail);
 	}

	@AfterMethod
	public void tearDown(){
		driver.quit();	
	} 

	
	public static void waitForPageLoad(WebDriver driver) {
	    ExpectedCondition <Boolean> pageLoadCondition = new
	        ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	            }
	        };
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}
	
	public static void waitForElementVisibleX(String locator){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	public boolean isElementPresent(By by) {
		try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	}
	
	// Switch to new window opened
	public void switchToWindow(){
	    for(String winHandle : driver.getWindowHandles()){
	       driver.switchTo().window(winHandle);
	    }
	}
}

