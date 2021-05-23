package jdbc_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest_2 {
	
	public static void main(String[] args) throws SQLException {
		
		Connection con = DriverManager.getConnection("jdbc:h2:test", "admin", "");
		Statement stmt = con.createStatement();
		
		String sql = "Insert into Student values(1, 'Anupam')";
		
		stmt.execute(sql);
		
		System.out.println("Done");
	}

}
