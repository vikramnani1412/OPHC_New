package patientObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import genericUtilities.WebDriverUtility;

public class AppointmentsPage {

    //Rule-1: Finding WebElements Using @FindBy Annotations
    
    @FindBy(xpath="//button[.='Upcoming']")private WebElement UpcomingBtn;
    
    @FindBy(xpath="//button[.='Past']")private WebElement PastBtn;
    
    @FindBy(xpath="//div[@class='appointment-list ng-star-inserted']")private WebElement UpcomingTotalAppointmentsList;
    
    @FindBy(xpath="((//div[@class='appointment-card status-default ng-star-inserted'])[1]//p[contains(.,'')])[1]")private WebElement FirstAppointment;
    
    @FindBy(xpath="//div[@class='appointments-container']")private WebElement PastTotalAppointmentsList;
    
    @FindBy(xpath="//i[@class='bi bi-three-dots-vertical']")private WebElement ThreeVerticalDots;
    
    @FindBy(xpath="//button[.=' Upload Report ']")private WebElement UploadReportBtn;
  
    @FindBy(xpath="//button[.=' Submit ']")private WebElement SubmitBtn;
    
    
//    @FindBy(xpath="//p[contains(.,'BK000730')]/../../following-sibling::div//i")private WebElement FirstAppointmentThreeVerticalDots;
  
  
	//Rule-2: Create a constructor to initilise these elements    
    
	public AppointmentsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//Rule-3: Provide getters to access these variables
	

	public WebElement getUpcomingBtn() {
		return UpcomingBtn;
	}


	public WebElement getFirstAppointment() {
		return FirstAppointment;
	}


	public WebElement getThreeVerticalDots() {
		return ThreeVerticalDots;
	}


	public WebElement getUploadReportBtn() {
		return UploadReportBtn;
	}


	public WebElement getSubmitBtn() {
		return SubmitBtn;
	}


	public WebElement getPastBtn() {
		return PastBtn;
	}


	public WebElement getUpcomingTotalAppointmentsList() {
		return UpcomingTotalAppointmentsList;
	}


	public WebElement getPastTotalAppointmentsList() {
		return PastTotalAppointmentsList;
	}

	// Business Library
	
	public void checkingAppointmentBookedOrNot(WebDriver driver, String bookingId) throws Exception
	{
	    WebDriverUtility wUtil = new WebDriverUtility();

	    WebElement ele = driver.findElement(By.xpath("//h6[.='Dr. xyug']/following-sibling::p[contains(.,'Booking ID: ')]"));

	    wUtil.waitForElementToBeVisible(driver, ele);

	    String Appointment = FirstAppointment.getText();

	    System.out.println("Appointment from Appointment Page: " + Appointment);
	    System.out.println("Booking ID from Confirm Page: " + bookingId);

	    Assert.assertTrue(
	        Appointment.contains(bookingId),
	        "Appointment not booked. Expected Booking ID: " 
	        + bookingId + " but found: " + Appointment
	    );

	    System.out.println("✅ Appointment Booked Successfully. Booking ID: " + bookingId);
	}
	
	public void uploadingReports(WebDriver driver, String BookingID, String Report) throws Exception
	{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[.='Booking ID: "+BookingID+"']")).click();
		Thread.sleep(2000);   
		UploadReportBtn.sendKeys(Report);
		Thread.sleep(2000);
		SubmitBtn.click();
	}
	
}
