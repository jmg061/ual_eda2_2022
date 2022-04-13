package practica02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.PriorityQueue;
//import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.util.LinkedList;
import java.nio.charset.StandardCharsets;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

public class Greedy {

	private Network<String> net;
	// private LinkedHashMap<String, LinkedHashMap<String, Double>> edaaux;// = new
	// LinkedHashMap<>();

	public Greedy() {

		net = new Network<>();
		// edaaux = new LinkedHashMap<>();

	}

	public void load(String file) {

		BufferedReader br = null;
		String line;
		String[] items = null;
		int cont;

		try {

			br = new BufferedReader(new FileReader(file));

			cont = Integer.parseInt(br.readLine());

			if (cont == 0)
				net.setDirected(false);

			while ((line = br.readLine()) != null) {

				cont = Integer.parseInt(line);

				for (int i = 0; i < cont; i++) {
					// line=br.readLine();
					// System.out.println(line +" cont: "+cont+" i: " + i);
					net.addVertex(br.readLine());

				}

				cont = Integer.parseInt(br.readLine());

				for (int i = 0; i < cont; i++) {

					line = br.readLine();
					items = line.split(" ");
					// for(String word : items)
					// System.out.println(word);
					net.addEdge(items[0], items[1], Integer.parseInt(items[2]));

				}
				items = null;

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

	public Network<String> getNet() {
		return net;
	}

	private Pavimento obtenerMenorCoste() {

		// LinkedHashMap<String, Double> aux;
		// Double peso;
		Pavimento minimo = new Pavimento();

		// Obtenemos la arista de menor coste
		for (Entry<String, TreeMap<String, Double>> entry : this.net.getAdjacencyMap().entrySet()) {
			for (Entry<String, Double> entry2 : entry.getValue().entrySet()) {

				if (entry2.getValue() < minimo.getCoste()) {
					// if(entry.getValue().size()<minimo.getCaminos())
					minimo.setInicio(entry.getKey());
					minimo.setFin(entry2.getKey());
					minimo.setCoste(entry2.getValue());
					minimo.setCaminos(entry.getValue().size());
				}

				if (entry2.getValue() == minimo.getCoste()) {
					if (entry.getValue().size() < minimo.getCaminos())
						minimo.setInicio(entry.getKey());
					minimo.setFin(entry2.getKey());
					minimo.setCoste(entry2.getValue());
					minimo.setCaminos(entry.getValue().size());
				}

			}
		}

		return minimo;

	}

	public ArrayList<Pavimento> ConexoBase() {

		ArrayList<Pavimento> result = new ArrayList<>();
		Pavimento pav = obtenerMenorCoste();
		PriorityQueue<Pavimento> cola = new PriorityQueue<>();
		LinkedList<String> Nodos = new LinkedList<>();
		LinkedList<String> Visitados = new LinkedList<>();

		for (String city : this.net.getAdjacencyMap().keySet())
			Nodos.add(city);

		Visitados.add(pav.getInicio());

		for (String ciudad : this.net.getAdjacencyMap().get(pav.getFin()).keySet()) {
			if (Visitados.contains(ciudad))
				continue;
			Pavimento aux = new Pavimento(pav.getFin(), ciudad, this.net.getWeight(pav.getFin(), ciudad));
			cola.add(aux);
		}

		while (Nodos.size() != Visitados.size()) {
			if (!Visitados.contains(pav.getFin())) {
				Visitados.add(pav.getFin());
				result.add(pav);
				for (String ciudad : this.net.getAdjacencyMap().get(pav.getFin()).keySet()) {
					if (Visitados.contains(ciudad))
						continue;
					Pavimento aux = new Pavimento(pav.getFin(), ciudad, this.net.getWeight(pav.getFin(), ciudad));
					cola.add(aux);
				}
			}
			pav = cola.poll();

		}

		return result;
	}

	public ArrayList<Pavimento> ConexoSinPQ() {

		ArrayList<Pavimento> result = new ArrayList<>();
		Pavimento pav = obtenerMenorCoste();
		LinkedList<Pavimento> cola = new LinkedList<>();
		LinkedList<String> Nodos = new LinkedList<>();
		LinkedList<String> Visitados = new LinkedList<>();

		for (String city : this.net.getAdjacencyMap().keySet())
			Nodos.add(city);

		Visitados.add(pav.getInicio());

		for (String ciudad : this.net.getAdjacencyMap().get(pav.getFin()).keySet()) {
			if (Visitados.contains(ciudad))
				continue;
			Pavimento aux = new Pavimento(pav.getFin(), ciudad, this.net.getWeight(pav.getFin(), ciudad));
			cola.add(aux);

			mergesort(cola);
		}

		while (Nodos.size() != Visitados.size()) {
			if (!Visitados.contains(pav.getFin())) {
				Visitados.add(pav.getFin());
				result.add(pav);
				for (String ciudad : this.net.getAdjacencyMap().get(pav.getFin()).keySet()) {
					if (Visitados.contains(ciudad))
						continue;
					Pavimento aux = new Pavimento(pav.getFin(), ciudad, this.net.getWeight(pav.getFin(), ciudad));
					cola.add(aux);
					mergesort(cola);
				}
			}
			pav = cola.poll();

		}

		return result;
	}

	public ArrayList<Pavimento> NoConexo() {

		ArrayList<Pavimento> result = new ArrayList<>();
		Pavimento pav = new Pavimento();
		PriorityQueue<Pavimento> cola = new PriorityQueue<>();
		LinkedList<String> Nodos = new LinkedList<>();
		LinkedList<String> Visitados = new LinkedList<>();

		for (String city : this.net.getAdjacencyMap().keySet()) {
			Nodos.add(city);

			for (String ciudad : this.net.getAdjacencyMap().get(city).keySet()) {
				Pavimento aux = new Pavimento(city, ciudad, this.net.getWeight(city, ciudad));
				cola.add(aux);
			}
		}

		while (Nodos.size() != Visitados.size()) {
			pav = cola.poll();
			if (!Visitados.contains(pav.getFin()) || !Visitados.contains(pav.getInicio())) {
				result.add(pav);
				if (!Visitados.contains(pav.getFin()))
					Visitados.add(pav.getFin());
				if (!Visitados.contains(pav.getInicio()))
					Visitados.add(pav.getInicio());
			}
		}

		return result;
	}

	public void mergesort(LinkedList<Pavimento> datos) {
		mergesort(datos, 0, datos.size() - 1);
		// return datos;
	}

	private void mergesort(LinkedList<Pavimento> datos, int izq, int der) { // O(n*log(n))
		if (izq < der && (der - izq) >= 1) {
			int medio = (izq + der) / 2;
			mergesort(datos, izq, medio); // log(n)
			mergesort(datos, medio + 1, der); // log(n)
			merge(datos, izq, medio, der); // n

		}

		// return datos;
	}

	private void merge(LinkedList<Pavimento> datos, int izq, int medio, int der) {
		int i, j, x;
		LinkedList<Pavimento> aux = new LinkedList<Pavimento>();

		j = medio + 1;
		x = izq;

		while (izq <= medio && j <= der) {
			if (datos.get(izq).getCoste() > datos.get(j).getCoste()) {
				aux.add(datos.get(izq));
				izq++;
			} else {
				aux.add(datos.get(j));
				j++;
			}
		}

		while (izq <= medio) {
			aux.add(datos.get(izq));
			izq++;

		}
		while (j <= der) {
			aux.add(datos.get(j));
			j++;
		}
		i = 0;
		while (i < aux.size()) {
			datos.set(x, aux.get(i++));
			x++;
		}

	}

	public void generadorDeRedes(boolean dirigido, int ciudades, int caminos) {

		if (caminos < ciudades)
			return;

		TreeMap<String, TreeMap<String, Integer>> mapa = new TreeMap<>();
		TreeMap<String, Integer> interno;
		ArrayList<Pavimento> aux = new ArrayList<>();
		Pavimento pavimento;
		String red = "";
		BufferedWriter bw;

		if (dirigido == true)
			red += "1\n";
		else
			red += "0\n";

		red += ciudades + "\n";

		for (int i = 1; i <= ciudades; i++) {
			red += i + "\n";
		}

		red += caminos + "\n";

		for (int i = 1; i <= ciudades;) {

			int vertice1 = i;
			int vertice2 = (int) Math.floor(Math.random() * (ciudades - 1 + 1) + 1);

			while (vertice1 == vertice2)
				vertice2 = (int) Math.random() * (ciudades - 1 + 1) + 1;

			pavimento = new Pavimento(String.valueOf(vertice1), String.valueOf(vertice2),
					(int) Math.random() * (510 - 90 + 1) + 90);

			if (!aux.contains(pavimento)) {
				aux.add(pavimento);
				i++;
			}
		}

		while (aux.size() < caminos) {

			int vertice1 = (int) Math.floor(Math.random() * (ciudades - 1 + 1) + 1);
			int vertice2 = (int) Math.floor(Math.random() * (ciudades - 1 + 1) + 1);

			while (vertice1 == vertice2)
				vertice2 = (int) Math.random() * (ciudades - 1 + 1) + 1;

			pavimento = new Pavimento(String.valueOf(vertice1), String.valueOf(vertice2),
					(int) Math.random() * (510 - 90 + 1) + 90);

			if (!aux.contains(pavimento))
				aux.add(pavimento);
		}

		for (Pavimento pav : aux) {
			interno = mapa.get(pav.getInicio());
			if (interno == null)
				mapa.put(pav.getInicio(), interno = new TreeMap<>());
			interno.put(pav.getFin(), (int) Math.floor(Math.random() * (490 - 90 + 1) + 90));

		}

		for (int i = 1; i <= ciudades; i++) {
			for (Entry<String, Integer> entry : mapa.get(String.valueOf(i)).entrySet()) {
				red += i + " " + entry.getKey() + " " + entry.getValue() + "\n";
			}
		}

		red = red.substring(0, red.length() - 1);

		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("MiRed"), StandardCharsets.UTF_8));
			bw.write(red);
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
