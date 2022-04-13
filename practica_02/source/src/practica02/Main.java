package practica02;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		Greedy greedy = new Greedy();
		ArrayList<Pavimento> resultados = new ArrayList<Pavimento>();

		greedy.load("graphEDAlandLarge.txt");
//		System.out.println(greedy.getNet());
		
		System.out.println();
		System.out.println("ConexoConPQ");
		greedy.InicializarNodos();
		long inicio = System.currentTimeMillis();
		resultados = greedy.ConexoBase();
		long fin = System.currentTimeMillis();
		double tiempo = (double) ((fin - inicio));
		System.out.println("Tiempo => " + tiempo);
//		for (Pavimento pavimento : resultados) {
//			System.out.println("Inicio: " + pavimento.getInicio() + " Fin: " + pavimento.getFin() + "  Coste:" + pavimento.getCoste());
//		}
		System.out.println();
		System.out.println("ConexoSinPQ");
		greedy.InicializarNodos();
		inicio = System.currentTimeMillis();
		resultados = greedy.ConexoSinPQ();
		fin = System.currentTimeMillis();
		tiempo = (double) ((fin - inicio));
		System.out.println("Tiempo => " + tiempo);
//		for (Pavimento pavimento : resultados) {
//			System.out.println("Inicio: " + pavimento.getInicio() + " Fin: " + pavimento.getFin() + "  Coste:" + pavimento.getCoste());
//		}
		System.out.println();
		System.out.println("NoConexo");
		greedy.InicializarNodos();
		inicio = System.currentTimeMillis();
		resultados = greedy.NoConexo();
		fin = System.currentTimeMillis();
		tiempo = (double) ((fin - inicio));
		System.out.println("Tiempo => " + tiempo);
//		for (Pavimento pavimento : resultados) {
//			System.out.println("Inicio: " + pavimento.getInicio() + " Fin: " + pavimento.getFin() + "  Coste:" + pavimento.getCoste());
//		}

		greedy.generadorDeRedes(false, 100, 200);

	}
}
