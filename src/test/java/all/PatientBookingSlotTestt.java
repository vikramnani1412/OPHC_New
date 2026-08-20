package all;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.DataStore;
import genericUtilities.PatientBaseClass;
import genericUtilities.WebDriverUtility;

import patientObjectRepository.AppointmentConfirmedPage;
import patientObjectRepository.DoctorDetailsPage;
import patientObjectRepository.FindDoctorsPage;
import patientObjectRepository.HowDoYouWantToConsultPage;
import patientObjectRepository.RazorpayNetBankingPage;
import patientObjectRepository.RazorpayOPHC;
import patientObjectRepository.UploadMedicalReportsAfterAppointmentConfirmPage;

@Listeners(genericUtilities.ListenersImplementationClass.class)
public class PatientBookingSlotTestt extends PatientBaseClass {

    WebDriverUtility wUtil = new WebDriverUtility();

    @Test(priority = 2)
    public void PatientBookingSlotTest() throws Exception {

        System.out.println(
                "Doctor Selected for Patient Booking : "
                + DataStore.doctorName
        );

        // Select same doctor created in DoctorAddingSlotTest
        FindDoctorsPage fdocPage = new FindDoctorsPage(driver);

        fdocPage.selectingDoctor(
                driver,
                DataStore.doctorName
        );

        Thread.sleep(2000);

        DoctorDetailsPage ddPage = new DoctorDetailsPage(driver);

        ddPage.BookingAppointment(driver);

        // Consultation type
        HowDoYouWantToConsultPage hPage =
                new HowDoYouWantToConsultPage(driver);

        hPage.CompleteHowDoYouWantToConsultDetailsAndClickOnContinueBtn();

        Thread.sleep(2000);

        // Razorpay payment
        WebElement Frame = driver.findElement(
                By.xpath("//iframe[@class='razorpay-checkout-frame']")
        );

        wUtil.waitForElementToBeClickable(driver, Frame);

        driver.switchTo().frame(Frame);

        RazorpayOPHC rPage = new RazorpayOPHC(driver);

        rPage.getNetBankingLnk().click();

        Thread.sleep(2000);

        RazorpayNetBankingPage rnPage =
                new RazorpayNetBankingPage(driver);

        rnPage.bookSlotUsingSBIbank(driver);

        Thread.sleep(2000);

        // Upload medical report
        UploadMedicalReportsAfterAppointmentConfirmPage umraaPage =
                new UploadMedicalReportsAfterAppointmentConfirmPage(driver);

        umraaPage.uploadingMedicalReports(driver);

        // Appointment confirmation
        AppointmentConfirmedPage acPage =
                new AppointmentConfirmedPage(driver);

        String bookingId =
                acPage.getBookingIDandClickContinue(driver);

        System.out.println(
                "Booking ID from Confirm Page : " + bookingId
        );
    }
}