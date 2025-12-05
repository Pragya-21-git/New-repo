package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class GetConnectionWithDatabase {
	public static void main(String[] args) throws SQLException {
		
		//Step1: load/register database in computer
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		
		//Step2: connect to database
		 Connection conn = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm","root","root");
		System.out.println("done");
		
		
		//Step3: create SQL statement
		Statement stat = conn.createStatement();
		
		//Step4: execute select query and 
		ResultSet resultSet = stat.executeQuery("Select * from project");
		resultSet.next();
	
		//close the connection
		conn.close();
	
}
}