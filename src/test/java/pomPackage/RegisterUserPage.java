package pomPackage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;



public class RegisterUserPage {
	
	WebDriver driver;
	
	
		
    // register new user
	@FindBy(id="ap_customer_name") WebElement Cname;
	@FindBy(id="ap_email") WebElement Cmail;
	@FindBy(id="ap_password") public static WebElement Cpassword;
	@FindBy(id="ap_password_check") public static WebElement Cpasswordagn;
	@FindBy(id="continue") WebElement Click;
	
	@FindBy(linkText="Men's 2-Pack Loose-Fit Short-Sleeve Crewneck T-Shirts, Red, Large") public static WebElement iTem;
	
	
	
	//Search functionality
	@FindBy(id="twotabsearchtextbox") WebElement search;
	
	@FindBy(xpath="//input[@id='nav-search-submit-button']//parent:: span[@id='nav-search-submit-text']") WebElement clickS;
	
	
	
	
	
	
	@FindBy(id="nav-link-accountList") WebElement signin;
	@FindBy(linkText="Start here.")  WebElement clickToregister;
	
	@FindBy(xpath=("//div[@class='a-alert-content' ]//parent::div[@class='a-box-inner a-alert-container']//parent::div[@id='auth-email-invalid-claim-alert']")) 
	public static WebElement mailalert;
	
	@FindBy(xpath=("//div[@class='a-alert-content']//parent:: div[@class='a-box-inner a-alert-container']//parent:: div[@id='auth-password-invalid-password-alert']")) 
	  public static WebElement pwdalert;
	
	@FindBy(xpath="//div[@class='a-alert-content']//parent:: div[@class='a-box-inner a-alert-container']//parent:: div[@id='auth-email-missing-alert']") 
	public static WebElement nomailAlert;
	
	@FindBy(xpath="//div[@class='a-alert-content']//parent:: div[@class='a-box-inner a-alert-container']//parent:: div[@id='auth-customerName-missing-alert']")
	public static WebElement nonameAlert;
	
	@FindBy(xpath="//span[@id='aacb-captcha-header']//parent:: div[@class='a-row a-spacing-mini']") public static WebElement captchamsg;
	
    public RegisterUserPage(WebDriver driver) {
    	
    	PageFactory.initElements(driver, this);
    }
	
	public void entername(String name)
	{
		Cname.sendKeys(name);
	}
	
	public void entermail(String mail)
	{
		Cmail.sendKeys(mail);
	}
	
	public void enterpassword(String password)
	{
	   Cpassword.sendKeys(password);	
	}
	
	public void enterpasswordAgain(String password)
	{
		Cpasswordagn.sendKeys(password);
	}
	
	public void signIN()
	{
		Click.click();
	}
	
	public  void verify(WebDriver driver)
	{
		this.driver=driver;
		//String msg=driver.findElement(By.id("auth-email-invalid-claim-alert")).getText();
		//String title=a.getText();
		//String title=driver.getTitle();
		//return msg;
		//System.out.println("User landed on page:  " + title);;
	}
	
	
	public void Search(String item) {
		
		search.sendKeys(item);
		clickS.click();
	}
	
	public void screenShot() throws IOException {
		
		File srcfile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcfile, new File("C:\\Users\\mohan\\eclipse-workspace\\TestAutomation\\src\\test\\java\\Screenshot\\2.png"));
	}

}
