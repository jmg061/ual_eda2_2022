package practica01;

import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DyV jugadores = new DyV();
		jugadores.load();
		// System.out.println(jugadores.getDatos().size());
		System.out.println("Con Heap:");
		long inicio = System.currentTimeMillis();
		PriorityQueue<Jugador> aux = jugadores.reduce();
		long fin = System.currentTimeMillis();
		 double tiempo = (double) ((fin - inicio));
		 System.out.println("Tiempo => " + tiempo);
		 DyV megsort = new DyV();
		 System.out.println("Con MergeSort");
		 megsort.load();
		 inicio = System.currentTimeMillis();
		 megsort.mergesort();
		 fin = System.currentTimeMillis();
		 tiempo = (double) ((fin - inicio));
		 System.out.println("Tiempo => " + tiempo);
//		 for (Jugador jugador : megsort.getDatos()) {
//			System.out.println(jugador.toString());
//		}
		 
		while (!aux.isEmpty())
			System.out.println(aux.poll());
		/*
		 * System.out.println("El otro:"); jugadores.mergesort(); for(int i=0; i<10;
		 * i++) System.out.println(jugadores.getDatos().get(i));
		 */
		// System.out.println(jugadores.getDatos().size());
		// for(int i=0; i<10; i++)
		// System.out.println(jugadores.getDatos().get(i));
		for (int i = 0; i < 10; i++) {
			System.out.println(megsort.getDatos().get(i));	
		}
		

	}

}