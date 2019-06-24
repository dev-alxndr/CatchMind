package gui.play;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import example.chat.Client;
import gui.user.Login;


public class MakeRoom2 extends JFrame implements ActionListener, KeyListener{

	
	Client client;
	MakeCanvas mc;
	Draw draw;
	
	public JPanel p_border, p_east, p_west, p_south, p_north, p_center, p_user1, p_user2, p_user3, p_user4;
	public JPanel p_word, p_drawCanvas, p_chat, p_chatlog, p_chatsend, p_btnbar;
	public JPanel p_user1_grid;
	public JLabel lb_user1, lb_user2, lb_user3, lb_user4, lb_answer, lb_answerWord;
	public JLabel lb_score1, lb_score2, lb_score3, lb_score4;
	public JTextField tf_msg;
	public JButton btn_start, btn_send, btn_logout;
	public JTextArea ta_chatlog;
	public JScrollPane txtScroll;
	public Font user_font, score_font;
	
	//삭제할 버튼(자리 확인용)
	JButton btn1 = new JButton("자리 확인용 버튼");
	public MakeRoom2(Client client){
		this.client = client;
		draw = new Draw(client);
		client.set_Draw(draw);

		setBounds(100, 100, 1000, 1000);

		p_border = new JPanel();
		p_border.setBorder(new LineBorder(new Color(0, 0, 0)));

		add(p_border);
		p_border.setLayout(new BorderLayout(0, 0));
		
		//동
		p_east = new JPanel();
		p_user3 = new JPanel();
		lb_user3 = new JLabel("user3");
		lb_score3 = new JLabel("0");
		
		p_user4 = new JPanel();
		lb_user4 = new JLabel("user4");
		lb_score4 = new JLabel("0");
		
		//서
		p_west = new JPanel(new BorderLayout());
		
		p_user1 = new JPanel(new GridLayout(2,1));
//		p_user1_grid = new JPanel(new GridLayout(2, 1));
		lb_user1 = new JLabel("user1");
		lb_score1 = new JLabel("0");
		
		p_user2 = new JPanel();
		lb_user2 = new JLabel("user2");
		lb_score2 = new JLabel("0");
		
		//중앙
		p_center = new JPanel();
		p_word = new JPanel(new GridLayout(1, 2));
		lb_answer = new JLabel("정답 : ");
		lb_answerWord = new JLabel("정답 단어");
		p_drawCanvas = new JPanel();
		p_chat = new JPanel();
		p_chatlog = new JPanel();
		p_chatsend = new JPanel();
		
		//북
		p_north = new JPanel();
		p_btnbar = new JPanel();
		
		ta_chatlog = new JTextArea();
		txtScroll = new JScrollPane(ta_chatlog,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		tf_msg = new JTextField();
		btn_start = new JButton("시작하기");
		btn_logout = new JButton("로그아웃");
		btn_send = new JButton("보내기");
//		draw.setSize(900,900);
		
		user_font = new Font("맑은 고딕", Font.PLAIN, 30);
//		score_font = new Font("맑은 고딕", 15);
		lb_user1.setFont(user_font);
		lb_user2.setFont(user_font);
		lb_user1.setHorizontalAlignment(SwingConstants.BOTTOM);
		lb_score1.setHorizontalAlignment(SwingConstants.TOP);
		
		lb_user3.setFont(user_font);
		lb_user4.setFont(user_font);
		
		//큰 테두리 동 서 북 중앙
		p_border.add(p_east, BorderLayout.EAST);
		p_border.add(p_west, BorderLayout.WEST);
		p_border.add(p_north, BorderLayout.NORTH);
		p_border.add(p_center, BorderLayout.CENTER);
		
		//동
		p_east.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_east.setLayout(new GridLayout(2, 1, 0, 0));
		p_east.setPreferredSize(new Dimension(100, 100));

		p_user3.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_user4.setBorder(new LineBorder(new Color(0, 0, 0)));

		p_east.add(p_user3);
			p_user3.add(lb_user3);
			p_user3.add(lb_score3);
		p_east.add(p_user4);
			p_user4.add(lb_user4);
			p_user4.add(lb_score4);
	
		//서
		p_west.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_west.setLayout(new GridLayout(2, 1, 0, 0));
		p_west.setPreferredSize(new Dimension(100, 100));
		
		p_user1.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_user2.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		p_west.add(p_user1);
//		p_user1.add(p_user1_grid,BorderLayout.CENTER);	
			p_user1.add(lb_user1);
			p_user1.add(lb_score1);
		p_west.add(p_user2);
			p_user2.add(lb_user2);
			p_user2.add(lb_score2);
		
		//북
		p_north.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_north.setLayout(new BorderLayout(0, 0));
		p_north.setPreferredSize(new Dimension(100, 50));
		p_north.add(p_btnbar, BorderLayout.EAST);

		p_btnbar.setLayout(new GridLayout(1, 2));
			p_btnbar.add(btn_start);
			p_btnbar.add(btn_logout);
		
		//중앙
		p_center.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_center.setPreferredSize(new Dimension(900, 900));
		p_center.setLayout(new BorderLayout(0, 0));
		p_center.add(p_word, BorderLayout.NORTH);
		p_center.add(p_drawCanvas, BorderLayout.CENTER);

		p_center.add(draw);
		p_center.add(p_chat, BorderLayout.SOUTH);

		p_word.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_word.setLayout(new BorderLayout(0, 0));
		p_word.add(lb_answer, BorderLayout.NORTH);
		p_word.add(lb_answerWord, BorderLayout.EAST);
			lb_answer.setPreferredSize(new Dimension(43, 30));
			
		
		p_drawCanvas.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_drawCanvas.setForeground(Color.BLACK);
		p_drawCanvas.setBackground(Color.PINK);
		p_drawCanvas.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
		p_chat.setPreferredSize(new Dimension(10, 200));
		p_chat.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_chat.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p_chat.add(p_chatlog);
		p_chat.add(p_chatsend);
		
		p_chatlog.setPreferredSize(new Dimension(750, 150));
		p_chatlog.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_chatlog.setBackground(Color.ORANGE);
		
		p_chatlog.add(txtScroll);
//			ta_chatlog.setPreferredSize(new Dimension(4, 140));
			ta_chatlog.setRows(8);
			ta_chatlog.setColumns(65);
		
		
		p_chatsend.setPreferredSize(new Dimension(750, 35));
		p_chatsend.setBackground(Color.BLUE);
		p_chatsend.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		p_chatsend.add(tf_msg);
			tf_msg.setColumns(55);
		p_chatsend.add(btn_send);
		
		btn_send.addActionListener(this);
		btn_start.addActionListener(this);
		btn_logout.addActionListener(this);
		tf_msg.addKeyListener(this);
	}
	
	public void display() {
		setVisible(true);
		setLayout(new FlowLayout());
		setTitle("놓지마! 정신줄!!");
		setSize(1200,1000);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//보내기 버튼
		if(e.getSource() == btn_send)
		{
			String chat = "";
			chat = tf_msg.getText();
			
			if(!chat.equals("")) {
//				client.send_msg(chat);
				
				tf_msg.setText("");
				client.send_msg(chat);
			}
			else if(chat.equals("")) {
				
			}
		}
		
		//로그아웃 버튼
		if(e.getSource() == btn_logout)
		{
			dispose();
			setVisible(false);
			//Login login;
		}
		
		//시작하기 버튼
		if(e.getSource() == btn_start) {
			System.out.println("시작버튼을 누름");
			client.gameStart();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			String chat = "";
			chat = tf_msg.getText();
			
			if(!chat.equals("")) {
//				client.send_msg(chat);
				
				tf_msg.setText("");
				client.send_msg(chat);
			}
			else if(chat.equals("")) {
				
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}