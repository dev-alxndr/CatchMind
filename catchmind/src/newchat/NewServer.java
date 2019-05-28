package newchat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import newchat.NewUser.UserInfo;

public class NewServer {
	private static final int PORT = 12345; 
	private ServerSocket serverSocket;
	private UserInfo userInfo;
	
	public NewServer() {
		try {
			serverSocket = new ServerSocket(PORT);// PORT OPEN
			while(true) {	//while waiting for clients
				System.out.println("running server");
				Socket socket = serverSocket.accept();
				
				// 그작업은 쓰레드 클래스에서 진행하도록 한다.
				// 클라이언트 접속시 입출력 스트림을 얻어야 한다.
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	// MAIN
	public static void main(String[] args) {
		
	}

}
