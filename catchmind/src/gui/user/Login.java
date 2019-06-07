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

public class Login extends JFrame implements ActionListener {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	public JPanel ptitle, pid, pps, pbtn;
	public JLabel lbtitle, lbid, lbpw;
	public JButton btnlogin, btnregister;
	public JTextField tfid, tfpw;
	public String id, pw;
	public Client client;

	public Login() {
		//this.client = client;
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
		add(ptitle);
		add(pid);
		add(pps);
		add(pbtn);
	}

	public void display() {
		setVisible(true);
		setLayout(new FlowLayout());
		setTitle("놓지마! 정신줄!!");
		setSize(300, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 로그인 버튼을 눌렀을 시
		if (e.getSource() == btnlogin) {
			
			id = tfid.getText();
			pw = tfpw.getText();

			// 아이디, 비밀번호 값이 없으면
			if (id.equals("") || pw.equals("")) {
				Notice notice = new Notice();
				notice.text("아이디 및 비밀번호를 써주세요");
				notice.display("로그인 실패");
			}else {
				client.do_login(id,pw);
			}
		}

		// 회원가입 버튼을 눌렀을 시
		if (e.getSource() == btnregister) {
			
			// 회원가입 화면으로 이동
			Register reg = new Register(client, this);
			client.setRegister(reg);
			reg.display();
			this.setVisible(false);
		}
	}
	public static void main(String[] args) {
		Login login = new Login();
		Client client = new Client(login);
		client.setClient(client);
		
		
	}
}

