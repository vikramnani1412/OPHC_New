package adminObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DrRejectDocumentPage {

	//Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="//button[@class='close-btn']")private WebElement PageCloseBtn;
    
    @FindBy(xpath="//textarea[@placeholder='Enter rejection reason...']")private WebElement EnterRejectionReasonTxtArea;
	
	@FindBy(xpath="//button[.=' Cancel ']")private WebElement CancelBtn;
	
	@FindBy(xpath="//h3[.='Reject Document']/following-sibling::div/button[.=' Reject ']")private WebElement RejectBtn;
	
	@FindBy(xpath="//span[.=' Close ']")private WebElement CloseBtn;
	
    
	//Rule-3:Create a constructor to initilise these elements
    
	public DrRejectDocumentPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Rule-4:Provide getters to access these variables
	
	public void DoctorRejectingWithReason(String Reason) throws Exception
	{
		Thread.sleep(2000);
		EnterRejectionReasonTxtArea.sendKeys(Reason);
		Thread.sleep(2000);
		RejectBtn.click();
		
	}
	
}
