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

import gui.play.Word;

public class Server {
	private AccessDB db;
	private static final int PORT = 12345;
	private ServerSocket ss;
	private String nick = null;
	private BufferedReader br;
	private UserInfoMap userInfoMap;
	private Word word;
	String answerWord = "";

	public Server() {
		userInfoMap = new UserInfoMap();
	}

	public void Connection() {

		try {
			ss = new ServerSocket(PORT);
			System.out.println("Server Running....");
			// HashMap<String, Object> map = new HashMap<String, Object>();
			db = new AccessDB(); // DB 생성
			while (true) {
				System.out.println("waiting....");
				Socket socket = ss.accept(); // socket은 클라이언트마다 새로 생

				RunServer runServer = new RunServer(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server server = new Server(); /// launch
		server.Connection();
	}

	class RunServer {
		private Socket socket;
		// private HashMap<String, Object> map;
		private String nick = "";
		private PrintWriter pw;
		private BufferedReader br;
		// UserInfoMap userInfoMap;

		RunServer(Socket socket) {
			try {
				// this.map = map;// 사용자 객체를 담을 HashMap
				this.socket = socket;
				//

				// br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//
				// nick = br.readLine();
				// System.out.println(nick + "접속");

				pw = new PrintWriter(socket.getOutputStream());

				Receiver r = new Receiver(socket);
				r.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void join_member(PrintWriter writer, String nick) {

			if (userInfoMap.size() < 4) {
				userInfoMap.add(nick, writer);
			} else {
				System.out.println("over 4");
			}
		}

		// 클라이언트에게 전송

		// 클라이언트로 부터 받음
		class Receiver extends Thread {
			// 아아아아아..........
			private Socket socket;
			private BufferedReader br;
			private StringTokenizer st;

			public Receiver(Socket socket) {
				this.socket = socket;

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
							st = new StringTokenizer(str, "#");
							num = Integer.parseInt(st.nextToken());

							message = st.nextToken();
							switch (num) {
							case 100:// try Login
								do_login(message);
								break;
							case 110:
								set_userInfo(message);
								break;
							case 200:
								readyForGame(message);
								break;
							case 300: // 그림 그리기
								do_draw(message);
								break;
							case 311: // 첫번째
								set_FirstXY(message);
								break;
							case 313:
								set_moved(message);
								break;
							case 314:
								set_released();
								break;
							case 320: // 색상 지정
								set_Colors(message);
								break;
							case 330: // 펜 사이즈 두께
								set_penSize(message);
								break;
							case 350: //
								sendAll("350#" + message);
								break;
							case 400: // 채팅 -- 정답체크 해줘야함
								System.out.println(message + "^^^");
								check_answer(message);

								// sendAll("400#"+message);
								break;
							case 600:
								System.out.println("From Android : " + message);
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					// UserInfo exitUser = userInfoMap.getUser(nick);
					// userInfoMap.remove(nick);
					// if (exitUser.host) {
					// if (userInfoMap.size() >= 1) {
					// userInfoMap.getRandomUser().host = true;
					// }
					// }
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

			void do_login(String msg) { // 로그인시 메소드

				st = new StringTokenizer(msg, ",");
				String id = st.nextToken();
				String password = st.nextToken();

				String nick = db.do_login(id, password);
				if (!nick.equals("")) { // null이 아니면 로그인 성공
					pw.println("100#" + nick); // 1 = sucess
					pw.flush();
					readyForGame(nick);

				} else {
					pw.println("100#" + nick); // 0 = failed
					pw.flush();
				}
			}

			void set_userInfo(String message) { // 회원가입 메소드
				st = new StringTokenizer(message, ",");
				String id = st.nextToken();
				String password = st.nextToken();
				String nick = st.nextToken();
				String msg = "";
				int result = db.set_userInfo(id, password, nick);

				if (result == 1) {
					msg = "110#" + result;
					pw.println(msg);
					pw.flush();
				} else {
					msg = "110#" + result;
					pw.println(msg);
					pw.flush();
				}
			}

			void readyForGame(String nick) { // 사용자의 자리 지정이 필요
				join_member(pw, nick); // HashMap에 저장

				join_msg(nick);
				get_players();
				String msg = "202#" + nick + "(이)가 입장하셨습니다."; // 사용자가 입장하셧습니다.
				sendAll(msg);
				if (userInfoMap.size() == 1) {
					userInfoMap.get().get(nick).host = true;
				}
				if (userInfoMap.size() == 2) {
					start_game(userInfoMap.getHost());
				}

			}

			void join_msg(String nick) {
				int seat = userInfoMap.size();

				String msg = "201#" + seat + "*" + nick;
				sendAll(msg);
			}

			void get_players() {
				Iterator<String> it = userInfoMap.get().keySet().iterator();

				String nick = "";
				int seat = 1;
				String message = "";
				while (it.hasNext()) {
					try {
						nick = (String) userInfoMap.get().get(it.next()).name;
						message = "201#" + seat + "*" + nick + "*d";
						seat++;
						sendAll(message);
					} catch (Exception e) {
					}
				} // while
			}

			String give_me_question() { // 문제 제출
				String msg = "";

				word = new Word();
				// getSTr로 단어를 가져옴 460#
				answerWord = word.getStr();

				msg = "460#" + answerWord;
				return msg;
				// sendAll(msg);
			}

			////// Start Flag/////
			void start_game(UserInfo user) {
				System.out.println("Start Game = " + user);
				userInfoMap.setTurn(user);
				set_turn("310#[" + user.name + "]당신차례입니다.");// 차례지정
				String str = give_me_question();
				set_turn(str);
				sendAll("400#[" + user.name + "]님 차례입니다.");
			}

			void set_turn(String msg) {
				Iterator<String> it = userInfoMap.get().keySet().iterator();

				while (it.hasNext()) {
					try {
						UserInfo user = userInfoMap.get().get(it.next());
						if (user.turn) {

							PrintWriter out = user.pw;
							out.println(msg);
							out.flush();
						} else {

							PrintWriter out = user.pw;
							out.print("315#당신차례가 아닙니다.");
							out.flush();
						}
					} catch (Exception e) {
					}
				}
			}

			void set_FirstXY(String message) {		//  나 아닌 다른 사람들에게 전송
				//UserInfo user = userInfoMap.getTurn();
				Iterator<String> it = userInfoMap.get().keySet().iterator();
				while (it.hasNext()) {
					try {
						UserInfo user = userInfoMap.get().get(it.next());
						if (!user.turn) {
							PrintWriter out = user.pw;
							out.println("312#"+message);
							out.flush();
						} 
					} catch (Exception e) {
					}
				} // while
				
				
			}

			void set_moved(String message) {
				message = "313#" + message;
				sendAll(message);
			}
			
			void set_released() {
				Iterator<String> it = userInfoMap.get().keySet().iterator();
				while (it.hasNext()) {
					try {
						UserInfo user = userInfoMap.get().get(it.next());
						if (!user.turn) {
							PrintWriter out = user.pw;
							out.println("314#-");
							out.flush();
						} 
					} catch (Exception e) {
					}
				} // while
				
			}
			

			void do_draw(String message) {

				String msg = "300#" + message;
				sendAll(msg);
			}

			void set_Colors(String message) {
				sendAll("320#" + message);
			}

			private void set_penSize(String message) {
				sendAll("330#" + message);

			}

			void check_answer(String message) {
				st = new StringTokenizer(message, "]");

				String id = st.nextToken();
				String word = st.nextToken();

				System.out.println(word + "/" + answerWord);
				String chk = "";
				///////// 480 = 정답나옴.
				if (word.equals(answerWord)) {
					System.out.println("정답자 나옴.");
					chk = "480#" + id + "]정답을 맞추셨습니다. 정답은 " + answerWord + "입니다.";
					sendAll(chk);
					// give_me_question();
					System.out.println(getUser(id) + "&&&&&&&&");
					start_game(userInfoMap.getUser(getUser(id)));

				} else {
					sendAll("400#" + message);
				}
			}

			public String getUser(String msg) {

				return msg.substring(1);

			}

			// 특정 사용자에게 턴을 보냄.
			public void sendToTurn() {
				userInfoMap.getRandomUser().turn = true;
			}

			// 모든 사용자에게 전파
			public void sendAll(String str) {
				Iterator<String> it = userInfoMap.get().keySet().iterator();
				while (it.hasNext()) {
					try {
						PrintWriter out = (PrintWriter) userInfoMap.get().get(it.next()).pw;
						out.println(str); //
						out.flush();
					} catch (Exception e) {
					}
				} // while
			}
		}
	}

}