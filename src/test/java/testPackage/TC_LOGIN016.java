package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class TC_LOGIN016 {
	
	WebDriver driver;
	RegisterUserPage pom;
	
	
	@BeforeMethod
	public void browserSetup() throws InterruptedException {
		
		
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3000));
		driver.get("https://www.amazon.ca/");
		
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.id("nav-link-accountList-nav-line-1"))).build().perform();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Start here.")).click();	
	}
	
	@DataProvider
	public Object[][] testData(){
		
		Object data[][] = DataExcelsheet.testdata("login016");
		return data;
	}
	
	
	@Test(dataProvider="testData")
	public void register(String name, String mail, String password) throws InterruptedException {
		
		pom= new RegisterUserPage(driver);
		pom.entername(name);
		pom.entermail(mail);
		pom.enterpassword(password);
		
		copy();
		
		pom.signIN();
		boolean msg=driver.findElement(By.xpath("//div[@class='a-alert-content']//parent:: div[@class='a-box-inner a-alert-container']//parent:: div[@id='auth-password-mismatch-alert']")).isDisplayed();
		
		Assert.assertEquals(true, msg);	
		System.out.println("cannot copy Password and paste in to another textbox");
		
	}
	
	public void copy() throws InterruptedException {
		
		Actions action=new Actions(driver);
		
		action.moveToElement(RegisterUserPage.Cpassword).click().keyDown(Keys.CONTROL).sendKeys("a");
		action.sendKeys("c");
		Thread.sleep(2000);
		action.moveToElement(driver.findElement(By.id("ap_password_check"))).click().keyDown(Keys.CONTROL).sendKeys("v");
		action.keyUp(Keys.CONTROL).build().perform();
		
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
	}

}
