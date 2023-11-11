package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import pomPackage.RegisterUserPage;
import pomPackage.SignINpage;

public class TC_ACCOUNT001 {
	
	WebDriver driver;
	RegisterUserPage pom;
	SignINpage pomS;
	
	@BeforeMethod
	public void browserSetup() throws InterruptedException {
		
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3000));
		driver.get("https://www.amazon.ca/");
		
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.id("nav-link-accountList-nav-line-1"))).build().perform();
		Thread.sleep(4000);
		
		driver.findElement(By.linkText("Sign in")).click();
	}
	
	
	@Test
	public void register() {
		
		pomS=new SignINpage(driver);
		pomS.entermail(testdata.signINmail);
		pomS.enterpassword(testdata.password);

        System.out.println("After entering username and password, user landed on: "+ driver.getTitle());
        
        pomS.Accountpage();
        
        System.out.println("After clicking on Hello<username>: " + driver.getTitle());
		
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
	}

}
