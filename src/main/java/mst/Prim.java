package mst;

import graph.AdjacencyMatrix;

public class Prim {

	public static void main(String[] args) {
		String vertexs[] = {"A", "B", "C", "D", "E","F","G"};
		AdjacencyMatrix graph = new AdjacencyMatrix(vertexs.length);
		
		for (String vertex : vertexs) {
			
			graph.addVertex(vertex);
		}
		
		int[][] edges = graph.getEdges();
		for (int i = 0; i < edges.length; i++) {
			for (int j = 0; j < edges[i].length; j++) {
				edges[i][j]=Integer.MAX_VALUE;
			}
		}
		graph.addEdge(0, 1, 5);
		graph.addEdge(0, 2, 7);
		graph.addEdge(0, 6, 2);
		graph.addEdge(1, 3, 9);
		graph.addEdge(1, 6, 3);
		graph.addEdge(2, 4, 8);
		graph.addEdge(3, 5, 4);
		graph.addEdge(4, 5, 5);
		graph.addEdge(4, 6, 4);
		graph.addEdge(5, 6, 6);
		
		graph.show();
		
		graph.prim(0);
		
		
	}
}
