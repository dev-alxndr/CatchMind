package example.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;
import gui.play.MakeRoom;
import gui.user.Login;


public class Client {
	private static final int PORT = 12345;
	private MakeRoom makeRoom;
	private Login login;
	private PrintWriter pw;
	String nick;
	private Client client;
	public void setLogin(Login login) {
		System.out.println("SetLogin");
		this.login = login;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Client(Login login) {
		this.login = login;
		login.setClient(this);
		login.display();
		runClient();
	}
	
	public void runClient() {
		//makeRoom = new MakeRoom();
		//makeRoom.display();
		//makeRoom.launch_client(this);
		try {
			Socket socket = new Socket("192.168.0.6", PORT);

			pw = new PrintWriter(socket.getOutputStream());
			
			GetStr gs = new GetStr(socket); // 서버로 부터 데이터를 받기 위한 쓰레드
			gs.start();
			SendStr ss = new SendStr(socket, nick); // 서버로 채팅을 보내기 위한클래
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
		System.out.println(msg);
		pw.println(msg);
		pw.flush();
	}
	public void do_login(String id, String password){
		System.out.println("do_Login : "+id+"/"+password);
		String str = "100#"+id+","+password;
		nick = id;
		pw.println(str);
		pw.flush();
	}

	class SendStr {
		private Socket socket;
		//private PrintWriter pw;
		private String nick;
		// private Play play;

		SendStr(Socket socket, String nick) {
			this.socket = socket;
			this.nick = nick;

		}
		
		
	
		
		public void go_chat() {
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)); // 키보드 입력을 위한 버
			String line = "";
			try {
				while ((line = keyboard.readLine()) != null) {
					pw.println(line);
					pw.flush();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public String user_draw() {// 사용자가 그림을 그릴때 서버로 좌표값을 전송하는 메소드

			return "";
		}

	}

	class GetStr extends Thread { // 클라이언트는 서버로 부터 정보를 받아서 그린다.
		private Socket socket;
		private BufferedReader br;
		private StringTokenizer st;
		int people = 0;
		
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
							//makeRoom.ta_chatting.append(message);
							if(message.equals("1")) {
								login.setVisible(false);
								MakeRoom mr = new MakeRoom();
								mr.set_client(client);
								mr.display();
								
							}else {
								System.out.println(message + "/ denied");
							}
							
							break;
						case 200: //Ready for Game
							st = new StringTokenizer(message,"#");
							String id = st.nextToken();
							if(st.hasMoreTokens()) {
								String note = st.nextToken();	
								//System.out.println("note : "+note);
							}
							people++;
							makeRoom.lb_userName1.setText(id);
							System.out.println(""+people);
							break;
						case 400:	// chat
							makeRoom.ta_chatting.append(message+" \n");
							makeRoom.tf_msg.setText("");
							break;
						}
					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
