package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import pomPackage.RegisterUserPage;

public class RegisteruserTestcase {
	WebDriver driver;
	RegisterUserPage pom;
	
	@BeforeMethod
	public void driverSetup() throws InterruptedException {
		
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3000));
		driver.get("https://www.amazon.ca/");
		
		pom=new RegisterUserPage(driver);
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.id("nav-link-accountList-nav-line-1"))).build().perform();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Start here.")).click();
	}
	
	@DataProvider
	public Object[][] testData(){
		
		Object data[][]= DataExcelsheet.testdata("sheet1");
		return data;
		
		
	}
	
	@Test(dataProvider="testData")
	public void registerUSER(String name, String mail, String password, String password1) {
		
		pom.entername(name);
		pom.entermail(mail);
		pom.enterpassword(password);
		pom.enterpasswordAgain(password1);
		pom.signIN();
		pom.verify(driver);
		String title=driver.getTitle();
		System.out.println("User landed on page: " + title);
	}
	
	@AfterMethod
	public void closeBrowser() {
		
		driver.quit();
	}

}
