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

public class TC_LOGIN002 {
	
	WebDriver driver;
	RegisterUserPage pom;
	String actualmailError="Wrong or invalid e-mail address or mobile phone number. Please correct it and try again.";
	String actualpwdError="Minimum 6 characters required";
	
	
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
		
		Object data[][]= DataExcelsheet.testdata("login002");
		return data;
	}
	
	@Test(dataProvider="testData")
	public void register(String data, String data1,String data2, String data3) throws InterruptedException {
		
		pom=new RegisterUserPage(driver);
		pom.entername(data);
		pom.entermail(data1);
		pom.enterpassword(data2);
		pom.enterpasswordAgain(data3);
		pom.signIN();
		Thread.sleep(2000);
		pom.verify(driver);	
		
		
		String mailError=pom.mailalert.getText();		
		Assert.assertEquals(actualmailError, mailError);
		System.out.println("Error message for mail is:  " + mailError);
		
		
		
		String pwdError=pom.pwdalert.getText();
		Assert.assertEquals(actualpwdError, pwdError);
		System.out.println("Error message for password is: " + pwdError);
}
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
		driver=null;
	}
	
	
}


