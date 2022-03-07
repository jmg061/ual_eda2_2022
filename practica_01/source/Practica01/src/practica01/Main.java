package practica01;

import java.util.Collection;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DyV jugadores = new DyV();
		jugadores.load();
		jugadores.mergesort(jugadores.datos, 0, jugadores.datos.size() - 1);
		for(Jugador aux : jugadores.getDatos())
			System.out.println(aux);
		
		
	}
	

}
