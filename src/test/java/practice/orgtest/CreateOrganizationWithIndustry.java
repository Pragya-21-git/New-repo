package practice.orgtest;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWithIndustry {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("U:\\Pragya\\Data\\CommonDataTek.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		
		//read from excel file
		FileInputStream fis1 = new FileInputStream("U:\\Pragya\\Data\\CreateOrg.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Sheet1");
		Row row = sh.getRow(4);
		String orgName = row.getCell(2).getStringCellValue();
		String energy = row.getCell(3).getStringCellValue();
		String press = row.getCell(4).getStringCellValue();
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
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		Random random = new Random();
		int ranInt = random.nextInt(10000);
		
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		driver.findElement(By.name("accountname")).sendKeys(orgName + ranInt);
		
		WebElement industry = driver.findElement(By.name("industry"));
		industry.click();
		
		WebElement type = driver.findElement(By.name("accounttype"));
		type.click();
		
		
		//option[@value='Press']
		
		Select sel = new Select(industry);
		sel.selectByVisibleText(energy);
		String industry1 = sel.getFirstSelectedOption().getText();
		System.out.println(industry1);
		
		Select sel2 = new Select(type);
		sel2.selectByVisibleText(press);
		String type1 = sel2.getFirstSelectedOption().getText();
		System.out.println(type1);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify the dropdown industries and type
		String actIndusries = driver.findElement(By.id("dtlview_Industry")).getText();
		
		
		if(actIndusries.equals(industry1)) {
			System.out.println(industry1 +"info is  verified==PASS");
		}
		else{
			System.out.println(industry1 +"info is not verified==FAIL");
		}
		
		String actType = driver.findElement(By.id("dtlview_Type")).getText();
		if(actType.equals(type1)) {
			System.out.println(type1 + "info is  verified==PASS");
		}
		else{
			System.out.println(type1 + "info is not verified==FAIL");
		}
		
		
	
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	  	
		
	}

}
