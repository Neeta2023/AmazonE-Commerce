package pomPackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YourAccountsPage {
	
	WebDriver driver;
	
	
@FindBy(xpath="//span[@id='nav-link-accountList-nav-line-1']") WebElement userAccount;
	
	@FindBy(xpath="//h2[contains(text(), 'Your Orders')]") WebElement orders;
	
	@FindBy(xpath="//span[@class='a-dropdown-container']") WebElement orderDuration;  //couldnot work on this

	@FindBy(xpath="//h2[normalize-space()='Your Addresses']") WebElement youraddress;
	@FindBy(xpath="//div[@class='a-box first-desktop-address-tile']") WebElement Addadrs;
	
	
	@FindBy(xpath="//span[@id='address-ui-widgets-countryCode']//span[@role='button']") public static WebElement country;
	
	
	@FindBy(id="address-ui-widgets-enterAddressFullName") 
	public static WebElement Fullname;
	
	@FindBy(xpath="//div[@class='a-section a-spacing-base adddress-ui-widgets-form-field-container']//input[@id='address-ui-widgets-enterAddressPhoneNumber']")
	
	public static WebElement phone;
	
	@FindBy(xpath="//*[@id='address-ui-widgets-enterAddressLine1']") public static WebElement adress1;
	@FindBy(xpath="//*[@id='address-ui-widgets-enterAddressLine2']") public static WebElement adress2;
	@FindBy(xpath="//*[@id='address-ui-widgets-enterAddressCity']") public static WebElement city;
	
	@FindBy(xpath="//*[@id=\"address-ui-widgets-enterAddressPostalCode\"]") public static WebElement postalCode;
	
	@FindBy(xpath="//input[@id='address-ui-widgets-use-as-my-default']")
	public static WebElement checkbox;
	
	@FindBy(xpath="//input[@aria-labelledby='address-ui-widgets-form-submit-button-announce']") public static WebElement submit;
	
	
	@FindBy(xpath="//span[contains(text(),'Add preferences, notes, access codes and more')]") WebElement DInfo;
	
	
	@FindBy(xpath="//button[normalize-space()='Apartment']") public static WebElement Apartment;
	
	
	@FindBy(xpath="//div[@class='a-section ma-property-type-form-block ma-apartment-form-block ma-form-section-without-spacing']//input[@placeholder='Security code for the door']")
	public static WebElement Scode;
	
	
	
	
	
	
	
	
	
	
	
	
	
	public YourAccountsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	public void Accountpage() {
		userAccount.click();	
		
	}
	
	public void YourOrder() {
		orders.click();
		//orderDuration.click();
		List<WebElement> options = orderDuration.findElements(By.xpath("//span[@id='a-autoid-1-announce']"));
		options.get(0).click();
	}
	
	public void address() {
		
		youraddress.click();
		Addadrs.click();
	}
	
	public void ExtraInfo() {
		
		DInfo.click();
		//Scode.sendKeys("123456");	
		}
}
