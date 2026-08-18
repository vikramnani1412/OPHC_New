package adminObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DrDocumentsPage {

	//Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="//h4[.='Aadhar card']/following-sibling::span[.='Open']")private WebElement AadharCardOpenBtn;
    
    @FindBy(xpath="//h4[.='Pan card']/following-sibling::span[.='Open']")private WebElement PanCardOpenBtn;
	
	@FindBy(xpath="//h4[.='Experience Certificate']/following-sibling::span[.='Open']")private WebElement ExperienceCertificateOpenBtn;
	
    @FindBy(xpath="//h4[.='Clinic / Hospital Proof']/following-sibling::span[.='Open']")private WebElement ClinicOrHospitalProofOpenBtn;
	
    @FindBy(xpath="//h4[.='Medical Certificate']/following-sibling::span[.='Open']")private WebElement MedicalCertificateOpenBtn;
    
    @FindBy(xpath="//h4[.='SMC Certificate']/following-sibling::span[.='Open']")private WebElement SMCCertificateOpenBtn;
    
    @FindBy(xpath="//button[.=' Approve ']")private WebElement ApproveBtn;
    
    @FindBy(xpath="//button[.=' Reject ']")private WebElement RejectBtn;
    
    @FindBy(xpath="//button[.=' Reject with Reason ']")private WebElement RejectWithReasonBtn;
    
    @FindBy(xpath="//button[.=' Approve with Rating ']")private WebElement ApproveWithRatingBtn;
    
    @FindBy(xpath="//textarea[@placeholder='Enter rejection reason']")private WebElement EnterRejectionReasonTextArea;
    
    @FindBy(xpath="//button[.=' Reject KYC ']")private WebElement RejectKYCBtn;
    
    @FindBy(xpath="//span[.=' Close ']")private WebElement CloseBtn;
    
    @FindBy(xpath="//i[@class='fa fa-arrow-left']")private WebElement BackArrow;
    
    @FindBy(xpath="//input[@placeholder='Enter the Fee /-']")private WebElement ConsultationFeeEdt;
  
    @FindBy(xpath="//button[.=' Approve KYC ']")private WebElement ApproveKycBtn;
    
  
	//Rule-3:Create a constructor to initilise these elements
    
	public DrDocumentsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	//Rule-4:Provide getters to access these variables
    
	public WebElement getAadharCardOpenBtn() {
		return AadharCardOpenBtn;
	}


	public WebElement getPanCardOpenBtn() {
		return PanCardOpenBtn;
	}


	public WebElement getExperienceCertificateOpenBtn() {
		return ExperienceCertificateOpenBtn;
	}


	public WebElement getClinicOrHospitalProofOpenBtn() {
		return ClinicOrHospitalProofOpenBtn;
	}


	public WebElement getMedicalCertificateOpenBtn() {
		return MedicalCertificateOpenBtn;
	}


	public WebElement getSMCCertificateOpenBtn() {
		return SMCCertificateOpenBtn;
	}


	public WebElement getApproveBtn() {
		return ApproveBtn;
	}


	public WebElement getRejectBtn() {
		return RejectBtn;
	}


	public WebElement getRejectWithReasonBtn() {
		return RejectWithReasonBtn;
	}


	public WebElement getApproveWithRatingBtn() {
		return ApproveWithRatingBtn;
	}


	public WebElement getEnterRejectionReasonTextArea() {
		return EnterRejectionReasonTextArea;
	}


	public WebElement getCloseBtn() {
		return CloseBtn;
	}


	public WebElement getBackArrow() {
		return BackArrow;
	}


	public WebElement getConsultationFeeEdt() {
		return ConsultationFeeEdt;
	}


	public WebElement getApproveKycBtn() {
		return ApproveKycBtn;
	}


	public WebElement getRejectKYCBtn() {
		return RejectKYCBtn;
	}

	// Business Library
	
	public void RejectingDoctorDocuments(WebDriver driver, String Reason) throws Exception
	{
		Thread.sleep(2000);
		AadharCardOpenBtn.click();
		Thread.sleep(2000);
		ApproveBtn.click();
		Thread.sleep(2000);
		BackArrow.click();		
		Thread.sleep(2000);
		PanCardOpenBtn.click();
		Thread.sleep(2000);
		ApproveBtn.click();
		Thread.sleep(2000);
		BackArrow.click();
		Thread.sleep(2000);
		ExperienceCertificateOpenBtn.click();
		Thread.sleep(2000);
		ApproveBtn.click();
		Thread.sleep(2000);
		BackArrow.click();
		Thread.sleep(2000);
		ClinicOrHospitalProofOpenBtn.click();
		Thread.sleep(2000);
		ApproveBtn.click();
		Thread.sleep(2000);
		BackArrow.click();
		Thread.sleep(2000);
		MedicalCertificateOpenBtn.click();
		Thread.sleep(2000);
		ApproveBtn.click();
		Thread.sleep(2000);
		BackArrow.click();
		Thread.sleep(2000);
		SMCCertificateOpenBtn.click();
		Thread.sleep(2000);
		RejectBtn.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//textarea[@placeholder='Enter rejection reason...']")).sendKeys(Reason);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//h3[.='Reject Document']/following-sibling::div/button[.=' Reject ']")).click();
		Thread.sleep(2000);
		BackArrow.click();
		Thread.sleep(2000);
		RejectWithReasonBtn.click();
		Thread.sleep(2000);
		EnterRejectionReasonTextArea.sendKeys(Reason);
		Thread.sleep(2000);
		RejectKYCBtn.click();
		Thread.sleep(2000);
		
	}
	
	
	public void ApprovingAllDocuments(WebDriver driver, int Rating, String ConsultationFee) throws Exception
	{
		Thread.sleep(2000);
		AadharCardOpenBtn.click();
		Thread.sleep(2000);
		ApproveBtn.click();
		Thread.sleep(2000);
		BackArrow.click();		
		Thread.sleep(2000);
		PanCardOpenBtn.click();
		Thread.sleep(2000);
		ApproveBtn.click();
		Thread.sleep(2000);
		BackArrow.click();
		Thread.sleep(2000);
		ExperienceCertificateOpenBtn.click();
		Thread.sleep(2000);
		ApproveBtn.click();
		Thread.sleep(2000);
		BackArrow.click();
		Thread.sleep(2000);
		ClinicOrHospitalProofOpenBtn.click();
		Thread.sleep(2000);
		ApproveBtn.click();
		Thread.sleep(2000);
		BackArrow.click();
		Thread.sleep(2000);
		MedicalCertificateOpenBtn.click();
		Thread.sleep(2000);
		ApproveBtn.click();
		Thread.sleep(2000);
		BackArrow.click();
		Thread.sleep(2000);
		SMCCertificateOpenBtn.click();
		Thread.sleep(2000);
		ApproveBtn.click();
		Thread.sleep(2000);
		BackArrow.click();
		Thread.sleep(2000);
		ApproveWithRatingBtn.click();
		Thread.sleep(2000);
		for(int i=1;i<=Rating;i++)
		{
			driver.findElement(By.xpath("(//span[.=' ★ '])["+i+"]")).click();
		}
		Thread.sleep(2000);
		ConsultationFeeEdt.clear();
		Thread.sleep(2000);
		ConsultationFeeEdt.sendKeys(ConsultationFee);
		Thread.sleep(2000);
		ApproveKycBtn.click();
		Thread.sleep(2000);
		
	}
	
}
