package gui.play;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
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
import javax.swing.border.LineBorder;



public class Draw extends JFrame implements ActionListener, MouseMotionListener, MouseListener{
	public boolean check = true;
	boolean first = true;
	int f_x =0 ;
	int f_y = 0;
	public static void main(String[] args) {
		Draw draw = new Draw();
		draw.display();
	}
	
	JPanel p_layout, p_drawCanvas, p_btnBar, p_colorBar, p_sizeBar;
	JButton btn_clear, btn_Black, btn_Red, btn_Green, btn_Blue, btn_Yellow, btn_White, btn_Thin, btn_Normal, btn_Thick;
	MakeCanvas makeCanvas;

	Draw() {

		p_drawCanvas = new JPanel(new FlowLayout());
		p_btnBar = new JPanel(new GridLayout(2, 1));
		p_colorBar = new JPanel();
		p_sizeBar = new JPanel();
		

		p_drawCanvas.setBorder(new LineBorder(Color.black));
		p_btnBar.setBorder(new LineBorder(Color.BLACK));
		p_colorBar.setBorder(new LineBorder(Color.black));
		p_sizeBar.setBorder(new LineBorder(Color.black));
		
		btn_Red = new JButton("빨간색");
		btn_Green = new JButton("초록색");
		btn_Blue = new JButton("파란색");
		btn_Yellow = new JButton("노란색");
		btn_Black = new JButton("검정색");
		btn_White = new JButton("지우개");
		btn_clear = new JButton("초기화");
		
		btn_Thin = new JButton("얇게");
		btn_Normal = new JButton("중간");
		btn_Thick = new JButton("두껍게");
		
		makeCanvas = new MakeCanvas();
		makeCanvas.setVariable(check, f_x, f_y);
		
		makeCanvas.addMouseMotionListener(this);
		makeCanvas.addMouseListener(this);
		
		makeCanvas.setSize(500,500);
		makeCanvas.setVisible(true);
		makeCanvas.setBackground(Color.WHITE);
		
		p_drawCanvas.add(makeCanvas);
		
		p_btnBar.add(p_colorBar);
		p_btnBar.add(p_sizeBar);
		
		p_colorBar.add(btn_Red);
		p_colorBar.add(btn_Green);
		p_colorBar.add(btn_Blue);
		p_colorBar.add(btn_Yellow);
		p_colorBar.add(btn_Black);
		p_colorBar.add(btn_White);
		p_colorBar.add(btn_clear);
		
		p_sizeBar.add(btn_Thin);
		p_sizeBar.add(btn_Normal);
		p_sizeBar.add(btn_Thick);
		
		add(p_drawCanvas,BorderLayout.CENTER);
		add(p_btnBar, BorderLayout.SOUTH);
		
		
		btn_Red.addActionListener(this);
		btn_Green.addActionListener(this);
		btn_Blue.addActionListener(this);
		btn_Yellow.addActionListener(this);
		btn_Black.addActionListener(this);
		btn_White.addActionListener(this);
		btn_clear.addActionListener(this);
		
		btn_Thin.addActionListener(this);
		btn_Normal.addActionListener(this);
		btn_Thick.addActionListener(this);
		
	}
	public void display() {
		setSize(700, 700);
		setVisible(true);
		setLayout(new BorderLayout());
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
		
		//초기화
		if(e.getSource() == btn_clear) {
			g.clearRect(0, 0, 600, 600);
		}
		
		//색상 바꾸기
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
		
		//굵기 바꾸기
		if(e.getSource() == btn_Thin) {
			makeCanvas.pen_size = 1;
		}
		if(e.getSource() == btn_Normal) {
			makeCanvas.pen_size = 3;
		}
		if(e.getSource() == btn_Thick) {
			makeCanvas.pen_size = 5;
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) { 
		
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
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
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