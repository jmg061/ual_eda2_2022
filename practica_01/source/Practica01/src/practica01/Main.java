package practica01;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DyV jugadores = new DyV();
		jugadores.load();
		//System.out.println(jugadores.getDatos().size());
		jugadores.mergesort();
		//System.out.println(jugadores.getDatos().size());
		for(int i=0; i<10; i++)
			System.out.println(jugadores.getDatos().get(i));
//		System.out.println(jugadores.getDatos().get(0));
//		System.out.println(jugadores.getDatos().get(1));
//		System.out.println(jugadores.getDatos().get(2));
//		
		
	}
	

}