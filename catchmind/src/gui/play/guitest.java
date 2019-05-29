package gui.play;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollBar;

public class guitest extends JFrame {

	private JPanel p_border;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guitest frame = new guitest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public guitest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 1000);
		setResizable(false);
		setLocationRelativeTo(null);
		p_border = new JPanel();
		p_border.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(p_border);
		p_border.setLayout(new BorderLayout(0, 0));
		
		JPanel p_east = new JPanel();
		p_east.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_east.setPreferredSize(new Dimension(100, 100));
		p_border.add(p_east, BorderLayout.EAST);
		p_east.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel p_user3 = new JPanel();
		p_user3.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_east.add(p_user3);
		
		JLabel lb_user3 = new JLabel("user3");
		p_user3.add(lb_user3);
		
		JPanel p_user4 = new JPanel();
		p_user4.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_east.add(p_user4);
		
		JLabel lb_user4 = new JLabel("user4");
		p_user4.add(lb_user4);
		
		JPanel p_west = new JPanel();
		p_west.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_west.setPreferredSize(new Dimension(100, 100));
		p_border.add(p_west, BorderLayout.WEST);
		p_west.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel p_user1 = new JPanel();
		p_user1.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_west.add(p_user1);
		
		JLabel lb_user1 = new JLabel("user1");
		p_user1.add(lb_user1);
		
		JPanel p_user2 = new JPanel();
		p_user2.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_west.add(p_user2);
		
		JLabel lb_user2 = new JLabel("user2");
		p_user2.add(lb_user2);
		
		JPanel p_center = new JPanel();
		p_center.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_center.setPreferredSize(new Dimension(100, 100));
		p_border.add(p_center, BorderLayout.CENTER);
		p_center.setLayout(new BorderLayout(0, 0));
		
		JPanel p_word = new JPanel();
		p_word.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_center.add(p_word, BorderLayout.NORTH);
		p_word.setLayout(new BorderLayout(0, 0));
		
		JLabel lb_answer = new JLabel("정답 : ");
		p_word.add(lb_answer, BorderLayout.NORTH);
		
		JPanel p_drawCanvas = new JPanel();
		p_drawCanvas.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_drawCanvas.setForeground(Color.BLACK);
		p_center.add(p_drawCanvas, BorderLayout.CENTER);
		p_drawCanvas.setBackground(Color.PINK);
		p_drawCanvas.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel p_chat = new JPanel();
		p_chat.setPreferredSize(new Dimension(10, 200));
		p_chat.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_center.add(p_chat, BorderLayout.SOUTH);
		p_chat.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel p_chatlog = new JPanel();
		p_chatlog.setPreferredSize(new Dimension(750, 150));
		p_chatlog.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_chatlog.setBackground(Color.ORANGE);
		p_chat.add(p_chatlog);
		
		JTextArea ta_chatlog = new JTextArea();
		ta_chatlog.setPreferredSize(new Dimension(4, 140));
		ta_chatlog.setColumns(102);
		p_chatlog.add(ta_chatlog);
		
		JPanel p_chatsend = new JPanel();
		p_chatsend.setPreferredSize(new Dimension(750, 35));
		p_chatsend.setBackground(Color.BLUE);
		p_chatsend.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_chat.add(p_chatsend);
		
		textField = new JTextField();
		p_chatsend.add(textField);
		textField.setColumns(55);
		
		JButton btnNewButton_1 = new JButton("보내기");
		p_chatsend.add(btnNewButton_1);
		
		JPanel p_south = new JPanel();
		p_south.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_south.setPreferredSize(new Dimension(100, 100));
		p_border.add(p_south, BorderLayout.SOUTH);
		p_south.setLayout(new BorderLayout(0, 0));
		
		JPanel p_north = new JPanel();
		p_north.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_north.setPreferredSize(new Dimension(100, 100));
		p_border.add(p_north, BorderLayout.NORTH);
		p_north.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("로그아웃");
		p_north.add(btnNewButton);
	}
}
