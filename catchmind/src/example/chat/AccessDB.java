package example.chat;

public class AccessDB {
	
	public AccessDB() {
		
	}
	
	// 로그인 하기
	int do_login(String id, String pw) {
		System.out.println(id +"/" +pw);
		
		return 1; // success = 1 / failed = 0;
	}
}
