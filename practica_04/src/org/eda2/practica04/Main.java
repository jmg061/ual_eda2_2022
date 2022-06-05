package org.eda2.practica04;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	private static long inicio;
	private static long fin;
	private static double tiempo;

	public static void main(String[] args) {
		// El primer argumento tiene que ser backtracking o bandb, con esto, decidimos
		// que algoritmo vamos a usar
		if ((args.length==3)) {

			Network<String> net = new Network<String>();
			if(!(new File(args[1])).exists()) 
				System.out.println("Error en la lectura del archivo");
			else
				net.load(args[1]);
			String camino;
			switch (args[0].toLowerCase()) {
			default:
				System.out.println("No existe la opcion");
				break;
			case "tsp_backtraking":
				
				inicio = System.currentTimeMillis();
				ArrayList<List<String>> caminos = net.viajero(args[2]==null ? "Almeria" : args[2]);
				fin = System.currentTimeMillis();
				tiempo = (double) ((fin - inicio));
				System.out.println("Tiempo => " + tiempo);
				
				System.out.println("Total caminos: "+caminos.size());
				
				camino = "";
				
				for(List<String> soluciones : caminos) {
				  
					System.out.println("***********CAMINO**********");
					
					for(String ciudad : soluciones)
						camino += ciudad+", ";
					System.out.println(camino.substring(0,camino.length()-2));
					camino="";
				}
				  
				  System.out.println("*************CAMINO CON MENOR COSTE****************");
				  
				  for(String ciudad : net.menorCosteBacktracking())
					  camino+=ciudad+" ,";
				  System.out.println(camino.substring(0, camino.length()-2));
				break;
			case "tsp_bab":
				camino="";
				inicio = System.currentTimeMillis();
				net.TSPBaB("Almeria");
				fin = System.currentTimeMillis();
				tiempo = (double) ((fin - inicio));
				System.out.println("Tiempo => " + tiempo);
				for(String ciudad : net.TSPBaB("Almeria"))
					camino+=ciudad+", ";
			  	System.out.println(camino.substring(0, camino.length()-2));
				break;

			}
		} else
			System.out.println("No se ha introducido el numero de argumentos necesarios");
	}
}