package practice.contactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactTest {

	public static void main(String[] args) throws IOException {
		//read common data from properties file
		FileInputStream fis = new FileInputStream("U:\\Pragya\\Data\\CommonDataTek.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		
		//read test script data from excel
		FileInputStream fis1 = new FileInputStream("U:\\Pragya\\Data\\CreateContact.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Sheet1");
		Row row = sh.getRow(1);
		String LastName = row.getCell(1).toString();  
		
		wb.close();
		
		
		WebDriver driver = null;
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}
		else {
			driver = new FirefoxDriver();
			}
		//Step1:login to application
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
				//step2: navigate to contact module
				driver.findElement(By.linkText("Contacts")).click();
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
				driver.findElement(By.name("lastname")).sendKeys(LastName);
				
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//verify header info expected result
				String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
				
				if(actLastName.contains(LastName)) {
						System.out.println(LastName + " is verified==PASS");
					}else {
						System.out.println(LastName + " is not verified==FAIL");
					}
				 
				
				//logout
				Actions act = new Actions(driver);
				act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				driver.quit();

	}

}
