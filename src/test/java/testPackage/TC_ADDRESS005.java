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
import pomPackage.SignINpage;
import pomPackage.YourAccountsPage;

public class TC_ADDRESS005 {
	
	WebDriver driver;
	SignINpage pomS;
	YourAccountsPage pomA;
	
	
	@BeforeMethod
	public void browserSetup() throws InterruptedException {
		
		
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
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
	public void Address005() throws InterruptedException {
		
		
		pomA=new YourAccountsPage(driver);
		pomA.Accountpage();
		pomA.address();
		
		//pomA.Fullname.sendKeys(" ");
		pomA.phone.sendKeys("123456");
		pomA.adress1.sendKeys("33 church town");
		Thread.sleep(2000);
		pomA.submit.click();
		
		Assert.assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'Please enter a name.')]")).isDisplayed());
		System.out.println("assertion passed");
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
	}

}
