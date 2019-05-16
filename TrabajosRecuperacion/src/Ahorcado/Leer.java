package Ahorcado;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Leer {
	
	public static ArrayList<String> leerLineas(String fichero) throws FileNotFoundException{
		File palabrasFichero = new File("/Res/listado-general.txt");
		Scanner teclado = new Scanner(new FileReader(palabrasFichero));
		ArrayList<String> palabras = new ArrayList<String>();
		 try {
			 while (teclado.hasNextLine()) {
				 String line = teclado.nextLine();
				 line = line.trim();
				 if (line.length() == 0) {
					 continue;
				 }
				 palabras.add(line.toLowerCase());
			 }
		 } 
		 finally {			 
			 teclado.close();		
			 
			
		 }
return palabras;
			
		
	}
		
	}
	

