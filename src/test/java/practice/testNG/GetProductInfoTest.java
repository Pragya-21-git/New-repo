package practice.testNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class GetProductInfoTest {

	@Test
	public void getProductInfoTest() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.flipkart.com/");
		
		//search product
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("moto edge 60 pro",Keys.ENTER);
		WebElement price = driver.findElement(By.xpath("//div[text()='MOTOROLA Edge 60 Pro (Pantone Sparkling Grape, 256 GB)']/../..//div[@class='Nx9bqj _4b5DiR']"));
		
		System.out.println(price.getText());
	}
}
