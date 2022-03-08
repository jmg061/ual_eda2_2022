package practica01;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DyV jugadores = new DyV();
		jugadores.load();
		for(Jugador aux : jugadores.getDatos())
			System.out.println(aux);
		Jugador[] arr = new Jugador[jugadores.getDatos().size()];
		jugadores.getDatos().toArray(arr);
		
	}

}
