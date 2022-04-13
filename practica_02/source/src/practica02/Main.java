package practica02;

import java.awt.Container;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		Greedy greedy = new Greedy();
		ArrayList<Pavimento> resultados = new ArrayList<Pavimento>();

		greedy.load("graphEDAland.txt");
		System.out.println(greedy.getNet());
		
		System.out.println();
		System.out.println("ConexoConPQ");
		greedy.InicializarNodos();
		resultados = greedy.ConexoBase();
		for (Pavimento pavimento : resultados) {
			System.out.println("Inicio: " + pavimento.getInicio() + " Fin: " + pavimento.getFin() + "  Coste:" + pavimento.getCoste());
		}
		System.out.println();
		System.out.println("ConexoSinPQ");
		greedy.InicializarNodos();
		resultados = greedy.ConexoSinPQ();
		for (Pavimento pavimento : resultados) {
			System.out.println("Inicio: " + pavimento.getInicio() + " Fin: " + pavimento.getFin() + "  Coste:" + pavimento.getCoste());
		}
		System.out.println();
		System.out.println("NoConexo");
		greedy.InicializarNodos();
		resultados = greedy.NoConexo();
		for (Pavimento pavimento : resultados) {
			System.out.println("Inicio: " + pavimento.getInicio() + " Fin: " + pavimento.getFin() + "  Coste:" + pavimento.getCoste());
		}
		
//		Greedy greedy = new Greedy();
//		greedy.generadorDeRedes(false, 100, 200);

	}

}

