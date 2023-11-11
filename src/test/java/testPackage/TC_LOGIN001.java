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

public class TC_LOGIN001 {
	
	WebDriver driver;
	RegisterUserPage pom;
	
	@BeforeMethod
	public void browserSetup() throws InterruptedException {
		
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3000));
		driver.get("https://www.amazon.ca/");
		
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.id("nav-link-accountList-nav-line-1"))).build().perform();
		Thread.sleep(2000);
		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3000));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Start here.")).wait());
		driver.findElement(By.linkText("Start here.")).click();
		
		
	}
	
	@DataProvider
	public String[][] testdata() {
		
		//Object[] dataArray= new Object {"neeta","neeta.rout@gmail.com","abc$123","abc$123"};
		//return new Object[] {"neeta","neeta.rout@gmail.com","abc$123","abc$123"};
		String TestData[][]= {{"neeta","neeta.rout@gmail.com","abc$123","abc$123"}};
		return TestData;
		
	}
	
	@Test(dataProvider="testdata")
	public void register(String data, String data1,String data2, String data3) throws InterruptedException {
		
		pom=new RegisterUserPage(driver);
		pom.entername(data);
		pom.entermail(data1);
		pom.enterpassword(data2);
		pom.enterpasswordAgain(data3);
		pom.signIN();
		Thread.sleep(2000);
		pom.verify(driver);	
		String title=driver.getTitle();
		System.out.println("page title is: " + title);
	}
	@Test(enabled=false)
	public void Output() {
		
		//pom.verify();
	}
	
	@AfterMethod
	public void CloseBrowser() {
		
		driver.close();
	}

}
