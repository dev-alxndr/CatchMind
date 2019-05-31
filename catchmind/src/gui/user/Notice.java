package gui.user;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Notice extends JFrame implements ActionListener{
	JLabel lbnotice;
	JButton btnclose;

	//실패시 라벨
	public void text(String text) {
		lbnotice = new JLabel(text);
		add(lbnotice);
	}
	
	//로그인 실패 화면
	public void display(String title) {
		btnclose = new JButton("닫기");
		btnclose.addActionListener(this);
		add(btnclose);
		
		setTitle(title);
		setVisible(true);
		setLayout(new FlowLayout());
		setSize(300,200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnclose) {
			dispose();
		}
	}
}
