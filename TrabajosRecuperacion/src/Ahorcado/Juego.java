package Ahorcado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;






public class Juego extends JPanel implements ActionListener{
	
	List<String> palabras = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;

	private Lienzo lienzo;
	private String letras="abcdefghijklmnñopqrstuvwxyz";
	private Font font;		
	
	

	public Juego(Lienzo lienzo) throws FontFormatException, IOException {
		this.lienzo=lienzo;			
	
		BufferedReader to = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/listado-getneral.txt")));
		String linea;
		while ((linea = to.readLine()) != null)
			palabras.add(linea);
		to.close();
		Random fil = new Random();
		
		InputStream in = getClass().getResourceAsStream ("/docktrin.ttf");
		font = Font.createFont(Font.PLAIN, in).deriveFont(30f);
		in.close();		
			
		setLayout(new BorderLayout());
		
		JPanel sup = new JPanel(new GridLayout(1, 1));		
		
		JLabel lineas = new JLabel("__ ");
		JLabel lblPalabra = new JLabel("PALABRA");
		
		
		
		for (int j = 0; j < 24;j++) {
			longitudPalabras[j] = listaPalabras[j].length();
		}
		int m = 0;
		while(m < longitudPalabras[nivel]) {
			linea += "__ ";
			m++;
		}
		lineas.setText(linea);
		
		lblPalabra.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30), 
								BorderFactory.createCompoundBorder (BorderFactory.createLineBorder(Color.BLACK),
										BorderFactory.createEmptyBorder(20, 20, 20, 20))));
		lblPalabra.setHorizontalAlignment(JLabel.CENTER);		
		lineas.setHorizontalAlignment(JLabel.CENTER);	
		lblPalabra.setFont(font);	
		sup.add(lblPalabra);
		
		
		
		
		JPanel inf = new JPanel (new GridLayout(4, 7));
		inf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30), BorderFactory.createBevelBorder(BevelBorder.RAISED)));
		for(int i=0; i<letras.length();i++) {
			JButton b = new JButton(letras.substring(i, i + 1));
			b.setBackground(Color.ORANGE);
			b.setFont(font);
			inf.add(b);
		}		
		
		JButton playGame = new JButton("Jugar");	
		playGame.addActionListener(this);
		
		inf.add(playGame);		
		add(sup, BorderLayout.CENTER);		
		add(inf, BorderLayout.SOUTH); 
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}		
	
	

	
}


