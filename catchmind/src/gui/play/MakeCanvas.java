package gui.play;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import example.chat.Client;

public class MakeCanvas extends Canvas	//스케치북
{
	public int x= 0, y = 0;
	public int pre_x = x, pre_y = y;
	boolean check = true;
	public Color color = Color.black;
	public int pen_size = 3;
	Client client;

	public MakeCanvas() {
		
	}
	
	public void paint(Graphics g)
	{

		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(pen_size));	//선 굵기 조절
		
		g2d.setColor(color);

		g.drawLine(pre_x, pre_y, x, y);
		pre_x = x;
		pre_y = y;
	}
	public void update(Graphics g)
	{
		paint(g);
	}

}
