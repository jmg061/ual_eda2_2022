package practica02;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class Network<Vertex extends Comparable<Vertex>> implements Graph<Vertex>, Iterable<Vertex> {

	private boolean directed;

	private TreeMap<Vertex, TreeMap<Vertex, Double>> adjacencyMap;

	public Network() {
		this.directed = true;
		this.adjacencyMap = new TreeMap<Vertex, TreeMap<Vertex, Double>>();
	}

	public Network(boolean uDOrD) {
		this.directed = uDOrD;
		this.adjacencyMap = new TreeMap<Vertex, TreeMap<Vertex, Double>>();
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
	
	
	
}