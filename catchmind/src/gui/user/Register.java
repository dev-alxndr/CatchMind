package gui.user;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import example.chat.Client;

public class Register extends JFrame implements ActionListener{
//	public static void main(String[] args) {
//		register1 register = new register1();
//		register.display();
//	}
	JPanel ptitle, pid, pps, pnick, pbtn;
	JLabel lbtitle, lbid, lbpw, lbnick;
	JButton btnback, btnregister;
	JTextField tfid, tfpw, tfnick;
	String reg_id, reg_pw, reg_nick;
	String err="";
	Client client;
	Login login;
	public int chk = 0;
	
	Register(Client client, Login login) {
		this.client = client;
		this.login = login;
		//객체생성
		ptitle = new JPanel(new FlowLayout());
		pid = new JPanel(new FlowLayout());
		pps = new JPanel(new FlowLayout());
		pnick = new JPanel(new FlowLayout());
		pbtn = new JPanel(new FlowLayout());
		
		lbtitle = new JLabel("회원가입 화면 입니다.");
		lbid = new JLabel("ID");
		lbpw = new JLabel("PW");
		lbnick = new JLabel("Nickname");
		
		tfid = new JTextField(15);
		tfpw = new JTextField(15);
		tfnick = new JTextField(15);
		
		btnregister = new JButton("가입완료");
		btnback = new JButton("돌아가기");
		
		//버튼
		btnregister.addActionListener(this);	//가입완료
		btnback.addActionListener(this);		//돌아가기
		
		//Panel에 추가
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
		setSize(300,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		add(ptitle);
		add(pid);
		add(pps);
		add(pnick);
		add(pbtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//회원가입 완료 버튼을 누를 시
		if(e.getSource() == btnregister) {
			System.out.println("[register]회원가입완료 버튼");
			
			//DB는 배우면 적용, 아이디, 패스워드, 닉네임 저장
			reg_id = tfid.getText();
			reg_pw = tfpw.getText();
			reg_nick = tfnick.getText();
			System.out.println(reg_id +","+ reg_pw +","+ reg_nick);
			
			Notice notice = new Notice();
			
			//아이디, 패스워드, 닉네임 값 확인
			if(reg_id.equals("") || reg_pw.equals("") || reg_nick.equals("")){
				notice.text("빈칸을 반드시 입력해 주세요");
				notice.display("로그인 실패");
			}
			
			//회원가입 성공시
			if(!reg_id.equals("") && !reg_pw.equals("") && !reg_nick.equals("")) {
				client.do_signUp(reg_id, reg_pw, reg_nick);
				
				if(chk == 1) {
					notice.text("회원가입에 성공하셨습니다!!");
					notice.display("회원가입 안내");
					dispose();
				}else {
					System.out.println(chk);
					notice.text("회원가입에 실패했습니다!!");
					notice.display("회원가입 안내");
					dispose();
				}
			}
		}
		
		//돌아가기 버튼을 누를 시
		if(e.getSource() == btnback) {
			System.out.println("[register]돌아가기 버튼");
			login.tfid.setText("");
			login.tfpw.setText("");
			login.setVisible(true);
			dispose();
		}
	}
}

