package all;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PatientBaseClass;
import genericUtilities.WebDriverUtility;
import patientObjectRepository.AppointmentConfirmedPage;
import patientObjectRepository.AppointmentsPage;
import patientObjectRepository.DoctorDetailsPage;
import patientObjectRepository.FindDoctorsPage;
import patientObjectRepository.HowDoYouWantToConsultPage;
import patientObjectRepository.RazorpayNetBankingPage;
import patientObjectRepository.RazorpayOPHC;
import patientObjectRepository.UploadMedicalReportsAfterAppointmentConfirmPage;

@Listeners(genericUtilities.ListenersImplementationClass.class)
public class PatientBookingSlot extends PatientBaseClass {

	WebDriverUtility wUtil = new WebDriverUtility();
	JavaUtility jUtil = new JavaUtility();
	
	@Test()
	void PatientBookingSlotTest() throws Exception
	{	
        FindDoctorsPage fdocPage = new FindDoctorsPage(driver);
        fdocPage.selectingDoctor();
        Thread.sleep(2000);

        DoctorDetailsPage ddPage = new DoctorDetailsPage(driver);
        ddPage.BookingAppointment(driver);

        // ── Consultation type ─────────────────────────────────────────────
        HowDoYouWantToConsultPage hPage = new HowDoYouWantToConsultPage(driver);
        hPage.CompleteHowDoYouWantToConsultDetailsAndClickOnContinueBtn();
        Thread.sleep(2000);

        // ── Razorpay payment ──────────────────────────────────────────────
        WebElement Frame = driver.findElement(
                By.xpath("//iframe[@class='razorpay-checkout-frame']"));
        wUtil.waitForElementToBeClickable(driver, Frame);
        driver.switchTo().frame(Frame);

        RazorpayOPHC rPage = new RazorpayOPHC(driver);
        rPage.getNetBankingLnk().click();
        Thread.sleep(2000);

        RazorpayNetBankingPage rnPage = new RazorpayNetBankingPage(driver);
        rnPage.bookSlotUsingSBIbank(driver);
        Thread.sleep(2000);
        
        ExcelFileUtility eUtil = new ExcelFileUtility();
        String path = eUtil.generateSampleMedicalReport(".\\src\\test\\resources\\Reports","Medical Report","Doctor",1,5);

		Thread.sleep(2000);
        UploadMedicalReportsAfterAppointmentConfirmPage umraaPage = new UploadMedicalReportsAfterAppointmentConfirmPage(driver);
        umraaPage.uploadingMedicalReports(driver);

        AppointmentConfirmedPage acPage = new AppointmentConfirmedPage(driver);

        // Capture Booking ID before navigating
        String bookingId = acPage.getBookingIDandClickContinue(driver);

        System.out.println("Booking ID from Confirm Page: " + bookingId);

//        AppointmentsPage aPage = new AppointmentsPage(driver);
//        aPage.checkingAppointmentBookedOrNot(driver, bookingId);
    }
	
}