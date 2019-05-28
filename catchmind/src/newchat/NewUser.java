package newchat;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class NewUser {

	private String nick; // 사용자의 닉네임 혹은 아이디
	private PrintWriter pw; // 연결된 아웃풋 스트
	private boolean turn; // 사용자 차례인지
	private boolean host; // 방장인가

	public NewUser() {
		this.nick = null;
		this.pw = null;
		this.turn = false;
		this.host = false;
	}

	public NewUser(String nick, PrintWriter pw) {
		this.nick = nick;
		this.pw = pw;
	}

	class UserInfo {
		// UserInfo를 HashMap으로 가지고있음.
		HashMap<String, NewUser> clients;

		public UserInfo() {
			clients = new HashMap<String, NewUser>();
			Collections.synchronizedMap(clients); // 교통정리란다.?
		}

		public void add(String nick, PrintWriter pw) {
			clients.put(nick, new NewUser(nick, pw));
		}

		public void remove(String nick) {
			clients.remove(nick);
		}

		public HashMap<String, NewUser> get_map() {
			return clients;
		}

		public int clients_size() {
			return clients.size();
		}

		// 현재 방장을 리턴
		NewUser getHost() {
			Iterator it = clients.keySet().iterator();

			while (it.hasNext()) {
				try {
					NewUser user = clients.get(it.next());
					if (user.host) {
						return user;
					}
				} catch (Exception e) {
				}
			}
			return null;
		}
		
		// 현재 턴을 리
		NewUser getTurn() {
			Iterator it = clients.keySet().iterator();

			while (it.hasNext()) {
				try {
					NewUser user = clients.get(it.next());
					if (user.turn) {
						return user;
					}
				} catch (Exception e) {
				}
			}
			return null;
		}
		
		// 유저를 현재 턴으로 설정
	    void setTurn(NewUser user) {
	        Iterator it = clients.keySet().iterator();

	        while (it.hasNext()) {
	            try {
	                NewUser tmp = clients.get(it.next());
	                if (tmp == user) {
	                    tmp.turn = true;
	                } else {
	                    tmp.turn = false;
	                }
	            } catch (Exception e) {
	            }
	        }
	    }
	    
	    // 랜덤 유저를 반
	    NewUser getRandomUser() {
	        Iterator it = clients.keySet().iterator();
	        NewUser user = null;
	        int randomIndex = (int) (Math.random() * clients.size()) + 1;
	        for (int i = 0; i < randomIndex; i++) {
	            user = clients.get(it.next());
	        }
	        return user;
	    }

	}

}
