package example.chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class AccessDB {
	ResultSet rs;
	Statement stmt;
	
	
	public AccessDB() {
		
	}
	
	// 로그인 하기
	int do_login(String id, String pw) {
		System.out.println("do_login ="+id +"/" +pw);
		
		return 1; // success = 1 / failed = 0;
	}

	int set_userInfo(String id, String password, String nick) {
		System.out.println("ser_userinfo = "+id+"/"+password+"/"+nick);
		
		return 1;
	}
	public void dbConn() throws SQLException {
		String id = null;
		String password = null;
		String nick = "";
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/book_db?serverTimezone=Asia/Seoul"; 
			
			conn = DriverManager.getConnection(url, "root", "1234");
			
			// statement �������
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM user");
			
			while(rs.next()) {
				id = rs.getString("id");
				password = rs.getString("password");
				nick = rs.getString("nick");
			}
			System.out.println(id);
		} catch (ClassNotFoundException e) {
			
		} catch (SQLException e) {
			
		}

	}
	
}
