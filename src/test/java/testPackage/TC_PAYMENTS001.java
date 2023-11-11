package testPackage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import pomPackage.SignINpage;

public class TC_PAYMENTS001 {
	
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
	public void Payments001() throws IOException, InterruptedException {
		
		driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']")).click();
		driver.findElement(By.xpath("//div[@class='a-column a-span9 a-span-last']//h2[normalize-space()='Your Payments']")).click();
		Thread.sleep(2000);
		screenshot();
		
	}
		public void screenshot() throws IOException {
			
			
			File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File("C:\\Users\\mohan\\eclipse-workspace\\TestAutomation\\src\\test\\java\\Screenshot\\paymentPage.png"));
			
		}
		
		@AfterMethod
		public void tearDown() {
			
			driver.close();
		}
	   
		
		
	}





