package practica02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map.Entry;
//import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.LinkedHashMap;

public class Greedy {
	
	private Network<String> net;
	private LinkedHashMap<String, LinkedHashMap<String, Double>> edaaux = new LinkedHashMap<>();
	
	public Greedy() {
		
		net = new Network<>();
		
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
	
	public void ConexoBase() {
		//Primero vamos a obtener la arista de menor coste
		//int menor = Integer.MAX_VALUE;
		//String inicio;
		//PriorityQueue<Pavimento> pq = new PriorityQueue<>();
		
		LinkedHashMap<String, Double> aux;
		Double peso;
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
			
		System.out.println(minimo);
		
		for(Entry<String, TreeMap<String, Double>> entry : this.net.getAdjacencyMap().entrySet()) {
			for(Entry<String, Double> entry2 : entry.getValue().entrySet()) {
				aux = this.edaaux.get(entry.getKey());
				if(aux == null) {
					edaaux.put(entry.getKey(), aux = new LinkedHashMap<>());
				}
				peso = aux.get(entry2.getKey());
				if(peso == null) {
					aux.put(entry2.getKey(), entry2.getValue());
				}
				
				//edaaux.add(entry.getKey(),  new TreeMap<String, Double>());
				
			}
			
		}
		
	}

}
