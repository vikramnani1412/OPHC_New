package all;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import adminObjectRepository.AdminDashboardPage;
import adminObjectRepository.AdminLoginPage;
import adminObjectRepository.DrDocumentsPage;
import adminObjectRepository.DrIdentityProofPage;
import adminObjectRepository.DrKycManagementPage;
import adminObjectRepository.PatientHomePage;
import doctorObjectRepository.ApplicationFormPage;
import doctorObjectRepository.DocumentUploadPage;
import doctorObjectRepository.DocumentsUploadAfterKycRejecting;
import doctorObjectRepository.LoginPage;
import doctorObjectRepository.LogoutPage;
import doctorObjectRepository.ProfileUnderVerificationPage;
import doctorObjectRepository.RegisterPage;
import doctorObjectRepository.VerifyCodePage;
import doctorObjectRepository.WelcomePage;
import genericUtilities.DataStore;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import patientObjectRepository.AppointmentConfirmedPage;
import patientObjectRepository.AppointmentsPage;
import patientObjectRepository.DoctorDetailsPage;
import patientObjectRepository.FeeDetailsPage;
import patientObjectRepository.FindDoctorsPage;
import patientObjectRepository.HowDoYouWantToConsultPage;
import patientObjectRepository.PatientDetailsPage;
import patientObjectRepository.PatientLoginPage;
import patientObjectRepository.PatientProfilePage;
import patientObjectRepository.PatientRegisterPage;
import patientObjectRepository.PatientVerifyCodePage;
import patientObjectRepository.RazorpayNetBankingPage;
import patientObjectRepository.RazorpayOPHC;
import patientObjectRepository.UploadMedicalReportsAfterAppointmentConfirmPage;

@Listeners(genericUtilities.ListenersImplementationClass.class)
public class UpdatedOphcTest {

    WebDriverUtility    wUtil = new WebDriverUtility();
    JavaUtility         jUtil = new JavaUtility();
    ExcelFileUtility    eUtil = new ExcelFileUtility();
    PropertyFileUtility pUtil = new PropertyFileUtility();

    // URLs & admin credentials
    String doctorURL;
    String adminURL;
    String adminUsername;
    String adminPassword;
    String patientURL;

    // Doctor identity
    String fakeName;
    String doctorName;
    String mobileNumber;

    // Doctor document paths
    String imagePath;
    String medicalCertificate;
    String nmcCertificate;
    String aadhar;
    String pan;
    String experience;
    String affiliationProof;

    // KYC / fee / rating data
    String firstRating;
    String consultancyFee;
    String editFirstRating;
    String editConsultancyFee;
    String finalRating;
    String reasonForRejection;

    int doctorNumber = 1;

    // Patient data
    String patientFullName;
    String patientEmail;
    String patientPhoneNo;
    String patientOTP;
    String phoneNumber;

    // ------------------------------------------------------------------
    // Shared driver-creation helper — replaces 6 duplicated blocks.
    // Every test still gets its own fresh browser/session (matches the
    // app's separate doctor/admin/patient portals), but the setup code
    // and options now live in one place.
    // ------------------------------------------------------------------
    
    private WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--start-maximized");
        
        Map<String, Object> prefs = new HashMap<>();

        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);

        options.setExperimentalOption("prefs", prefs);

        WebDriver driver =
                new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(30)
        );

        return driver;
    }
    
    
//    private WebDriver createDriver() {
//        WebDriverManager.chromedriver().setup();
//
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-notifications");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--disable-infobars");
//        options.addArguments("--disable-save-password-bubble");
//
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("credentials_enable_service", false);
//        prefs.put("profile.password_manager_enabled", false);
//        prefs.put("profile.default_content_setting_values.notifications", 2);
//        options.setExperimentalOption("prefs", prefs);
//
//        WebDriver driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//        return driver;
//    }
//    
//
    @DataProvider(name = "mobileNumbers")
    public Object[][] mobileNumbers() throws Exception {

        List<String> mobileNumbers = new ArrayList<>();

        for (int row = 27; row <= 35; row++) {
            String mobileNumber = eUtil.readDataFromExcel("Doctor", row, 1);
            if (mobileNumber != null && !mobileNumber.trim().isEmpty()) {
                mobileNumbers.add(mobileNumber.trim());
                System.out.println("Excel Row " + row + " : " + mobileNumber);
            }
        }

        if (mobileNumbers.isEmpty()) {
            throw new Exception("No mobile numbers found from Excel rows 27 to 35.");
        }

        return new Object[][] { { mobileNumbers } };
    }

    // Doctor Registration
    @Test(priority = 1, dataProvider = "mobileNumbers")
    public void DoctorRegisteringToOphcTest(List<String> mobileNumbers) throws Exception {

        doctorURL          = pUtil.readDataFromPropertyFile("doctorUrl");
        imagePath          = eUtil.readDataFromExcel("Doctor", 4, 1);
        medicalCertificate = eUtil.readDataFromExcel("Doctor", 5, 1);
        nmcCertificate     = eUtil.readDataFromExcel("Doctor", 6, 1);
        aadhar             = eUtil.readDataFromExcel("Doctor", 7, 1);
        pan                = eUtil.readDataFromExcel("Doctor", 8, 1);
        experience         = eUtil.readDataFromExcel("Doctor", 9, 1);
        affiliationProof   = eUtil.readDataFromExcel("Doctor", 10, 1);

        doctorName = jUtil.getRandomSingleName().trim().split(" ")[0];
        DataStore.doctorName = doctorName;
        DataStore.email = doctorName + "@gmail.com";

        System.out.println("========================================");
        System.out.println("Registration Started");
        System.out.println("Doctor Name : " + DataStore.doctorName);
        System.out.println("========================================");

        WebDriver driver = createDriver();

        try {
            driver.get(doctorURL);

            LoginPage lPage = new LoginPage(driver);
            wUtil.waitForElementToBeClickable(driver, lPage.getRegisterLnk());
            lPage.getRegisterLnk().click();

            RegisterPage rPage = new RegisterPage(driver);

            // Returns the first unique mobile number accepted by the app.
            String uniqueMobileNumber =
                    rPage.RegisterToDoctorApplication(driver, doctorName, doctorName + "@gmail.com", mobileNumbers);

            mobileNumber = uniqueMobileNumber;
            DataStore.mobileNumber = uniqueMobileNumber;
            System.out.println("Final Unique Mobile Number : " + DataStore.mobileNumber);

            VerifyCodePage vcPage = new VerifyCodePage(driver);
            vcPage.enteringOtpAndClickOnVerifyBtn(driver);
            Thread.sleep(2000);

            ApplicationFormPage afPage = new ApplicationFormPage(driver);
            afPage.uploadDoctorDetails(driver, imagePath);
            Thread.sleep(2000);

            wUtil.scrollPageUp(2);

            driver.findElement(By.xpath("//span[.='Medical Degree  Certificate']/../preceding-sibling::input")).sendKeys(medicalCertificate);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[.='State Medical Council Certificate']/../preceding-sibling::input")).sendKeys(nmcCertificate);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[.='Aadhaar Card']/../preceding-sibling::input")).sendKeys(aadhar);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[.='PAN Card']/../preceding-sibling::input")).sendKeys(pan);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[.='Experience  Certificate']/../preceding-sibling::input")).sendKeys(experience);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[.='Clinic / Hospital  Affiliation Proof']/../preceding-sibling::input")).sendKeys(affiliationProof);
            Thread.sleep(2000);

            DocumentUploadPage duPage = new DocumentUploadPage(driver);
            duPage.documentsUploading(driver, medicalCertificate, nmcCertificate, aadhar, pan, experience, affiliationProof);
            Thread.sleep(2000);

            ProfileUnderVerificationPage puvPage = new ProfileUnderVerificationPage(driver);
            puvPage.clickOnLogoutBtn(driver);
            Thread.sleep(2000);

            LogoutPage lOutPage = new LogoutPage(driver);
            lOutPage.clickOnYesLogoutBtn();

            System.out.println("========================================");
            System.out.println("Registration Completed");
            System.out.println("Doctor Name : " + DataStore.doctorName);
            System.out.println("Mobile     : " + DataStore.mobileNumber);
            System.out.println("========================================");

        } finally {
            driver.quit();
        }
    }

    @Test(dependsOnMethods = "DoctorRegisteringToOphcTest")
    public void AdminRejectingDoctorTest() throws Throwable {

        adminURL           = pUtil.readDataFromPropertyFile("adminUrl");
        adminUsername      = pUtil.readDataFromPropertyFile("adminusername");
        adminPassword      = pUtil.readDataFromPropertyFile("adminpassword");
        firstRating        = eUtil.readDataFromExcel("Doctor", 11, 1);
        consultancyFee     = eUtil.readDataFromExcel("Doctor", 12, 1);
        editFirstRating    = eUtil.readDataFromExcel("Doctor", 11, 2);
        editConsultancyFee = eUtil.readDataFromExcel("Doctor", 12, 2);
        reasonForRejection = eUtil.readDataFromExcel("Doctor", 3, 3);

        WebDriver driver = createDriver();

        try {
            driver.get(adminURL);

            AdminLoginPage alPage = new AdminLoginPage(driver);
            alPage.loginToAdmin(driver, adminUsername, adminPassword);
            Thread.sleep(2000);

            AdminDashboardPage adPage = new AdminDashboardPage(driver);
            adPage.clickOnDoctorIcon(driver);
            Thread.sleep(2000);

            System.out.println("Admin Started Visiting " + DataStore.doctorName + " Profile");

            DrKycManagementPage kycmngPage = new DrKycManagementPage(driver);
            kycmngPage.ComparingNewlyRegisteredDoctorAndFirstDoctorInAdminPannelAndClickPreviewBtn(
                    driver, DataStore.doctorName, doctorNumber);
            Thread.sleep(2000);

            DrDocumentsPage ddPage = new DrDocumentsPage(driver);
            ddPage.RejectingDoctorDocuments(driver, reasonForRejection);
            Thread.sleep(2000);

            System.out.println("Registered Doctor Rejected by Admin");

        } finally {
            driver.quit();
        }
    }

    /**
     * Re-uploads a single rejected document ONLY if the "Re-Upload" option
     * is actually shown for it. documentName must be the NORMALIZED label
     * text (single spaces, no leading/trailing whitespace) as it will
     * render in the DOM after normalize-space() — e.g. pass
     * "Medical Degree Certificate", not "Medical Degree  Certificate".
     * Uses findElements (never findElement) so a missing/approved document
     * is skipped instead of throwing.
     */
    private boolean uploadRejectedDocument(WebDriver driver, String documentName, String filePath) {
        try {
            String reUploadXpath =
                    "//span[normalize-space()='" + documentName + "']"
                  + "/../following-sibling::span[normalize-space()='Re-Upload']";

            List<WebElement> reUploadElements = driver.findElements(By.xpath(reUploadXpath));

            if (reUploadElements.isEmpty() || !reUploadElements.get(0).isDisplayed()) {
                System.out.println(documentName + " -> Re-Upload not found. Skipping.");
                return false;
            }

            System.out.println(documentName + " -> Re-Upload found. Uploading...");

            String fileInputXpath =
                    "//span[normalize-space()='" + documentName + "']"
                  + "/../preceding-sibling::input[@type='file']";

            List<WebElement> fileInputs = driver.findElements(By.xpath(fileInputXpath));

            if (fileInputs.isEmpty()) {
                System.out.println(documentName + " -> File input not found.");
                return false;
            }

            fileInputs.get(0).sendKeys(filePath);
            System.out.println(documentName + " -> Uploaded successfully.");
            return true;

        } catch (Exception e) {
            System.out.println(documentName + " -> Upload failed: " + e.getMessage());
            return false;
        }
    }

    @Test(dependsOnMethods = "AdminRejectingDoctorTest")
    public void RejectedDoctorReuploadingDocumentsTest() throws Exception {

        doctorURL          = pUtil.readDataFromPropertyFile("doctorUrl");
        medicalCertificate = eUtil.readDataFromExcel("Doctor", 5, 1);
        nmcCertificate     = eUtil.readDataFromExcel("Doctor", 6, 1);
        aadhar             = eUtil.readDataFromExcel("Doctor", 7, 1);
        pan                = eUtil.readDataFromExcel("Doctor", 8, 1);
        experience         = eUtil.readDataFromExcel("Doctor", 9, 1);
        affiliationProof   = eUtil.readDataFromExcel("Doctor", 10, 1);

        WebDriver driver = createDriver();

        try {
            driver.get(doctorURL);
            System.out.println("Doctor Re Started Registration");

            LoginPage lPage = new LoginPage(driver);
            lPage.loginToDoctor(DataStore.mobileNumber);
            Thread.sleep(1000);

            VerifyCodePage vcPage = new VerifyCodePage(driver);
            vcPage.enteringOtpAndClickOnVerifyBtn(driver);
            Thread.sleep(1000);

            // Check for the rejection banner WITHOUT throwing if it's absent
            // (findElements never throws NoSuchElementException).
            List<WebElement> profileRejectMsg =
                    driver.findElements(By.xpath("//h6[.='Profile Verification Failed']"));

            if (profileRejectMsg.isEmpty() || !profileRejectMsg.get(0).isDisplayed()) {
                System.out.println("No rejection banner found — nothing to re-upload. Skipping re-submission.");
                return;
            }

            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[text()='Re-Upload']")).click();
            Thread.sleep(2000);

            // Only documents that actually show "Re-Upload" get touched —
            // everything already approved/unaffected is left alone.
            boolean anyUploaded = false;
            anyUploaded |= uploadRejectedDocument(driver, "Medical Degree Certificate", medicalCertificate);
            anyUploaded |= uploadRejectedDocument(driver, "State Medical Council Certificate", nmcCertificate);
            anyUploaded |= uploadRejectedDocument(driver, "Aadhaar_Card.jpeg", aadhar);
            anyUploaded |= uploadRejectedDocument(driver, "PAN_Card.jpeg", pan);
            anyUploaded |= uploadRejectedDocument(driver, "Experience_Certificate.jpeg", experience);
            anyUploaded |= uploadRejectedDocument(driver, "Clinic_Hospital_Affiliation_Proof.jpeg", affiliationProof);

            Thread.sleep(2000);

            if (!anyUploaded) {
                System.out.println("WARNING: Re-Upload banner was shown but no individual document matched — check locator/label text against the live DOM.");
            }
            DocumentUploadPage duPage = new DocumentUploadPage(driver);
            Thread.sleep(2000);
            
            wUtil.waitUntilElementToBeClickableUsingJavaScriptExecutor(driver, duPage.getSubmitDocumentsBtn());
            Thread.sleep(2000);

            ProfileUnderVerificationPage puvPage = new ProfileUnderVerificationPage(driver);
            puvPage.clickOnLogoutBtn(driver);

            System.out.println("Re Submission Completed");

        } finally {
            driver.quit();
        }
    }

    
    // HAVE TO CHECK FROM HERE
    
    
    // AdminApprovingNewlyAddedDoctorTest
    @Test(dependsOnMethods = "RejectedDoctorReuploadingDocumentsTest")
    public void AdminApprovingNewlyAddedDoctorTest() throws Throwable {

        adminURL           = pUtil.readDataFromPropertyFile("adminUrl");
        adminUsername      = pUtil.readDataFromPropertyFile("adminusername");
        adminPassword      = pUtil.readDataFromPropertyFile("adminpassword");
        firstRating        = eUtil.readDataFromExcel("Doctor", 11, 1);
        consultancyFee     = eUtil.readDataFromExcel("Doctor", 12, 1);
        editFirstRating    = eUtil.readDataFromExcel("Doctor", 11, 2);
        editConsultancyFee = eUtil.readDataFromExcel("Doctor", 12, 2);
        reasonForRejection = eUtil.readDataFromExcel("Doctor", 3, 3);

        WebDriver driver = createDriver();

        try {
            driver.get(adminURL);
            System.out.println("Admin Started Visiting " + DataStore.doctorName + " Profile");

            AdminLoginPage alPage = new AdminLoginPage(driver);
            alPage.loginToAdmin(driver, adminUsername, adminPassword);
            Thread.sleep(2000);

            AdminDashboardPage adPage = new AdminDashboardPage(driver);
            adPage.clickOnDoctorIcon(driver);
            Thread.sleep(2000);

            DrKycManagementPage kycmngPage = new DrKycManagementPage(driver);
            kycmngPage.ComparingNewlyRegisteredDoctorAndFirstDoctorInAdminPannelAndClickPreviewBtn(
                    driver, DataStore.doctorName, doctorNumber);
            Thread.sleep(2000);

            DrDocumentsPage ddPage = new DrDocumentsPage(driver);
            ddPage.ApprovingAllDocuments(driver, 4, consultancyFee);
            Thread.sleep(2000);

            System.out.println("Registered Doctor Approved by Admin");

        } finally {
            driver.quit();
        }
    }

    // doctor sets availability slots
    @Test(priority = 5, dependsOnMethods = "AdminApprovingNewlyAddedDoctorTest")
    public void loginToDoctorPannelSettingDoctorAvailabilityTest() throws Throwable {

        doctorURL = pUtil.readDataFromPropertyFile("doctorUrl");

        WebDriver driver = createDriver();

        try {
            driver.get(doctorURL);

            LoginPage lPage = new LoginPage(driver);
            lPage.loginToDoctor(DataStore.mobileNumber);
            Thread.sleep(2000);

            VerifyCodePage vcPage = new VerifyCodePage(driver);
            vcPage.enteringOtpAndClickOnVerifyBtn(driver);
            Thread.sleep(2000);

            WelcomePage wPage = new WelcomePage(driver);
            wPage.DoctorAddingSlot(driver);

            System.out.println("Doctor Availability Slot Added Successfully");

        } finally {
            driver.quit();
        }
    } // 4,5 Methods quit commented while execution passed

    // patient registers and books appointment
    @Test(priority = 6, dependsOnMethods = "loginToDoctorPannelSettingDoctorAvailabilityTest")
    public void PatientRegisteringAndBookingSameDoctorTest() throws Exception {

        patientFullName = jUtil.getRandomSingleName();
        patientEmail    = patientFullName + "@gmail.com";
        patientPhoneNo  = jUtil.getRandomMobileNum();
        patientOTP      = pUtil.readDataFromPropertyFile("potp");
        patientURL      = pUtil.readDataFromPropertyFile("patientUrl");

        WebDriver driver = createDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        try {
            driver.get(patientURL);
            System.out.println("Patient Registration Started " + patientPhoneNo);

            // ── Patient registration ──────────────────────────────────────
//            PatientHomePage phPage = new PatientHomePage(driver);
//            phPage.getLoginBtn().click();

            PatientLoginPage plPage = new PatientLoginPage(driver);
            plPage.clickOnRegisterLnk(driver);

            PatientRegisterPage prPage = new PatientRegisterPage(driver);
            prPage.registerAsPatient(patientFullName, patientEmail, patientPhoneNo);

            PatientVerifyCodePage vcPage = new PatientVerifyCodePage(driver);
            vcPage.enterOtpAndClickVerifyBtn(patientOTP);

            driver.findElement(By.className("profile-avatar")).click();

            WebElement nameElement = driver.findElement(
                    By.xpath("//h4[contains(text(),'" + patientFullName + "')]"));
            wUtil.waitForElementToBeVisible(driver, nameElement);

            if (nameElement.isDisplayed()) {
                String visibleName = nameElement.getText().trim();
                System.out.println("Expected Patient Name : " + patientFullName);
                System.out.println("Visible Patient Name  : " + visibleName);
                Assert.assertEquals(visibleName, patientFullName,
                        "Name mismatch! Expected: " + patientFullName + " but got: " + visibleName);
            }

            // ── Find doctor & select slot ───────────────────────────────────
            PatientProfilePage pPage = new PatientProfilePage(driver);
            pPage.getPageCloseBtn().click();
            Thread.sleep(2000);

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

            PatientDetailsPage pdPage = new PatientDetailsPage(driver);
            pdPage.givingPatientDetails(patientFullName);
            
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
            

        } finally {
            driver.quit();
        }
    }
}
