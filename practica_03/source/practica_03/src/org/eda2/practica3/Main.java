package org.eda2.practica3;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Dinamica dinamica = new Dinamica(8,6, 16, 8);
		long inicio;
		long fin;
		double tiempo;
		
		//dinamica.pruebas2();
		//dinamica.Greed();

        dinamica.cargaDatos2();

//		dinamica.pruebas();
//		System.out.println("Dinamicon con Int");
//		inicio = System.currentTimeMillis();
//        dinamica.resolucion();
//        fin = System.currentTimeMillis();
//		tiempo = (double) ((fin - inicio));
//		System.out.println("Tiempo => " + tiempo);
//		System.out.println();
		
//		dinamica.pruebas2();
		System.out.println();
		System.out.println("Dinamicon con Double");
		inicio = System.currentTimeMillis();
        dinamica.resolucion2();
        fin = System.currentTimeMillis();
		tiempo = (double) ((fin - inicio));
		System.out.println("Tiempo => " + tiempo);
		
//		System.out.println();
//		System.out.println("Greed con Double");
//		inicio = System.currentTimeMillis();
//		dinamica.Greed();
//        fin = System.currentTimeMillis();
//		tiempo = (double) ((fin - inicio));
//		System.out.println("Tiempo => " + tiempo);

	}

}
