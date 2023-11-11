package testPackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pomPackage.SignINpage;
import pomPackage.YourAccountsPage;

public class TC_ADDRESS026 {
	
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
	public void Address026() {
		
		
		
		pomA = new YourAccountsPage(driver);
		pomA.Accountpage();
		pomA.address();
		
		
		pomA.Fullname.sendKeys("akshya");
		pomA.phone.sendKeys("2384561234");
		pomA.adress1.sendKeys("5 massey");
		pomA.adress2.sendKeys("toronto");
		pomA.city.sendKeys("toronto");
		
		
		driver.findElement(By.xpath("//span[@id='address-ui-widgets-enterAddressStateOrRegion']//span[@role='button']")).click();
		List<WebElement> listC = driver.findElements(By.xpath("//ul[@class='a-nostyle a-list-link']//li//a"));
		System.out.println(listC.size());
		
		for(int i=0;i<listC.size();i++) {
			if(listC.get(i).getText().equalsIgnoreCase("Ontario")) {
				listC.get(i).click();
			}
		}
		
		pomA.postalCode.sendKeys("m4c5l6");
		pomA.ExtraInfo();
		
		pomA.Apartment.click();
		pomA.Scode.sendKeys("123");
		
		
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
	}

}
