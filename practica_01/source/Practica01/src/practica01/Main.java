package practica01;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DyV jugadores = new DyV();
		jugadores.load();
		System.out.println("Con Heap:");
		long inicio = System.currentTimeMillis();
		PriorityQueue<Jugador> aux = jugadores.reduce();
		long fin = System.currentTimeMillis();
		double tiempo = (double) ((fin - inicio));
		System.out.println("Tiempo => " + tiempo);
		
		while (!aux.isEmpty()) {
			System.out.println(aux.poll());
		}
		
		DyV megsort = new DyV();
		System.out.println("Con MergeSort");
		megsort.load();
		inicio = System.currentTimeMillis();
		megsort.mergesort();
		fin = System.currentTimeMillis();
		tiempo = (double) ((fin - inicio));
		System.out.println("Tiempo => " + tiempo);
		
//		for (int i = 0; i < 10; i++) {
//			System.out.println(megsort.getDatos().get(i));
//		}

		
		DyV dediezendiez = new DyV();
		System.out.println("Con DiezEnDiez");
		dediezendiez.load();
		inicio = System.currentTimeMillis();
		/*PriorityQueue<Jugador>*/ aux = dediezendiez.deDiezEnDiez();
		fin = System.currentTimeMillis();
		tiempo = (double) ((fin - inicio));
		System.out.println("Tiempo => " + tiempo);
		
//		while (!aux.isEmpty()) {
//			System.out.println(aux.poll());
//		}

		DyV megsort2 = new DyV();
		System.out.println("Con MergeSort2");
		megsort2.load();
		inicio = System.currentTimeMillis();
		ArrayList<Jugador> ArrayListAux2 = megsort2.mergesort2();
		DyV ObjetoAux = new DyV(ArrayListAux2);
		ObjetoAux.mergesort();
		fin = System.currentTimeMillis();
		tiempo = (double) ((fin - inicio));
		System.out.println("Tiempo => " + tiempo);
		
//		for (int i = 0; i < 10; i++) {
//		System.out.println(ObjetoAux.getDatos().get(i));
//	}

	}

}