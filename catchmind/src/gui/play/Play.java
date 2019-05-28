package gui.play;

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
	//유저 패널
	JPanel p_player1, p_player2, p_player3, p_player4;
	
	//단어, 그림판
	JPanel p_menubar, p_paintCanvas, p_word;
	
	JLabel lb_playerName1, lb_playerName2, lb_playerName3, lb_playerName4;
	JLabel lb_playerScore1, lb_playerScore2, lb_playerScore3, lb_playerScore4;
	JLabel lb_scoreView1, lb_scoreView2, lb_scoreView3, lb_scoreView4;
	JButton btn_send, btn_back;
	JTextArea ta_chatting;
	JTextField tf_msg;

	MakeRoom(){
//		p_menubar = new JPanel(new FlowLayout());
//		
		p_player1 = new JPanel(new FlowLayout());
		p_player2 = new JPanel(new FlowLayout());
		p_player3 = new JPanel(new FlowLayout());
		p_player4 = new JPanel(new FlowLayout());
		
		
		lb_playerName1 = new JLabel("lb_playerName1");
		lb_playerName2 = new JLabel("lb_playerName2");
		lb_playerName3 = new JLabel("lb_playerName3");
		lb_playerName4 = new JLabel("lb_playerName4");
		
		ta_chatting = new JTextArea(20,20);
		tf_msg = new JTextField(15);
		
		btn_send = new JButton("보내기");
		
		p_player1.add(lb_playerName1);
		p_player2.add(lb_playerName2);
		p_player3.add(lb_playerName3);
		p_player4.add(lb_playerName4);
		
		p_player1.add(ta_chatting);
		p_player1.add(tf_msg);
		p_player1.add(btn_send);
		
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
		
		add(p_player1);
		add(p_player2);
		add(p_player3);
		add(p_player4);
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