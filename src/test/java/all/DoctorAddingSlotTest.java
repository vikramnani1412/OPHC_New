package all;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import doctorObjectRepository.WelcomePage;
import genericUtilities.DataStore;
import genericUtilities.DoctorBaseClass;

@Listeners(genericUtilities.ListenersImplementationClass.class)
public class DoctorAddingSlotTest extends DoctorBaseClass {

    @Test(priority = 1)
    public void doctorAddingSlot() throws Exception {

        WelcomePage wPage = new WelcomePage(driver);

        // Capture logged-in doctor name
        DataStore.doctorName = wPage.getDoctorName();

        System.out.println(
                "Doctor Name Stored : " + DataStore.doctorName
        );

        // Add availability slot
        wPage.DoctorAddingSlot(driver);

        System.out.println(
                "Doctor Availability Slot Added Successfully"
        );
    }
}