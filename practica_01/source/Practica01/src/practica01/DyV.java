package practica01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class DyV {

	private ArrayList<Jugador> datos;

	public DyV() {

		this.datos = new ArrayList<>();

	}

	public void load() {

		BufferedReader br = null;
		String line;
		String[] items;
		Jugador aux = null;

		try {
			br = new BufferedReader(
					new FileReader("src" + File.separator + "practica01" + File.separator + "NbaStats.csv"));
			while ((line = br.readLine()) != null) {
				if (line.contains("#"))
					continue;
				items = line.split(";");
				System.out.println(Double.parseDouble(items[7]));
				aux = new Jugador(items[2], items[6], items[4], (int)(Double.parseDouble(items[7])/100*Integer.parseInt(items[8])));
				if (!this.datos.contains(aux))
					// System.out.println(this.datos.contains(aux));
					this.datos.add(aux);
				else {

					aux = this.datos.get(this.datos.indexOf(aux));
					aux.setScore((int)(aux.getScore() + Double.parseDouble(items[7])/100*Integer.parseInt(items[8])) / 2);
					if (!aux.getPositions().contains(items[4]))
						aux.getPositions().add(items[4]);
					if (!aux.getTeams().contains(items[6]))
						aux.getTeams().add(items[6]);
				}
				// for(String word : items) System.out.println(word);

			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void mergesort() {
		mergesort(0, this.datos.size() - 1);
	}

	private void mergesort(int izq, int der) {
		if (izq < der && (der - izq) >= 1) {
			int medio = (izq + der) / 2;
			mergesort(izq, medio);
			mergesort(medio + 1, der);
			merge(izq, medio, der);

		}
	}

	private void merge(int izq, int medio, int der) {
		int i, j, x;
		ArrayList<Jugador> aux = new ArrayList<Jugador>();

		j = medio + 1;
		x = izq;

		while (izq <= medio && j <= der) {
			if (this.datos.get(izq).getScore() > this.datos.get(j).getScore()) {
				aux.add(this.datos.get(izq));
				izq++;
			} else {
				aux.add(this.datos.get(j));
				j++;
			}
		}

		while (izq <= medio) {
			aux.add(this.datos.get(izq));
			izq++;

		}
		while (j <= der) {
			aux.add(this.datos.get(j));
			j++;
		}
		i = 0;
		while (i < aux.size()) {
			this.datos.set(x, aux.get(i++));
			x++;
		}

	}

	public PriorityQueue<Jugador> reduce() {

		PriorityQueue<Jugador> data = new PriorityQueue<>();
		ArrayList<ArrayList<Jugador>> aux = new ArrayList<>();
		ArrayList<ArrayList<Jugador>> dividir = null;
		// boolean seguir=true;

		while (aux.isEmpty() || aux.get(aux.size() - 1).size() != 1) {
			// if(!aux.isEmpty())System.out.println(aux.get(0).size());
			if (aux.isEmpty()) {
				dividir = this.divide(this.datos);
				aux.addAll(dividir);
				//// System.out.println(dividir);
				// System.out.println(aux);
			} else {
				dividir = new ArrayList<>();
				for (int i = 0; i < aux.size(); i++) {
					if (aux.get(i).size() > 1)
						dividir.addAll(divide(aux.get(i)));
					else
						dividir.add(aux.get(i));
				}
				aux = new ArrayList<>();
				aux.addAll(dividir);
			}
			/*int tam = 0;
			for (int i = 0; i < aux.size(); i++) {
				tam += aux.get(i).size();
			}*/
			// System.out.println(tam);
			/*
			 * seguir=false; for(int i = 0 ; i<aux.size(); i++) if(aux.get(i).size()>1) {
			 * seguir=true; continue; }
			 */
		}

		// System.out.println(aux.get(0));

		for (int i = 0; i < aux.size(); i++) {
			data.add(aux.get(i).get(0));
			while (data.size() > 10)
				data.poll();

		}

		return data;

	}

	private ArrayList<ArrayList<Jugador>> divide(ArrayList<Jugador> jugadores) {

		ArrayList<ArrayList<Jugador>> data = new ArrayList<ArrayList<Jugador>>();
		ArrayList<Jugador> aux = new ArrayList<Jugador>();

		int mitad = jugadores.size() / 2;
		// System.out.println(mitad);

		for (int i = 0; i < mitad; i++) {
			aux.add(jugadores.get(i));
		}

		data.add(new ArrayList<Jugador>(aux));
		// System.out.println(data);
		aux.clear();
		for (; mitad < jugadores.size(); mitad++) {
			aux.add(jugadores.get(mitad));
		}

		data.add(new ArrayList<Jugador>(aux));
		aux.clear();
		// System.out.println(data);
		return data;
	}

	public ArrayList<Jugador> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<Jugador> datos) {
		this.datos = datos;
	}

}
