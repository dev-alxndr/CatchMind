package gui.play;

import java.awt.Canvas;
import java.awt.Color;
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

	public static void main(String[] args) {
		Draw draw = new Draw();
	}
	
	JPanel p_drawCanvas, p_btnbar;
	JButton btn_clear;
	NewCanvas newCanvas;
	
	public Draw() {
		p_drawCanvas = new JPanel();
		p_btnbar = new JPanel();
		btn_clear = new JButton("지우기");
		btn_clear.addActionListener(this);
		
		newCanvas = new NewCanvas();
		newCanvas.setVisible(true);
		newCanvas.setBackground(Color.WHITE);
		
		
		p_drawCanvas.add(newCanvas);
		p_btnbar.add(btn_clear);
		
		add(p_drawCanvas);
		add(p_btnbar);
		
		setSize(500, 500);
		setVisible(true);
		setLayout(new GridLayout(2, 1));
		//라벨에 layout종류 넣고 캔버스 만들기 (메인에서 되면 붙이기)
		
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

class NewCanvas extends Canvas {
	int x = 750, y = 750;
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(x, y, 2, 2);
	}
	
	public void update(Graphics g) {
		paint(g);
	}
}