package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SampleDataDrivenTesting {

	public static void main(String[] args) throws IOException {
		//step1: get the java represesntation object of the physical file
		
		FileInputStream fis = new FileInputStream("C:\\Users\\utsav\\OneDrive\\Desktop\\Pragya assignment\\CommonDataTek.properties");
		
		//step2: using property class, load all the keys
		
		Properties pObj = new Properties();
		pObj.load(fis);
		
		//step3: get the value based on key 
		System.out.println(pObj.getProperty("browser"));
	}

}
