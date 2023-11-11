package testPackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pomPackage.SignINpage;

public class TC_PAYMENT002 {
	
	WebDriver driver;
	SignINpage pomS;
	
	@BeforeMethod
	public void browserSetup() throws InterruptedException {
		
		
		
		
		WebDriverManager.firefoxdriver().setup();
		driver= new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.amazon.ca/");
		driver.navigate().refresh();
		
		
		
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.id("nav-link-accountList-nav-line-1"))).build().perform();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Sign in")).click();
		
		
		
		
		
		pomS=new SignINpage(driver);
		pomS.entermail(testdata.signINmail);
		pomS.enterpassword(testdata.password);
	}
	
	
	@Test
	public void Payment002() throws InterruptedException {
		
		
		
		driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']")).click();
		driver.findElement(By.xpath("//div[@class='a-column a-span9 a-span-last']//h2[normalize-space()='Your Payments']")).click();
		//Thread.sleep(2000);
		System.out.println(driver.getTitle());
		//driver.findElement(By.id("pp-FzQY9P-20")).click();
		
		Thread.sleep(2000);
		//click on Add a payment method
		driver.findElement(By.xpath("//div[@class='a-fixed-left-grid-col apx-wallet-add-link-section a-col-right']//a[@aria-label = 'add a payment method link']")).click();
		Thread.sleep(2000);
		
		
		//Add a debit or credit card
		driver.findElement(By.xpath("//span[@id='apx-add-credit-card-action-test-id']")).click();
		Thread.sleep(2000);
		
		
		
		//driver.switchTo().defaultContent();
		WebElement iframe = driver.findElement(By.xpath("//div[@class='a-row a-spacing-medium a-spacing-top-medium apx-wallet-payment-method-details-row']//iframe"));
		driver.switchTo().frame(iframe);
		
		System.out.println("iframe is: " + iframe.getTagName());
		
		
		
		//driver.findElement(By.xpath("//h2[normalize-space()='Add a New Payment Method']")).sendKeys("123456789987654");
		
		driver.findElement(By.cssSelector("input[name='addCreditCardNumber']")).sendKeys("123");
		driver.findElement(By.cssSelector("input[name='ppw-accountHolderName']")).sendKeys("neeta");
		
		Thread.sleep(2000);
		//select month from dropdown list
		driver.findElement(By.cssSelector("span[class='a-dropdown-prompt']")).click();
		List<WebElement> monthList =driver.findElements(By.cssSelector("ul[class='a-nostyle a-list-link'] > li"));
		
		System.out.println(monthList.size());
		
		for(int i=0;i<monthList.size();i++) {
			
			System.out.println(monthList.get(i).getText());
		}
		monthList.get(5).click();
		
		//select year from dropdown list
		
	     driver.findElement(By.cssSelector("select[name='ppw-expirationDate_year'] + span span[class='a-dropdown-prompt']")).click();
	     List<WebElement> yearList =driver.findElements(By.cssSelector("ul[class='a-nostyle a-list-link'] > li"));
		System.out.println(yearList.size());
		
		for(int i=0;i<yearList.size();i++) {
			
			if(yearList.get(i).getText().contains("2024")) {
				yearList.get(i).click();
				break;
			}
		}
		
		
		
		driver.findElement(By.cssSelector("input[name='addCreditCardVerificationNumber']")).sendKeys("123");
		driver.findElement(By.cssSelector("input[name='ppw-widgetEvent:AddCreditCardEvent']")).click();
		WebElement msg=driver.findElement(By.cssSelector("div[class='a-box a-alert a-alert-error']>div>h4"));
		System.out.println(msg.getText());
	
	   		
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
	}

}
