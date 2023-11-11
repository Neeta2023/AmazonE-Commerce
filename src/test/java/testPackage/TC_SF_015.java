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
import java.time.Duration;
import pomPackage.RegisterUserPage;
import pomPackage.SignINpage;

public class TC_SF_015 {
	
	
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
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.id("nav-link-accountList-nav-line-1"))).build().perform();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Sign in")).click();
		
		pomS = new SignINpage(driver);
		pomS.entermail(testdata.signINmail);
		pomS.enterpassword(testdata.password);
		
		
	}
	
	@Test
	public void Search015() {
		
		pom= new RegisterUserPage(driver);
		pom.Search(testdata.searchItem);
		
		
		//sorting
		driver.findElement(By.cssSelector("span[id='a-autoid-0-announce'] span[class='a-dropdown-label']")).click();
		List<WebElement> sortList = driver.findElements(By.xpath("//ul[@role='listbox']//li"));
		
		System.out.println(sortList.size());
		
		for(int i=0;i<sortList.size();i++) {
			
			if(sortList.get(i).getText().contains("New")) {
				sortList.get(i).click();
				break;
			}
		}
		
		//filteration
		
		List<WebElement> filterList = driver.findElements(By.xpath("//ul[@aria-labelledby='p_n_feature_sixteen_browse-bin-title']//li//a//input"));
		System.out.println("filter lists are: " + filterList.size());
		
		for(int i=0;i<filterList.size();i++) {
			System.out.println(filterList.get(i).getText());
			if(filterList.get(i).getText().contains("4G")) {
				
				
				filterList.get(i).click();
				System.out.println("clicked");
				break;
			}
		}
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
	}

}
