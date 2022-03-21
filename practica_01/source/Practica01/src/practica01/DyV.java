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
	private Jugador[] arrAux = new Jugador[10];
	
	public DyV (ArrayList<Jugador> datos) {
		this.datos = datos;
	}

	public DyV() {

		this.datos = new ArrayList<>();

	}

	public void load() {
		for (int i = 0; i < 10; i++) {
			arrAux[i] = new Jugador(null, null, null, Integer.MIN_VALUE);
		}

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
				//System.out.println(Double.parseDouble(items[7].replace(",", ".")));
				double calculo;
				if(items[7].isEmpty()) calculo=0;
				else
				calculo=Double.parseDouble(items[7].replace(",", "."))/100*Integer.parseInt(items[8]);
				//System.out.println((int)calculo);
				aux = new Jugador(items[2], items[6], items[4], (int)calculo);
				if (!this.datos.contains(aux))
					// System.out.println(this.datos.contains(aux));
					this.datos.add(aux);
				else {
					//int calculo;
					if(items[7].isEmpty()) calculo=0;
					else
					calculo=Double.parseDouble(items[7].replace(",", "."))/100*Integer.parseInt(items[8]);
					aux = this.datos.get(this.datos.indexOf(aux));
					aux.setScore((int)(aux.getScore() + (int)calculo)/2);
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
	
	
	public PriorityQueue<Jugador> deDiezEnDiez(){
		
		PriorityQueue<Jugador> result = new PriorityQueue<>();
		ArrayList<PriorityQueue<Jugador>> aux = new ArrayList<>();
		
		for(int i = 0 ; i<this.datos.size(); i ++) {
			aux.add(new PriorityQueue<Jugador>());
			for(int cont = 0; cont<10; cont++) {
				if((10*i+cont)<this.datos.size())
					aux.get(i).add(this.datos.get(10*i+cont));
				//if((i+1)<this.datos.size())
					//i++;
			}
			
		}
		
		for(int i = aux.size()-1 ; i>=0 ; i--) {
			//System.out.println(i);
			
			if(i==aux.size()-1) {
				//System.out.println(aux.get(i));
				result.addAll(aux.get(i));
				//System.out.println(result.size());
				i--;
			}
			result.addAll(aux.get(i));
			while(result.size()>10) {
				result.poll();
				//System.out.println(result.size());
			}
			
		}
		
		return result;
	}
	
	public ArrayList<Jugador> mergesort2() {
		mergesort(0, this.datos.size()/2);
		mergesort(this.datos.size()/2 + 1, this.datos.size() - 1);
		ArrayList<Jugador> salida = new ArrayList<Jugador>();
		for (int i = 0; i < 10; i++) {
			salida.add(this.datos.get(i));
		}
		for (int j = this.datos.size()/2 + 1; j < this.datos.size()/2 + 11; j++) {
			salida.add(this.datos.get(j));
		}
		return salida;
		
	}
	

	private void mergesort2(int izq, int der) {
		if (izq < der && (der - izq) >= 1) {
			int medio = (izq + der) / 2;
			mergesort2(izq, medio);
			mergesort2(medio + 1, der);
			merge2(izq, medio, der);

		}
	}

	private void merge2(int izq, int medio, int der) {
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


	public ArrayList<Jugador> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<Jugador> datos) {
		this.datos = datos;
	}

	public Jugador[] getArrAux() {
		return arrAux;
	}

}
