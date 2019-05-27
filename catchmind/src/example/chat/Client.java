package example.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private static final int PORT = 12345;
	private PrintWriter pw;

	public Client() {
		Scanner s = new Scanner(System.in);
		String nick = s.nextLine();
		try {
			Socket socket = new Socket("localhost", PORT);
			System.out.println("connted complete");

			PrintWriter pw = new PrintWriter(socket.getOutputStream());

			pw = new PrintWriter(socket.getOutputStream());

			pw.println(nick);
			pw.flush();

			GetStr gs = new GetStr(socket);
			gs.start();
			System.out.println("Start to Read");
			
			SendStr ss = new SendStr(socket);
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Client();
	}

}

class SendStr {	
	private Socket socket;
	private PrintWriter pw;

	SendStr(Socket socket) {
		this.socket = socket;
		try {
			pw = new PrintWriter(socket.getOutputStream());
			go_chat();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void go_chat() {
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("wating for keyboard");
		String line = "";
		try {
			while( (line = keyboard.readLine()) != null) {
				pw.println(line);
				pw.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class GetStr extends Thread {
	private Socket socket;
	private BufferedReader br;

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
		String msg= "";
		try {
			while(true) {
				if ((msg = br.readLine()) != null) {
					System.out.println("서버로 부터옴 : " + msg);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
