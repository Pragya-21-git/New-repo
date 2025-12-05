package practice.contactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		//read common data from properties file
				FileInputStream fis = new FileInputStream("U:\\Pragya\\Data\\CommonDataTek.properties");
				Properties prop = new Properties();
				prop.load(fis);
				String BROWSER = prop.getProperty("browser");
				String URL = prop.getProperty("url");
				String USERNAME = prop.getProperty("username");
				String PASSWORD = prop.getProperty("password");
				
				Random random = new Random();
				int ranInt = random.nextInt(1000);
				
				//read test script data from excel
				FileInputStream fis1 = new FileInputStream("U:\\Pragya\\Data\\CreateContact.xlsx");
				Workbook wb = WorkbookFactory.create(fis1);
				Sheet sh = wb.getSheet("Sheet1");
				Row row = sh.getRow(7);
				String LastName1 = row.getCell(3).toString();  
				String OrgName = row.getCell(2).toString() + ranInt;
				
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
				
				driver.findElement(By.xpath("//a[text()='Organizations']")).click();
				
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
				driver.findElement(By.name("accountname")).sendKeys(OrgName);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				String actOrgName = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
				if(actOrgName.contains(OrgName)) {
						System.out.println(OrgName + "is verified==PASS");
					}else {
						System.out.println(OrgName + "is not verified==FAIL");
					} 
				Thread.sleep(3000);
						//step2: navigate to contact module
						driver.findElement(By.linkText("Contacts")).click();
						driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
						
						driver.findElement(By.name("lastname")).sendKeys(LastName1);
						driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
				
						
						
						
						//switch to child window
						Set<String> set = driver.getWindowHandles();
						Iterator<String> it = set.iterator();
						
						while (it.hasNext()) {
							String WindowID = it.next();
							driver.switchTo().window(WindowID);
							
							String actUrl = driver.getCurrentUrl();
							if(actUrl.contains("module=Accounts")) {
								break;
							}
							
						}
						
						driver.findElement(By.name("search_text")).sendKeys(OrgName);
						driver.findElement(By.name("search")).click();
						driver.findElement(By.linkText(OrgName)).click();
						
						//switch to parent window
						Set<String> set1 = driver.getWindowHandles();
						Iterator<String> it1 = set1.iterator();
						
						while (it1.hasNext()) {
							String WindowID = it1.next();
							driver.switchTo().window(WindowID);
							
							String actUrl = driver.getCurrentUrl();
							if(actUrl.contains("Contacts&action")) {
								break;
							}
							
						}
						
						
						driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
						
						//verify header info expected result
						String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
						
						if(headerInfo.contains(LastName1)) {
							System.out.println(LastName1 + "is created==PASS");
						}else {
							System.out.println(LastName1 + "is not created==FAIL");
						}
						
						//logout
						Actions act = new Actions(driver);
						act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
						driver.findElement(By.linkText("Sign Out")).click();
						driver.quit();

	}

}
