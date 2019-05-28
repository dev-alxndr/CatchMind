package gui.play;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Play {

	public static void main(String[] args) {
		MakeRoom mr = new MakeRoom();
		mr.display();
	}

}
class MakeRoom extends JFrame implements ActionListener{
	//전체 패널
	JPanel p_layout = new JPanel(new BorderLayout(5,5));
	
	//유저 패널
	JPanel p_user1, p_user2, p_user3, p_user4;
	JPanel p_leftuser, p_rightuser;
	
	//뒤로가기 버튼, 단어, 그림판
	JPanel p_menubar, p_paintCanvas, p_word;
	
	
	//유저 이름
	JLabel lb_userName1, lb_userName2, lb_userName3, lb_userName4;
	
//	점수 스코어 채팅시 -10점 정답시 +100
//	JLabel lb_userScore1, lb_userScore2, lb_userScore3, lb_userScore4;
//	JLabel lb_scoreView1, lb_scoreView2, lb_scoreView3, lb_scoreView4;
	
	JButton btn_send, btn_logout;
	JTextArea ta_chatting;
	JTextField tf_msg;

	MakeRoom(){
		p_menubar = new JPanel(new FlowLayout());
		
		p_user1 = new JPanel(new FlowLayout());
		p_user2 = new JPanel(new FlowLayout());
		p_user3 = new JPanel(new FlowLayout());
		p_user4 = new JPanel(new FlowLayout());
		
		
		lb_userName1 = new JLabel("lb_userName1");
		lb_userName2 = new JLabel("lb_userName2");
		lb_userName3 = new JLabel("lb_userName3");
		lb_userName4 = new JLabel("lb_userName4");
		
		ta_chatting = new JTextArea(20,20);
		tf_msg = new JTextField(15);
		
		btn_send = new JButton("보내기");
		
//		p_layout.add();
		p_user1.add(lb_userName1);
		p_user2.add(lb_userName2);
		p_user3.add(lb_userName3);
		p_user4.add(lb_userName4);
		
		p_user1.add(ta_chatting);
		p_user1.add(tf_msg);
		p_user1.add(btn_send);
		
		btn_send.addActionListener(this);
	}
	
	void display() {
		setVisible(true);
		setLayout(new FlowLayout());
		setTitle("놓지마! 정신줄!!");
		setSize(600,600);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(p_user1);
		add(p_user2);
		add(p_user3);
		add(p_user4);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String chat = "";
		if(e.getSource() == btn_send)
		{
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
	}
}