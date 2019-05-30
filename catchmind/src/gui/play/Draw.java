package gui.play;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Draw extends JFrame implements ActionListener, MouseMotionListener, MouseListener{
	public boolean check = true;
	boolean first = true;
	int f_x =0 ;
	int f_y = 0;
	public static void main(String[] args) {
		Draw draw = new Draw();
		draw.display();
	}
	
	JPanel p_layout, p_drawCanvas, p_btnbar;
	JButton btn_clear;
	MakeCanvas makeCanvas;

	
	Draw() {
		p_layout = new JPanel();
		p_drawCanvas = new JPanel();
		p_btnbar = new JPanel();
		btn_clear = new JButton("지우기");
		btn_clear.addActionListener(this);
		makeCanvas = new MakeCanvas();
		makeCanvas.setVariable(check, f_x, f_y);
		makeCanvas.addMouseMotionListener(this);
		makeCanvas.addMouseListener(this);
		
		
		makeCanvas.setSize(500,500);
		makeCanvas.setVisible(true);
		makeCanvas.setBackground(Color.WHITE);
		
		p_layout.add(p_drawCanvas);
		p_layout.add(p_btnbar);
		
		p_drawCanvas.add(makeCanvas);
		p_btnbar.add(btn_clear);
		add(p_layout);
		
		
		//라벨에 layout종류 넣고 캔버스 만들기 (메인에서 되면 붙이기)
	}
	public void display() {
		setSize(500, 500);
		setVisible(true);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		makeCanvas.x = e.getX();
		makeCanvas.y = e.getY();
		System.out.println(makeCanvas.x + "/ " + makeCanvas.y);
		makeCanvas.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		f_x = e.getX();
		f_y = e.getY();
		if(!check) {
			makeCanvas.pre_x = e.getX();
			makeCanvas.pre_y = e.getY();
		}
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_clear) {
			Graphics g = makeCanvas.getGraphics();
			g.clearRect(0, 0, 600, 600);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		check = true;
		if(first) {
			first = false;
			makeCanvas.pre_x = e.getX();
			makeCanvas.pre_y = e.getY();
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		check = false;
		System.out.println(check);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class MakeCanvas extends Canvas	//스케치북
{
	int x= 0, y = 0;
	int pre_x = x, pre_y =y;
	boolean check = true;
	public MakeCanvas() {
		
	}
	public void setVariable(boolean check, int f_x, int f_y) {
		this.check = check;
		this.x = f_x;
		this.y = f_y;
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.black);	//연필 색
//		g.set
		//g.fillRect(x, y, 1, 1);	//채워진 동그라미
		if(check) {
			g.drawLine(pre_x, pre_y, x, y);
			
		}else {
			pre_x = x;
			pre_y = y;
		}
//		g.drawString("재미있...", 50, 200);
		pre_x = x ;
		pre_y = y;
//		g.drawRect(x, y, 30, 40);
	}
	public void update(Graphics g)
	{
		paint(g);
	}
}