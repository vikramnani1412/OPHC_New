package doctorObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.WebDriverUtility;

public class ApplicationFormPage {

	//Finding WebElements Using @FindBy Annotations
	
    @FindBy(xpath="//button[.='Choose File']")private WebElement ChooseFileBtn;
    
    @FindBy(xpath="//input[@type='file']")private WebElement InputFile;
    
    @FindBy(xpath="//button[.=' Preview AI Look ']")private WebElement PreviewAILookBtn;
    
    @FindBy(xpath="//i[@class='fa-regular fa-trash-can']")private WebElement ProfilePicDeleteBtn;
    
    @FindBy(xpath="//h6[.='Doctor_profile_jpg']")private WebElement DrProfileNameScrolling;
    
    @FindBy(xpath="//input[@formcontrolname='fullName']")private WebElement FullNameEdt;
    
    @FindBy(xpath="//input[@formcontrolname='smcRegistrationNumber']")private WebElement StateMedicalCouncilRegistrationNoEdt;
    
    @FindBy(xpath="(//div[.=' Select State Council '])[2]")private WebElement StateMedicalCouncilDrpdwn;
    
    @FindBy(xpath="//input[@formcontrolname='nmcNumber']")private WebElement NmcNumberEdt;
    
    @FindBy(xpath="(//div[.=' Select Graduation Degree '])[2]")private WebElement GraduationDegreeDrpDwn;
    
    @FindBy(xpath="(//div[.=' Select PG '])[2]")private WebElement PostGraduationDegreeDrpDwn;
    
    @FindBy(xpath="//div[@class='custom-dropdown']/div[.=' Select Specialization ']")private WebElement SpecializationDrpdwn;
    
    @FindBy(xpath="//label[.=' Super Specialization Degree ']/following-sibling::div/div[.=' Select Super Specialisty Qualification ']")private WebElement SuperSpecializationDegreeDrpdwn;
    
    @FindBy(xpath="//label[.=' Super Specialization ']/following-sibling::div/div[.=' Select Super Specialization ']")private WebElement SuperSpecializationDrpdwn;
    
    @FindBy(xpath="//input[@formcontrolname='additionalQualification']")private WebElement AdditionalQualificationEdt;
    
    @FindBy(xpath="//div[@class='custom-dropdown']/div[.=' Select Experience ']")private WebElement ExperianceDrpDwn;
    
    @FindBy(xpath="//label[text()=' Languages Spoken ']/following-sibling::div/div[.=' Select Languages ']")private WebElement LanguagesDrpdwn;
    
    @FindBy(xpath="//input[@placeholder='Enter hospital/clinic name']")private WebElement CurrentHospitalEdt;
    
    @FindBy(xpath="//input[@formcontrolname='consultationFees']")private WebElement ExpectedConsultationFees;
    
    @FindBy(xpath="//textarea[@formcontrolname='expertise']")private WebElement ExpertiseEdt;
    
    @FindBy(xpath="//div[@data-placeholder='Enter about yourself...']")private WebElement AboutYourselfTextarea;
    
    @FindBy(xpath="//div[.=' Submit ']")private WebElement SubmitBtn;
    
    
	//Rule-3:Create a constructor to initilise these elements    
    
	public ApplicationFormPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	//Rule-4:Provide getters to access these variables
	
	public WebElement getChooseFileBtn() {
		return ChooseFileBtn;
	}


	public WebElement getInputFile() {
		return InputFile;
	}


	public WebElement getPreviewAILookBtn() {
		return PreviewAILookBtn;
	}


	public WebElement getProfilePicDeleteBtn() {
		return ProfilePicDeleteBtn;
	}


	public WebElement getDrProfileNameScrolling() {
		return DrProfileNameScrolling;
	}


	public WebElement getFullNameEdt() {
		return FullNameEdt;
	}


	public WebElement getStateMedicalCouncilRegistrationNoEdt() {
		return StateMedicalCouncilRegistrationNoEdt;
	}


	public WebElement getStateMedicalCouncilDrpdwn() {
		return StateMedicalCouncilDrpdwn;
	}


	public WebElement getNmcNumberEdt() {
		return NmcNumberEdt;
	}


	public WebElement getGraduationDegreeDrpDwn() {
		return GraduationDegreeDrpDwn;
	}


	public WebElement getPostGraduationDegreeDrpDwn() {
		return PostGraduationDegreeDrpDwn;
	}


	public WebElement getSpecializationDrpdwn() {
		return SpecializationDrpdwn;
	}


	public WebElement getSuperSpecializationDegreeDrpdwn() {
		return SuperSpecializationDegreeDrpdwn;
	}


	public WebElement getSuperSpecializationDrpdwn() {
		return SuperSpecializationDrpdwn;
	}


	public WebElement getAdditionalQualificationEdt() {
		return AdditionalQualificationEdt;
	}


	public WebElement getExperianceDrpDwn() {
		return ExperianceDrpDwn;
	}


	public WebElement getLanguagesDrpdwn() {
		return LanguagesDrpdwn;
	}


	public WebElement getCurrentHospitalEdt() {
		return CurrentHospitalEdt;
	}


	public WebElement getExpectedConsultationFees() {
		return ExpectedConsultationFees;
	}


	public WebElement getExpertiseEdt() {
		return ExpertiseEdt;
	}


	public WebElement getAboutYourselfTextarea() {
		return AboutYourselfTextarea;
	}


	public WebElement getSubmitBtn() {
		return SubmitBtn;
	}
	
	//Business Library
	
   	public void uploadDoctorDetails(WebDriver driver, String ImagePath) throws Exception
    {
    	WebDriverUtility wUtil = new WebDriverUtility();
    	ExcelFileUtility eUtil = new ExcelFileUtility();
    	JavaUtility jUtil = new JavaUtility();
    	
    	String AboutYourself = eUtil.readDataFromExcel("Doctor", 24, 1);
    	
    	System.out.println("Driver Entered Application Form Page waiting to click Choose File Btn");
    	
    	wUtil.waitForElementToBeClickable(driver, ChooseFileBtn);
    	ChooseFileBtn.click();
//        driver.findElement(By.xpath("//button[.='Choose File']")).click();
        
        Thread.sleep(2000);
        InputFile.sendKeys(ImagePath);
//        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(ImagePath);
        
        Thread.sleep(2000);
    	
    	wUtil.clickOnEscapeButton();
    	
    	Thread.sleep(2000);
    	String smcrn = String.valueOf(jUtil.getRandomNum());
    	StateMedicalCouncilRegistrationNoEdt.sendKeys(smcrn);
    	
    	Thread.sleep(2000);
    	wUtil.scrollToParticularWebElement(driver, StateMedicalCouncilDrpdwn);
    	
    	Thread.sleep(2000);
    	StateMedicalCouncilDrpdwn.click();
    	driver.findElement(By.xpath("//li[.=' Telangana State Medical Council (TSMC) ']")).click();
    	
    	Thread.sleep(2000);
    	wUtil.scrollToParticularWebElement(driver, NmcNumberEdt);
    	
    	Thread.sleep(2000);
    	String nmcn = String.valueOf(jUtil.getRandomNum());
    	NmcNumberEdt.sendKeys(nmcn);
    	
    	Thread.sleep(2000);
    	wUtil.scrollToParticularWebElement(driver, GraduationDegreeDrpDwn);
    	
    	Thread.sleep(2000);
    	GraduationDegreeDrpDwn.click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//li[.=' MBBS ']")).click();    	
    	
    	Thread.sleep(2000);
    	PostGraduationDegreeDrpDwn.click();
    	driver.findElement(By.xpath("//li[.=' MD ']")).click();
    	
    	Thread.sleep(2000);
    	wUtil.scrollToParticularWebElement(driver, SpecializationDrpdwn);
    	
    	Thread.sleep(2000);
    	SpecializationDrpdwn.click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//li[.=' Aerospace Medicine ']")).click();
    	
    	Thread.sleep(2000);
    	SuperSpecializationDegreeDrpdwn.click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//li[.=' DM ']")).click();
    	Thread.sleep(2000);
    	
    	Thread.sleep(2000);
    	wUtil.scrollToParticularWebElement(driver, SuperSpecializationDrpdwn);
    	
    	SuperSpecializationDrpdwn.click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//li[.=' Child & Adolescent Psychiatry ']")).click();
    	
    	Thread.sleep(2000);
    	AdditionalQualificationEdt.sendKeys("MBBS");
    	
    	Thread.sleep(2000);
    	wUtil.scrollToParticularWebElement(driver, ExperianceDrpDwn);
    	
    	Thread.sleep(2000);
    	ExperianceDrpDwn.click();
    	driver.findElement(By.xpath("//li[.=' 5 years ']")).click();
    	
    	Thread.sleep(2000);
    	LanguagesDrpdwn.click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//li[.=' English ']")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//li[.=' Telugu ']")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//li[.=' Hindi ']")).click();
//    	Thread.sleep(2000);
//    	LanguagesDrpdwn.click();
    	
    	Thread.sleep(2000);
    	wUtil.scrollToParticularWebElement(driver, CurrentHospitalEdt);
    	
    	Thread.sleep(2000);
    	CurrentHospitalEdt.sendKeys("Narayana Super Speciality Hospital");
    	
    	Thread.sleep(2000);
    	ExpectedConsultationFees.sendKeys("2000");
    	
    	Thread.sleep(2000);
    	ExpertiseEdt.sendKeys("Neurosurgery");
    	
    	Thread.sleep(2000);
    	wUtil.scrollToParticularWebElement(driver, AboutYourselfTextarea);
    	
        Thread.sleep(2000);
    	AboutYourselfTextarea.sendKeys(AboutYourself);
    	
    	Thread.sleep(2000);
		wUtil.waitUntilElementVisibleUptoThirtyMin(driver, SubmitBtn);
		Thread.sleep(2000);
    	wUtil.scrollToParticularWebElement(driver, SubmitBtn);
    	Thread.sleep(2000);
		SubmitBtn.click();
    	    
    }
   	
   	
	public void UploadDoctorDetailsNegative(WebDriver driver, String ImagePath, String Name) throws Exception
   	{
//   		WebDriverUtility wUtil = new WebDriverUtility();
//    	ExcelFileUtility eUtil = new ExcelFileUtility();
//    	JavaUtility jUtil = new JavaUtility();
//    	
//    	String AboutYourself = eUtil.readDataFromExcel("Doctor", 24, 1);
//    	
//        driver.findElement(By.xpath("//button[.='Choose File']")).click();
//        
//        Thread.sleep(2000);
//        
//        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(ImagePath);
//        
//        Thread.sleep(2000);
//    	
//    	wUtil.clickOnEscapeButton();
//		
//    	Thread.sleep(2000);
//    	
//    	wUtil.scrollToParticularWebElement(driver, DrProfileNameScrolling);
//    	
//    	Thread.sleep(2000);
//    	
//    	String smcrn = String.valueOf(jUtil.getRandomNum());
//    	
//    	StateMedicalCouncilRegistrationNoEdt.sendKeys(smcrn);
//    	Thread.sleep(2000);
//    	
//    	String nmcn = String.valueOf(jUtil.getRandomNum());
//    	NmcNumberEdt.sendKeys(nmcn);
//    	Thread.sleep(2000);
//    	
//    	ExperianceDrpDwn.click();
//    	Thread.sleep(1000);
//    	driver.findElement(By.xpath("//li[.=' 3 years ']")).click();
//    	Thread.sleep(2000);
//    	
//    	PostGraduationDegreeDrpDwn.click();
//    	Thread.sleep(1000);
//    	driver.findElement(By.xpath("//li[.=' MD ']")).click();
//    	Thread.sleep(2000);
//    	
//    	SpecializationDrpdwn.click();
//    	Thread.sleep(1000);
//    	driver.findElement(By.xpath("//li[.=' Critical Care ']")).click();
//    	Thread.sleep(2000);
//    	
//    	SuperSpecializationDegreeDwn.click();
//    	Thread.sleep(1000);
//    	driver.findElement(By.xpath("//li[.=' DM ']")).click();
//    	Thread.sleep(2000);
//    	
//    	SuperSpecializationDrpdwn.click();
//    	Thread.sleep(1000);
//    	driver.findElement(By.xpath("//li[.=' Child & Adolescent Psychiatry ']")).click();
//    	Thread.sleep(2000);
//    	
//    	QualificationEdt.sendKeys("MBBS");
//    	Thread.sleep(2000);
//    	
//    	wUtil.scrollPageDown(1);
//    	
//    	LanguagesDrpdwn.click();
//    	Thread.sleep(1000);
//    	driver.findElement(By.xpath("//li[.=' English ']")).click();
//    	Thread.sleep(2000);
//    	
//    	YearOfAdmissionDrpdwn.click();
//    	Thread.sleep(2000);
//    	driver.findElement(By.xpath("//li[.=' 2020 ']")).click();
//    	
//    	Thread.sleep(2000);
//    	CurrentHospitalOrClinicEdt.sendKeys("abcdef");
//    	Thread.sleep(2000);
//    	
//    	ExpertiseEdt.sendKeys("Neurosurgery");
//    	Thread.sleep(2000);
//    	
//    	wUtil.scrollToParticularWebElement(driver, AboutYourselfTextarea);
//		Thread.sleep(2000);
//    	AboutYourselfTextarea.sendKeys(AboutYourself);
//    	
//    	Thread.sleep(2000);
////    	wUtil.scrollPageUp(2);
////    	wUtil.waitUntilElementVisibleUptoThirtyMin(driver, AiImage);
//    	wUtil.waitForElementToBeVisible(driver, SubmitBtn);
//    	
//    	try 
//    	{
//    		if(SubmitBtn.isDisplayed())
//        	{
//        		Thread.sleep(2000);
//        		System.out.println("Submit button Displayed");
//        	}
//    		AboutYourselfTextarea.clear();
//    		Thread.sleep(2000);
//    		wUtil.scrollToParticularWebElement(driver, SubmitBtn);
//    		Thread.sleep(1000);
////    		SubmitBtn.click();
//    		Thread.sleep(1000);
//    		if(SubmitBtn.isDisplayed())
//    		{
//    			wUtil.takeScreenShot(driver, "L_Submit Button Disabled without giving ABOUT YOURSELF TEXT");
//    		}
//    		Thread.sleep(2000);
//    		wUtil.scrollToParticularWebElement(driver, ExpertiseEdt);
//    		ExpertiseEdt.clear();
//    		Thread.sleep(2000);
//    		wUtil.scrollToParticularWebElement(driver, SubmitBtn);
//    		Thread.sleep(1000);
////    		SubmitBtn.click();
//    		Thread.sleep(1000);
//    		if(SubmitBtn.isDisplayed())
//    		{
//    			wUtil.takeScreenShot(driver, "M_Submit Button Disabled without giving Expertise");
//    		}
//    		Thread.sleep(2000);
//    		ExpertiseEdt.sendKeys("Neurosurgery");
//        	Thread.sleep(2000);
//        	CurrentHospitalOrClinicEdt.clear();
//    		Thread.sleep(2000);
//    		wUtil.scrollToParticularWebElement(driver, SubmitBtn);
//    		Thread.sleep(1000);
////    		SubmitBtn.click();
//    		Thread.sleep(1000);
//    		if(SubmitBtn.isDisplayed())
//    		{
//    			wUtil.takeScreenShot(driver, "N_Submit Button Disabled without giving Current Hospital Or Clinic Name");
//    		}
//    		Thread.sleep(2000);
//        	CurrentHospitalOrClinicEdt.sendKeys("abcdef");
//    		Thread.sleep(2000);
//    		wUtil.scrollToParticularWebElement(driver, QualificationEdt);
//    		Thread.sleep(1000);
//    		QualificationEdt.clear();
//    		Thread.sleep(2000);
//    		wUtil.scrollToParticularWebElement(driver, SubmitBtn);
//    		Thread.sleep(2000);
////    		SubmitBtn.click();
//    		Thread.sleep(2000);
//    		if(SubmitBtn.isDisplayed())
//    		{
//    			wUtil.takeScreenShot(driver, "O_Submit Button Disabled without giving Qualification");
//    		}
//    		Thread.sleep(2000);
//    		wUtil.scrollToParticularWebElement(driver, QualificationEdt);
//    		Thread.sleep(1000);
//    		QualificationEdt.sendKeys("MBBS");
//    		Thread.sleep(2000);
//    		wUtil.scrollToParticularWebElement(driver, NmcNumberEdt);
//    		Thread.sleep(2000);
//    		NmcNumberEdt.clear();
//    		Thread.sleep(2000);
//    		wUtil.scrollToParticularWebElement(driver, SubmitBtn);
//    		Thread.sleep(2000);
////    		SubmitBtn.click();
//    		Thread.sleep(2000);
//    		if(SubmitBtn.isDisplayed())
//    		{
//    			wUtil.takeScreenShot(driver, "P_Submit Button Disabled without giving NMC Number");
//    		}
//    		Thread.sleep(2000);
//    		wUtil.scrollToParticularWebElement(driver, NmcNumberEdt);
//    		Thread.sleep(2000);
//    		NmcNumberEdt.sendKeys(nmcn);
//        	Thread.sleep(2000);
//        	wUtil.scrollToParticularWebElement(driver, StateMedicalCouncilRegistrationNoEdt);
//    		Thread.sleep(2000);
//    		StateMedicalCouncilRegistrationNoEdt.clear();
//    		Thread.sleep(2000);
//    		wUtil.scrollToParticularWebElement(driver, SubmitBtn);
//    		Thread.sleep(2000);
////    		SubmitBtn.click();
//    		Thread.sleep(2000);
//    		if(SubmitBtn.isDisplayed())
//    		{
//    			wUtil.takeScreenShot(driver, "Q_Submit Button Disabled without giving State Medical Council Registration Number");
//    		}
//    		Thread.sleep(2000);
//    		wUtil.scrollToParticularWebElement(driver, StateMedicalCouncilRegistrationNoEdt);
//    		Thread.sleep(2000);
//    		StateMedicalCouncilRegistrationNoEdt.sendKeys(smcrn);
//    		Thread.sleep(2000);
//    		wUtil.scrollToParticularWebElement(driver, SubmitBtn);
//    		Thread.sleep(2000);
//    		AboutYourselfTextarea.sendKeys(AboutYourself);
//    		Thread.sleep(2000);
//    		if(SubmitBtn.isDisplayed())
//    		{
//    			wUtil.takeScreenShot(driver, "R_Submit Button Displayed After All Details Giving");
//    		}
//    		Thread.sleep(2000);
//    		SubmitBtn.click();
//		} catch (Exception e) 
//    	{
//			System.out.println("Application Form Page Not Displayed");
//		}
//    	
    	
    }
    	
}
