package Ahorcado;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Lienzo extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final float [] patron = {5, 3};
	private static final BasicStroke solido = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
	private static final BasicStroke discontinuo = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, patron, 0);
	
	private Shape[] vector;
	private int fallos = 5;
	
	
	public Lienzo(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		vector = new Shape[11];
		vector[0] = new Line2D.Float(470, 470, 30, 470);
		vector[1] = new Line2D.Float(30, 470, 30, 30);
		vector[2] = new Line2D.Float(30, 30, 350, 30);
		vector[3] = new Line2D.Float(150, 30, 30, 150);
		vector[4] = new Line2D.Float(350, 30, 350, 120);
		vector[5] = new Arc2D.Float(320, 120, 61, 61, 0, 360, Arc2D.OPEN);
		vector[6] = new Line2D.Float(350, 180, 350, 290);
		vector[7] = new Line2D.Float(350, 200, 300, 250);
		vector[8] = new Line2D.Float(350, 200, 400, 250);
		vector[9] = new Line2D.Float(350, 290, 300, 390);
		vector[10] = new Line2D.Float(350, 290, 400, 390);
	}
	
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for (int i = 0; i < vector.length; i++) {
			if(i < fallos)	{
			// trazo continuo y color negro
				g2d.setColor(Color.BLACK);
				g2d.setStroke(solido);
			}
			else {
				//trazo a puntos y color gris claro
				g2d.setColor(Color.LIGHT_GRAY);
				g2d.setStroke(discontinuo);
			}
			g2d.draw(vector[i]);
		}
	}

	public void reset() {
		fallos = 0;
		repaint();
	}
	
	
	
	public boolean incrementarFallos() {
		fallos++;
		repaint();
		return fallos == vector.length;
	}
}
