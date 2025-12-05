package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class CreateOrgByJsonTest{
	@Test
	public void CreateOrgByJson() throws IOException, ParseException {
		//read common data from json file
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("C:\\Users\\utsav\\OneDrive\\Desktop\\Pragya assignment\\appCommonData.json"));
		
		JSONObject map = (JSONObject)obj;
		
		
			String BROWSER = (String)map.get("browser");
			String URL = map.get("url").toString();
			String username = map.get("username").toString();
			String password = (String) map.get("password");
			
			
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
			
			driver.findElement(By.name("user_name")).sendKeys(username);
			driver.findElement(By.name("user_password")).sendKeys(password);
			driver.findElement(By.id("submitButton")).click();
			
			
			Random random = new Random();
			int ranInt = random.nextInt(1000);
			
			//step2: navigate to organization module
			driver.findElement(By.xpath("//a[text()='Organizations']")).click();
			
			//step3: click on "create organization" button
			driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			
			//step4: enter all details of create organization
			driver.findElement(By.name("accountname")).sendKeys(OrgName + ranInt);
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']"));
			//logout
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
			driver.findElement(By.linkText("Sign Out")).click();
			driver.quit();

	}
}

