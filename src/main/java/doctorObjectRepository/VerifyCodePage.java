package doctorObjectRepository;

import java.security.PublicKey;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.JavaUtility;
import genericUtilities.WebDriverUtility;

public class VerifyCodePage {

	// Finding WebElements Using @FindBy Annotations
    
    //@FindAll({@FindBy(xpath = "(//input[@inputmode='numeric'])[1]"), @FindBy(xpath = "(//input[@type='text'])[1]")}) private WebElement OtpFirstBox;
    
//    @FindBys({@FindBy(tagName = "input"), @FindBy(id = "username")})
    @FindBy(xpath="(//input[@inputmode='numeric'])[1]")private WebElement OtpFirstBox;
    
    @FindBy(xpath = "//div[contains(text(),'Invalid OTP code')]")private WebElement InvalidOTPTxt;
    
//    @FindBy(xpath="(//div[contains(.,'Invalid OTP code')])[8]")private WebElement InvalidOTPTxt;
    
    @FindBy(xpath="//a[.=' Resend ']")private WebElement ResendLnk;
    
    @FindBy(xpath="//button[.=' Verify ']")private WebElement VerifyBtn;
  
	//Rule-3:Create a constructor to initilise these elements    
    
	public VerifyCodePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	

	//Rule-4:Provide getters to access these variables
	
	public WebElement getOtpFirstBox() {
		return OtpFirstBox;
	}


	public WebElement getInvalidOTPTxt() {
		return InvalidOTPTxt;
	}


	public WebElement getResendLnk() {
		return ResendLnk;
	}


	public WebElement getVerifyBtn() {
		return VerifyBtn;
	}
	
	//Business Library
	public void enteringOtpAndClickOnVerifyBtn(WebDriver driver) throws Exception {
	    WebDriverUtility wUtil = new WebDriverUtility();

	    Thread.sleep(2000);
	    OtpFirstBox.sendKeys("123456");
	    Thread.sleep(2000);

	    // Click Verify button using WebDriverUtility
	    wUtil.waitForElementToBeClickable(driver, VerifyBtn);
	    VerifyBtn.click();
	    Thread.sleep(2000);

	    // Safely verify whether "Invalid OTP" message appeared
	    boolean isInvalid = false;
	    try {
	        isInvalid = InvalidOTPTxt.isDisplayed();
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        // Element is absent from DOM -> OTP was valid
	        isInvalid = false;
	    }

	    if (isInvalid) {
	        System.out.println("Invalid OTP entered");
	    } else {
	        System.out.println("OTP Accepted");
	    }
	}
	
	
	public void enteringOtpAndClickOnVerifyBtnNegative(WebDriver driver) throws Exception
	{
		WebDriverUtility wUtil = new WebDriverUtility();
		JavaUtility jUtil = new JavaUtility();
		
		Thread.sleep(2000);
		OtpFirstBox.sendKeys("");
		Thread.sleep(2000);
		wUtil.takeScreenShot(driver, "J_Without Entering OTP Verify Button Disabled");
		OtpFirstBox.sendKeys("123");
		Thread.sleep(2000);
		wUtil.takeScreenShot(driver, "K_Entering OTP 3 digit OTP Verify Button Disabled");
		Thread.sleep(2000);
		wUtil.pressBackspaceKeyThreeTimes();
		Thread.sleep(2000);
		OtpFirstBox.sendKeys("123456");
		Thread.sleep(2000);
		wUtil.waitForElementToBeClickable(driver, VerifyBtn);
		VerifyBtn.click();
		Thread.sleep(2000);
	}


	
	
}
