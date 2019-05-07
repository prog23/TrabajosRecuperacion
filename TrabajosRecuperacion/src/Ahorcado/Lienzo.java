package Ahorcado;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class Lienzo extends JPanel{

private static final long serialVersionUID = 1L;
	
	private Shape[] vector; //Declarar vector de objetos
	int fallos = 5;
	
	public Lienzo(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		vector = new Shape[11];
		vector[0] = new Line2D.Float(470, 470, 30, 470);
		vector[1] = new Line2D.Float(30, 470, 30, 30);
		vector[2] = new Line2D.Float(30, 30, 350, 30);
		vector[3] = new Line2D.Float(150, 30, 30, 150);
		vector[4] = new Line2D.Float(350, 30, 350, 120);
		vector[5] = 	new Arc2D.Float(320, 120, 61, 61, 0, 360, Arc2D.OPEN);
		vector[6] = new Line2D.Float(350, 180, 350, 290);
		vector[7] = new Line2D.Float(350, 200, 300, 250);
		vector[8] = new Line2D.Float(350, 200, 400, 250);
		vector[9] = new Line2D.Float(350, 290, 300, 390);
		vector[10] = new Line2D.Float(350, 290, 400, 390);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);		
		for (int i=0; i<fallos;i++)
			((Graphics2D) g).draw(vector[i]);
        }
      
        
	}
		
	