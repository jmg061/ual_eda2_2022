package practica02;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		Greedy greedy = new Greedy();
		ArrayList<Pavimento> resultados = new ArrayList<Pavimento>();

		greedy.load("graphEDAland.txt");
		System.out.println(greedy.getNet());
		
		System.out.println();
		System.out.println("ConexoConPQ");
		resultados = greedy.ConexoBase();
		for (Pavimento pavimento : resultados) {
			System.out.println("Inicio: " + pavimento.getInicio() + " Fin: " + pavimento.getFin() + "  Coste:" + pavimento.getCoste());
		}
		System.out.println();
		System.out.println("ConexoSinPQ");
		resultados = greedy.ConexoSinPQ();
		for (Pavimento pavimento : resultados) {
			System.out.println("Inicio: " + pavimento.getInicio() + " Fin: " + pavimento.getFin() + "  Coste:" + pavimento.getCoste());
		}
		System.out.println();
		System.out.println("NoConexo");
		resultados = greedy.NoConexo();
		for (Pavimento pavimento : resultados) {
			System.out.println("Inicio: " + pavimento.getInicio() + " Fin: " + pavimento.getFin() + "  Coste:" + pavimento.getCoste());
		}
		
		greedy.generadorDeRedes(false, 100, 200);

	}

}