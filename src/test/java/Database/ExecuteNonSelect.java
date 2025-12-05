package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ExecuteNonSelect {

	public static void main(String[] args) throws SQLException {
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm","root","root");
		System.out.println("done");
		Statement stat = conn.createStatement();
		int result = stat.executeUpdate("insert into project values('111001','Gopu','2025-05-30','Pragyak','Created','0')");
		System.out.println(result); 
		conn.close();
	}

}
 