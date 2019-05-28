package gui.play;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javafx.scene.layout.Border;

public class Play {

	public static void main(String[] args) {
		MakeRoom mr = new MakeRoom();
		mr.display();
	}

}
class MakeRoom extends JFrame implements ActionListener{
	//전체 패널
	JPanel p_layout;
	//유저 패널
	JPanel p_leftuser, p_rightuser;
	JPanel p_user1, p_user2, p_user3, p_user4;
	//뒤로가기 버튼,중앙패널(단어, 그림판),하단(채팅)
	JPanel p_menubar, p_center, p_paintCanvas, p_answerWord, p_chat;

	//유저 이름
	JLabel lb_userName1, lb_userName2, lb_userName3, lb_userName4;
	
//	점수 스코어 채팅시 -10점 정답시 +100
//	JLabel lb_userScore1, lb_userScore2, lb_userScore3, lb_userScore4;
//	JLabel lb_scoreView1, lb_scoreView2, lb_scoreView3, lb_scoreView4;
	
	JButton btn_send, btn_logout;
	JTextArea ta_chatting;
	JTextField tf_msg;

	
	//삭제할 버튼(자리 확인용)
	JButton btn1 = new JButton("확인");
	MakeRoom(){
		p_layout = new JPanel(new BorderLayout());
		//위쪽
		p_menubar = new JPanel();
		//중앙
		p_center = new JPanel();
			p_answerWord = new JPanel();
			p_paintCanvas = new JPanel();
		p_answerWord = new JPanel();
		p_paintCanvas = new JPanel();
		//왼쪽
		p_leftuser = new JPanel();
			p_user1 = new JPanel();
			p_user2 = new JPanel();
		//오른쪽
		p_rightuser = new JPanel();
			p_user3 = new JPanel();
			p_user4 = new JPanel();
		//하단
		p_chat = new JPanel();
		
		lb_userName1 = new JLabel("유저1");
		lb_userName2 = new JLabel("유저2");
		lb_userName3 = new JLabel("유저3");
		lb_userName4 = new JLabel("유저4");
		
		ta_chatting = new JTextArea(15,15);
		tf_msg = new JTextField(15);
		btn_logout = new JButton("로그아웃");
		btn_send = new JButton("보내기");
		
		p_layout.add(p_menubar,BorderLayout.NORTH);
		p_menubar.add(btn_logout);
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
		p_center.add(p_paintCanvas);
		p_paintCanvas.add(btn1);
		
		p_layout.add(p_chat,BorderLayout.SOUTH);
		p_chat.add(ta_chatting);
		p_chat.add(tf_msg);
		p_chat.add(btn_send);
		
//		add(lb_userName1);
//		add(lb_userName2);
//		add(lb_userName3);
//		add(lb_userName4);
//		add(btn_logout);
//		add(btn_send);
//		add(ta_chatting);
//		add(tf_msg);
		
		//버튼 설정
		btn_send.addActionListener(this);
		btn_logout.addActionListener(this);
	}
	
	void display() {
		setVisible(true);
		setLayout(new FlowLayout());
		setTitle("놓지마! 정신줄!!");
		setSize(600,600);
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
				ta_chatting.append(chat + "\n");
				tf_msg.setText("");
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