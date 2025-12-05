package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class EcecuteSelectQuery {

	public static void main(String[] args) throws SQLException {
																																									
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm", "root", "root");
		System.out.println("done");
		Statement stat = conn.createStatement();
		ResultSet resultSet = stat.executeQuery("select * from project");
		while(resultSet.next()) {
			System.out.println(resultSet.getString(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3));
		}
		conn.close();
	}

}
