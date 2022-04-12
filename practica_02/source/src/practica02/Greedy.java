package practica02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.PriorityQueue;
//import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Greedy {
	
	private Network<String> net;
	//private LinkedHashMap<String, LinkedHashMap<String, Double>> edaaux;// = new LinkedHashMap<>();
	
	public Greedy() {
		
		net = new Network<>();
		//edaaux = new LinkedHashMap<>();
		
	}
	
	public void load(String file) {
		
		BufferedReader br = null;
		String line;
		String[] items = null;
		int cont;
		
		try {
			
			br = new BufferedReader(new FileReader(file));
			
			cont = Integer.parseInt(br.readLine());
			
			
			if(cont==0) 
				net.setDirected(false);
			
				while((line = br.readLine()) != null) {
					
					cont = Integer.parseInt(line);
					
					for(int i = 0; i<cont; i++) {
						//line=br.readLine();
						//System.out.println(line +" cont: "+cont+" i: " + i);
						net.addVertex(br.readLine());
						
					}
					
					cont = Integer.parseInt(br.readLine());
					
					for(int i = 0; i<cont; i++) {
						
						line = br.readLine();
						items = line.split(" ");
						//for(String word : items)
							//System.out.println(word);
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
		
		//LinkedHashMap<String, Double> aux;
		//Double peso;
		Pavimento minimo = new Pavimento();
		
		//Obtenemos la arista de menor coste
		for(Entry<String, TreeMap<String, Double>> entry : this.net.getAdjacencyMap().entrySet()) {
			for(Entry<String, Double> entry2 : entry.getValue().entrySet()) {
				
				if(entry2.getValue()<minimo.getCoste()) {
					//if(entry.getValue().size()<minimo.getCaminos())
					minimo.setInicio(entry.getKey());
					minimo.setFin(entry2.getKey());
					minimo.setCoste(entry2.getValue());
					minimo.setCaminos(entry.getValue().size());
				}
				
				if(entry2.getValue()==minimo.getCoste()) {
					if(entry.getValue().size()<minimo.getCaminos())
					minimo.setInicio(entry.getKey());
					minimo.setFin(entry2.getKey());
					minimo.setCoste(entry2.getValue());
					minimo.setCaminos(entry.getValue().size());
				}
					
				
			}
		}
		
		return minimo;
		
	}
	
	public void ConexoBase() {
		
		ArrayList<Pavimento> result= new ArrayList<>();
		Pavimento pav = obtenerMenorCoste();
		PriorityQueue<Pavimento> cola = new PriorityQueue<>();
		LinkedList<String> Nodos = new LinkedList<>();
		LinkedList<String> Visitados = new LinkedList<>();

		
		for(String city : this.net.getAdjacencyMap().keySet())
			Nodos.add(city);
		
		Visitados.add(pav.getInicio());
		
		for (String ciudad : this.net.getAdjacencyMap().get(pav.getFin()).keySet()) {
			if (Visitados.contains(ciudad)) continue;
			Pavimento aux = new Pavimento(pav.getFin(), ciudad, this.net.getWeight(pav.getFin(), ciudad));
			cola.add(aux);
			}
		
		
		
		while (Nodos.size() != Visitados.size()){
			if (!Visitados.contains(pav.getFin())){
			Visitados.add(pav.getFin());
			result.add(pav);
			for (String ciudad : this.net.getAdjacencyMap().get(pav.getFin()).keySet()) {
				if (Visitados.contains(ciudad)) continue;
				Pavimento aux = new Pavimento(pav.getFin(), ciudad, this.net.getWeight(pav.getFin(), ciudad));
				cola.add(aux);
				}
			}
			pav = cola.poll();
			
		}
		
		System.out.println(result.size());
		for(Pavimento aux : result)
			System.out.println(aux);
	}
	
public void NoConexo() {
		
		ArrayList<Pavimento> result= new ArrayList<>();
		Pavimento pav = new Pavimento();
		PriorityQueue<Pavimento> cola = new PriorityQueue<>();
		LinkedList<String> Nodos = new LinkedList<>();
		LinkedList<String> Visitados = new LinkedList<>();

		
		for(String city : this.net.getAdjacencyMap().keySet()) {
			Nodos.add(city);
			
			for (String ciudad : this.net.getAdjacencyMap().get(city).keySet()) {
				Pavimento aux = new Pavimento(city, ciudad, this.net.getWeight(city, ciudad));
				cola.add(aux);
				}
		}

		while (Nodos.size() != Visitados.size()){
			pav = cola.poll();
			if (!Visitados.contains(pav.getFin()) || !Visitados.contains(pav.getInicio())) {
				result.add(pav);
				if (!Visitados.contains(pav.getFin())) Visitados.add(pav.getFin());
				if (!Visitados.contains(pav.getInicio())) Visitados.add(pav.getInicio());
			}
		}
		System.out.println(result.size());
		for(Pavimento aux : result)
			System.out.println(aux);
	}
}



