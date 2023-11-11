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

public class TC_LOGIN_008 {
	
	
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
		
		Object data[][]=DataExcelsheet.testdata("login008");
		return data;
	}
	
	@Test(dataProvider="testData")
	public void register(String name, String mobile, String pasword, String paswordA) throws InterruptedException {
		pom=new RegisterUserPage(driver);
		
		pom.entername(name);
		pom.entermail(mobile);
		pom.enterpassword(pasword);
		pom.enterpasswordAgain(paswordA);
		pom.signIN();
		Thread.sleep(2000);
		
		
		System.out.println(driver.getTitle());
		Assert.assertEquals("Authentication required", driver.getTitle());
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
		driver=null;
	}

}
