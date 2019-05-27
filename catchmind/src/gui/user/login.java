package gui.user;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class login {
	public static void main(String[] args) {
		login1 login = new login1();
		login.display();
	}
}

class login1 extends JFrame{
	
	
	JPanel ptitle, pid, pps;
	JLabel lbtitle, lbid, lbpw;
	JButton btnlogin, btnregister;
	JTextField tfid, tfpw;
	
	login1() {
		ptitle = new JPanel(new FlowLayout());
		pid = new JPanel(new FlowLayout());
		pps = new JPanel(new FlowLayout());
		lbtitle = new JLabel("로그인을 해주세요");
		lbid = new JLabel("ID");
		lbpw = new JLabel("PW");
		tfid = new JTextField(20);
		tfpw = new JTextField(20);
		btnlogin = new JButton("로그인");
		btnregister = new JButton("회원가입");
		
		ptitle.add(lbtitle);
		pid.add(lbid);
		pid.add(tfid);
		
		pps.add(lbpw);
		pps.add(tfpw);
	}
	void display() {
		setVisible(true);
		setLayout(new FlowLayout());
		setTitle("놓지마! 정신줄!!");
		setSize(500,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(ptitle);
		add(pid);
		add(pps);
	}
}
