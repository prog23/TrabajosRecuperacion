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
import java.io.LineNumberReader;
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
	JButton[] letra = new JButton[letras.length()];
	JButton playGame = new JButton("Jugar");
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
		font = Font.createFont(Font.PLAIN, in).deriveFont(30f);
		in.close();		
			
		setLayout(new BorderLayout());
		
		JPanel sup = new JPanel(new GridLayout(1, 1));		
					
		
		
		lblPalabra.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30), 
								BorderFactory.createCompoundBorder (BorderFactory.createLineBorder(Color.BLACK),
										BorderFactory.createEmptyBorder(20, 20, 20, 20))));
			
		lblPalabra.setFont(font);	
		sup.add(lblPalabra);		
		
		
		JPanel inf = new JPanel (new GridLayout(4, 7));
		inf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30), BorderFactory.createBevelBorder(BevelBorder.RAISED)));
		
		
	
		
		
		for(int i=0; i<letras.length();i++) {
			letra[i] = new JButton(letras.substring(i, i + 1));
			letra[i].setFont(font);
			inf.add(letra[i]);
			letra[i].addActionListener(this);
			letra[i].setEnabled(false);
		}	
		
		inf.add(playGame);	
		playGame.addActionListener(this);
		add(sup, BorderLayout.CENTER);		
		add(inf, BorderLayout.SOUTH); 
	
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		String accion = e.getActionCommand();
		
		if(accion.equals("Jugar")) {
			for(JButton b:letra)
				b.setEnabled(true);
			int posicion = (int) (Math.random() * palabras.size());
			adivinar = palabras.get(posicion).toCharArray();	
			guiones = new char[adivinar.length];
			playGame.setBackground(Color.RED);
				for (int i = 0; i < adivinar.length; i++) {
					guiones[i] = '_';
					linea += guiones[i] = '_';
					System.out.print(guiones);
					lblPalabra.setText(linea);
				}
			
		}else {
			char c = accion.charAt(0);
			boolean fallo = true;
			//boolean encontrada = true;
			for(int i=0;i<adivinar.length;i++) {
				if(c==adivinar[i]) {
					guiones[i] = c;
					fallo = false;
				}
			}
			
			if(fallo) {
				lienzo.incrementarFallos();
			}
			else {
				lblPalabra.setText(String.valueOf(guiones));		
			}
			
					
				if (Lienzo.fallos >=11) {					
					lblPalabra.setText("GAME OVER");
				}
				
				else if (aciertos==linea.length()){
					lblPalabra.setText("GANASTE");
				}
				
			//aciertos==guiones.length
			
		}		
		}
		
		

	
}


