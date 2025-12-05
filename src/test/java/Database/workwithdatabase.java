package Database;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class workwithdatabase {
   @Test
	public  void main() throws SQLException {
		 
		Driver driver=new Driver();
		
		DriverManager.registerDriver(driver);
		DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm", "root", "root");
		System.out.println("====done===="); 
		
		

	}

}
