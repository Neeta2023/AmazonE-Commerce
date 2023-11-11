package testPackage;

import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import pomPackage.SignINpage;
import pomPackage.YourAccountsPage;

public class TC_ADDRESS004 {
	
	
	WebDriver driver;
	SignINpage pomS;
	YourAccountsPage pomA;
	
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
		
		pomS= new SignINpage(driver);
		pomS.entermail(testdata.signINmail);
		pomS.enterpassword(testdata.password);		
		
	}
	
	
	@Test
	public void Address004() throws IOException {
		
		pomA = new YourAccountsPage(driver);
		pomA.Accountpage();
		pomA.address();
		
		//entering different country name other than Canada
		
		driver.findElement(By.xpath("//*[@id='address-ui-widgets-countryCode']")).click();
		
        List<WebElement> list= driver.findElements(By.xpath("//ul[@class='a-nostyle a-list-link' and @role='listbox']//li//a"));
		
		System.out.println(list.size());
		
		for(int i=0;i< list.size();i++) {
			System.out.println(list.get(i).getText());
			
			if(list.get(i).getText().equalsIgnoreCase("Brazil")) {
				list.get(i).click();
				break;
			}
		}
		
		//screenshot
		File srcfile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcfile, new File("C:\\Users\\mohan\\eclipse-workspace\\TestAutomation\\src\\test\\java\\Screenshot\\countryChange.png"));
		
		
	}
	

	@AfterMethod
	public void tearDown() {
		
		//driver.close();
	}

}
