package Ejercicio3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ejercicio3 {
	public static void main(String[] args) throws IOException {
		Map<String, Map<String, Integer>> direccion = new HashMap<>();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String ip = null;
		String usuario = null;
		int mens = 0;
		boolean fin = false;
		boolean fin2 = false;
		String token;
		int estado = 0;
		do {
			System.out.println("Introducir los datos:");
			System.out.print("> ");
			Scanner s = new Scanner(in.readLine());
			do {
				switch (estado) {
				case 0:

					try {

						token = s.skip("fin|FIN|IP\\s*=\\(").match().group();
						if (token.equalsIgnoreCase("fin")) {
							fin = true;
							fin2 = true;
							break;
						} else {
							estado = 1;
						}
					} catch (NoSuchElementException e) {
						System.out.println("Se esperaba 'fin' o 'IP=('");
						System.out.println(" ");
						estado = 0;
						fin2 = true;
					}

				case 1:
					try {
						ip = s.skip("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)")
								.match().group();
						estado = 2;
					} catch (NoSuchElementException e) {
						System.out.println("Se esperaba una IP válida");
						System.out.println(" ");
						estado = 0;
						fin2 = true;
					}

				case 2:
					try {
						token = s.skip("\\)\\s*mensaje\\=\\(.*\\)\\s*usuario\\=\\(").match().group();
						estado = 3;
					} catch (NoSuchElementException e) {
						System.out.println("Se esperaba 'mensaje=' y 'usuario='");
						System.out.println(" ");
						estado = 0;
						fin2 = true;
					}

				case 3:

					try {
						usuario = s.skip("\\p{L}+").match().group();
						estado = 4;
					} catch (NoSuchElementException e) {
						System.out.println("Se esperaba nombre el nombre del usuario");
						System.out.println(" ");
						estado = 0;
						fin2 = true;
						break;
					}

				case 4:
					try {
						token = s.skip("\\)").match().group();
						if (direccion.containsKey(usuario)) {
							if (direccion.get(usuario).containsKey(ip)) {
								direccion.get(usuario).replace(ip, direccion.get(usuario).get(ip), direccion.get(usuario).get(ip) + 1);
								System.out.println("Nuevo mensaje del Usuario: " + usuario + " - IP de acceso: " + ip);
								System.out.println(" ");
								estado = 0;
								token = null;
								s.reset();
								fin2 = true;
							} else {
								direccion.get(usuario).put(ip, 1);
								System.out.println("Nueva IP: " + ip + " Usuario: " + usuario);
								System.out.println(" ");
								estado = 0;
								token = null;
								s.reset();
								fin2 = true;
							}
						} else {
							mens = 1;
							direccion.put(usuario, new HashMap<>());
							direccion.get(usuario).put(ip, mens);
							System.out.println("Nuevo Usuario: " + usuario + " IP de acceso: " + ip);
							System.out.println(" ");
							estado = 0;
							token = null;
							s.reset();
							fin2 = true;
						}

					} catch (NoSuchElementException e) {
						System.out.println("se esperaba ')'");
						System.out.println(" ");
						estado = 0;
						fin2 = true;
						break;
					}

				}

			} while (!fin2);
			s.close();
		} while (!fin);
		
		
	
		
		for (Entry<String, Map<String, Integer>> user : direccion.entrySet()) {
			int total1 = 0;
			int total2 = 0;
			String clave = user.getKey();
			System.out.println(clave+":");
			System.out.println(direccion.get(clave));
			
			for (Entry<String, Integer> ipKey : direccion.get(clave).entrySet()) {
				total1++;
				total2 = total2 + ipKey.getValue();	
			}
			System.out.println("Total de IP: " + total1);
			System.out.println("Total de mensajes: " + total2);
			System.out.println(" ");
		}
	}


	}

