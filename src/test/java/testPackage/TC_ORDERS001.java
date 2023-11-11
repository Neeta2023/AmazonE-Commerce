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
import java.time.Duration;
import pomPackage.SignINpage;

public class TC_ORDERS001 {
	
	WebDriver driver;
	SignINpage pomS;
	
	@BeforeMethod
	public void browserSetup() throws InterruptedException {
		
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3000));
		driver.get("https://www.amazon.ca/?tag=admpdesktopca-20&ref=nav_ya_signin&mfadid=adm");
		
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.id("nav-link-accountList-nav-line-1"))).build().perform();
		Thread.sleep(4000);
		
		driver.findElement(By.linkText("Sign in")).click();
		
		pomS=new SignINpage(driver);
		pomS.entermail(testdata.signINmail);
		pomS.enterpassword(testdata.password);
	}
	
	@Test
	public void accounts() throws IOException, InterruptedException {
		
		pomS = new SignINpage(driver);
		pomS.Accountpage();
		pomS.YourOrder();
		Thread.sleep(2000);		
		File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destfile= new File("C:\\Users\\mohan\\eclipse-workspace\\TestAutomation\\src\\test\\java\\Screenshot\\orderhistory.png");
		FileUtils.copyFile(file, destfile);
		System.out.println("screenshot taken");
		
		
	    //Select obj1=new Select(driver.findElement(By.xpath("//select[@id='time-filter']//parent:: span[@class='a-dropdown-container']")));	
		//obj1.selectByVisibleText("2023");
		//driver.findElement(By.xpath("//*[@id=\"a-autoid-1-announce\"]")).sendKeys("the past 30 days");
		
		driver.findElement(By.xpath("//a[@class='a-link-normal'][normalize-space()='Buy Again']")).click();
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
	}

}
