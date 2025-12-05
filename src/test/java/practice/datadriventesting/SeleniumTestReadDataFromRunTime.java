package practice.datadriventesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class SeleniumTestReadDataFromRunTime {
	
		@Test
		public void seleniumTest() {
			
			String BROWSER = 
			String URL = 
			String USERNAME = 
			String PASSWORD
			
			
			WebDriver driver = null;
			
			if(BROWSER.equals("chrome")) {
				driver = new ChromeDriver();
			}
			else if(BROWSER.equals("Edge")) {
			driver = new EdgeDriver();
			}
			else {
			driver = new ChromeDriver();
			}
			//Step1:login to application
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			driver.get(URL);
			
			driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			driver.findElement(By.id("submitButton")).click();
			
		}

	}

}
