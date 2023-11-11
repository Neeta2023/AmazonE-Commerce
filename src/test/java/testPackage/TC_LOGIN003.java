package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import pomPackage.RegisterUserPage;

public class TC_LOGIN003 {
	
	WebDriver driver;
	RegisterUserPage pom;
	String actualnomailError = "Enter your e-mail address or mobile phone number";
	
	
	@BeforeMethod
	public void browserSetup() throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
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
	public Object[][] testdata(){
		
		Object data[][]=DataExcelsheet.testdata("login003");
		return data;
	}
	
	
	
	@Test(dataProvider="testdata")
	public void register1(String name, String mail,String pwd, String pwd1) {
		
		pom=new RegisterUserPage(driver);
		pom.entername(name);
		pom.entermail(mail);
		pom.enterpassword(pwd);
		pom.enterpasswordAgain(pwd1);
		pom.signIN();
		
		String nomailError = pom.nomailAlert.getText();
		Assert.assertEquals(actualnomailError, nomailError);
		System.out.println(nomailError);
			
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
	}

}
