package example.chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class AccessDB {
	ResultSet rs = null;
	Statement stmt = null;
	Connection conn = null;
	PreparedStatement pstm = null; 
	
	String url = "";
	int score = 0;
	public AccessDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url =  "jdbc:mysql://localhost:3306/catchmind?serverTimezone=Asia/Seoul";
			System.out.println("Connected DB");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void closeDatabases() {
		try {
			if(conn != null) {
				conn.close(); 
			}
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(pstm != null) {
				pstm.close();
			}
			
		}catch(SQLException e) {
			System.out.println("error : "+ e.getStackTrace());
		}
		
	}
	
	// 로그인 하기
	String do_login(String id, String pw) {
		int chk = 0;
		String password= "";
		String nick = "";
		int score = 0;
		
		try {
			conn = DriverManager.getConnection(url, "root", "1234");

			String sql = "select * from user where id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1 , id);
			rs = pstm.executeQuery();
			while(rs.next()) {
				id = rs.getString("id");
				password = rs.getString("password");
				nick = rs.getString("nick");
				
			}
			if(pw.equals(password)) {
				chk = 1;
			}else {
				chk = 0;
			}
		} catch (SQLException e) {
			e.getStackTrace();
			closeDatabases();
		} catch(Exception e1) {
			e1.getStackTrace();
		}
		return nick;
	}

	public int set_userInfo(String id, String password, String nick) {
		System.out.println("ser_userinfo = "+id+"/"+password+"/"+nick);
		int chk = 0;
		try {
			conn = DriverManager.getConnection(url, "root", "1234");

			String sql = "insert into user(id, password, nick, score) values(?, ?, ?, ?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1 , id);
			pstm.setString(2, password);
			pstm.setString(3, nick);
			pstm.setInt(4, 0);
			chk = pstm.executeUpdate();
		} catch (SQLException e) {
			e.getStackTrace();
			closeDatabases();
		} catch(Exception e1) {
			e1.getStackTrace();
		}
		return chk;

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
			System.out.println("id값을 확인"+id);
		} catch (ClassNotFoundException e) {
			
		} catch (SQLException e) {
			
		}

	}
	
	public void update_score(String id) {
		//맞춘 아이디로 스코어를 찾고
		//스코어에 +10점
		try {
			conn = DriverManager.getConnection(url, "root", "1234");

//			String sql = "select * from user where id = ?";
			String sql = "update user set score = score + 10 where id = ?";
			System.out.println(sql);
			pstm = conn.prepareStatement(sql);
			pstm.setString(1 , id);
			pstm.executeUpdate();
			System.out.println("id :: " + id);
			
		} catch (SQLException e) {
			e.getStackTrace();
			closeDatabases();
		} catch(Exception e1) {
			e1.getStackTrace();
		}
	}
	
}
