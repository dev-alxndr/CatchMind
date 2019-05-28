package example.chat;

public class AccessDB {
	
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
	
	
}
