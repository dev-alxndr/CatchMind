package gui.play;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import example.chat.Client;

//public class Play {
//
//	public static void main(String[] args) {
//		MakeRoom mr = new MakeRoom();
//		mr.display();
//	}
//
//}
public class MakeRoom extends JFrame implements ActionListener{
	//전체 패널
	public JPanel p_layout;
	//유저 패널
	public JPanel p_leftuser, p_rightuser;
	public JPanel p_user1, p_user2, p_user3, p_user4;
	//뒤로가기 버튼,중앙패널(단어, 그림판),하단(채팅)
	public JPanel p_menubar, p_center, p_paintCanvas, p_answerWord, p_chat, p_chatlog, p_send;

	//유저 이름
	public JLabel lb_userName1, lb_userName2, lb_userName3, lb_userName4;
	public JLabel lb_answerWord;

//	점수 스코어 채팅시 -10점 정답시 +100
//	JLabel lb_userScore1, lb_userScore2, lb_userScore3, lb_userScore4;
//	JLabel lb_scoreView1, lb_scoreView2, lb_scoreView3, lb_scoreView4;
	
	public JButton btn_send, btn_logout;
	public JTextArea ta_chatting;
	public JTextField tf_msg;
	Client client;

	public void set_client(Client client) {
		this.client = client;
		System.out.println("dddddd");
	}
	//삭제할 버튼(자리 확인용)
	JButton btn1 = new JButton("자리 확인용 버튼");
	public MakeRoom(){
		
		p_layout = new JPanel(new BorderLayout());
		//위쪽
		p_menubar = new JPanel();
		//중앙
		p_center = new JPanel(new GridLayout(3, 1));
			p_answerWord = new JPanel();
			p_paintCanvas = new JPanel();
		p_answerWord = new JPanel(new FlowLayout());
		p_paintCanvas = new JPanel();
		//왼쪽
		p_leftuser = new JPanel(new GridLayout(2, 1));
			p_user1 = new JPanel();
			p_user2 = new JPanel();
		//오른쪽
		p_rightuser = new JPanel(new GridLayout(2, 1));
			p_user3 = new JPanel();
			p_user4 = new JPanel();
		//하단
		p_chat = new JPanel(new GridLayout(2, 1));
			p_chatlog = new JPanel();
			p_send = new JPanel(new GridLayout(1, 2));
		
		lb_userName1 = new JLabel("유저1");
		lb_userName2 = new JLabel("유저2");
		lb_userName3 = new JLabel("유저3");
		lb_userName4 = new JLabel("유저4");
		
		
		ta_chatting = new JTextArea(15,15);
		tf_msg = new JTextField(15);
		btn_logout = new JButton("로그아웃");
		btn_send = new JButton("보내기");
		
		lb_answerWord = new JLabel("답이 표시되는 곳");
		
		p_layout.add(p_menubar,BorderLayout.NORTH);
		p_menubar.add(btn_logout);
		p_menubar.add(ta_chatting);
		p_menubar.add(tf_msg);
		p_menubar.add(btn_send);
		
		
		add(p_layout);
		
		p_layout.add(p_leftuser,BorderLayout.WEST);
		p_leftuser.add(p_user1);
		p_leftuser.add(p_user2);
		p_user1.add(lb_userName1);
		p_user2.add(lb_userName2);
		
		p_layout.add(p_rightuser,BorderLayout.EAST);
		p_rightuser.add(p_user3);
		p_rightuser.add(p_user4);
		p_user3.add(lb_userName3);
		p_user4.add(lb_userName4);
		
		p_layout.add(p_center,BorderLayout.CENTER);
		p_center.add(p_answerWord);
			p_answerWord.add(lb_answerWord);
			
		p_center.add(p_paintCanvas);
		p_center.add(p_chat);
			p_chat.add(p_chatlog);
				//p_chatlog.add(ta_chatting);
			p_chat.add(p_send);
				//p_send.add(tf_msg);
				//p_send.add(btn_send);
		
		p_layout.add(btn1, BorderLayout.SOUTH);
		
//		p_layout.add(p_chat,BorderLayout.SOUTH);
//		p_chat.add(p_chatlog);
//		p_chatlog.add(ta_chatting);
//		p_chat.add(p_send);
//			p_send.add(tf_msg);
//			p_send.add(btn_send);
		
//		add(lb_userName1);
//		add(lb_userName2);
//		add(lb_userName3);
//		add(lb_userName4);
//		add(btn_logout);
//		add(btn_send);
//		add(ta_chatting);
//		add(tf_msg);
		
		p_layout.setPreferredSize(new Dimension(1000, 1000));
		p_layout.setBorder(BorderFactory.createLineBorder(Color.black));
		p_menubar.setBorder(BorderFactory.createLineBorder(Color.black));
		p_leftuser.setBorder(BorderFactory.createLineBorder(Color.black));
		p_rightuser.setBorder(BorderFactory.createLineBorder(Color.black));
		p_user1.setBorder(BorderFactory.createLineBorder(Color.black));
		p_user2.setBorder(BorderFactory.createLineBorder(Color.black));
		p_user3.setBorder(BorderFactory.createLineBorder(Color.black));
		p_user4.setBorder(BorderFactory.createLineBorder(Color.black));
		p_user1.setPreferredSize(new Dimension(200,200));
		p_user2.setPreferredSize(new Dimension(200,200));
		p_user3.setPreferredSize(new Dimension(200,200));
		p_user4.setPreferredSize(new Dimension(200,200));
		p_center.setBorder(BorderFactory.createLineBorder(Color.black));
		p_chat.setBorder(BorderFactory.createLineBorder(Color.black));
		p_chatlog.setBorder(BorderFactory.createLineBorder(Color.black));
		p_paintCanvas.setBorder(BorderFactory.createLineBorder(Color.black));
		p_answerWord.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		
		
		//버튼 설정
		btn_send.addActionListener(this);
		btn_logout.addActionListener(this);
	}
	
	public void display() {
		setVisible(true);
		setLayout(new FlowLayout());
		setTitle("놓지마! 정신줄!!");
		setSize(1500,1500);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		add(p_user1);
//		add(p_user2);
//		add(p_user3);
//		add(p_user4);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//보내기 버튼
		if(e.getSource() == btn_send)
		{
			String chat = "";
			chat = tf_msg.getText();
			
			//널값이 아니면
			if(!chat.equals(""))
			{
				client.send_msg(chat);
				//ta_chatting.append(chat + "\n");
				//tf_msg.setText("");
			}
			else if(chat.equals("")) {
				
			}
		}
		
		//로그아웃 버튼
		if(e.getSource() == btn_logout)
		{
			dispose();
			setVisible(false);
		}
		
		
	}
}