package org.eda2.practica04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class Network<Vertex extends Comparable<Vertex>> implements Graph<Vertex>, Iterable<Vertex> {

	protected class PathNode implements Comparable<PathNode> {
	    private ArrayList<Vertex> res; // result
	    private int visitedVertices; // The number of the visited vertices
	    private double totalCost; // The total cost of the path taken so far
	    private double estimatedCost; // Representing the lower bound of this state. * Priority

	    PathNode(Vertex vertexToVisit) {
	        res = new ArrayList<Vertex>();
	        res.add(vertexToVisit);
	        visitedVertices = 1;
	        totalCost = 0.0;
	        estimatedCost = numberOfVertices() * minEdgeValue;
	    }

	    PathNode(PathNode parentPathNode) {
	        // Constructor copia
	    	this.res=new ArrayList<Vertex>(parentPathNode.res);
	    	this.visitedVertices=parentPathNode.visitedVertices;
	    	this.totalCost=parentPathNode.totalCost;
	    	this.estimatedCost=parentPathNode.estimatedCost;
	    }

	    @Override
	    public int compareTo(PathNode p) {
	        // El criterio de comparacion es 'estimatedCost' que se correponde con la prioridad
	    	if(this.estimatedCost==p.estimatedCost)
	    		return 0;
	    	return this.estimatedCost>p.estimatedCost ? 1 : -1;
	    }

	    public ArrayList<Vertex> getRes() {
	        return res;
	    }

	    public void addVertexRes(Vertex v) {
	        this.res.add(v);
	    }

	    public Vertex lastVertexRes() {
	        // Devuelve el ultimo vertice que se ha anadido al camino (ultimo elemento de 'res')
	    	return this.res.get(res.size()-1);

		}

	    public boolean isVertexVisited(Vertex v) {
	    	// Se ha visitado el vertice v si esta actualmente en 'res'. Una sola linea
	    	return this.res.contains(v);
		}

	    public int getVisitedVertices() {
	        return visitedVertices;
	    }

	    public void setVisitedVertices(int visitedVertices) {
	        this.visitedVertices = visitedVertices;
	    }

	    public double getTotalCost() {
	        return totalCost;
	    }

	    public void setTotalCost(double totalCost) {
	        this.totalCost = totalCost;
	    }

	    public double getEstimatedCost() {
	        return estimatedCost;
	    }

	    public void setEstimatedCost(double estimatedCost) {
	        this.estimatedCost = estimatedCost;
	    }
	}

	private boolean directed;
	
	private double minEdgeValue;
	
	private ArrayList<Vertex> shortestCircuit;
	
	private TreeMap<Vertex, TreeMap<Vertex, Double>> adjacencyMap;

	private String[] nodos;
	
	private ArrayList<List<String>> soluciones;

	public Network() {
		this.directed = true;
		this.adjacencyMap = new TreeMap<Vertex, TreeMap<Vertex, Double>>();
		this.minEdgeValue=this.minimumEdgeValue();
	}

	public Network(boolean uDOrD) {
		this.directed = uDOrD;
		this.adjacencyMap = new TreeMap<Vertex, TreeMap<Vertex, Double>>();
		this.minEdgeValue=this.minimumEdgeValue();
	}

	public void setDirected(boolean uDOrD) {
		this.directed = uDOrD;
	}

	public boolean getDirected() {
		return this.directed;
	}

	@Override
	public boolean isEmpty() {
		return this.adjacencyMap.isEmpty();
	}

	@Override
	public void clear() {
		this.adjacencyMap.clear();
	}

	@Override
	public int numberOfVertices() {
		return this.adjacencyMap.size();
	}

	@Override
	public int numberOfEdges() {
		int count = 0;
		for (TreeMap<Vertex, Double> itMap : this.adjacencyMap.values())
			count += itMap.size();
		return count;
	}

	@Override
	public boolean containsVertex(Vertex vertex) {
		return this.adjacencyMap.containsKey(vertex);
	}

	@Override
	public boolean containsEdge(Vertex v1, Vertex v2) {
		return this.adjacencyMap.containsKey(v1) && this.adjacencyMap.get(v1).containsKey(v2);
	}

	@Override
	public double getWeight(Vertex v1, Vertex v2) {
		TreeMap<Vertex, Double> neighbors = this.adjacencyMap.get(v1);
		return neighbors.get(v2) == null ? -1 : neighbors.get(v2);
	}

	@Override
	public double setWeight(Vertex v1, Vertex v2, double w) {
		TreeMap<Vertex, Double> neighbors = this.adjacencyMap.get(v1);
		if (neighbors == null)
			return -1;
		Double oldWeight = neighbors.get(v2);
		if (oldWeight == null)
			return -1;
		neighbors.put(v2, w);
		return oldWeight;
	}

	public boolean isAdjacent(Vertex v1, Vertex v2) {
		return (adjacencyMap.containsKey(v1) && adjacencyMap.get(v1).containsKey(v2));

	}

	public boolean addVertex(Vertex vertex) {
		if (this.adjacencyMap.containsKey(vertex))
			return false;
		this.adjacencyMap.put(vertex, new TreeMap<Vertex, Double>());
		return true;
	}

	public boolean addEdge(Vertex v1, Vertex v2, double w) {
		if (!containsVertex(v1) || !containsVertex(v2))
			return false;
		this.adjacencyMap.get(v1).put(v2, w);
		if (!this.directed) {
			this.adjacencyMap.get(v2).put(v1, w);
		}
		return true;
	}

	public boolean removeVertex(Vertex vertex) {
		if (!containsVertex(vertex))
			return false;
		for (TreeMap<Vertex, Double> ver : this.adjacencyMap.values())
			ver.remove(vertex);
		this.adjacencyMap.remove(vertex);
		return true;
	}

	public boolean removeEdge(Vertex v1, Vertex v2) {
		if (!containsEdge(v1, v2))
			return false;

		this.adjacencyMap.get(v1).remove(v2);

		if (!this.directed) {
			this.adjacencyMap.get(v2).remove(v1);
		}

		return true;
	}

	@Override
	public TreeSet<Vertex> vertexSet() {
		return new TreeSet<Vertex>(this.adjacencyMap.keySet());
	}

	/**
	 * Returns a LinkedList object of the neighbors of a specified Vertex object.
	 *
	 * @param v - the Vertex object whose neighbors are returned.
	 *
	 * @return a LinkedList of the vertices that are neighbors of v.
	 */

	public TreeSet<Vertex> getNeighbors(Vertex v) {
		TreeMap<Vertex, Double> neighbors = this.adjacencyMap.get(v);
		if (neighbors == null)
			return null;
		TreeSet<Vertex> aux = new TreeSet<Vertex>();
		for (Vertex ver : neighbors.keySet())
			aux.add(ver);
		return aux;
	}

	@Override
	public String toString() {
		return this.adjacencyMap.toString();
	}

	private TreeMap<Vertex, Vertex> Dijkstra(Vertex source, Vertex destination) {
		final double INFINITY = Double.MAX_VALUE;

		Double weight = .0, minWeight = INFINITY;
		TreeMap<Vertex, Double> D = new TreeMap<Vertex, Double>();
		TreeMap<Vertex, Vertex> S = new TreeMap<Vertex, Vertex>();
		TreeSet<Vertex> V_minus_S = new TreeSet<Vertex>();

		Vertex from = null;

		for (Vertex e : this.adjacencyMap.keySet()) {
			if (source.equals(e))
				continue;
			V_minus_S.add(e);
		}

		for (Vertex v : V_minus_S) {
			if (isAdjacent(source, v)) {
				S.put(v, source);
				D.put(v, getWeight(source, v));
			} else {
				S.put(v, null);
				D.put(v, INFINITY);
			}
		}

		S.put(source, source);
		D.put(source, 0.0);

		while (!V_minus_S.isEmpty()) {
			minWeight = INFINITY;
			from = null;
			for (Vertex v : V_minus_S) {
				if (D.get(v) < minWeight) {
					minWeight = D.get(v);
					from = v;
				}
			}
			if (from == null)
				break;

			V_minus_S.remove(from);

			for (Vertex v : V_minus_S) {
				if (isAdjacent(from, v)) {
					weight = getWeight(from, v);
					if (D.get(from) + weight < D.get(v)) {
						D.put(v, D.get(from) + weight);
						S.put(v, from);
					}
				}
			}
		}
		if (S.get(destination) == null) {
			throw new RuntimeException("The vertex " + destination + " is not reachable from " + source);
		}
		return S;
	}

	public ArrayList<Vertex> getDijkstra(Vertex source, Vertex destination) {
		ArrayList<Vertex> path = null;
		Stack<Vertex> pila = null;
		TreeMap<Vertex, Vertex> salidaDijkstra = null;

		if (source == null || destination == null)
			return null;

		if (source.equals(destination))
			return null;

		try {
			salidaDijkstra = Dijkstra(source, destination);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		path = new ArrayList<Vertex>();
		pila = new Stack<Vertex>();

		pila.push(destination);
		while (!pila.peek().equals(source)) {
			pila.push(salidaDijkstra.get(pila.peek()));
		}
		while (!pila.isEmpty()) {
			path.add(pila.pop());
		}
		return path;
	}

	public ArrayList<String> getDijkstraWithDistance(Vertex source, Vertex destination) {
		ArrayList<Vertex> path = getDijkstra(source, destination);
		ArrayList<String> pathWithDistance = null;
		double suma = .0;
		if (path == null)
			return null;

		pathWithDistance = new ArrayList<String>();

		pathWithDistance.add(path.get(0) + "=0.0");
		for (int i = 0; i < path.size() - 1; i++) {
			pathWithDistance.add(path.get(i + 1) + "=" + String.valueOf(this.getWeight(path.get(i), path.get(i + 1))));
			suma += this.getWeight(path.get(i), path.get(i + 1));
		}

		pathWithDistance.add(0, String.valueOf(suma));
		return pathWithDistance;
	}

	public ArrayList<Vertex> toArrayDFRecursive(Vertex start) {
		if (!containsVertex(start))
			return null;
		ArrayList<Vertex> result = new ArrayList<Vertex>();
		TreeMap<Vertex, Boolean> visited = new TreeMap<Vertex, Boolean>();
		for (Vertex v : this.adjacencyMap.keySet()) {
			visited.put(v, false);
		}

		toArrayDFRecursive(start, result, visited);
		return result;
	}

	private void toArrayDFRecursive(Vertex current, ArrayList<Vertex> result, TreeMap<Vertex, Boolean> visited) {
		result.add(current);
		visited.put(current, true);
		for (Vertex to : this.adjacencyMap.get(current).descendingKeySet()) {
			if (visited.get(to))
				continue;
			toArrayDFRecursive(to, result, visited);
		}
	}

	public ArrayList<Vertex> toArrayDF(Vertex start) {
		// ... que pasa si el vertice start no pertenece a la red
		if (!containsVertex(start))
			return null;
		ArrayList<Vertex> resultado = new ArrayList<Vertex>();
		Stack<Vertex> stack = new Stack<Vertex>();
		TreeMap<Vertex, Boolean> visited = new TreeMap<Vertex, Boolean>();
		for (Vertex vertex : this.adjacencyMap.keySet()) {
			visited.put(vertex, false);
		}
		Vertex current;
		stack.push(start);
		while (!stack.isEmpty()) {
			current = stack.pop();
			if (visited.get(current))
				continue;
			visited.put(current, true);
			resultado.add(current);
			for (Vertex to : this.adjacencyMap.get(current).keySet()) {
				if (visited.get(to))
					continue;
				stack.push(to);
			}
		}
		return resultado;
	}

	public ArrayList<Vertex> toArrayBF(Vertex start) {
		if (!containsVertex(start))
			return null;
		ArrayList<Vertex> resultado = new ArrayList<Vertex>();
		Queue<Vertex> stack = new LinkedList<Vertex>();
		TreeMap<Vertex, Boolean> visited = new TreeMap<Vertex, Boolean>();
		for (Vertex vertex : this.adjacencyMap.keySet()) {
			visited.put(vertex, false);
		}
		Vertex current;
		stack.add(start);
		while (!stack.isEmpty()) {
			current = stack.poll();
			if (visited.get(current))
				continue;
			visited.put(current, true);
			resultado.add(current);
			for (Vertex to : this.adjacencyMap.get(current).keySet()) {
				if (visited.get(to))
					continue;
				stack.add(to);
			}
		}
		return resultado;
	}

	@Override
	public Iterator<Vertex> iterator() {
		return this.adjacencyMap.keySet().iterator();
	}

	public ArrayList<Vertex> breadthFirstIterator(Vertex v) {
		if (!adjacencyMap.containsKey(v))
			return null;
		return this.toArrayBF(v);
	}

	public ArrayList<Vertex> depthFirstIterator(Vertex v) {
		if (!adjacencyMap.containsKey(v))
			return null;
		return this.toArrayDF(v);
	}

	public TreeMap<Vertex, TreeMap<Vertex, Double>> getAdjacencyMap() {
		return adjacencyMap;
	}
	
	public double minimumEdgeValue() {
  		double minimum = Double.MAX_VALUE;
  		// Devuelve el menor valor de arista del grafo
  		// Dos bucles 'for' anidados
  		for(Entry<Vertex, TreeMap<Vertex, Double>> entry : this.adjacencyMap.entrySet())
  			for(Entry<Vertex, Double> entry2 : entry.getValue().entrySet())
  				if(minimum > entry2.getValue())
  					minimum = entry2.getValue();

  		return minimum;
  	}

	// TSP - BaB - Best-First
		@SuppressWarnings("unchecked")
		public ArrayList<Vertex> TSPBaB(Vertex source) {
			TreeMap<Vertex, Double> neighborMap = adjacencyMap.get(source);
			if (neighborMap == null)
				return null;

			minEdgeValue = minimumEdgeValue();

			// Constructor de clase PathNode
			PathNode firstNode = new PathNode(source);

			PriorityQueue<PathNode> priorityQueue = new PriorityQueue<>();

			priorityQueue.add(firstNode);

			shortestCircuit = null;
			double bestCost = Double.MAX_VALUE;
			double costeTotal = 0;

			while(priorityQueue.size() > 0) {
				// Y (PathNode) = menorElemento de la cola de prioridad en funcion de 'estimatedCost'
				PathNode Y = priorityQueue.poll();
				if (Y.getEstimatedCost() >= bestCost)
	                break;
				else {
					Vertex from = Y.lastVertexRes();
					// Si el numero de vertices visitados es n
					// y existe una arista que conecte 'from' con source
					if ((Y.getVisitedVertices() == numberOfVertices()) &&
						(containsEdge(from, source))) {
						// Actualizar 'res' en Y añadiendo el vertice 'source'
						// Actualizar 'totalCost' en Y con Y.totalCost + weight(from, source)
						Y.res.add(source);
						Y.totalCost+=this.getWeight(from, source);
						if (Y.getTotalCost() < bestCost) {
							// Actualizar 'bestCost', 'shortestDistanceCircuit' y 'shortestCircuit'
							bestCost=Y.totalCost;
							shortestCircuit=Y.res;
							//shortestDistanceCircuit???????????

						}
					}
					else {
						// Iterar para todos los vertices adyacentes a from,
						// a cada vertice lo denominamos 'to'
						for(Vertex to : adjacencyMap.get(from).keySet()) {
							//if (vertice 'to' todavia no ha sido visitado en Y) { // hacer uso de la funcion 'isVertexVisited(vertex)' de PathNode
							if(!Y.isVertexVisited(to)) {
								PathNode X = new PathNode(Y); // Uso de constructor copia
								// Anadir 'to' a 'res' en X
								X.res.add(to);
								// Incrementar en 1 los vertices visitados en X
								X.visitedVertices++;
								// Actualizar 'totalCost' en X con Y.totalCost + weight(from, to)
								X.totalCost+=this.getWeight(from, to);
								// Actualizar 'estimatedCost' en X con X.totalCost + ((nVertices - X.getVisitedVertices() + 1) * minEdgeValue)
								X.estimatedCost+=((X.visitedVertices - X.getVisitedVertices() + 1))*minEdgeValue;
								
								if (X.getEstimatedCost() < bestCost) {
									priorityQueue.add(X);
									//System.out.println("Hola");
								}
							}
						}
					}
				}
				//System.out.println(Y.totalCost);
				costeTotal=Y.totalCost;
			}
			shortestCircuit.add((Vertex)String.valueOf(costeTotal));
			//System.out.println(shortestCircuit);
			return shortestCircuit;
		}
	
	//******************************************************************************************************************
	
	@SuppressWarnings("unchecked")
	public void load(String file) {

		BufferedReader br = null;
		String line;
		String[] items = null;
		int cont;

		try {

			br = new BufferedReader(new FileReader(file));
			line = br.readLine();
			while (line.isEmpty())
				line = br.readLine();
			cont = Integer.parseInt(line);

			if (cont == 0)
				this.setDirected(false);

			while ((line = br.readLine()) != null) {
				if (line.isEmpty())
					continue;
				cont = Integer.parseInt(line);
				nodos = new String[cont];
				for (int i = 0; i < cont;) {
					String nodo = br.readLine();
					if (nodo.isEmpty())
						continue;
					this.addVertex((Vertex)nodo);
					nodos[i] = nodo;
					i++;
				}
				line = br.readLine();
				while (line.isEmpty())
					line = br.readLine();
				cont = Integer.parseInt(line);

				for (int i = 0; i < cont;) {

					line = br.readLine();
					if (line.isEmpty())
						continue;
					items = line.split(" ");

					this.addEdge((Vertex)items[0], (Vertex)items[1], Integer.parseInt(items[2]));
					i++;
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

	public ArrayList<List<String>> viajero(String comienzo) {

		if(comienzo == null)
			comienzo = "Almeria";
		
		soluciones = new ArrayList<>();
		TreeMap<Vertex, Double> info = null;

		String[] camino = new String[nodos.length + 2];
		for (int i = 0; i < camino.length; i++)
			camino[i] = null;
		
		camino[0] = comienzo;
		viajeroRecursivo(0, 0, camino, info);

		return soluciones;

	}

	private void viajeroRecursivo(int posicion, double distancia, String[] caminoFinal,
			TreeMap<Vertex, Double> info) {
		
		if (caminoFinal[nodos.length - 1] != null) {

			info = this.getAdjacencyMap().get(caminoFinal[nodos.length - 1]);
			if (info.containsKey(caminoFinal[0])) {
				caminoFinal[nodos.length] = caminoFinal[0];
				double miDistancia = info.get(caminoFinal[0]) + distancia;
				caminoFinal[nodos.length + 1] = String.valueOf(miDistancia);

				soluciones.add(new ArrayList<String>(Arrays.asList(caminoFinal)));
			}


		} else {

			for (int i = 0; i < nodos.length; i++) {

				for (int x = posicion + 1; x < caminoFinal.length; x++)
					caminoFinal[x] = null;
				info = this.getAdjacencyMap().get(caminoFinal[posicion]);

				 if (!Arrays.asList(caminoFinal).contains(nodos[i]) && info.containsKey(nodos[i])) {

					caminoFinal[posicion + 1] = nodos[i];

					double miDistancia = info.get(nodos[i]) + distancia;

					viajeroRecursivo(posicion + 1, miDistancia, caminoFinal, info);
					
				}

			}

		}

	}
	
	public String[] menorCosteBacktracking() {
		if(this.soluciones==null || this.soluciones.isEmpty())
			return null;
		
		String[] sol = new String[this.soluciones.get(0).size()-1];
		sol=this.soluciones.get(0).toArray(sol);
		for(int i=1; i<this.soluciones.size(); i++) 
			if(Double.parseDouble(sol[sol.length-1])>Double.parseDouble(soluciones.get(i).get(soluciones.get(i).size()-1)))
				sol=this.soluciones.get(i).toArray(sol);
		
		return sol;
		
	}
	
}
