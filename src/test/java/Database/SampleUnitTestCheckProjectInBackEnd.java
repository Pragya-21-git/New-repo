package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class SampleUnitTestCheckProjectInBackEnd {

	@Test
	public void projectCheckTest() throws SQLException {
		
		String xpectedProjectName = "Pragyak";
		boolean flag=false;
		Driver driverRef = new Driver();
		   
		DriverManager.registerDriver(driverRef);
		
		DriverManager.getConnection("jdbe:mysql://49.249.28.218:3333/ninza_hrm", "root", "root");
		
		//Connection conn = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm","root","root");
		
		System.out.println("done");
		//Statement stat = conn.createStatement();
		//ResultSet resultSet = stat.executeQuery("select * from project");
		//while(resultSet.next()) {
			//String actProjectName = resultSet.getString(4);
			//if(xpectedProjectName.equals(actProjectName)) {
				 //flag = true;
				//System.out.println(xpectedProjectName + "is available == PASS");
		//	}
			
		//}
		//if(flag==false) {
		//	System.out.println(xpectedProjectName + "is not available == PASS");
		//}
		//conn.close();
	}
	
}
