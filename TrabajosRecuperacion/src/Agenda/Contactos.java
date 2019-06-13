package Agenda;

import java.io.File;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;

public class Contactos extends TreeMap<String, String>{

	private static final long serialVersionUID = 1L;
	
	private Map<String, String> mapa = new TreeMap<String, String>();
	public String exec(String cmd) {
		//String result = null;		
		Scanner s = new Scanner(cmd);
		int estado = 0;
		String mensaje="";
		String token;
		String nombre = null;
		while (estado != 5) {
			switch (estado) {
			case 0:
				try {
					token = s.skip("buscar|\\p{L}+(\\s+\\p{L}+)*").match().group();
					if (token.equals("buscar"))
						estado = 2;
					else {
						nombre = token;
						estado = 1;
					}
				} catch (NoSuchElementException e) {
					mensaje = "Se esperaba 'buscar' o 'fin' o un nombre";
					estado = 5;
				}
				break;
			case 1:
				try {
					s.skip("-");
					estado = 3;
				}catch (NoSuchElementException e) {
					mensaje = "Se esperaba '-'";
					estado = 5;
				}
				break;
			case 2:
				try {
					s.skip(":");
					estado = 4;
				}catch (NoSuchElementException e) {
					mensaje = "Se esperaba ':'";
					estado = 5;
				}
				break;
			case 3:
				try {
					token = s.skip("\\d{9}").match().group();
					put(nombre, token);
					estado = 5;
				}catch (NoSuchElementException e) {
					mensaje = "Se esperaba un teléfono";
					estado = 5;
				}
				break;
			case 4:
				try {
					token = s.skip("\\p{L}+(\\s+\\p{L}+)*").match().group();
					String telefono = get(token);
					if (telefono != null)
						mensaje = token + " -> " + telefono;
					else
						mensaje = token + " no se encuentra en la agenda";
					estado = 5;
				} catch (NoSuchElementException e) {
					mensaje = "Se esperaba un nombre";
					estado = 5;
				}
				break;
			}
		}
		
		return mensaje;
	}
	public Map<String, String> getMapa() {
		
		return mapa;
	}
	public void setMapa(String nombre, String telefono) {
		
		mapa.put(nombre, telefono);
		
	}
	public String sacarClave(String nombre) {
		
		return mapa.get(nombre);
	}
	
	
	
}
