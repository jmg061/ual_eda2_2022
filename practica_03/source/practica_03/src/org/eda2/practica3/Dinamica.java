package org.eda2.practica3;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Dinamica {

	ArrayList<Tesoro> tesoros;
	ArrayList<Tesoro2> tesoros2;
	// Mochila mochila;
	int[][] matriz;
	int carga[];// = new int [n + 1] Indica los tesoros que se cargan en la mochila
	int maxCapacidad;
	int cantidadTesoros;
	int pesoMaximo;
	int beneficioMaximo;

	public Dinamica(int maxCapacidad, int cantidadTesoros, int pesoMaximo, int beneficioMaximo) {
		// mochila = new Mochila(maxCapacidad);
		matriz = new int[cantidadTesoros + 1][maxCapacidad + 1];
		carga = new int[cantidadTesoros + 1];
		this.maxCapacidad = maxCapacidad;
		this.cantidadTesoros = cantidadTesoros;
		this.pesoMaximo = pesoMaximo;
		this.beneficioMaximo = beneficioMaximo;
	}

	public void pruebas() {
		tesoros = new ArrayList<>();
		tesoros.add(new Tesoro("0", 0, 0));// Esta posicion no se utiliza
		tesoros.add(new Tesoro("A", 1, 2));
		tesoros.add(new Tesoro("B", 2, 5));
		tesoros.add(new Tesoro("C", 4, 6));
		tesoros.add(new Tesoro("D", 5, 10));
		tesoros.add(new Tesoro("E", 7, 13));
		tesoros.add(new Tesoro("F", 8, 16));

		tesoros.sort(null);

		/*
		 * for (Tesoro tesoro : tesoros) System.out.println(tesoro);
		 */
	}

	public void pruebas2() {
		tesoros2 = new ArrayList<>();
		tesoros2.add(new Tesoro2("0", 0, 0));// Esta posicion no se utiliza
		tesoros2.add(new Tesoro2("A", 0.24, 2));
		tesoros2.add(new Tesoro2("B", 1.25, 5));
		tesoros2.add(new Tesoro2("C", 3.75, 6));
		tesoros2.add(new Tesoro2("D", 5, 10));
		tesoros2.add(new Tesoro2("E", 7, 13));
		tesoros2.add(new Tesoro2("F", 7.75, 16));

		tesoros2.sort(null);

		/*
		 * for (Tesoro2 tesoro2 : tesoros2) System.out.println(tesoro2);
		 */
	}

	public void cargaDatos() {// Crea tesoros con pesos enteros

		// Inicializamos
		tesoros = new ArrayList<>();
		tesoros.add(new Tesoro("0", 0, 0));// Esta posicion no se utiliza
		// Rellenamos el arraylist con los tesoros generados aleatoriamente
		for (int i = 0; i < cantidadTesoros;) {
			String id = String.valueOf(i + 1);
			int peso = (int) Math.floor(Math.random() * pesoMaximo) + 1;
			int beneficio = (int) Math.floor(Math.random() * beneficioMaximo) + 1;
			if (peso > beneficio)
				continue;
			Tesoro tesoroAux = new Tesoro(id, peso, beneficio);
			if (!tesoros.contains(tesoroAux)) {
				tesoros.add(tesoroAux);
				i++;
			}
		}

		tesoros.sort(null);

		// Imprimimos el ArrayList para comprobar que todo esta correctamente
		/*
		 * for (Tesoro tesoro : tesoros) System.out.println(tesoro);
		 */
	}

	public void cargaDatos2() {// Crea tesoros con pesos reales

		// Inicializamos
		tesoros2 = new ArrayList<>();
		tesoros2.add(new Tesoro2("0", 0, 0));// Esta posicion no se utiliza
		// Rellenamos el arraylist con los tesoros generados aleatoriamente
		for (int i = 0; i < cantidadTesoros;) {
			String id = String.valueOf(i + 1);
			Random r = new Random();
			double peso = 1 + (pesoMaximo - 1) * r.nextDouble();
			// double peso = Math.floor(Math.random() * pesoMaximo) + 1;
			int beneficio = (int) Math.floor(Math.random() * beneficioMaximo) + 1;
			if (peso > beneficio)
				continue;
			Tesoro2 tesoroAux = new Tesoro2(id, peso, beneficio);
			if (!tesoros2.contains(tesoroAux)) {
				tesoros2.add(tesoroAux);
				i++;
			}
		}

		tesoros2.sort(null);

		// Imprimimos el ArrayList para comprobar que todo esta correctamente
		/*
		 * for (Tesoro2 tesoro2 : tesoros2) System.out.println(tesoro2);
		 */
	}

	public String[] resolucion() {//
		String[] result = new String[2];
		// System.out.println(tesoros);
		String aux = "";
		// Conjunto vacio
		for (int i = 0; i <= cantidadTesoros; i++)
			matriz[i][0] = 0;
		for (int j = 0; j <= maxCapacidad; j++)
			matriz[0][j] = 0;
		// Rellenamos la matriz
		for (int i = 1; i <= cantidadTesoros; i++) {// Comenzamos desde 1 ya que la posicion 0 no se utilizar
			for (int j = 1; j <= maxCapacidad; j++) {
				if (tesoros.get(i).getPeso() > j) // Si el peso > la capacidad de la mochila, no se carga
					matriz[i][j] = matriz[i - 1][j]; // El valor m�ximo no cambia
				else // Cargamos la mejor solucion en caso de que este elemento se pueda cargar
					matriz[i][j] = Math.max(matriz[i - 1][j],
							matriz[i - 1][j - tesoros.get(i).getPeso()] + tesoros.get(i).getBeneficio());
			}
		}
		// Imprime el valor de la matriz
		System.out.println("Valor de la matriz:");
		for (int i = 0; i <= cantidadTesoros; i++) {
			for (int j = 0; j <= maxCapacidad; j++) {
				// System.out.print(matriz[i][j]+"\t");
				aux += matriz[i][j] + "\t";
			}
			System.out.println();
			aux += "\n";
		}

		result[0] = aux;
		aux = "";

		// Complete el formulario x [i] para calcular el valor total
		int j = maxCapacidad;
		for (int i = cantidadTesoros; i > 0; i--) {
			if (matriz[i][j] > matriz[i - 1][j]) {// Si se carga el i-�simo �tem
				carga[i] = 1; // x [i] est� configurado
				j -= tesoros.get(i).getPeso(); // Peso de la mochila menos w [i]
			} else // si el �tem i-�simo no est� cargado
				carga[i] = 0; // x [i] se establece en 0, el peso de la mochila permanece sin cambios
		}
		// Imprime los tesoros introducidos
		System.out.println("Tesoros introducidos:");
		for (int i = 1; i <= cantidadTesoros; i++)
			if (carga[i] == 1) {
				// System.out.println(tesoros.get(i));
				aux += tesoros.get(i).toString() + "\n";
			}
		System.out.println("El valor total es: " + matriz[cantidadTesoros][maxCapacidad]);
		result[1] = aux;

		// System.out.println(result[0]);
		// System.out.println(result[1]);

		return result;
	}

	public String[] resolucion2() {
		// Conjunto vacio
		String[] result = new String[2];
		String aux = "";
		for (int i = 0; i <= cantidadTesoros; i++)
			matriz[i][0] = 0;
		for (int j = 0; j <= maxCapacidad; j++)
			matriz[0][j] = 0;
		// Rellenamos la matriz
		for (int i = 1; i <= cantidadTesoros; i++) {// Comenzamos desde 1 ya que la posicion 0 no se utiliza
			for (int j = 1; j <= maxCapacidad; j++) {
				if (tesoros2.get(i).getPeso() > j) // Si el peso > la capacidad de la mochila, no se carga
					matriz[i][j] = matriz[i - 1][j]; // El valor m�ximo no cambia
				else // Cargamos la mejor solucion en caso de que este elemento se pueda cargar
					matriz[i][j] = Math.max(matriz[i - 1][j],
							matriz[i - 1][j - (int) tesoros2.get(i).getPeso()] + tesoros2.get(i).getBeneficio());
				// int value = (int)tesoros2.get(i).getPeso();
				// System.out.println(value);

			}
		}
		// Imprime el valor de la matriz
		System.out.println("Valor de la matriz:");
		for (int i = 0; i <= cantidadTesoros; i++) {
			for (int j = 0; j <= maxCapacidad; j++) {
				System.out.print(matriz[i][j] + "\t");
				aux += matriz[i][j] + "\t";
			}
			System.out.println();
			aux += "\n";
		}

		result[0] = aux;
		aux = "";

		double j = maxCapacidad;
		for (int i = cantidadTesoros; i > 0; i--) {
			// int index=(int)j;
			// System.out.println(j);
			// System.out.println(matriz[i][(int)j]);
			// System.out.println(matriz[i - 1][(int)j]);
			// System.out.println(matriz[i][(int)j] > matriz[i - 1][(int)j]);
			if (matriz[i][(int) j] > matriz[i - 1][(int) j]) {// Si se carga el i-�simo �tem
				carga[i] = 1; // Elemento en la mochila
				j -= tesoros2.get(i).getPeso(); // Restamos el peso
				if (j > 0 && i != 1 && j % 1 != 0) // Obtenemos el indice peso con el que podemos trabajar en la
													// siguiente iteracion
					j += 1;
				j = (int) j;

				// System.out.println(j);
			} else
				carga[i] = 0; // No cambia el peso de la mochila
		}
		// imprime los objetos cargados
		System.out.println("Tesoros cargados:");
		for (int i = 1; i <= cantidadTesoros; i++)
			if (carga[i] == 1) {
				System.out.println(tesoros2.get(i));
				aux += tesoros2.get(i).toString() + "\n";
			}
		result[1] = aux;
		System.out.println();
		System.out.println("El valor total es: " + matriz[cantidadTesoros][maxCapacidad]);

		return result;
	}

	public String Greed() {
		Double PesoActualMuchila = 0.0;
		String result = "";
		PriorityQueue<Tesoro2> aux = new PriorityQueue<Tesoro2>();
		for (int i = 0; i < cantidadTesoros; i++) {
			aux.add(tesoros2.get(i));
		}

		for (int i = 1; i < cantidadTesoros; i++) {
			if ((PesoActualMuchila + aux.poll().getPeso()) < pesoMaximo) {
				carga[1] = 1;
			} else {
				carga[1] = 0;
			}
		}
		System.out.println("Objetos cargados:");
		double valortotal = 0;
		for (int i = 1; i < carga.length; i++)
			// System.out.print("x" + i + " = " + carga[i] + "\t");
			if (carga[i] == 1) {
				valortotal += this.tesoros2.get(i).getBeneficio();
				result+=tesoros2.get(i)+"\n";
			}
		System.out.print(result);
		System.out.println("El valor total es: " + valortotal);
		
		return result;
	}

}
