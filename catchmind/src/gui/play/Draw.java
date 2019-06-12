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

import example.chat.Client;





public class Draw extends JPanel implements ActionListener, MouseMotionListener, MouseListener{
	public boolean check = true;
	boolean first = true;
	private Client client;
	public Graphics graphic;
	public int pen_size = 3;
	public int r,g,b = 0;
	
	
//	public static void main(String[] args) {
//		Draw draw = new Draw();
//		draw.display();
//	}
//	
	JPanel p_layout, p_drawCanvas, p_btnBar, p_colorBar, p_sizeBar;
	JButton btn_clear, btn_Black, btn_Red, btn_Green, btn_Blue, btn_Yellow, btn_White, btn_Thin, btn_Normal, btn_Thick;
	MakeCanvas makeCanvas;

	Draw(Client client) {
		this.client = client;
		
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
		//makeCanvas.setVariable(check, f_x, f_y);
		
		client.set_MakeCanvas(makeCanvas);
		
		
		makeCanvas.addMouseMotionListener(this);
		makeCanvas.addMouseListener(this);
		
		
		makeCanvas.setSize(780,550);
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
//		setSize(700, 700);
		setVisible(true);
		setLayout(new BorderLayout());
//		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}


	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String colors = "";
		String penSize;
		graphic = makeCanvas.getGraphics();
		Graphics2D g2d = (Graphics2D)graphic;
		
		//초기화
		if(e.getSource() == btn_clear) {
			graphic.clearRect(0, 0, 900, 900);
			client.canvas_Clear();
		}
		
		//색상 바꾸기
		if(e.getSource() == btn_Red) {
			r = 255;
			g = 0;
			b = 0;
		}
		if(e.getSource() == btn_Green) {
			r = 0;
			g = 255;
			b = 0;
		}
		if(e.getSource() == btn_Blue) {
			r = 0;
			g = 0;
			b = 255;
		}
		if(e.getSource() == btn_Yellow) {
			r = 239;
			g = 234;
			b = 16;
		}
		if(e.getSource() == btn_Black) {
			r = 0;
			g = 0;
			b = 0;
		}
		if(e.getSource() == btn_White) {
			r = 255;
			g = 255;
			b = 255;
		}
		makeCanvas.color = new Color(r,g,b);
		colors = ""+r+"*"+g+"*"+b;
		client.set_Color(colors);
		
		//굵기 바꾸기
		if(e.getSource() == btn_Thin) {
			pen_size = 3;
		}
		if(e.getSource() == btn_Normal) {
			pen_size = 5;
		}
		if(e.getSource() == btn_Thick) {
			pen_size = 10;
		}
		
		client.set_penSize(pen_size);	// 메소드가 필요함. 하지만 귀찮
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		makeCanvas.x = e.getX();
		makeCanvas.y = e.getY();
		makeCanvas.repaint();	
		client.user_draw(e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(!check) {
			System.out.println(!check);
			makeCanvas.pre_x = e.getX();
			makeCanvas.pre_y = e.getY();
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
			System.out.println("pre_x = "+e.getX()+"/pre_y = "+e.getY());
			client.set_FirstXY(e.getX(), e.getY());
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		check = false;
		int r_x = e.getX();
		int r_y = e.getY();
		
	
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}

