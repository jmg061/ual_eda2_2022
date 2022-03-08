package practica01;

import java.util.Collection;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DyV jugadores = new DyV();
		jugadores.load();
		System.out.println(jugadores.datos.size());
		jugadores.mergesort();
		System.out.println(jugadores.datos.size());
		for(Jugador aux : jugadores.getDatos())
			System.out.println(aux);
//		System.out.println(jugadores.getDatos().get(0));
//		System.out.println(jugadores.getDatos().get(1));
//		System.out.println(jugadores.getDatos().get(2));
//		
		
	}
	

}