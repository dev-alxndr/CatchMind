//package example.chat;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.util.Scanner;
//
//public class Client {
//	private static final int PORT = 12345;
//	private PrintWriter pw;
//	public Client() {
//		try {
//			Socket socket = new Socket("localhost", PORT);
//			System.out.println("connted complete");
//			
//			Scanner s = new Scanner(System.in);
//			String nick = s.nextLine();
//			pw = new PrintWriter(socket.getOutputStream());
//			pw.println(nick);
//			pw.flush();
//			
//			
//			GetStr gs = new GetStr(socket);
//			gs.start();
//			nick = s.nextLine();
//		}catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//	}
//	
//	
//	public static void main(String[] args) {
//		
//		new Client();
//	}
//
//}
//
//
//class sendStr extends Thread{
//	private Socket socket;
//	private PrintWriter pw;
//	
//	
//	sendStr(Socket socket){
//		this.socket = socket;
//		try {
//			pw = new PrintWriter(socket.getOutputStream());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void run() {
//		pw.println(str);
//		pw.flush();
//	}
//}
//
//
//
//
//class GetStr extends Thread{
//	private Socket socket;
//	private BufferedReader br;
//	
//	GetStr(Socket socket){
//		this.socket = socket;
//		try {
//			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Override
//	public void run() {
//		try {
//			if(br.readLine()!=null) {
//				System.out.println("서버로 부터옴 : "+ br.readLine());
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
