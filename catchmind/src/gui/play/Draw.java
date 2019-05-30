package gui.play;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	JButton btn_clear, btn_Black, btn_Red, btn_Green, btn_Blue, btn_Yellow, btn_White;
	MakeCanvas makeCanvas;

	Draw() {
		p_layout = new JPanel(new GridLayout(2,1));
		p_drawCanvas = new JPanel();
		p_btnbar = new JPanel();
		
		btn_Red = new JButton("빨간색");
		btn_Green = new JButton("초록색");
		btn_Blue = new JButton("파란색");
		btn_Yellow = new JButton("노란색");
		btn_Black = new JButton("검정색");
		btn_White = new JButton("지우개");
		
		btn_clear = new JButton("초기화");
		
		
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
		p_btnbar.add(btn_Red);
		p_btnbar.add(btn_Green);
		p_btnbar.add(btn_Blue);
		p_btnbar.add(btn_Yellow);
		p_btnbar.add(btn_Black);
		p_btnbar.add(btn_White);
		p_btnbar.add(btn_clear);
		
		btn_Red.addActionListener(this);
		btn_Green.addActionListener(this);
		btn_Blue.addActionListener(this);
		btn_Yellow.addActionListener(this);
		btn_Black.addActionListener(this);
		btn_White.addActionListener(this);
		btn_clear.addActionListener(this);
		
		add(p_layout);
		
		//라벨에 layout종류 넣고 캔버스 만들기 (메인에서 되면 붙이기)
	}
	public void display() {
		setSize(700, 700);
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
		Graphics g = makeCanvas.getGraphics();
		Graphics2D g2d = (Graphics2D)g;
				
		if(e.getSource() == btn_clear) {
			g.clearRect(0, 0, 600, 600);
		}
		
		if(e.getSource() == btn_Red) {
			makeCanvas.sc = Color.RED;
		}
		if(e.getSource() == btn_Green) {
			makeCanvas.sc = Color.GREEN;
		}
		if(e.getSource() == btn_Blue) {
			makeCanvas.sc = Color.BLUE;
		}
		if(e.getSource() == btn_Yellow) {
			makeCanvas.sc = Color.YELLOW;
		}
		if(e.getSource() == btn_Black) {
			makeCanvas.sc = Color.BLACK;
		}
		if(e.getSource() == btn_White) {
			makeCanvas.sc = Color.WHITE;
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
	int pre_x = x, pre_y = y;
	boolean check = true;
	Color sc = Color.black;
	int pen_size = 3;
	
	public MakeCanvas() {
		
	}
	
	public void setVariable(boolean check, int f_x, int f_y) {
		this.check = check;
		this.x = f_x;
		this.y = f_y;
		this.sc = sc;
	}
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(pen_size));	//선 굵기 조절
		
		g2d.setColor(sc);
		if(check) {
			g.drawLine(pre_x, pre_y, x, y);
		}else {
			pre_x = x;
			pre_y = y;
		}
		pre_x = x ;
		pre_y = y;
	}
	public void update(Graphics g)
	{
		paint(g);
	}

}