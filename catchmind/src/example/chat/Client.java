package example.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;
import gui.play.MakeRoom;
import gui.play.MakeRoom2;
import gui.user.Login;
import gui.user.Notice;
import gui.user.Register;


public class Client {
	private static final int PORT = 12345;
	private Login login;
	private PrintWriter pw;
	String nick;
	SendStr ss;
	private Client client;
	private Register register;
	
	public void setLogin(Login login) {
		this.login = login;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public void setRegister(Register register) {
		this.register = register;
	}
	
	public Client(Login login) {
		this.login = login;
		login.setClient(this);
		login.display();
		runClient();
	}
	
	public void runClient() {
		try {
			Socket socket = new Socket("localhost", PORT);

			pw = new PrintWriter(socket.getOutputStream());
			
			GetStr gs = new GetStr(socket); // 서버로 부터 데이터를 받기 위한 쓰레드
			gs.start();
			ss = new SendStr(socket, nick); // 서버로 채팅을 보내기 위한클래
			System.out.println("Server Ready");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Client() {
		runClient();
	}
	// 사용자 채팅 전
	public void send_msg(String message) {
		String status = "400#";
		String msg = status+"["+nick+"] "+ message;
		pw.println(msg);
		pw.flush();
	}
	
	
	public void do_login(String id, String password){
		String str = "100#"+id+","+password;
		System.out.println(str);
		nick = id;
		pw.println(str);
		pw.flush();
	}

	
	
	public void do_signUp(String reg_id, String reg_pw, String reg_nick) {
		String str = "110#"+reg_id+","+reg_pw+","+reg_nick;
		pw.println(str);
		pw.flush();
	}
	
	public int go_signUp(String str) {
		return 0; 
		
	}

	class SendStr {
		private Socket socket;
		private String nick;


		SendStr(Socket socket, String nick) {
			this.socket = socket;
			this.nick = nick;

		}
//		public void go_chat() {
//			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)); // 키보드 입력을 위한 버
//			String line = "";
//			try {
//				while ((line = keyboard.readLine()) != null) {
//					pw.println(line);
//					pw.flush();
//				}
//			} catch (IOException e) {
//
//				e.printStackTrace();
//			}
//		}

		public String user_draw() {// 사용자가 그림을 그릴때 서버로 좌표값을 전송하는 메소드

			return "";
		}
	}

	class GetStr extends Thread { // 클라이언트는 서버로 부터 정보를 받아서 그린다.
		private Socket socket;
		private BufferedReader br;
		private StringTokenizer st;
		private MakeRoom2 makeRoom;
		
		GetStr(Socket socket) {
			this.socket = socket;
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			String msg = "";
			int num = 0;
			String message = "";
			try {
				while (true) {
					if ((msg = br.readLine()) != null) {
						st = new StringTokenizer(msg, "#");
						num = Integer.parseInt(st.nextToken());
						
						message = st.nextToken();
						System.out.println(message + "......");
						switch (num) {
						case 100: //Login
							if(message.equals("1")) {
								login.setVisible(false);
								MakeRoom2 mr = new MakeRoom2();
								this.makeRoom = mr;
								String str = "200#"+nick;
								makeRoom.set_client(client);
								makeRoom.display();
								pw.println(str);
								pw.flush();
							}else {
								System.out.println(message + "/ denied");
							}
							
							break;
						case 110:	//check SignUp
							Notice notice = new Notice();
							int check = Integer.parseInt(message);
							if(check == 1) {
								login.setVisible(true);
								register.setVisible(false);
								notice.text("회원가입 성공");
								notice.display("성공");
							}else {
								notice.text("회원가입 실패");
								notice.display("실패");
							}
							break;
						case 200: //Ready for Game
							st = new StringTokenizer(message,"*");
							String seat = st.nextToken();
							System.out.println(seat +"-11");
							String id = st.nextToken();
							System.out.println(id + "--2");
							String msg1 = st.nextToken();
							System.out.println(msg1+"--3" );
							
							appendChat((id+msg1));
							//makeRoom.lb_userName1.setText(id);
							break;
						case 400:	// chat
							appendChat(message);
							break;
						}
					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void appendChat(String str) {
			makeRoom.ta_chatlog.append(str+" \n");			
		}
	}
	
}
