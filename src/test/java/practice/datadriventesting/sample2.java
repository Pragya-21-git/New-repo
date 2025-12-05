package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class sample2 {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\utsav\\OneDrive\\Desktop\\Pragya assignment\\CommonDataTek.properties");
		Properties pObj2 = new Properties();
		pObj2.load(fis);
		String BROWSER = pObj2.getProperty("browser");
		String URL = pObj2.getProperty("url");
		String USERNAME = pObj2.getProperty("username");
		String PASSWORD = pObj2.getProperty("password");
		
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
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
	}
		
	}
