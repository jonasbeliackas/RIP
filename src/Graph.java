import javax.swing.JTable;

public class Graph {

	private final int weight = 1;
	private int V ; // Number of vertices in graph 
	private int E ;
	public Edge edge[];
	//private JTable table;

	class Edge { 
		int src, dest, weight; 
		Edge() 
		{ 
			src = dest = weight = 0; 
		} 
	}; 



	public Graph(int v, int e) 
	{ 
		//this.table = table;
		V = v; 
		E = e; 
		edge = new Edge[e]; 
		for (int i = 0; i < e; ++i) 
			edge[i] = new Edge(); 
	} 
	int[] BellmanFord(Graph graph, int src) 
	{ 
		int V = graph.V, E = graph.E; 
		int dist[] = new int[V]; 

		// Step 1: Initialize distances from src to all other 
		// vertices as INFINITE 
		for (int i = 0; i < V; ++i) 
			dist[i] = Integer.MAX_VALUE; 
		dist[src] = 0; 

		// Step 2: Relax all edges |V| - 1 times. A simple 
		// shortest path from src to any other vertex can 
		// have at-most |V| - 1 edges 
		for (int i = 1; i < V; ++i) { 
			for (int j = 0; j < E; ++j) { 
				int u = graph.edge[j].src; 
				int v = graph.edge[j].dest; 
				int weight = graph.edge[j].weight; 
				if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) 
					dist[v] = dist[u] + weight; 
			} 
		} 

		// Step 3: check for negative-weight cycles. The above 
		// step guarantees shortest distances if graph doesn't 
		// contain negative weight cycle. If we get a shorter 
		// path, then there is a cycle. 
		for (int j = 0; j < E; ++j) { 
			int u = graph.edge[j].src; 
			int v = graph.edge[j].dest; 
			int weight = graph.edge[j].weight; 
			if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) { 
				System.out.println("Graph contains negative weight cycle"); 
				return null; 
			} 
		} 
		printArr(dist, V); 
		return dist;
	} 

	// A utility function used to print the solution 
	void printArr(int dist[], int V) 
	{ 
		
		for (int i = 0; i < V; ++i) 
			System.out.println(i + "\t\t" + dist[i]); 
	} 



}
