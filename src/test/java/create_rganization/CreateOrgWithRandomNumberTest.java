package create_rganization;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrgWithRandomNumberTest {

	public static void main(String[] args) throws IOException {
		//read common data from properties file
				FileInputStream fis = new FileInputStream("C:\\Users\\utsav\\OneDrive\\Desktop\\Pragya assignment\\CommonDataTek.properties");
				Properties pObj2 = new Properties();
				pObj2.load(fis);
				String BROWSER = pObj2.getProperty("browser");
				String URL = pObj2.getProperty("url");
				String USERNAME = pObj2.getProperty("username");
				String PASSWORD = pObj2.getProperty("password");
				 
				
				//read testScript data from excel
				FileInputStream fis1 = new FileInputStream("C:\\Users\\utsav\\Downloads\\ConditionBased.xlsx");
				
				Workbook wb = WorkbookFactory.create(fis1);
				   Sheet sh = wb.getSheet("Sheet1");
				   Row row = sh.getRow(1);
				   String OrgName = row.getCell(2).toString();
				   wb.close();
				
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
				
				
				Random random = new Random();
				int ranInt = random.nextInt(1000);
				
				//step2: navigate to organization module
				driver.findElement(By.xpath("//a[text()='Organizations']")).click();
				
				//step3: click on "create organization" button
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
				//step4: enter all details of create organization
				driver.findElement(By.name("accountname")).sendKeys(OrgName + ranInt);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				//logout
				Actions act = new Actions(driver);
				act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				driver.quit();


	}

}
