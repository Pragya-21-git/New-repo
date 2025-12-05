package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleDataFromExcelTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\utsav\\Downloads\\TekExcel1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");
		
		int rowCount = sh.getLastRowNum();
		for(int i= 1; i<=rowCount; i++)
		{
			Row row = sh.getRow(i);
			String column1Data = row.getCell(0).toString();
			String column2Data = row.getCell(1).toString();
			System.out.println(column1Data + "\t" + column2Data);
		}
		wb.close();
	}

}
