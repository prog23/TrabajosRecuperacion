package Ahorcado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class Juego extends JPanel implements ActionListener{
	
	List<String> palabras = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;
	private Lienzo lienzo;
	private Color marronclaro=new Color(230,186,163);
	//private Color marronletras=new Color(84,71,64);
	private String letras="abcdefghijklmnñopqrstuvwxyz";
	private Font font;		
	private Font fontlte;
	private Font fontplay;
	JButton[] letra = new JButton[letras.length()];
	JButton playGame = new JButton("PLAY");
//	static int fallos = 0;
	static int aciertos = 0;
	//static int pos = 0;
	String linea;
	private char[] guiones;
	private char[] adivinar;
	JLabel lblPalabra = new JLabel("Palabra");
	
	
	public Juego(Lienzo lienzo) throws FontFormatException, IOException {
		this.lienzo=lienzo;			
		
		
		BufferedReader to = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/listado-general.txt")));
		
		while ((linea = to.readLine()) != null)
			palabras.add(linea);
		to.close();		
			
		
		
		InputStream in = getClass().getResourceAsStream ("/docktrin.ttf");
		InputStream lte = getClass().getResourceAsStream("/teclado.ttf");
		InputStream lpl = getClass().getResourceAsStream("/play.ttf");
		font = Font.createFont(Font.PLAIN, in).deriveFont(30f);
		fontlte = Font.createFont(Font.PLAIN, lte).deriveFont(30f);
		fontplay = Font.createFont(Font.PLAIN, lpl).deriveFont(15f);
		in.close();		
		
		setLayout(new BorderLayout());
		
		JPanel sup = new JPanel(new GridLayout(1, 1));		
		sup.setBackground(Color.WHITE);
		
		
		lblPalabra.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30), 
								BorderFactory.createCompoundBorder (BorderFactory.createLineBorder(Color.BLACK),
										BorderFactory.createEmptyBorder(20, 20, 20, 20))));
		
		lblPalabra.setHorizontalAlignment(JLabel.CENTER);		
		lblPalabra.setFont(font);		
		sup.add(lblPalabra);
		
		
		
		
		JPanel inf = new JPanel (new GridLayout(4, 7));
		inf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30), 
				BorderFactory.createBevelBorder(BevelBorder.RAISED)));		
		inf.setBackground(Color.WHITE);
		
		
		for(int i=0; i<letras.length();i++) {
			letra[i] = new JButton(letras.substring(i, i + 1));
			letra[i].setFont(fontlte);
			letra[i].setBackground(marronclaro);
			inf.add(letra[i]);
			letra[i].addActionListener(this);
			letra[i].setEnabled(false);
		}	
		
		inf.add(playGame);	
		playGame.setBackground(Color.GREEN);
		playGame.setFont(fontplay);
		playGame.addActionListener(this);
		add(sup, BorderLayout.CENTER);		
		add(inf, BorderLayout.SOUTH); 
		
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		String accion = e.getActionCommand();
		
		if(accion.equals("PLAY")) {
			for(JButton b:letra)
				b.setEnabled(true);
			int posicion = (int) (Math.random() * palabras.size());
			adivinar = palabras.get(posicion).toCharArray();
			System.out.println(palabras.get(posicion));
			guiones = new char[adivinar.length];
			playGame.setBackground(Color.RED);
			linea = "";
			for (int i = 0; i < adivinar.length; i++) {
				guiones[i] = '_';
				linea += (guiones[i] + " ");
			}
			
			lblPalabra.setText(linea);				
			
			
			
		}else {
			char c = accion.charAt(0);
			boolean fallo = true;		
			linea = "";
			for(int i=0;i<adivinar.length;i++) {
				if(c==adivinar[i]) {
					guiones[i] = c;
					fallo = false;
				}
				linea += (guiones[i] + " ");
			}
			lblPalabra.setText(linea);		
			
			
			
			
			
			boolean fin = false;
			if(fallo) {
				fin = lienzo.incrementarFallos();
			}	
			
			for (int i = 0; i < letras.length();i++) {
				if(e.getSource()==letra[i]) {
					letra[i].setBackground(Color.RED);
				} 
		}
					
				if (fin) {				
					
					lblPalabra.setText("GAME OVER");	
					
					playGame.setBackground(Color.GREEN);				
					for(int i=0; i<letras.length();i++) {
					letra[i].setEnabled(false);
					letra[i].setBackground(marronclaro);
					}
					lienzo.reset();
										
					
					
				} else {
					boolean ganaste = true;
					for (char letra: guiones)
						if (letra == '_') {
							ganaste = false;
							break;
						}
					if (ganaste){					
						lblPalabra.setText("GANASTE");	
						playGame.setBackground(Color.GREEN);
						for(int i=0; i<letras.length();i++) {
						letra[i].setEnabled(false);
						letra[i].setBackground(marronclaro);
						}
						lienzo.reset();
					}
				}			
			
		}		
		}
		
		

	
}


