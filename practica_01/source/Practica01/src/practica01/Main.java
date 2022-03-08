package practica01;

import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DyV jugadores = new DyV();
		jugadores.load();
		// System.out.println(jugadores.getDatos().size());
		System.out.println("El mio:");
		PriorityQueue<Jugador> aux = jugadores.reduce();
		while (!aux.isEmpty())
			System.out.println(aux.poll());
		/*
		 * System.out.println("El otro:"); jugadores.mergesort(); for(int i=0; i<10;
		 * i++) System.out.println(jugadores.getDatos().get(i));
		 */
		// System.out.println(jugadores.getDatos().size());
		// for(int i=0; i<10; i++)
		// System.out.println(jugadores.getDatos().get(i));
//		System.out.println(jugadores.getDatos().get(0));
//		System.out.println(jugadores.getDatos().get(1));
//		System.out.println(jugadores.getDatos().get(2));
//		

	}

}