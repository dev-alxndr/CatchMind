package example.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private static final int PORT = 12345;
	public Client() {
		Scanner s = new Scanner(System.in);
		String nick = s.nextLine();
		try {
			Socket socket = new Socket("localhost", PORT);
			System.out.println("connted complete");
			
			
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			pw.println(nick);
			pw.flush();
			
			
			
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	public static void main(String[] args) {
		
		new Client();
	}

}
