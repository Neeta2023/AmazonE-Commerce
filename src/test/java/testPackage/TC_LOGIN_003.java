package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pomPackage.RegisterUserPage;

public class TC_LOGIN_003 {
	
	WebDriver driver;
	RegisterUserPage pom;
	String actualNonameError="Enter your name";
	
	@BeforeMethod
	public void browserSetup() throws InterruptedException {
		
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.amazon.ca/");
		
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.id("nav-link-accountList-nav-line-1"))).build().perform();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Start here.")).click();
	}
	
	@Test
	public void register() throws InterruptedException {
		
		pom=new RegisterUserPage(driver);
		pom.entername(testdata.name);
		pom.signIN();
		Thread.sleep(2000);
		String nonameError=pom.nonameAlert.getText();
		Assert.assertEquals(nonameError, actualNonameError);
		//System.out.println(nonameError);
	}
	
	@Test
	public void register1() {
		
		pom=new RegisterUserPage(driver);
		
		pom.entername(testdata.nameA);
		pom.signIN();
		boolean msg=pom.nonameAlert.isDisplayed();
		Assert.assertEquals(false, msg);
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
		driver=null;
	}

}
