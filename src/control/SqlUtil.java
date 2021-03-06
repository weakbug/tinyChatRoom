package control;

import java.sql.*;

import model.Member;
public class SqlUtil {
	private static Connection conn;
	private static Statement stat;
	private static PreparedStatement ps = null;

	public static void init(){
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("opened database successfully");
			
			stat = conn.createStatement();
			String sql = "CREATE TABLE  IF NOT EXISTS USERINFO"+
							   "(NICKNAME TEXT PRIMARY KEY  NOT NULL ,"+
							   "PASSWORD CHAR(20)                 NOT NULL )";
			stat.executeUpdate(sql);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//添加帐号昵称及密码
	public static void addUser(Member member){
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			stat = conn.createStatement();
			String nickName = member.getNickname();
			String password = member.getPassword();
//			String sql = "INSERT INTO USERINFO(NICKNAME,PASSWORD) "+
//								"VALUES(?,?,?,?);";
			String sql = "INSERT INTO USERINFO(NICKNAME,PASSWORD) "+
					"SELECT "+"'"+nickName+"',"+"'"+password+"'"+" WHERE not exists(select * FROM USERINFO where NICKNAME='"+nickName+"');";
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, nickName);
//			ps.setString(2,password);
//			ps.setString(3,ipAddress);
//			ps.setString(4, publicKey);
//			ps.executeUpdate();
			stat.executeUpdate(sql);
			stat.close();
			conn.close();
			System.out.println("adduser "+nickName+" done");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}
	//显示单独用户信息
	public static void selectUser(String nickName){
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			stat = conn.createStatement();
			
			ResultSet rs=stat.executeQuery("select * from USERINFO WHERE NICKNAME = '"+nickName+"'");
	        String checkNickName=rs.getString("NICKNAME");
	        String checkPassword=rs.getString("PASSWORD");
	        System.out.println(checkNickName+"\t"+checkPassword);
	        stat.close();
			conn.close();
			System.out.println("selectuser "+nickName+" done");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	//显示全部用户信息
	public static void selectAllUser(){
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			stat = conn.createStatement();
			
			ResultSet rs=stat.executeQuery("select * from UserInfo");
	        while(rs.next()){
	            String checkNickName=rs.getString("NICKNAME");
	            String checkPassword=rs.getString("PASSWORD");
	            System.out.println(checkNickName+"\t"+checkPassword);
	        }
	        stat.close();
			conn.close();
			System.out.println("selectalluser done");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//删除单个用户信息
	public static void deleteUser(String nickName){
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			stat = conn.createStatement();
		    String sql = "DELETE from USERINFO WHERE NICKNAME = '"+nickName+"'";
		    stat.executeUpdate(sql);
		    stat.close();
			conn.close();
			System.out.println("deleteuser "+nickName+" done");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void updateUser(String nickName,String password){
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			stat = conn.createStatement();
		    String sql = "UPDATE USERINFO set PASSWORD='"+password+"',"+
		    													" where NICKNAME= '"+nickName+"'";
		    stat.executeUpdate(sql);
		    stat.close();
			conn.close();
			System.out.println("updateuser "+nickName+" done");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
//	public static void main(String args[]){
//	
//	try{
//		Class.forName("org.sqlite.JDBC");
//		conn = DriverManager.getConnection("jdbc:sqlite:test.db");
//		System.out.println("opened database successfully");
//		
//		stat = conn.createStatement();
//		String sql = "CREATE TABLE  IF NOT EXISTS USERINFO"+
//						   "(NICKNAME TEXT PRIMARY KEY  NOT NULL ,"+
//						   "PASSWORD CHAR(20)                 NOT NULL )";
//		stat.executeUpdate(sql);
//		Member test = new Member("test","test","test","test");
//		addUser(test);//启动时默认添加测试帐号
//		selectAllUser();//显示所有帐号及密码
//	}catch (Exception e) {
//		// TODO: handle exception
//		e.printStackTrace();
//	}
//}
		
}
