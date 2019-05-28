package gui.user;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login {
	public static void main(String[] args) {
		login1 login = new login1();
		login.display();
	}
}

class login1 extends JFrame implements ActionListener{
	
	
	JPanel ptitle, pid, pps, pbtn;
	JLabel lbtitle, lbid, lbpw;
	JButton btnlogin, btnregister;
	JTextField tfid, tfpw;
	String id, pw;
	
	login1() {
		//객체생성
		ptitle = new JPanel(new FlowLayout());
		pid = new JPanel(new FlowLayout());
		pps = new JPanel(new FlowLayout());
		pbtn = new JPanel(new FlowLayout());
		
		lbtitle = new JLabel("로그인을 해주세요");
		lbid = new JLabel("ID");
		lbpw = new JLabel("PW");
		
		tfid = new JTextField(15);
		tfpw = new JTextField(15);
		
		btnlogin = new JButton("로그인");
		btnregister = new JButton("회원가입");
		
		//버튼
		btnlogin.addActionListener(this);		//로그인
		btnregister.addActionListener(this);	//회원가입
		
		//Panel에 추가
		ptitle.add(lbtitle);
		
		pid.add(lbid);
		pid.add(tfid);
		
		pps.add(lbpw);
		pps.add(tfpw);
		
		pbtn.add(btnlogin);
		pbtn.add(btnregister);
		
//		tfid.setText("");
//		tfpw.setText("");
	}
	
	void display() {
		setVisible(true);
		setLayout(new FlowLayout());
		setTitle("놓지마! 정신줄!!");
		setSize(300,400);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(ptitle);
		add(pid);
		add(pps);
		add(pbtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//로그인 버튼을 눌렀을 시
		if(e.getSource() == btnlogin) {
			System.out.println("[login]로그인 버튼이에요");
			id = tfid.getText();
			pw = tfpw.getText();
			
			//아이디, 비밀번호 값이 없으면
			if(id.equals("") || pw.equals("")) {
				Notice notice = new Notice();
				notice.text("아이디 및 비밀번호를 써주세요");
				notice.display("로그인 실패");
			}
		}

		//회원가입 버튼을 눌렀을 시
		if(e.getSource() == btnregister) {
			System.out.println("[login]회원가입 하러 갈게요");
			
			//회원가입 화면으로 이동
			register1 reg = new register1();
			reg.display();
		}
	}
}
