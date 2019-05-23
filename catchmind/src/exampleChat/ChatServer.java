package exampleChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ChatServer {
	private static final int PORT = 12345;
	public static void main(String[] args) {
		
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("run Server port = "+ PORT);
			HashMap<String, Object> hm = new HashMap<String, Object>();
			
			while(true) {
				System.out.println("waiting...");
				Socket socket = serverSocket.accept();
				Server server = new Server(socket, hm);
				
				server.start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

class Server extends Thread{
	private Socket socket = null;
	private HashMap<String, Object> hm = null;	//사용자의 아이디와 사용자마다의 Writer 저장 
	private BufferedReader br = null;
	private String id = null;
	private boolean initFlag = false;
	
	
	public Server(Socket socket, HashMap<String, Object> hm	) {
		this.socket = socket;
		this.hm = hm;
		
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			id = br.readLine();
			broadcast(id + "= Join");
			
			synchronized (hm) {
				hm.put(this.id, pw);
			}
			initFlag = true;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		String line = null;
		try {
			while((line = br.readLine())!=null) {
				sendMsg(line);
				if(line.equals("/quit")) {
					break;
				}
				
				if(line.indexOf("/to") == 0) {
					sendMsg(line);
				}else {
					broadcast(id + "   :   "+ line);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			synchronized (hm) {
				hm.remove(id);
			}
			broadcast(id+" is OUT");
			try {
				if(socket != null) {
					socket.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendMsg(String line) {
		int start = line.indexOf(" ")+1;
		int end = line.indexOf(" ", start);
		
		if(end != -1) {
			String to = line.substring(start, end);
			String msg = line.substring(end+1);
			Object obj = hm.get(to);
			if(obj != null) {
				PrintWriter pw =(PrintWriter) obj;
				pw.println(id+"whisper :" + msg);
				pw.flush();
			}
		}
	}
	
	
	public void broadcast(String line) {
		synchronized (hm) {
			Collection<Object> collection = hm.values();
			Iterator<?> iter = collection.iterator();
			while(iter.hasNext()) {
				PrintWriter pw = (PrintWriter) iter.next();
				pw.println(line);
				pw.flush();
			}
		}
	}
	
}
















