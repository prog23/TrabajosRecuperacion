package Ahorcado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;






public class Juego extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private Lienzo lienzo;
	private String letras="abcdefghijklmnñopqrstuvwxyz";
	private Font font;	
	File archivo = new File ("/listado-general.txt");	
	Random azar = new Random();		
	
	private final Map<Integer, List<String>> diccionario;
	



	public Juego(Lienzo lienzo) throws FontFormatException, IOException {
		this.lienzo=lienzo;			
	
		
		InputStream in = getClass().getResourceAsStream ("/docktrin.ttf");
		font = Font.createFont(Font.PLAIN, in).deriveFont(30f);
		in.close();		
			
		setLayout(new BorderLayout());
		
		JPanel sup = new JPanel(new GridLayout(1, 1));		
		
		
		
		JLabel lblPalabra = new JLabel("PALABRA");
		
		lblPalabra.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30), 
								BorderFactory.createCompoundBorder (BorderFactory.createLineBorder(Color.BLACK),
										BorderFactory.createEmptyBorder(20, 20, 20, 20))));
		lblPalabra.setHorizontalAlignment(JLabel.CENTER);		
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

		
	
	
	
/*JButton btnNewButton = new JButton("New button");
btnNewButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
	}
});		
btnNewButton.setAction(action);
btnNewButton.setBounds(237, 11, 297, 156);		
*/
