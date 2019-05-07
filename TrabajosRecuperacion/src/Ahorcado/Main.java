package Ahorcado;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Ejercicio 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Lienzo lienzo = new Lienzo(500, 600);
		frame.getContentPane().add(lienzo);
		frame.pack();
		frame.setLocationRelativeTo(null);
		SwingUtilities.invokeLater(() -> {frame.setVisible(true);});
	}
}
