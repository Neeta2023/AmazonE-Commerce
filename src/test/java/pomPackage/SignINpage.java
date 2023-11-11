package pomPackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignINpage {
	
	
	WebDriver driver;
	
	@FindBy(id="ap_email") WebElement email;
	@FindBy(id="continue") WebElement next;
	
	@FindBy(xpath="//input[@id='ap_password']") WebElement passWord;
	@FindBy(xpath="//input[@id='signInSubmit']") WebElement submit;
	
	
	@FindBy(xpath="//span[@id='nav-link-accountList-nav-line-1']") WebElement userAccount;
	
	@FindBy(xpath="//h2[contains(text(), 'Your Orders')]") WebElement orders;
	
	@FindBy(xpath="//span[@class='a-dropdown-container']") WebElement orderDuration;  //couldnot work on this
	
	
	public SignINpage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void entermail(String mail) {
		email.sendKeys(mail);
		next.click();
	}
	
	public void enterpassword(String password) {
		passWord.sendKeys(password);
		submit.click();
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
	
	

}
