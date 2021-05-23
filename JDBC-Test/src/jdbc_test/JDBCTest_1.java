package jdbc_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest_1 {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
//		Class.forName("org.h2.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:h2:test", "admin", "");
		Statement stmt = con.createStatement();
		
		String sql = "Create table Student(id int, name varchar(100))";
		stmt.execute(sql);
		System.out.println("Done");
	}
	
}
