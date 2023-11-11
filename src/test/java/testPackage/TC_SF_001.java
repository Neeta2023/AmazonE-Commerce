package testPackage;

import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import pomPackage.RegisterUserPage;

public class TC_SF_001 {
	
	
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
	}
	
	
	@Test
	public void Search001() throws IOException {
		
		
		pom= new RegisterUserPage(driver);		
		pom.Search(testdata.searchItem);
		
	  List<WebElement> list=driver.findElements(By.xpath("//div[@class='autocomplete-results-container']//div[@class='s-suggestion-container']//descendant:: span[@class='s-heavy']"));
		
	  System.out.println("searched items are:  "+ list.size());
	  
	  
	  
	  for(int i=0;i<list.size();i++) {
		  
		  System.out.println(list.get(i).getText());		  
		  if(list.get(i).getText().contains("15 pro")) {
			  list.get(i).click();
			  break;
		  }
		  
	  }
		  List<WebElement> itemsPerpage= driver.findElements(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']//div[@data-component-type='s-search-result']"));
		  System.out.println("items per page are:  " + itemsPerpage.size());
		  //driver.findElement(By.xpath("//input[@id='nav-search-submit-button']//parent:: span[@id='nav-search-submit-text']")).click();
		  
		  //click on next pagination button 
		  driver.findElement(By.xpath("//span[@class='s-pagination-strip']//a[@aria-label='Go to next page, page 2']")).click();
		  
		  File srcfile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcfile, new File("C:\\Users\\mohan\\eclipse-workspace\\TestAutomation\\src\\test\\java\\Screenshot\\itemperPage2.png"));
	  
			
			
	
	}



@AfterMethod
public void tearDown() {
	
	driver.close();
}

}
