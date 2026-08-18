package adminObjectRepository;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminDashboardPage {

	//Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="//img[@alt='Child Patient']")private WebElement DoctorIcon;
    
    @FindBy(xpath="//img[@alt='Medical Team']")private WebElement PatientIcon;
    
    @FindBy(xpath="//img[@alt='Data Analytics']")private WebElement DashboardIcon;
    
    @FindBy(xpath="//p[.=' Logout ']")private WebElement LogoutLnk;
    
  
	//Rule-3:Create a constructor to initilise these elements
    
	public AdminDashboardPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
    

	//Rule-4:Provide getters to access these variables

	public WebElement getDoctorIcon() {
		return DoctorIcon;
	}


	public WebElement getPatientIcon() {
		return PatientIcon;
	}


	public WebElement getDashboardIcon() {
		return DashboardIcon;
	}
	
	
	public WebElement getLogoutLnk() {
		return LogoutLnk;
	}
	
	
	//Business Libraries

	public void clickOnDoctorIcon(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		driver.navigate().refresh();
		((JavascriptExecutor)driver)
    	.executeScript("arguments[0].click();", DoctorIcon);
	}
	
	public void clickOnPatientIcon(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		driver.navigate().refresh();
		((JavascriptExecutor)driver)
    	.executeScript("arguments[0].click();", PatientIcon);
	}
	
	public void clickOnDashboardIcon(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		driver.navigate().refresh();
		((JavascriptExecutor)driver)
    	.executeScript("arguments[0].click();", DashboardIcon);
	}
	
	public void logoutOfApplication() throws Exception
	{
		Thread.sleep(2000);
		LogoutLnk.click();
	}
	
}
