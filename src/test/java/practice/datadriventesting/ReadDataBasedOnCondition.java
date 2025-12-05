package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataBasedOnCondition {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		String ExpectedTestid = "tc_02";
		String data1 = "";
		String data2 = "";
		String data3 = "";
		boolean flag=false;
		
		FileInputStream fis = new FileInputStream("C:\\Users\\utsav\\Downloads\\ConditionBased.xlsx");
				Workbook wb = WorkbookFactory.create(fis);
				Sheet sh = wb.getSheet("Sheet1");
			int rowCount = sh.getLastRowNum();
			for(int i=0; i<=rowCount; i++) {
				
				String data="";
				try {
					 data=sh.getRow(i).getCell(0).toString();
					if(data.equals(ExpectedTestid))
					{
						flag =true;
						data1=sh.getRow(i).getCell(1).toString();
						data2=sh.getRow(i).getCell(1).toString();
						data3=sh.getRow(i).getCell(1).toString();
					}
				}catch(Exception e) {}
				
			}
			if(flag==true) {
				System.out.println(data1);
				System.out.println(data2);
				System.out.println(data3);
			}else {
				System.out.println(ExpectedTestid+"data is not available");
			}
			wb.close();	
				
				
				
				
	}

}
