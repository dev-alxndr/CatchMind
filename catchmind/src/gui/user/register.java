package gui.user;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class register extends JFrame{
	public static void main(String[] args) {
		register1 register = new register1();
		register.display();
	}
}


class register1 extends JFrame{
	JPanel ptitle, pid, pps, pnick, pbtn;
	JLabel lbtitle, lbid, lbpw, lbnick;
	JButton btnback, btnregister;
	JTextField tfid, tfpw, tfnick;
	
	register1() {
		ptitle = new JPanel(new FlowLayout());
		pid = new JPanel(new FlowLayout());
		pps = new JPanel(new FlowLayout());
		pnick = new JPanel(new FlowLayout());
		pbtn = new JPanel(new FlowLayout());
		
		lbtitle = new JLabel("회원가입 화면 입니다.");
		lbid = new JLabel("ID");
		lbpw = new JLabel("PW");
		lbnick = new JLabel("NickName");
		
		tfid = new JTextField(20);
		tfpw = new JTextField(20);
		tfnick = new JTextField(20);
		
		
		btnback = new JButton("돌아가기");
		btnregister = new JButton("회원가입");
		
		ptitle.add(lbtitle);
		
		pid.add(lbid);
		pid.add(tfid);
		
		pps.add(lbpw);
		pps.add(tfpw);
		
		pnick.add(lbnick);
		pnick.add(tfnick);
		
		pbtn.add(btnregister);
		pbtn.add(btnback);
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
		add(pbtn);
	}
}
