package all;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import doctorObjectRepository.WelcomePage;
import genericUtilities.DoctorBaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.WebDriverUtility;
import patientObjectRepository.AppointmentConfirmedPage;
import patientObjectRepository.AppointmentsPage;
import patientObjectRepository.DoctorDetailsPage;
import patientObjectRepository.FindDoctorsPage;
import patientObjectRepository.HowDoYouWantToConsultPage;
import patientObjectRepository.PatientDetailsPage;
import patientObjectRepository.RazorpayNetBankingPage;
import patientObjectRepository.RazorpayOPHC;
import patientObjectRepository.UploadMedicalReportsAfterAppointmentConfirmPage;

@Listeners(genericUtilities.ListenersImplementationClass.class)
public class DoctorAddingSlot extends DoctorBaseClass {
	
	@Test(priority = 1, groups = {"slotCreation"})
	public void doctorAddingSlot() throws Exception
	{
		WelcomePage wPage = new WelcomePage(driver);
        wPage.DoctorAddingSlot(driver);
        System.out.println("Doctor Availability Slot Added Successfully");
	}
	
	
	
}
