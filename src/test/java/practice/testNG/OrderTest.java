package practice.testNG;

import org.testng.annotations.Test;

public class OrderTest {

	@Test(invocationCount = 5)
	public void createOrderTest() {
		System.out.println("execute createOrderTest==>123");
		
	}
	@Test(enabled = false)
	public void billingAnOrderTest() {
		System.out.println("execute billingAnOrderTest==>123");
	}
}
