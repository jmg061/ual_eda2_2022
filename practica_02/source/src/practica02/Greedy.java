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
		
		Pavimento inicio = obtenerMenorCoste();
		PriorityQueue<Pavimento> result = new PriorityQueue<>();
		LinkedList<String> noVisitados = new LinkedList<>();
		//LinkedHashMap<String, Double> aux = new LinkedHashMap<>();
		ArrayList<Pavimento> aux= new ArrayList<>();
		Pavimento pav = null;
		
		for(String ciudad : this.net.getAdjacencyMap().keySet())
			noVisitados.add(ciudad);
		
		while(!noVisitados.isEmpty()) {
			if(inicio!=null) {
				TreeMap<String, Double> tm = this.net.getAdjacencyMap().get(inicio.getInicio());
				for(Entry<String, Double> entry : tm.entrySet())
					result.add(new Pavimento(inicio.getInicio(), entry.getKey(), entry.getValue(), tm.size()));
				noVisitados.remove(inicio.getInicio());
				//aux.put(inicio.getInicio(), inicio.getCoste());
				inicio=null;
			}else {
				//System.out.println("estoy dentro");
				//Pavimento pav = result.poll();
				//System.out.println(pav.getFin());
				if(!noVisitados.contains(pav.getFin())) continue;
				TreeMap<String, Double> tm = this.net.getAdjacencyMap().get(pav.getFin());
				//System.out.println(tm);
				for(Entry<String, Double> entry : tm.entrySet())
					result.add(new Pavimento(pav.getFin(), entry.getKey(), entry.getValue()));
				noVisitados.remove(pav.getFin());
				//System.out.println(result);
			}
			while(!result.isEmpty()) {
				aux.add(result.poll());
			}
			pav=aux.get(aux.size()-1);
			for(Pavimento city : aux)
			System.out.println(city);
		}
	}

}