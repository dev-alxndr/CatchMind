package example.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.awt.Color;
import gui.play.Draw;
import gui.play.MakeCanvas;
import gui.play.MakeRoom;
import gui.play.MakeRoom2;
import gui.play.Word;
import gui.user.Login;
import gui.user.Notice;
import gui.user.Register;


public class Client {
	private static final int PORT = 12345;
	private PrintWriter pw;
	String nick;
	SendStr ss;
	private Login login;
	private Client client;
	private Register register;
	private Draw draw;
	private MakeCanvas mc;
	private String temp_id = "";
	private boolean turn = false;
	
	
	public void setLogin(Login login) {
		this.login = login;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public void setRegister(Register register) {
		this.register = register;
	}
	public void set_Draw(Draw draw) {
		this.draw = draw;
	}
	public void set_MakeCanvas(MakeCanvas mc) {
		this.mc = mc;
	}
	
	
	public Client(Login login) {
		this.login = login;
		login.setClient(this);
		login.display();
		runClient();
	}
	
	public void runClient() {
		try {
			Socket socket = new Socket("172.16.52.66", PORT);

			pw = new PrintWriter(socket.getOutputStream());
			
			GetStr gs = new GetStr(socket); // 서버로 부터 데이터를 받기 위한 쓰레드
			gs.start();
			ss = new SendStr(socket, nick); // 서버로 채팅을 보내기 위한클래
			System.out.println("Success Connect");
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
		String msg = status+"["+nick+"]"+ message;
		pw.println(msg);
		pw.flush();
	}
	
	
	public void do_login(String id, String password){
		String str = "100#"+id+","+password;

		nick = id;
		pw.println(str);
		pw.flush();
	}

	public void canvas_Clear() {
		String str = "350#-";
		pw.println(str);
		pw.flush();
	}
	
	
	public void set_Color(String colors) {
		String str = "320#"+colors;
		pw.println(str);
		pw.flush();
	}
	
	public void set_penSize(int pen_size) {
		pw.println("330#"+pen_size);
		pw.flush();
	}
	
	public void set_FirstXY(int x, int y) {
		pw.println("311#"+x+"*"+y);
		pw.flush();
	}
	
	
	
	public void do_signUp(String reg_id, String reg_pw, String reg_nick) {
		String str = "110#"+reg_id+","+reg_pw+","+reg_nick;
		pw.println(str);
		pw.flush();
	}
	
	public void user_draw(int x , int y) {// 사용자가 그림을 그릴때 서버로 좌표값을 전송하는 메소드
		String msg = "300#"+x+"*"+y;
		pw.println(msg);
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

	}

	class GetStr extends Thread { // 클라이언트는 서버로 부터 정보를 받아서 그린다.
		private Socket socket;
		private BufferedReader br;
		private StringTokenizer st;
		private MakeRoom2 makeRoom;
		
		private boolean turn = false;
	
		
		
		
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
						
						switch (num) {
						case 100: //Login
							if(!message.equals("")) {
								login.setVisible(false);
								
								//String str = "200#"+nick;
								System.out.println("[Client] Success LogIn");
								makeRoom = new MakeRoom2(client);								
								
								makeRoom.display();
								//pw.println(str);
								//pw.flush();
							}else {
								Notice notice = new Notice();
								
								notice.text("로그인 실패");
								notice.display("Failed");
							}
							
							break;
						case 110:	//check SignUp
							int check = Integer.parseInt(message);
							Notice notice = new Notice();
							if(check == 1) {
								System.out.println("[Client] Success Sign Up ");
								login.setVisible(true);
								register.setVisible(false);
								notice.text("회원가입 성공");
								notice.display("성공");
							}else {
								System.out.println("[Client] Failed Sign Up");
								notice.text("회원가입 실패");
								notice.display("실패");
							}
							break;
						case 200: //Start Game When players Number is 4
							System.out.println("Start Game");
							
							break;
						case 201 : // set Seat
							st = new StringTokenizer(message,"*");
							int seat = Integer.parseInt(st.nextToken());
							
							String id = st.nextToken();
							
							String msg1 = st.nextToken();
							
							if(seat == 1) {
								makeRoom.lb_user1.setText(id);								
							}else if(seat == 2) {
								makeRoom.lb_user2.setText(id);
							}else if(seat == 3) {
								makeRoom.lb_user3.setText(id);
							}else if(seat == 4) {
								makeRoom.lb_user4.setText(id);
							}
							break;
						case 202:		// 사용자 입장
							appendChat(message);
							break;
						case 300:
							st = new StringTokenizer(message, "*");
							int x = Integer.parseInt(st.nextToken());
							int y = Integer.parseInt(st.nextToken());
							
							mc.x = x;
							mc.y = y;
							mc.repaint();
							break;
						case 310:
							System.out.println("내차례");
							appendChat(message);
							draw.turn = true;
							break;
						case 320:
							st = new StringTokenizer(message, "*");
							int r = Integer.parseInt(st.nextToken());
							int g = Integer.parseInt(st.nextToken());
							int b = Integer.parseInt(st.nextToken());

							mc.color = new Color(r,g,b);
							break;
						case 330:
							int pensize = Integer.parseInt(message);
							mc.pen_size = pensize;
							break;
						case 350:
							draw.graphic = mc.getGraphics();
							draw.graphic.clearRect(0, 0, 900, 900);
							break;
						case 400:	// chat
							appendChat(message);
							break;
						case 460:	// 문제 출제
							makeRoom.lb_answerWord.setText(message);
							break;
						case 480:	// 정답 시
							appendChat(message);
							Notice notice2 = new Notice();
							notice2.text(message);
							notice2.display("Correct");
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
