package example.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

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
	

	class Sender extends Thread {
		private Socket socket;
		private PrintWriter writer;
	

		public Sender(Socket socket, String nick) {
			
			

			try {
				writer = new PrintWriter(socket.getOutputStream());

			} catch (IOException e) {
				map.remove(nick);
				e.printStackTrace();

			}

		}

		public void join_member(Socket socket, PrintWriter writer, String nick) {
			map.put(nick, writer);
		}

		@Override
		public void run() {
			writer.write("HI Client");
		}
	}

	class Receiver extends Thread {

		private Socket socket;
		private BufferedReader br;

		@Override 
		public void run() {
			// TODO Auto-generated method stub
			super.run();
		}
	}
}