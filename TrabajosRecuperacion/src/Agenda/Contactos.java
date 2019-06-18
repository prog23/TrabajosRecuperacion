package Agenda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Contactos{

	private static final long serialVersionUID = 1L;
	 static SortedMap<String,String>agenda=new TreeMap<>();	
	public String exec(String cmd) throws IOException{
		
		
	 
 
  String nombre=" ";
  String token=" ";
  String ruta=" ";
  String telefono=" ";
  
  boolean fin=false;
  do {
  	System.out.println(">");  
  	Scanner sc = new Scanner(cmd);
  	
  	int estado=0;
  	while(estado!=8) {
  		switch(estado) {
  		case 0:
				try {
					token = sc.skip("fin|buscar:|borrar:|guardar:|cargar:|\\p{L}+(\\s+\\p{L}+)*").match().group();
					if (token.equals("fin")) {
						estado = 8;
						fin = true;
					}
					else if (token.equals("buscar:"))
						estado = 4;
					else if (token.equals("borrar:"))
						estado = 3;
					else if (token.equals("guardar:"))
						estado = 2;
					else if (token.equals("cargar:"))
						estado = 7;
					else {
						nombre = token;
						estado = 1;
					}
				} catch (NoSuchElementException e) {
					System.out.println("Se esperaba los comandos -> 'buscar: guardar: borrar: cargar: fin o un nombre");
					estado = 8;
				}
				break;
			case 1:
				try {
					sc.skip("-");
					estado = 5;
				}catch (NoSuchElementException e) {
					System.out.println("Se esperaba el comando -> -");
					estado = 8;
				}
				break;
			case 2:
			
				try {
				
				
					
					ruta=sc.skip("C:/\\w+/\\w+/\\w+/\\w+").match().group();
					System.out.println(ruta);
					 escribir(ruta);
					 System.out.println("Guardado con éxito");
					estado = 8;
				}catch (NoSuchElementException e) {
					System.out.println("Se esperaba el comando -> ruta");
					estado = 8;
				}
				
				
           break;
			case 3:
				try {
					nombre=sc.skip("\\p{L}+").match().group();
					if(agenda.containsKey(nombre)) {
						agenda.remove(nombre);
						System.out.println("Se ha borrado con éxito");
					}else {
						System.out.println("No existe el contacto");
					}
					estado = 8;
				}catch (NoSuchElementException e) {
					System.out.println("Se esperaba el comando -> nombre borrar");
					estado = 8;
				}
           break;
			case 4:
				
				try {
					nombre=sc.skip("\\p{L}+").match().group();
				   
					
					if(agenda.containsKey(nombre)) {
						telefono=agenda.get(nombre);
						System.out.println(nombre+"->"+telefono);
					}else {
						System.out.println("Error, no existe "+nombre);
					}
					
					
					
					estado=8;
				}catch (NoSuchElementException e) {
					System.out.println("Se esperaba el comando -> nombre");
					estado = 8;
				}
           break;
			case 5:
				try {
				 telefono=sc.skip("\\d+").match().group();
				 if(!agenda.containsKey(nombre)) {
					 agenda.put(nombre, telefono);
				System.out.println("Se ha insertado correctamente "+nombre+"->"+telefono);
					 
				 }else {
					 agenda.put(nombre, telefono);
						System.out.println("Se ha actualizado correctamente "+nombre+"->"+telefono);
				 }
				 
				estado=8;
				}catch (NoSuchElementException e) {
					System.out.println("Se esperaba el comando -> telefono");
					estado = 8;
				}
           break;
			case 7:				
						try {
						ruta=sc.skip("C:/\\w+/\\w+/\\w+/\\w+").match().group();
						System.out.println(ruta);
						 leer(ruta);
						 estado=8;
						}catch (NoSuchElementException e) {
							System.out.println("Se esperaba el comando -> ruta");
							estado = 8;
						}					
				break;
  		}
  		
  		
  		
  		}
  	
  	}while(fin!=true);
  
   System.out.println("----MI AGENDA----");
return telefono;
  
  
	}
	public static void escribir(String ruta)  {
	
		
		FileWriter fw;
		try {
			fw = new FileWriter(ruta);
			BufferedWriter bw= new BufferedWriter(fw);
			PrintWriter pw=new PrintWriter(bw);
			
			Set<String> conjuntoClaves=agenda.keySet();
			Iterator<String> it=conjuntoClaves.iterator();
			
			while (it.hasNext()) {
				String clave=(String) it.next();
				String tf= agenda.get(clave);
				pw.println(clave+"-"+tf);
			}
			pw.close();
		} catch (IOException e) {
			System.out.println("No se ha encontrado el fichero donde lo quieres escribir");
		}
		
		
	}
	public static void leer(String ruta)  {
		
		File f=new File(ruta);
		try {
			FileReader filr=new FileReader(f);
			BufferedReader bufr=new BufferedReader(filr);
			String linea;
			while ((linea=bufr.readLine())!=null) {
				String [] datos=linea.split("-");
				agenda.put(datos[0], datos[1]);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido un error al leer");
		} catch (IOException e) {
			System.out.println("No existe el fichero");
		} 
		
	}

	}
	

