package patientObjectRepository;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtilities.WebDriverUtility;

public class RazorpayNetBankingPage {

    //Rule-1 :  Finding WebElements Using @FindBy Annotations
	
	@FindBy(xpath="//div[@data-value='SBIN']")private WebElement SBILnk;
	
	@FindBy(xpath="//div[@data-value='HDFC']")private WebElement HDFCLnk;
	
    @FindBy(xpath="//div[@data-value='ICIC']")private WebElement ICICILnk;
    
    @FindBy(xpath="//div[@data-value='UTIB']")private WebElement AxisLnk;
    
    @FindBy(xpath="//div[@data-value='BARB_R']")private WebElement BOBLnk;
    
    @FindBy(xpath="//span[@data-testid='More Banks']")private WebElement MoreBanksLnk;
    
    @FindBy(xpath="//button[.=' Continue']")private WebElement ContinueBtn;
    
    @FindBy(xpath="//button[.='Success']")private WebElement SuccessBtn;
    
    @FindBy(xpath="//button[.='Failure']")private WebElement FailureBtn;
  
	//Rule-2 : Create a constructor to initilise these elements    
    
	public RazorpayNetBankingPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	//Rule-3 : Provide getters to access these variables
	

	public WebElement getSBILnk() {
		return SBILnk;
	}


	public WebElement getHDFCLnk() {
		return HDFCLnk;
	}


	public WebElement getICICILnk() {
		return ICICILnk;
	}


	public WebElement getAxisLnk() {
		return AxisLnk;
	}


	public WebElement getBOBLnk() {
		return BOBLnk;
	}


	public WebElement getMoreBanksLnk() {
		return MoreBanksLnk;
	}


	public WebElement getContinueBtn() {
		return ContinueBtn;
	}


	public WebElement getSuccessBtn() {
		return SuccessBtn;
	}


	public WebElement getFailureBtn() {
		return FailureBtn;
	}

	
	// Business Library
	
	public void bookSlotUsingSBIbank(WebDriver driver) throws Exception {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    // Store parent window BEFORE clicking SBI
	    String parentWindow = driver.getWindowHandle();

	    // Store existing windows
	    Set<String> oldWindows = driver.getWindowHandles();

	    // Click SBI
	    wait.until(ExpectedConditions.elementToBeClickable(SBILnk)).click();

//	    // Wait for new window if SBI opens one
//	    wait.until(driver -> driver.getWindowHandles().size() > oldWindows.size());

	    // Find newly opened window
	    Set<String> newWindows = driver.getWindowHandles();

	    for (String window : newWindows) {

	        if (!oldWindows.contains(window)) {

	            driver.switchTo().window(window);

	            System.out.println("Switched to SBI window");

	            break;
	        }
	    }

	    // Now handle SBI/payment page
	    try {

	        WebElement paymentDeclined = new WebDriverWait(
	                driver, Duration.ofSeconds(5))
	                .until(ExpectedConditions.visibilityOfElementLocated(
	                        By.xpath("//div[.='Payment could not be completed']")
	                ));

	        if (paymentDeclined.isDisplayed()) {

	            System.out.println("Payment Declined");

	            wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//button[contains(@class,'absolute')]")
	            )).click();

	            wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//button[@title='Close Checkout']")
	            )).click();

	            wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//button[.='Yes, exit']")
	            )).click();
	        }

	    } catch (TimeoutException e) {

	        System.out.println("Payment Declined message not displayed.");
	    }

	    // Find Success button AFTER switching to SBI window
	    WebElement successBtn = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                    By.xpath("//button[normalize-space()='Success']")
	            )
	    );

	    successBtn.click();

	    System.out.println("Success button clicked.");

	    // Switch back to OPHC parent window
	    if (driver.getWindowHandles().contains(parentWindow)) {
	        driver.switchTo().window(parentWindow);
	    }
		
	}

}




