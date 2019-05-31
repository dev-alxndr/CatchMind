package example.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.ObjectOutputStream.PutField;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;




public class Server {
	private static final int PORT = 12345;
	private ServerSocket ss;
	private String nick = null;
	private BufferedReader br;
	private UserInfoMap userInfoMap;
	public Server() { 
		userInfoMap = new UserInfoMap();	
	}
/*
 *  while (it.hasNext()) {
            try {
                PrintWriter out = (PrintWriter) userInfoMap.get().get(it.next()).pw;
                out.println(msg);
                out.flush();
            } catch (Exception e) {
            }
        } // while
 * */
	// 클라이언트 접수 대기....
	public void Connection() {
		
		try {
			ss = new ServerSocket(PORT);
			System.out.println("Server Running....");
			//HashMap<String, Object> map = new HashMap<String, Object>();
			
			while (true) {
				System.out.println("waiting....");
				Socket socket = ss.accept(); // socket은 클라이언트마다 새로 생

				RunServer runServer = new RunServer(socket, userInfoMap);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server server = new Server();	/// launch
		server.Connection();
	}
	
	class RunServer {
		private Socket socket;
		//private HashMap<String, Object> map;
		private String nick = "";
		private PrintWriter pw;
		private BufferedReader br;
		private UserInfoMap userInfoMap;
		
		
		RunServer(Socket socket, UserInfoMap userInfoMap) {
			try {
				//this.map = map;// 사용자 객체를 담을 HashMap
				this.socket = socket;
				this.userInfoMap = userInfoMap;
				
				//br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//				nick = br.readLine();
//				System.out.println(nick + "접속");

				pw = new PrintWriter(socket.getOutputStream());
				

				Receiver r = new Receiver(socket);
				r.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void join_member(PrintWriter writer, String nick) {
			
			if(userInfoMap.size() < 4) {
				userInfoMap.add(nick, writer);
				
			}else {				
				System.out.println("over 4");
			}
		}

		// 클라이언트에게 전송

		// 클라이언트로 부터 받음
		class Receiver extends Thread {
			//아아아아아..........
			private Socket socket;
			private BufferedReader br;
			private StringTokenizer st;
			private AccessDB db;
			
			public Receiver(Socket socket) {
				this.socket = socket;
				db = new AccessDB();	// DB 생성
				
				try {
					br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void run() {
				String str = "";
				String message = "";
	            int num = 1;
				try {
					while (true) {
						if ((str = br.readLine()) != null) {
							st = new StringTokenizer(str,"#");						
							num = Integer.parseInt(st.nextToken());
							
							message = st.nextToken();
							switch(num) {
								case 100:// try Login
									do_login(message);	
									break;
								case 110:
									set_userInfo(message);
									break;
								case 200:
									readyForGame(message);
									break;
								case 300:									
									break;
								case 400:
									sendAll("400#"+message);
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					//UserInfo exitUser = userInfoMap.getUser(nick);
					//userInfoMap.remove(nick);
//					if (exitUser.host) {
//	                    if (userInfoMap.size() >= 1) {
//	                        userInfoMap.getRandomUser().host = true;
//	                    }
//	                }
//					
					try {
						if (socket != null) {
							socket.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			void readyForGame(String nick) {
				String message = "200#"+nick+"#님이 입장하셨습니다.";
				join_member(pw, nick); // HashMap에 저장
				//pw.println(message);
				//pw.flush();
				sendAll(message);
			}
			
			
			void set_userInfo(String message) {	// 회원가입 메소드
				st = new StringTokenizer(message, ",");
				String id = st.nextToken();
				String password = st.nextToken();
				String nick = st.nextToken();
				String msg = "";
				int result = db.set_userInfo(id, password, nick);
				System.out.println("set_userInfo = "+ result);
				if(result == 1) {
					msg = "110#"+result;
					pw.println(msg);
					pw.flush();
				}else {
					msg = "110#"+result;
					pw.println(msg);
					pw.flush();
				}
				
			}
		
			void do_login(String msg) {	// 로그인시 메소드
				
				st = new StringTokenizer(msg, ",");
				String id = st.nextToken();
				String password = st.nextToken();
				
				int result = db.do_login(id, password);
					if(result == 1) {	
						pw.println("100#"+result); // 1 = sucess
						pw.flush();
					}else {
						pw.println("100#"+result); // 0 = failed
						pw.flush();
					}
				
			}
			
			// 모든 사용자에게 전파
			public void sendAll(String str) {
				Iterator it = userInfoMap.get().keySet().iterator();
		        while (it.hasNext()) {
		            try {
		                PrintWriter out = (PrintWriter) userInfoMap.get().get(it.next()).pw;
		                out.println(str);
		                out.flush();
		            } catch (Exception e) {
		            }
		        } // while
			}
		}
}


}