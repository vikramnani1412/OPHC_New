package patientObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class AppointmentConfirmedPage {
    
    //Rule-1: Finding WebElements Using @FindBy Annotations	
	
    @FindBy(xpath="//p[.='Booking ID']/following-sibling::div[contains(.,'')]")private WebElement BookingInfo;
    
    @FindBy(xpath="//button[.='Upload Document ']")private WebElement UploadDocumentBtn;
    
    @FindBy(xpath="//button[.='Add Notes ']")private WebElement AddNotesBtn;
    
    @FindBy(xpath="//button[.=' Reschedule Appointment ']")private WebElement RescheduleAppointmentBtn;
    
    @FindBy(xpath="//button[.=' Cancel Appointment ']")private WebElement CancelAppointmentBtn;
    
    @FindBy(xpath="//button[.=' Need Help? ']")private WebElement NeedHelpBtn;
    
    @FindBy(xpath="//button[.='Continue']")private WebElement ContinueBtn;
    
  
	//Rule-2: Create a constructor to initilise these elements    
    
	public AppointmentConfirmedPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	//Rule-3: Provide getters to access these variables
	
	public WebElement getBookingInfo() {
		return BookingInfo;
	}


	public WebElement getUploadDocumentBtn() {
		return UploadDocumentBtn;
	}


	public WebElement getAddNotesBtn() {
		return AddNotesBtn;
	}


	public WebElement getRescheduleAppointmentBtn() {
		return RescheduleAppointmentBtn;
	}


	public WebElement getCancelAppointmentBtn() {
		return CancelAppointmentBtn;
	}


	public WebElement getNeedHelpBtn() {
		return NeedHelpBtn;
	}


	public WebElement getContinueBtn() {
		return ContinueBtn;
	}

	// Business Library

	public String getBookingIDandClickContinue(WebDriver driver) throws Exception
	{
	    WebDriverUtility wUtil = new WebDriverUtility();

	    wUtil.waitForElementToBeVisible(driver, BookingInfo);

	    String bookingId = BookingInfo.getText().trim();
	    Thread.sleep(1000);
	    System.out.println("AppointmentConfirmedPage Booking ID : " + bookingId);
	    Thread.sleep(2000);
	    wUtil.scrollPageDown(1000);
	    wUtil.waitUntilElementVisibleUptoThirtyMin(driver, ContinueBtn);
	    ContinueBtn.click();

	    return bookingId;   // <-- return it
	}
	
	public String getBookingID() throws Exception
	{
	    Thread.sleep(2000);
	    String bookingId = BookingInfo.getText();
	    System.out.println("Booking ID is " + bookingId);
	    return bookingId;
	}
	
	public void getBookingIDandClickContinueBtn(WebDriver driver) throws Exception
	{
	    WebDriverUtility wUtil = new WebDriverUtility();

	    Thread.sleep(2000);
	    String bookingId = BookingInfo.getText();
	    Thread.sleep(2000);
	    System.out.println("Booking ID is " + bookingId);
	    Thread.sleep(2000);
	    wUtil.scrollPageDown(1000);
	    wUtil.waitForElementToBeVisible(driver, ContinueBtn);
	    ContinueBtn.click();
	    
	}
		
	
	
	public void DirectlyClickOnContinueBtn() throws Exception
	{
		Thread.sleep(2000);
		ContinueBtn.click();
	}

	
}
