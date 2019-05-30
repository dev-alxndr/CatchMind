package gui.play;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Draw extends JFrame implements ActionListener, MouseMotionListener{
	JPanel p_layout, p_drawCanvas, p_btnbar;
	JButton btn_clear;
	MakeCanvas makeCanvas;

	public static void main(String[] args) {
		Draw draw = new Draw();
		draw.display();
	}
	
	
	Draw() {
		p_layout = new JPanel(new FlowLayout());
		p_drawCanvas = new JPanel();
		p_btnbar = new JPanel();
		btn_clear = new JButton("지우기");
		btn_clear.addActionListener(this);
		makeCanvas = new MakeCanvas();
		
		makeCanvas.setVisible(true);
		makeCanvas.setBackground(Color.WHITE);
		
		p_layout.add(p_drawCanvas);
		p_layout.add(p_btnbar);
		
		p_drawCanvas.add(makeCanvas);
		p_btnbar.add(btn_clear);
		


		//라벨에 layout종류 넣고 캔버스 만들기 (메인에서 되면 붙이기)
		
	}
	public void display() {
		setSize(500, 500);
		setVisible(true);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(p_layout);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class MakeCanvas extends Canvas {
	int x = 100, y = 100;
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(x, y, 2, 2);
	}
	
	public void update(Graphics g) {
		paint(g);
	}
}