package testPackage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import pomPackage.RegisterUserPage;

public class TC_LOGIN027 {
	
	
	WebDriver driver;
	RegisterUserPage pom;
	
	
	
	
	@BeforeMethod
	public void browserSetup() {
		
		
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3000));
		driver.get("https://www.amazon.ca/");
		driver.getCurrentUrl();
		
	}
	
	@Test
	public void search() throws IOException, InterruptedException {
		
		pom= new RegisterUserPage(driver);		
		pom.Search(testdata.item);
		
		
		
		//taking screenshot of results page
		File srcfile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcfile, new File("C:\\Users\\mohan\\eclipse-workspace\\TestAutomation\\src\\test\\java\\Screenshot\\2.png"));
		
		System.out.println(driver.getTitle());
		
		
		//JavascriptExecutor Js1 = (JavascriptExecutor) driver;
		//Js1.executeScript("window.scrollBy(0,2000)"); 
		
		Thread.sleep(3000);
		
		//using url of the item to add to cart
		driver.get("https://www.amazon.ca/Amazon-Essentials-Regular-Fit-Short-Sleeve-Crewneck/dp/B06XWPGWD3/ref=sr_1_1_ffob_sspa?crid=176RKDO49C788&keywords=red+tshirt&qid=1699130124&sprefix=red+tshirt%2Caps%2C127&sr=8-1-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&psc=1");
		
		
		//driver.findElement(By.linkText("Men's 2-Pack Loose-Fit Short-Sleeve Crewneck T-Shirts, Red, Large")).click();
		//pom.iTem.click();
			
		//Actions action= new Actions(driver);
		//action.moveToElement(pom.iTem).build().perform();
		
		Select obj = new Select(driver.findElement(By.id("quantity")));
		obj.selectByVisibleText("3");
		
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		
		driver.findElement(By.xpath("//a[@id='nav-cart']")).click();
		
		driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']")).click();
		
		System.out.println(driver.getTitle());
		
		Assert.assertEquals(driver.getTitle(), "Amazon Sign In");
		System.out.println("assertion passed");
	}
	
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
	}

}
