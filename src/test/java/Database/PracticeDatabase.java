package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class PracticeDatabase {
	@Test
	public void test() throws SQLException {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		Connection con = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm","root","root");
		System.out.println("done");
		Statement stat = con.createStatement();
		ResultSet q = stat.executeQuery("Select * from project"); 
		while(q.next()) {
			System.out.println(q.getString(1)+"\t"+q.getString(2));
			}
		con.close();
		}
		
	}

