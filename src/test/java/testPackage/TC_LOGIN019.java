package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import pomPackage.RegisterUserPage;

public class TC_LOGIN019 {
	
	WebDriver driver;
	RegisterUserPage pom;
	Actions action;
	
	@BeforeMethod
	public void browserSetup() throws InterruptedException {
		
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2000));
		driver.get("https://www.amazon.ca/");
		
		action=new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']//parent:: div[@class='nav-line-1-container']//parent:: a[@id='nav-link-accountList']"))).build().perform();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		Thread.sleep(2000);
		driver.findElement(By.linkText("Start here.")).click();
		
	}
	
	@DataProvider
	public Object[][] testData(){
		
		Object data[][] = DataExcelsheet.testdata("login019");
		return data;
		
	}
	
	@Test(dataProvider="testData")
	public void register(String name, String mail, String password, String passwordA) {
		
		pom = new RegisterUserPage(driver);
		
		pom.entername(name);
		pom.entermail(mail);
		pom.enterpassword(password);
		pom.enterpasswordAgain(passwordA);
		pom.signIN();
		
		boolean mismatchErr=driver.findElement(By.xpath("//div[@class='a-alert-content']//parent:: div[@class='a-box-inner a-alert-container']//parent:: div[@id='auth-password-mismatch-alert']")).isDisplayed();
		Assert.assertEquals(true, mismatchErr);
		System.out.println("assertion passed");
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
		
	}

}
