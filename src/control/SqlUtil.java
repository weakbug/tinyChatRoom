package control;

import java.sql.*;
public class SqlUtil {
	public static void main(String args[]){
		Connection conn =null;
		Statement stat = null;
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("opened database successfully");
			
//			stat.executeUpdate("drop table if exists tbl1;");
//			stat.executeUpdate("create table if not exits)
			stat = conn.createStatement();
			String sql = "CREATE TABLE  COMPANY "+
							   "(NICKNAME TEXT PRIMARY KEY   NOT NULL,"+
							   "PASSWORD CHAR(20)                 NOT NULL,"+
							   "IPADRESS    CHAR(20)                 NOT NULL,"+
							   "PUBLICKEY  CHAR(50)                 )";
			stat.executeUpdate(sql);
			stat.close();
			conn.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
//			System.out.println("error");
//			System.exit(0);
		}
		
	}
}
