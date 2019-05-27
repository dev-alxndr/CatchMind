package example.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;


public class Server {
	private static final int PORT = 12345;
	private Socket socket;
	private ServerSocket ss;

	String nick = null;
	private BufferedReader br;

	public Server() {

	}

	public void Connection() {
		try {
			ss = new ServerSocket(PORT);
			System.out.println("Server Running....");

			while (true) {
				System.out.println("waiting....");
				socket = ss.accept();

				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				nick = br.readLine();
				System.out.println(nick + "접속");
				RunServer sender = new RunServer(socket, nick);
				System.out.println("Connected to" + socket.getInetAddress());
				
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.Connection();
	}
}


class RunServer {
	private Socket socket;
	private HashMap<String, PrintWriter> map;
	private String nick = "";  
	
	
	RunServer(Socket socket, String nick) {
		map = new HashMap<String, PrintWriter>();
		this.nick = nick;
		this.socket = socket;
	}

	public void join_member(Socket socket, PrintWriter writer, String nick) {
		map.put(nick, writer);
	}

	// 클라이언트에게 전송

	
	
	// 클라이언트로 부터 받음
	class Receiver extends Thread {

		private Socket socket;
		private BufferedReader br;
		
		public Receiver(Socket socket) {
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
			String str = "";
			
			while(true) {
				try {
					if((str = br.readLine())!=null) {
						sendAll(str);
						System.out.println("client :" + str);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		// 모든 사용자에게 전파
		public void sendAll(String str) {
			synchronized (map) {
				Collection<PrintWriter> collection = map.values();
				Iterator<?> iter = collection.iterator();
				while(iter.hasNext()) {
					PrintWriter pw = (PrintWriter) iter.next();
					pw.println(str);
					pw.flush();
				}
			}
		}
	}
}